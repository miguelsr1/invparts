/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.invparts.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author misanchez
 */
public class DynamicImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get image file.
            String file = request.getParameter("file");
            byte[] bytes;
            // Get image contents. C:\Users\Administrator
            //try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:/Users/Administrator/invParts/fotos/" + file))) {
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:/Users/DesarrolloPc/invParts/fotos/" + file))) {
                // Get image contents.
                bytes = new byte[in.available()];
                in.read(bytes);
            }

            // Write image contents to response.
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            Logger.getLogger(DynamicImageServlet.class.getName()).log(Level.INFO, "El proveedor con NIT: {0} no posee fotografia", request.getParameter("file"));
        }
    }
}