/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic.Service;

import Data.Data;
import Logic.TipoInstrumento;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Pamela
 */

public class ServiceTipoInstrumento {
    private static ServiceTipoInstrumento theInstance;

    public static ServiceTipoInstrumento instance(){
        if (theInstance == null) theInstance = new ServiceTipoInstrumento();
        return theInstance;
    }
    private Data data;

    private ServiceTipoInstrumento(){
    }
    
    public void uptadeData(Data dat){
        data = dat;
    }

    //================= TIPOS DE INSTRUMENTO ============
    public void create(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTiposInstrumentos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result==null) data.getTiposInstrumentos().add(e);
        else throw new Exception("Tipo ya existe");
    }

    public TipoInstrumento read(TipoInstrumento e) throws Exception{
        TipoInstrumento result = data.getTiposInstrumentos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) return result;
        else throw new Exception("Tipo no existe");
    }

    public void update(TipoInstrumento e) throws Exception{
        TipoInstrumento result;
        try{
            result = this.read(e);
            data.getTiposInstrumentos().remove(result);
            data.getTiposInstrumentos().add(e);
        }catch (Exception ex) {
            throw new Exception("Tipo no existe");
        }
    }

    public void delete(TipoInstrumento e) throws Exception{
        data.getTiposInstrumentos().remove(e);
     }

    public List<TipoInstrumento> search(TipoInstrumento e){
        return data.getTiposInstrumentos().stream()
                .filter(i->i.getNombre().contains(e.getNombre()))
                .sorted(Comparator.comparing(TipoInstrumento::getNombre))
                .collect(Collectors.toList());
    }
    public boolean ExistInstrumento(TipoInstrumento e){
        TipoInstrumento result = data.getTiposInstrumentos().stream()
                .filter(i->i.getCodigo().equals(e.getCodigo())).findFirst().orElse(null);
        if (result!=null) 
                return true;
        else
            return false;
    }
    public int getPosicionPorNombre(String nombre) {
        List<TipoInstrumento> instrumentos = data.getTiposInstrumentos();

        for (int i = 0; i < instrumentos.size(); i++) {
            if (instrumentos.get(i).getNombre().equals(nombre)) {
                return i;              }
        }
        return -1;  
    }
    
    public void reporteTiposInstrumento() throws FileNotFoundException, DocumentException{
        Format f = new SimpleDateFormat("dd/MM/yy"); 
        FileOutputStream file = new FileOutputStream("Reporte Tipos de Instrumento.pdf");
        Document doc = new Document();
        PdfWriter.getInstance(doc,file);
        doc.open();
        doc.add(new Paragraph("Fecha: " + f.format(new Date())));
        doc.add(Chunk.NEWLINE);
        Paragraph title = new Paragraph("Reporte de Tipos de instrumento");
        title.setAlignment(1);
        doc.add(title);
        doc.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        
        PdfPCell codigoCell = new PdfPCell(new Phrase("Código"));
        codigoCell.setBackgroundColor(BaseColor.BLUE);
        PdfPCell nombreCell = new PdfPCell(new Phrase("Nombre"));
        nombreCell.setBackgroundColor(BaseColor.BLUE);
        PdfPCell unidadCell = new PdfPCell(new Phrase("Unidad"));
        unidadCell.setBackgroundColor(BaseColor.BLUE);
        
        table.addCell(codigoCell);
        table.addCell(nombreCell);
        table.addCell(unidadCell);
        
        for (TipoInstrumento tipoInstrumento: data.getTiposInstrumentos()){
            table.addCell(tipoInstrumento.getCodigo());
            table.addCell(tipoInstrumento.getNombre());
            table.addCell(tipoInstrumento.getUnidad());
            
        }
        doc.add(table);

        doc.close();
        System.out.println ("Pdf Created");
    }
    
 }
