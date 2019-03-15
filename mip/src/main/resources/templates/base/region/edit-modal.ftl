<div id="editModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlUpdate}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title"><@spring.message "modal.title.update.region" /></h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label><@spring.message "modal.label.name"/></label>
							<input type="text" class="form-control" id="edit-name" name="name" required>
							<input type="hidden" class="form-control" id="edit-id" name="id">
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.macroregion" /></label>
							<select name="macroRegionId" class="form-control" id="edit-macroregion">
								<#list macroRegions as macroRegion>
  									<option value="${macroRegion.id}">${macroRegion.name}</option>
								</#list>
  							</select>
						</div>
						<div class="form-group">
							<label><@spring.message "modal.label.city" /></label>
							<select name="citiesIDs" class="form-control" size="5" multiple>
								<#list cities as city>
  									<option value="${city.id}">${city.name}</option>
								</#list>
  							</select>
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