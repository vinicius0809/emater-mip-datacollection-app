<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${pageTitle}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel='stylesheet'href='https://fonts.googleapis.com/css?family=Arimo'>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.dataTables.min.css">

</head>

<body style="font-family: 'Arimo'">
    <div class="container-fluid">

    <!-- Add Menu -->
    <#include "/menubar.ftl">

        <form action="#" method="post" class="card" style="margin: 15px;">
            <div class="card-header text-white" style="background-color: #004900">
                <h2 class="card-title" style="display: inline">${pageTitle}</h2>

                <a href="/survey-field/select-field" class="btn btn-success float-right" >
                    <i class="material-icons align-middle">&#xE147;</i>
                    <!-- <span>Criar Nov${article} ${entity}</span> -->
                    <span class="align-middle">Adicionar Unidade de Referência à Pesquisa</span>
                </a>
            </div>

            <div class="card-body table-responsive-md">

                <table id="mainTable" class="table table-striped table-hover display responsive nowrap">
                    <thead style="background-color: #004900; color: white">
                        <tr>
                            <th class="col-sm-1">Safra</th>
                            <th class="col-sm-1">Cultivar</th>
                            <th class="col-sm-1">Resistente a Ferrugem</th>
                            <th class="col-sm-1">BT</th>
                            <th class="col-sm-1">Data Semeadura</th>
                            <th class="col-sm-1">Data Emergência</th>
                            <th class="col-sm-1">Data Colheita</th>
                            <th class="col-sm-1">Produtividade da Unidade</th>
                            <th class="col-sm-1">Produtividade da Área total do Produtor</th>
                            <th class="col-sm-1">Pesou em Separado</th>
                            <th class="col-sm-1">Área da Unidade</th>
                            <th class="col-sm-1">Área Total Cultivada</th>
                            <th class="col-sm-1"># Plantas/Metro</th>
                            <th class="col-sm-1">Latitude</th>
                            <th class="col-sm-1">Longitude</th>
                            <th class="col-sm-1">Unidade de Referência</th>
                            <th class="col-sm-1">Ações</th>    
                        </tr>
                    </thead>
                    <tbody id="mainTable-body">

                        <#list surveyFields as surveyField>

                            <tr>
                                <td>${surveyField.harvest.name}</td>
                                <td>${surveyField.name}</td>
                                <td>${surveyField.questionData.rustResistant?string('Sim', 'Não')}</td>
                                <td>${surveyField.questionData.bt?string('Sim', 'Não')}</td>
                                <td>${surveyField.dateData.sowedDate?string.medium}</td>
                                <td>${surveyField.dateData.emergenceDate?string.medium}</td>
                                <td>${surveyField.dateData.harvestDate?string.medium}</td>
                                <td>${surveyField.productivityData.productivityField}</td>
                                <td>${surveyField.productivityData.productivityFarmer}</td>
                                <td>${surveyField.productivityData.separatedWeight?string('Sim', 'Não')}</td>
                                <td>${surveyField.sizeData.totalArea}</td>
                                <td>${surveyField.sizeData.totalPlantedArea}</td>
                                <td>${surveyField.sizeData.plantPerMeter}</td>
                                <td>${surveyField.locationData.latitute}</td>
                                <td>${surveyField.locationData.longitude}</td>
                                <td>${surveyField.field.name}</td>
                                <td>
                                    <a href="#deleteModal" class="text-danger" data-toggle="modal" data-id="${surveyField.id}" data-name="${surveyField.name}">
                                        <i class="material-icons" data-toggle="tooltip" title="Retirar da pesquisa">clear</i>
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

    <!-- Delete Modal HTML -->
    <#include "delete-modal.ftl">

    <!-- External JS libs -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>    
    <script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>    

    <script>
        $(document).ready(function () {
            $('#mainTable').DataTable({
                responsive: true,
                language: {
                    processing:     "Processando...",
                    search:         "Buscar",
                    lengthMenu:    "_MENU_ resultados por página",
                    info:           "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    infoEmpty:      "Mostrando de 0 até 0 de 0 registros",
                    infoFiltered:   "(Filtrados de _MAX_ registros)",
                    infoPostFix:    "",
                    loadingRecords: "Carregando...",
                    zeroRecords:    "Nenhum registro encontrado",
                    emptyTable:     "Nenhum registro encontrado",
                    paginate: {
                        first:      "Primeiro",
                        previous:   "Anterior",
                        next:       "Próximo",
                        last:       "Último"
                    },
                    aria: {
                        sortAscending:  ": Ordenar colunas de forma crescente",
                        sortDescending: ": Ordenar colunas de forma decrescente"
                    }
                }
            });
        }); 
    </script>
        
    <script src="/js/survey/survey-management.js"></script>
</body>

</html>