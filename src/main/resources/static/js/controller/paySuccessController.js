app.controller('paySuccessCtrl', ['$scope', 'CheckoutService', '$http', 'CartService',
	function($scope, CheckoutService, $http, CartService) {
		$scope.saveOrder = function() {
			var url = window.location.protocol + "//" + window.location.hostname + `:8080/api/order/save`;
			$scope.productList = CheckoutService.getSelectedProduct();
			$http
				.post(url, $scope.productList)
				.then((resp) => {

				})
				.catch((error) => {
				});
		}

		$scope.backIndex = function() {
			var productList1 = CartService.getCart();
			var productList = [];
			for (var i = 0; i < productList1.length; i++) {
				if (productList1[i].selected === true) {
					productList.push(productList1[i]);
				}
			}
			for (var i = 0; i < productList.length; i++) {
				CartService.removeFromCart(productList[i]);
			}
			location.href = window.location.protocol + "//" + window.location.hostname + ":8080/"
		}
		
	}]);