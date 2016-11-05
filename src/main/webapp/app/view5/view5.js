'use strict';

angular.module('myApp.view5', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view5', {
                    templateUrl: 'app/view5/view5.html',
                    controller: 'View5Ctrl'

                });
            }])
        .controller('View5Ctrl', function ($scope, $http) {
            $scope.users;
    
            $http({
                method: 'GET',
                url: "api/admin/users"

            }).then(function successCallback(res) {
                $scope.users = res.data;

            }, function errorCallback(res) {
                $scope.error = res.status + ": " + res.data.statusText;
            });


            $scope.deleteUser = function(username) {
                var response = $http.get('api/admin/user/' +username);
                response.success(function() {
                    alert("User deleted.");
                });
            };
        });