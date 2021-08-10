package com.yanqun.demo.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringBootController {
	@ResponseBody
	@RequestMapping("hello")
	public String hello(){
		return "hello Spring Boot" ;
	}
	
	@RequestMapping("testThymeleaf")
	public String testThymeleaf(Map<String,Object> map){
		//将pidValue等属性，放入request域中
		map.put("pidValue", "pidV") ;
		map.put("pclassValue", "pclassV") ;
		map.put("ptextValue", "ptextV") ;
		return "result" ;
	}
	
	@RequestMapping("testIter")
	public String testIter(Map<String,Object>map) {
		List<Product>prods = new ArrayList<>() ;
		prods.add(new Product("iphone",7288,999));
		prods.add(new Product("MI",2999,666));
		prods.add(new Product("Mac",16799,888));
		map.put("prods", prods);
		return "result" ;
	}
}
