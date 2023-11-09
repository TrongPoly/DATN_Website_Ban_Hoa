app.controller('CheckoutCtrl', ["$scope", "ToastService", "CheckoutService", "$http", "CustomerService",
	function($scope, ToastService, CheckoutService, $http, CustomerService) {
		$scope.orders = [];
		$scope.toasts = [];
		$scope.totalOrder = 0;
		$scope.customer = {};
		$scope.methodPayment = "Chuyển khoản";
		$scope.isDisabled = true;
		if (location.href == "http://localhost:8080/checkout/invalid") {
			ToastService.createToast("warning", "Vui lòng chọn  ngày lấy hoa", $scope.toasts);
		}

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
			console.log(datePU);
			if (datePU == "") {
				location.href = location.origin+ "checkout/invalid";
			} else {
				sessionStorage.removeItem("pickUpDate")
				sessionStorage.setItem("pickUpDate", datePU);
				if ($scope.methodPayment == "Chuyển khoản") {

					var url = location.origin + `/api/payment/create_payment?amount=${amount}`;
					$http
						.get(url)
						.then((resp) => {
							location.href = resp.data.url;
						})
						.catch((error) => {
							alert(error.status)
							alert("Lỗi");
						});
				} else {
					location.href = location.origin + "/order/success";
				}
			}
		}

		$scope.toggleDisabled = function() {
			$scope.isDisabled = !$scope.isDisabled;
			let fullName = document.getElementById("fullName").value;
			let phoneNumber = document.getElementById("phoneNumber").value;
			$scope.customer.fullName = fullName;
			$scope.customer.phoneNumber = phoneNumber;
			CustomerService.updateCustomer($scope.customer);
			console.log($scope.isDisabled);
			console.log($scope.customer);
		}
		console.log($scope.isDisabled);
		$scope.get();
	}]);