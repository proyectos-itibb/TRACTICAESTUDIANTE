package com.emergentes.controller;

import com.emergentes.beam.Beamestudiante;
import com.emergentes.entidades.Estudiante;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class mainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // System.out.println("estamos en el servlet");
        //nuevo();
        //mostrar();
        //editar();
        //mostrar();
        //eliminar();

        Beamestudiante dao = new Beamestudiante();
        Estudiante es = new Estudiante();
        int id;
        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("estudiante", es);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                break;
            case "edit":
                //Avisocontrolleer
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    //obtenener el objeto correspondiente al registro
                    // es = dao.editar(e);
                } catch (Exception ex) {
                    System.out.println("error al obtener el registro" + ex.getMessage());
                }

                //colocar como atributo'
                request.setAttribute("estudiante", es);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);

                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));

                try {
                    dao.eliminar(id);
                } catch (Exception ex) {
                    System.out.println("error eliminar" + ex.getMessage());
                }

                response.sendRedirect("MainController");

                break;

            case "view":
                List<Estudiante> lista = new ArrayList<Estudiante>();
                try {
                    lista = dao.listarTodos();
                } catch (Exception ex) {
                    System.out.println("error al listar" + ex.getMessage());
                }
                request.setAttribute("estudiante", lista);
                request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
                break;
            default:
                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void nuevo() {
        Beamestudiante dao = new Beamestudiante();

        Estudiante e = new Estudiante();
        e.setNombre("Daniela ");
        e.setApellidos("Huanca Ramirez");
        e.setEmail("danihuanca@gmail.com");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            e.setFechaNacimiento(sdf.parse("1998-06-07"));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        dao.insertar(e);
    }

    private void editar() {
        Beamestudiante dao = new Beamestudiante();
        Integer id = 2;

        Estudiante e = dao.buscar(id);

        e.setNombre("Rolando");
        e.setEmail("ricky@gmail.com");
        dao.editar(e);

    }

    private void eliminar() {
        Beamestudiante dao = new Beamestudiante();
        Integer id = 5;

        dao.eliminar(id);
    }

    private void mostrar() {
        Beamestudiante dao = new Beamestudiante();
        List<Estudiante> lista = dao.listarTodos();

        for (Estudiante item : lista) {
            System.out.println(item.toString());
        }
    }

}
   
    


//