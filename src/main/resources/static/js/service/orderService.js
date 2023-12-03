app.service('OrderService', function($http) {
	var url = location.origin + `/api/order`;
	
	this.init = function() {
		$http.get(location.origin+"/api/userLogin")
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
	
	this.getOrder = function(status) {
		return $http.get(url+"/"+sessionStorage.getItem("email")+"?status="+status);
	}
	this.getOrderById = function(idOrder){
		return $http.get(url+"/id?idOrder="+idOrder);
	}
	this.getOrderDetails = function(idOrder){
		return $http.get(url+"/details/"+idOrder);
	}
	this.updateOrder = function(idOrder,statusId, note){
		let email = sessionStorage.getItem("email");
		if(note==undefined){
		return $http.put(url+"/update_status/"+idOrder+"?statusId="+statusId+"&email="+email+"&note=");
		}
		return $http.put(url+"/update_status/"+idOrder+"?statusId="+statusId+"&email="+email+"&note="+note);
	}
	//get status
	this.getOrderStatus = function(idOrder){
		return $http.get(url+"/status/"+idOrder);
	}
	
	//ADMIN
	this.getAllOrderByStatus = function(status) {
		return $http.get(url+"?status="+status);
	}
	this.getAllOrder = function(){
		return $http.get(url+"/get_all");
	}
	this.getAllOrderByEmail = function(email){
		return $http.get(url+"/get_all/"+email);
	}
})