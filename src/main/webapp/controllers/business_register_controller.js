controllers.controller('BusinessRegisterController', [
		'$scope',
		'NgMap',
		'PlaceRESTful',
		function($scope, NgMap, PlaceRESTful) {

			$scope.steps = [ 'Step 1: Personal Information',
					'Step 2: Address Information',
					'Step 3: Registration on Google Maps' ];
			$scope.selection = $scope.steps[0];
			$scope.business = {};
			$scope.search = {};

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
				$scope.map = map;

			});

			$scope.nearbysearch = function() {

				console.log("ENTRO    AQUI");

				$scope.currentlocation = {
					lat : $scope.map.markers[0].getPosition().lat(),
					lng : $scope.map.markers[0].getPosition().lng(),
				};

				params = {
					radius : $scope.search.radius,
					name : $scope.search.name,
					types : $scope.search.types,
					lat : $scope.currentlocation.lat,
					lng : $scope.currentlocation.lng

				}

				var respuesta = PlaceRESTful.nearSearchByFree(params);

				respuesta.then(function successCallback(response) {
					$scope.lista = response.data;
				}, function errorCallback(response) {
				});

			};
			
			
			
			$scope.savePlace =function(result) {
				$scope.currentlocation = {
					lat : result.geometry.location.lat,
					lng : result.geometry.location.lng
				};
				
				$scope.map.markers[0].setPosition($scope.currentlocation);
				console.log($scope.map);

				$scope.address.place_id = result.place_id;

			}

		} ]);