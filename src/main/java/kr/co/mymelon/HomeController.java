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
	// mymelon 프로젝트의 첫 페이지 호출
	// http://localhost:9090/mymelon/
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		// redirect -> 등록된 명령어 호출할수 있다.
		mav.setViewName("redirect:/mediagroup/list.do");
		return mav;
	}
	
}
