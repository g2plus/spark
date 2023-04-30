const express = require("express");
const mysql = require('mysql');
const db = mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: 'root',
    port: '3306',
    database: 'bank1'
})
module.exports = {
    dbPool: db,
    express: express
}