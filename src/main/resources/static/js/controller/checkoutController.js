app.controller('CheckoutCtrl', ["$scope", "ToastService", "CheckoutService", "$http", "CustomerService",
	function($scope, ToastService, CheckoutService, $http, CustomerService) {
		$scope.orders = [];
		$scope.toasts = [];
		$scope.totalOrder = 0;
		$scope.customer = {};
		$scope.getCustomer = function() {
			CustomerService.getCustomer()
				.then((resp) => {
					$scope.customer = resp.data;
				})
				.catch((error) => {
					console.log(error.status);
				});
		}

		$scope.get = function() {
			$scope.orders = CheckoutService.getSelectedProduct();
			$scope.getOrderTotal();
			if ($scope.totalOrder == 0) {
				ToastService.createToast("error", "Không có sản phẩm nào", $scope.toasts);
			}
			$scope.getCustomer();
		}
		$scope.getOrderTotal = function() {
			for (let i = 0; i < $scope.orders.length; i++) {
				let total = $scope.orders[i].price * $scope.orders[i].quant;
				$scope.totalOrder += total;
			}
			return $scope.totalOrder;
		}


		$scope.checkout = function(amount) {
			let datePU = document.getElementById("datePickUp").value;
			sessionStorage.removeItem("pickUpDate")
			sessionStorage.setItem("pickUpDate", datePU);
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