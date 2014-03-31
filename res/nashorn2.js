var Nashorn2 = Java.type('com.winterbe.java8.Nashorn2');
var result = Nashorn2.fun('John Doe');
print('\n' + result);

Nashorn2.fun2(new Date());
Nashorn2.fun2(new RegExp());
Nashorn2.fun2({foo: 'bar'});


print('passing object hash:');
Nashorn2.fun3({
    foo: 'bar',
    bar: 'foo'
});


print('passing custom person object:');

function Person(firstName, lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.getFullName = function() {
        return this.firstName + " " + this.lastName;
    }
}

var person1 = new Person("Peter", "Parker");
Nashorn2.fun3(person1);
Nashorn2.fun4(person1);