
define ['services/services'], (services) ->
    services.factory 'queueService', ['$http', ($http) ->
        getQueues: () ->
            $http.get("/rs/queue")

        newQueue: () ->
            $http.get("/rs/queue/new")

        getQueue: (queueId) ->
            $http.get("/rs/queue/#{queueId}")

        saveQueue: (queue) ->
            $http.post("/rs/queue/save", { "queue" : queue })

        removeQueue: (queueId) ->
            $http.delete("/rs/queue/#{queueId}")

        newQueueItem: (queueId) ->
            $http.get("/rs/queue/#{queueId}/queueItem/new")

        getQueueItem: (queueId, queueItemId) ->
            $http.get("/rs/queue/#{queueId}/queueItem/#{queueItemId}")

        markItemMerged: (queueId, queueItemId) ->
            $http.put("/rs/queue/#{queueId}/queueItem/#{queueItemId}/merged")

        saveQueueItem: (queueItem) ->
            $http.post("/rs/queue/#{queueItem.queueId}/queueItem/save", { "queueItem": queueItem })

        deleteQueueItem: (queueId, queueItemId) ->
            $http.delete("/rs/queue/#{queueId}/queueItem/#{queueItemId}")
    ]