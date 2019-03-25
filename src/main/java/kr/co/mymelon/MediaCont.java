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
		System.out.println("---- MediaCont() ��ü ����");
	}

	@RequestMapping("/media/list.do")
	public ModelAndView list(MediaDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/list");
		mav.addObject("list", dao.list(dto));
		mav.addObject("root", Utility.getRoot());
		// �θ�۹�ȣ
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
		// ���۵� ���� ó��
		// -> ���� ������ /media/storage������ ����
		// -> ����� ���ϰ��� ������ media���̺� ����

		// ���� �������� ���
		String basePath = req.getRealPath("/media/storage");

		// 1) <input type='file' name='posterMF'>
		// ���� ��������
		MultipartFile posterMF = dto.getPosterMF();
		//System.out.println("posterMF" + posterMF);
		// ���� �����ϱ�
		String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
		// ���ϸ� dto��ü�� ���
		dto.setPoster(poster);

		// 2) <input type='file' name='filenameMF'>
		MultipartFile filenameMF = dto.getFilenameMF();
		String filename = UploadSaveManager.saveFileSpring30(filenameMF, basePath);
		dto.setFilename(filename);
		dto.setFilesize(filenameMF.getSize());

		int cnt = dao.create(dto);
		if (cnt == 0) {
			mav.addObject("msg1", "<p>���� ��� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");

		} else {
			mav.addObject("msg1", "<p>���� ��� ����</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");
		} // if end
		return mav;
	}// createForm end

		
		@RequestMapping(value="/media/read.do", method = RequestMethod.GET)
		ModelAndView read(int mediano) {
			ModelAndView mav = new ModelAndView();
			MediaDTO dto = dao.read(mediano);
			if(dto!=null) {
				String filename = dto.getFilename();
				// �̵�����ϸ��� ���� �ҹ��ڷ� ġȯ
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
			// �������� ���� ��������
			mav.addObject("dto", dao.read(dto.getMediano()));
			return mav;
		}
		@RequestMapping(value="/media/delete.do", method=RequestMethod.POST)
		public ModelAndView deleteProc(MediaDTO dto, HttpServletRequest req) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("media/msgView");
			mav.addObject("root", Utility.getRoot());
			
			//�����ϰ� �ϴ� ���� ��������
			MediaDTO oldDTO = dao.read(dto.getMediano());
			
			int cnt = dao.delete(dto.getMediano());
			if(cnt==0) {
				mav.addObject("msg1", "<p>�������� ���� ����</p>");
				mav.addObject("img", "<img src='../images/fail.png'>");
				mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascrip:thistory.back()'>");
				mav.addObject("link2", "<input type='button' value='�������' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");
			}else {
				// ���� ���� ����
				String basepath = req.getRealPath("/media/storage");
				UploadSaveManager.deleteFile(basepath, oldDTO.getPoster());
				UploadSaveManager.deleteFile(basepath, oldDTO.getFilename());				
				mav.addObject("msg1", "<p>�������� ���� ����</p>");
				mav.addObject("img", "<img src='../images/sound.png'>");
				mav.addObject("link2", "<input type='button' value='�������' onclick='location.href=\"./list.do?mediagroupno=" + dto.getMediagroupno() + "\"'>");
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
		System.out.println("��"+dto.getMediano());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("media/msgView");
	    String basePath = req.getRealPath("/media/storage");

		MediaDTO oldDTO = dao.read(dto.getMediano());
				
		MultipartFile posterMF = dto.getPosterMF();
		if (posterMF.getSize() > 0) { // ������ ������ ���۵� ���
			// ���� ���� ����
			UploadSaveManager.deleteFile(basePath, oldDTO.getPoster());
			// �ű� ���� ����
			String poster = UploadSaveManager.saveFileSpring30(posterMF, basePath);
			dto.setPoster(poster);
		} else { // ������ ������ �������� �ʴ� ���
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
			mav.addObject("msg1", "<p>���� ���� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
			
		}else {
			mav.addObject("msg1", "<p>���� ���� ����</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1", "<input type='button' value='���' onclick='location.href=\"./list.do?mediagroupno="+ dto.getMediagroupno()+"\"'>");
		}//if end
		return mav;
		
	}
	
}// class end