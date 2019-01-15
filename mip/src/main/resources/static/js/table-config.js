$(document).ready(function () {
    $('#mainTable').DataTable({
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