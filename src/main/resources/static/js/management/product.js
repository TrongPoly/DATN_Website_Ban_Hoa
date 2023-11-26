let host = "http://localhost:8080/rest";
const app = angular.module("AdminSpApp", []);
app.controller("AdminSpCtrl", function($scope, $http, ToastService) {
	$scope.form = {};
	$scope.sps = [];
	$scope.cats = [];
	$scope.toasts = [];

	$scope.reset = function() {
		$scope.form = { trangThai: true };
		$scope.key = null;
	}

	$scope.load_all_category = function() {
		var url = `${host}/category`;
		$http.get(url).then(resp => {
			$scope.cats = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.load_all = function() {
		var url = `${host}/product`;
		$http.get(url).then(resp => {
			$scope.sps = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.edit = function(id) {
		var url = `${host}/product/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;

			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error", error);
		})
	}

	$scope.create = function() {



		//Không chọn thương hiệu
		if (!$scope.form.category || !$scope.form.category.id) {
			ToastService.createToast("warning", "vui lòng chọn loại sản phẩm", $scope.toasts);
			/*	$scope.errorMessage = "Vui lòng chọn thương hiệu!";
				$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi bỏ trống tên sản phẩm 
		if (!$scope.form.name) {
			ToastService.createToast("warning", "Vui lòng nhập tên sản phẩm!!", $scope.toasts);
			/*$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi trùng tên sản phẩm
		let existingProduct = $scope.sps.find(sp => sp.name === $scope.form.name);
		if (existingProduct) {
			ToastService.createToast("warning", "tên sản phẩm đã tồn tại!!", $scope.toasts);
			/*$scope.errorMessage = "Tên sản phẩm đã tồn tại!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi bỏ trống đơn giá
		if (!$scope.form.quantity) {
			ToastService.createToast("warning", "Vui lòng nhập số lượng!!", $scope.toasts);
			/*$scope.errorMessage = "Vui lòng nhập đơn giá!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi bỏ trống đơn giá
		if (!$scope.form.price) {
			ToastService.createToast("warning", "Vui lòng nhập đơn giá!!", $scope.toasts);
			/*$scope.errorMessage = "Vui lòng nhập đơn giá!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}


		var item = angular.copy($scope.form);
		var url = `${host}/product`;
		$http.post(url, item).then(resp => {

			$scope.sps.push(item);

			$scope.reset();
			$scope.errorMessage = ''; // Xóa thông báo lỗi khi thành công
			alert("Thêm mới thành công");
			console.log("Success", resp);


			location.reload();
		}).catch((error) => {
			alert("thêm thất bại!")
			console.log("Error", error);
		});
	}

	$scope.update = function() {

		//Không chọn thương hiệu
		if (!$scope.form.category || !$scope.form.category.id) {
			ToastService.createToast("warning", "vui lòng chọn loại sản phẩm!!", $scope.toasts);
			/*	$scope.errorMessage = "Vui lòng chọn thương hiệu!";
				$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi bỏ trống tên sản phẩm 
		if (!$scope.form.name) {
			ToastService.createToast("warning", "Vui lòng nhập tên sản phẩm!!", $scope.toasts);
			/*$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}


		//Lỗi bỏ trống đơn giá
		if (!$scope.form.quantity) {
			ToastService.createToast("warning", "Vui lòng nhập số lượng!!", $scope.toasts);
			/*$scope.errorMessage = "Vui lòng nhập đơn giá!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi bỏ trống đơn giá
		if (!$scope.form.price) {
			ToastService.createToast("warning", "Vui lòng nhập đơn giá!!", $scope.toasts);
			/*$scope.errorMessage = "Vui lòng nhập đơn giá!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}


		var item = angular.copy($scope.form);
		var cat = angular.copy($scope.form.category.id);
		var url = `${host}/product/${$scope.form.id}?category=${cat}`;

		$http
			.put(url, item).then(resp => {
				var index = $scope.sps.findIndex(item =>
					item.id == $scope.form.id);

				alert("cập nhật thành công!")
				$scope.sps[index] = resp.data;

				console.log("Succes", resp);
			}).catch((error) => {
				alert("cập nhật thất bại!")
				console.log("Error", error)
			});


	}

	$scope.delete = function(id) {
		var url = `${host}/product/${id}`;
		if (confirm("Bạn có chắc muốn xóa sản phẩm này?")) {
			$http.delete(url).then(resp => {
				var index = $scope.sps.findIndex(item => item.id == id);

				$scope.sps.splice(index, 1);
				alert("xóa thành công")
				$scope.reset();
				console.log("Succes", resp);
			}).catch((error) => {
				alert("xóa không thành công")
				console.log("Error", error)
			});
		}
	}


	// Tìm kiếm sản phẩm 
	$scope.searchProductByName = function() {
		if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
			$http.get("/rest/product/search", {
				params: { keyword: $scope.searchKeyword }
			}).then(resp => {
				$scope.sps = resp.data;
			}).catch(error => {
				alert("lỗi tìm kiếm sản phẩm")
				console.log("Error", error);
			});
		} else {
			// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm

			$scope.load_all();
		}
	};

	//upload image
	$scope.imageChanged = function(files) {
		var data = new FormData();

		data.append('file', files[0]);
		$http.post('/rest/upload/img', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.image = resp.data.name
		}).catch(error => {
			alert("lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}


	$scope.pager = {
		page: 0,
		size: 5,

		get sps() {
			var start = this.page * this.size;
			return $scope.sps.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.sps.length / this.size);
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
	$scope.load_all_category();
	$scope.reset();
})