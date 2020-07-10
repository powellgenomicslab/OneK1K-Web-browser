package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/selectgene")
@CrossOrigin(value = "http://localhost:8080", maxAge = 3600)
public class Getrequest_drawgraph {
    public String gene;
    @ResponseBody
//    get which gene to plot from front end then draw
    @RequestMapping(value = "/expression", method = RequestMethod.POST)
//    Springboot fetch gene from front end
    public String tableinfo(@RequestBody String gene) throws ExecutionException, InterruptedException {
//        process the gene name
        this.gene=gene.split("=")[0];
//        plot
        try {
            System.out.println("drawing graph for "+this.gene);
//            call python script that draw the plot
            Process p = Runtime.getRuntime().exec("python -m expression "+this.gene);
            int re = p.waitFor();
            if (re == 0) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }
            System.out.println("getting result for "+this.gene);
            System.out.println("/image/"+this.gene+".html");

//            specify the path of finished plot
            File wd = new File("./graph_folder/");
//            run start.py to put the plot to localhost:8282
            Process p1 = Runtime.getRuntime().exec("python start.py 8282",null,wd);


        } catch (Exception e) {
            System.out.println(e);

        };
        return "localhost:8282";
    }

}



