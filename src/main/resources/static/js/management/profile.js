let host = location.origin+"/rest";
const app = angular.module("AdminPfApp", []);
app.controller("AdminPfCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.editProfile = false;
	
	$scope.edit = function(id) {
		$scope.editProfile = !id;
	}

	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
	}

	$scope.load_all = function() {
		var url = `${host}/profile/getUser`;
		$scope.khachHang = {};
		$http.get(url).then(resp => {
			$scope.khachHang = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	
	$scope.edit = function(id) {
		$scope.editProfile = !id;
	}
	
	$scope.cancelEditADR = function(id) {
		
	};
	
	$scope.update = function() {

		//Lỗi bỏ trống tên khách hàng
		if (!$scope.khachHang.fullName) {
			alert("Vui lòng nhập tên khách hàng!!")

			return;
		}

		var item = angular.copy($scope.khachHang);
		var url = `${host}/profile/${$scope.khachHang.id}`;
		$http
			.put(url, item).then(resp => {
				alert("cập nhật thành công!")
				$scope.editProfile = false;
				console.log("Succes", resp);
			}).catch((error) => {
				alert("Cập nhật thất bại")
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

	$scope.load_all();
})