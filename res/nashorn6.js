load('http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.6.0/underscore-min.js');
load('http://cdnjs.cloudflare.com/ajax/libs/backbone.js/1.1.2/backbone-min.js');


// simple backbone model:
// valueOfGoods will automatically be calculated when stock or price changes
var Product = Backbone.Model.extend({
    defaults: {
        stock: 0,
        price: 0.0,
        name:'',
        valueOfGoods: 0.0
    },

    initialize: function() {
        this.on('change:stock change:price', function() {
            var stock = this.get('stock');
            var price = this.get('price');
            var valueOfGoods = this.getValueOfGoods(stock, price);
            this.set('valueOfGoods', valueOfGoods);
        });
    },

    getValueOfGoods: function(stock, price) {
        return stock * price;
    }
});

var product = new Product();
product.set('name', 'Pencil');
product.set('stock', 1000);
product.set('price', 3.99);


// pass backbone model to java method
var Nashorn6 = Java.type('com.winterbe.java8.samples.nashorn.Nashorn6');
Nashorn6.getProduct(product.attributes);


// bind java object to backbone model and pass result back to java
var calculate = function(javaProduct) {
    var model = new Product();
    model.set('name', javaProduct.name);
    model.set('price', javaProduct.price);
    model.set('stock', javaProduct.stock);
    return model.attributes;
};