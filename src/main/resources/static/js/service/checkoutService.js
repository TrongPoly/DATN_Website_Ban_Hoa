app.service('CheckoutService', function() {
	var cart = [];
	var order = [];
	// Lấy giỏ hàng từ LocalStorage khi khởi động service
	if (localStorage.getItem('cartItems-user@gmail.com')) {
		cart = JSON.parse(localStorage.getItem('cartItems-user@gmail.com'));
	}
	// Lấy giỏ hàng hiện tại
	this.getCart = function() {
		return cart;
	};
	// Lấy sản phẩm có selected = true trong cart
	this.getSelectedProduct = function(){
		console.log(cart);
		for(var i = 0; i<cart.length; i++){
			if(cart[i].selected===true){
				order.push(cart[i]);
			}
		}
		return order;
	}
	
	
	
});