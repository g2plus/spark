import thenFs from "then-fs"
//基于promise依次读取文件
var op = thenFs.readFile("./files/1.txt", "utf8")
    .then(dataStr1 => {
        console.log(dataStr1);
        var op1 = thenFs.readFile("./files/2.txt", "utf8")
        console.log(op1);
        return op1;
    }).then(dataStr2 => {
        console.log(dataStr2);
        var op1 = thenFs.readFile("./files/3.txt", "utf8")
        console.log(op1);
        return op1;
    }).then(dataStr3 => {
        var op1 = new Promise();
        console.log(op1);

        console.log(dataStr3);
    });
console.log(op);