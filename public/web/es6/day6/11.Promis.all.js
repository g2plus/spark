import thenFs from "then-fs"

//promise all 
const arr = [
    thenFs.readFile("./files/11.txt", "utf8"),
    thenFs.readFile("./files/2.txt", "utf8"),
    thenFs.readFile("./files/3.txt", "utf8"),
];

Promise.all(arr)
    //批量异步任务，更具任务先后执行
    .then(([r1, r2, r3]) => {
        console.log(r1, r2, r3);
    }).catch(err => {
        console.log(err.message);
    });