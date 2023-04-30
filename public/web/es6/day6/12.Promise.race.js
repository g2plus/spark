import thenFs from "then-fs"

//promise race 
//Promise.race() 方法会发起并行的 Promise 异步操作，只要任何一个异步操作完成，就立即执行下一步的.then 操作（赛跑机制）
const arr = [
    thenFs.readFile("./files/1.txt", "utf8"),
    thenFs.readFile("./files/2.txt", "utf8"),
    thenFs.readFile("./files/3.txt", "utf8"),
];
//Creates a Promise that is resolved or rejected when any of the provided Promises are resolved or rejected.
Promise.race(arr)
    //批量异步任务，更具任务先后执行
    .then(([r1, r2, r3]) => {
        console.log(r1, r2, r3);
    }).catch(err => {
        console.log(err.message);
    });