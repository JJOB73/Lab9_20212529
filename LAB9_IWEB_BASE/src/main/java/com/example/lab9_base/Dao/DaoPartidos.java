package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase{
    public ArrayList<Partido> listaDePartidos() {
        ArrayList<Partido> listaPartidos = new ArrayList<>();

        String query = "SELECT p.idPartido, p.fecha, sl.nombre AS seleccion_local, sv.nombre AS seleccion_visitante, " +
                "es.nombre AS estadio, ar.nombre AS arbitro, p.numeroJornada AS jornada " +
                "FROM partido p " +
                "JOIN seleccion sl ON p.seleccionLocal = sl.idSeleccion " +
                "JOIN seleccion sv ON p.seleccionVisitante = sv.idSeleccion " +
                "JOIN arbitro ar ON p.arbitro = ar.idArbitro " +
                "JOIN estadio es ON sl.estadio_idEstadio = es.idEstadio;";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Partido partido = new Partido();
                Seleccion seleccionLocal = new Seleccion();
                Seleccion seleccionVisitante = new Seleccion();
                Estadio estadio = new Estadio();
                Arbitro arbitro = new Arbitro();

                partido.setIdPartido(rs.getInt("idPartido"));
                partido.setFecha(rs.getString("fecha"));

                seleccionLocal.setNombre(rs.getString("seleccion_local"));

                seleccionVisitante.setNombre(rs.getString("seleccion_visitante"));

                estadio.setNombre(rs.getString("estadio"));
                seleccionLocal.setEstadio(estadio);

                arbitro.setNombre(rs.getString("arbitro"));
                partido.setArbitro(arbitro);

                partido.setNumeroJornada(rs.getInt("jornada"));

                partido.setSeleccionLocal(seleccionLocal);
                partido.setSeleccionVisitante(seleccionVisitante);

                listaPartidos.add(partido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPartidos;
    }


    public void crearPartido(Partido partido) {
        String query = "INSERT INTO partido (seleccionLocal, seleccionVisitante, arbitro, fecha, numeroJornada) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, partido.getSeleccionLocal().getIdSeleccion());
            stmt.setInt(2, partido.getSeleccionVisitante().getIdSeleccion());
            stmt.setInt(3, partido.getArbitro().getIdArbitro());
            stmt.setString(4, partido.getFecha());
            stmt.setInt(5,partido.getNumeroJornada());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
