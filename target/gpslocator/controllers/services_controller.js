controllers.controller('ServicesController', [
		'$scope',
		'ServiceRESTful',
		'UserServiceRESTful',
		function($scope, ServiceRESTful, UserServiceRESTful) {

			$scope.getAll = getAll;
			$scope.addService = addService;
			$scope.removeService = removeService;

			$scope.userservices = [];
			$scope.services = [];

			$scope.data = {
				singleSelect : null,
				option1 : null
			};

			function addService() {

				var service = JSON.parse($scope.data.singleSelect);
				var userservice = {
					service : service
				};
				var respuesta = UserServiceRESTful.create(userservice);
				respuesta.then(function exito(response) {
					$scope.userservices.push(response.data);
				}, function error(response) {

				});

			}

			function removeService(userservice) {
				console.log(userservice);
				var respuesta = UserServiceRESTful.remove(userservice);
				respuesta.then(function exito(response) {
					$scope.userservices.splice($scope.userservices
							.indexOf(userservice), 1);
				}, function error(response) {

				});

			}

			function getAll(para) {

				var respuesta = ServiceRESTful.getAll();
				respuesta.then(function exito(response) {

					$scope.services = response.data;

				}, function error(response) {

				});

				respuesta = UserServiceRESTful.getAll();
				respuesta.then(function exito(response) {

					$scope.userservices = response.data;

				}, function error(response) {

				});

			}

		} ]);