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

var imports = new JavaImporter(java.util, java.io);
with (imports) {
    var map = new HashMap();
}