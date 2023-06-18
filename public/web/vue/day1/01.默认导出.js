let n1 = 10;
let n2 = 20;
let say = function () {
    console.log("What did you say?");
}


//按需要进行导出
export function show() {
    console.log("Hello!")
}

//默认导出，只能使用导出一次
export default {
    n1,
    n2,
    "hello": say
}

//导出只能导出一次
// export default {
//  n2
// }
