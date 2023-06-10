//备注请使用postman等工具进行测试

// 1. 导入 express
const express = require('express')
    // 2. 创建 web 服务器
const app = express()
const users = [
    { id: 1, name: 'zs' },
    { id: 2, name: 'lisi' },
    { id: 3, name: 'wangwu' },
];

// 4. 监听客户端的 GET 和 POST 请求，并向客户端响应具体的内容
app.get('/user', (req, res) => {
    // 调用 express 提供的 res.send() 方法，向客户端响应一个 JSON 对象
    res.send({ name: 'zs', age: 20, gender: '男' })
})
app.post('/user', (req, res) => {
    // 调用 express 提供的 res.send() 方法，向客户端响应一个 JSON 对象
    res.send({ name: 'ruoyi', age: 1, gender: '女' })
})
app.get('/', (req, res) => {
        // 通过 req.query 可以获取到客户端发送过来的 查询参数
        // 注意：默认情况下，req.query 是一个空对象
        console.log(req.query)
        res.send(req.query)
    })
    // 注意：这里的 :id 是一个动态的参数
app.get('/user/:id/:username', (req, res) => {
    // req.params 是动态匹配到的 URL 参数，默认也是一个空对象
    console.log(req.params)
    res.send(req.params)
})

//参考链接:https://www.jianshu.com/p/211b4f76c304
app.get('/user/:id/', (req, res) => {
    let user = users.find(b => b.id === parseInt(req.params.id));
    if (!user) return res.status(404).json({ msg: 'The user with the given ID not find.' });
    res.json(user).end();
})

// 3. 启动 web 服务器
app.listen(80, () => {
    console.log('express server running at http://127.0.0.1')
})