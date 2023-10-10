// Tạo một AngularJS service mới và đăng ký nó trong ứng dụng AngularJS của bạn
app.service('CartService', function() {
	var cart = [];

	// Lấy giỏ hàng từ LocalStorage khi khởi động service
	if (localStorage.getItem('cartItems-user@gmail.com')) {
		cart = JSON.parse(localStorage.getItem('cartItems-user@gmail.com'));
	}
	// Kiểm tra một item đã có trong giỏ hàng hay chưa
	this.isItemInCart = function(item) {
		return cart.includes(item);
	};

	// Thêm sản phẩm vào giỏ hàng
	this.addToCart = function(item) {
		let found = false;
		for (const i of cart) {
			if (item.id === i.id) {
				i.quantity += item.quantity;
				found = true;
				break;
			}
		}
		if (found == false) {
			cart.push(item);
		}
		saveCartToLocalStorage();
	};

	// Xóa sản phẩm khỏi giỏ hàng
	this.removeFromCart = function(item) {
		var index = cart.indexOf(item);
		if (index !== -1) {
			cart.splice(index, 1);
			saveCartToLocalStorage();
		}
	};

	// Xóa toàn bộ giỏ hàng
	this.clearCart = function() {
		cart = [];
		saveCartToLocalStorage();
	};
	//Tăng số lượng sản phẩm
	this.increase = function(item){
		item.quantity +=1;
		saveCartToLocalStorage();
	}
	//Tăng số lượng sản phẩm
	this.reduce = function(item){
		item.quantity -=1;
		saveCartToLocalStorage();
	}

	// Lưu giỏ hàng vào LocalStorage
	function saveCartToLocalStorage() {
		localStorage.setItem('cartItems-user@gmail.com', JSON.stringify(cart));
	};

	// Lấy giỏ hàng hiện tại
	this.getCart = function() {
		return cart;
	};
});