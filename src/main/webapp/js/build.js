$('#rooftype > select').change(function() {
    /// for roof type flat, make wood roofing and fittings visible
    if ($(this).val() === '0') {
        $('#wood-roofing').removeClass('hidden');
        $('#fittings-screws').removeClass('hidden');
        $('#wood').addClass('hidden');
        $('#roof-skeleton').addClass('hidden');
    }else if ($(this).val() === '1') {
    /// for roof type triangle, make wood, roof-skeleton and fittings visible
    
        $('#wood').removeClass('hidden');
        $('#roof-skeleton').removeClass('hidden');
        $('#fittings-screws').removeClass('hidden');
        $('#wood-roofing').addClass('hidden');
    }
    else{
        if (!$('#wood-roofing').hasClass('hidden'))
        {
            $('#wood-roofing').addClass('hidden');
        }
        if (!$('#fittings-screws').hasClass('hidden'))
        {
            $('#fittings-screws').addClass('hidden');
        }
        if (!$('#wood').hasClass('hidden'))
        {
            $('#wood').addClass('hidden');
        }
        if (!$('#roof-skeleton').hasClass('hidden'))
        {
            $('#roof-skeleton').addClass('hidden');
        }
    }
});

$('select').change(function() {
    // check if all selects have values and show the add to cart button
    var roofTypeSelected = $('#rooftype > select').find(':selected');
    var woodRoofingSelected = $('#wood-roofing > select').find(':selected');
    var fittingsScrewsSelected = $('#fittings-screws > select').find(':selected');
    var woodSelected = $('#wood > select').find(':selected');
    var roofSkeletonSelected = $('#roof-skeleton > select').find(':selected');
    
    var addToCartButton = $('#add-to-cart');
    
    if (roofTypeSelected.val() === '0') {
        
        if (woodRoofingSelected.val() !== '' && fittingsScrewsSelected.val() !== ''){
            if(addToCartButton.hasClass('hidden')){
                addToCartButton.removeClass('hidden');
            }
        }
        else{
            if(!addToCartButton.hasClass('hidden')){
                addToCartButton.addClass('hidden');
            }
        }
    }
    
    else if (roofTypeSelected.val() === '1') {
        if (woodSelected.val() !== '' &&  roofSkeletonSelected.val() !== '' && fittingsScrewsSelected.val() !== ''){
            if(addToCartButton.hasClass('hidden')){
                addToCartButton.removeClass('hidden');
            }
        }
        else{
            if(!addToCartButton.hasClass('hidden')){
                addToCartButton.addClass('hidden');
            }
        }
    }
    else {
        if(!addToCartButton.hasClass('hidden')){
            addToCartButton.addClass('hidden');
        }
    }
});

//plugin bootstrap minus and plus
//http://jsfiddle.net/laelitenetwork/puJ6G/
$('.btn-number').click(function(e){
    e.preventDefault();
    
    fieldName = $(this).attr('data-field');
    type      = $(this).attr('data-type');
    var input = $("input[name='"+fieldName+"']");
    var currentVal = parseInt(input.val());
    if (!isNaN(currentVal)) {
        if(type == 'minus') {
            
            if(currentVal > input.attr('min')) {
                input.val(currentVal - 1).change();
            } 
            if(parseInt(input.val()) == input.attr('min')) {
                $(this).attr('disabled', true);
            }

        } else if(type == 'plus') {

            if(currentVal < input.attr('max')) {
                input.val(currentVal + 1).change();
            }
            if(parseInt(input.val()) == input.attr('max')) {
                $(this).attr('disabled', true);
            }

        }
    } else {
        input.val(0);
    }
});
$('.input-number').focusin(function(){
   $(this).data('oldValue', $(this).val());
});
$('.input-number').change(function() {
    
    minValue =  parseInt($(this).attr('min'));
    maxValue =  parseInt($(this).attr('max'));
    valueCurrent = parseInt($(this).val());
    
    name = $(this).attr('name');
    if(valueCurrent >= minValue) {
        $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the minimum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    if(valueCurrent <= maxValue) {
        $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the maximum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    
    
});
$(".input-number").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });