	<div id="addModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="/region/create">
					<div class="modal-header">						
						<h4 class="modal-title">Criar Nova Região</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Nome</label>
							<input type="text" class="form-control" name="name" required>
						</div>
						<div class="form-group">
							<label>Macrorregião</label>
							<select name="macroRegionId" class="form-control">
								<#list macroRegions as macroRegion>
  									<option value="${macroRegion.id}">${macroRegion.name}</option>
								</#list>
  							</select>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-success" value="Criar">
					</div>
				</form>
			</div>
		</div>
	</div>