/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.FileCompressor.Servlet;

import com.FileCompressor.Huffman.HuffmanCompressor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Jay Hire
 */
@WebServlet(name = "compressorservlet", urlPatterns = {"/compressFile"})
@MultipartConfig
public class compressorservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet compressorservlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet compressorservlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
//        processRequest(request, response);
        
                Part filePart = request.getPart("file");
               String fileName = filePart.getSubmittedFileName();
               InputStream fileContent = filePart.getInputStream();

               // Save the uploaded file to a temporary location
               String tempDir = getServletContext().getRealPath("/") + "temp/";
               File tempFile = new File(tempDir, fileName);
               if (!tempFile.getParentFile().exists()) {
                   tempFile.getParentFile().mkdirs();
               }

               FileOutputStream fos = new FileOutputStream(tempFile);
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = fileContent.read(buffer)) != -1) {
                   fos.write(buffer, 0, bytesRead);
               }
               fos.close();
               fileContent.close();

               // Compress the file
               HuffmanCompressor compressor = new HuffmanCompressor();
               String outputFilePath = tempDir + "compressed_" + fileName;
               compressor.huffCompressor(tempFile.getAbsolutePath(), outputFilePath);

               // Send the compressed file for download
               File compressedFile = new File(outputFilePath);
               FileInputStream fis = new FileInputStream(compressedFile);

               response.setContentType("application/octet-stream");
               response.setHeader("Content-Disposition", "attachment; filename=" + compressedFile.getName());

               OutputStream os = response.getOutputStream();
               while ((bytesRead = fis.read(buffer)) != -1) {
                   os.write(buffer, 0, bytesRead);
               }

               os.flush();
               fis.close();
               compressedFile.delete(); // Clean up the temporary file

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