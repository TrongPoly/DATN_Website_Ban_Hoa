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
			var url = location.origin +
				`/api/order/save/` + sessionStorage.getItem("email") + "?methodPayment=" +
				sessionStorage.getItem("methodPayment") + "&pickUpDate=" +
				sessionStorage.getItem("pickUpDate");
			$scope.productList = CheckoutService.getSelectedProduct();
			$http
				.post(url, $scope.productList)
				.then((resp) => {
				})
				.catch((error) => {
				});
		}
		$scope.updateProduct = function(products) {
			var url = location.origin +
				`/api/product/update/after_order`;
			$http.put(url, products)
				.then((resp) => {
				}).catch((error) => {
					alert(error.status);
				})
		}

		$scope.backIndex = function() {
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
			location.href = location.origin;
		}
		if (check == true) {
			$scope.saveOrder();
			check = false;
		}
	}]);