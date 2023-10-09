app.service('ProductService', function($http) {
	this.getAllProduct = function() {
		return $http.get('http://localhost:8080/api/product');
	};
	this.getOneProduct = function(idProduct) {
		return $http.get(`http://localhost:8080/api/product/${idProduct}`);
	};
});