# Web browser
Ran Zhang
10/07/2020

/data/eQTL/: stores the whole eQTL dataset

/data/Samples_for_FrontEnd: stores the expression dataset (Files with name …_expressions.tsv)

/vue_project_local/: scripts that allow browser running locally

/ vue_project_deployed/: for packaging and deploying, paths are changed

/website_deployment/: to be uploaded to the server

There are 3 sections for displaying the data on the web browser. eQTL and association display datatable, expression display graph. The browser is deployed on a testing VM and you can see via http://10.214.132.61:8181/ with VPN connected. The deployed browser only displays eQTL data, but association data has the same format as eQTL data. The default display of eQTL data on the web browser shows the top 10 significant data for each cell type. Full display of eQTL can be see by clicking eQTL (Full).  


There are in total of 14 cell types in 14 tsv files. Each file contains data from chromosome 1 to chromosome 22. 

# Vue structure

The web browser is created using Springboot and Vue.

Public:

     Index.html: contains js packages
     
Src:

     Assets: main.css
     
     Plugins: js packages
     
     Router: index.js(url for all children htmls)
     
     Views: children htmls
     
     App.vue: main html (usually defines a page design structure and all children htmls use it)
     
     Main.js: pre-loading all js packages
     
     Vue.config.js: for packaging and deployment, point to path

# Vue script structure

<template>
     
Html content 

</template>

<script>
     
1 script statement (mostly javascript)：define global variable，establish datatable

2 export default{

         Methods:{
         
Contains all defined functions

         }
         
 } 
 
3 mounted: function(){

Contains all statements that need to be run before the page is loaded, for example hide()

}

</script>

# Current project structure

1.	Vue_project_local: 

a.	get data locally and run locally. 

b.	Vuetest1 folder contains everything for front end views. Demon contains everything for back end

c.	To run: run demo in IDEA. Type “npm run serve” in vuetest1 terminal.

d.	Go to chrome and type http://localhost:8080/


2.	Vue_project_deployed:

a.	Get data from server and run at server

b.	Vuetest1 folder contains everything for front end views. Demon contains everything for back end

c.	For deployment and running see below.


# Deployment

API and some parameters need to be changed. You can compare the differences between the local version and deployed version.

1.	Create a local folder: e.g. website_deployment

2.	Copy and paste all the data folders: table_data and Samples_for_FrontEnd, and python files into the website_deployment folder.

3.	Go into the vue_project_deployed . Open vuetest1 and demon using IDEA. 

4.	In the vuetest1 project terminal, run command: npm run build. After the command finishes, a new folder called dist will appear.

5.	Copy all five files in the dist folder and paste inton website_deplotment folder and /vue_project_deployed/demo/src/main/resources/static/ folder

6.	Delete target folder in /vue_project_deployed/demo/ if there is one

7.	In the demo project, run maven package. After this is finished, a target folder will appear.

8.	In the target folder, copy demo-0.0.1-SNAPSHOT.jar and paste it into the website_deployment folder

9.	Copy and paste the entire website_deployment folder into the server.

10.	To run on the server, go into website_deployment and type in the command: nohup java -jar demo-0.0.1-SNAPSHOT.jar &

Go into chrome and type http://10.214.132.61:8181/






 
