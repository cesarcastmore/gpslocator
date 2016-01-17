factories.factory('CategoryMenuService',   function ($http) {
	var service= {};
	
    service.getCategoryMenus = function() {
    	var response =$http({
            method: 'GET',
            url: 'api/categorymenus',
          })
          
          return response; 	
                   
    }
    
    service.getMenus = function(categoryMenuId) {
    	var response =$http({
            method: 'GET',
            url: 'api/categorymenus/'+ categoryMenuId + '/menus',
          })
          
          return response; 
    	
    }
        
       
    
    return service;
    
 
});
