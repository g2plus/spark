//mysql的连接信息
const mysql = require('mysql')

const db = mysql.createPool({
    host: '114.132.210.77',
    user: 'root',
    password: 'cpc!23',
    database: 'remote',
})

module.exports = db