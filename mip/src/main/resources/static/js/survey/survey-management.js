$(document).ready(function(){

	$('#editModal').on('show.bs.modal', function (event) {
	var button = $(event.relatedTarget); // Button that triggered the modal

	var harvestBegin = button.data('harvestBegin');
	var harvestEnd = button.data('harvestEnd');
	var name = button.data('name'); // Extract info from data-* attributes
	var id = button.data('id'); // Extract info from data-* attributes

	// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	var modal = $(this);

	modal.find('#edit-harvestBegin').val(harvestBegin);
	modal.find('#edit-harvestEnd').val(harvestEnd);
	modal.find('#edit-name').val(name);
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
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});