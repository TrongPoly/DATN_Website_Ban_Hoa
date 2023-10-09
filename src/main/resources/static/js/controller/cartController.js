app.controller('IndexController', function($scope, CartService) {
  $scope.addToCart = function(product) {
    CartService.addToCart(product);
  };
});