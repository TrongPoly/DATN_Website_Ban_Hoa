app.controller('CheckoutCtrl', ["$scope", "ToastService", "CheckoutService", "$http",
	function($scope, ToastService, CheckoutService, $http) {
		$scope.orders = [];
		$scope.toasts = [];
		$scope.totalOrder = 0;

		$scope.get = function() {
			$scope.orders = CheckoutService.getSelectedProduct();
			$scope.getOrderTotal();
		}
		$scope.getOrderTotal = function() {
			for (let i = 0; i < $scope.orders.length; i++) {
				let total = $scope.orders[i].price * $scope.orders[i].quant;
				$scope.totalOrder += total;
			}
			return $scope.totalOrder;
		}
		$scope.checkout = function(amount) {
			var url = window.location.protocol + "//" + window.location.hostname + `:8080/api/payment/create_payment?amount=${amount}`;
			$http
				.get(url)
				.then((resp) => {
					location.href = resp.data.url;
				})
				.catch((error) => {
					alert(error.status)
					alert("Lỗi");
				});
		}
		$scope.get();
	}]);