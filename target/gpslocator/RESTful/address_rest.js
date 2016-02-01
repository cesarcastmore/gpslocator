factories.factory('AddressService',   function ($http) {
	var service= {};
	
    service.getAddress = function() {
    	var response =$http({
            method: 'GET',
            url: 'api/addresses'
            	})
          
          return response; 	
                   
    }
    
    service.updateAddress = function(address) {
    	var response =$http({
            method: 'PUT',
            url: 'api/addresses',
            data: address
          })
          
          return response; 	
                   
    }
    
    
    service.saveAddress = function(address) {
    	var response =$http({
            method: 'POST',
            url: 'api/addresses',
            data: address
          })
          
          return response; 	
                   
    }
       
    
    return service;
    
 
});