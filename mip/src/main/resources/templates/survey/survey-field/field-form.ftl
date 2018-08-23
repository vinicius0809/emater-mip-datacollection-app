<!DOCTYPE html>
<html lang="pt-BR">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>${pageTitle}</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

</head>

<body>
	<div class="container-fluid">

		<form action="/survey-field/create" method="post" class="card" style="margin: 15px">
			<div class="card-header text-white" style="background-color: #004900">
				<h2>${pageTitle}</h2>
			</div>
			<div class="card-body">

					<input type="hidden" name="fieldId" value="${selectedField.id}">

					<div class="card" style="margin-top: 15px">
						<div class="card-header text-white" style="background-color: #004900">
							Safra
						</div>
						<div class="card-body">
							<div class="form-group">
								<select class="form-control" id="harvest" name="harvestId">
									<#list harvests as harvest>
										<option value="${harvest.id}">${harvest.name}</option>
									</#list>
								</select>
							</div>
						</div>
					</div>

					<div class="card" style="margin-top: 15px">
						<div class="card-header text-white" style="background-color: #004900">
							Cultivar
						</div>
						<div class="card-body">
							<div class="form-group ">
								<label for="name">Nome</label>
								<input type="text" class="form-control" id="name" name="name">
							</div>

							<div class="form-row col-md-6">
								<div class="form-check col">
									<input class="form-check-input" type="checkbox" id="rustResistant" name="rustResistant">
									<label class="form-check-label" for="rustResistant">
										Resistente à ferrugem?
									</label>
								</div>

								<div class="form-check col">
									<input class="form-check-input" type="checkbox" id="bt" name="bt">
									<label class="form-check-label" for="bt">
										Bt?
									</label>
								</div>
							</div>

						</div>
					</div>


					<div class="card" style="margin-top: 15px">
						<div class="card-header text-white" style="background-color: #004900">
							Área
						</div>
						<div class="card-body">
							<div class="form-row">
								<div class="form-group col">
									<label for="totalArea">Área da unidade (UO/UR) </label>
									<input type="number" step="0.01" class="form-control" id="totalArea" name="totalArea">
								</div>

								<div class="form-group col">
									<label for="totalPlantedArea">Área total cultivada </label>
									<input type="number" step="0.01" class="form-control" id="totalPlantedArea" name="totalPlantedArea">
								</div>

								<div class="form-group col">
									<label for="plantPerMeter">No. plantas por metro </label>
									<input type="number" step="0.01" class="form-control" id="plantPerMeter" name="plantPerMeter">
								</div>
							</div>

							<div class="form-row">
								<div class="form-group col">
									<label for="latitute">Latitude </label>
									<input type="number" step="0.01" class="form-control" id="latitute" name="latitute">
								</div>

								<div class="form-group col">
									<label for="longitude">Longitude </label>
									<input type="number" step="0.01" class="form-control" id="longitude" name="longitude">
								</div>
							</div>
						</div>
					</div>

					<div class="card" style="margin-top: 15px">
						<div class="card-header text-white" style="background-color: #004900">
							Produtividade
						</div>
						<div class="card-body">

							<div class="form-row">
								<div class="form-group col">
									<label for="productivityField">Produtividade da unidade (UO/UR) </label>
									<input type="number" step="0.01" class="form-control" id="productivityField" name="productivityField">
								</div>

								<div class="form-group col">
									<label for="productivityFarmer">Produtividade área total do produtor </label>
									<input type="number" step="0.01" class="form-control" id="productivityFarmer" name="productivityFarmer">
								</div>
							</div>

							<div class="form-row">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="separatedWeight" name="separatedWeight">
									<label class="form-check-label" for="separatedWeight">
										Pesou em separado?
									</label>
								</div>
							</div>

						</div>
					</div>

					<div class="card" style="margin-top: 15px">
						<div class="card-header text-white" style="background-color: #004900">
							Datas
						</div>
						<div class="card-body">

							<div class="form-row">
								<div class="form-group col">
									<label for="sowedDate">Data da Semeadura</label>
									<input type="date" class="form-control" id="sowedDate" name="sowedDate">
								</div>
								<div class="form-group col">
									<label for="emergenceDate">Data da Emergência</label>
									<input type="date" class="form-control" id="emergenceDate" name="emergenceDate">
								</div>
								<div class="form-group col">
									<label for="harvestDate">Data da Colheita</label>
									<input type="date" class="form-control" id="harvestDate" name="harvestDate">
								</div>
							</div>
						</div>
					</div>
			</div>
			<div class="card-footer text-muted">
    			<input type="submit" class="btn btn-success" value="Salvar Dados da Unidade de Referência na Pesquisa" />
  			</div>
		</form>	
	</div>

	<!-- External JS libs -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>