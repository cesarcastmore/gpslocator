controllers.controller('ProfileController', [
		'$scope',
		'UserService',
		'Upload',
		function($scope, UserService, Upload) {

			$scope.getUser = getUser;

			function getUser() {
				var respuesta = UserService.getUser();
				respuesta.then(function successCallback(response) {
					console.log("inicializa las variables");
					$scope.user = response.data;
				}, function errorCallback(response) {

				});

			}

			$scope.updateUser = updateUser;

			function updateUser() {
				var respuesta = UserService.updateUser($scope.user);
				respuesta.then(function successCallback(response) {
					$scope.user = response.data;
				}, function errorCallback(response) {

				});

			}

			$scope.upload = function(file) {
				if (typeof(file) != "undefined") {
					Upload.upload({
						url : 'api/upload/profile/image',
						data : {
							file : file
						}
					}).success(function (data, status, headers, config) {
	                    $scope.user.profileimage=data;
	                });
				}

			};
			
			$scope.$watch('file', function () {
		        $scope.upload($scope.file);
		    });

		} ]);