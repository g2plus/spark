const server = require("../server/config");
const dbPool = server.dbPool;

module.exports = {
    test0(req, res) {
        // 测试 mysql 模块能否正常工作
        dbPool.query('select 1', (err, results) => {
            // mysql 模块工作期间报错了
            if (err) return console.log(err.message)
                // 能够成功的执行 SQL 语句
            console.log(results)
        })
    },
    test1(req, res) {
        // 查询 book 表中所有的数据
        const sqlStr = 'select * from book'
        dbPool.query(sqlStr, (err, results) => {
            // 查询数据失败
            if (err) return console.log(err.message)
                // 查询数据成功
                // 注意：如果执行的是 select 查询语句，则执行的结果是数组
            console.log(results);
            res.send(results);
        })
    },
    test2(req, res) {
        const book = { book_name: '活着', book_author_name: '余华' }
            // 定义待执行的 SQL 语句
        const sqlStr = 'insert into book (book_name, book_author_name) values (?, ?)'
            // 执行 SQL 语句
        dbPool.query(sqlStr, [book.book_name, book.book_author_name], (err, results) => {
            // 执行 SQL 语句失败了
            if (err) return console.log(err.message)
                // 成功了
                // 注意：如果执行的是 insert into 插入语句，则 results 是一个对象
                // 可以通过 affectedRows 属性，来判断是否插入数据成功
            if (results.affectedRows === 1) {
                console.log('插入数据成功!')
            }
        })
    },
    test5(req, res) {
        // 演示插入数据的便捷方式
        const book = { book_name: 'Spider-Man2', book_author_name: 'pcc4321' }
            // 定义待执行的 SQL 语句
        const sqlStr = 'insert into book set ?'
            // 执行 SQL 语句
        dbPool.query(sqlStr, book, (err, results) => {
            if (err) return console.log(err.message)
            if (results.affectedRows === 1) {
                console.log('插入数据成功')
            }
        })
    },
    test6(req, res) {
        // 演示如何更新用户的信息
        const book = { book_id: 6, book_name: 'aaa', book_author_name: '000' }
            // 定义 SQL 语句
        const sqlStr = 'update book set book_name=?, book_author_name=? where book_id=?'
            // 执行 SQL 语句
        dbPool.query(sqlStr, [book.book_name, book.book_author_name, book.book_id], (err, results) => {
            if (err) return console.log(err.message)
                // 注意：执行了 update 语句之后，执行的结果，也是一个对象，可以通过 affectedRows 判断是否更新成功
            if (results.affectedRows === 1) {
                console.log('更新成功')
            }
        })
    },
    test7(req, res) {
        // 演示更新数据的便捷方式
        const book = { book_id: 7, book_name: 'aaaa', book_author_name: '0001' }
            // 定义 SQL 语句
        const sqlStr = 'update book set ? where book_id=?'
            // 执行 SQL 语句
        dbPool.query(sqlStr, [book, book.book_id], (err, results) => {
            if (err) return console.log(err.message)
            if (results.affectedRows === 1) {
                console.log('更新数据成功')
            }
        })
    },
    test8(req, res) {
        // 删除 id 为 8 的用户
        const sqlStr = 'delete from book where book_id=?'
        dbPool.query(sqlStr, 8, (err, results) => {
            if (err) return console.log(err.message)
                // 注意：执行 delete 语句之后，结果也是一个对象，也会包含 affectedRows 属性
            if (results.affectedRows === 1) {
                console.log('删除数据成功')
            }
        })
    },
    test9(req, res) {
        // 逻辑删除
        const sqlStr = 'update book set enabled=? where book_id=?'
        dbPool.query(sqlStr, [0, 9], (err, results) => {
            if (err) return console.log(err.message)
            if (results.affectedRows === 1) {
                console.log('逻辑删除成功')
            }
        })
    }

}