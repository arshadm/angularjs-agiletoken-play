
define ['controllers/controllers'], (controllers) ->
    controllers.controller 'EditQueueItemController', ["$scope", "$location", "$routeParams", "queueService", "queueItem", ($scope, $location, $routeParams, queueService, queueItem) ->
        $scope.queueItem = queueItem.data.queueItem

        $scope.saveQueueItem = () ->
            queueService.saveQueueItem($scope.queueItem)
                .success(() -> $location.path("/queue/#{$routeParams.queueId}"))
    ]