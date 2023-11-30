app.controller('CheckoutCtrl', ["$scope", "ToastService", "CheckoutService", "$http", 
	function($scope, ToastService, CheckoutService, $http) {
		$scope.orders = [];
		$scope.toasts = [];
		$scope.totalOrder = 0;
		$scope.methodPayment = "Chuyển khoản";
		if (location.href == location.origin+"/checkout/invalid") {
			ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin", $scope.toasts);
		}

		

		$scope.get = function() {
			$scope.orders = CheckoutService.getSelectedProduct();
			$scope.getOrderTotal();
			if ($scope.totalOrder == 0) {
				ToastService.createToast("error", "Không có sản phẩm nào", $scope.toasts);
			}
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
			let fullName = document.getElementById("fullName").value.trim();
			let phoneNumber = document.getElementById("phoneNumber").value.trim();
			let note = document.getElementById("orderNote").value.trim();

			if (datePU == "" || fullName == "" || phoneNumber == "") {
				location.href = location.origin + "/checkout/invalid";
			} else {
				let pickupDate = new Date(datePU);

				// Lấy thời gian hiện tại
				let currentDate = new Date();

				// So sánh ngày lấy hoa với thời gian hiện tại
				if (pickupDate < currentDate) {
					ToastService.createToast("error", "Ngày lấy hoa không hợp lệ", $scope.toasts);
				} else {

					sessionStorage.setItem("methodPayment", 0);
					sessionStorage.removeItem("pickUpDate")
					sessionStorage.setItem("pickUpDate", datePU);
					sessionStorage.setItem("fullName", fullName);
					sessionStorage.setItem("phoneNumber", phoneNumber);
					sessionStorage.setItem("phoneNumber", phoneNumber);
					sessionStorage.setItem("note",note);
					if ($scope.methodPayment == "Chuyển khoản") {

						var url = location.origin + `/api/payment/create_payment?amount=${amount}`;
						$http
							.get(url)
							.then((resp) => {
								location.href = resp.data.url;
							})
							.catch((error) => {
								console.log(error.status)
							});
					} else {
						sessionStorage.setItem("methodPayment", 1);
						location.href = location.origin + "/order/success";
					}
				}
			}
		}

	
	
		$scope.get();
	}]);