'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'

                });
            }])
        .controller('View4Ctrl', function ($scope, $http) {
            $scope.$on('$viewContentLoaded', function () {
                $http({
                    method: 'GET',
                    url: 'api/currency/dailyrates'
                }).then(function successCallback(res) {
                    console.log(res.data);
                    $scope.response = res.data.map.currencies.myArrayList;
                    console.log($scope.response);
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });

            });
            $scope.convert = function () {
                console.log($scope.selectedCurrency);
                console.log($scope.desiredCurrency);
                console.log($scope.fromAmount);
                $http({
                    method: 'GET',
                    url: 'api/currency/calculator/' + $scope.fromAmount + '/' + $scope.selectedCurrency + '/' + $scope.desiredCurrency
                }).then(function successCallback(res) {
                    $scope.result = res.data.map.result;
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;

                });
            };

        });
