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
	<div class="container">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>${pageTitle}</h2>
					</div>
					<div class="col-sm-6">
						<a href="#addModal" class="btn btn-success" data-toggle="modal">
							<i class="material-icons">&#xE147;</i>
							<span>Criar Nov${article} ${entity}</span>
						</a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>
							<span class="custom-checkbox">
								<input type="checkbox" id="selectAll">
								<label for="selectAll"></label>
							</span>
						</th>  
						<th>Cultivar</th>
						<th>Resistente a Ferrugem</th>
						<th>BT</th>
						<th>Data Semeadura</th>
						<th>Data Emergência</th>
						<th>Data Colheita</th>
						<th>Produtividade da Unidade</th>
						<th>Produtividade da Área total do Produtor</th>
						<th>Pesou em Separado</th>
						<th>Área da Unidade</th>
						<th>Área Total Cultivada</th>
						<th># Plantas/Metro</th>
						<th>Latitude</th>
						<th>Longitude</th>
						<th>Unidade de Referência</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<#list surveyFields as surveyField>
						<tr>
							<td>
								<span class="custom-checkbox">
									<input type="checkbox" id="checkbox1" name="options[]" value="1">
									<label for="checkbox1"></label>
								</span>
							</td>
							<td>${surveyField.name}</td>
							<td>${surveyField.questionData.rustResistant?string('yes', 'no')}</td>
							<td>${surveyField.questionData.bt?string('yes', 'no')}</td>
							<td>${surveyField.dateData.sowedDate}</td>
							<td>${surveyField.dateData.emergenceDate}</td>
							<td>${surveyField.dateData.harvestDate}</td>
							<td>${surveyField.productivityData.productivityField}</td>
							<td>${surveyField.productivityData.productivityFarmer}</td>
							<td>${surveyField.productivityData.separatedWeight?string('yes', 'no')}</td>
							<td>${surveyField.sizeData.totalArea}</td>
							<td>${surveyField.sizeData.totalPlantedArea}</td>
							<td>${surveyField.sizeData.plantPerMeter}</td>
							<td>${surveyField.locationData.latitute}</td>
							<td>${surveyField.locationData.longitude}</td>
							<td>${surveyField.field.name}</td>
							<td>
								<a href="#editModal" class="edit" data-toggle="modal" data-id="${surveyField.id}" data-name="${surveyField.name}" >
									<i class="material-icons" data-toggle="tooltip" title="Alterar">&#xE254;</i>
								</a>
								<a href="#deleteModal" class="delete" data-toggle="modal" data-id="${surveyField.id}" data-name="${surveyField.name}">
									<i class="material-icons" data-toggle="tooltip" title="Apagar">&#xE872;</i>
								</a>
							</td>
						</tr>

					</#list>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Add Modal HTML -->
	<#include "add-modal.ftl">

	<!-- Edit Modal HTML -->
	<#include "edit-modal.ftl">

	<!-- Delete Modal HTML -->
	<#include "delete-modal.ftl">

	<!-- External JS libs -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>

	<script src="/js/survey/survey-management.js"></script>
</body>

</html>