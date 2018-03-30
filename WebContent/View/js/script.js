$(document).ready(function() {
	// Datepicker Popups calender to Choose date.
	$(function() {
		$("#datepicker_birthDate").datepicker({
			showOn : "button",
			buttonImage : "View/images/calendar.gif",
			buttonImageOnly : true
		});
		// Pass the user selected date format.
		$("#format").change(function() {
			$("#datepicker_birthDate").datepicker("option", "dateFormat", $(this).val());
		});
	});
});
$(document).ready(function() {
	// Datepicker Popups calender to Choose date.
	$(function() {
		$("#datepicker_insuranceStartDate").datepicker({
			showOn : "button",
			buttonImage : "View/images/calendar.gif",
			buttonImageOnly : true
		});
		// Pass the user selected date format.
		$("#format").change(function() {
			$("#datepicker_insuranceStartDate").datepicker("option", "dateFormat", $(this).val());
		});
	});
});
$(document).ready(function() {
	// Datepicker Popups calender to Choose date.
	$(function() {
		$("#datepicker_insuranceEndDate").datepicker({
			showOn : "button",
			buttonImage : "View/images/calendar.gif",
			buttonImageOnly : true
		});
		// Pass the user selected date format.
		$("#format").change(function() {
			$("#datepicker_insuranceEndDate").datepicker("option", "dateFormat", $(this).val());
		});
	});
});