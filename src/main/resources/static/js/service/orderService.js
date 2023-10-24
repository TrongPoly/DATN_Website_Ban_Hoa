app.service('OrderService', function($http) {
	var url = location.origin + `/api/order/`;
	this.getOrder = function(status) {
		return $http.get(url+sessionStorage.getItem("email")+"?status="+status);
	}
	this.getOrderDetails = function(idOrder){
		return $http.get(url+"details/"+idOrder);
	}
	this.requestCancelOrder = function(idOrder){
		return $http.put(url+"cancel_reuquest/"+idOrder);
	}
})