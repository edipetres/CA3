'use strict';

angular.module('myApp.view3', ['ngRoute', 'ui.bootstrap'])

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

            $scope.searchText = "31678021";
            $scope.searchOptions = [{name: "VAT", opt: "vat"}, {name: "Name", opt: "name"}, {name: "Phone number", opt: "phone"}];
            $scope.searcyBy = $scope.searchOptions[1];
            console.log($scope.searchOptions[1]);
            $scope.country = "dk";

            //These code is for testing with local data
//            $scope.data = '{"vat":31678021,"name":"EA Copenhagen Business","address":"Landem\u00e6rket 11, 5","zipcode":"1119","city":"K\u00f8benhavn K","cityname":null,"protected":false,"phone":"36154500","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"01\/10 - 2008","enddate":null,"employees":"200-499","addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau","companycode":280,"companydesc":"\u00d8vrige virksomhedsformer","creditstartdate":null,"creditbankrupt":false,"creditstatus":null,"owners":null,"productionunits":[{"pno":1015222219,"main":false,"name":"EA CBA Nordsj\u00e6lland","address":"Milnersvej 48","zipcode":"3400","city":"Hiller\u00f8d","cityname":null,"protected":false,"phone":null,"email":null,"fax":null,"startdate":"27\/04 - 2009","enddate":"27\/04 - 2009","employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1015210865,"main":false,"name":"Laborantskolen","address":"Milnersvej 35","zipcode":"3400","city":"Hiller\u00f8d","cityname":null,"protected":false,"phone":null,"email":null,"fax":null,"startdate":"20\/04 - 2009","enddate":"20\/04 - 2009","employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1018679732,"main":false,"name":"Cphbusiness Lyngby (N\u00f8rgaardsvej)","address":"N\u00f8rgaardsvej 30","zipcode":"2800","city":"Kgs. Lyngby","cityname":null,"protected":false,"phone":"36154505","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"12\/08 - 2013","enddate":"12\/08 - 2013","employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1018679740,"main":false,"name":"Cphbusiness Lyngby (Lundtoftevej)","address":"Lundtoftevej 93","zipcode":"2800","city":"Kgs. Lyngby","cityname":null,"protected":false,"phone":"36154505","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"12\/08 - 2013","enddate":"31\/08 - 2014","employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1014765472,"main":true,"name":"Copenhagen Business Academy","address":"Landem\u00e6rket 11, 5","zipcode":"1119","city":"K\u00f8benhavn K","cityname":null,"protected":false,"phone":"36154500","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"01\/10 - 2008","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1015222235,"main":false,"name":"Cphbusiness N\u00f8rrebro","address":"Bl\u00e5g\u00e5rdsgade 23B","zipcode":"2200","city":"K\u00f8benhavn N","cityname":null,"protected":false,"phone":"36154500","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"27\/04 - 2009","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1015222200,"main":false,"name":"Cphbusiness S\u00f8erne","address":"Nansensgade 19","zipcode":"1366","city":"K\u00f8benhavn K","cityname":null,"protected":false,"phone":"36154513","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"27\/04 - 2009","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1019598469,"main":false,"name":"Cphbusiness Partner","address":"Nansensgade 19","zipcode":"1366","city":"K\u00f8benhavn K","cityname":null,"protected":false,"phone":"36154500","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"01\/08 - 2014","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1015222189,"main":false,"name":"Cphbusiness Lyngby","address":"N\u00f8rgaardsvej 30","zipcode":"2800","city":"Kgs. Lyngby","cityname":null,"protected":false,"phone":"36154504","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"27\/04 - 2009","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1015222227,"main":false,"name":"Cphbusiness City","address":"Landem\u00e6rket 11","zipcode":"1119","city":"K\u00f8benhavn K","cityname":null,"protected":false,"phone":"36154512","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"27\/04 - 2009","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1018494805,"main":false,"name":"Smart Learning","address":"Nansensgade 19","zipcode":"1366","city":"K\u00f8benhavn K","cityname":null,"protected":false,"phone":"36154509","email":"info@smartlearning.dk","fax":null,"startdate":"17\/05 - 2013","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"},{"pno":1015222197,"main":false,"name":"Cphbusiness Laboratorie og Milj\u00f8","address":"Peder Oxes Alle 4","zipcode":"3400","city":"Hiller\u00f8d","cityname":null,"protected":false,"phone":"36154516","email":"kontakt@cphbusiness.dk","fax":null,"startdate":"27\/04 - 2009","enddate":null,"employees":null,"addressco":null,"industrycode":854100,"industrydesc":"Videreg\u00e5ende uddannelser ikke p\u00e5 universitetsniveau"}],"t":100,"version":6}';
//            //checkForGETRequestError();
//            var jsonobj = JSON.parse($scope.data);
//            $scope.info = jsonobj;
//            $scope.productionUnits = jsonobj.productionunits;
//            $scope.hasSomethingToShow = true;


            $scope.getCompany = function () {



                $http({
                    method: 'GET',
                    skipAuthorization: true,
                    url: 'http://cvrapi.dk/api?' + $scope.searcyBy.opt + '=' + $scope.searchText + '&country=' + $scope.country

                }).then(function successCallback(res) {
                    $scope.data = res.data;
                    checkForGETRequestError();
                    $scope.info = res.data;
                    $scope.productionUnits = res.data.productionunits;
                    $scope.hasSomethingToShow = true;

                }, function errorCallback(res) {
                    $scope.error = res.status + ": " + res.data.statusText;
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
