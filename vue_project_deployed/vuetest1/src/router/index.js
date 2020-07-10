import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import eQTL_Table from '../views/eQTL_Table.vue'
import Association_Table from '../views/Association_Table.vue'
import Association_Table_full from '../views/Association_Table_full.vue'
import eQTL_Table_full from '../views/eQTL_Table_full.vue'
import expression from '../views/expression.vue'
import About from '../views/About.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },

  {
    path: '/eQTL_table',
    name: 'eQTL_Table',
    component: eQTL_Table
  },
  {
    path: '/association_table',
    name: 'association_Table',
    component: Association_Table
  },
  {
    path: '/association_table_full',
    name: 'Association_Table_full',
    component: Association_Table_full
  },
  {
    path: '/eQTL_table_full',
    name: 'eQTL_Table_full',
    component: eQTL_Table_full
  },
  {
    path: '/expression',
    name: 'expression',
    component: expression
  },
  {
    path: '/about',
    name: 'about',
    component:About
  }

]

const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

export default router