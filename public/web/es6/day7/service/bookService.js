//备注熟悉es的导包而已，后台请用java写
import connection from "../server/config.js"


async function test0(req, res) {
    // 测试 mysql 模块能否正常工作
    await connection.query('select 1', (err, results) => {
        // mysql 模块工作期间报错了
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            res.send({ code: "9000", message: "connected" });
        }
    })
};

async function test1(req, res) {
    // 查询 book 表中所有的数据
    const sqlStr = 'select * from book'
    await connection.query(sqlStr, (err, results) => {
        // 查询数据失败
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            res.send({ code: "9000", message: "success", data: results });
        }
    })

    // try {
    //     const sqlStr = 'select * from book';
    //     const [results] = connection.query(sqlStr);
    //     res.send({ code: "9000", message: "success", data: results });
    // } catch (err) {
    //     res.send({ code: "9000", message: "success", data: results });
    // }
};

async function test2(req, res) {
    const book = { book_name: '活着' }
        // 定义待执行的 SQL 语句
    const sqlStr = 'insert into book (book_name, book_author_name) values (?, ?)'
        // 执行 SQL 语句
    await connection.query(sqlStr, [book.book_name], (err, results) => {
        // 执行 SQL 语句失败了
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            if (results.affectedRows === 1) {
                res.send({ code: "9000", message: "success" });
            }
            res.send({ code: "9001", message: err.message });

        }

    })
};

async function test5(req, res) {
    // 演示插入数据的便捷方式
    const book = { book_name: 'Spider-Man2', book_id: 100000000 }
        // 定义待执行的 SQL 语句
    const sqlStr = 'insert into book set ?'
        // 执行 SQL 语句
    await connection.query(sqlStr, book, (err, results) => {
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            if (results.affectedRows === 1) {
                res.send({ code: "9000", message: "success" });
            }
            res.send({ code: "9001", message: err.message });
        }

    })
};

async function test6(req, res) {
    // 演示如何更新用户的信息
    const book = { book_id: 100000000000006, book_name: 'aaa' /*, book_author_name: '000'*/ }
        // 定义 SQL 语句
    const sqlStr = 'update book set book_name=?, book_author_name=? where book_id=?'
        // 执行 SQL 语句
    await connection.query(sqlStr, [book.book_name, book.book_id], (err, results) => {
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            // 注意：执行了 update 语句之后，执行的结果，也是一个对象，可以通过 affectedRows 判断是否更新成功
            if (results.affectedRows === 1) {
                res.send({ code: "9000", message: "success" });
            }
            res.send({ code: "9001", message: err.message });
        }
    })
};

async function test7(req, res) {
    // 演示更新数据的便捷方式
    const book = { book_id: 7, book_name: 'aaaa' }
        // 定义 SQL 语句
    const sqlStr = 'update book set ? where book_id=?'
        // 执行 SQL 语句
    await connection.query(sqlStr, [book, book.book_id], (err, results) => {
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            // 注意：执行了 update 语句之后，执行的结果，也是一个对象，可以通过 affectedRows 判断是否更新成功
            if (results.affectedRows === 1) {
                res.send({ code: "9000", message: "success" });
            }
            res.send({ code: "9001", message: err.message });
        }
    })
};

async function test8(req, res) {
    // 删除 id 为 8 的用户
    const sqlStr = 'delete from book where book_id=?'
    await connection.query(sqlStr, 8, (err, results) => {
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            // 注意：执行了 update 语句之后，执行的结果，也是一个对象，可以通过 affectedRows 判断是否更新成功
            if (results.affectedRows === 1) {
                res.send({ code: "9000", message: "success" });
            }
            res.send({ code: "9001", message: err.message });
        }
    })
};

async function test9(req, res) {
    // 逻辑删除
    const sqlStr = 'update book set enabled=? where book_id=?'
    await connection.query(sqlStr, [0, 9], (err, results) => {
        if (err) {
            res.send({ code: "9001", message: err.message });
        } else {
            // 注意：执行了 update 语句之后，执行的结果，也是一个对象，可以通过 affectedRows 判断是否更新成功
            if (results.affectedRows === 1) {
                res.send({ code: "9000", message: "success" });
            }
            res.send({ code: "9001", message: err.message });
        }
    })
}

export default {
    test0,
    test1,
    test2,
    test5,
    test6,
    test7,
    test8,
    test9
}