var mysql = require('mysql');

var connection = mysql.createConnection({
    host: '114.132.210.77',
    user: 'root',
    password: 'cpc!23',
    port: '3306',
    database: 'remote'
});


connection.connect();

var sql = 'SELECT * FROM book';
//查
connection.query(sql, function(err, result) {
    var startTime = new Date();
    console.log(startTime);
    if (err) {
        console.log('[SELECT ERROR] - ', err.message);
        return;
    }
    console.log('--------------------------SELECT----------------------------');
    console.log(result);
    console.log('------------------------------------------------------------\n');
    var endTime = new Date();
    console.log(endTime);
    console.log(endTime - startTime);
});

connection.end();