app.controller('CartCtrl', ["$scope", "CartService", "ToastService", function($scope, CartService, ToastService) {
	$scope.carts = [];
	$scope.toasts = [];
	$scope.total = 0;
	$scope.get = function() {
		$scope.carts = CartService.getCart();
		$scope.totalCart();
		console.log($scope.carts);
	}

	$scope.totalCart = function() {
		$scope.total = CartService.total();
	}
	$scope.remove = function(item) {
		CartService.removeFromCart(item);
		$scope.totalCart();
	}
	$scope.clearCart = function() {
		CartService.clearCart();
		$scope.get();
	}
	$scope.increaseProduct = function(item) {
		if (item.quantity > item.quant) {
			CartService.increase(item);
			$scope.totalCart();
		} else {
			ToastService.createToast("error", "Số lượng sản phẩm còn lại không đủ!", $scope.toasts)
		}
	}
	$scope.reduceProduct = function(item) {
		if (item.quant > 1) {
			CartService.reduce(item);
			$scope.totalCart();
		} else {
			ToastService.createToast("error", "Số lượng phải lớn hơn 0!", $scope.toasts)
		}
	}
	$scope.checkCartSelect = function() {
		var check = false;
		for (var i = 0; i < $scope.carts.length; i++) {
			if ($scope.carts[i].selected === true) {
				check = true;
				break;
			}
		}
		if (check === false) {
			ToastService.createToast("error", "Chưa chọn sản phẩm nào", $scope.toasts)
		}else{
			location.href="http://localhost:8080/checkout";
		}
	}

	$scope.select = function(item) {
		CartService.selectProduct(item);
		$scope.totalCart();
	}
	$scope.get();
}]);
