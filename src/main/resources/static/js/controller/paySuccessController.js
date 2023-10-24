app.controller('paySuccessCtrl', ['$scope', 'CheckoutService', '$http', 'CartService',
	function($scope, CheckoutService, $http, CartService) {
		let check = true;
		$scope.saveOrder = function() {
			var url = window.location.protocol + "//" + window.location.hostname +
				`:8080/api/order/save/` + sessionStorage.getItem("email") + "?pickUpDate=" +
				sessionStorage.getItem("pickUpDate");
			$scope.productList = CheckoutService.getSelectedProduct();
			$http
				.post(url, $scope.productList)
				.then((resp) => {
				})
				.catch((error) => {
				});
		}
		$scope.updateProduct = function(products){
			var url = window.location.protocol + "//" + window.location.hostname +
				`:8080/api/product/update/after_order`;
			$http.put(url,products)
			.then((resp)=>{
			}).catch((error)=>{
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
			location.href = window.location.protocol + "//" + window.location.hostname + ":8080/"
		}
		if (check == true) {
			$scope.saveOrder();
			check = false;
		}
	}]);