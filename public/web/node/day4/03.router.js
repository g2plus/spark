// 这是路由模块
// 1. 导入 express
const express = require('express')
// 2. 创建路由对象
const router1 = express.Router()

// 3. 挂载具体的路由
router1.get('/user/list', (req, res) => {
    res.send({"code": 200, "data": [], "message": "success"})
})
router1.post('/user/add', (req, res) => {
    res.send({"code": 200, "message": "success"})
})

// 4. 向外导出路由对象
module.exports = router1