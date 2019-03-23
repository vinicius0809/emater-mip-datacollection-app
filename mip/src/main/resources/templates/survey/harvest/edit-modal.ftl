<div id="editModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlUpdate}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title"><@spring.message "modal.title.update.harvest" /></h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label><@spring.message "modal.label.description" /></label>
							<input type="text" class="form-control" name="name" id="edit-name" required autofocus maxlength="25">
							<input type="hidden" class="form-control" id="edit-id" name="id">
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.dataBegin" /></label>
							<input type="date" class="form-control" name="begin" id="edit-harvestBegin" required>
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.dataEnd" /></label>
							<input type="date" class="form-control" name="end" id="edit-harvestEnd" required>
						</div>
					</div>
					<div class="modal-footer">
						<#assign buttonSave><@spring.message "modal.button.save" /></#assign>
						<#assign buttonCancel><@spring.message "modal.button.cancel" /></#assign>

						<input type="button" class="btn btn-default" data-dismiss="modal" value="${buttonCancel}">
						<input type="submit" class="btn btn-info" value="${buttonSave}">
					</div>
				</form>
			</div>
		</div>
	</div>