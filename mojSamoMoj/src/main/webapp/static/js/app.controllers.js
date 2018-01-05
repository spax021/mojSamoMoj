wafepaApp = angular.module('wafepaApp.controllers', []);

wafepaApp.controller('ActivityController', function($scope, $location, $routeParams, activityRestService) {

	$scope.page = 0;
	// prvobitnavrednost po stranici, taj index se uvek salje "per-page"
	$scope.indexStranice = 5;
	// vrednost koju cu slati iz browser-a
	$scope.stranica = 5;
	
	$scope.showHidden = false;

	$scope.getActivities = function() {
		var request_params = {}
		if($scope.search){
			request_params['name'] = $scope.search;
		}
//        nikolin kod
		if($scope.showHidden === false){
			request_params['showHidden'] = $scope.showHidden;
        }
// ako sam menjao vrednost po stranici, onda ce se pozivati ovaj if
		if($scope.indexStranice != $scope.stranica){
// vracam stranicu na prvu tj. nultu
			$scope.page = 0;
// ta stranica dobija vrednost koju sam uneo
			$scope.indexStranice = $scope.stranica;
// taj perPage se salje na backend, mora da se poklapa sa onim tamo
			request_params['perPage'] = $scope.indexStranice
		}
		else{
			request_params['perPage'] = $scope.indexStranice
		}
		request_params['page'] = $scope.page;
		
		activityRestService.getActivities(request_params)
			.success(function(data, status, headers) {
				$scope.activities = data;
				$scope.successMessage = 'Sve je u redu sa aktivnostima.';
				
				$scope.totalPages = headers()['total-pages']
				$scope.totalElements = headers()['total-elements']
		})
			.error(function() {
				$scope.errorMessage = 'Oops, nesto ba ne valja sa aktivnostima!';
		});
	};

	$scope.deleteActivity = function(id, index) {
		activityRestService.deleteActivity(id)
		.success(function(){
			$scope.activities.splice(index, 1);
			
		})
		.error(function(){
			
		});
	};

	$scope.initActivity = function (){
		$scope.activity = {};
		
		if($routeParams && $routeParams.id){
			activityRestService.getActivity($routeParams.id)
			.success(function(data){
				$scope.activity = data;
			})
		}
	};

	$scope.saveActivity = function() {
		activityRestService.saveActivity($scope.activity)
			.success(function() {
			$location.path('/activities');
		});
	};
	
    //no need to pass id
    $scope.toggleHideActivity = function (index) {
        var activity = $scope.activities[index];
        activity.hidden === true ? activity.hidden = false : activity.hidden = true;
        /*if (activity.hidden === true) {
         activity.hidden = false;
         }
         else {
         activity.hidden = true;
         }*/
        activityRestService.saveActivity(activity)
            .success(function () {
                //no need to check if activity is hidden, it can't be shown if showHidden is turned off
                if ($scope.showHidden === false) {
                    $scope.activities.splice(index, 1);
                }
            })
            .error(function () {

            });
    };
	
	$scope.changePage = function(page){
		$scope.page = page;
		$scope.getActivities();
	}
	
});


wafepaApp.controller('LogController', function($scope, $http, $location, $routeParams){
	
	$scope.page = 10;
	$scope.pageSize = 5;

	$scope.initLog = function(){
		$scope.log = {};
		
		var request_params = {};
		request_params['page'] = $scope.page;
		request_params['pageSize'] = $scope.pageSize;
		
		$http.get('api/activities', { params : request_params })
			.success(function(data){
				$scope.activities = data;
			});
		$http.get('api/users', { params : request_params })
			.success(function(data){
				$scope.users = data;
			});
	};
	
	$scope.saveLog = function(){
		$scope.log.activity = $scope.activitySelected;
		$scope.log.user = $scope.userSelected;
		$http.post('api/logs', $scope.log)
			.success(function(){
				$location.path('/');
			});
	};
	
	$scope.getLogs = function(){
		var request_params = {};
		request_params['page'] = $scope.page;
		request_params['pageSize'] = $scope.pageSize;		
		if($routeParams && ($routeParams.activityId || $routeParams.userId)){
			request_params = {};
			request_params['activityId'] = $routeParams.activityId;
			request_params['userId'] = $routeParams.userId;
		}
		$http.get('api/logs', { params : request_params })
		.success(function(data, status, headers){
			$scope.logs = data;
			$scope.successMessage = 'Everything is ok.';
			$scope.totalPages = headers()['total-pages'];
		})
		.error(function() {
			$scope.errorMessage = 'Oops, nesto ba nevalja!';
		});
	};
	
});


wafepaApp.controller('UserController', function($scope, $http, $location, $routeParams){

	$scope.page = 0;
	$scope.indexStranice = 5;
	$scope.stranica = 5;

	$scope.getUsers = function(){
		var request_params = {}
		if($scope.search){
			request_params['email'] = $scope.search;
		}
		if($scope.indexStranice != $scope.stranica){
			$scope.page = 0;
			$scope.indexStranice = $scope.stranica;
			request_params['perPage'] = $scope.indexStranice
		}
		else{
			request_params['perPage'] = $scope.indexStranice
		}
		request_params['page'] = $scope.page;
		 					
		$http.get('api/users', { params : request_params })
			.success(function(data, status, headers){
				$scope.users = data;
				$scope.successMessage = 'Sve je u redu sa juserima.';
				
				$scope.totalPages = headers()['total-pages']
				$scope.totalElements = headers()['total-elements']
			})
			.error(function(){
				$scope.errorMessage = 'Oops, nesto ba ne valja sa juserima!';
			});
	};
	
	$scope.deleteUser = function(id, index){
		$http.delete('api/users/' + id)
			.success(function(){
				$scope.users.splice(index, 1);
			})
			.error(function(){
				
			});
		};
		
		$scope.initUser = function(){
			$scope.user = {};
			
			if($routeParams && $routeParams.id){
				$http.get('api/users/' + $routeParams.id)
					.success(function(data){
						$scope.user = data;
					})
			}
		};
		
		$scope.saveUser = function(){
			if($scope.user.id){
				$http.put('api/users/' + $scope.user.id, $scope.user)
					.success(function(){
						$location.path('/users');
					});
			}else{
				$http.post('api/users', $scope.user)
					.success(function(){
						$location.path('/users');
					});
			};
		};
		
		$scope.changePage = function(page){
			$scope.page = page;
			$scope.getUsers();
		}
});

wafepaApp.controller('MovieController', function($scope, $http){
	$scope.getMovie = function(){
		$http.get('http://www.omdbapi.com/?t=' + $scope.title + '&r=json')
			.success(function(data){
				$scope.movie = data;
				$scope.successMessage = 'Sve je OK.';
			});
		};
	
	});