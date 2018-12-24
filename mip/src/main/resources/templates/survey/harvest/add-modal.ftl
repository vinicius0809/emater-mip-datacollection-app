	<div id="addModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlCreate}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title"><@spring.message "modal.title.new.harvest" /></h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label><@spring.message "modal.label.description" /></label>
							<input type="text" class="form-control" name="name" required>
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.dataBegin" /></label>
							<input type="date" class="form-control" name="begin" required>
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.dataEnd" /></label>
							<input type="date" class="form-control" name="end" required>
						</div>
					</div>
					<div class="modal-footer">
						<#assign buttonCreate><@spring.message "modal.button.new" /></#assign>
						<#assign buttonCancel><@spring.message "modal.button.cancel" /></#assign>

						<input type="button" class="btn btn-default" data-dismiss="modal" value="${buttonCancel}">
						<input type="submit" class="btn btn-success" value="${buttonCreate}">
					</div>
				</form>
			</div>
		</div>
	</div>