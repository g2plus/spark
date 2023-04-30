import thenFs from "then-fs"
//A 和 D 属于同步任务。会根据代码的先后顺序依次被执行
//C 和 B 属于异步任务。它们的回调函数会被加入到任务队列中，等待主线程空闲时再执行
//JavaScript 主线程从“任务队列”中读取异步任务的回调函数，放到执行栈中依次执行。

//这个过程是循环不断的，所以整个的这种运行机制又称为 EventLoop（事件循环）
//JavaScript 把异步任务又做了进一步的划分，异步任务又分为两类
//在执行下一个宏任务之前，先检查是否有待执行的微任务
console.log("A") // 宏任务
    //读文件为宏任务
thenFs.readFile("./files/1.txt", "utf8").then(
    //微任务
    console.log("B")
);
//微任务
setTimeout(() => {
        console.log("c");
    }, 0)
    //A -> D 
    //B -> C
    //在执行下一个宏任务之前，先检查是否有待执行的微任务
    //宏任务
console.log("D");