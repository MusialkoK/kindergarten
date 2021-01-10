$(document).ready(function () {
    const childrenCount = document.getElementById('childrenCount').value;
    let i;
    for (i = 0; i < childrenCount; i++) {
        $.datepicker.setDefaults(
            $.extend(
                $.datepicker.regional['pl']
            )
        );
        $('#mdp-demo' + i).multiDatesPicker({
            beforeShowDay: $.datepicker.noWeekends,     // Disable selection of weekends
            firstDay: 1,                                // Start with Monday
            addDisabledDates: document.getElementById('holidays').value.split(","),
            addDates: document.getElementById('presences' + i).value.split(","),
            onSelect: function () {
                console.log($(this).data("test"));
                $('#datesLabel' + $(this).data("test")).val(JSON.stringify($(this).multiDatesPicker("getDates")))
            }
        });
    }
});
