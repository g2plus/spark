//按需导入
import { n1, n2 as var1, hello } from "./03.按照需要导出.js"
//默认导入-------------------------
import mod1 from "./03.按照需要导出.js"
console.log(n1);
console.log(var1);
hello();
console.log(hello);
console.log("默认导入-------------------------")
console.log(mod1.welcome);
console.log(mod1.n1);
console.log(mod1.n2);
console.log(mod1.hello)