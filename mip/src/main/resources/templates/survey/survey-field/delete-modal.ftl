	<div id="deleteModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${urlDelete}" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Retirar da Pesquisa ${entity}</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Você tem certeza que deseja retirar ${article} ${entity} "<span id="edit-name" class="bg-danger text-white"></span>" da pesquisa atual?</p>
						<p class="text-danger">Essa ação NÃO pode ser desfeita.</p>
					</div>
					<div class="modal-footer">
						<input type="hidden" class="form-control" id="edit-id" name="surveyFieldId">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" value="Retirar da Pesquisa">
					</div>
				</form>
			</div>
		</div>
	</div>
