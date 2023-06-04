// 1. 导入 fs 文件系统模块
const fs = require('fs')

// 2. 调用 fs.writeFile() 方法，写入文件的内容
//    参数1：表示文件的存放路径
//    参数2：表示要写入的内容
//    参数3：回调函数
const content = "For the ideal that I hold dear to my heart,I'd not regret a thousand times to die." +
    "\nDifferences between brothers can not sever their bloodties." +
    "\nNo matter how high the mountain is, one can always ascend to its top." +
    "\nMy conscience stays untainted in spite of rumors and slanders from the outside." +
    "\nMan with hands in pockets feels cocky all day." +
    "\nWithout the one percent of inspiration, all the perspiration in the world is only a bucket of sweat.";
fs.writeFile('./files/3.txt', content, function(err) {
    // 2.1 如果文件写入成功，则 err 的值等于 null
    // 2.2 如果文件写入失败，则 err 的值等于一个 错误对象
    // console.log(err)

    if (err) {
        return console.log('文件写入失败！' + err.message)
    }else{
        return console.log("文件写入成功");
    }

    console.log('文件写入成功！')
})