print('------------------');
print('IntArray:');

var IntArray = Java.type('int[]');

var array = new IntArray(5);
array[0] = 5;
array[1] = 4;
array[2] = 3;
array[3] = 2;
array[4] = 1;

for (var i = 0; i < array.length; i++) {
    print(array[i]);
}

try {
    array[5] = 23;
} catch (e) {
    print(e.message);
}

array[0] = "17";
print(array[0]);

array[0] = "wrong type";
print(array[0]);

array[0] = "17.3";
print(array[0]);

print('------------------');

for (var i in array) print(i);

print('------------------');

for each (var val in array) print(val);

print('------------------');
print('ArrayList:');

var ArrayList = Java.type('java.util.ArrayList');

var list = new ArrayList();
list.add('a');
list.add('b');
list.add('c');

for each (var el in list) print(el);
