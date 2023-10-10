app.controller('CartCtrl', ["$scope", "CartService", "ToastService", function($scope, CartService, ToastService) {
	$scope.carts = [];
	$scope.toasts = [];
	$scope.get = function() {
		$scope.carts = CartService.getCart();
	}
	$scope.remove = function(item) {
		CartService.removeFromCart(item);
	}

	$scope.increaseProduct = function(item) {
		CartService.increase(item);
	}
	$scope.reduceProduct = function(item) {
		if (item.quantity > 1) {
			CartService.reduce(item);
		} else {
			ToastService.createToast("error", "Số lượng phải lớn hơn 0!",$scope.toasts)
		}
	}
	$scope.get();
}]);
