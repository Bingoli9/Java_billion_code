package com.yanqun.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	@RequestMapping("/request")
	public String request(Map<String,Object> map){
		map.put("name","zs") ;
		return "index" ;
	}
	
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request) {
	    List<MultipartFile> multiFiles = ((MultipartHttpServletRequest) request).getFiles("myPicture");
	    ModelAndView mv = new ModelAndView("success");
	    for (int i = 0; i < multiFiles.size(); i++) {
	        MultipartFile fileItem = multiFiles.get(i);
	        String fileName = fileItem.getOriginalFilename();
	        File file = new File("d:/upload/" + fileName);
	        try {
	            fileItem.transferTo(file);
	            mv.addObject("file",fileItem);
	        } catch (Exception e) {
	        	mv.addObject("error",e) ;
	            return mv;
	        }
	    }
	    return mv;

	}
	
}
