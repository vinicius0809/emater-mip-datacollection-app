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
				</div>
			</div>

			<div class="row">
				<form action="/survey-field/create" method="post" >

					<input type="hidden" name="fieldId" value="${selectedField.id}" >

					<div class="form-group">
						<label for="harvest">Safra</label>
						<select class="form-control" id="harvest" name="harvestId">
							<#list harvests as harvest>
								<option value="${harvest.id}">${harvest.name}</option>
							</#list>
						</select>
					</div>
					<div class="form-group">
						<label for="name">Cultivar</label>
						<input type="text" class="form-control" id="name" name="name" >
					</div>

					<div class="form-group">
						<label for="sowedDate">Data da Semeadura</label>
						<input type="date" class="form-control" id="sowedDate" name="sowedDate" >
					</div>
					<div class="form-group">
						<label for="emergenceDate">Data da Emergência</label>
						<input type="date" class="form-control" id="emergenceDate" name="emergenceDate" >
					</div>
					<div class="form-group">
						<label for="harvestDate">Data da Colheita</label>
						<input type="date" class="form-control" id="harvestDate" name="harvestDate" > 
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="rustResistant" name="rustResistant">
						<label class="form-check-label" for="rustResistant">
							A cultivar é resistente à ferrugem?
						</label>
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="bt" name="bt">
						<label class="form-check-label" for="bt">
							A cultivar é Bt?
						</label>
					</div>

					<div class="form-group">
						<label for="totalArea">Área da unidade (UO/UR) </label>
						<input type="number" step="0.01" class="form-control" id="totalArea" name="totalArea" >
					</div>

					<div class="form-group">
						<label for="totalPlantedArea">Área total cultivada </label>
						<input type="number" step="0.01" class="form-control" id="totalPlantedArea" name="totalPlantedArea" >
					</div>

					<div class="form-group">
						<label for="plantPerMeter">No. plantas por metro </label>
						<input type="number" step="0.01" class="form-control" id="plantPerMeter" name="plantPerMeter" >
					</div>

					<div class="form-group">
						<label for="productivityField">Produtividade da unidade (UO/UR) </label>
						<input type="number" step="0.01" class="form-control" id="productivityField" name="productivityField" >
					</div>

					<div class="form-group">
						<label for="productivityFarmer">Produtividade área total do produtor </label>
						<input type="number" step="0.01" class="form-control" id="productivityFarmer" name="productivityFarmer" >
					</div>

					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="separatedWeight" name="separatedWeight">
						<label class="form-check-label" for="separatedWeight">
							Pesou em separado?
						</label>
					</div>

					<div class="form-group">
						<label for="latitute">Latitude </label>
						<input type="number" step="0.01" class="form-control" id="latitute" name="latitute" >
					</div>

					<div class="form-group">
						<label for="longitude">Longitude </label>
						<input type="number" step="0.01" class="form-control" id="longitude" name="longitude" >
					</div>

					<input type="submit" class="btn btn-success" value="Salvar Dados da Unidade de Referência na Pesquisa" />	
				</form>
			</div>
		</div>
	</div>

	<!-- External JS libs -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>

</html>