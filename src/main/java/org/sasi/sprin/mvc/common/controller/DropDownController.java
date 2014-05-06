package org.sasi.sprin.mvc.common.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.sasi.sprin.mvc.dao.GeoInfoDAO;
import org.sasi.sprin.mvc.model.City;
import org.sasi.sprin.mvc.model.Country;
import org.sasi.sprin.mvc.model.State;
import org.sasi.sprin.mvc.worker.GeoInfoWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.SimpleFormController;

@Controller

@RequestMapping("/chooseCity")
public class DropDownController{
	
	
	
	//@RequestParam
	/*To specify the requestParam optional @RequestParam(value="id", required=false))*/
	
	//@ResponseBody
	/*The @ResponseBody annotation is similar to @RequestBody. This annotation can be put on a method and indicates that the return type 
	should be written straight to the HTTP response body (and not placed in a Model, or interpreted as a view name)*/
	
	//Using HttpEntity<?>
	/*The HttpEntity is similar to @RequestBody and @ResponseBody. Besides getting access to the request and response body, 
	 * HttpEntity (and the response-specific subclass ResponseEntity) also allows access to the request and response headers, like so:

	@RequestMapping("/something")
	public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws UnsupportedEncodingException {
	  String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader"));
	  byte[] requestBody = requestEntity.getBody();
	  // do something with request header and body

	  HttpHeaders responseHeaders = new HttpHeaders();
	  responseHeaders.set("MyResponseHeader", "MyValue");
	  return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	}*/
	
	//Using @ModelAttribute on a method
	 /*@ModelAttribute methods in a controller are invoked before @RequestMapping methods, within the same controller
	  * 
	  * @ModelAttribute
		public Account addAccount(@RequestParam String number) {
		    return accountManager.findAccount(number);
		}
		
		// Add multiple attributes
		
		@ModelAttribute
		public void populateModel(@RequestParam String number, Model model) {
		    model.addAttribute(accountManager.findAccount(number));
		    // add more ...
		}
	  * 
	  * */
	
	//@CookieValue
	/*public void displayHeaderInfo(@CookieValue("JSESSIONID") String cookie)  {*/
	
	//@RequestHeader
	/*
	 * public void displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding,
     *                         @RequestHeader("Keep-Alive") long keepAlive)  {
	 * */
	
	@Autowired
	private GeoInfoWorker geoInfoWorkerImpl;
	
	@ModelAttribute
	public List<Country> populateCountry(){
		return geoInfoWorkerImpl.getCountry();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {	
		Country c= new Country();
		model.addAttribute("message", "Spring 3 MVC Ajax populate");
		/*int id=1;
		for(int i=1;i<=25;i++){
			for(int j=1;j<=5;j++)
				System.out.println("INSERT INTO CITY(CITY_ID,STATE_ID,CITY_NAME) VALUES("+(id++)+","+i+",'"+i+"-CITY-"+j+"');");
			
		}*/
		
		return "dropDown";
 
	}
	


	@RequestMapping(value="/get/state", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<State> getState(@RequestParam String id,ModelMap model) {
		if(id!=null && id.equals("Select"))
			return null;
		else
			return geoInfoWorkerImpl.getState(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/get/city", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody List<City> getCity(@RequestParam String id,ModelMap model) {
		if(id!=null && id.equals("Select"))
			return null;
		else
			return geoInfoWorkerImpl.getCity(Integer.parseInt(id));
	}
	
	@RequestMapping(value="/get/json", method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody String sendFilteredJsonBack(ModelMap model) throws JsonGenerationException, JsonMappingException, IOException {
		Numbers n = new Numbers();
		n.setNumber(22);
		n.setName("Sasi");
		ObjectMapper mapper = new ObjectMapper();
		// first, construct filter provider to exclude all properties but 'name', bind it as 'myFilter'
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter",
		    SimpleBeanPropertyFilter.filterOutAllExcept(new HashSet<String>(Arrays.asList(new String[]{"name", "field1"}))));
		// and then serialize using that filter provider:
		@SuppressWarnings("deprecation")
		String json = mapper.filteredWriter(filters).writeValueAsString(n);
		mapper.generateJsonSchema(Numbers.class).toString();
		
		System.out.println(json);
		return json;
	}
	
	public List<Numbers> findNumber(String number){
		List<Numbers> numbers=new ArrayList<Numbers>();
		
		if(number!=null && !number.equals("select")){
			int nextIncr = Integer.parseInt(number);
			Numbers num = null;
			for(int i=0 ;i<=2;i++){
				num = new Numbers();
				num.setNumber(++nextIncr);
				numbers.add(num);
			}
		}
		return numbers;
	}
	
}


