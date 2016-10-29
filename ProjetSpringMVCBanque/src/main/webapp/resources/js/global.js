/**
 * Global js file
 */

$(document).ready(function () {
	/*
	 * add CSRF token 
	 */
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader("X-CSRF-Token", csrf_token);
      });
    
    $.ajaxSetup({
    	  headers: {
    		  'X-CSRF-Token': csrf_token
    	  }
    	});
    
});