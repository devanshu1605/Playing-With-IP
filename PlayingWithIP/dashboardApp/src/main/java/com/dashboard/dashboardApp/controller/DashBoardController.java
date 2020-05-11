package com.dashboard.dashboardApp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dashboard.dashboardApp.model.ResponseModel;
import com.dashboard.dashboardApp.service.FileUploadService;

@RestController
@CrossOrigin
public class DashBoardController {

	@Autowired
    private FileUploadService fileUploadService;
    
    @PostMapping("/uploadFile")
    public ResponseModel uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
    	Date date=new Date();
    	long start= date.getTime();
        ResponseModel model =fileUploadService.uploadFile(file);
        long stop= date.getTime();
        System.out.println("Latency is "+(stop-start));
        return model;
    }
}
