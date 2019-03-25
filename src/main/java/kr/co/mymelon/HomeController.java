package kr.co.mymelon;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	// mymelon ������Ʈ�� ù ������ ȣ��
	// http://localhost:9090/mymelon/
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		// redirect -> ��ϵ� ��ɾ� ȣ���Ҽ� �ִ�.
		mav.setViewName("redirect:/mediagroup/list.do");
		return mav;
	}
	
}
