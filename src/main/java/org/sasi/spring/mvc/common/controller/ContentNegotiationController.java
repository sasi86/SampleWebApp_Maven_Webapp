package org.sasi.spring.mvc.common.controller;

import org.sasi.spring.mvc.model.State;
import org.sasi.spring.mvc.worker.GeoInfoWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/contentNegotiation")
public class ContentNegotiationController {
	
	@Autowired
	private GeoInfoWorker geoInfoWorkerImpl;
	
	@RequestMapping(value="/get/state", method = RequestMethod.GET, produces={"application/xml","application/json"})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody State getStateAnyFormat(@RequestParam String id,ModelMap model) {
		if(id!=null && id.equals("Select"))
			return null;
		else{
			State state= geoInfoWorkerImpl.getState(Integer.parseInt(id)).get(0);
			model.addAttribute("state",state);
			return state;
		}
	}
	
	@RequestMapping(value="/get/state", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String getStateAsHtml(@RequestParam String id,ModelMap model) {
		if(id!=null && id.equals("Select"))
			return null;
		else{
			State state= geoInfoWorkerImpl.getState(Integer.parseInt(id)).get(0);
			model.addAttribute("state",state);
			return "contentAsHtml";
		}
	}

}
