let host = location.origin + "/rest";
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
	$scope.editMode = false;
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
				ToastService.createToast("info", "Đã chặn tài khoản", $scope.toasts);
				$scope.load_all();
				$scope.load_all_role();
				$scope.reset();
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
				ToastService.createToast("info", "Đã bỏ chặn tài khoản", $scope.toasts);
				$scope.load_all();
				$scope.load_all_role();
				$scope.reset();
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
		//lỗi email đã tồn tại
		let existingEmail = $scope.items.find(item => item.email === $scope.form.email);
		if (existingEmail) {
			ToastService.createToast("warning", "Email đã tồn tại", $scope.toasts);
			/*$scope.errorMessage = "Tên sản phẩm đã tồn tại!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//lỗi bỏ trống email
		if (!$scope.form.email) {
			ToastService.createToast("warning", "Không để trống Email", $scope.toasts);
			return;
		}
		//lỗi bỏ trống fullname
		if (!$scope.form.fullName) {
			ToastService.createToast("warning", "Không để trống họ và tên", $scope.toasts);
			return;
		}
		//lỗi bỏ trống password
		if (!$scope.form.password) {
			ToastService.createToast("warning", "Không để trống mật khẩu", $scope.toasts);
			return;
		}
		//lỗi bỏ trống role
		if (!$scope.form.role) {
			ToastService.createToast("warning", "Không để trống quyền", $scope.toasts);
			return;
		}

		$http.post(url, item).then(resp => {

			$scope.items.push(item);

			$scope.reset();
			$scope.errorMessage = ''; // Xóa thông báo lỗi khi thành công
			ToastService.createToast("success", "Thêm mới thành công!", $scope.toasts);
			$scope.load_all();
			$scope.load_all_role();
			$scope.reset();
		}).catch((error) => {
			ToastService.createToast("error", "thêm thất bại!", $scope.toasts);
		});
	}
	$scope.update = function() {

		//lỗi bỏ trống email
		if (!$scope.form.email) {
			ToastService.createToast("warning", "Không để trống Email", $scope.toasts);
			return;
		}
		//lỗi bỏ trống fullname
		if (!$scope.form.fullName) {
			ToastService.createToast("warning", "Không để trống họ và tên", $scope.toasts);
			return;
		}
		//lỗi bỏ trống password
		if (!$scope.form.password) {
			ToastService.createToast("warning", "Không để trống mật khẩu", $scope.toasts);
			return;
		}
		//lỗi bỏ trống role
		if (!$scope.form.role) {
			ToastService.createToast("warning", "Không để trống quyền", $scope.toasts);
			return;
		}

		var item = angular.copy($scope.form);
		var url = `${host}/account/${$scope.form.email}`;
		$http
			.put(url, item).then(resp => {
				var index = $scope.items.findIndex(item =>
					item.email == $scope.form.email);
				ToastService.createToast("success", "Cập nhật thành công!", $scope.toasts);
				$scope.editMode = false;
				$scope.items[index] = resp.data;
				$scope.load_all();
				$scope.load_all_role();
				$scope.reset();
				console.log("Succes", resp);
			}).catch((error) => {
				ToastService.createToast("error", "Cập nhật thất bại!", $scope.toasts);
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