app.controller('loginCtrl',
	function($scope, ToastService) {
		$scope.toasts = [];
		
		if (location.href === "http://localhost:8080/auth/login?error") {
			ToastService.createToast("error", "Sai tài khoản hoặc mật khẩu", $scope.toasts);
		}
		if (location.href === "http://localhost:8080/auth/blocked") {
			ToastService.createToast("error", "Tài khoản đã bị khóa", $scope.toasts);
		}
		/*$scope.productDetails = function(idProduct) {
			ProductService.getOneProduct(idProduct)
				.then((resp) => {
					$scope.product = resp.data;
					$scope.moreProduct(sessionStorage.getItem("idProduct"));
					console.log(resp.data);
				})
				.catch((error) => {
					console.log(error.status);
				});
		}*/

	});
