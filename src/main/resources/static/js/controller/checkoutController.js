app.controller('CheckoutCtrl', ["$scope", "ToastService", "CheckoutService", "$http", "ProductService", "CartService","$interval",
	function($scope, ToastService, CheckoutService, $http, ProductService, CartServive,$interval) {
		$scope.orders = [];
		$scope.toasts = [];
		$scope.totalOrder = 0;
		$scope.methodPayment = "Chuyển khoản";
		if (location.href == location.origin + "/checkout/invalid") {
			ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin", $scope.toasts);
		}


		$http
			.get(location.origin+"/rest/profile/"+sessionStorage.getItem("email"))
			.then((resp) => {
				$scope.account = resp.data;
			})
			.catch((error) => {
				console.log(error.status)
			});

		var checkQuantProduct = async function() {
			$scope.carts = CartServive.getCart();
			for (let i = 0; i < $scope.carts.length; i++) {
				try {
					const resp = await ProductService.getOneProduct($scope.carts[i].id);
					console.log(resp.data.isAvailable);
					if ($scope.carts[i].selected === true && $scope.carts[i].quant > resp.data.quantity) {
						location.href = location.origin + "/cart/refresh";
					} else if ($scope.carts[i].selected === true && resp.data.isAvailable == false) {
						//Sản phẩm tạm ngừng kinh doanh
						location.href = location.origin + "/cart/refresh";
					}
				} catch (error) {
					console.log(error.status);
				}
			}
			return true;
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

		$scope.preventSpace = function(event) {
			if (event.keyCode === 32) { // 32 là mã ký tự cho phím space
				event.preventDefault(); // Ngăn chặn sự kiện mặc định của phím space
			}
		}
		$scope.checkout = function(amount) {
			let datePU = document.getElementById("datePickUp").value;
			let billing_fullName = document.getElementById("billing_fullName").value.trim();
			let billing_phoneNumber = document.getElementById("billing_phoneNumber").value.trim();
			let shipping_fullName = document.getElementById("shipping_fullName").value.trim();
			let shipping_phoneNumber = document.getElementById("shipping_phoneNumber").value.trim();
			let note = document.getElementById("orderNote").value.trim();

			if (datePU == "" || billing_fullName == "" || billing_phoneNumber == "" || shipping_fullName == "" || shipping_phoneNumber == "") {
				ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin", $scope.toasts);
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
					sessionStorage.setItem("billing_fullName", billing_fullName);
					sessionStorage.setItem("billing_phoneNumber", billing_phoneNumber);
					sessionStorage.setItem("shipping_fullName", shipping_fullName);
					sessionStorage.setItem("shipping_phoneNumber", shipping_phoneNumber);
					sessionStorage.setItem("note", note);

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

$interval(checkQuantProduct, 1000);

		$scope.get();
	}]);