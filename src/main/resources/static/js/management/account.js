let host = "http://localhost:8080/api";
const app = angular.module("AdminTaiKhoanApp", []);
app.controller("AdminTaiKhoanCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.accs = [];
	


	$scope.reset = function() {
		$scope.form = { trangThai: true };
		$scope.key = null;
	}

	$scope.load_all_account = function() {
		var url = `${host}/taikhoan`;
		$http.get(url).then(resp => {
			$scope.accs = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	

	$scope.load_all = function() {
		var url = `${host}/taikhoan`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	$scope.edit = function(id) {
		var url = `${host}/taikhoan/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;

			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error", error);
		})
	}
	$scope.create = function() {

		//Lỗi bỏ trống tên khách hàng
		if (!$scope.form.tentaikhoan) {
			alert("Vui lòng nhập tên khách hàng!!")
			
			return;
		}
		//Lỗi bỏ trống số điện thoại 
		if (!$scope.form.soDienThoai) {
			alert("Vui lòng nhập tên khách hàng!!")
			$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal
			return;
		}
		if (isNaN($scope.form.soDienThoai) || $scope.form.soDienThoai.length < 10 || $scope.form.soDienThoai.length > 11) {
			alert("Số điện thoại không hợp lệ!");
			return;
		}


		var item = angular.copy($scope.form);
		var url = `${host}/taikhoan`;
		$http.post(url, item).then(resp => {

			$scope.items.push(item);
			
			
			$scope.errorMessage = '';
			alert("thêm thành công!")
			console.log("Success", resp);
			
			$('#errorModal1').modal('show'); // Show the modal
			location.reload();
			$scope.reset();
		}).catch((error) => {
			alert("thêm thất bại!")
			console.log("Error", error);
		});
	}

	$scope.chan = function(customerId) {
		var url = `${host}/taikhoan/chan/${customerId}`;
		$http
			.put(url)
			.then((resp) => {
				alert("Chặn thành công!");
				location.reload();
			})
			.catch((error) => {
				alert("Lỗi");
			});
	}
	$scope.boChan = function(customerId) {
		var url = `${host}/taikhoan/boChan/${customerId}`;
		$http
			.put(url)
			.then((resp) => {
				alert("Bỏ chặn thành công!");
				location.reload();
			})
			.catch((error) => {
				alert("Lỗi");
			});
	}
	$scope.update = function() {
		
		//Lỗi bỏ trống tên khách hàng
		if (!$scope.form.tentaikhoan) {
			alert("Vui lòng nhập tên khách hàng!!")
			
			return;
		}
		
		//Lỗi bỏ trống số điện thoại 
		if (!$scope.form.soDienThoai) {
			alert("Vui lòng nhập tên khách hàng!!")
			$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal
			return;
		}
		//Lỗi sai cú pháp số điện thoại 
		if (isNaN($scope.form.soDienThoai) || $scope.form.soDienThoai.length < 10 || $scope.form.soDienThoai.length > 11) {
			alert("Số điện thoại không hợp lệ!");
			return;
		}
		
		
		
		var item = angular.copy($scope.form);
		var url = `${host}/taikhoan/${$scope.form.id}`;
		$http
			.put(url, item).then(resp => {
				var index = $scope.items.findIndex(item =>
					item.id == $scope.form.id);
				alert("cập nhật thành công!")
				$scope.items[index] = resp.data;

				console.log("Succes", resp);
			}).catch((error) => {
				alert("cập nhật thất bại!")
				console.log("Error", error)
			});


	}
	
	// Tìm kiếm sản phẩm 
	$scope.searchCustomerByName = function() {
		if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
			$http.get("/rest/taikhoan/search", {
				params: { keyword: $scope.searchKeyword }
			}).then(resp => {
				$scope.items = resp.data;
			}).catch(error => {
				alert("lỗi tìm kiếm tên khách hàng")
				console.log("Error", error);
			});
		} else {
			// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm
			$scope.load_all_nsx();
			$scope.load_all();
		}
	};
	
	

	$scope.delete = function(id) {
		var url = `${host}/taikhoan/${id}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.id == id);

			$scope.items.splice(index, 1);
			alert("xóa thành công")
			$scope.reset();
			console.log("Succes", resp);
		}).catch((error) => {
			alert("xóa không thành công")
			console.log("Error", error)
		});
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


	$scope.load_all_hkh();
	$scope.load_all();
	
})