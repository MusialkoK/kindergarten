$(document).ready(function () {
    const childrenCount = document.getElementById('childrenCount').value;
    let i;
    for (i = 0; i < childrenCount; i++) {
        $('#mdp-demo' + i).multiDatesPicker({
            beforeShowDay: $.datepicker.noWeekends,     // Disable selection of weekends
            firstDay: 1,                                // Start with Monday
            minDate: new Date(),                       // prevent selection of date older than today
            onSelect: function () {
                $("#DatesLabel+i").val(JSON.stringify($(this).multiDatesPicker("getDates")))
            }
        });
    }
});
