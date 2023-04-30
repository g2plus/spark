const fs = require('fs');

// directory path
const dir = './files';

// list all files in the directory
fs.readdir(dir, (err, files) => {
    if (err) {
        throw err;
    }

    // files object contains all files names
    // log them on console
    files.forEach(file => {
        fs.stat(dir + "/" + file, function(err, stat) {
            if (err) {
                console.error(err);
                throw err;
            }
            console.log(stat);
            console.log(dir + "/" + file + "是一个文件" + stat.isFile());
        })
    });
});