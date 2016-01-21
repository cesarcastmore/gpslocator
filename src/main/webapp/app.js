var app = angular.module('app', [ 'ngRoute', 'ngCookies', 'controllers',
		'factories', 'directives', 'services', 'uiGmapgoogle-maps',
		'angular-loading-bar', 'ngMap', 'ngFileUpload' ]);

app.config(function($routeProvider, uiGmapGoogleMapApiProvider) {

	$routeProvider.when('/home', {
		controller : 'HomeController',
		templateUrl : 'view/home_view.html',
	}).when('/address', {
		controller : 'AddressController',
		templateUrl : 'view/address_view.html',
	}).when('/profile', {
		controller : 'ProfileController',
		templateUrl : 'view/profile_view.html',
	}).when('/login', {
		controller : 'LoginController',
		templateUrl : 'view/login_view.html',
	}).when('/request/show/:requestId', {
		controller : 'RequestShowController',
		templateUrl : 'view/request_show_view.html',
	}).when('/business/info/:businessId', {
		controller : 'BusinessInfoController',
		templateUrl : 'view/business_info_view.html',
	}).when('/client/register', {
		controller : 'ClientRegisterController',
		templateUrl : 'view/client_register_view.html',
	}).when('/places', {
		controller : 'PlacesController',
		templateUrl : 'view/places_view.html',
	}).when('/business/register', {
		controller : 'BusinessRegisterController',
		templateUrl : 'view/business_register_view.html',
	}).when('/request/locate', {
		controller : 'RequestLocateController',
		templateUrl : 'view/request_locate_view.html',
	}).when('/services', {
		controller : 'ServicesController',
		templateUrl : 'view/services_view.html',
	}).when('/business/find', {
		controller : 'BusinessFindController',
		templateUrl : 'view/business_find_view.html',
	}).when('/request/edit', {
		controller : 'RequestEditController',
		templateUrl : 'view/request_edit_view.html',
	}).otherwise({
		redirectTo : '/login'
	});

	uiGmapGoogleMapApiProvider.configure({
		// key: 'your api key',
		v : '3.20', // defaults to latest 3.X anyhow
		libraries : 'weather,geometry,visualization'
	});

});

app.run(function($rootScope, $cookieStore, $http, $location) {

	$rootScope.globals = $cookieStore.get('globals') || {};

	if ($rootScope.globals.currentUser) {

		$http.defaults.headers.common['Authorization'] = 'Basic '
				+ $rootScope.globals.currentUser.authdata;
		$rootScope.$broadcast('handleBroadcast', {
			sidebar : 'session',
			menu : true
		});

	}
	if (!$rootScope.globals) {
		$location.path('#/login');
	}

	/*
	 * Receive emitted message and broadcast it. Event names must be distinct or
	 * browser will blow up!
	 */
	$rootScope.$on('handleEmit', function(event, args) {
		$rootScope.$broadcast('handleBroadcast', args);
	});
});

var controllers = angular.module('controllers', []);
var factories = angular.module('factories', []);
var directives = angular.module('directives', []);
var services = angular.module('services', []);
