app.controller('loginCtrl',
	function($scope, ToastService, $http) {
		$scope.toasts = [];
		$scope.newPassword = "";
		$scope.confirmPassword = "";
		$scope.isLoading = false;
		var origin = location.origin;
		if (location.href === origin + "/auth/login?error") {
			ToastService.createToast("error", "Sai tài khoản hoặc mật khẩu", $scope.toasts);
		}
		if (location.href === origin + "/auth/locked") {
			ToastService.createToast("error", "Tài khoản đã bị khóa", $scope.toasts);
		}
		if (location.href === origin + "/auth/blocked") {
			ToastService.createToast("info", "Tài khoản chưa được xác minh", $scope.toasts);
		}
		if (location.href === origin + "/auth/success_verify") {
			ToastService.createToast("success", "Xác minh tài khoản thành công", $scope.toasts);
		}
		if (location.href === origin + "/auth/determined") {
			ToastService.createToast("info", "Tài khoản đã được xác minh", $scope.toasts);
		}
		if (location.href === origin + "/auth/success_change_pw") {
			ToastService.createToast("success", "Đổi mật khẩu thành công", $scope.toasts);
		}

		$scope.fogotPassword = function() {
			let email = document.getElementById("emailFogotPw").value;
			if (email == "") {
				ToastService.createToast("warning", "Vui lòng nhập email", $scope.toasts);
				return;
			}
			$scope.isLoading = true;
			var url = origin + `/api/auth/fogotPassword/${email}`
			$http.get(url)
				.then((resp) => {
					location.href = origin + "/auth/confirm_otp";
				})
				.catch((error) => {
					ToastService.createToast("error", "Email không tồn tại", $scope.toasts);
					$scope.isLoading = false;
				});
		}
		$scope.confirmOtp = function() {
			let otp = $scope.otp;
			var url = origin + `/api/auth/confirm_otp?otp=${otp}`
			$http.get(url)
				.then((resp) => {
					location.href = origin + "/auth/changePassword";
				})
				.catch((error) => {
					ToastService.createToast("error", "Mã OTP không chính xác", $scope.toasts);
				});
		}
		$scope.preventSpace = function(event) {
		if (event.keyCode === 32) { // 32 là mã ký tự cho phím space
			event.preventDefault(); // Ngăn chặn sự kiện mặc định của phím space
		}
	}

	$scope.checkPassword = function() {
		let password = $scope.newPassword;
		let confirm = $scope.confirmPassword;
		if (password != confirm) {
			return false;
		}
		return true;
	}

		$scope.changePassword = function() {
			if ($scope.newPassword == "" || $scope.confirmPassword == "") {
				ToastService.createToast("warning", "Vui lòng điền đầy đủ các ô nhập", $scope.toasts);
			} else if ($scope.newPassword == $scope.confirmPassword) {
				let newPw = $scope.newPassword;
				var url = origin + `/api/auth/changePassword?newPw=${newPw}`
				$http.get(url)
					.then((resp) => {
						location.href = origin + "/auth/success_change_pw";
					})
					.catch((error) => {
						if (error.status === 400) {
							ToastService.createToast("error", "Có lỗi xảy ra khi thực hiện yêu cầu", $scope.toasts);
						} else if (error.status === 404) {
							ToastService.createToast("error", "Yêu cầu đã hết hạn, vui lòng tạo yêu cầu mới", $scope.toasts);
						}

					});
			}
			else {
				ToastService.createToast("warning", "Mật khẩu xác nhận không khớp", $scope.toasts);
			}
		}
		$scope.verifyAccount = function() {
			let email = document.getElementById("emailVerify").value;
			var url = origin + `/api/auth/verify?email=${email}`
			if (email == "") {
				ToastService.createToast("warning", "Vui lòng nhập email", $scope.toasts);
				return;
			}
			$scope.isLoading = true;
			$http.get(url)
				.then((resp) => {
					ToastService.createToast("success", "Đã gửi yêu cầu xác minh", $scope.toasts);
				})
				.catch((error) => {
					ToastService.createToast("error", "Email không tồn tại", $scope.toasts);
					$scope.isLoading = false;
				});
		}
	});
