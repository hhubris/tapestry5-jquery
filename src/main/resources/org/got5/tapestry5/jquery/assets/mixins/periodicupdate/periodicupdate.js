(function ($) {

    T5.extendInitializers(function() {

        function init(specs) {
            $(document).everyTime(specs.updateFrequency, specs.zoneId, function(i) {
                var zone = $("#" + specs.zoneId);
                if (!zone) return;
                zone.tapestryZone("update", specs);
            }, specs.maxUpdates)
        }

        return {
            periodicupdate : init
        }

    });

}) (jQuery);