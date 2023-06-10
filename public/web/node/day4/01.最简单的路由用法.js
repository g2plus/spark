const express = require('express')
const app = express()

var ajaxResult = {
    "code": 0,
    "message": "deleted successfully"
}

// 挂载路由
//get
app.get('/', (req, res) => {
    res.send('hello world.')
})
//post
app.post('/', (req, res) => {
    res.send('Post Request.')
})
//delete
app.delete("/", (req, res) => {
    res.send("Delete Request")
})
//put
app.put("/", (req, res) => {
    res.send(ajaxResult)
})

app.listen(80, () => {
    console.log('http://127.0.0.1')
})