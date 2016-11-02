'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            $scope.isError = false;
            $scope.hasSomethingToShow = false;
            $scope.productionUnits = [];
            $scope.getCompany = function () {

                $http({
                    method: 'GET',
                    skipAuthorization: true,
                    url: 'http://cvrapi.dk/api?vat=' + $scope.cvr + '&country=dk'

                }).then(function successCallback(res) {
                    $scope.data = res.data;
                    checkForGETRequestError();
                    $scope.productionUnits = res.data.productionunits;
                    $scope.hasSomethingToShow = true;

                }, function errorCallback(res) {

                });
            };

            var checkForGETRequestError = function () {
                if ($scope.data.error === "QUOTA_EXCEEDED") {
                    $scope.errorMessage = "Your IP address or IP range has been temporarily restricted - usually until the next day. Stop posting. You have 200 free rounds per day.";
                    $scope.isError = true;
                }
                if ($scope.data.error === "BANNED") {
                    $scope.errorMessage = "Your IP address or IP range is blocked. Stop posting.";
                    $scope.isError = true;
                }
                if ($scope.data.error === "INVALID_VAT") {
                    $scope.errorMessage = "The VAT number you have turned up is not in the correct format.";
                    $scope.isError = true;
                }
                if ($scope.data.error === "NOT_FOUND") {
                    $scope.errorMessage = "Found something useful to deliver to you.";
                    $scope.isError = true;
                }
                if ($scope.data.error === "INTERNAL_ERROR") {
                    $scope.errorMessage = "There has been an error in either the call or the logging of the call. Cabinet finally contact.";
                    $scope.isError = true;
                }
                if ($scope.data.error === "INVALID_UA") {
                    $scope.errorMessage = "Please do not use the default useragent. Use useragent template that you can find here.";
                    $scope.isError = true;
                }
            };
        });