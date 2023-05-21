package com.tigeranalytics.Customers.controllers;

import com.tigeranalytics.Customers.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    StoreService storeService;

    @PostMapping("/api/v1/upload")
    public void createUser(@RequestParam("file") MultipartFile file) throws IOException {
        storeService.importExcel(file);
    }
}
