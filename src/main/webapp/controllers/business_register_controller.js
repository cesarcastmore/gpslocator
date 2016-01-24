controllers.controller('BusinessRegisterController', [
		'$scope',
		'NgMap',
		'PlaceRESTful',
		'CredentialService',
		'$location',
		function($scope, NgMap, PlaceRESTful,
				CredentialService, $location) {

			$scope.steps = [ 'Step 1: Registration on Google Maps',
					'Step 2: Personal Information',
					'Step 3: Address Information', 'Step 4: Register' ];
			$scope.selection = $scope.steps[0];
			$scope.business = {};
			$scope.address = {};
			$scope.search = {};
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

			$scope.data = {
				type : null,
				option1 : null
			};


			$scope.getCurrentStepIndex = function() {
				// Get the index of the current step given selection
				return _.indexOf($scope.steps, $scope.selection);
			};

			// Go to a defined step index
			$scope.goToStep = function(index) {
				if (!_.isUndefined($scope.steps[index])) {
					$scope.selection = $scope.steps[index];
				}
			};

			NgMap.getMap().then(function(map) {
				console.log("ENTRO AQUI A REVISAR EL MAP");
				$scope.map = map;

			});

			$scope.nearbysearch = function() {

				params = {
					radius : $scope.search.radius,
					name : $scope.search.name,
					types : $scope.search.types,
					lat : $scope.map.markers[0].getPosition().lat(),
					lng : $scope.map.markers[0].getPosition().lng()

				}

				var respuesta = PlaceRESTful.nearSearchByFree(params);

				respuesta.then(function successCallback(response) {
					$scope.lista = response.data;
				}, function errorCallback(response) {
				});

			};

			$scope.savePlace = function(result) {
				
				$scope.currentlocation = {
					lat : result.geometry.location.lat,
					lng : result.geometry.location.lng
				};
				
				console.log(".................................");
				console.log(result);

				$scope.map.markers[0].setPosition($scope.currentlocation);
				$scope.map.setCenter($scope.currentlocation);
				$scope.place_id = result.place_id;
				$scope.data.type = result.types[0];
				$scope.business.name= result.name;

			}

			$scope.register = function() {

				$scope.address.place_id = $scope.place_id;

				$scope.business.addresses = [];
				$scope.address.latitude = $scope.currentlocation.lat;
				$scope.address.longitude = $scope.currentlocation.lng;
				$scope.address.categoryname = $scope.data.type;

				$scope.business.addresses.push($scope.address);

				$scope.business.type = 'business';

				var respuesta = CredentialService.Create($scope.business);

				respuesta.then(function successCallback(response) {
					console.log("EXITOSAMENTE CREO EL USUARIO");
					$location.path('/login');

				}, function errorCallback(response) {

				});

				console.log($scope.business);

			}

		} ]);