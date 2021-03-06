package com.pitchfinder.partita.controller;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Disponibilita")
    public class DisponibilitaServlet extends HttpServlet {
        /**
         *  doPost method.
         * @param req request
         * @param resp response
         * @throws ServletException
         * @throws IOException
         */
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doGet(req, resp);
        }

        /**
         *  doPost method.
         * @param req request
         * @param resp response
         * @throws ServletException
         * @throws IOException
         */
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/view/disponibilitaCampo/dareDisponibilita.jsp");
            dispatcher.forward(req, resp);
        }
    }

