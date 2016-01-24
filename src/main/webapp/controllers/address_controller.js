controllers.controller('AddressController', [
		'$scope',
		'AddressService',
		'$rootScope',
		'NgMap',
		function($scope, AddressService, $rootScope, NgMap) {

			$scope.getAddress = getAddress;
			$scope.type = $rootScope.globals.currentUser.user.type;
			
			
			$scope.initBusiness= initBusiness;
			function initBusiness(){
				if($scope.type == 'business'){
					getAddress();
				}
			}

			function getAddress() {

				var respuesta = AddressService.getAddress();

				respuesta.then(function successCallback(response) {

					$scope.address = response.data;

					if ($scope.address.latitude && $scope.type == 'client') {

						$scope.map.setCenter({
							lat : $scope.address.latitude,
							lng : $scope.address.longitude
						});

						$scope.map.setZoom($scope.address.zoom);

						$scope.map.markers[0].setPosition({
							lat : $scope.address.latitude,
							lng : $scope.address.longitude
						});

					}

				}, function errorCallback(response) {
				});
			}

			$scope.updateAddress = updateAddress;
			function updateAddress() {

				if ($scope.type == 'client') {
					$scope.address.latitude = $scope.map.markers[0].position
							.lat();
					$scope.address.longitude = $scope.map.markers[0].position
							.lng();
					$scope.address.zoom = $scope.map.zoom;
				}

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

			NgMap.getMap().then(function(map) {
				$scope.map = map;
				console.log("inicitaliza GETMAP");
				getAddress();

			});

		} ]);