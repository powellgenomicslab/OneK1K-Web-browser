<!--This script build the framework for all vue script-->
<template>
<!--    navigation side bar-->

    <body class="w3-light-grey">
    <div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
        <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
        <span class="w3-bar-item w3-right">OneK1K logo</span>
    </div>

    <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
        <div class="w3-container">
            <h5>OneK1K</h5>
        </div>
        <div class="w3-bar-block">
            <a href="/" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
            <a href="/" class="w3-bar-item w3-button w3-padding"><i class="fa fa-home"></i>  Home</a>
            <a href="/eQTL_table" class="w3-bar-item w3-button w3-padding"><i class="fa fa-table"></i>  eQTL</a>
            <a href="/association_table" class="w3-bar-item w3-button w3-padding"><i class="fa fa-line-chart"></i>  Association</a>
            <a href="/expression" class="w3-bar-item w3-button w3-padding"><i class="fa fa-pie-chart"></i>  Expression</a>
            <a href="/" class="w3-bar-item w3-button w3-padding"><i class="fa fa-download"></i>  Download</a>
            <br><br>
        </div>
    </nav>

    <!-- Overlay effect when opening sidebar on small screens -->
    <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

    <!-- !PAGE CONTENT! -->
    <div class="w3-main" style="margin-left:300px;margin-top:43px;">
        <header class="w3-container" style="padding-top:22px">
            <h5>OneK1K</h5>
        </header>


    </div>
    <!--    navigation side bar end-->


    <router-view/>
    </body>
</template>













<style>
  @import "http://meyerweb.com/eric/tools/css/reset/reset.css";
  @import "http://fonts.googleapis.com/css?family=Stoke:300|Roboto:400,300";
  @import "assets/main.css";
  @import "https://cdn.datatables.net/1.10.20/css/jquery.dataTables.css";
  @import "https://cdn.datatables.net/buttons/1.4.2/css/buttons.dataTables.css";
  @import "https://cdn.datatables.net/v/dt/jszip-2.5.0/dt-1.10.21/b-1.6.2/b-flash-1.6.2/b-html5-1.6.2/datatables.min.css";
  @import "https://www.w3schools.com/w3css/4/w3.css";
  @import "https://fonts.googleapis.com/css?family=Raleway";
  @import "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css";


</style>
