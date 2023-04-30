const fs = require('fs');
const superheroes = require('superheroes');

const express = require('express');
const app = express();

app.get('/', function(req, res) {
    res.send('<h1>Hello world!</h1>')
})

app.listen(3000, function() {
    console.log("server started");
})

console.log(superheroes.all);
console.log("-----------------------");
//=> ['3-D Man', 'A-Bomb', …]

console.log(superheroes.random());
//=> 'Spider-Ham'

// destination.txt will be created or overwritten by default.
fs.copyFileSync('source.txt', 'destination.txt');
console.log('source.txt was copied to destination.txt');