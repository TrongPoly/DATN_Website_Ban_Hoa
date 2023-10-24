app.controller('orderCtrl', function($scope, OrderService) {
	$scope.listOrder = [];
	$scope.listOrderDetails = [];
	$scope.getOrder = function(status) {
		if (status === undefined) {
			status = 0;
		}
		OrderService.getOrder(status)
			.then((resp) => {
				$scope.listOrder = resp.data;
			})
			.catch((error) => {
				console.log(error.status);
			});
	}
	$scope.total = function() {
		let sum = 0;
		for (let i = 0; i < $scope.listOrderDetails.length; i++) {
			sum += $scope.listOrderDetails[i].product.price * $scope.listOrderDetails[i].quantity;
		}
		return sum;
	}
	$scope.getOrderDetails = function(idOrder) {
		OrderService.getOrderDetails(idOrder)
			.then((resp) => {
				$scope.listOrderDetails = resp.data;
				console.log($scope.listOrder);
			})
			.catch((error) => {
				console.log(error.status);
			});
	}
	$scope.requestCancelOrder = function(idOrder) {
		OrderService.requestCancelOrder(idOrder)
			.then((resp) => {
				alert("Yêu cầu hủy thành công");
				location.reload();
			})
			.catch((error) => {
				console.log(error.status);
			});
	}
	$scope.getOrder();
});