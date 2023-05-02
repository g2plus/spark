import { createRouter, createWebHashHistory } from 'vue-router'

import Home from './MyHome.vue'
import Movie from './MyMovie.vue'
import About from './MyAbout.vue'

import Tab1 from './tabs/MyTab1.vue'
import Tab2 from './tabs/MyTab2.vue'

// 创建路由对象
const router = createRouter({
  // 指定路由的工作模式
  history: createWebHashHistory(),
  // 自定义路由高亮的 class 类
  linkActiveClass: 'active-router',
  // 声明路由的匹配规则
  routes: [
    { path: '/', redirect: '/home' },
    { path: '/home', component: Home },
    // 动态路由匹配（声明路由）
    { name: 'mov', path: '/movie/:mid', component: Movie, props: true },
    {
      path: '/about',
      component: About,
      // 嵌套路由的重定向
      redirect: '/about/tab1',
      // 通过 children 属性嵌套声明子级路由规则
      children: [
        { path: 'tab1', component: Tab1 },
        { path: 'tab2', component: Tab2 },
      ],
    },
  ],
})

// 声明全局的导航守卫（前置守卫）
//在守卫方法中如果不声明 next 形参，则默认允许用户访问每一个路由！
//在守卫方法中如果声明了 next 形参，则必须调用 next() 函数，否则不允许用户访问任何一个路由
//此案列允许访问任意路由
//经过验证添加了next形参，无法除了首页无法访问其他路由
//使用场景判断有无登录，登录后可以访问路由限制
router.beforeEach((to, from/*,next*/) => {
  console.log(from)
  console.log('ok')
})

// 导出路由对象
export default router
