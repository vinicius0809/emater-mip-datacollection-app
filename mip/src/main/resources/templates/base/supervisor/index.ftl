<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><@spring.message "page.title.supervisor" /></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel='stylesheet'href='https://fonts.googleapis.com/css?family=Arimo'>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">

</head>

<body style="font-family: 'Arimo'">
    <div class="container-fluid">

    <!-- Add Menu -->
    <#include "/menubar.ftl">

        <form action="#" method="post" class="card" style="margin: 15px">
            <div class="card-header text-white" style="background-color: #004900">
                <h2 class="card-title" style="display: inline"><@spring.message "card.title.supervisor" /></h2>

                <a href="#addModal" class="btn btn-success float-right" data-toggle="modal">
                    <i class="material-icons align-middle">&#xE147;</i>
                    <span class="align-middle"><@spring.message "card.button.new.supervisor" /></span>
                </a>
            </div>

            <div class="card-body table-responsive-md">

                <table id="mainTable" class="table table-striped table-hover">
                    <thead style="background-color: #004900; color: white">
                        <tr>
                            <th class="col-sm-7"><@spring.message "table.list.name" /></th>
                            <th class="col-sm-3"><@spring.message "table.list.email" /></th>
                            <th class="col-sm-2"><@spring.message "table.list.action" /></th>
                        </tr>
                    </thead>
                    <tbody id="mainTable-body">

                        <#list supervisors as supervisor>

                            <tr>
                                <td>${supervisor.name}</td>
                                <td>${supervisor.email}</td>
                                <td>
                                    <#assign updateLabel><@spring.message "table.list.actions.update" /></#assign>
                                    <#assign deleteLabel><@spring.message "table.list.actions.delete" /></#assign>

                                    <a href="#editModal" class="text-warning" data-toggle="modal" data-id="${supervisor.id}" data-name="${supervisor.name}" data-email="${supervisor.email}">
                                        <i class="material-icons" data-toggle="tooltip" title="${updateLabel}">&#xE254;</i>
                                    </a>
                                    <a href="#deleteModal" class="text-danger" data-toggle="modal" data-id="${supervisor.id}" data-name="${supervisor.name}">
                                        <i class="material-icons" data-toggle="tooltip" title="${deleteLabel}">&#xE872;</i>
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
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>    
    <script src="/js/table-config.js"></script>
    <script src="/js/field/field-management.js"></script>
</body>

</html>