//引入fs模块
import { readFile } from 'fs';
//resolved or rejected.
readFile('./files/11.txt', 'utf8', (err, data1) => {
    console.log(data1);
    readFile('./files/2.txt', 'utf8', (err, data2) => {
        console.log(data2);
        readFile('./files/3.txt', 'utf8', (err, data3) => {
            console.log(data3);
        });
    });
});