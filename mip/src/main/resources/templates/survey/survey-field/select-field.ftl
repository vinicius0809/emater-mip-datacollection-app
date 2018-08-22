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
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>
							Selecione
						</th>  
						<th>Identificação</th>
						<th>Localização</th>
						<th>Cidade</th>
						<th>Produtor</th>
						<th>Responsável Técnico</th>
					</tr>
				</thead>
				<tbody>
					<#list fields as field>
						<tr>
							<td>
								<a href="/survey-field/field-form?fieldId=${field.id}" >
									<i class="material-icons" data-toggle="tooltip" title="Selecionar">arrow_forward</i>
								</a>
							</td>
							<td>${field.name}</td>
							<td>${field.location}</td>
							<td>${field.city.name}</td>
							<td>${field.farmer.name}</td>
							<td>
								<#list field.supervisors as supervisor>
									<span>${supervisor.name}</span> <br>
								</#list>
							</td>
						</tr>

					</#list>
				</tbody>
			</table>
		</div>
	</div>

	<!-- External JS libs -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>