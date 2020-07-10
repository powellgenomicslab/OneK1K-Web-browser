<template>



  <!-- ##############################Homepage content####################################### -->

  <div class="w3-container" style="margin-left:300px;margin-top:43px;">
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
          <router-link :to="{path:'/eQTL_table'}" @click.native="refreshrefresh"> <h5>eQTL Table</h5> </router-link>

        </div>
      </div>

      <div class="w3-quarter">
        <div class="w3-container w3-teal w3-padding-16">
          <div class="w3-left"><i class="fa fa-line-chart w3-xxxlarge"></i></div>
          <div class="w3-clear"></div>
          <router-link :to="{path:'/association_table'}" @click.native="refreshrefresh"> <h5>Association Table</h5> </router-link>

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

    <section >
    <article>
      <header class="clearfix">
        <h2>About OneK1K</h2>
      </header>

      <p class="homepage-p">Lorem ipsum dolor sit amet, eam ne commodo habemus invenire. Quo ne feugiat platonem, elitr labore in vim. Meis deleniti cu ius, his in suscipit urbanitas torquatos. Soleat ridens euripidis ne cum.>Lorem ipsum dolor sit amet, eam ne commodo habemus invenire. Quo ne feugiat platonem, elitr labore in vim. Meis deleniti cu ius, his in suscipit urbanitas torquatos. Soleat ridens euripidis ne cum.>Lorem ipsum dolor sit amet, eam ne commodo habemus invenire. Quo ne feugiat platonem, elitr labore in vim. Meis deleniti cu ius, his in suscipit urbanitas torquatos. Soleat ridens euripidis ne cum.</p>

    </article>

  </section>
  </div>
  <!-- ##############################Homepage content####################################### -->


  <!-- ##############################Association table####################################### -->


   -->



  <!-- ##############################Footer####################################### -->
  <!--  <footer class="footer">-->
  <!--    <div class="footer-content clearfix">-->
  <!--      <small class="copyright">All Content Copyright 2020-GWCCG. All Rights Researved</small>-->
  <!--      <small class="credits">Site Designed by GWCCG</small>-->
  <!--    </div>-->
  <!--  </footer>-->

  <!-- ##############################Footer####################################### -->










</template>

<script>
  export default {
    methods: {
      refreshrefresh(){
        console.log("here")
        location.reload()}}}

</script>
