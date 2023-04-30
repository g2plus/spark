import fs from 'fs'

//模拟实现thenfs
function getFile(fpath, encode) {
    return new Promise(function (resolve, reject) {
        fs.readFile(fpath, encode, (err, dataStr) => {
            if (err) return reject(err)
            resolve(dataStr)
        })
    })
}

getFile('./files/鲁迅名言.txt', 'utf8')
    .then((r1) => {
        console.log(r1)
    })
    .catch((err) => console.log(err.message))
