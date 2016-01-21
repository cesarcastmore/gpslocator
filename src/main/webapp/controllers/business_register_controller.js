controllers.controller('BusinessRegisterController', [
		'$scope',
		'NgMap',
		'PlaceRESTful',
		'ServiceRESTful', 'CredentialService',
		function($scope, NgMap, PlaceRESTful, ServiceRESTful, CredentialService) {

			$scope.steps = [ 'Step 1: Registration on Google Maps',
					'Step 2: Personal Information',
					'Step 3: Address Information', 'Step 4: Register' ];
			$scope.selection = $scope.steps[0];
			$scope.business = {};
			$scope.address = {};
			$scope.search= {};

			$scope.data = {
				singleSelect : null,
				option1 : null
			};

			$scope.initServices = function(para) {

				var respuesta = ServiceRESTful.getAll();
				respuesta.then(function exito(response) {

					$scope.services = response.data;

				}, function error(response) {

				});

			};

			$scope.getCurrentStepIndex = function() {
				// Get the index of the current step given selection
				return _.indexOf($scope.steps, $scope.selection);
			};

			// Go to a defined step index
			$scope.goToStep = function(index) {
				if (!_.isUndefined($scope.steps[index])) {
					$scope.selection = $scope.steps[index];
				}
			};

			NgMap.getMap().then(function(map) {
				console.log("ENTRO AQUI A REVISAR EL MAP");
				$scope.map = map;

			});

			$scope.nearbysearch = function() {

				params = {
					radius : $scope.search.radius,
					name : $scope.search.name,
					types : $scope.search.types,
					lat : $scope.map.markers[0].getPosition().lat(),
					lng : $scope.map.markers[0].getPosition().lng()

				}

				var respuesta = PlaceRESTful.nearSearchByFree(params);

				respuesta.then(function successCallback(response) {
					$scope.lista = response.data;
				}, function errorCallback(response) {
				});

			};

			$scope.savePlace = function(result) {;
				$scope.currentlocation = {
					lat : result.geometry.location.lat,
					lng : result.geometry.location.lng
				};

				$scope.map.markers[0].setPosition($scope.currentlocation);
				$scope.map.setCenter($scope.currentlocation);
				$scope.place_id= result.place_id;

			}

			$scope.register = function() {
				
				$scope.address.place_id=$scope.place_id;
				
				$scope.business.addresses=[];
				$scope.address.latitude = $scope.currentlocation.lat;
				$scope.address.longitude = $scope.currentlocation.lng;

				
				$scope.business.addresses.push($scope.address);
				
				$scope.business.type='business';
				
				var respuesta = CredentialService.Create($scope.business);
				
				respuesta.then(function successCallback(response) {
					console.log("EXITOSAMENTE CREO EL USUARIO");
				}, function errorCallback(response) {

				});
				
				
				console.log($scope.business);

			}

		} ]);