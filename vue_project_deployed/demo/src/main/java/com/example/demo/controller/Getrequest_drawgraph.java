package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/selectgene")
@CrossOrigin(value = "http://10.214.132.61:8080", maxAge = 3600)
public class Getrequest_drawgraph {
    public String gene;
    @ResponseBody
    @RequestMapping(value = "/expression", method = RequestMethod.POST)
    public String tableinfo(@RequestBody String gene) throws ExecutionException, InterruptedException {
        this.gene=gene.split("=")[0];
        //todo run the algo
        try {
            System.out.println("drawing graph for "+this.gene);
            //todo run the algo
//            Process p = Runtime.getRuntime().exec("python -m expression "+this.gene);
            String[] arguments = new String[] {"python3", "//home//ranzha//website_deployment//expression.py", this.gene};
            Process p = Runtime.getRuntime().exec(arguments);
            int re = p.waitFor();
            if (re == 0) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }
            System.out.println("getting result for "+this.gene);
            System.out.println("/image/"+this.gene+".html");
            File wd = new File("//home//ranzha//website_deployment//");
            Process p1 = Runtime.getRuntime().exec("python3 start.py 8282",null,wd);


        } catch (Exception e) {
            System.out.println(e);

        };
        return "10.214.132.61:8282";
    }

}



