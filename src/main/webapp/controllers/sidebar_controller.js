controllers.controller('SideBarController', [ '$scope', '$rootScope',
		function($scope, $rootScope) {

			if ($rootScope.globals) {
				$scope.sidebar = 'session';

			} else {
				$scope.sidebar = 'login';
			}
			

			$scope.$on('handleBroadcast', function(event, args) {
				$scope.sidebar = args.sidebar;
			})

		} ]);
