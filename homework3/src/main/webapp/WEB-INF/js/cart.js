var cart = new Array();
function addToCartButton(name) {
    return $("<button>")
        .html("add to cart")
        .click(function () {
            addToCart(name);
        });
}

function addToCart(name) {
     var newProduct =_.filter(disks, function (disk) {
                    return disk.name == name;
                    });
     cart.push(newProduct[0]);

}

function goToCart() {
    dataToView(cart);
    }
