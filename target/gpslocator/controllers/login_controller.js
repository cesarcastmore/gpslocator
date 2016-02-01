controllers.controller('LoginController', [
		'$location',
		'AuthenticationService',
		'$scope',
		function($location, AuthenticationService, $scope) {

			(function initController() {
				$scope.$emit('handleEmit', {
					sidebar : 'login',
					menu : false
				});
				AuthenticationService.clearCredentials();
			})();
			$scope.login = login;
			function login() {
				var respuesta = AuthenticationService.auth(
						$scope.user.username, $scope.user.password);

				respuesta.then(function exito(response) {
					
					AuthenticationService.setCredentials($scope.user.username,
							$scope.user.password, response.data);

					$scope.$emit('handleEmit', {
						sidebar : 'session',
						menu : 'menu'
					});
					$location.path('/home');

				}, function error(response) {

				});

			}

		} ]);
