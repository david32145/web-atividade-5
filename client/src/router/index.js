import Vue from 'vue'
import VueRouter from 'vue-router'
import CarListView from '../views/CarListView'
import CarCreateView from '../views/CarCreateView'
import CarUpdateView from '../views/CarUpdateView'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/carros',
      component: CarListView
    },
    {
      path: '/carros/novo',
      component: CarCreateView
    },
    {
      path: '/carros/editar/:id',
      component: CarUpdateView
    },
    {
      path: '*',
      redirect: '/carros'
    }
  ]
})

export default router
