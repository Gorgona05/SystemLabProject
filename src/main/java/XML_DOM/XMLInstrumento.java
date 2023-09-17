/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML_DOM;

import Logic.Instrumento;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author David
 */
public class XMLInstrumento {
     private static String xmlFilePath = "";

    public XMLInstrumento(String filepath){
        xmlFilePath = filepath;
        CreateFile();
    }
    private void CreateFile()
    {
        try {
        
            File xmlFile = new File(xmlFilePath);
        
            if(!xmlFile.exists())
            {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                
                Element instrumentos = document.createElement("Instrumentos");
                document.appendChild(instrumentos);
                                 
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                transformer.transform(domSource, streamResult);

                System.out.println("Done add the instrument to XML File");
            }
        } catch (ParserConfigurationException | TransformerException pce) {
            System.out.println(pce.getMessage());
            
        }
    }
    
     public boolean AddInstrumento(Instrumento instrumento) throws TransformerConfigurationException, TransformerException
    {
       boolean result = false;
        boolean idexist = false;
      
             
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            if(!xmlFile.exists())
                return result;
            NodeList instrumentoNodes = doc.getElementsByTagName("Instrumento");
            for(int i = 0; i < instrumentoNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentoNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    String serie = instrumentoElement.getAttribute("Serie");

                    if(instrumento.getSerie().equals(serie))
                    {
                        idexist = true;
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
         pce.printStackTrace();
        }
        try {
            if(!idexist)
            {
                File xmlFile = new File(xmlFilePath);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);
                
                Node root = doc.getElementsByTagName("Instrumentos").item(0);

                Element newInstrumento = doc.createElement("Instrumento");
                
                Attr attrSerie = doc.createAttribute("Serie");
                attrSerie.setValue(instrumento.getSerie());
                newInstrumento.setAttributeNode(attrSerie);

                Element descripcionElement = doc.createElement("Descripción");
                descripcionElement.appendChild(doc.createTextNode(instrumento.getDescripcion()));
                newInstrumento.appendChild(descripcionElement);

                Element minimoElement = doc.createElement("Mínimo");
                minimoElement.appendChild(doc.createTextNode(instrumento.getMinimo()));
                newInstrumento.appendChild(minimoElement);
                
                Element maximoElement = doc.createElement("Máximo");
                maximoElement.appendChild(doc.createTextNode(instrumento.getMaximo()));
                newInstrumento.appendChild(maximoElement);
                
                Element toleranciaElement = doc.createElement("Tolerancia");
                toleranciaElement.appendChild(doc.createTextNode(instrumento.getTolerancia()));
                newInstrumento.appendChild(toleranciaElement);
                
                Element tipoElement = doc.createElement("Tipo");
                tipoElement.appendChild(doc.createTextNode(instrumento.getTipo()));
                newInstrumento.appendChild(tipoElement);

                root.appendChild(newInstrumento);
                 
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(doc);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                transformer.transform(domSource, streamResult);

                result = true;
            }

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException pce) {
            pce.printStackTrace();
        }
        
        return result;
    }
      public boolean UpdateInstrumento(Instrumento instrumento) throws TransformerConfigurationException, TransformerException
    {
        boolean result = false;
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile);
            NodeList instrumentosNodes = doc.getElementsByTagName("Instrumento");
            for(int i = 0; i < instrumentosNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentosNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    //int id = Integer.parseInt(userElement.getAttribute("id"));
                     
                    if(instrumento.getSerie().equals(instrumentoElement.getAttribute("Serie")))
                    {
                        instrumentoElement.getElementsByTagName("Descripción").item(0).setTextContent(instrumento.getDescripcion());
                        instrumentoElement.getElementsByTagName("Mínimo").item(0).setTextContent(instrumento.getMinimo()); 
                        instrumentoElement.getElementsByTagName("Máximo").item(0).setTextContent(instrumento.getMaximo());
                        instrumentoElement.getElementsByTagName("Tolerancia").item(0).setTextContent(instrumento.getTolerancia());
                        instrumentoElement.getElementsByTagName("Tipo").item(0).setTextContent(instrumento.getTipo());
                        
               
                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource domSource = new DOMSource(doc);
                        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                        transformer.transform(domSource, streamResult);
                        
                        System.out.println("Done updating the instrument to XML File");
                        
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
               pce.printStackTrace();
        }
        
        return result;
    }
    
    public boolean deleteInstrumento(Instrumento instrumento) throws TransformerException{
        boolean result = false;
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile); 
            NodeList instrumentosNodes = doc.getElementsByTagName("Instrumento");
            for(int i = 0; i < instrumentosNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentosNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                     
                    if(instrumento.getSerie().equals(instrumentoElement.getAttribute("Serie")))
                    {
                        instrumentoElement.getParentNode().removeChild( instrumentoElement);

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        DOMSource domSource = new DOMSource(doc);
                        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                        transformer.transform(domSource, streamResult);
                        
                        System.out.println("Done updating the instrument to XML File");
                        
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
               pce.printStackTrace();
        }
        
        return result;
        
    }
    
     public boolean recoverInstrumento(List<Instrumento> instrumentos) {
        boolean result = false;

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            
            NodeList instrumentosNodes = doc.getElementsByTagName("Instrumento");
            for (int i = 0; i < instrumentosNodes.getLength(); i++) {
                Node instrumentoNode = instrumentosNodes.item(i);
                if (instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    
                    String serie = instrumentoElement.getAttribute("Serie");
                    String descripcion = instrumentoElement.getElementsByTagName("Descripción").item(0).getTextContent();
                    String minimo = instrumentoElement.getElementsByTagName("Mínimo").item(0).getTextContent();
                    String maximo = instrumentoElement.getElementsByTagName("Máximo").item(0).getTextContent();
                    String tolerancia = instrumentoElement.getElementsByTagName("Tolerancia").item(0).getTextContent();
                    String tipo = instrumentoElement.getElementsByTagName("Tipo").item(0).getTextContent();
                    instrumentos.add(new Instrumento(serie,tipo,descripcion,minimo,maximo,tolerancia));
                }
            }
            result = true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
             ex.printStackTrace();
        }

        return result;
    }
}
