import { createRouter, createWebHistory } from 'vue-router'
import register from "../views/register.vue";
import login from "../views/login.vue"
import InfoOfUser from "../views/InfoOfUser.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/reg',
      name: 'reg',
      component: register
    },
    {
      path: '/user',
      name: 'user',
      component: InfoOfUser
    }
    
  ]
})


router.beforeEach((to, from, next) =>{
  if(!localStorage.getItem("token") && to.name === 'user'){
    alert("Plase login first")
    
    next('/')
  }else{
    next()
  }

})

export default router
