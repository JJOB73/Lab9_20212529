package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoPartidos;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;

        switch (action) {

            case "guardar":

                //System.out.println(request.getParameter("jornada"));
                //System.out.println(request.getParameter("fecha"));
                //System.out.println(request.getParameter("local"));
                //System.out.println(request.getParameter("visitante"));
                //System.out.println(request.getParameter("arbitro"));

                Partido partido = new Partido();
                Seleccion sel = new Seleccion();
                switch (request.getParameter("local")) {
                    case "Argentina":
                        sel.setIdSeleccion(3);
                        break;
                    case "Chile":
                        sel.setIdSeleccion(2);
                        break;
                    case "Peru":
                        sel.setIdSeleccion(1);
                        break;
                }
                partido.setSeleccionLocal(sel);
                Seleccion sele = new Seleccion();
                switch (request.getParameter("visitante")) {
                    case "Argentina":
                        sele.setIdSeleccion(3);
                        break;

                    case "Chile":
                        sele.setIdSeleccion(2);
                        break;

                    case "Peru":
                        sele.setIdSeleccion(1);
                        break;
                }
                partido.setSeleccionVisitante(sele);

                Arbitro ar = new Arbitro();

                switch (request.getParameter("arbitro")) {
                    case "Guillermo Guerrero":
                        ar.setIdArbitro(3);
                        break;

                    case "Diego Haro":
                        ar.setIdArbitro(4);
                        break;

                    case "Eber Aquino":
                        ar.setIdArbitro(2);
                        break;

                    case "Nestor Pitana":
                        ar.setIdArbitro(1);
                        break;
                }
                partido.setArbitro(ar);

                partido.setFecha(request.getParameter("fecha"));
                partido.setNumeroJornada(Integer.parseInt(request.getParameter("jornada")));

                DaoPartidos dao = new DaoPartidos();
                dao.crearPartido(partido);

                response.sendRedirect("PartidoServlet?action=lista");
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        switch (action) {
            case "lista":
                List<Partido> list = new ArrayList<>();
                DaoPartidos dao = new DaoPartidos();
                list = dao.listaDePartidos();

                request.setAttribute("listaPartidos", list);

                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "irCrear":
                request.getRequestDispatcher("partidos/form.jsp").forward(request, response);
                break;


        }

    }
}
