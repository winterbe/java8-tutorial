function sqrt(x)  x * x
print(sqrt(3));

var array = [1, 2, 3, 4];
for each (var num in array) print(num);

var runnable = new java.lang.Runnable() {
    run: function () {
        print('on the run');
    }
};

runnable.run();

var System = Java.type('java.lang.System');
System.out["println(double)"](12);

var Arrays = Java.type("java.util.Arrays");
var javaArray = Java.to([2, 3, 7, 11, 14], "int[]");

Arrays.stream(javaArray)
    .filter(function (num) {
        return num % 2 === 1;
    })
    .forEach(function (num) {
        print(num);
    });