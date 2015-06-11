
require.config
    baseUrl: '/resources/js/app'
    paths:
        "jquery"           : "../vendor/jquery-1.11.0.min"
        "angular"          : "../vendor/angularjs-1.2.9/angular.min"
        "angular-animate"  : "../vendor/angularjs-1.2.9/angular-animate.min"
        "angular-cookies"  : "../vendor/angularjs-1.2.9/angular-cookies.min"
        "angular-loader"   : "../vendor/angularjs-1.2.9/angular-loader.min"
        "angular-resource" : "../vendor/angularjs-1.2.9/angular-resource.min"
        "angular-route"    : "../vendor/angularjs-1.2.9/angular-route.min"
        "angular-sanitize" : "../vendor/angularjs-1.2.9/angular-sanitize.min"
        "angular-touch"    : "../vendor/angularjs-1.2.9/angular-touch.min"
        "ui-bootstrap"     : "../vendor/ui-bootstrap-tpls-0.10.0.min"
        "ng-grid"          : "../vendor/ng-grid-2.0.7.min"
        "domReady"         : "../vendor/domReady"
        "app"              : "app"
    shim:
        "angular"          : { deps: [ "jquery"], exports: "angular" }
        "angular-animate"  : { deps: [ "angular" ], exports: "angular-animate" }
        "angular-cookies"  : { deps: [ "angular" ], exports: "angular-cookies" }
        "angular-loader"   : { deps: [ "angular" ], exports: "angular-loader" }
        "angular-resource" : { deps: [ "angular" ], exports: "angular-resource" }
        "angular-route"    : { deps: [ "angular" ], exports: "angular-route" }
        "angular-sanitize" : { deps: [ "angular" ], exports: "angular-sanitize" }
        "angular-touch"    : { deps: [ "angular" ], exports: "angular-touch" }
        "ui-bootstrap"     : { deps: [ "angular" ], exports: "ui-bootstrap" }
        "ng-grid"          : { deps: [ "angular" ], exports: "ng-grid" }
        "domReady"         : { exports: "domReady" }

require [ "app", "domReady" ], (app, domReady) ->
    app.config ["$routeProvider", "$locationProvider", ($routeProvider, $locationProvider) ->
        $routeProvider
        .when "/",
            templateUrl: "/template/index"
            controller: "HomeController"
            resolve:
                queues: [ "queueService", (queueService) ->
                    queueService.getQueues()
                ]
        .when "/queue/new",
            templateUrl: "/template/editQueue"
            controller: "EditQueueController"
            resolve:
                queue: [ "queueService", (queueService) ->
                    queueService.newQueue()
                ]
        .when "/queue/:queueId",
            templateUrl: "/template/viewQueue"
            controller: "ViewQueueController"
            resolve:
                queue: [ "$route", "queueService", ($route, queueService) ->
                    queueService.getQueue($route.current.params.queueId)
                ]
        .when "/queue/:queueId/edit",
            templateUrl: "/template/editQueue"
            controller: "EditQueueController"
            resolve:
                queue: [ "$route", "queueService", ($route, queueService) ->
                    queueService.getQueue($route.current.params.queueId)
                ]
        .when "/queue/:queueId/queueItem/new",
            templateUrl: "/template/editQueueItem"
            controller: "EditQueueItemController"
            resolve:
                queueItem: [ "$route", "queueService", ($route, queueService) ->
                    queueService.newQueueItem($route.current.params.queueId)
                ]
        .when "/queue/:queueId/queueItem/:queueItemId/edit",
            templateUrl: "/template/editQueueItem"
            controller: "EditQueueItemController"
            resolve:
                queueItem: [ "$route", "queueService", ($route, queueService) ->
                    queueService.getQueueItem($route.current.params.queueId, $route.current.params.queueItemId)
                ]

        $locationProvider.html5Mode true
    ]
    domReady ->
        angular.bootstrap document, ["AgileTokenApp"]

