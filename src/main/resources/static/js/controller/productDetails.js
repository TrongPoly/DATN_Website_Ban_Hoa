app.controller('productDetailsCtrl', ['$scope', 'ProductService', 'CartService', 'ToastService',
	function($scope, ProductService, CartService, ToastService) {
		$scope.listProduct = [];
		$scope.product = {};
		$scope.toasts = [];

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
			console.log(CartService.checkLogin());
			if (CartService.checkLogin() == true) {
				if (CartService.isItemInCart(item) == true) {
					type = "info";
					text = "Sản phẩm đã có trong giỏ hàng";
				} else {
					item.quant = 1;
					item.selected = true;
					CartService.addToCart(item)
				}
				ToastService.createToast(type, text, $scope.toasts);
			} else {
				type = "warning";
				text = "Vui lòng đăng nhập";
				ToastService.createToast(type, text, $scope.toasts);
			}
		};

		$scope.productDetails = function(idProduct) {
			ProductService.getOneProduct(idProduct)
				.then((resp) => {
					$scope.product = resp.data;
					$scope.moreProduct(sessionStorage.getItem("idProduct"));
					console.log(resp.data);
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		$scope.moreProduct = function(idProduct) {
			ProductService.getByCategory(idProduct)
				.then((resp) => {
					$scope.listProduct = resp.data;
					console.log(resp.data);
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		$scope.productDetails(sessionStorage.getItem("idProduct"));
	}]);
