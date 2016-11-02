'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {


            $scope.searchText;
            $scope.searchOptions = [{name: "VAT",opt: "vat"}, {name: "Name",opt: "name"}, {name: "Phone number",opt: "phone"}];
            $scope.searcyBy = $scope.searchOptions[0];
            
            $scope.country;



            $scope.getCompany = function () {
                console.log("inside getCompany()");


                
                $http({
                    method: 'GET',
                    skipAuthorization: true,
                    url: 'http://cvrapi.dk/api?' + $scope.searcyBy.opt + '=' + $scope.searchText + '&country=' + $scope.country

                }).then(function successCallback(res) {
                    $scope.data = res.data;


                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
                });




            };

        });