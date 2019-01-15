<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><@spring.message "page.survey.select-ur" /></title>

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
            <div class="card-header text-white d-flex" style="background-color: #004900">
                <h2 class="col-sm-12"><@spring.message "card.title.select-ur" /></h2>
            </div>

            <div class="card-body table-responsive-md">

                <table id="mainTable" class="table table-striped table-hover">
                    <thead style="background-color: #004900; color: white">
                        <tr>
                            <th class="col-sm-3"><@spring.message "table.list.identification" /></th>
                            <th class="col-sm-3"><@spring.message "table.list.location" /></th>
                            <th class="col-sm-2"><@spring.message "table.list.city" /></th>
                            <th class="col-sm-2"><@spring.message "table.list.farmer" /> </th>
                            <th class="col-sm-2"><@spring.message "table.list.supervisor" /></th>
                        </tr>
                    </thead>
                    <tbody id="mainTable-body">

                        <#list fields as field>
                        	<#assign selectTip><@spring.message "table.list.select" /></#assign>
                        
                            <tr title="${selectTip}" style="cursor: pointer;" onclick="location.assign('/survey-field/field-form?fieldId=${field.id}')">   
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

            <div class="card-footer text-muted">
            </div>
        </form>
    </div>

    <!-- External JS libs -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>    
    <script src="/js/table-config.js"></script>
        
</body>

</html>