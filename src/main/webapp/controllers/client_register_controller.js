controllers.controller('ClientRegisterController', [ 'CredentialService',
		'$location', '$scope',
		function(CredentialService, $location, $scope) {

			$scope.register = register;

			function register() {
				$scope.user.type='client';
				var respuesta = CredentialService.Create($scope.user);
				respuesta.then(function successCallback(response) {
					$location.path('/login');
				}, function errorCallback(response) {

				});
			}
		} ]);
