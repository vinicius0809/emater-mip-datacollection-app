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

    <main class="container-fluid">

        <!-- Add Menu -->
        <#include "/menubar.ftl">

        <div class="jumbotron">
            <h1>Oooooops!</h1> 
            <p>Parece que alguma coisa deu errado!</p> 
        </div>
        
        <div class="alert alert-warning">
            <p>Por favor, tente novamente. Se o problema persistir, entre em contato com o suporte.</p>
            <p>Você pode retornar para a página principal clicando <a href="/" class="alert-link">aqui</a>.</p>
        </div>    
        
    </main>

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>

</html>