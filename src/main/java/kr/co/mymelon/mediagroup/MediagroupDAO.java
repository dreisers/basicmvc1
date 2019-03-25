package kr.co.mymelon.mediagroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.cj.api.mysqla.result.Resultset;

import net.utility.DBClose;
import net.utility.DBOpen;
import net.utility.Utility;

@Component
public class MediagroupDAO {
	@Autowired // Component 어노테이션으로 자동생성된 객체를
				// 사용하려면 객체간 서로 연결이 되어야 한다.
	private DBOpen dbopen;
	@Autowired
	private DBClose dbclose;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuffer sql = null;
	ArrayList<MediagroupDTO> list = null;

	public MediagroupDAO() {
		System.out.println("--- MediagroupDAO() 객체 생성");
	}

	public int create(MediagroupDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" INSERT INTO mediagroup(mediagroupno, title)");
			sql.append(" VALUES((SELECT NVL(MAX(mediagroupno),0)+1 FROM mediagroup), ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			cnt = pstmt.executeUpdate();
			//System.out.println("씨엔티"+cnt);
		} catch (Exception e) {
			System.out.println("추가실패" + e);
		} finally {
			dbclose.close(con, pstmt);
		}
		return cnt;
	}

	public ArrayList<MediagroupDTO> list() {
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" SELECT mediagroupno, title");
			sql.append(" FROM mediagroup");
			sql.append(" ORDER BY mediagroupno DESC");

			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				list = new ArrayList<MediagroupDTO>();
				do {
					MediagroupDTO dto = new MediagroupDTO();
					dto.setMediagroupno(rs.getInt("mediagroupno"));
					dto.setTitle(rs.getString("title"));
					list.add(dto);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("목록 실패" + e);
		} finally {
			dbclose.close(con, pstmt, rs);
		}
		return list;
	}
	
	public int delete(MediagroupDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" DELETE FROM mediagroup");
			sql.append(" WHERE mediagroupno = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, dto.getMediagroupno());
			cnt = pstmt.executeUpdate();
						
		}catch (Exception e) {
			System.out.println("삭제실패" + e);
		}finally {
			dbclose.close(con, pstmt);
		}
		return cnt;
	}
	
	public int update(MediagroupDTO dto) {
		int cnt = 0;
		try {
			con = dbopen.getConnection();
			sql = new StringBuffer();
			sql.append(" UPDATE mediagroup");
			sql.append(" SET title = ?");
			sql.append(" WHERE mediagroupno = ?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle());
			pstmt.setInt(2, dto.getMediagroupno());
			
			cnt = pstmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("수정실패" + e);
		}finally {
			dbclose.close(con, pstmt, rs);
		}
		return cnt;
	}
}// class end
