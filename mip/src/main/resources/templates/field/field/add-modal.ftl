	<div id="addModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlCreate}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Criar Nov${article} ${entity}</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Identificação</label>
							<input type="text" class="form-control" name="name" required>
						</div>

						<div class="form-group">
							<label>Localização</label>
							<input type="text" class="form-control" name="location" required>
						</div>

						<div class="form-group">
							<label>Cidade</label>
							<select name="cityId" class="form-control">
								<#list cities as city>
  									<option value="${city.id}">${city.name}</option>
								</#list>
  							</select>
						</div>

						<div class="form-group">
							<label>Produtor</label>
							<select name="farmerId" class="form-control">
								<#list farmers as farmer>
  									<option value="${farmer.id}">${farmer.name}</option>
								</#list>
  							</select>
						</div>

						<div class="form-group">
							<label>Responsável Técnico</label>
							<select name="supervisorIds" class="form-control" size="5" multiple>
								<#list supervisors as supervisor>
  									<option value="${supervisor.id}">${supervisor.name}</option>
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