app.service('OrderService', function($http) {
	var url = location.origin + `/api/order`;
	this.getOrder = function(status) {
		return $http.get(url+"/"+sessionStorage.getItem("email")+"?status="+status);
	}
	this.getOrderDetails = function(idOrder){
		return $http.get(url+"/details/"+idOrder);
	}
	this.updateOrder = function(idOrder,statusId, note){
		if(note==undefined){
		return $http.put(url+"/update_status/"+idOrder+"?statusId="+statusId+"&note=");
		}
		return $http.put(url+"/update_status/"+idOrder+"?statusId="+statusId+"&note="+note);
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
})