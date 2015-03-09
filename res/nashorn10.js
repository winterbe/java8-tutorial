var results = [];

var Context = function () {
    this.foo = 'bar';
};

Context.prototype.testArgs = function () {
    if (arguments[0]) {
        results.push(true);
    }
    if (arguments[1]) {
        results.push(true);
    }
    if (arguments[2]) {
        results.push(true);
    }
    if (arguments[3]) {
        results.push(true);
    }
};

var testPerf = function () {
    var context = new Context();
    context.testArgs();
    context.testArgs(1);
    context.testArgs(1, 2);
    context.testArgs(1, 2, 3);
    context.testArgs(1, 2, 3, 4);
};