app.controller('productCtrl', function($scope, ProductService) {
	$scope.listProduct = [];
	$scope.product = {};
	$scope.getAllProduct = function() {

		ProductService.getAllProduct()
			.then((resp) => {
				$scope.listProduct = resp.data;
			})
			.catch((error) => {
				console.log(error.status);
			});
	}

	$scope.productDetails = function(idProduct) {
		sessionStorage.setItem("idProduct",idProduct);	
		location.href = "http://localhost:8080/product/details";
	}
	$scope.getAllProduct();
});