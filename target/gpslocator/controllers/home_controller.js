controllers.controller('HomeController', [ '$rootScope', '$scope', '$location', 'UserService',
		function($rootScope, $scope, $location, UserService) {

	        console.log("entro a HomerController");
	        
	        var respuesta = UserService.getUser();
	        respuesta.then(function successCallback(response) {
		        $scope.user = response.data;
			}, function errorCallback(response) {
				$location.path('/login');
			})

			  

		} ]);