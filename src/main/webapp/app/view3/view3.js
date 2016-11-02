'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            $scope.getCompany = function () {

                $http({
                    method: 'GET',
                    url: 'http://cvrapi.dk/api?vat=' + $scope.cvr + '&country=dk'
                }).then(function successCallback(res) {
                    $scope.data = res.data.toString();
                    console.log($scope.data);
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });
            };

        });