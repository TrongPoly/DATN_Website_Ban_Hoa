// Tạo một AngularJS service mới và đăng ký nó trong ứng dụng AngularJS của bạn
app.service('CartService', function() {
	var cart = [];
	// Lấy giỏ hàng từ LocalStorage khi khởi động service

	if (localStorage.getItem('cartItems-' + sessionStorage.getItem("email"))) {
		cart = JSON.parse(localStorage.getItem('cartItems-' + sessionStorage.getItem("email")));
	}
	// Lấy giỏ hàng hiện tại
	this.getCart = function() {
		cart.forEach(function(item) {
			delete item.$$hashKey;
		});
		return cart;
	};
	// Kiểm tra một item đã có trong giỏ hàng hay chưa
	this.isItemInCart = function(item) {
		for (var i = 0; i < cart.length; i++) {
			if (item.id === cart[i].id) {
				return true;
			}
		}
		return false;
	};

	// Thêm sản phẩm vào giỏ hàng
	this.addToCart = function(item) {
		var itemCopy = angular.copy(item);
		delete itemCopy.$$hashKey;
		// Check if item.id already exists in cart
		for (var i = 0; i < cart.length; i++) {
			if (cart[i].id === itemCopy.id) {
				// Item already exists, update quantity and exit the function
				cart[i].quant = itemCopy.quant;
				cart[i].selected = true;
				saveCartToLocalStorage();
				return;
			}
		}
		cart.push(itemCopy);
		saveCartToLocalStorage();
	};
	this.checkLogin = function() {
		console.log(sessionStorage.getItem("email"))
		if (sessionStorage.getItem("email") === 'undefined') {
			console.log("aaa")
			return false;
		}
		console.log("bbbb")
		return true;

	}
	// Xóa sản phẩm khỏi giỏ hàng
	this.removeFromCart = function(item) {
		var index = cart.indexOf(item);
		if (index !== -1) {
			cart.splice(index, 1);
			saveCartToLocalStorage();
		}
	};

	//Bỏ chọn sản phẩm
	this.selectProduct = function(item) {
		if (item.selected == false) {
			item.selected = true;
		} else {
			item.selected = false;
		}
		saveCartToLocalStorage();
	}

	// Xóa toàn bộ giỏ hàng
	this.clearCart = function() {
		cart = [];
		saveCartToLocalStorage();
	};
	//Tăng số lượng sản phẩm
	this.increase = function(item) {
		item.quant += 1;
		saveCartToLocalStorage();
	}
	//Giảm số lượng sản phẩm
	this.reduce = function(item) {
		item.quant -= 1;
		saveCartToLocalStorage();
	}

	//Cập nhật số lượng còn lại của sản phẩm
	this.resetQuantity = function(item, quantity, isAvailable) {
		item.quantity = quantity;
		item.isAvailable = isAvailable;
		if (item.quant > quantity) {
			item.quant = quantity;
		};
		saveCartToLocalStorage();
	}

	this.resetCart = function() {
		cart.forEach(function(item) {
			item.selected = false;
		});
		saveCartToLocalStorage();
	}

	// Lưu giỏ hàng vào LocalStorage
	function saveCartToLocalStorage() {
		localStorage.setItem('cartItems-' + sessionStorage.getItem("email"), JSON.stringify(cart));
	};




	//Tính tổng tiền
	this.total = function() {
		var total = 0;
		for (var i = 0; i < cart.length; i++) {
			if (cart[i].selected == true) {
				var product = cart[i];
				total += product.quant * product.price;
			}
		}
		return total;
	}
});