factories.factory('UserServiceRESTful',   function ($http) {
	var service= {};
	
 
    
    service.create = function(userservice) {
    	var response =$http({
            method: 'POST',
            url: 'api/userservices',
			data : userservice
          })
          
          return response; 
    	
    }
    
    
    service.getAll = function() {
    	var response =$http({
            method: 'GET',
            url: 'api/userservices'
          })
          
          return response; 
    	
    }
    
    
    service.remove = function(userservice) {
    	var response =$http({
            method: 'DELETE',
            url: 'api/userservices/'+ userservice.userServiceId
          })
          
          return response; 
    	
    }
        
       
    
    return service;
    
 
});