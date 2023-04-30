import thenFs from "then-fs"
//基于promise依次读取文件
var p = thenFs.readFile("./files/11.txt", "utf8")
    .catch(err => {
        console.log(err.message);
    })
    .then(dataStr1 => {
        console.log(dataStr1);
        return thenFs.readFile("./files/2.txt", "utf8");
    }).then(dataStr2 => {
        console.log(dataStr2);
        return thenFs.readFile("./files/3.txt", "utf8");
    }).then(dataStr3 => {
        console.log(dataStr3);
    });
console.log(p);