app.controller('paySuccessCtrl', ['$scope', 'CheckoutService', '$http', 'CartService',
	function($scope, CheckoutService, $http, CartService) {
		let check = true;
		$scope.titleMsg = "Chuyển khoản thành công";
		$scope.msg = "Cảm ơn bạn đã thực hiện chuyển khoản thành công.";

		if (location.href == location.origin + "/order/success") {
			$scope.titleMsg = "Đặt hàng thành công";
			$scope.msg = "Cảm ơn bạn đã thực hiện đặt hàng.";
		}
		$scope.saveOrder = function() {
			let methodPayment = sessionStorage.getItem("methodPayment");
			let pickUpdate = sessionStorage.getItem("pickUpDate");
			let email = sessionStorage.getItem("email");
			let billing_fullName = sessionStorage.getItem("billing_fullName");
			let billing_phoneNumber = sessionStorage.getItem("billing_phoneNumber");
			let shipping_fullName = sessionStorage.getItem("shipping_fullName");
			let shipping_phoneNumber = sessionStorage.getItem("shipping_phoneNumber");
			let note = sessionStorage.getItem("note");

			var url = location.origin +
				`/api/order/save/` + email + "?methodPayment=" +
				methodPayment + "&pickUpDate=" + pickUpdate + "&billingFullName=" + billing_fullName 
				+"&billingPhoneNumber=" + billing_phoneNumber +" &shippingFullName="+shipping_fullName+"&shippingPhoneNumber="+shipping_phoneNumber
				 +"&note=" + note;

			$scope.productList = CheckoutService.getSelectedProduct();
			$http
				.post(url, $scope.productList)
		}
		$scope.updateProduct = function(products) {
			var url = location.origin +
				`/api/product/update/after_order`;
			$http.put(url, products)
				.then((resp) => {
				}).catch((error) => {
					console.log(error.status);
				})
		}

		$scope.backIndex = function() {
			location.href = location.origin;
		}

		//Xóa sản phẩm đã mua khỏi giỏ hàng, update số lượng sản phẩm còn lại sau khi mua
		$scope.updateCartAndProduct = function() {
			var productList1 = CartService.getCart();
			var productList = [];
			for (var i = 0; i < productList1.length; i++) {
				if (productList1[i].selected === true) {
					productList.push(productList1[i]);
				}
			}
			$scope.updateProduct(productList);

			for (var i = 0; i < productList.length; i++) {
				CartService.removeFromCart(productList[i]);
			}
		}

		//if dùng để đảm bảo đoạn mã trong if chỉ thực hiện 1 lần
		if (check == true) {
			$scope.saveOrder();
			$scope.updateCartAndProduct();
			check = false;
		}
	}]);