<template>
  <div class="w3-container" data-id="show-table" style="margin-left:300px;margin-top:43px;">
    <div class="w3-row-padding w3-margin-bottom">
      <div class="w3-quarter">
        <div class="w3-container w3-red w3-padding-16">
          <div class="w3-left"><i class="fa fa-home w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <router-link :to="{path:'/'}" @click.native="refreshrefresh"> <h5>Home</h5> </router-link>
        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-blue w3-padding-16">
          <div class="w3-left"><i class="fa fa-table w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <router-link :to="{path:'/eQTL_table'}" @click.native="refreshrefresh"><h5>eQTL</h5></router-link>
        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-teal w3-padding-16">
          <div class="w3-left"><i class="fa fa-line-chart w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <router-link :to="{path:'/association_table'}" @click.native="refreshrefresh"> <h5>Association </h5> </router-link>
        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-orange w3-text-white w3-padding-16">
          <div class="w3-left"><i class="fa fa-pie-chart w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <router-link :to="{path:'/expression'}" @click.native="refreshrefresh"> <h5>Expression Graph</h5> </router-link>
        </div>
      </div>
    </div>

    <div class="w3-container" >

      <div id="loading_spinner">
        <atom-spinner
                :animation-duration="1000"
                :size="60"
                :color="'#1aa1e1'"/>
      </div>
      <p>Please choose a gene</p>
      <br>
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
      <div id="pic">
      </div>
    </div>
  </div>

</template>
<script>
  import {AtomSpinner} from 'epic-spinners'
  var pichtml
  export default {
    components: {
      AtomSpinner
    },


    mounted: function() {

      $("#linka").hide()
      $("#loading_spinner").hide()


    },


    data() {

      return {
        options: [{'label': 'AC000068.5'}, {'label': 'AC002472.13'}, {'label': 'AC007308.6'}, {'label': 'ADORA2A-AS1'}, {'label': 'ADRBK2'}, {'label': 'APOBEC3A'}, {'label': 'APOBEC3B'}, {'label': 'APOBEC3C'}, {'label': 'APOBEC3G'}, {'label': 'APOBEC3H'}, {'label': 'APOL2'}, {'label': 'APOL6'}, {'label': 'ARFGAP3'}, {'label': 'ARSA'}, {'label': 'ARVCF'}, {'label': 'ASPHD2'}, {'label': 'BCR'}, {'label': 'BIK'}, {'label': 'C22orf34'}, {'label': 'CBX6'}, {'label': 'CDC42EP1'}, {'label': 'CHCHD10'}, {'label': 'CRYBB2'}, {'label': 'CTA-29F11.1'}, {'label': 'DDT'}, {'label': 'FAM118A'}, {'label': 'GGT1'}, {'label': 'IGLL1'}, {'label': 'LGALS2'}, {'label': 'MIF'}, {'label': 'NDUFA6'}, {'label': 'SELM'}]

        ,
        value:'',

      }},




    methods: {
      hidebutton(){
        $("#linka").hide();
      },
      selectOne(item) {
        $("#loading_spinner").show()



        console.log(item)
        $.ajax({

          url:"http://10.214.132.61:8181/selectgene/expression",
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

          $("#pic").load("http://10.214.132.61:8282")
        }, 8000);
      }
    }
  }
</script>