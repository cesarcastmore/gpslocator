controllers.controller('BusinessInfoController', [
		'$scope',
		'$routeParams',
		'BusinessRESTful',
		function($scope, $routeParams, BusinessRESTful) {
			
			$scope.tab=1;

			$scope.enableAddress = enableAddress;
			$scope.enableDirection = enableDirection;
			$scope.getBusiness = getBusiness;
			$scope.getAddress = getAddress;			
			$scope.destination = {};
			
			
			function enableAddress(){
				$scope.tab=2;

				
			}
			
			function enableDirection(){
				$scope.tab=1;
			}

			
			function getBusiness() {

				var respuesta = BusinessRESTful.get($routeParams.businessId);
				respuesta.then(function exito(response) {

					$scope.business = response.data;
					
					getAddress();
					
				}, function error(response) {

				})

			}

			
			function getAddress() {

				var respuesta = BusinessRESTful.getAddress($scope.business);
				respuesta.then(function exito(response) {
					$scope.business.address = response.data;
					$scope.destination={
							lat: $scope.business.address.latitude,
							lng: $scope.business.address.longitude
					}
					

				}, function error(response) {

				})

			}



		} ]);