'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'app/view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($http, $scope) {
            
            
            $scope.cvr;
    
            $scope.getCompany = function () {
                $http({
                    method: 'GET',
                    skipAuthorization: true,
                    url: 'http://cvrapi.dk/api?vat=' + $scope.cvr + '&country=dk'
                }).then(function successCallback(res) {

                    $scope.data = res.data.toString();
                    console.log($scope.data);

                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });
            };


        });
