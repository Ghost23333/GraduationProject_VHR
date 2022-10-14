import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Home from "@/views/Home";
import userInfo from "@/views/UserInfo";
import UserInfo from "@/views/UserInfo";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login,
    hidden:true
  },{
    path: '/home',
    name: 'home',
    component: Home,
    hidden: true,
    children:[
      {
        path: '/userInfo',
        name: 'userInfo',
        component: UserInfo,
      }
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
