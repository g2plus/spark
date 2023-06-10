const express = require('express')
const app = express()

// app.use('/files', express.static('./files'))

// 1. 导入路由模块()
const router1 = require('./03.router')
const router2 = require("./04.router")
// 2. 注册路由模块(相当于java里面的具体某个Controller)
//这个类似于springboot里面的@RequestMapping("/api")
app.use('/api', router1)
app.use("/test", router2)

// 注意： app.use() 函数的作用，就是来注册全局中间件

app.listen(80, () => {
    console.log('http://127.0.0.1')
})