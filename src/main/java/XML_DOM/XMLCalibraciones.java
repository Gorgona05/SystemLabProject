
package XML_DOM;

import Logic.Calibraciones;
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
public class XMLCalibraciones {
    private static String xmlFilePath = "";

    public XMLCalibraciones(String filepath){
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
                
                Element calibraciones = document.createElement("Calibraciones");
                document.appendChild(calibraciones);
                                 
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
    
          public boolean AddCalibracion(Calibraciones calibracion ) throws TransformerConfigurationException, TransformerException
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
            NodeList instrumentoNodes = doc.getElementsByTagName("Calibración");
            for(int i = 0; i < instrumentoNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentoNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    String numero = instrumentoElement.getAttribute("Número");

                    if(calibracion.getNumero().equals(numero))
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
                
                Node root = doc.getElementsByTagName("Calibraciones").item(0);

                Element newInstrumento = doc.createElement("Calibración");
                
                Attr attrNumero = doc.createAttribute("Número");
                attrNumero.setValue(calibracion.getNumero());
                newInstrumento.setAttributeNode(attrNumero);

                Element fechaElement = doc.createElement("Fecha");
                fechaElement.appendChild(doc.createTextNode(calibracion.getFecha()));
                newInstrumento.appendChild(fechaElement);

                Element medicionesElement = doc.createElement("Mediciones");
                medicionesElement.appendChild(doc.createTextNode(calibracion.getMediciones()));
                newInstrumento.appendChild(medicionesElement);
                
     
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
    public boolean UpdateCalibracion(Calibraciones calibracion) throws TransformerConfigurationException, TransformerException
    {
        boolean result = false;
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile);
            NodeList instrumentosNodes = doc.getElementsByTagName("Calibración");
            for(int i = 0; i < instrumentosNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentosNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    //int id = Integer.parseInt(userElement.getAttribute("id"));
                     
                    if(calibracion.getNumero().equals(instrumentoElement.getAttribute("Número")))
                    {
                        instrumentoElement.getElementsByTagName("Fecha").item(0).setTextContent(calibracion.getFecha());
                        instrumentoElement.getElementsByTagName("Mediciones").item(0).setTextContent(calibracion.getMediciones());
                        
                       
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
    
    public boolean deleteCalibracion(Calibraciones calibracion) throws TransformerException{
        boolean result = false;
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile); 
            NodeList instrumentosNodes = doc.getElementsByTagName("Calibración");
            for(int i = 0; i < instrumentosNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentosNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                     
                    if(calibracion.getNumero().equals(instrumentoElement.getAttribute("Número")))
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
    
     public boolean recoverCalibracion(List<Calibraciones> calibraciones) {
        boolean result = false;

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            
            NodeList instrumentosNodes = doc.getElementsByTagName("Calibración");
            for (int i = 0; i < instrumentosNodes.getLength(); i++) {
                Node instrumentoNode = instrumentosNodes.item(i);
                if (instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    
                    String numero = instrumentoElement.getAttribute("Número");
                    String fecha = instrumentoElement.getElementsByTagName("Fecha").item(0).getTextContent();
                    String mediciones = instrumentoElement.getElementsByTagName("Mediciones").item(0).getTextContent();
                    String tipo = instrumentoElement.getElementsByTagName("Tipo").item(0).getTextContent();
                    calibraciones.add(new Calibraciones(numero, fecha, mediciones));
                }
            }
            result = true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
             ex.printStackTrace();
        }

        return result;
    }
    
}
