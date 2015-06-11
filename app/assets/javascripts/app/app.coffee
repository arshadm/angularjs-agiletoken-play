
define [
    "angular-animate"
    "angular-cookies"
    "angular-loader"
    "angular-resource"
    "angular-route"
    "angular-sanitize"
    "angular-touch"
    "ui-bootstrap"
    "ng-grid"
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
        "ngGrid"
        "ui.bootstrap"
        "controllers"
        "services"
        "directives"
        "filters"
    ]