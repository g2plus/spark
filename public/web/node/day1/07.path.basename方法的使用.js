const path = require('path')
const {username} = require("../day2/11.自定义模块");

// 定义文件的存放路径
const fpath = '/a/b/c/index.html'

// const fullName = path.basename(fpath)
// console.log(fullName)

const nameWithoutExt = path.basename(fpath, '.html')
//index
console.log(nameWithoutExt)