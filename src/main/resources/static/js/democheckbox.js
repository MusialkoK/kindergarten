$(function() {
    $('#demo-checkbox').on('change', function() {
        if($(this).is(':checked')) {
            $('#username')
                .attr('value', 'parent1@wp.pl');
            $('#password')
                .attr('value', 'parent1');
        } else {
            $('#username')
                .prop('value', '');
            $('#password')
                .prop('value', '');
        }
    });
});
