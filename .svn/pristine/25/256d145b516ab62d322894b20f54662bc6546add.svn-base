/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigeco.ejb.utilities;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author csacanam
 */
public class CustomLogger
{
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;
    
    public static Logger logger;

    public static void setup(Class clase) throws IOException
    {
        // Obtener logger global para configurarlo

        logger = Logger.getLogger(clase.getName());
 

        // Agregar nivel INFO al logger
        logger.setLevel(Level.INFO);
        
        // Crear un formatter TXT
        fileTxt = new FileHandler("Logging.txt");
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // Crear un formatter HTML
        fileHTML = new FileHandler("Logging.html");
        formatterHTML = new CustomHtmlFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}
