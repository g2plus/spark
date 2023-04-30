var fs = require("fs");

//
var path = "./files";
fs.stat(path, function(err, stat) {
    if (err) {
        console.error(err);
        throw err;
    }
    console.log(stat);
    console.log(path + "是一个文件" + stat.isFile());
    console.log(path + "是一个文件夹" + stat.isDirectory());
})



console.log("end");