package com.yanqun.cloud.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yanqun.cloud.config.ConfigUpload;

@FeignClient(name = "uploadDemo",configuration = ConfigUpload.class)
public interface  UploadController {
    @RequestMapping(method = RequestMethod.POST, value = "/consumerfeign/upload",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload(@RequestPart(value = "file") MultipartFile file);
}

