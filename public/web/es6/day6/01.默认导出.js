let n1 = 10;
let n2 = 20;

function es6() {
    console.log("es6");
}
export default {
    n1,
    es6
}

//note 
//默认导出仅能使用一次
//SyntaxError: Identifier '.default' has already been declared
// export default {
//     n1,
//     es6
// }