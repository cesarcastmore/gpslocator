controllers.controller('RequestEditController', [
		'RequestRESTful',
		'$scope',
		'$location',
		'LocationService',
		function(RequestRESTful, $scope, $location,
				LocationService) {

			$scope.create = create;
			$scope.getRequests = getRequests;
			$scope.remove = remove;
			$scope.update = update;
			$scope.find = find;
			$scope.showResponses = showResponses;
			$scope.getCurrentLocation = getCurrentLocation;

			$scope.categories = [ "accounting", "airport", "amusement_park",
					"aquarium", "art_gallery", "atm", "bakery", "bank", "bar",
					"beauty_salon", "bicycle_store", "book_store",
					"bowling_alley", "bus_station", "cafe", "campground",
					"car_dealer", "car_rental", "car_repair", "car_wash",
					"casino", "cemetery", "church", "city_hall",
					"clothing_store", "convenience_store", "courthouse",
					"dentist", "department_store", "doctor", "electrician",
					"electronics_store", "embassy", "establishment", "finance",
					"fire_station", "florist", "food", "funeral_home",
					"furniture_store", "gas_station", "general_contractor",
					"grocery_or_supermarket", "gym", "hair_care",
					"hardware_store", "health", "hindu_temple",
					"home_goods_store", "hospital", "insurance_agency",
					"jewelry_store", "laundry", "lawyer", "library",
					"liquor_store", "local_government_office", "locksmith",
					"lodging", "meal_delivery", "meal_takeaway", "mosque",
					"movie_rental", "movie_theater", "moving_company",
					"museum", "night_club", "painter", "park", "parking",
					"pet_store", "pharmacy", "physiotherapist",
					"place_of_worship", "plumber", "police", "post_office",
					"real_estate_agency", "restaurant", "roofing_contractor",
					"rv_park", "school", "shoe_store", "shopping_mall", "spa",
					"stadium", "storage", "store", "subway_station",
					"synagogue", "taxi_stand", "train_station",
					"travel_agency", "university", "veterinary_care", "zoo" ];

			$scope.requests = [];

			$scope.data = {
				singleSelect : null,
				option1 : null,
			};

			$scope.forceUnknownOption = function() {
				$scope.data.singleSelect = 'nonsense';
			};

			function find() {
				params = {
					findBy : $scope.findby
				};

				var respuesta = RequestRESTful.getAll(params);
				respuesta.then(function exito(response) {
					$scope.requests = response.data;

				}, function error(response) {

				});

			}

			function getCurrentLocation() {

				LocationService.getCurrentLocation(locationHandler);

			}

			function create() {

				$scope.request.created = new Date();
				$scope.request.latitude = $scope.currentlocation.latitude;
				$scope.request.longitude = $scope.currentlocation.longitude;
				$scope.request.categoryname= $scope.data.singleSelect;
				

				var respuesta = RequestRESTful.create($scope.request);
				respuesta.then(function exito(response) {

					$scope.requests.push(response.data);
					$scope.request = null;

				}, function error(response) {

				});
			}


			function getRequests(para) {

				var params = {
					service : para
				};

				var respuesta = RequestRESTful.getAll(params);
				respuesta.then(function exito(response) {
					$scope.requests = [];

					if (response.data) {
						getCurrentLocation();
						$scope.requests = response.data;
					}

				}, function error(response) {

				});

			}

			function remove(request) {

				var respuesta = RequestRESTful.remove(request.requestId);
				respuesta.then(
						function exito(response) {
							$scope.requests.splice($scope.requests
									.indexOf(request), 1);

						}, function error(response) {

						});
			}

			function update(request) {

				var respuesta = RequestRESTful.update(request);
				respuesta.then(function exito(response) {
					request.edit = false;

				}, function error(response) {

				});
			}

			function showResponses(request) {
				$location.path('/request/show/' + request.requestId);

			}

			function locationHandler(position) {

				$scope.currentlocation = {
					latitude : position.coords.latitude,
					longitude : position.coords.longitude
				}

			}

		} ]);
