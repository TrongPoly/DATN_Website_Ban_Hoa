app.controller('productDetailsCtrl', ['$scope', 'ProductService', 'CartService', 'ToastService',
	function($scope, ProductService, CartService, ToastService) {
		$scope.listProduct = [];
		$scope.product = {};
		$scope.toasts = [];
		// Thêm sản phẩm vào giỏ hàng
		$scope.addToCart = function(item) {
			let text ="Thêm sản phẩm thành công";
			let type = "success";
			if(CartService.isItemInCart(item)==true){
				type = "info";
				text="Đã tăng thêm số lượng sản phẩm";
			}
			item.quantity = parseInt(document.getElementById("product-quanity").value);
			CartService.addToCart(item)
			ToastService.createToast(type, text,$scope.toasts);
		};

		$scope.productDetails = function(idProduct) {
			ProductService.getOneProduct(idProduct)
				.then((resp) => {
					$scope.product = resp.data;
					console.log(resp.data);
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		$scope.userId = 1;


		$scope.productDetails(sessionStorage.getItem("idProduct"));
	}]);
