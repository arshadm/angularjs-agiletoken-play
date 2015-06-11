
define ['directives/directives'], (directives) ->
    directives.directive('capitalizeFirst', () ->
        require: "ngModel"
        link: (scope,element,attrs,modelCtrl) ->
            capitalize = (inputValue) ->
                capitalized = inputValue.charAt(0).toUpperCase() + inputValue.substring(1)
                if(capitalized != inputValue)
                    modelCtrl.$setViewValue(capitalized)
                    modelCtrl.$render()
                capitalized
            modelCtrl.$parsers.push(capitalize)
            capitalize(scope[attrs.ngModel]) // capitalize initial value
    )