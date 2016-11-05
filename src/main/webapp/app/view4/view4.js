'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'

                });
            }])
        .controller('View4Ctrl', function ($scope, $http) {
            $scope.rates = [];
    
            $scope.getDailyRates = function () {
                $http({
                    method: 'GET',
                    skipAuthorization: true,
                    url: 'api/currency/dailyrates'
                }).then(function successCallback(res) {
                    $scope.rates = res.data;
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });
            };

            $scope.convert = function () {

            };

        });
