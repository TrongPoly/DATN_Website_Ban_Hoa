const app = angular.module("reportApp", []);
app.controller("dashboard-ctrl", function($scope, $http) {
	$scope.tota = 0;
	$scope.month = 11;
	$scope.costInMonth = [];
	$scope.costDate = [];
	$scope.costData = [];
	$scope.orderData = [];
	var chart;
	$scope.productInMonth = [];
	$scope.productName = [];
	$scope.productCount = [];

	// Tạo một đối tượng Date
	var currentDate = new Date();

	// Lấy tháng từ đối tượng Date (lưu ý rằng phương thức getMonth() trả về giá trị từ 0 đến 11)
	var currentMonth = currentDate.getMonth() + 1; // Bạn có thể cộng thêm 1 để có giá trị từ 1 đến 12

	// Gán giá trị tháng vào $scope để sử dụng trong HTML
	$scope.currentMonth = currentMonth;

	$scope.getDoanhThu = function() {
		$scope.costDate = [];
		$scope.costData = [];
		$http.get(location.origin + '/admin/rest/report/total?month=' + $scope.month).then(resp => {
			$scope.tota = resp.data;
			console.log($scope.total)
		}).catch(error => {
			alert("Load total data fail");
			console.log(error);
		});

		$http.get(location.origin + '/admin/rest/report/reportcost?month=' + $scope.month).then(resp => {

			$scope.costInMonth = resp.data;
			console.log($scope.costInMonth)
			$scope.costInMonth.forEach(c => c.date = new Date(c.date));
			for (var i = 0; i < $scope.costInMonth.length; i++) {
				let dateObject = new Date($scope.costInMonth[i].ngayBan);
				let ngayBan = "Ngày " + dateObject.getDate();


				for (var j = 0; j < $scope.costDate.length; j++) {
					var checkDate = true;
					if (ngayBan == $scope.costDate[j]) {
						checkDate = false;
						break;
					}
				}
				if (checkDate == false) {
					$scope.costData[$scope.costData.length - 1] += $scope.costInMonth[i].doanhThu
				} else {
					$scope.costDate.push(ngayBan);
					$scope.costData.push($scope.costInMonth[i].doanhThu);
				}
				console.log($scope.costDate);

				$scope.$watch('tota', function() {
					drawChart($scope.costData, $scope.costDate);
				});
				$scope.orderData.push($scope.costInMonth[i].tongHoaDon);
				if ($scope.costInMonth[i].ngayBan == (new Date()).getDate()) {
					$scope.costToday = $scope.costInMonth[i].doanhThu;
					$scope.orderToday = $scope.costInMonth[i].tongHoaDon;
				} else {
					$scope.costToday = 0;
					$scope.orderToday = 0;
				}
			}
			console.log((new Date()).getDate())
		}).catch(error => {
			alert("Load cost data fail");
			console.log(error);
		});
	}
	// Vẽ biểu đồ khi dữ liệu thay đổi

	// Hàm vẽ biểu đồ sử dụng Chart.js
	function drawChart(data, date) {
		if (chart) {
			chart.destroy();
		}
		var ctx = document.getElementById('income-chart').getContext('2d');
		chart = new Chart(ctx, {
			type: 'bar',
			data: {
				labels: date,
				datasets: [
					{
						label: 'Doanh thu',
						data: data,
						backgroundColor: 'rgba(75, 192, 192, 0.2)',
						borderColor: 'rgba(75, 192, 192, 1)',
						borderWidth: 1
					}
				]
			},
			options: {
				scales: {
					x: {
						maxTicksLimit: 31
					},
					y: {
						beginAtZero: true
					}
				}
			}
		});
	}

	$scope.getDoanhThu();
	/* $scope.reportCost = function () {
	   let date = (new Date()).toLocaleString('default', { month: 'short' });
   
	   const data = {
		 labels: $scope.costDate,
		 datasets: [
		   {
		   label: 'Cost in '+date,
		   data: $scope.costData,
		   fill: false,
		   borderColor: 'rgb(75, 192, 192)',
		   tension: 0.1
		 }
	   ]
	   };
   
	   const config = {
		 type: 'line',
		 data: data,
	   };
	   const myChart = new Chart(
		 document.getElementById('costChart'),
		 config
	   );
	 }*/
});


/* 

  $scope.reportCost = function () {
	let date = (new Date()).toLocaleString('default', { month: 'short' });

	const data = {
	  labels: $scope.costDate,
	  datasets: [
		{
		label: 'Cost in '+date,
		data: $scope.costData,
		fill: false,
		borderColor: 'rgb(75, 192, 192)',
		tension: 0.1
	  }
	]
	};

	const config = {
	  type: 'line',
	  data: data,
	};
	const myChart = new Chart(
	  document.getElementById('costChart'),
	  config
	);
  }*/
/* $scope.reportProduct = function(){
   let date = (new Date()).toLocaleString('default', { month: 'short' });
   const data = {
	 labels: $scope.productName,
	 datasets: [
	   {
	   label: 'Best seller in '+date,
	   data: $scope.productCount,
	   fill: false,
	   backgroundColor: [
		 'rgb(255, 99, 132)',
		 'rgb(54, 162, 235)',
		 'rgb(255, 205, 86)',
		 'rgb(4, 76, 153 )',
		 'rgb(181, 216, 253 )',
	   ],
	   hoverOffset: 4
	 }
   ]
   };

   const config = {
	 type: 'pie',
	 data: data,
   };
   const bestSeller = new Chart(
	 document.getElementById('bestSeller'),
	 config
   );
 }
 $scope.reportProduct();*/
/*   $scope.reportCost();*/


