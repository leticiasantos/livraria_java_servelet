/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exemplo.biblioteca.servlet;

import com.exemplo.biblioteca.dao.LivrosDao;
import com.exemplo.biblioteca.entidades.Livro;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Doctum
 */
@WebServlet(name = "CriarLivroServlet", urlPatterns = {"/CriarLivroServlet"})
public class CriarLivroServlet extends HttpServlet {
    
    @Inject
    private LivrosDao dao;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">");
            out.println("<title>Livraria</title>");            
            out.println("</head>");
            out.println("<body>");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            String numPaginas = request.getParameter("paginas");
            
            Livro novoLivro = new Livro();
            novoLivro.setTitulo(titulo);
            novoLivro.setAutor(autor);
            novoLivro.setNumPaginas(Integer.valueOf(numPaginas));
            dao.adicionaLivro(novoLivro);
            out.println("<div class=\"alert alert-success\" role=\"alert\">Livro \"" + titulo + "\" adicionado com sucesso.</div>");
        } catch (Exception ex){
            out.println("<div class=\"alert alert-danger\" role=\"alert\">Erro ao adicionar um novo livro: "+ ex +"</div>");
        }
        out.println("<div class=\"card-footer text-muted\">\n" +
"                       <a href=\"/Livraria\">Voltar</a>\n" +
"                   </div>");
        out.println("</body></html>");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
