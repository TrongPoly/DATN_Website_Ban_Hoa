app.controller('productDetailsCtrl', ['$scope', 'ProductService', 'CartService', 'ToastService',
	function($scope, ProductService, CartService, ToastService) {
		$scope.listProduct = [];
		$scope.product = {};
		$scope.toasts = [];
		$scope.quant = 1;

		if (location.href == location.origin + "/product/refresh") {
			ToastService.createToast("info", "Số lượng sản phẩm không đủ", $scope.toasts);
		}

		$scope.removeToast = function(toast) {
			var index = $scope.toasts.indexOf(toast);
			if (index !== -1) {
				$scope.toasts.splice(index, 1);
			}
		};


		// Thêm sản phẩm vào giỏ hàng
		$scope.addToCart = function(item) {
			let text = "Thêm sản phẩm thành công";
			let type = "success";
			if (CartService.checkLogin() == true) {
				if (CartService.isItemInCart(item) == true) {
					type = "info";
					text = "Sản phẩm đã có trong giỏ hàng";
				} else {
					item.quant = 1;
					item.selected = false;
					CartService.addToCart(item)
				}
				ToastService.createToast(type, text, $scope.toasts);
			} else {
				location.href = location.origin + "/auth/login";
			}
		};
		// Mua ngay
		$scope.buyNow = async function(item) {
			if (CartService.checkLogin() == true) {
				item.quant = $scope.quant;
				item.selected = true;
				
				const resp = await ProductService.getOneProduct(item.id);
				if (item.quant > resp.data.quantity) {
					location.href = location.origin + "/product/refresh";
				} else {
					CartService.addToCart(item)
					location.href = location.origin + "/checkout/index"
				}
			} else {
				location.href = location.origin + "/auth/login";
			}
		};



		$scope.productDetails = function(idProduct) {
			ProductService.getOneProduct(idProduct)
				.then((resp) => {
					$scope.product = resp.data;
					$scope.moreProduct(sessionStorage.getItem("idProduct"));
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		$scope.moreProduct = function(idProduct) {
			ProductService.getByCategory(idProduct)
				.then((resp) => {
					$scope.listProduct = resp.data;
				})
				.catch((error) => {
					console.log(error.status);
				});
		}


		CartService.resetCart();
		$scope.productDetails(sessionStorage.getItem("idProduct"));
	}]);
