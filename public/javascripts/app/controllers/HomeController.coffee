
define ['controllers/controllers'], (controllers) ->
    controllers.controller 'HomeController', ["$scope", "$location", "$route", "queueService", "queues", ($scope, $location, $route, queueService, queues) ->
        $scope.queues = queues.data.queues

        $scope.newQueue = () ->
            $location.path("/queue/new")

        $scope.editQueue = (queueId) ->
            $location.path("/queue/#{queueId}/edit")

        $scope.removeQueue = (queueId) ->
            queueService.removeQueue(queueId)
                .success(() => $route.reload())
    ]