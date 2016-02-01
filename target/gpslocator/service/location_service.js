/*
 * 
 *
 * https://forum.ionicframework.com/t/geolocation-service-is-not-working-without-a-hard-refresh/2003
 * 
 * 
 */
factories.factory('LocationService',  function() {
    
   var service={};
   service.getCurrentLocation= getCurrentLocation;
   
   
   return service;
   
   function getCurrentLocation(locationHandler){
	   navigator.geolocation.getCurrentPosition(locationHandler);
   }
   
 
        
     
});


