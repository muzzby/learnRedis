package com.hg.demo.controller;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

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


    @Test
    public String setName(String str){
        Jedis jedis = new Jedis("localhost", 6379);
        return null;
    }

}
