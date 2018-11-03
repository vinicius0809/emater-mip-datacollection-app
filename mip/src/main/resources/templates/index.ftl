<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Page Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

    <!-- Material icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

<body>

    <!-- Add Menu -->
    <#include "/menubar.ftl">

    <main class="container">

        <section id="top-panel-section" class="row" style="margin-top: 1rem">

            <div class="card col border-primary" style="padding: 0rem; margin-right: 1rem">
                <div class="card-body bg-primary text-white flex-row text-center">
                    <span>
                        <i class="material-icons" style="font-size: 48px">
                            home
                        </i>
                    </span>
                    <span class="float-right">
                        <h2 class="card-title">5</h5>
                            <p class="card-text">URs Cadastradas</p>
                    </span>
                </div>
                <div class="card-footer text-primary">
                    <a href="#">Criar nova UR</a>
                </div>
            </div>

            <div class="card col border-success" style="padding: 0rem; margin-right: 1rem">
                <div class="card-body bg-success text-white flex-row text-center">
                    <span>
                        <i class="material-icons" style="font-size: 48px">
                            home
                        </i>
                    </span>
                    <span class="float-right">
                        <h2 class="card-title">5</h5>
                            <p class="card-text">URs Cadastradas</p>
                    </span>
                </div>
                <div class="card-footer text-success">
                    <a href="#" class="text-success">Criar nova UR</a>
                </div>
            </div>

            <div class="card col border-warning" style="padding: 0rem; margin-right: 1rem">
                <div class="card-body bg-warning text-white flex-row text-center">
                    <span>
                        <i class="material-icons" style="font-size: 48px">
                            home
                        </i>
                    </span>
                    <span class="float-right">
                        <h2 class="card-title">5</h5>
                            <p class="card-text">URs Cadastradas</p>
                    </span>
                </div>
                <div class="card-footer text-warning">
                    <a href="#" class="text-warning">Criar nova UR</a>
                </div>
            </div>


            <div class="card col border-danger" style="padding: 0rem; margin-right: 1rem">
                <div class="card-body bg-danger text-white flex-row text-center">
                    <span>
                        <i class="material-icons" style="font-size: 48px">
                            home
                        </i>
                    </span>
                    <span class="float-right">
                        <h2 class="card-title">5</h5>
                            <p class="card-text">URs Cadastradas</p>
                    </span>
                </div>
                <div class="card-footer text-danger">
                    <a href="#" class="text-danger">Criar nova UR</a>
                </div>
            </div>

        </section>

        <section id="central-panel-section" class="row" style="margin-top: 1rem">

            <article id="right-centered-panel" class="col-sm-8">
                
                <div class="card" style="margin-top: 1rem">
                    <div class="card-header">
                        Flutuação das Pragas
                    </div>
                    <div id="pie-chart"></div>
                </div>                    

            </article>

            <article id="left-panel" class="col-sm-4">

                <div class="card" style="margin-top: 1rem">
                    <div class="card-header">
                        Últimas amostras coletadas
                    </div>
                    <div class="card" style="margin: 1rem">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Cras justo odio</li>
                            <li class="list-group-item">Dapibus ac facilisis in</li>
                            <li class="list-group-item">Vestibulum at eros</li>
                        </ul>

                    </div>
                </div>
            </article>

        </section>
    </main>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <!-- Plotly.js -->
    <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>

    <!-- Create plots -->
    <script>
        var data = [{
            values: [19, 26, 55],
            labels: ['Lagarta da soja', 'Falsa medideira', 'Lagarta das vagens'],
            type: 'pie'
        }];

        Plotly.newPlot('pie-chart', data);
    </script>
</body>

</html>