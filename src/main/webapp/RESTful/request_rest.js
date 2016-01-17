controllers.factory('RequestRESTful', [ '$http', function($http) {
	var service = {};

	service.create = function(request) {

		var response = $http({
			method : 'POST',
			url : 'api/requests',
			data : request,
		})

		return response;

	}

	service.getAll = function(params) {

		var response = $http({
			method : 'GET',
			url : 'api/requests',
			params : params
		});

		return response;

	}

	service.get = function(requestId) {

		var response = $http({
			method : 'GET',
			url : 'api/requests/' + requestId
		});

		return response;

	}

	service.remove = function(requestId) {
		var response = $http({
			method : 'DELETE',
			url : 'api/requests/' + requestId,
		})

		return response;

	}

	service.update = function(request) {
		var response = $http({
			method : 'PUT',
			url : 'api/requests/' + request.requestId,
			data : request
		})

		return response;

	}

	service.createNode = function(parent, request) {
		var response = $http({
			method : 'POST',
			url : 'api/requests/' + parent.requestId + "/nodes",
			data : request
		})

		return response;

	}

	service.getNodes = function(parent) {
		var response = $http({
			method : 'GET',
			url : 'api/requests/' + parent.requestId + "/nodes"
		})

		return response;

	}
	
	service.getOffers = function(parent) {
		var response = $http({
			method : 'GET',
			url : 'api/requests/' + parent.requestId + "/offers"
		})

		return response;

	}
	
	
	service.getResponses = function(parent) {
		var response = $http({
			method : 'GET',
			url : 'api/requests/' + parent.requestId + "/responses"
		})

		return response;

	}
	
	service.getAuthor = function(request) {
		var response = $http({
			method : 'GET',
			url : 'api/requests/' + request.requestId + "/author"
		})

		return response;

	}
	
	service.getAddress = function(request) {
		var response = $http({
			method : 'GET',
			url : 'api/requests/' + request.requestId + "/author/address"
		})

		return response;

	}
	
	
	service.removeNode = function(parent, request) {
		var response = $http({
			method : 'DELETE',
			url : 'api/requests/' + parent.requestId + "/nodes/" + request.requestId
		})

		return response;

	}

	return service;

} ]);