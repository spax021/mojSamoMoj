wafepaApp = angular.module('wafepaApp.routes', ['ngRoute']);

wafepaApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'static/html/home.html'
	})
	.when('/activities', {
		templateUrl : 'static/html/activities.html',
		controller : 'ActivityController'
	})
	.when('/activities/add', {
		templateUrl : 'static/html/addEditActivity.html',
		controller : 'ActivityController'
	})
	.when('/activities/edit/:id', {
		templateUrl : 'static/html/addEditActivity.html',
		controller : 'ActivityController'
	})
	.when('/activities/view/:id', {
		templateUrl : 'static/html/viewActivity.html',
		controller : 'ActivityController'
	})
	.when('/activities/search/:name', {
		templateUrl : 'static/html/searchActivity.html',
		controller : 'ActivityController'
	})
	
	.when('/users', {
		templateUrl : 'static/html/users.html',
		controller : 'UserController'
	})
	.when('/users/add', {
		templateUrl : 'static/html/addEditUser.html',
		controller : 'UserController'
	})
	.when('/users/edit/:id', {
		templateUrl : 'static/html/addEditUser.html',
		controller : 'UserController'
	})
	.when('/users/view/:id', {
		templateUrl : 'static/html/viewUser.html',
		controller : 'UserController'
	})
	
	.when('/logs', {
            templateUrl: 'static/html/logs.html',
            controller: 'LogController'
    })
	.when('/logs/add', {
		templateUrl : 'static/html/addEditLog.html',
		controller : 'LogController'
	})
	.when('/activities/:activityId/logs', {
		templateUrl : 'static/html/logs.html',
		controller : 'LogController'
	})
	.when('/users/:userId/logs', {
		templateUrl : 'static/html/logsUser.html',
		controller : 'LogController'
	})
	.when('/omdb', {
		templateUrl : 'static/html/omdb.html',
		controller : 'MovieController'
	})
	.otherwise({
		redirectTo : '/'
	});
} ]);