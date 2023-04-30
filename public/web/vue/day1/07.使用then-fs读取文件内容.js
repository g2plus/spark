import thenFs from 'then-fs'
//实现的功能读取文件(编码utf8)，并在cosole打印内容
thenFs.readFile('./files/鲁迅名言.txt', 'utf8').then((r1) => {
    console.log(r1)
})
thenFs.readFile('./files/钱钟书.txt', 'utf8').then((r2) => {
    console.log(r2)
})
thenFs.readFile('./files/冰心.txt', 'utf8').then((r3) => {
    console.log(r3)
})
