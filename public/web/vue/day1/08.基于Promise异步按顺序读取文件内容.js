import thenFs from 'then-fs'

//异步读取文件，没有读取到的文件会被catch捕获
thenFs
    .readFile('./files/戴望舒.txt', 'utf8')
    .catch((err) => {
        console.log(err.message)
    })
    .then((r1) => {
        console.log(r1)
        return thenFs.readFile('./files/钱钟书.txt', 'utf8')
    })
    .then((r2) => {
        console.log(r2)
        return thenFs.readFile('./files/冰心.txt', 'utf8')
    })
    .then((r3) => {
        console.log(r3)
    })
