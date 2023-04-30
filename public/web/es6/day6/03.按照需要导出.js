// 每个模块中可以使用多次按需导出
// 按需导入的成员名称必须和按需导出的名称保持一致
// 按需导入时，可以使用 as 关键字进行重命名
// 按需导入可以和默认导入一起使用

export let n1 = 10;
export let n2 = 20;
export function hello() {
    console.log("Hello");
}

function welcome() {
    console.log("You are welcomed to China!");
}
export default {
    n1,
    n2,
    hello,
    welcome
}