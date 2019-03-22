<!-- The Modal -->
<div class="modal fade" id="list-pest-modal">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
      <div class="modal-content">
  
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Insetos Praga Encontrados na Amostra</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
  
        <!-- Modal body -->
        <div class="modal-body table-responsive-md">
                  <table id="mainTable" class="table table-striped table-hover">
                    <thead style="background-color: #004900; color: white">
                        <tr>
                            <th><@spring.message "table.list.pest-name" /></th>
                            <th><@spring.message "table.list.average-found" /></th>
                        </tr>
                    </thead>
                    <tbody id="mainTable-body">
                        <#list pestOccurrences as aPestOccurrence>
                            <tr>
                                <td>${aPestOccurrence.pest.usualName}</td>
                                <td>${aPestOccurrence.value}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
        </div>
  
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
        </div>
  
      </div>
    </div>
  </div>