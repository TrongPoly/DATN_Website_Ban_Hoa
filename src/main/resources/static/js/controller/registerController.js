app.controller('registerCtrl', ['$scope', 'ToastService', '$http',
	function($scope, ToastService, $http) {
		$scope.account = {};
		$scope.customer = {};
		$scope.toasts = [];
		$scope.isLoading = false;
		var origin = location.origin;

		$scope.register = function() {
			// Kiểm tra các trường dữ liệu có rỗng không
			if (!$scope.account.email || !$scope.account.password || !$scope.customer.fullName ||
				!$scope.customer.phoneNumber || !$scope.customer.gender) {
				// Nếu có trường dữ liệu rỗng, hiển thị thông báo lỗi
				ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin!", $scope.toasts)
				return;
			}
			$scope.isLoading = true;
			var url = origin + "/api";
			let email = $scope.account.email;
			let password = $scope.account.password;
			// Nếu không có trường dữ liệu rỗng, tiến hành xử lý đăng ký
			$http
				.post(url + "/account/register?email=" + email + "&password=" + password, $scope.customer)
				.then((resp) => {
					location.href = "http://localhost:8080/auth/verify_email";
				})
				.catch((error) => {
					ToastService.createToast("warning", "Email đã được sử dụng!", $scope.toasts)
					$scope.isLoading = false;
				});
		}
	}]);