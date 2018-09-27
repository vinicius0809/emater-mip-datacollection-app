<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${pageTitle}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>

<body>
    <div class="container-fluid">

        <form action="#" method="post" class="card" style="margin: 15px">
            <div class="card-header text-white" style="background-color: #004900">
                <h2>${pageTitle}</h2>
            </div>

            <div class="card-body table-responsive-md">
                <div>
                    <input class="form-control" id="search" type="text" placeholder="Buscar...">
                </div>

                <table id="mainTable" class="table table-striped table-hover">
                    <thead style="background-color: #004900; color: white">
                        <tr>
                            <th>Safra</th>
                            <th>Identificação</th>
                            <th>Localização</th>
                            <th>Cidade</th>
                            <th>Cultivar</th>
                            <th>Produtor</th>
                            <th>Técnico responsável</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody id="mainTable-body">
                        <#list mipPestSurveys as mipPestSurvey>
                            <tr>
                                <td>${mipPestSurvey.surveyField.harvest.name}</td>
                                <td>${mipPestSurvey.surveyField.field.name}</td>
                                <td>${mipPestSurvey.surveyField.field.location}</td>
                                <td>${mipPestSurvey.surveyField.field.city.name}</td>
                                <td>${mipPestSurvey.surveyField.name}</td>
                                <td>${mipPestSurvey.surveyField.field.farmer.name}</td>
                                <td>
                                    <#list mipPestSurvey.surveyField.field.supervisors as supervisor>
                                        <span>${supervisor.name}</span> <br>
                                    </#list>
							    </td>
                                <td>
                                    <a href="/pest-survey/add-sample?mipPestSurveyId=${mipPestSurvey.id}">
                                        <i class="material-icons" style="color: #004900" title="Coletar amostra">add_circle</i>
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>

            <div class="card-footer text-muted">

            </div>
        </form>

    </div>

    <!-- External JS libs -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function () {
            $("#search").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#mainTable-body tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>

</body>

</html>