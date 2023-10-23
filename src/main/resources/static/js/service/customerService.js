// Tạo một AngularJS service mới và đăng ký nó trong ứng dụng AngularJS của bạn
app.service('CustomerService', function($http) {
	var url = window.location.protocol + "//" + window.location.hostname + `:8080/api/customer/`;
	this.getCustomer = function() {
		return $http.get(url+sessionStorage.getItem("email"));
	}
})