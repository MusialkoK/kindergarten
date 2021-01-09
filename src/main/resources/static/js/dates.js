$(document).ready(function () {
    const childrenCount = document.getElementById('childrenCount').value;
    let i;
    for (i = 0; i < childrenCount; i++) {
        $('#mdp-demo' + i).multiDatesPicker({
            beforeShowDay: $.datepicker.noWeekends,     // Disable selection of weekends
            firstDay: 1,                                // Start with Monday
            minDate: new Date(),                       // prevent selection of date older than today
            addDisabledDates: document.getElementById('holidays').value.split(","),
            onSelect: function () {
                console.log($(this).data("test"));
                $('#DatesLabel'+$(this).data("test")).val(JSON.stringify($(this).multiDatesPicker("getDates")))
            }
        });
    }
});
