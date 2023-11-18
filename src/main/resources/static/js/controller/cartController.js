app.controller('CartCtrl', ["$scope", "CartService", "ToastService", "ProductService",
	function($scope, CartService, ToastService, ProductService) {
		$scope.carts = [];
		$scope.toasts = [];
		$scope.total = 0;
		$scope.user = {};

		if (location.href == location.origin + "/cart/refresh") {
			ToastService.createToast("info", "Số lượng sản phẩm vừa được cập nhật!", $scope.toasts)
		}

		$scope.get = function() {
			$scope.carts = CartService.getCart();
			//Bỏ select
			CartService.resetCart();
			//Reset số lượng
			for (let i = 0; i < $scope.carts.length; i++) {
				ProductService.getOneProduct($scope.carts[i].id)
					.then((resp) => {
						CartService.resetQuantity($scope.carts[i], resp.data.quantity);
						if (resp.data.quantity == 0) {
							$scope.remove($scope.carts[i]);
						}
					})
					.catch((error) => {
						console.log(error.status);
					});
			}
			$scope.totalCart();
		}

		$scope.checkQuantProduct = async function() {
			for (let i = 0; i < $scope.carts.length; i++) {
				try {
					const resp = await ProductService.getOneProduct($scope.carts[i].id);
					if ($scope.carts[i].selected === true && $scope.carts[i].quant > resp.data.quantity) {
						location.href = location.origin + "/cart/refresh";
					}
				} catch (error) {
					console.log(error.status);
				}
			}
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
			$scope.checkQuantProduct();

			console.log(sessionStorage.getItem("checkQuant"));
			var check = false;
			for (var i = 0; i < $scope.carts.length; i++) {
				if ($scope.carts[i].selected === true) {
					check = true;
					break;
				}
			}
			if (check === false) {
				ToastService.createToast("error", "Chưa chọn sản phẩm nào", $scope.toasts)
			} else {
				location.href = "http://localhost:8080/checkout/index";
			}

		}

		$scope.select = function(item) {
			CartService.selectProduct(item);
			$scope.totalCart();
		}
		$scope.get();
	}]);
