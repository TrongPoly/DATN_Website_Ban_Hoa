const reportProduct = angular.module("reportProductApp", []);
reportProduct.controller("reportCtrl", function($scope, $http) {
	$scope.month = 11;
	$scope.year = 2023;
	var chart;
	$scope.listYear = [];
	$http.get(location.origin + '/admin/rest/report/data/year')
		.then(resp => {
			$scope.listYear = resp.data;
		})
		
	$scope.getDoanhthuSanPham = function(){
		$scope.tenSP = [];
		$scope.doanhThu = [];
		$scope.slBanDuoc = [];
		$http.get(location.origin+"/admin/rest/report/product?month="+$scope.month+"&year="+$scope.year)
		.then(resp=>{
			$scope.productReport = resp.data;
			for(let i =0; i<$scope.productReport.length; i++){
					$scope.tenSP.push($scope.productReport[i].tenSanPham);
					$scope.doanhThu.push($scope.productReport[i].tongTien);
					$scope.slBanDuoc.push($scope.productReport[i].soLuongBan);
			}
			$scope.$watch('total', function() {
					drawChart($scope.doanhThu,$scope.slBanDuoc,$scope.tenSP);
				});
			
		console.log($scope.tenSP);
		console.log($scope.doanhThu);
		})
	}
         var chart;

        function drawChart(revenueData, quantityData, date) {
            var ctx = document.getElementById('income-chart').getContext('2d');

            if (chart) {
                chart.destroy();
            }

            chart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: date,
                    datasets: [
                        {
                            label: 'Doanh thu',
                            data: revenueData,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1,
                            yAxisID: 'y-axis-1' // Chỉ định sử dụng trục y chính
                        },
                        {
                            label: 'Số lượng bán',
                            data: quantityData,
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1,
                            yAxisID: 'y-axis-2' // Chỉ định sử dụng trục y thứ hai
                        }
                    ]
                },
                options: {
                    scales: {
                        y: {
                            type: 'linear',
                            display: true,
                            position: 'left',
                            id: 'y-axis-1',
                        },
                        y1: {
                            type: 'linear',
                            display: true,
                            position: 'right',
                            id: 'y-axis-2',

                            // Đặt giới hạn trục y thứ hai nếu cần
                           
                        }
                    }
                }
            });
        }
$scope.getDoanhthuSanPham();
      
});
