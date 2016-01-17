controllers.controller('AddressController', [
		'$scope',
		'AddressService',
		'$rootScope',
		'NgMap',
		'LocationService',
		'PlaceRESTful',
		function($scope, AddressService, $rootScope, NgMap, LocationService,
				PlaceRESTful) {

			$scope.zoom = 15;

			$scope.getAddress = getAddress;

			function getAddress() {
				$scope.type = $rootScope.globals.currentUser.user.type;

				var respuesta = AddressService.getAddress();

				respuesta.then(function successCallback(response) {
					$scope.address = response.data;
					setPosition($scope.address);
					$scope.zoom = $scope.address.zoom;

				}, function errorCallback(response) {
				});
			}

			$scope.updateAddress = updateAddress;
			function updateAddress() {
				$scope.address.longitude = $scope.map.getPosition().lng();
				$scope.address.latitude = $scope.map.getPosition().lat();
				$scope.address.zoom = $scope.map.getZoom();

				console.log($scope.address);

				if (!$scope.address.addressId) {
					var respuesta = AddressService.saveAddress($scope.address);
					respuesta.then(function successCallback(response) {
						$scope.address = response.data;
					}, function errorCallback(response) {

					});
				} else {
					var respuesta = AddressService
							.updateAddress($scope.address);
					respuesta.then(function successCallback(response) {
						$scope.address = response.data;
					}, function errorCallback(response) {

					});

				}

			}

			$scope.setPosition = setPosition;
			function setPosition(address) {

				if (address.latitude) {
					latitude = parseFloat(address.latitude);
					longitude = parseFloat(address.longitude);
					$scope.location = {
						lat : latitude,
						lng : longitude
					};

				}

			}

			function locationHandler(position) {

				console.log("initialize current position");
				$scope.currentlocation = {
					latitude : position.coords.latitude,
					longitude : position.coords.longitude,
				}
			}

			$scope.getCurrentLocation = getCurrentLocation;
			function getCurrentLocation() {
				LocationService.getCurrentLocation(locationHandler);

			}

			// Esta funcion la carga al final
			NgMap.getMap().then(function(map) {
				$scope.map = map;

				if (!$scope.location) {
					latitude = $scope.currentlocation.latitude;
					longitude = $scope.currentlocation.longitude;
					$scope.location = {
						lat : latitude,
						lng : longitude
					};

				}

			});

			$scope.nearbysearch = nearbysearch;

			function nearbysearch() {

				params = {
					radius : $scope.search.radius,
					name : $scope.search.name,
					types : $scope.search.types
				}

				var respuesta = PlaceRESTful.nearSearchBy(params);

				respuesta.then(function successCallback(response) {
					$scope.lista = response.data;
				}, function errorCallback(response) {
				});

			}

			$scope.savePlace = savePlace;

			function savePlace(result) {
				$scope.location = {
					lat : result.geometry.location.lat,
					lng : result.geometry.location.lng
				};

				$scope.address.place_id = result.place_id;

			}

		} ]);