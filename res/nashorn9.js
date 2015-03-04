var size = 100000;

var testPerf = function () {
    var result = Math.floor(Math.random() * size) + 1;
    for (var i = 0; i < size; i++) {
        result += i;
    }
    return result;
};