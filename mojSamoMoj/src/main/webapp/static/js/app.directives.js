wafepaApp = angular.module('wafepaApp.directives', []);

wafepaApp.directive('activitiesTable', function() {
    return {
        restrict: 'E',                          			// moguće vrednosti: 'A' (attribute), 'E' (element), 'C' (class), 'M' (comment)
//        template: '<span>My span</span>',       			// HTML koji će biti renderovan (koristi se ovo ILI templateUrl, ne oba)
        templateUrl: 'static/html/activitiesTable.html',    // putanja ka fajlu koji sadrži HTML (koristi se ovo ILI template, ne oba)
        controller: 'ActivityController'              		// kontroler
    }
});

wafepaApp.directive('usersTable', function() {
    return {
        restrict: 'E',                          			// moguće vrednosti: 'A' (attribute), 'E' (element), 'C' (class), 'M' (comment)
//        template: '<span>My span</span>',       			// HTML koji će biti renderovan (koristi se ovo ILI templateUrl, ne oba)
        templateUrl: 'static/html/usersTable.html',    // putanja ka fajlu koji sadrži HTML (koristi se ovo ILI template, ne oba)
        controller: 'UserController'              		// kontroler
    }
});