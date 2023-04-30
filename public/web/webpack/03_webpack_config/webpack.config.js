const path = require("path");

//https://www.cnblogs.com/StephenWu5/p/16444818.html
module.exports = {
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: 'bundle.js'
    }
}