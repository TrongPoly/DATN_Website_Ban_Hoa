app.service('ProductService', function($http) {
	var url = window.location.protocol + "//" + window.location.hostname + `:8080/api/userLogin`;
	$http.get(url)
		.then(function(resp) {
			user = resp.data;
			sessionStorage.setItem("email",user.email);
			console.log(user);
		}).catch(function(error) {
			console.error('Lỗi khi lấy cart từ session:', error);
		});
	console.log(sessionStorage.getItem("email"))
	
	this.getAllProduct = function() {
		return $http.get('http://localhost:8080/api/product');
	};
	this.getOneProduct = function(idProduct) {
		return $http.get(`http://localhost:8080/api/product/${idProduct}`);
	};
});