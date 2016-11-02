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
            function getCompany(cvr) {
                $http({
                    method: 'GET',
                    url: 'http://cvrapi.dk/api?vat=' + cvr + '&country=dk',
                    skipAuthorization: true
                }).then(function successCallback(res) {
                   
                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });
            }


        });