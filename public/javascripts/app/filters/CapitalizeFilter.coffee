
define ['filters/filters'], (filters) ->
    filters.filter('capitalize', () ->
        (inputValue) ->
            inputValue.toUpperCase()
    )