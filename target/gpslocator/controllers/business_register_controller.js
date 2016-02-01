controllers
		.controller(
				'BusinessRegisterController',
				[
						'$scope',
						'PlaceRESTful',
						'CredentialService',
						'$location',
						'$uibModal',
						function($scope, PlaceRESTful, CredentialService,
								$location, $uibModal) {

							$scope.steps = [
									'Step 1: Registration on Google Maps',
									'Step 2: Personal Information',
									'Step 3: Address Information',
									'Step 4: Register' ];
							$scope.selection = $scope.steps[0];
							$scope.search = {};
							$scope.business = {};
							$scope.place = {};

							$scope.categories = [ "accounting", "airport",
									"amusement_park", "aquarium",
									"art_gallery", "atm", "bakery", "bank",
									"bar", "beauty_salon", "bicycle_store",
									"book_store", "bowling_alley",
									"bus_station", "cafe", "campground",
									"car_dealer", "car_rental", "car_repair",
									"car_wash", "casino", "cemetery", "church",
									"city_hall", "clothing_store",
									"convenience_store", "courthouse",
									"dentist", "department_store", "doctor",
									"electrician", "electronics_store",
									"embassy", "establishment", "finance",
									"fire_station", "florist", "food",
									"funeral_home", "furniture_store",
									"gas_station", "general_contractor",
									"grocery_or_supermarket", "gym",
									"hair_care", "hardware_store", "health",
									"hindu_temple", "home_goods_store",
									"hospital", "insurance_agency",
									"jewelry_store", "laundry", "lawyer",
									"library", "liquor_store",
									"local_government_office", "locksmith",
									"lodging", "meal_delivery",
									"meal_takeaway", "mosque", "movie_rental",
									"movie_theater", "moving_company",
									"museum", "night_club", "painter", "park",
									"parking", "pet_store", "pharmacy",
									"physiotherapist", "place_of_worship",
									"plumber", "police", "post_office",
									"real_estate_agency", "restaurant",
									"roofing_contractor", "rv_park", "school",
									"shoe_store", "shopping_mall", "spa",
									"stadium", "storage", "store",
									"subway_station", "synagogue",
									"taxi_stand", "train_station",
									"travel_agency", "university",
									"veterinary_care", "zoo" ];

							$scope.data = {
								type : null,
								option1 : null
							};

							$scope.getCurrentStepIndex = function() {
								return _
										.indexOf($scope.steps, $scope.selection);
							};

							$scope.goToStep = function(index) {
								if (!_.isUndefined($scope.steps[index])) {
									$scope.selection = $scope.steps[index];
								}
							};

							$scope.nearbysearch = function() {

								params = {
									radius : $scope.search.radius,
									name : $scope.search.name,
									types : $scope.search.type,
									lat : $scope.LatLng.lat,
									lng : $scope.LatLng.lng

								}

								var respuesta = PlaceRESTful
										.nearSearchByFree(params);

								respuesta.then(function successCallback(
										response) {
									$scope.lista = response.data;
								}, function errorCallback(response) {
								});

							};

							$scope.savePlace = function(result) {

								$scope.place.place_id = result.place_id;
								$scope.place.type = result.types[0];
								$scope.place.name = result.name;
								$scope.LatLng.lat = result.geometry.location.lat;
								$scope.LatLng.lng = result.geometry.location.lng;
								console.log(result.place_id);

							}

							$scope.registerPlace = function() {

								$scope.place.location = {
									lat : $scope.LatLng.lat,
									lng : $scope.LatLng.lng
								}
								
								$scope.place.types=[];
								$scope.place.types.push($scope.place.type)

								var respuesta = PlaceRESTful
										.addPlace($scope.place);

								respuesta.then(function successCallback(
										response) {
									console.log(response.data);
								}, function errorCallback(response) {

								});

							}

							$scope.register = function() {

								$scope.address.place_id = $scope.place_id;

								$scope.business.addresses = [];
								$scope.address.latitude = $scope.LatLng.lat;
								$scope.address.longitude = $scope.LatLng.lng;
								$scope.address.categoryname = $scope.place.type;

								$scope.business.addresses.push($scope.address);

								$scope.business.type = 'business';

								var respuesta = CredentialService
										.Create($scope.business);

								respuesta.then(function successCallback(
										response) {
									$location.path('/login');

								}, function errorCallback(response) {

								});

							}

							// CODIGO PARA POPUP

							$scope.LatLng = {
								lat : 25.7405442,
								lng : -100.392488
							}

							$scope.animationsEnabled = true;

							$scope.open = function(size) {

								var modalInstance = $uibModal.open({
									animation : $scope.animationsEnabled,
									templateUrl : 'myModalContent.html',
									controller : 'ModalInstanceCtrl',
									size : size,
									resolve : {
										LatLng : function() {
											return $scope.LatLng;
										}
									}
								});

								modalInstance.result.then(function(LatLng) {
									console.log("resultado   111");
									console.log(LatLng);

									$scope.LatLng = LatLng;
								}, function() {
								});
							};

							$scope.toggleAnimation = function() {
								$scope.animationsEnabled = !$scope.animationsEnabled;
							};

						} ]);

controllers.controller('ModalInstanceCtrl', function($scope, $uibModalInstance,
		LatLng, NgMap) {

	$scope.LatLng = LatLng;

	$scope.ok = function() {
		$scope.LatLng = {
			lat : $scope.map.markers[0].getPosition().lat(),
			lng : $scope.map.markers[0].getPosition().lng()
		}
		$uibModalInstance.close($scope.LatLng);
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	NgMap.getMap().then(function(map) {
		$scope.map = map;
		$scope.map.markers[0].setPosition($scope.LatLng);
		$scope.map.setCenter($scope.LatLng);
		console.log($scope.map)
	});

});