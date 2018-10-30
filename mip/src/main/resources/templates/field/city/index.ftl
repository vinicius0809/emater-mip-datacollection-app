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

    <!-- Add Menu -->
    <#include "/field/menubar.ftl">

        <form action="#" method="post" class="card" style="margin: 15px">
            <div class="card-header text-white d-flex" style="background-color: #004900">
                <h2 class="col-md-9">${pageTitle}</h2>

                <a href="#addModal" class="btn btn-success col-md-3 d-flex justify-content-center" data-toggle="modal">
                    <i class="material-icons">&#xE147;</i>
                    <span>Criar Nov${article} ${entity}</span>
                </a>
            </div>

            <div class="card-body table-responsive-md">
                <div>
                    <input class="form-control" id="search" type="text" placeholder="Buscar..." style="margin-bottom: 15px">
                </div>

                <table id="mainTable" class="table table-striped table-hover">
                    <thead style="background-color: #004900; color: white">
                        <tr>
                            <th class="col-sm-5">Nome</th>
                            <th class="col-sm-3">Região</th>
                            <th class="col-sm-3">Macrorregião</th>
                            <th class="col-sm-1">Ações</th>
                        </tr>
                    </thead>
                    <tbody id="mainTable-body">

                            <#list cities as city>

                            <tr>
                                <td>${city.name}</td>
                                <td>${city.region.name}</td>
                                <td>${city.region.macroRegion.name}</td>
                                <td>
                                    <a href="#editModal" class="text-warning" data-toggle="modal" data-id="${city.id}" data-name="${city.name}" data-region=${city.region.id}>
                                        <i class="material-icons" data-toggle="tooltip" title="Alterar">&#xE254;</i>
                                    </a>
                                    <a href="#deleteModal" class="text-danger" data-toggle="modal" data-id="${city.id}" data-name="${city.name}">
                                        <i class="material-icons" data-toggle="tooltip" title="Apagar">&#xE872;</i>
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

    <!-- Add Modal HTML -->
    <#include "add-modal.ftl">

    <!-- Edit Modal HTML -->
    <#include "edit-modal.ftl">

    <!-- Delete Modal HTML -->
    <#include "delete-modal.ftl">

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
        
        <script src="/js/field/field-management.js"></script>
</body>

</html>