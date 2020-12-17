$(document).ready(function () {
    $('#mdp-demo').multiDatesPicker({
        beforeShowDay: $.datepicker.noWeekends,     // Disable selection of weekends
        firstDay: 1,                                // Start with Monday
        minDate: new Date(),                       // prevent selection of date older than today
        onSelect: function () {
            $("#DatesLabel").val(JSON.stringify($(this).multiDatesPicker("getDates")))
        }
    });
});


