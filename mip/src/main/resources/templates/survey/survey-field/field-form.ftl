<!DOCTYPE html>
<html lang="pt-BR">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>${pageTitle}</title>

	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.css">

	<link rel="stylesheet" href="/css/crud.css">
</head>

<body>
	<div class="container-fluid">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>${pageTitle}</h2>
					</div>
					<div class="col-sm-6">
						<a href="/survey-field/select-field" class="btn btn-success" >
							<i class="material-icons">save</i>
							<span>Salvar Dados da Unidade de Referência na Pesquisa</span>
						</a>
					</div>
				</div>
			</div>

			<div class="row">
				<form>
					<div class="form-group">
						<label for="harvest">Safra</label>
						<select class="form-control" id="harvest">
							<#list harvests as harvest>
								<option value="${harvest.id}">${harvest.name}</option>
							</#list>
						</select>
					</div>
					<div class="form-group">
						<label for="name">Cultivar</label>
						<input type="text" class="form-control" id="name" >
					</div>

					<div class="form-group">
						<label for="sowedDate">Data da Semeadura</label>
						<input type="date" class="form-control" id="sowedDate" >
					</div>
					<div class="form-group">
						<label for="emergenceDate">Data da Emergência</label>
						<input type="date" class="form-control" id="emergenceDate" >
					</div>
					<div class="form-group">
						<label for="harvestDate">Data da Colheita</label>
						<input type="date" class="form-control" id="harvestDate" >
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="" id="rustResistant">
						<label class="form-check-label" for="rustResistant">
							A cultivar é resistente à ferrugem?
						</label>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="" id="bt">
						<label class="form-check-label" for="bt">
							A cultivar é Bt?
						</label>
					</div>

				</form>
			</div>
		</div>
	</div>

	<!-- External JS libs -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>