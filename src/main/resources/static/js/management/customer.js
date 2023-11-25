let host = "http://localhost:8080/rest";
const app = angular.module("AdminKhApp", []);
app.controller("AdminKhCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.roles = [];
	$scope.accounts = [];
	

	$scope.reset = function() {
		$scope.form = { active: true };
		$scope.key = null;
	}
	

	$scope.load_all = function() {
		var url = `${host}/customer`;
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
	
	$scope.load_all_account = function() {
		var url = `${host}/account`;
		$http.get(url).then(resp => {
			$scope.accounts = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	
	
	$scope.chan = function(customerId) {
		var url = `${host}/customer/chan/${customerId}`;
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
		var url = `${host}/customer/boChan/${customerId}`;
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
	
		
	// Tìm kiếm sản phẩm 
	$scope.searchCustomerByName = function() {
		if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
			$http.get("/rest/customer/search", {
				params: { keyword: $scope.searchKeyword }
			}).then(resp => {
				$scope.items = resp.data;
			}).catch(error => {
				alert("lỗi tìm kiếm tên khách hàng")
				console.log("Error", error);
			});
		} else {
			// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm
			$scope.load_all();
		}
	};
	
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var url = `${host}/customer`;
		$http.post(url, item).then(resp => {

			$scope.items.push(item);

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
	
	$scope.edit = function(id) {
		var url = `${host}/customer/${id}`;
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
	$scope.load_all_account();
	$scope.load_all_role();
	$scope.reset();
})