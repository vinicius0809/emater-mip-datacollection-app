<div id="editModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlUpdate}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Alterar ${entity}</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Nome</label>
							<input type="text" class="form-control" id="edit-usualName" name="usualName" required>
							<input type="hidden" class="form-control" id="edit-id" name="id">
						</div>
						<div class="form-group">
							<label>Nome Científico</label>
							<input type="text" class="form-control" id="edit-scientificName" name="scientificName" required>
						</div>
						<div class="form-group">
							<label>Tamanho</label>
							<select name="pestSize" class="form-control" id="edit-pestSize">
								<#list pestSizes as pestSize>
  									<option value="${pestSize}">${pestSize.getName()}</option>
								</#list>
  							</select>
						</div>
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-info" value="Salvar Alterações">
					</div>
				</form>
			</div>
		</div>
	</div>