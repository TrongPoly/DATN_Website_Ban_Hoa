app.controller('loginCtrl',
	function($scope, ToastService) {
		$scope.toasts = [];
		
		if (location.href === "http://localhost:8080/auth/login?error") {
			ToastService.createToast("error", "Sai tài khoản hoặc mật khẩu", $scope.toasts);
		}
		if (location.href === "http://localhost:8080/auth/blocked") {
			ToastService.createToast("error", "Tài khoản hiện chưa được xác minh, Vui lòng xác minh tài khoản trong Email của bạn", $scope.toasts);
		}
		if (location.href === "http://localhost:8080/auth/success_verify") {
			ToastService.createToast("success", "Xác minh tài khoản thành công", $scope.toasts);
		}
		if (location.href === "http://localhost:8080/auth/determined") {
			ToastService.createToast("info", "Tài khoản đã được xác minh trước đó", $scope.toasts);
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
