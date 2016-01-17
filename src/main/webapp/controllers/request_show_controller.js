controllers.controller('RequestShowController', [
		'$scope',
		'$routeParams',
		'RequestRESTful',
		'LocationService',
		function($scope, $routeParams, RequestRESTful, LocationService) {

			$scope.getRequest = getRequest;
			$scope.getOffers = getOffers;
			$scope.actionOnClick = actionOnClick;
			$scope.getResponses = getResponses;
			$scope.createNode = createNode;
			$scope.removeNode = removeNode;
			$scope.getAuthor = getAuthor;
			$scope.getAddress = getAddress;
			$scope.getCurrentLocation = getCurrentLocation;


			function getRequest() {
				
				getCurrentLocation();

				var respuesta = RequestRESTful.get($routeParams.requestId);
				respuesta.then(function exito(response) {

					$scope.request = response.data;
					getOffers($scope.request);
					
				}, function error(response) {

				})

			}

			function actionOnClick(parent) {
				$scope.menu = {
					click : 1
				};

				if (!parent.click) {
					parent.click = false;
				}
				parent.click = !parent.click;
				getResponses(parent);

			}

			function getOffers(parent) {

				var respuesta = RequestRESTful.getOffers(parent);
				respuesta.then(function exito(response) {
					parent.requests = [];
					parent.requests = response.data;
				}, function error(response) {

				})

			}

			function getResponses(parent) {
				
				var respuesta = RequestRESTful.getResponses(parent);
				respuesta.then(function exito(response) {
					parent.requests = [];
					parent.requests = response.data;
				}, function error(response) {

				})

			}

			function getAuthor(request) {

				var respuesta = RequestRESTful.getAuthor(request);
				respuesta.then(function exito(response) {
					request.author = response.data;
					getAddress(request);
				}, function error(response) {

				})

			}

			function getAddress(request) {

				var respuesta = RequestRESTful.getAddress(request);
				respuesta.then(function exito(response) {
					request.author.address = response.data;
				}, function error(response) {

				})

			}

			function createNode(parent, request) {

				request.latitude = $scope.currentlocation.latitude;
				request.longitude = $scope.currentlocation.longitude;
				request.created = new Date();
				
				var respuesta = RequestRESTful.createNode(parent, request);
				respuesta.then(function exito(response) {
					parent.requests.push(response.data);
					request.commit = null;

				}, function error(response) {

				})

			}

			function removeNode(parent, request) {

				var respuesta = RequestRESTful.removeNode(parent, request);
				respuesta.then(
						function exito(response) {
							parent.requests.splice(parent.requests
									.indexOf(request), 1);

						}, function error(response) {

						})

			}
			
			function locationHandler(position) {

				$scope.currentlocation = {
					latitude : position.coords.latitude,
					longitude : position.coords.longitude, 
					zoom: 12
				}


			}
			
			function getCurrentLocation(){
				LocationService.getCurrentLocation(locationHandler);

			}

		} ]);
