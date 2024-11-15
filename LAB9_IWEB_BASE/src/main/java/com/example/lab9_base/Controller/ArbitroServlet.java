package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        switch (action) {

            case "buscar":
                String tipoBusqueda = request.getParameter("tipo");
                String valorBusqueda = request.getParameter("buscar");
                DaoArbitros daoArbitros = new DaoArbitros();
                ArrayList<Arbitro> resultados;

                if ("pais".equals(tipoBusqueda)) {
                    resultados = daoArbitros.busquedaPais(valorBusqueda);
                } else {
                    resultados = daoArbitros.busquedaNombre(valorBusqueda);
                }

                HttpSession session = request.getSession();
                request.setAttribute("listaArbitros", resultados);
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "crear":
                //System.out.println(request.getParameter("nombre"));
                //System.out.println(request.getParameter("pais"));
                Arbitro ar  = new Arbitro();
                ar.setNombre(request.getParameter("nombre"));
                ar.setPais(request.getParameter("pais"));
                DaoArbitros Dao = new DaoArbitros();
                Dao.crearArbitro(ar);

                response.sendRedirect("ArbitroServlet");

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        switch (action) {
            case "lista":
                response.setContentType("text/html");
                List<Arbitro> lista = new ArrayList<Arbitro>();
                DaoArbitros dao = new DaoArbitros();
                lista= dao.listaArbitros();
                HttpSession session = request.getSession();
                request.setAttribute("listaArbitros", lista);
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "irCrear":
                response.setContentType("text/html");
                List<Arbitro> list = new ArrayList<Arbitro>();
                DaoArbitros Dao = new DaoArbitros();
                list= Dao.listaArbitros();
                HttpSession se = request.getSession();
                request.setAttribute("listaArbitros", list);
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                int id = Integer.parseInt(request.getParameter("id"));
                System.out.println(id);
                DaoArbitros daoA = new DaoArbitros();
                daoA.borrarArbitro(id);
                response.sendRedirect("ArbitroServlet");
                break;
        }
    }
}
