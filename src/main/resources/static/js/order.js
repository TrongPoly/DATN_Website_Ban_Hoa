let host = "http://localhost:8080/api/payment";
const app = angular.module("myApp", []);
app.controller("checkoutCtrl", function($scope, $http) {
	$scope.amount=1500000;
	$scope.checkout = function(amount) {
    var url = `${host}/create_payment?amount=${amount}`;
    console.log(url);
    $http
        .get(url)
        .then((resp) => {
		var res = resp.data;
		location.href=res.url;
        })
        .catch((error) => {
            alert(error.status);
            console.log(error.message);
        });
}
});
