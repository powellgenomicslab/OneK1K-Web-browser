package com.example.demo.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(value = "http://10.214.132.61:8080", maxAge = 3600)
public class Eqtl_table_top10 {
    @RequestMapping(value ="/eqtlTable_10", method = RequestMethod.GET)
    @ResponseBody
    public List<tablejson> tableinfo() {
        List<tablejson> list = new ArrayList<tablejson>();

        long start = System.currentTimeMillis();
        String jsonStr = getJson();
        long end = System.currentTimeMillis();
        System.out.println("jsontime:"+(end-start));

        JSONArray arr = new JSONArray(jsonStr);

        long start1 = System.currentTimeMillis();
        for(int i=0;i<arr.length();i++) {
            tablejson tablejson1 = new tablejson();
            JSONObject jsonObj = arr.getJSONObject(i);
            tablejson1.ASSESSED_ALLELE = jsonObj.getString("ASSESSED ALLELE");
            tablejson1.OTHER_ALLELE = jsonObj.getString("OTHER ALLELE");
            tablejson1.POS = jsonObj.getInt("POS");
            tablejson1.CHR = jsonObj.getInt("CHR");
            tablejson1.FDR = jsonObj.getFloat("FDR");
            tablejson1.GENE = jsonObj.getString("GENE");
            tablejson1.GENE_ID = jsonObj.getString("GENE ID");
            tablejson1.P_VALUE = jsonObj.getFloat("P-VALUE");
            tablejson1.CORR_COEF = jsonObj.getFloat("CORR COEF");
            tablejson1.SNP_ID = jsonObj.getString("SNP ID");
            tablejson1.CELL_TYPE = jsonObj.getString("CELL TYPE");
            list.add(tablejson1);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("iterationtime:"+(end1-start1));
        return list;
    }
    public static String getJson() {
        String jsonStr = "";
        try {
            File file = new File("//home//ranzha//website_deployment//table_data//eqtl_10.json");
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
            }
        catch (Exception e) {
            System.out.println(System.getProperty("user.dir"));
            System.out.println(e+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return null;
        }
    }

}
