import { createRouter, createWebHashHistory } from 'vue-router'

import Login from './components/MyLogin.vue'
import Home from './components/MyHome.vue'

import Users from './components/menus/MyUsers.vue'
import Rights from './components/menus/MyRights.vue'
import Goods from './components/menus/MyGoods.vue'
import Orders from './components/menus/MyOrders.vue'
import Settings from './components/menus/MySettings.vue'
import UserDetail from './components/user/MyUserDetail.vue'

// 创建路由实例对象
const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    { path: '/', redirect: '/login' },
      //动态路由配置
    { path: '/login', component: Login, name: 'login' },
    {
      path: '/home',
      //重定向，默认在body区域显示该页面内容
      redirect: '/home/rights',
      //设置页面
      component: Home,
      name: 'home',
      children: [
          //二级路由
        { path: 'users', component: Users },
        { path: 'rights', component: Rights },
        { path: 'goods', component: Goods },
        { path: 'orders', component: Orders },
        { path: 'settings', component: Settings },
          //参数路由
        { path: 'users/:id', component: UserDetail, props: true },
      ],
    },
  ],
})

// 全局路由导航守卫
router.beforeEach((to, from, next) => {
  // 判断用户访问的是否为登录页
  if (to.path === '/login') return next()
  // 获取 token 值
  const tokenStr = localStorage.getItem('token')
  if (!tokenStr) {
    next('/login')
  } else {
    next()
  }
})

export default router
