// import './05.直接运行模块中的代码.js' 在控制台打印1-100之间的所有质数
import {PrimeNumber} from './05.直接运行模块中的代码.js'

var number = 10;


console.log("-------------------------------------------")


var arr = PrimeNumber(number);


arr.forEach((element) => console.log(element));
