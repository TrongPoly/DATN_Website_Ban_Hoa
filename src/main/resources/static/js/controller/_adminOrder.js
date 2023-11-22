app.controller('adminOrder', ["$scope", "OrderService", "ToastService",
	function($scope, OrderService, ToastService) {
		$scope.listOrder = [];
		$scope.listOrderDetails = [];
		$scope.allOrder = [];
		$scope.toasts = [];
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
				sum += $scope.listOrderDetails[i].product.price * $scope.listOrderDetails[i].quantity;
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
		//Hủy đơn, status =4
		$scope.cancelOrder = function(idOrder) {
			if ($scope.note == undefined) {
				ToastService.createToast("warning", "Vui lòng nhập lý do", $scope.toasts);
			} else {
				alert($scope.note);
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
		//điếm số đơn
		$scope.countOrder();;
		//Load đơn hàng
		$scope.getOrder();
	}]);