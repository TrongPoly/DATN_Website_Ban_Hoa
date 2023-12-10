app.controller('adminOrder', ["$scope", "OrderService", "ToastService","$http",
	function($scope, OrderService, ToastService,$http) {
		$scope.listOrder = [];
		$scope.listOrderDetails = [];
		$scope.allOrder = [];
		$scope.toasts = [];
		$scope.oneOrder = {}
		$scope.methodSearch = true;
		$scope.getOrder = function(status) {
			if (status === undefined) {
				status = 0;
			}
			OrderService.getAllOrderByStatus(status)
				.then((resp) => {
					$scope.listOrder = resp.data;
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		//Tìm kiếm đơn hàng theo ID
		$scope.searchOrderById = function() {
			if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
				$http.get("/api/order/search?key="+$scope.searchKeyword).then(resp => {
					$scope.listOrder = resp.data;
				}).catch(error => {
					ToastService.createToast("error", "Lỗi tìm kiếm tên đơn hàng", $scope.toasts);
					console.log("Error", error);
				});
			} else {
				// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm
				$scope.getOrder(0);
			}
		};
		$scope.searchOrderByEmail = function() {
			if ($scope.searchEmail && $scope.searchEmail.trim() !== "") {
				$http.get("/api/order/search_by_email?email="+$scope.searchEmail).then(resp => {
					$scope.listOrder = resp.data;
				}).catch(error => {
					ToastService.createToast("error", "Lỗi tìm kiếm tên đơn hàng", $scope.toasts);
					console.log("Error", error);
				});
			} else {
				// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm
				$scope.getOrder(0);
			}
		};

		$scope.getOrderById = function(idOrder) {
			OrderService.getOrderById(idOrder)
				.then((resp) => {
					$scope.oneOrder = resp.data;
					$scope.getOrderDetails(idOrder);
				})
		}

		$scope.getOrderDetails = function(idOrder) {
			OrderService.getOrderDetails(idOrder)
				.then((resp) => {
					$scope.listOrderDetails = resp.data;
					$scope.getStatus(idOrder);
					$scope.idOrderForCancel = idOrder;
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		$scope.getStatus = function(idOrder) {
			OrderService.getOrderStatus(idOrder)
				.then((resp) => {
					$scope.listStatus = resp.data;
				})
		}

		$scope.total = function() {
			let sum = 0;
			for (let i = 0; i < $scope.listOrderDetails.length; i++) {
				sum += $scope.listOrderDetails[i].price * $scope.listOrderDetails[i].quantity;
			}
			return sum;
		}

		$scope.orderCounts = {
			pending: 0,
			confirmed: 0,
			readyForPickup: 0,
			received: 0,
			canceled: 0
		};
		$scope.countOrder = function() {
			OrderService.getAllOrder()
				.then((resp) => {
					$scope.allOrder = resp.data;
					console.log($scope.allOrder.length);
					$scope.orderCounts = {
						pending: 0,
						confirmed: 0,
						readyForPickup: 0,
						received: 0,
						canceled: 0
					};

					$scope.allOrder.forEach((order) => {
						switch (order.status.statusId) {
							case 0: // Chờ xử lý
								$scope.orderCounts.pending++;
								break;
							case 1: // Đã xác nhận
								$scope.orderCounts.confirmed++;
								break;
							case 2: // Sẵn sàng để lấy
								$scope.orderCounts.readyForPickup++;
								break;
							case 3: // Đã nhận hàng
								$scope.orderCounts.received++;
								break;
							case 4: // Đã hủy
								$scope.orderCounts.canceled++;
								break;
							default:
								break;
						}
					});
				})
		}
		//Hủy đơn, status =4
		$scope.cancelOrder = function(idOrder) {
			if ($scope.note == undefined) {
				ToastService.createToast("warning", "Vui lòng nhập lý do", $scope.toasts);
			} else {
				if (confirm("Xác nhận hủy đơn?")) {
					OrderService.updateOrder(idOrder, 4, $scope.note)
						.then((resp) => {
							ToastService.createToast("info", "Đã hủy đơn hàng", $scope.toasts);
							//điếm số đơn
							$scope.countOrder();
							//Load đơn hàng
							$scope.getOrder();
							$('#noteStatus').modal('hide');
						})
						.catch((error) => {
							console.log(error.status);
						});
				}
			}
		}
		//Xác nhận đơn
		$scope.confirmOrder = function(idOrder) {
			if (confirm("Xác nhận đơn hàng?")) {
				OrderService.updateOrder(idOrder, 1)
					.then((resp) => {
						ToastService.createToast("success", "Đã xác nhận đơn hàng", $scope.toasts);
						//điếm số đơn
						$scope.countOrder();
						//Load đơn hàng
						$scope.getOrder();
					})
					.catch((error) => {
						console.log(error.status);
					});
			}
		}
		//Chuẩn bị đơn hoàn tất 
		$scope.preparedOrder = function(idOrder) {
			if (confirm("Đã chuẩn bị xong đơn hàng?")) {
				OrderService.updateOrder(idOrder, 2)
					.then((resp) => {
						ToastService.createToast("success", "Đơn hàng đã chuẩn bị xong", $scope.toasts);
						//điếm số đơn
						$scope.countOrder();
						//Load đơn hàng
						$scope.getOrder();
					})
					.catch((error) => {
						console.log(error.status);
					});
			}
		}
		//Hoàn thành đơn hàng 
		$scope.finishOrder = function(idOrder) {
			if (confirm("Xác nhận hoàn thành đơn hàng?")) {
				OrderService.updateOrder(idOrder, 3)
					.then((resp) => {
						ToastService.createToast("success", "Hoàn tất đơn hàng", $scope.toasts);
						//điếm số đơn
						$scope.countOrder();
						//Load đơn hàng
						$scope.getOrder();
					})
					.catch((error) => {
						console.log(error.status);
					});
			}
		}
		$scope.pager = {
			page: 0,
			size: 8,

			get listOrder() {
				var start = this.page * this.size;
				return $scope.listOrder.slice(start, start + this.size);
			},

			get count() {
				return Math.ceil(1.0 * $scope.listOrder.length / this.size);
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
		//điếm số đơn
		$scope.countOrder();;
		//Load đơn hàng
		$scope.getOrder();
	}]);