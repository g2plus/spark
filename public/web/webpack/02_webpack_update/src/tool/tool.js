export const getArrSum = getArrSumFn;

// export const getArrSum = arr => arr.reduce((sum, val) => sum += val, 0)

function getArrSumFn(arr) {
    let sum = 0;
    arr.forEach(element => sum += element);
    return sum;
}