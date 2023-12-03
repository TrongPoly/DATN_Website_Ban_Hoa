app.controller('productCtrl', function($scope, ProductService,$http) {
	$scope.listProduct = [];
	$scope.product = {};
	$scope.ascending ="";
	$scope.idCategory ="0";
	$scope.categorys =[];
	$scope.getAllProduct = function() {
		ProductService.getAllProduct($scope.ascending,$scope.idCategory)
			.then((resp) => {
				$scope.listProduct = resp.data;
			})
			.catch((error) => {
				console.log(error.status);
			});
	}
	
	if(location.href==location.origin+"/product/category?id=0"){
		$scope.idCategory ="0";
	}
	if(location.href==location.origin+"/product/category?id=1"){
		$scope.idCategory ="1";
	}
	if(location.href==location.origin+"/product/category?id=2"){
		$scope.idCategory ="2";
	}
	if(location.href==location.origin+"/product/category?id=3"){
		$scope.idCategory ="3";
	}

	$scope.loadCategory = function(){
		 $http.get(location.origin+"/api/category")
		.then((resp) => {
			 $scope.categorys = resp.data;
		 });
	}
	
	$scope.productDetails = function(idProduct) {
		sessionStorage.setItem("idProduct",idProduct);	
		location.href = location.origin+"/product/details";
	}
	
	$scope.productFilter = function(){
		$scope.getAllProduct();
	}
	$scope.pager = {
		page: 0,
		size: 12,

		get listProduct() {
			var start = this.page * this.size;
			return $scope.listProduct.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.listProduct.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}

	};
	$scope.loadCategory();
	$scope.getAllProduct();
});