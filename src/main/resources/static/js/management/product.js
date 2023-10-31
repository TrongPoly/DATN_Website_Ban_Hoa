let host = "http://localhost:8080/rest";
const app = angular.module("AdminSpApp", []);
app.controller("AdminSpCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.sps = [];
	$scope.cats = [];
	
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
		$http.post('/rest/upload/images', data, {
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