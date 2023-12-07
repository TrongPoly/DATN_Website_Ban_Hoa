app.controller('registerCtrl', ['$scope', 'ToastService', '$http',
	function($scope, ToastService, $http) {
		$scope.account = {};
		$scope.toasts = [];
		$scope.isLoading = false;
		var origin = location.origin;

		$scope.checkPassword = function(){
			let password = $scope.account.password;
			let confirm = $scope.confirmPassword;
			console.log(password)
			console.log(confirm)
			if(password!=confirm){
				return false;
			}
			return true;
		}
		$scope.preventSpace = function(event) {
        if (event.keyCode === 32) { // 32 là mã ký tự cho phím space
          event.preventDefault(); // Ngăn chặn sự kiện mặc định của phím space
        }
      };

		$scope.register = function() {
			// Kiểm tra các trường dữ liệu có rỗng không
			if (!$scope.account.email || !$scope.account.password || !$scope.account.phoneNumber) {
				// Nếu có trường dữ liệu rỗng, hiển thị thông báo lỗi
				ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin!", $scope.toasts)
				return;
			}
			$scope.isLoading = true;
			var url = origin + "/api";
			
			// Nếu không có trường dữ liệu rỗng, tiến hành xử lý đăng ký
			$http
				.post(url + "/account/register",$scope.account)
				.then((resp) => {
					location.href = location.origin+"/auth/verify_email";
				})
				.catch((error) => {
					ToastService.createToast("warning", "Email đã được sử dụng!", $scope.toasts)
					$scope.isLoading = false;
				});
		}
	}]);