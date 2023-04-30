import mysql from "mysql"
const connection = mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: 'root',
    port: '3306',
    database: 'bank1'
})

export default connection