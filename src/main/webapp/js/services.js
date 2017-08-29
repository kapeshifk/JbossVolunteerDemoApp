(function (angular) {
    "use strict";

    angular.module("services", ["ngResource"])

        .factory("resourceBuilder", [
            "$resource",
            "$location",

            function ($resource, $location) {
                var protocol = $location.protocol(),
                    host = $location.host(),
                    port = $location.port(),
                    basePath = "/FNB_WIM_Volunteer_Practical/rest/api/";

                return {
                    buildResource: function (resourcePath) {
                        var resourceUrl = protocol + "://" + host + "\\:" + port + basePath + resourcePath;

                        return $resource(resourceUrl);
                    }
                };
            }
        ])

        .factory("donationStorage", [
            "resourceBuilder",
            "$log",

            function (resourceBuilder, $log) {
                var donationResource = resourceBuilder.buildResource("donation");

                return {
                    saveTasks: function (items) {
                        donationResource.save({}, items, function () {
                            // success
                            $log.info("Saved donation items.");
                        }, function () {
                            // failure
                            $log.error("Could not save donation items.");
                        });
                    },

                    getExternalData: function () {
                        return donationResource.get();
                    }
                };
            }
        ])

        .factory("authentication", [
            "resourceBuilder",

            function (resourceBuilder) {
                return {
                    getAuthenticatedUser: function () {
                        var authenticatedUserResource = resourceBuilder.buildResource("user");
                        return authenticatedUserResource.get();
                    },

                    logout: function (success, failure) {
                        var sessionsResource = resourceBuilder.buildResource("session");
                        sessionsResource.delete(success, failure);
                    }
                };
            }
        ]);
}(angular));