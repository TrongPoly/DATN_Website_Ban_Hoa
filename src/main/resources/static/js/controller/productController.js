app.controller('productCtrl', function($scope, ProductService) {
	$scope.listProduct = [];
	$scope.product = {};
	$scope.ascending ="";
	$scope.getAllProduct = function() {
		
		ProductService.getAllProduct($scope.ascending)
			.then((resp) => {
				$scope.listProduct = resp.data;
			})
			.catch((error) => {
				console.log(error.status);
			});
	}

	$scope.productDetails = function(idProduct) {
		sessionStorage.setItem("idProduct",idProduct);	
		location.href = location.origin+"/product/details";
	}
	
	$scope.productFilter = function(){
		$scope.getAllProduct();
	}
	$scope.getAllProduct();
});