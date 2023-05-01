import { createApp } from 'vue'
//#region import App

//ref引用
//import App from './components/01.ref/App.vue'

//$nextTick的调用时机
//import App from './components/02.$nextTick/App.vue'

//动态组件 component :is  keep-alive
//import App from './components/03.dynamic/App.vue'

//插槽
//import App from './components/04.base-slot/App.vue'

//具名插槽
//import App from './components/05.named-slot/App.vue'

//数据插槽(子组件提供数据,父组件控制数据的显示方式)
//import App from './components/06.scoped-slot/App.vue'

//自定义指令
import App from './components/07.directive/App.vue'

import './assets/css/bootstrap.css'
import './index.css'

const app = createApp(App)

// 声明全局自定义指令
/* app.directive('focus', {
  mounted(el) {
    console.log('mounted')
    el.focus()
  },
  updated(el) {
    console.log('updated')
    el.focus()
  },
}) */

//全局注册(当mounted与updated执行相同的业务逻辑，可简写为以下模式)
app.directive('focus', (el) => {
  el.focus()
})

//传递参数键home组件v-color=blue
app.directive('color', (el, colorEnum) => {
  el.style.color = colorEnum.value
})

app.mount('#app')
