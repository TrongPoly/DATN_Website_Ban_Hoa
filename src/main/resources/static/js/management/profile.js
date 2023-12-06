let host = location.origin + "/rest";
const app = angular.module("AdminPfApp", []);
app.controller("AdminPfCtrl", function($scope, $http, ToastService) {
	$scope.formChangePassword = false;
	$scope.oldPassword = "";
	$scope.newPassword = "";
	$scope.toasts = [];
	$scope.confirmPassword = "";
	$scope.showFormChangePassword = function() {
		$scope.formChangePassword = !$scope.formChangePassword;
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

		let email = sessionStorage.getItem("email");
		let oldPassword = $scope.oldPassword;
		let newPassword = $scope.newPassword;
		if (oldPassword == "" || newPassword == "") {
			ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin!", $scope.toasts)
			return;
		}

		var url = host + "/profile/change_password/" + email + "?oldPw=" + oldPassword + "&newPw=" + newPassword;
		$http.put(url).then(resp => {
			ToastService.createToast("success", "Đổi mật khẩu thành công", $scope.toasts)
			$scope.oldPassword = "";
			$scope.newPassword = "";
			$scope.confirmPassword = "";
			$scope.formChangePassword = false;
		}).catch((error) => {
			ToastService.createToast("error", "Mật khẩu cũ không chính xác", $scope.toasts)
		});
	}

	$scope.load_profile = function() {
		let email = sessionStorage.getItem("email");
		var url = `${host}/profile/getUser/${email}`;
		$http.get(url).then(resp => {
			$scope.account = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.edit = function(id) {
		$scope.editProfile = !id;
	}

	$scope.update = function() {
		if (!$scope.account.fullName || !$scope.account.phoneNumber) {
			ToastService.createToast("warning", "Vui lòng điền đầy đủ thông tin!", $scope.toasts)
			return;
		}

		var url = host + "/profile/update";
		$http
			.put(url, $scope.account).then(resp => {
				ToastService.createToast("success", "Cập nhật khẩu thành công", $scope.toasts)
			}).catch((error) => {
				ToastService.createToast("error", "Cập nhật thất bại", $scope.toasts)
			});


	}
	$scope.load_profile();
})