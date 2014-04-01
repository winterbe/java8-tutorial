function Product(name) {
    this.name = name;
}

Product.prototype.stock = 0;
Product.prototype.price = 0;
Product.prototype.getValueOfGoods = function() {
    return this.stock * this.price;
};

var product = new Product('Pencil');
product.price = 4.99;
product.stock = 78;

print('Value of Goods: ' + product.getValueOfGoods());


var getValueOfGoods = function(javaProduct) {
    var jsProduct = new Product();
    Object.bindProperties(jsProduct, javaProduct);
    return jsProduct.getValueOfGoods();
};