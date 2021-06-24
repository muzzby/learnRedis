package com.hg.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author admin
 */
@RestController
@RequestMapping("testing")
public class IndexController {


    @RequestMapping("/index")
    public String index(){
        return "这是spring boot首页";
    }

}
