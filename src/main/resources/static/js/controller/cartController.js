app.controller('CartCtrl', ["$scope","CartService", function($scope,CartService) {
	$scope.carts =[];
	$scope.get = function(){
		$scope.carts=CartService.getCart();
	}
	$scope.get();
}]);
