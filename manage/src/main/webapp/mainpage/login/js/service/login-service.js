myApp.factory('loginService', ['$http', function($http) {
	return new loginService($http);
}]);

function loginService(http) {
	this.doLogin = function(scope) {
		console.log("in loginService");
		console.log("userName: " +  scope.userName);
		console.log("password: " +  scope.password);
		http({
			method : 'POST',
			url : "login/dologin",
			data : {
				userName : scope.userName,
				password : scope.password
			},
		}).then(function successCallback(response) {
			console.log("success.status" + response.status + "..headers:" + response.headers + "..data:" + response.data.resultCode);
			console.log(response);
			var userName = response.data.resultData.userName;
			scope.welcomeMessage="Welcome, " + userName;
            
        }, function errorCallback(response) {
            // 请求失败执行代码
        	console.log("failed.status..." + response.status + "....");
        });
		
		
	};


};
