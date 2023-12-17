app.controller('orderCtrl', ["$scope", "OrderService", "ToastService","$interval",
	function($scope, OrderService, ToastService,$interval) {
		$scope.listOrder = [];
		$scope.toasts = [];
		$scope.listOrderDetails = [];
		$scope.note = "";
		$scope.getOrder = function(status) {
			if (status === undefined) {
				status = 0;
			}
			OrderService.getOrder(status)
				.then((resp) => {
					$scope.listOrder = resp.data;
				})
				.catch((error) => {
					console.log(error.status);
				});
		}
		$scope.total = function() {
			let sum = 0;
			for (let i = 0; i < $scope.listOrderDetails.length; i++) {
				sum += $scope.listOrderDetails[i].price * $scope.listOrderDetails[i].quantity;
			}
			return sum;
		}
		$scope.getOrderById = function(idOrder){
			OrderService.getOrderById(idOrder)
			.then((resp)=>{
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

		$scope.cancelOrder = function(idOrder) {
			if ($scope.note == undefined) {
				ToastService.createToast("warning", "Vui lòng nhập lý do", $scope.toasts);
			} else {
				if (confirm("Xác nhận hủy đơn?")) {
					OrderService.updateOrder(idOrder, 4, $scope.note)
						.then((resp) => {
							ToastService.createToast("info", "Đã hủy đơn hàng", $scope.toasts);
							$scope.getOrder();
							$scope.countOrder();
							$('#noteStatus').modal('hide');
						})
						.catch((error) => {
							console.log(error.status);
						});
				}
			}
		}
		$scope.orderCounts = {
			pending: 0,
			confirmed: 0,
			readyForPickup: 0,
			received: 0,
			canceled: 0
		};
		$scope.countOrder = function() {
			let email = sessionStorage.getItem("email");
			OrderService.getAllOrderByEmail(email)
				.then((resp) => {
					$scope.allOrder = resp.data;
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
		
		
		var checkOrderConfirm = function() {
			OrderService.getOrder(0)
				.then((resp) => {
					if(resp.data.length<$scope.orderCounts.pending){
						$scope.getOrder();
						$scope.countOrder();
						ToastService.createToast("info", "Một đơn hàng vừa được cập nhật", $scope.toasts);
					}		
				});
		};

		// Sử dụng $interval để gọi hàm updateMessage mỗi 5 giây
		var intervalPromise = $interval(checkOrderConfirm, 1000);

		// Xóa interval khi scope bị hủy để tránh rò rỉ bộ nhớ
		$scope.$on('$destroy', function() {
			$interval.cancel(checkOrderConfirm);
		});		
		
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
		$scope.getOrder();
		$scope.countOrder();
	}]);