
define ['controllers/controllers'], (controllers) ->
    controllers.controller 'EditQueueController', ["$scope", "$location", "queueService", "queue", ($scope, $location, queueService, queue) ->
        $scope.queue = queue.data.queue

        $scope.saveQueue = () ->
            queueService.saveQueue($scope.queue)
                .success(() -> $location.path("/"))
    ]