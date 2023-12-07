let host = location.origin+"/rest";
const app = angular.module("AdminCatApp", []);
app.controller("AdminCatCtrl", function($scope, $http, ToastService) {
	$scope.form = {};
	$scope.cats = [];
	$scope.editMode = false;
	$scope.toasts = [];

	$scope.reset = function() {
		$scope.form = { trangThai: true };
		$scope.editMode = false;
		$scope.key = null;
	}

	$scope.load_all = function() {
		var url = `${host}/category`;
		$http.get(url).then(resp => {
			$scope.cats = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.edit = function(id) {
		var url = `${host}/category/${id}`;
		$scope.editMode = true;
		$http.get(url).then(resp => {
			$scope.form = resp.data;

			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error", error);
		})
	}

	$scope.create = function() {

		//lỗi email đã tồn tại
		let existingName = $scope.cats.find(cat => cat.name === $scope.form.name);
		if (existingName) {
			ToastService.createToast("warning", "tên loại hoa đã tồn tại!!", $scope.toasts);
			/*$scope.errorMessage = "Tên sản phẩm đã tồn tại!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		if (!$scope.form.name) {
			ToastService.createToast("warning", "Không để trống Tên", $scope.toasts);
			return;
		}

		var item = angular.copy($scope.form);
		var url = `${host}/category`;
		$http.post(url, item).then(resp => {

			$scope.cats.push(item);

			$scope.reset();
			$scope.errorMessage = ''; // Xóa thông báo lỗi khi thành công
			ToastService.createToast("success", "Thêm mới thành công!", $scope.toasts);
			console.log("Success", resp);
			$scope.load_all();
			$scope.reset();
		}).catch((error) => {
			ToastService.createToast("error", "thêm thất bại!", $scope.toasts);
			console.log("Error", error);
		});
	}

	$scope.update = function() {

		if (!$scope.form.name) {
			ToastService.createToast("warning", "Không để trống Tên", $scope.toasts);
			return;
		}

		var cat = angular.copy($scope.form);
		var url = `${host}/category/${$scope.form.id}`;
		$http
			.put(url, cat).then(resp => {
				var index = $scope.cats.findIndex(cat =>
					cat.email == $scope.form.email);
				ToastService.createToast("success", "cập nhật thành công!", $scope.toasts);
				$scope.cats[index] = resp.data;
				$scope.reset();
				$scope.load_all();
				$scope.reset();
				$scope.editMode = false;
				console.log("Succes", resp);
			}).catch((error) => {
				ToastService.createToast("error", "cập nhật thất bại!", $scope.toasts);
				console.log("Error", error)
			});

	}

	$scope.delete = function(id) {
		var url = `${host}/category/${id}`;
		if (confirm("Bạn có chắc muốn xóa sản phẩm này?")) {
			$http.delete(url).then(resp => {
				var index = $scope.cats.findIndex(item => item.id == id);

				$scope.cats.splice(index, 1);
				ToastService.createToast("success", "xóa thành công", $scope.toasts);
				$scope.reset();
				console.log("Succes", resp);
			}).catch((error) => {
				ToastService.createToast("error", "xóa không thành công", $scope.toasts);
				console.log("Error", error)
			});
		}
	}
	// Tìm kiếm sản phẩm 
	$scope.searchCategoryByName = function() {
		if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
			$http.get("/rest/category/search", {
				params: { keyword: $scope.searchKeyword }
			}).then(resp => {
				$scope.cats = resp.data;
			}).catch(error => {
				ToastService.createToast("error", "lỗi tìm kiếm sản phẩm", $scope.toasts);
				console.log("Error", error);
			});
		} else {
			// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm

			$scope.load_all();
		}
	};
$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/img', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name
		}).catch(error => {
			ToastService.createToast("error", "lỗi upload hình ảnh", $scope.toasts);
			console.log("Error", error);
		})
	}

	$scope.pager = {
		page: 0,
		size: 5,

		get cats() {
			var start = this.page * this.size;
			return $scope.cats.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.cats.length / this.size);
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

	};
	$scope.load_all();
	$scope.reset();
})