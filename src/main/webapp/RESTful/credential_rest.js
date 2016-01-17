controllers.factory('CredentialService', [ '$http', function($http) {
	var service = {};

	service.Create = function(user) {
		var response = $http({
			method : 'POST',
			url : 'api/credentials',
			data : user,
			cache : true
		})

		return response;

	}
	
	
	service.authetication = function(username, password) {
		var response = $http({
			method : 'GET',
			url : 'api/credentials',
			cache : true, 
			params: {username: username, password: password }
		})

		return response;

	}

	return service;

}]);