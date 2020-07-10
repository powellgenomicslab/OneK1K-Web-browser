import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

// To distribute routes and path

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },

  {
    path: '/eQTL_table',
    name: 'eQTL_Table',
    component: () => import(/* webpackChunkName: "about" */ '../views/eQTL_Table.vue')
  },
    {
      path: '/association_table',
      name: 'association_Table',
      component: () => import(/* webpackChunkName: "about" */ '../views/Association_Table.vue')
    },
    {
      path: '/association_table_full',
      name: 'association_Table',
      component: () => import(/* webpackChunkName: "about" */ '../views/Association_Table_full.vue')
    },
    {
      path: '/eQTL_table_full',
      name: 'association_Table',
      component: () => import(/* webpackChunkName: "about" */ '../views/eQTL_Table_full.vue')
    },
    {
      path: '/expression',
      name: 'expression',
      component: () => import(/* webpackChunkName: "about" */ '../views/expression.vue')
    },
    {
      path: '/about',
      name: 'about',
      component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
