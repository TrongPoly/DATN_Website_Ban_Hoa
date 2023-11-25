app.controller('orderCtrl', ["$scope", "OrderService", "ToastService",
	function($scope, OrderService, ToastService) {
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
				sum += $scope.listOrderDetails[i].product.price * $scope.listOrderDetails[i].quantity;
			}
			return sum;
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
						console.log(order.status);
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
					console.log($scope.orderCounts);
				})
		}
		$scope.getOrder();
		$scope.countOrder();
	}]);