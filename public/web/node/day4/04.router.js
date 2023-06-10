// 这是路由模块
// 1. 导入 express
const express = require('express');
// 2. 创建路由对象
const router2 = express.Router();

const pageResult = {
    data: [{
        book_id: 1,
        book_name: "李白"
    }],
    code: "",
    message: "",
    total: 0
}


// 3. 挂载具体的路由
router2.get('/book/list', (req, res) => {
    res.send(pageResult)
})
router2.post('/book/add', (req, res) => {
    res.send('Add new book.')
})

// 4. 向外导出路由对象
module.exports = router2