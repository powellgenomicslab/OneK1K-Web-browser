/*assiciation_talbe_top10.java,association_tableHandler.java,Eqtl_table,javaEqtl_table_top10.java.
 These files are for reading data from local disk and give those data to four APIs,
 the only difference is the url of API and the path of data.
 If you want to understand the data process, please read the documentation in assiciation_talbe_top10.java* */

//To use the controller function of Springboot so that can interact with front end
package com.example.demo.controller;

//libraries that process json files
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//children function of controller.CrossOrigin is to avoid cross resource error,
//RequestMapping is to give API, RequestMethod is to allow html request,
//ResponseBody is to put data into allocated API

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//libraries that process local files
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//begin controller part
@Controller
//point an url that allow cross resource
@CrossOrigin(value = "http://localhost:8080", maxAge = 3600)
//declare a public variable, name needs to be the same to the file name
public class association_table_top10 {
//    specify the url for the API
    @RequestMapping(value ="/association_table_10", method = RequestMethod.GET)
//    the following codes for retuning data
    @ResponseBody

//    create function tableinfo, this return data that follows the format of List<tablejson>
    public List<tablejson> tableinfo() throws JSONException {
        List<tablejson> list = new ArrayList<tablejson>();

//        create variables that gets current time points to measure time of getting local data
        long start = System.currentTimeMillis();
//        call getJson function and store data into jsonStr
        String jsonStr = getJson();
        long end = System.currentTimeMillis();
        System.out.println("jsontime:"+(end-start));

//        change jsonStr into an array
        JSONArray arr = new JSONArray(jsonStr);
//measure time for processing the data
        long start1 = System.currentTimeMillis();
//        loop through the array. change every JSONObject in array to tablejson
        for(int i=0;i<arr.length();i++) {
            tablejson tablejson1 = new tablejson();
            JSONObject jsonObj = arr.getJSONObject(i);
            tablejson1.ASSESSED_ALLELE = jsonObj.getString("ASSESSED ALLELE");
            tablejson1.OTHER_ALLELE = jsonObj.getString("OTHER ALLELE");
            tablejson1.POS = jsonObj.getInt("POS");
            tablejson1.CHR = jsonObj.getInt("CHR");
            tablejson1.FDR = jsonObj.getString("FDR");
            tablejson1.GENE = jsonObj.getString("GENE");
            tablejson1.GENE_ID = jsonObj.getString("GENE ID");
            tablejson1.P_VALUE = jsonObj.getString("P-VALUE");
            tablejson1.CORR_COEF = jsonObj.getString("CORR COEF");
            tablejson1.SNP_ID = jsonObj.getString("SNP ID");
            tablejson1.CELL_TYPE = jsonObj.getString("CELL TYPE");
            list.add(tablejson1);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("iterationtime:"+(end1-start1));
        return list;
    }

//    This read local json file and return into string
    public static String getJson() {
        String jsonStr = "";
        try {
            File file = new File("src/main/resources/static/associations_10.json");
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
