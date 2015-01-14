var evaluate1 = function () {
    (function () {
        print(eval("this"));
    }).call(this);
};

var evaluate2 = function () {
    var context = {};
    (function () {
        print(eval("this"));
    }).call(context);
};

var evaluate3 = function (context) {
    (function () {
        print(eval("this"));
    }).call(context);
};