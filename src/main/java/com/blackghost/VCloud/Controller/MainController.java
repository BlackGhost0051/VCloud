package com.blackghost.VCloud.Controller;

import com.blackghost.VCloud.Service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final DataBaseService dataBaseService;

    @Autowired
    public MainController(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
