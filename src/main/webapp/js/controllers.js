(function (angular) {
    "use strict";

    angular.module("controllers", [
        "services"
    ])

        .controller("donationController", [
            "$scope",
            "donationStorage",
            "$log",

            function ($scope, storage, $log) {
                $scope.items = [];
                $scope.deliveries = [];

                var externalData = storage.getExternalData();
                externalData.$promise.then(function(data){
                    $scope.categories = data.categories;
                    $scope.dropOffPoints = data.dropOffPoints;
                });

                $scope.category = new Category();
                $scope.dropOff = new DropOffPoint();
                $scope.donation = new Donation();
                $scope.successTextAlert = '';
                $scope.showSuccessAlert = false;

                $scope.changedCategory = function(item) {
                    if (!item) {
                        $scope.category.selectedUnits = null;
                        return;
                    }
                    var selectedObject = JSON.parse(item);
                    $scope.category.selectedUnits = selectedObject.maxUnits;
                }

                $scope.addItem = function () {
                    var newItem;

                    if (!$scope.category.selectedCategory) { return; }

                    var selectedObject = JSON.parse($scope.category.selectedCategory);
                    console.log(selectedObject);
                    newItem = {
                        productId: selectedObject.id,
                        description: selectedObject.description,
                        max_units: $scope.category.selectedUnits,
                        country: selectedObject.country
                    };

                    $scope.items.push(newItem);
                    $scope.category = new Category();
                    // reset UI validation
                    $scope.categoryForm.Category.$touched = false;
                    $scope.categoryForm.Units.$touched = false;
                };


                $scope.saveDonation = function () {
                    if ($scope.items) {
                        var selectedDrops = JSON.parse($scope.dropOff.selectedDropOff);
                        $scope.donation = new Donation($scope.items, selectedDrops.description, $scope.dropOff.selectedComment);
                        storage.saveTasks($scope.donation);

                        $scope.successTextAlert = 'Donation saved successfully';
                        $scope.showSuccessAlert = true;
                        //reset UI
                        $scope.items = [];
                        $scope.dropOff = new DropOffPoint();
                        $scope.donation = new Donation();
                        $scope.submissionForm.DropOffPoint.$touched = false;
                        $scope.submissionForm.Comments.$touched = false;
                    } else {
                        $scope.successTextAlert = 'Failed to save donation';
                        $scope.showSuccessAlert = true;
                    }
                };

                function Category(selectedCategory, selectedUnits) {
                    this.selectedCategory = selectedCategory,
                        this.selectedUnits = selectedUnits
                };

                function DropOffPoint(selectedDropOff, selectedComment) {
                    this.selectedDropOff = selectedDropOff,
                        this.selectedComment = selectedComment
                };

                function Donation(products, drop_off_point, comment) {
                    this.products = products,
                        this.drop_off_point = drop_off_point,
                        this.comment = comment;
                };
            }
        ])

        .controller("authenticationController", [
            "$scope",
            "authentication",
            "$log",
            "$window",

            function ($scope, authentication, $log, $window) {
                $scope.authenticatedUser = authentication.getAuthenticatedUser();

                $scope.logout = function () {
                    authentication.logout(function () {
                        $log.log("User successfully loggged out");
                        $window.location.reload(true);
                    }, function () {
                        $log.error("Could not logout.");
                    });

                };
            }
        ]);
}(angular));