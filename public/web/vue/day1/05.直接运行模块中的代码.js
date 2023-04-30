//函数片段，未使用export关键字，在外部import 时，代码自动被执行
// for (let i = 0; i < 10; i++) {
//     console.log(isPrinme(i));
// }
console.log("1到100之间的所有质数:");
let arr = prinmeN(100);
for (let i = 0; i < arr.length; i++) {
    console.log(arr[i]);
}

//判断这个数是否为质数
function isPrinme(n) {
    if (n == 0 || n == 1) {
        return false;
    }
    if (n == 2) {
        return true;
    }
    for (var i = 2; i < Math.sqrt(n); i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

//输出某个范围之间的所有质数
function prinmeN(n) {
    var flag = 0;
    var result = [];
    if (n == 0 || n == 1) {
        result = [];
    } else if (n == 2) {
        result = [2];
    } else if (n == 3 || n == 4) {
        result = [2, 3]
    } else {
        result.push(2, 3);
        for (var i = 5; i <= n; i++) {
            for (var j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    flag = 1;
                    break;
                } else {
                    flag = 0;
                }
            }
            if (flag == 0) {
                result.push(i);
            }
        }

    }
    //返回一个数组
    return result;
}

export function PrimeNumber(N) {
    return prinmeN(N);
}
