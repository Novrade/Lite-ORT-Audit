$(document).ready(function(){
  $('table td').each(
    function(){
      var td_value = $(this).th:text();
      if (td_value == 'X' ) {
        $(this).css('background', 'red');
      }
    }
  );
});