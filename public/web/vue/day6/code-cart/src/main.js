import { createApp } from 'vue'
import App from './App.vue'

import './assets/css/bootstrap.css'
import './index.css'

import axios from 'axios'

const app = createApp(App)

//axios.defaults.baseURL = 'https://www.escook.cn'


axios.defaults.baseURL = 'http://localhost:8081'
app.config.globalProperties.$http = axios

app.mount('#app')
