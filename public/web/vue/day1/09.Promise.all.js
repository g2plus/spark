import thenFs from 'then-fs'
//有一个文件不存在,则会被捕获异常
const promiseArr = [
    thenFs.readFile('./files/冰心.txt', 'utf8'),
    thenFs.readFile('./files/钱钟书.txt', 'utf8'),
    thenFs.readFile('./files/鲁迅名言.txt', 'utf8'),
]


//Promise.all() 方法会发起并行的 Promise 异步操作，
//等所有的异步操作全部结束后才会执行下一步的 .then 操作（ 等待机制）
Promise.all(promiseArr)
    .then(([r1, r2, r3]) => {
        console.log(r1, r2, r3);
    }).catch(err => {
    console.log(err.message);
})


//Promise.race() 方法会发起并行的 Promise 异步操作，只要任何一个异步操作完成，就立即执行下一步的
//.then 操作（赛跑机制）
//仅仅打印一个文件的内容
Promise.race(promiseArr)
    .then(result => {
        console.log(result)
    }).catch(err => {
    console.log(err.message);
})
