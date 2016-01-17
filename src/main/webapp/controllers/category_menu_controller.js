controllers.controller('CategoryMenuController', [ 'CategoryMenuService', '$scope', '$rootScope', '$location',
		function(CategoryMenuService,$scope, $rootScope, $location) {
	
	     $scope.categorymenus= {};	     

			if ($rootScope.globals.currentUser) {
				$scope.menu = true;
				fill();
				console.log($scope.categorymenus)
			} else {
				$scope.menu = false;
			}
			

			$scope.$on('handleBroadcast', function(event, args) {
				$scope.menu = args.menu;
				if($scope.menu){
					fill();
				}

			})
			
			function fill(){

				var respuesta = CategoryMenuService.getCategoryMenus();
				
				respuesta.then(function successCallback(response) {										
					$scope.categorymenus=response.data;
				}, function errorCallback(response) {
					
					if(response.status== 401){
						$location.path('#/login')
					}


				});
			}
			
			
			

		} ]);
