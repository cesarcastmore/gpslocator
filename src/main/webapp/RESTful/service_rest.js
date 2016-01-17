controllers.factory('ServiceRESTful', [ '$http', function($http) {
	var service = {};

	
	service.getAll = function() {
		var response = $http({
			method : 'GET',
			url : 'api/services',
		})

		return response;

	}	
	

	return service;

}]);