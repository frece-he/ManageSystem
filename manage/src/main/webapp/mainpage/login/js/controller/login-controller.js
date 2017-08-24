myApp
.controller(
		'loginController', [
			'loginService',
			'$scope',
			'$http',
			function(loginService, $scope, $http) {
				$scope.welcomeMessage = "Please do login"
				
				$scope.doLogin = function() {
					loginService.doLogin($scope);
				}
			}
			]);