controllers.controller('RequestLocateController', [ 'RequestRESTful',
		'UserServiceRESTful', '$scope', 'cfpLoadingBar',
		function(RequestRESTful, UserServiceRESTful, $scope, cfpLoadingBar) {

			console.log("LocateRequestController");

			$scope.find = find;
			$scope.getUserServices = getUserServices;
			$scope.getRequest = getRequest;
			$scope.createNode = createNode;
			$scope.getOffers = getOffers;
			$scope.getResponses = getResponses;
			$scope.removeNode = removeNode;


			$scope.requests = [];
			$scope.userservices = [];

			function find() {
				params = {
					findBy : $scope.findby
				};

				var respuesta = RequestRESTful.getAll(params);
				respuesta.then(function exito(response) {
					$scope.requests = response.data;

				}, function error(response) {

				});

			}

			function getUserServices() {

				var respuesta = UserServiceRESTful.getAll();
				respuesta.then(function exito(response) {
					$scope.userservices = response.data;
				}, function error(response) {

				});

				getRequest('all');

			}

			function getRequest(para) {

				params = {
					service : para
				};
				console.log(para);

				var respuesta = RequestRESTful.getAll(params);
				respuesta.then(function exito(response) {
					$scope.requests = response.data;
				}, function error(response) {

				});

			}

			function createNode(parent, request) {

				var respuesta = RequestRESTful.createNode(parent, request);
				respuesta.then(function exito(response) {					
					parent.requests.push(response.data);
					request.commit=null;


				}, function error(response) {

				})

			}

			function getOffers(parent) {

				var respuesta = RequestRESTful.getOffers(parent);
				respuesta.then(function exito(response) {
					parent.requests = response.data;
				}, function error(response) {

				})

			}
			
			function getResponses(parent) {

				var respuesta = RequestRESTful.getResponses(parent);
				respuesta.then(function exito(response) {
					parent.requests = response.data;
				}, function error(response) {

				})

			}

			function removeNode(parent, request) {
				
				
				var respuesta = RequestRESTful.removeNode(parent, request);
				respuesta.then(function exito(response) {
					parent.requests.splice(parent.requests.indexOf(request), 1);

				}, function error(response) {

				})

			}

		} ]);