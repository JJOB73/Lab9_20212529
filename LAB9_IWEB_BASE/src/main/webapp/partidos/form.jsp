<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear un Partido de Clasificatorias</title>
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp" />
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Crear un Partido de Clasificatorias</h1>
            <label>Solo se pueden crear partido con Peru, Chile y Argentina debido a que no hay más selecciones en la base de datos. </label>
            <!-- Formulario para registrar un partido -->
            <form id="formPartido" method="POST" action="<%=request.getContextPath()%>/PartidoServlet?action=guardar">
                <div class="form-group">
                    <label>Jornada</label>
                    <input type="number" class="form-control" name="jornada" id="jornada">
                </div>
                <div class="form-group">
                    <label>Fecha</label>
                    <input class="form-control" id="fecha" name="fecha" type="date"/>
                </div>
                <div class="form-group">
                    <label>Selección local</label>
                    <select name="local" id="local" class="form-control">
                        <option value="">Seleccione</option>
                        <option value="Argentina">Argentina</option>
                        <option value="Bolivia">Bolivia</option>
                        <option value="Brasil">Brasil</option>
                        <option value="Chile">Chile</option>
                        <option value="Colombia">Colombia</option>
                        <option value="Ecuador">Ecuador</option>
                        <option value="Paraguay">Paraguay</option>
                        <option value="Peru">Perú</option>
                        <option value="Uruguay">Uruguay</option>
                        <option value="Venezuela">Venezuela</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Selección Visitante</label>
                    <select name="visitante" id="visitante" class="form-control">
                        <option value="">Seleccione</option>
                        <option value="Peru">Perú</option>
                        <option value="Argentina">Argentina</option>
                        <option value="Chile">Chile</option>
                        <option value="Bolivia">Bolivia</option>
                        <option value="Brasil">Brasil</option>
                        <option value="Colombia">Colombia</option>
                        <option value="Ecuador">Ecuador</option>
                        <option value="Paraguay">Paraguay</option>
                        <option value="Uruguay">Uruguay</option>
                        <option value="Venezuela">Venezuela</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Árbitro</label>
                    <select name="arbitro" id="arbitro" class="form-control">
                        <option value="Nestor Pitana">Néstor Pitana</option>
                        <option value="Eber Aquino">Eber Aquino</option>
                        <option value="Guillermo Guerrero">Guillermo Guerrero</option>
                        <option value="Diego Haro">Diego Haro</option>

                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="<%=request.getContextPath()%>/PartidoServlet" class="btn btn-danger">Cancelar</a>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("formPartido").addEventListener("submit", function(event) {
            const jornada = document.getElementById("jornada").value.trim();
            const fecha = document.getElementById("fecha").value.trim();
            const local = document.getElementById("local").value;
            const visitante = document.getElementById("visitante").value;
            const arbitro = document.getElementById("arbitro").value;

            if (!jornada || !fecha || local === "" || visitante === "" || arbitro === "") {
                alert("Todos los campos son obligatorios.");
                event.preventDefault();
                document.getElementById("formPartido").reset();
                return;
            }

            if (local === visitante) {
                alert("La selección local y visitante deben ser distintas.");
                event.preventDefault();
                document.getElementById("formPartido").reset();
                return;
            }
        });
    });
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>

