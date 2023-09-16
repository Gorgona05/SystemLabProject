/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projectmockup;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import Presentation.Controller;
import Presentation.View;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author PABLO MORERA
 */
public class ProjectMockup {
    public static final String DEST = "/downloads/hello.pdf";

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        System.out.println("Hello World!");
//        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
//        Document document = new Document(pdf);
//        String line = "Hello! Welcome to iTextPdf";
//        document.add(new Paragraph(line));
//        document.close();
        Format f = new SimpleDateFormat("dd/MM/yy"); 
        String line = "Hello! Welcome to iTextPdf";

        FileOutputStream archivo = new FileOutputStream("Reporte.pdf");
        Document document = new Document();
        PdfWriter.getInstance(document, archivo);
        document.open();
        document.add(new Paragraph("Fecha: " + f.format(new Date())));
        document.add(new Paragraph(line));
        document.close();
        
        View vista = new View();
        Controller ctrl = new Controller(vista);
        vista.setTitle("Sistema Laboratorios");
        vista.setLocationRelativeTo(null);
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        vista.setVisible(true);
        
    }
}
