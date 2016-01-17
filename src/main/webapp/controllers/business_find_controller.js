controllers.controller('BusinessFindController', [
		'$scope',
		'PlaceRESTful',
		'$location',
		function($scope, PlaceRESTful, $location) {

			$scope.nearbysearch = nearbysearch;

			function nearbysearch() {

				params = {
					radius : $scope.search.radius,
					name : $scope.search.name,
					types : $scope.search.types
				}

				var respuesta = PlaceRESTful.nearSearchBy(params);

				respuesta.then(function successCallback(response) {
					console.log(response.data);

					$scope.lista = response.data;
				}, function errorCallback(response) {
				});

			}

			$scope.showInfo = showInfo;

			function showInfo(placeid) {
				console.log(placeid)
				var respuesta = PlaceRESTful.getBusiness(placeid);

				respuesta.then(
						function successCallback(response) {
							;

							console.log(response.data);
							$scope.business = response.data;

							$location.path('/business/info/'
									+ $scope.business.userId);

						}, function errorCallback(response) {
						});

			}

		} ]);
