<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${pageTitle}</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    
    <link rel="stylesheet" href="/css/smart_wizard.css" type="text/css" />
    <link rel="stylesheet" href="/css/smart_wizard_theme_arrows.css" type="text/css" />
    <link rel='stylesheet'href='https://fonts.googleapis.com/css?family=Arimo'>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

<body style="font-family: 'Arimo'">
    <div class="container-fluid">

        <!-- Add Menu -->
        <#include "/menubar.ftl">

        <form action="/survey-field/create" method="post" class="card" style="margin: 15px">
            <input type="hidden" id="harvestId" name="harvestId" value="${harvestId}">

            <div class="card-header text-white" style="background-color: #004900">
                <h2>${pageTitle}</h2>
            </div>


            <div id="smartwizard" class="card-body">

                <input type="hidden" name="fieldId" value="${selectedField.id}">

				<ul>
					<li>
                        <a href="#cultivar-tab"><@spring.message "table.list.seed-name" /></a>
                    </li>
					<li>
                        <a href="#area-tab"><@spring.message "table.list.area" /></a>
                    </li>
					<li>
                        <a href="#productivity-tab"><@spring.message "table.list.productivity" /></a>
                    </li>
					<li>
                        <a href="#date-tab"><@spring.message "table.list.dates" /></a>
                    </li>
				</ul>

                <div class="tab-content">

                    <div id="cultivar-tab" class="card" style="margin-top: 15px">
                        <div class="card-header text-white" style="background-color: #004900">
                            <@spring.message "table.list.seed-name" />
                        </div>
                        <div class="card-body">
                            <div class="form-group ">
                                <label for="seedName"><@spring.message "table.list.name" /></label>
                                <input type="text" class="form-control" id="seedName" name="seedName" required autofocus maxlength="25" >
                            </div>

                            <div class="form-row col-md-6">
                                <div class="form-check col">
                                    <input class="form-check-input" type="checkbox" id="rustResistant" name="rustResistant">
                                    <label class="form-check-label" for="rustResistant">
                                        <@spring.message "table.list.rust-resitant" />
                                    </label>
                                </div>

                                <div class="form-check col">
                                    <input class="form-check-input" type="checkbox" id="bt" name="bt">
                                    <label class="form-check-label" for="bt">
                                        <@spring.message "table.list.bt" />
                                    </label>
                                </div>

                                <div class="form-check col">
                                    <input class="form-check-input" type="checkbox" id="sporeCollector" name="sporeCollector">
                                    <label class="form-check-label" for="sporeCollector">
                                        <@spring.message "table.list.spore-collector" />
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="area-tab" class="card" style="margin-top: 15px">
                        <div class="card-header text-white" style="background-color: #004900">
                            <@spring.message "table.list.area" />
                        </div>
                        <div class="card-body">
                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="totalArea"><@spring.message "table.list.total-area" /> (UO/UR) </label>
                                    <input type="number" step="0.01" class="form-control" id="totalArea" name="totalArea">
                                </div>

                                <div class="form-group col">
                                    <label for="totalPlantedArea"><@spring.message "table.list.total-planted-area" /> </label>
                                    <input type="number" step="0.01" class="form-control" id="totalPlantedArea" name="totalPlantedArea">
                                </div>

                                <div class="form-group col">
                                    <label for="plantPerMeter"><@spring.message "table.list.plant-per-meter" /> </label>
                                    <input type="number" step="0.01" class="form-control" id="plantPerMeter" name="plantPerMeter">
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="latitute"><@spring.message "table.list.latitude" /> </label>
                                    <input type="number" step="0.01" class="form-control" id="latitute" name="latitute">
                                </div>

                                <div class="form-group col">
                                    <label for="longitude"><@spring.message "table.list.longitude" /> </label>
                                    <input type="number" step="0.01" class="form-control" id="longitude" name="longitude">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="productivity-tab" class="card" style="margin-top: 15px">
                        <div class="card-header text-white" style="background-color: #004900">
                            <@spring.message "table.list.productivity" />
                        </div>
                        <div class="card-body">

                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="productivityField"><@spring.message "table.list.productivity-field" /> (UO/UR) </label>
                                    <input type="number" step="0.01" class="form-control" id="productivityField" name="productivityField">
                                </div>

                                <div class="form-group col">
                                    <label for="productivityFarmer"><@spring.message "table.list.productivity-farmer" /> </label>
                                    <input type="number" step="0.01" class="form-control" id="productivityFarmer" name="productivityFarmer">
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="separatedWeight" name="separatedWeight">
                                    <label class="form-check-label" for="separatedWeight">
                                        <@spring.message "table.list.separated-weight" />
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>

                    <div id="date-tab" class="card" style="margin-top: 15px">
                        <div class="card-header text-white" style="background-color: #004900">
                            <@spring.message "table.list.dates" />
                        </div>
                        <div class="card-body">

                            <div class="form-row">
                                <div class="form-group col">
                                    <label for="sowedDate"><@spring.message "table.list.sowed-date" /></label>
                                    <input type="date" class="form-control" id="sowedDate" name="sowedDate">
                                </div>
                                <div class="form-group col">
                                    <label for="emergenceDate"><@spring.message "table.list.emergence-date" /></label>
                                    <input type="date" class="form-control" id="emergenceDate" name="emergenceDate">
                                </div>
                                <div class="form-group col">
                                    <label for="harvestDate"><@spring.message "table.list.harvest-date" /></label>
                                    <input type="date" class="form-control" id="harvestDate" name="harvestDate">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card-footer text-muted">
                <#assign saveButton><@spring.message "modal.button.save-ru" /></#assign>
                <input style="display: none" id="saveButton" type="submit" class="btn btn-success float-right" value="${saveButton}" />
            </div>
        </form>
    </div>

    <!-- External JS libs -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <script src="/js/jquery.smartWizard.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            // Smart Wizard
            $('#smartwizard').smartWizard({
                lang: {  // Language variables
                    next: 'Próximo',
                    previous: 'Anterior'
                },
                theme: 'arrows',
                transitionEffect: 'fade', // Effect on navigation, none/slide/fade
                transitionSpeed: '400'
            });

            // Show conclusion button only in the last step
            $("#smartwizard").on("showStep", function(e, anchorObject, stepNumber, stepDirection) {
			if($('button.sw-btn-next').hasClass('disabled')){
				$('#saveButton').show(); // show the button extra only in the last page

			}else{
				$('#saveButton').hide();				
			}
	      });

        }); 
    </script>
</body>

</html>