package it.reply.sytel.monitoraggio.controller;
 
import it.reply.sytel.monitoraggio.beanVO.impl.Phone;
import it.reply.sytel.monitoraggio.handler.ProductHandlerInterface;
import it.reply.sytel.monitoraggio.handler.impl.ProductHandler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class BaseController {
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public ModelAndView getData() {
//		ModelAndView model = new ModelAndView("index");
//	
//		return model;
//	}
// 
//	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
//	public ModelAndView welcomePage() {
// 
//		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Benvenuto ....");
//		model.addObject("message", "This is welcome page!");
//		model.setViewName("hello");
//		return model;
// 
//	}
	
	@RequestMapping(value = "/index**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Benvenuto...");
		model.addObject("message", "This is protected page!");
		model.setViewName("index");
 
		return model;
 
	}
 
 
	
 
	@RequestMapping(value = { "/", "/login", "/j_spring_security_logout" }, method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request, HttpServletResponse response) {

		//logger.info("START controller login GET");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			auth.setAuthenticated(false);
			
			model.addObject("msg", "You've been logged out successfully.");
			model.setViewName("login");
			return model;

		}

		
		model.setViewName("login");
		//logger.info("END controller login  GET");
		return model;

	}

	@RequestMapping(value = { "/j_spring_security_check", "/", "/login",
			"/j_spring_security_logout" }, method = RequestMethod.POST)
	public ModelAndView loginP(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			HttpServletRequest request, HttpServletResponse response) {

		//logger.info("START controller login POST");
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			auth.setAuthenticated(false);
			SecurityContextHolder.clearContext();

		//	removeUser(request.getSession(), auth.getName());
		
			model.addObject("msg", "You've been logged out successfully.");
			model.setViewName("login");
			return model;
		}

		
		model.setViewName("login");
		
		//logger.info("END controller login  POST");
		return model;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(HttpServletRequest request,
			HttpServletResponse response) {
		//logger.info("START controller 403  GET");
		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			if (auth != null) {
				// da problemi se accedo direttamente senza login
				//MyUserDetails userDetail = (MyUserDetails) auth.getPrincipal();

				model.addObject("username", null);
				model.addObject("userdetail", auth.getPrincipal());
				SecurityContextHolder.clearContext();
			}

		}
		model.setViewName("403");
		//sessionCounter.decreaseCounter();
		//logger.info("END controller 403  GET");
		return model;

	}
	
	@RequestMapping(value = "/phones", method = RequestMethod.GET)
	public ModelAndView viewPhones() {
		//logger.info("START controller phones");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		ModelAndView model = new ModelAndView();

		
		List<Phone> phones = new ArrayList<Phone>();
		//UserHandlerInterface userHandlerInterface = new UserHandler();
		ProductHandlerInterface productHandlerInterface=new ProductHandler();
		phones = productHandlerInterface.allphone();
		Phone phone = new Phone();
		model.addObject("phones", phones);
		model.addObject("phone", phone);
		model.addObject("numeroPhones", phones.size());
		
		model.setViewName("phones");
		//logger.info("END controller gestioneUtenti");
		return model;

	}

 
}
