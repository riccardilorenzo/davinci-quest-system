<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DaVinci Quest System</title>
</head>
<body>
    <div class="container-fluid d-flex flex-column h-100">
        <div class="row mb-5">
            <h1 class="display-4 text-center">Benvenuto nel sistema quest DaVinci!</h1>
        </div>
        <div class="row">
                <form method="POST" action="home">
                    <div class="mb-3">
                        <input type="text" class="form-control" name="login" placeholder="Inserisci il tuo ID" maxlength="14" required>
                    </div>
                    <div class="col d-flex justify-content-center">
                        <button type="submit" name="send" class="btn btn-primary">Entra</button>
                    </div>
                </form>
        </div>

        <c:if test="${ sessionScope.error != null }">
            <div class="row">
                <div class="col d-flex justify-content-center">
                    <div class="alert alert-danger mt-5">
                        ${ sessionScope.error }
                        <c:remove var="error" scope="session" />
                        <% HttpSession sess = request.getSession(false);
                            if (sess != null) sess.invalidate(); %>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row mt-auto mb-3">
            <figure class="text-center">
                <blockquote class="blockquote">
                    <p>Nel buio dello spazio d'Anaconda non son mai sazio.</p>
                </blockquote>
                <figcaption class="blockquote-footer">
                    Vostro Sindacalista Anno Domini 2021, <cite title="Source Title">DaVinci Corporation</cite>
                </figcaption>
            </figure>
        </div>
    </div>

    <style>
        html, body {
            height: 100%;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>