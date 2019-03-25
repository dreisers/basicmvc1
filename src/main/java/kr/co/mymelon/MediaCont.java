package kr.co.mymelon;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.mymelon.mediagroup.MediagroupDTO;
import net.utility.UploadSaveManager;
import net.utility.Utility;

@Controller
public class MediaCont {

	@Autowired
	MediaDAO dao;

	public MediaCont() {
		System.out.println("---- MediaCont() 객체 생성");
	}

	@RequestMapping("/media/list.do")
	public ModelAndView list(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/list");
		mav.addObject("list", dao.list(dto));
		mav.addObject("root", Utility.getRoot());
		// 부모글번호
		mav.addObject("mediagroupno", dto.getMediagroupno());
		return mav;
	}// list end

	@RequestMapping(value = "/media/create.do", method = RequestMethod.GET)
	public ModelAndView creatrForm(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/createForm");
		mav.addObject("list", dao.list(dto));
		mav.addObject("root", Utility.getRoot());
		mav.addObject("mediagroupno", dto.getMediagroupno());
		return mav;
	}// createForm end

	@RequestMapping(value = "/media/create.do", method = RequestMethod.POST)
	public ModelAndView creatrProc(MediaDTO dto, HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/msgView");
		mav.addObject("root", Utility.getRoot());

//--------------------------------------------------------------------------
		// 전송된 파일 처리
		// -> 실제 파일은 /media/storage폴더에 저장
		// -> 저장된 파일관련 정보는 media테이블에 저장

		// 실제 물리적인 경로
		String basePath = req.getRealPath("/media/storage");

		// 1) <input type='file' name='posterMF'>
		// 파일 가져오기
		MultipartFile posterMF = dto.getPosterMF();
		//System.out.println("posterMF" + posterMF);
		// 파일 저장하기
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		// 파일명 dto객체에 담기
		dto.setPoster(poster);

		// 2) <input type='file' name='filenameMF'>
		MultipartFile filenameMF = dto.getFilenameMF();
		String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath);
		dto.setFilename(filename);
		dto.setFilesize(filenameMF.getSize());

		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>음원 등록 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");

		} else {
			mav.addObject("msg1", "<p>음원 등록 성공</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");
		} // if end
		return mav;
	}// createForm end

		
		@RequestMapping(value="/media/read.do", method = RequestMethod.GET)
		ModelAndView read(int mediano) {
			ModelAndView mav = new ModelAndView();
			MediaDTO dto = dao.read(mediano);
			if(dto!=null) {
				String filename = dto.getFilename();
				// 미디어파일명을 전부 소문자로 치환
				filename = filename.toLowerCase();
				if(filename.endsWith(".mp3")) {
					mav.setViewName("media/readMP3");
				}else if(filename.endsWith(".mp4")) {
					mav.setViewName("media/readMP4");
				}
			}//if end
			
			mav.addObject("root", Utility.getRoot());
			mav.addObject("dto", dto);
			return mav;
		}//read end
		
		@RequestMapping(value="/media/delete.do", method=RequestMethod.GET)
		public ModelAndView deleteForm(MediaDTO dto) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("media/deleteForm");
			mav.addObject("root", Utility.getRoot());
			// 삭제관련 정보 가져오기
			mav.addObject("dto", dao.read(dto.getMediano()));
			return mav;
		}
		@RequestMapping(value="/media/delete.do", method=RequestMethod.POST)
		public ModelAndView deleteProc(MediaDTO dto, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("media/msgView");
			mav.addObject("root", Utility.getRoot());
			
			//삭제하고 하는 정보 가져오기
			MediaDTO oldDTO = dao.read(dto.getMediano());
			
			int cnt = dao.delete(dto.getMediano());
			if(cnt==0) {
				mav.addObject("msg1", "<p>음원파일 삭제 실패</p>");
				mav.addObject("img", "<img src='../images/fail.png'>");
				mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascrip:thistory.back()'>");
				mav.addObject("link2", "<input type='button' value='음원목록' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");
			}else {
				// 관련 파일 삭제
				String basepath = req.getRealPath("/media/storage");
				UploadSaveManager.deleteFile(basepath, oldDTO.getPoster());
				UploadSaveManager.deleteFile(basepath, oldDTO.getFilename());				
				mav.addObject("msg1", "<p>음원파일 삭제 성공</p>");
				mav.addObject("img", "<img src='../images/sound.png'>");
				mav.addObject("link2", "<input type='button' value='음원목록' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");
			}//if end
			return mav;
		}
		
		@RequestMapping(value="/media/update.do", method=RequestMethod.GET)
		public ModelAndView updateForm(MediaDTO dto) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("media/updateForm");
			mav.addObject("root", Utility.getRoot());
			mav.addObject("dto", dao.read(dto.getMediano()));
			return mav;
	}
	
	@RequestMapping(value="/media/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediaDTO dto,  HttpServletRequest req) {
		System.out.println("★"+dto.getMediano());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/msgView");
	    String basePath = req.getRealPath("/media/storage");

		MediaDTO oldDTO = dao.read(dto.getMediano());
				
		MultipartFile posterMF = dto.getPosterMF();
		if (posterMF.getSize() > 0) { // 포스터 파일이 전송된 경우
			// 기존 파일 삭제
			UploadSaveManager.deleteFile(basePath, oldDTO.getPoster());
			// 신규 파일 저장
			String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
			dto.setPoster(poster);
		} else { // 포스터 파일을 수정하지 않는 경우
			dto.setPoster(oldDTO.getPoster());
		} // if end

		MultipartFile filenameMF = dto.getFilenameMF();
		if (filenameMF.getSize() > 0) {
			UploadSaveManager.deleteFile(basePath, oldDTO.getFilename());
			String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath);
			dto.setFilename(filename);
			dto.setFilesize(filenameMF.getSize());

		} else {
			dto.setFilename(oldDTO.getFilename());
			dto.setFilesize(oldDTO.getFilesize());
		}
		
		int cnt = dao.update(dto);
		if(cnt==0) {
			mav.addObject("msg1", "<p>음원 수정 실패</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='다시시도' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='그룹목록' onclick='location.href=\"./list.do\"'>");
			
		}else {
			mav.addObject("msg1", "<p>음원 수정 성공</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1", "<input type='button' value='목록' onclick='location.href=\"./list.do?mediagroupno="+ dto.getMediagroupno()+"\"'>");
		}//if end
		return mav;
		
	}
	
}// class end