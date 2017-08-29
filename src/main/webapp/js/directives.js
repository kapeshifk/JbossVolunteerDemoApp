(function (angular) {
    "use strict";

    angular.module("directives", [])

        .constant("PARTIAL_PATH", "partials/")

        .directive("formValidState", [
            function () {
                return {
                    restrict: "A",
                    link: function (scope, element, attrs) {
                        var formName = attrs.name;

                        scope.$watch(formName + ".$valid", function () {
                            scope.$emit(formName + "ValidStateChanged", { "valid" : scope[formName].$valid });
                        });
                    }
                };
            }
        ]);
}(angular));