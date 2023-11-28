let host = "http://localhost:8080/rest";
const app = angular.module("AdminAccApp", []);
app.controller("AdminAccCtrl", function($scope, $http, ToastService) {
	$scope.form = {};
	$scope.items = [];
	$scope.roles = [];
	$scope.accounts = [];
	$scope.toasts = [];
	$scope.editMode = false;


	$scope.reset = function() {
		$scope.form = { active: true };
		$scope.key = null;
	}


	$scope.load_all = function() {
		var url = `${host}/account`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.load_all_role = function() {
		var url = `${host}/role`;
		$http.get(url).then(resp => {
			$scope.roles = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}


	$scope.chan = function(accId) {
		var url = `${host}/account/chan/${accId}`;
		$http
			.put(url)
			.then((resp) => {
				ToastService.createToast("info", "Chặn thành công!", $scope.toasts);
				setTimeout(function() {
					location.reload();
				}, 500);
			})
			.catch((error) => {
				ToastService.createToast("error", "Lỗi", $scope.toasts);
			});
	}
	$scope.boChan = function(accId) {
		var url = `${host}/account/boChan/${accId}`;
		$http
			.put(url)
			.then((resp) => {
				ToastService.createToast("info", "Bỏ chặn thành công!", $scope.toasts);
				setTimeout(function() {
					location.reload();
				}, 500);
			})
			.catch((error) => {
				ToastService.createToast("error", "Lỗi", $scope.toasts);
			});
	}


	// Tìm kiếm sản phẩm 
	$scope.searchCustomerByName = function() {
		if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
			$http.get("/rest/account/search", {
				params: { keyword: $scope.searchKeyword }
			}).then(resp => {
				$scope.items = resp.data;
			}).catch(error => {
				ToastService.createToast("error", "lỗi tìm kiếm tên khách hàng", $scope.toasts);
				console.log("Error", error);
			});
		} else {
			// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm
			$scope.load_all();
		}
	};

	$scope.create = function() {
		var item = angular.copy($scope.form);
		var url = `${host}/account`;
		//lỗi bỏ trống email
		if (!$scope.form.email) {
			ToastService.createToast("warning", "Không để trống email", $scope.toasts);
			return;
		}
		//lỗi bỏ trống fullname
		if (!$scope.form.fullName) {
			ToastService.createToast("warning", "Không để trống fullname", $scope.toasts);
			return;
		}
		//lỗi bỏ trống password
		if (!$scope.form.password) {
			ToastService.createToast("warning", "Không để trống mật khẩu", $scope.toasts);
			return;
		}
		//lỗi bỏ trống role
		if (!$scope.form.role) {
			ToastService.createToast("warning", "Không để trống role", $scope.toasts);
			return;
		}

		$http.post(url, item).then(resp => {

			$scope.items.push(item);

			$scope.reset();
			$scope.errorMessage = ''; // Xóa thông báo lỗi khi thành công
			ToastService.createToast("success", "Thêm mới thành công!", $scope.toasts);
			setTimeout(function() {
					location.reload();
				}, 1000);
			console.log("Success", resp);
		}).catch((error) => {
			ToastService.createToast("error", "thêm thất bại!", $scope.toasts);
			console.log("Error", error);
		});
	}
	$scope.update = function() {

		//lỗi bỏ trống email
		if (!$scope.form.email) {
			ToastService.createToast("warning", "Không để trống email", $scope.toasts);
			return;
		}
		//lỗi bỏ trống fullname
		if (!$scope.form.fullName) {
			ToastService.createToast("warning", "Không để trống fullname", $scope.toasts);
			return;
		}
		//lỗi bỏ trống password
		if (!$scope.form.password) {
			ToastService.createToast("warning", "Không để trống mật khẩu", $scope.toasts);
			return;
		}
		//lỗi bỏ trống role
		if (!$scope.form.role) {
			ToastService.createToast("warning", "Không để trống role", $scope.toasts);
			return;
		}

		var item = angular.copy($scope.form);
		var url = `${host}/account/${$scope.form.email}`;
		$http
			.put(url, item).then(resp => {
				var index = $scope.items.findIndex(item =>
					item.email == $scope.form.email);
				ToastService.createToast("success", "cập nhật thành công!", $scope.toasts);
				$scope.items[index] = resp.data;
				$scope.reset();
				setTimeout(function() {
					location.reload();
				}, 1000);
				console.log("Succes", resp);
			}).catch((error) => {
				ToastService.createToast("error", "cập nhật thất bại!", $scope.toasts);
				console.log("Error", error)
			});


	}

	$scope.edit = function(email) {
		var url = `${host}/account/${email}`;
		$scope.editMode = true;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 5,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}

	}

	$scope.load_all();
	$scope.load_all_role();
	$scope.reset();
})