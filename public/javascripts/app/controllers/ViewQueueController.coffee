
define ['controllers/controllers'], (controllers) ->
    controllers.controller 'ViewQueueController', ["$scope", "$location", "$route", "$routeParams", "queueService", "queue", ($scope, $location, $route, $routeParams, queueService, queue) ->
        $scope.queue = queue.data.queue

        $scope.mergedEntries = []
        $scope.unmergedEntries = []
        $scope.mergedEntries.push(queueItem) for queueItem in $scope.queue.queueItems when queueItem.mergedInd
        $scope.unmergedEntries.push(queueItem) for queueItem in $scope.queue.queueItems when !queueItem.mergedInd

        $scope.newQueueItem = () ->
            $location.path("/queue/#{$routeParams.queueId}/queueItem/new")

        $scope.markItemMerged = (id) ->
            queueService.markItemMerged($scope.queue.id, id)
                .success(() => $route.reload())

        $scope.editQueueItem = (id) ->
            $location.path("/queue/#{$scope.queue.id}/queueItem/#{id}/edit")

        $scope.deleteQueueItem = (id) ->
            queueService.deleteQueueItem($scope.queue.id, id)
                .success(() => $route.reload())
    ]