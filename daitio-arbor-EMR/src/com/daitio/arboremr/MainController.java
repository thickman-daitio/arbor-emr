package com.daitio.arboremr;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping(value="/login.html", method = RequestMethod.GET)
	public ModelAndView getAdmissionForm() {
		
		int i = 15; 
		ModelAndView model1 = new ModelAndView("loginForm");
		
		return model1;
	}	
	
	@RequestMapping(value="/myPage.html", method = RequestMethod.POST)
	public ModelAndView submitAdmissionForm(@ModelAttribute("user1") User user, BindingResult result) {
		
		 if (result.hasErrors()) {

				ModelAndView model1 = new ModelAndView("loginForm");
				return model1;
		 }

		ModelAndView model1 = new ModelAndView("myPageSuccess");
		return model1;
	}
	
	
}
