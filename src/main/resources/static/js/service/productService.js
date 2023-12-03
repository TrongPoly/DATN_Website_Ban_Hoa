app.service('ProductService', function($http) {
	var url = location.origin + "/api/userLogin";
	this.init = function() {
		$http.get(url)
			.then(function(resp) {
				var user = resp.data;
				sessionStorage.setItem("email", user.email);
			})
			.catch(function(error) {
				console.error('Lỗi khi lấy cart từ session:', error);
			});
	};

	// Gọi phương thức init() trong constructor
	this.init();

	this.getAllProduct = function(ascending,idCategory) {
		return $http.get(location.origin+ '/api/product?ascending='+ascending+'&idCategory='+idCategory);
	};
	this.getOneProduct = function(idProduct) {
		return $http.get(location.origin+`/api/product/${idProduct}`);
	};
	this.getByCategory = function(idProduct) {
		return $http.get(location.origin+`/api/product/category/${idProduct}`)
	}

});