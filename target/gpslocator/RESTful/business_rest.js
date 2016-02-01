controllers.factory('BusinessRESTful', [ '$http', function($http) {
	var service = {};
	
	service.get = function(businessId) {
		var response = $http({
			method : 'GET',
			url : 'api/business/'+businessId
		})

		return response;

	}
	
	service.getAddress = function(user) {
		var response = $http({
			method : 'GET',
			url : 'api/business/' + user.userId + "/address"
		})

		return response;

	}
	
	return service;

} ]);