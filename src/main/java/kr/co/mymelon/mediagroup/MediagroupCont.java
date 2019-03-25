package kr.co.mymelon.mediagroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.utility.Utility;

@Controller
public class MediagroupCont {
	// �ڵ����� ������ ��ü�� ���� ���� ������ �����̳ʿ� �̹� ��ü�� �����Ǿ�
	// �����Ƿ� ������ new MediaGroipDAO() ���� �ʾƵ� �ȴ�
	@Autowired
	MediagroupDAO dao;
	
	public MediagroupCont() {
		System.out.println("----MediagroupCont() ��ü ����");
	}

// mymelon ������Ʈ�� ù ������ ȣ�� 
// http://localhost:9090/mymelon/mediagroup/list.do

// �̵��׷� ���� ������ ȣ��
// http://localhost:9090/mymelon/mediagroup/create.do
	@RequestMapping(value="/mediagroup/create.do", method=RequestMethod.GET)
	public String createForm() {
		return "mediagroup/createForm";
	}
	
	@RequestMapping(value="/mediagroup/create.do", method=RequestMethod.POST)
	public ModelAndView createProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		
		int cnt = 0;
		cnt=dao.create(dto);
		if(cnt==0) {
			mav.addObject("msg1", "<p>�̵�� �׷� ��� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
			
		}else {
			mav.addObject("msg1", "<p>�̵�� �׷� ��� ����</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link1", "<input type='button' value='��ӵ��' onclick='location.href\"./create.do\"'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
		}//if end
		return mav;
		
	}
	
	@RequestMapping("/mediagroup/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/list");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("list", dao.list());
		return mav;
		
	}
	@RequestMapping(value="/mediagroup/delete.do", method=RequestMethod.GET)
	public ModelAndView deleteForm(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/deleteForm");
		mav.addObject("root", Utility.getRoot());
		mav.addObject("dto", dto);
		return mav;
	}
	
	@RequestMapping(value="/mediagroup/delete.do", method=RequestMethod.POST)
	public ModelAndView deleteProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		
		int cnt = dao.delete(dto);
		if(cnt==0) {
			mav.addObject("msg1", "<p>�̵�� �׷� ���� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
			
		}else {
			mav.addObject("msg1", "<p>�̵�� �׷� ���� ����</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
		}//if end
		return mav;
		
	}
	
	
	@RequestMapping(value="/mediagroup/update.do", method=RequestMethod.GET)
		public ModelAndView updateForm(MediagroupDTO dto) {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("mediagroup/updateForm");
			mav.addObject("root", Utility.getRoot());
			mav.addObject("dto", dto);
			return mav;
	}
	
	@RequestMapping(value="/mediagroup/update.do", method=RequestMethod.POST)
	public ModelAndView updateProc(MediagroupDTO dto) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mediagroup/msgView");
		
		int cnt = dao.update(dto);
		if(cnt==0) {
			mav.addObject("msg1", "<p>�̵�� �׷� ���� ����</p>");
			mav.addObject("img", "<img src='../images/fail.png'>");
			mav.addObject("link1", "<input type='button' value='�ٽýõ�' onclick='javascrip:thistory.back()'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
			
		}else {
			mav.addObject("msg1", "<p>�̵�� �׷� ���� ����</p>");
			mav.addObject("img", "<img src='../images/sound.png'>");
			mav.addObject("link2", "<input type='button' value='�׷���' onclick='location.href=\"./list.do\"'>");
		}//if end
		return mav;
		
	}
	
}//class end