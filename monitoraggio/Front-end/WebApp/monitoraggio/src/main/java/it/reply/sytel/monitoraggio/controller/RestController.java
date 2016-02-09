package it.reply.sytel.monitoraggio.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.reply.sytel.monitoraggio.beanVO.impl.JsonResponse;
import it.reply.sytel.monitoraggio.beanVO.impl.Person;

@Controller
@RequestMapping("/v3/auth")
public class RestController {

	public RestController() {
		System.out.println("init RestController");
	}

	// this method responses to GET request
	// http://localhost/spring-mvc-json/rest/cont
	// return Person object as json

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Person get(HttpServletResponse res) {

		Person person = new Person();
		person.setUsername("bingo");
		person.setPassword("fabio");
		System.out.println("RestController GET");

		return person;
	}

	// this method response to POST request
	// http://localhost/spring-mvc-json/rest/cont/person
	// receives json data sent by client --> map it to Person object
	// return Person object as json
	// @RequestMapping(value="person", method = RequestMethod.POST,headers =
	// {"Content-type=application/json"})
	// @ResponseBody
	// public Person post( @RequestBody final Person person) {
	//
	// System.out.println(person.getId() + " " + person.getName());
	// return person;
	// }
	@RequestMapping(value = "/tokens", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	@ResponseBody
	public JsonResponse addPerson(@RequestBody Person person) {
		System.out.println(person.toString());
		return new JsonResponse("OK", "");
	}
}
