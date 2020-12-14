$(document).ready(function () {
    $('#mdp-demo').multiDatesPicker({
        beforeShowDay: $.datepicker.noWeekends,     // Disable selection of weekends
        firstDay: 1,                                // Start with Monday
        minDate: new Date(),                       // prevent selection of date older than today
        altFormat: 'dd-mm-yyyy'                     // Date Format used
    });

});
