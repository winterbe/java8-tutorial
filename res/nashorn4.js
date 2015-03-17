// function literal with no braces

function sqr(x) x * x;

print(sqr(3));


// for each

var array = [1, 2, 3, 4];
for each (var num in array) print(num);


// object literals in constructors

var runnable = new java.lang.Runnable() {
    run: function() {
        print('on the run');
    }
};

runnable.run();


// bind properties

var o1 = {};
var o2 = { foo: 'bar'};

Object.bindProperties(o1, o2);

print(o1.foo);
o1.foo = 'BAM';
print(o2.foo);


// string trim

print("   hehe".trimLeft());
print("hehe    ".trimRight() + "he");


// whereis
print(__FILE__, __LINE__, __DIR__);


// java import

var imports = new JavaImporter(java.io, java.lang);
with (imports) {
    var file = new File(__FILE__);
    System.out.println(file.getAbsolutePath());
    // /path/to/my/script.js
}


// convert iterable to js array

var list = new java.util.ArrayList();
list.add("s1");
list.add("s2");
list.add("s3");

var jsArray = Java.from(list);
print(jsArray);
print(Object.prototype.toString.call(jsArray));


// convert js array to java array

var javaArray = Java.to([3, 5, 7, 11], "int[]");
print(Object.prototype.toString.call(javaArray));


// calling super

var SuperRunner = Java.type('com.winterbe.java8.samples.nashorn.SuperRunner');
var Runner = Java.extend(SuperRunner);

var runner = new Runner() {
    run: function() {
        Java.super(runner).run();
        print('on my run');
    }
}
runner.run();



// load

load('http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.6.0/underscore-min.js');

var odds = _.filter([1, 2, 3, 4, 5, 6], function (num) {
    return num % 2 == 1;
});

print(odds);