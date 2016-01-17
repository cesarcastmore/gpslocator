controllers.controller('RequestEditController', [
		'RequestRESTful',
		'ServiceRESTful',
		'$scope',
		'$location',
		'LocationService',
		function(RequestRESTful, ServiceRESTful, $scope, $location,
				LocationService) {

			$scope.create = create;
			$scope.getServices = getServices;
			$scope.getRequests = getRequests;
			$scope.remove = remove;
			$scope.update = update;
			$scope.find = find;
			$scope.showResponses = showResponses;
			$scope.getCurrentLocation = getCurrentLocation;


			$scope.requests = [];

			$scope.data = {
				singleSelect : null,
				option1 : null,
			};

			$scope.forceUnknownOption = function() {
				$scope.data.singleSelect = 'nonsense';
			};

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
			
			function getCurrentLocation(){
				LocationService.getCurrentLocation(locationHandler);

			}

			function create() {

				$scope.request.created = new Date();
				
				$scope.request.latitude = $scope.currentlocation.latitude;
				$scope.request.longitude = $scope.currentlocation.longitude;

				
				$scope.request.service = {
					serviceId : $scope.data.singleSelect
				}

				var respuesta = RequestRESTful.create($scope.request);
				respuesta.then(function exito(response) {

					$scope.requests.push(response.data);
					$scope.request = null;

				}, function error(response) {

				});
			}

			function getServices() {

				// Obtiene todos los servicios de la lista
				var respuesta = ServiceRESTful.getAll();
				respuesta.then(function exito(response) {
					$scope.services = response.data;
				}, function error(response) {

				});

				getRequests('all');
				console.log("entro aqui");
				getCurrentLocation();
			}

			function getRequests(para) {

				var params = {
					service : para
				};

				var respuesta = RequestRESTful.getAll(params);
				respuesta.then(function exito(response) {
					$scope.requests = [];

					if (response.data) {
						$scope.requests = response.data;
					}

				}, function error(response) {

				});

			}

			function remove(request) {

				var respuesta = RequestRESTful.remove(request.requestId);
				respuesta.then(
						function exito(response) {
							$scope.requests.splice($scope.requests
									.indexOf(request), 1);

						}, function error(response) {

						});
			}

			function update(request) {

				var respuesta = RequestRESTful.update(request);
				respuesta.then(function exito(response) {
					request.edit = false;

				}, function error(response) {

				});
			}

			function showResponses(request) {
				$location.path('/request/show/' + request.requestId);

			}

			function locationHandler(position) {

				$scope.currentlocation = {
					latitude : position.coords.latitude,
					longitude : position.coords.longitude
				}


			}

		} ]);
