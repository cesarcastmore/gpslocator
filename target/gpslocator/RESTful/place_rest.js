controllers.factory('PlaceRESTful', [ '$http', function($http) {
	var service = {};

	service.nearSearchBy = function(params) {
		var response = $http({
			method : 'GET',
			url : 'api/place/nearbysearch',
			cache : true,
			params : params
		});
		
		return response;
	}
	
	service.nearSearchByFree = function(params) {
		var response = $http({
			method : 'GET',
			url : 'api/place/nearbysearchfree',
			params : params
		});
		
		return response;
	}
	
	service.getBusiness = function(placeid) {
		var response = $http({
			method : 'GET',
			url : 'api/place/'+placeid+'/business',
			cache : true
		});
		
		return response;
	}
	
	
	service.addPlace = function(place) {
		var response = $http({
			method : 'POST',
			url : 'api/place/addplace',
            data: place

		});
		
		return response;
	}

	return service;

} ]);