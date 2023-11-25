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

	this.getAllProduct = function() {
		return $http.get('http://localhost:8080/api/product');
	};
	this.getOneProduct = function(idProduct) {
		return $http.get(`http://localhost:8080/api/product/${idProduct}`);
	};
	this.getByCategory = function(idProduct) {
		return $http.get(`http://localhost:8080/api/product/category/${idProduct}`)
	}
});