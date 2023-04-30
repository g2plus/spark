import { createApp } from 'vue'
//#region 导入 App 根组件

//监听 watch
//import App from './components/01.watch/App.vue'

//生命周期 created mounted
//import App from './components/02.life-cycle/App.vue'

//父子传值 emit props
//import App from './components/03.father-son/App.vue'

//兄弟组件传值 eventbus
//import App from './components/04.brother/App.vue'

//后代关系传值 provide inject computed函数
//import App from './components/05.provide&inject/App.vue'

//封装axios
import App from './components/06.network/App.vue'

import './assets/css/bootstrap.css'
import './index.css'

import axios from 'axios'

const app = createApp(App)

//设置基本请求路径
axios.defaults.baseURL = 'https://www.escook.cn'

//封装为vue的某个属性
app.config.globalProperties.$http = axios


app.mount('#app')
