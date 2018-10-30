$(document).ready(function(){

	$('#editModal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal

		var pestSize = button.data('size');
		var usualName = button.data('usual');
		var scientificName = button.data('scientific'); // Extract info from data-* attributes
		var id = button.data('id'); // Extract info from data-* attributes

		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this);
		
		modal.find('#edit-size').val(pestSize);
		modal.find('#edit-usual').val(usualName);
		modal.find('#edit-scientific').val(scientificName);
		modal.find('#edit-id').val(id);
	});

	$('#deleteModal').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget); // Button that triggered the modal
		var id = button.data('id'); // Extract info from data-* attributes
		var name = button.data('name'); // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this);
		modal.find('#edit-id').val(id);
		modal.find('#edit-name').text(name);
	});


	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
});