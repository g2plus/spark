import thenFs from 'then-fs'

console.log('A');
// 如果在 function 中使用了 await，则 function 必须被 async 修饰
// 在 async 方法中，第一个 await 之前的代码会同步执行，await 之后的代码会异步执行
async function getAllFile() {
    console.log('B');
    console.log("你就是个垃圾!");
    const r1 = await thenFs.readFile('./files/鲁迅名言.txt', 'utf8')
    console.log(r1)
    const r2 = await thenFs.readFile('./files/钱钟书.txt', 'utf8')
    console.log(r2)
    const r3 = await thenFs.readFile('./files/冰心.txt', 'utf8')
    console.log(r3)
    console.log('D')
}

getAllFile()
console.log('C');

// 为了防止某个耗时任务导致程序假死的问题，JavaScript 把待执行的任务分为了两类：
// ① 同步任务（synchronous）
// ⚫ 又叫做非耗时任务，指的是在主线程上排队执行的那些任务
// ⚫ 只有前一个任务执行完毕，才能执行后一个任务
// ② 异步任务（asynchronous）
// ⚫ 又叫做耗时任务，异步任务由 JavaScript 委托给宿主环境进行执行
// ⚫ 当异步任务执行完成后，会通知 JavaScript 主线程执行异步任务的回调函数

// ① 同步任务由 JavaScript 主线程次序执行
// ② 异步任务委托给宿主环境执行
// ③ 已完成的异步任务对应的回调函数，会被
// 加入到任务队列中等待执行
// ④ JavaScript 主线程的执行栈被清空后，会
// 读取任务队列中的回调函数，次序执行
// ⑤ JavaScript 主线程不断重复上面的第 4 步


// JavaScript 主线程从“任务队列”中读取异步
// 任务的回调函数，放到执行栈中依次执行。这
// 个过程是循环不断的，所以整个的这种运行机
// 制又称为 EventLoop（事件循环）
