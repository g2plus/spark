// 这是路由模块
// 1. 导入 express
const express = require('express');
// 2. 创建路由对象
const router2 = express.Router();

const pageResult = {
    rows: [{
        book_id: 1,
        book_name: "李白"
    }],
    code: "",
    message: "",
    total: 0
}

// var mysql = require('mysql');

// var connection = mysql.createConnection({
//     host: '114.132.210.77',
//     user: 'root',
//     password: 'cpc!23',
//     port: '3306',
//     database: 'remote'
// });



// 3. 挂载具体的路由
router2.get('/book/list', (req, res) => {
    // connection.connect();
    // var sql = 'SELECT * FROM book';
    // //查
    // connection.query(sql, function(err, data) {
    //     if (err) {
    //         console.log('[SELECT ERROR] - ', err.message);
    //         return;
    //     }
    //     pageResult.data = data;
    //     pageResult.code = "0";
    //     pageResult.message = "successfully";
    // });
    // connection.query(sql, function(err, data) {
    //     if (err) {
    //         console.log('[SELECT ERROR] - ', err.message);
    //         return;
    //     }
    //     let obj = JSON.parse(JSON.stringify(data));
    //     pageResult.total = obj[0].count;
    // })
    // connection.end();
    res.send(pageResult)
})
router2.post('/book/add', (req, res) => {
    res.send('Add new book.')
})

// 4. 向外导出路由对象
module.exports = router2