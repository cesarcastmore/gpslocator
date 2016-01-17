controllers.controller('ClientRegisterController', [ 'CredentialService',
		'$location', '$scope',
		function(CredentialService, $location, $scope) {

			$scope.register = register;

			function register() {
				var respuesta = CredentialService.Create($scope.user);
				respuesta.then(function successCallback(response) {
					$location.path('/login');
				}, function errorCallback(response) {

				});
			}
		} ]);
