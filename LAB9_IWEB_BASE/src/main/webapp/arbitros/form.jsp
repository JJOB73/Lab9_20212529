<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.lab9_base.Bean.Arbitro" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
    <title>LAB 9</title>
</head>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp" />
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Crear un Árbitro</h1>
            <form name="crearArbitroForm" method="POST" action="<%=request.getContextPath()%>/ArbitroServlet?action=crear" onsubmit="return validarFormulario()">
                <div class="form-group">
                    <label>Nombre</label>
                    <br>
                    <label>Escribir el nombre completo para hacer la validación</label>
                    <input type="text" class="form-control" name="nombre" id="nombre">
                </div>
                <div class="form-group">
                    <label>País</label>
                    <select name="pais" class="form-control" id="pais">
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
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="<%= request.getContextPath()%>/ArbitroServlet" class="btn btn-danger">Cancelar</a>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

<script>
    const nombresArbitros = [];
    <%
        List<Arbitro> listaArbitros = (List<Arbitro>) request.getAttribute("listaArbitros");
        for (Arbitro ar : listaArbitros) {
    %>
    nombresArbitros.push("<%= ar.getNombre() %>");

    <%
        }
    %>

    function validarFormulario() {
        const nombreInput = document.getElementById("nombre");
        const paisSelect = document.getElementById("pais");

        const nombre = nombreInput.value.trim();
        const pais = paisSelect.value;
        if (nombre === "" || pais === ""){
            alert("Debe completar todos los campos.");
            nombreInput.value = "";
            paisSelect.value = "";
            return false;
        }

        if (nombresArbitros.includes(nombre)) {
            alert("El nombre del árbitro ya existe en la base de datos.");
            nombreInput.value = "";
            paisSelect.value = "";
            return false;
        }

        return true;
    }
</script>
</body>
</html>


