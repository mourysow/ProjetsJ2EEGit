var app = angular.module('statelessApp', []).factory('TokenStorage', function() {
	var storageKey = 'auth_token';
	return {		
		store : function(token) {
			return localStorage.setItem(storageKey, token);
		},
		retrieve : function() {
			return localStorage.getItem(storageKey);
		},
		clear : function() {
			return localStorage.removeItem(storageKey);
		}
	};
}).factory('TokenAuthInterceptor', function($q, TokenStorage) {
	return {
		request: function(config) {
			var authToken = TokenStorage.retrieve();
			if (authToken) {
				config.headers['X-AUTH-TOKEN'] = authToken;
			}
			return config;
		},
		responseError: function(error) {
			if (error.status === 401 || error.status === 403) {
				TokenStorage.clear();
			}
			return $q.reject(error);
		}
	};
}).config(function($httpProvider) {
	$httpProvider.interceptors.push('TokenAuthInterceptor');
});

app.controller('AuthCtrl', function ($scope, $http, TokenStorage) {
	$scope.authenticated = false;
	$scope.token; // For display purposes only
	$scope.services;
	
	$scope.init = function () {
		$http.get('/bp/api/users/current').success(function (user) {
			if(user.uuid !== 'anonymousUser'){
				$scope.authenticated = true;
				$scope.uuid = user.uuid;
				
				// For display purposes only
				$scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
			}
		});
		
		$scope.loadServices();
	};

	$scope.login = function () {
		$http.post('/bp/api/login', { uuid:'uuid',anonymousUser:'true', userLogin: $scope.userLogin, password: $scope.password }).success(function (result, status, headers) {
			$scope.authenticated = true;
			TokenStorage.store(headers('X-AUTH-TOKEN'));
			
			// For display purposes only
			$scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
			$scope.loadServices();
		});  
	};

	$scope.loadServices = function(){
		$scope.results = [];
		[
		 
		 '/web/sites/1/services',
		 '/web/sites/1/actus',
		 '/web/sites/1/informations',
		 '/web/sites/1/informations',
		 '/web/sites/1/numerosUtiles',
		 '/web/sites/SQYPARK',
		 '/web/sites/2/services',
		 '/web/sites/2/actus',
		 '/web/sites/2/informations',
		 '/web/sites/2/informations',
		 '/web/sites/2/numerosUtiles',
		 '/web/sites/EVERGREEN',

		 ].forEach(function(route, i) {
			$http.get(route).success(function (result) {
				$scope.results.push({index: i, route: route, result: result});
			});
		});
	};
	
	$scope.logout = function () {
		// Just clear the local storage
		TokenStorage.clear();
		$scope.authenticated = false;
		$scope.loadServices();
	};
});