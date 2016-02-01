factories.factory('UserService',   function ($http) {
	var service= {};
	
    service.getUser = function() {
    	var response =$http({
            method: 'GET',
            url: 'api/users'
            	})
          
          return response; 	
                   
    }
    
    service.updateUser = function(user) {
    	var response =$http({
            method: 'PUT',
            url: 'api/users',
            data: user,
            cache: true
          })
          
          return response; 	
                   
    }
       
    
    return service;
    
 
});
