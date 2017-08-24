myApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider.when('/login', {
		templateUrl : 'mainpage/login/template/login.html',
		controller : 'loginController'
	});

}]);