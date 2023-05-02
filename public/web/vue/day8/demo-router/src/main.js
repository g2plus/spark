import {createApp} from 'vue'

//使用动态组件模拟路由
//import App from './components/00.dynamic/App.vue'

//使用location的onhashchange来实现跳转 locatin.hash
//import App from './components/01.easy-router/App.vue'

//router-link与占位符配合使用路由跳转
// import App from './components/02.start/App.vue'
// import router from './components/02.start/router'

//多级路由children的使用
// import App from './components/03.plus/App.vue'
// import router from './components/03.plus/router'

//参数路由，使用函数方式进行路由的跳转 props:true 编程式导航
// import App from './components/04.params/App.vue'
// import router from './components/04.params/router'

//声明路由进行跳转 name
// import App from './components/05.named/App.vue'
// import router from './components/05.named/router'


//声明全局的导航守卫 导航守卫可以控制路由的访问权限 三个参数 to from next
//全局导航守卫会拦截每个路由规则，从而对每个路由进行访问权限的控制
//在守卫方法中如果不声明 next 形参，则默认允许用户访问每一个路由！
//在守卫方法中如果声明了 next 形参，则必须调用 next() 函数，否则不允许用户访问任何一个路由
//此案列允许访问任意路由
// import App from './components/06.guards/App.vue'
// import router from './components/06.guards/router'


//全局路由导航守卫 next的使用
import App from './components/07.next/App.vue'
import router from './components/07.next/router'

import './assets/css/bootstrap.css'
import './index.css'

const app = createApp(App)

// 挂载路由模块 重点
app.use(router)

app.mount('#app')
