
define [
    "angular-animate"
    "angular-cookies"
    "angular-loader"
    "angular-resource"
    "angular-route"
    "angular-sanitize"
    "angular-touch"
    "controllers/HomeController"
    "controllers/ViewQueueController"
    "controllers/EditQueueController"
    "controllers/EditQueueItemController"
    "services/QueueService"
    "filters/CapitalizeFilter"
    "directives/CapitalizeFirstDirective"
], ->
    angular.module "AgileTokenApp", [
        "ngResource"
        "ngRoute"
        "controllers"
        "services"
        "directives"
        "filters"
    ]