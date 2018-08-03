	<div id="deleteModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="/macroregion/delete">
					<div class="modal-header">						
						<h4 class="modal-title">Apagar Macrorregião</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Você tem certeza que deseja apagar a macrorregião "<span id="edit-name"></span>" ?</p>
						<p class="text-warning"><small>Essa ação NÃO pode ser desfeita.</small></p>
					</div>
					<div class="modal-footer">
						<input type="hidden" class="form-control" id="edit-id" name="id">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancelar">
						<input type="submit" class="btn btn-danger" value="Apagar">
					</div>
				</form>
			</div>
		</div>
	</div>
