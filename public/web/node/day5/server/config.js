const express = require("express");
const mysql = require('mysql');
const db = mysql.createPool({
    host: '114.132.210.77',
    user: 'root',
    password: 'cpc!23',
    port: '3306',
    database: 'bank1'
})
module.exports = {
    dbPool: db,
    express: express
}