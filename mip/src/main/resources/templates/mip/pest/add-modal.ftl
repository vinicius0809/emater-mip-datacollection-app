	<div id="addModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlCreate}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title"><@spring.message "modal.title.new.pest" /></h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label><@spring.message "modal.label.name" /></label>
							<input type="text" class="form-control" name="usualName" required autofocus maxlength="50">
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.scientific-name" /></label>
							<input type="text" class="form-control" name="scientificName" required maxlength="50">
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.size" /></label>
							<select name="pestSize" class="form-control" required>
								<#list pestSizes as pestSize>
  									<option value="${pestSize}">${pestSize.getName()}</option>
								</#list>
  							</select>
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