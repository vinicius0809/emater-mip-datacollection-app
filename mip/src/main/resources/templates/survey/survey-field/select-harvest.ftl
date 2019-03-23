	<div id="selectHarvestModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="/survey-field/select-field" method="post">
					<div class="modal-header">						
						<h4 class="modal-title"><@spring.message "modal.title.select.harvest" /></h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label><@spring.message "table.list.harvest" /></label>
							<select name="harvestId" class="form-control">
								<#list harvests as harvest>
  									<option value="${harvest.id}">${harvest.name}</option>
								</#list>
  							</select>
						</div>					
					</div>
					<div class="modal-footer">
						<#assign buttonSelect><@spring.message "modal.button.select" /></#assign>
						<#assign buttonCancel><@spring.message "modal.button.cancel" /></#assign>

						<input type="button" class="btn btn-default" data-dismiss="modal" value="${buttonCancel}">
						<input type="submit" class="btn btn-success" value="${buttonSelect}">
					</div>
				</form>
			</div>
		</div>
	</div>