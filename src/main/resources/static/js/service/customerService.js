// Tạo một AngularJS service mới và đăng ký nó trong ứng dụng AngularJS của bạn
app.service('CustomerService', function($http) {
	var url = window.origin+`/api/customer/`;
	this.getCustomer = function() {
		return $http.get(url+sessionStorage.getItem("email"));
	}
	this.updateCustomer = function(Customer) {
		return $http.post(url+"update",Customer);
	}
})