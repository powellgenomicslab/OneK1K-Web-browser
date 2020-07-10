<template>
  <!--    navigation top bar-->

  <div class="w3-container" data-id="show-table" style="margin-left:300px;margin-top:43px;">
    <div class="w3-row-padding w3-margin-bottom">
      <div class="w3-quarter">
        <div class="w3-container w3-red w3-padding-16">
          <div class="w3-left"><i class="fa fa-home w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <a href="/" > <h5>Home</h5> </a>
        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-blue w3-padding-16">
          <div class="w3-left"><i class="fa fa-table w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <a href="/eQTL_table"><h5>eQTL Table</h5></a>
        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-teal w3-padding-16">
          <div class="w3-left"><i class="fa fa-line-chart w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <a href="/association_table"><h5>Association Table</h5></a>
        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-orange w3-text-white w3-padding-16">
          <div class="w3-left"><i class="fa fa-pie-chart w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <a href="/expression"><h5>Expression Graph</h5></a>
        </div>
      </div>
    </div>
    <!--    navigation top bar end-->


    <div class="w3-container" >
<!--loading element, assigned an id to be called in script-->
    <div id="loading_spinner">
      <atom-spinner
              :animation-duration="1000"
              :size="60"
              :color="'#1aa1e1'"/>
    </div>
    <p>Please choose a gene</p>
    <br>
<!--      build the drop down menu for people to choose gene, give a function name so that once click, initiate 'selectOne' function-->
    <el-select v-model="value"  placeholder="e.g. APOL2" @change="selectOne" >
      <el-option
              v-for="item in options"
              :key="item.label"
              :label="item.label"
              :value="item.label">
      </el-option>
    </el-select>

    <br>
    <br>
<!--      insert the plot here, assign an id to be fetched in script and backend-->
    <div id="pic">
    </div>
  </div>
  </div>
</template>
<script>
  // this is a Vue library that has lots of different pattern for loading elements
  import {AtomSpinner} from 'epic-spinners'
  var pichtml

  export default {
    components: {
      AtomSpinner
    },


    mounted: function() {
      // hide the loading element after the plot is showed

      $("#linka").hide()
      $("#loading_spinner").hide()


    },


    data() {

      // give the list of genes that can be selected by user. I used Python to parse the expression file and get all genes and store in dictionary

      return {
        options: [{'label': 'AC000068.5'}, {'label': 'AC002472.13'}, {'label': 'AC007308.6'}, {'label': 'ADORA2A-AS1'}, {'label': 'ADRBK2'}, {'label': 'APOBEC3A'}, {'label': 'APOBEC3B'}, {'label': 'APOBEC3C'}, {'label': 'APOBEC3G'}, {'label': 'APOBEC3H'}, {'label': 'APOL2'}, {'label': 'APOL6'}, {'label': 'ARFGAP3'}, {'label': 'ARSA'}, {'label': 'ARVCF'}, {'label': 'ASPHD2'}, {'label': 'BCR'}, {'label': 'BIK'}, {'label': 'C22orf34'}, {'label': 'CBX6'}, {'label': 'CDC42EP1'}, {'label': 'CHCHD10'}, {'label': 'CRYBB2'}, {'label': 'CTA-29F11.1'}, {'label': 'DDT'}, {'label': 'FAM118A'}, {'label': 'GGT1'}, {'label': 'IGLL1'}, {'label': 'LGALS2'}, {'label': 'MIF'}, {'label': 'NDUFA6'}, {'label': 'SELM'}]

        ,
        value:'',

      }},




    methods: {
      hidebutton(){
        $("#linka").hide();
      },
      // this function shows the loading element
      selectOne(item) {
        $("#loading_spinner").show()



        console.log(item)
        // get data from expression data
        $.ajax({

          url:"http://localhost:8181/selectgene/expression",
          type:"POST",

          dataType: "text",

          data:item,

          success: function(data) {
            pichtml = data;

          },
          error:function(XMLHttpRequest, textStatus){
            console.log(XMLHttpRequest);  //XMLHttpRequest.responseText    XMLHttpRequest.status   XMLHttpRequest.readyState
            console.log(textStatus);
          }
        })
        console.log(pichtml)
        setTimeout(function(){
          $("#loading_spinner").hide()
// finished plot is loaded in this url which is open at the backend, we need to fetch from this url
          $("#pic").load("http://localhost:8282")
        }, 8000);
      }
    }
  }
</script>