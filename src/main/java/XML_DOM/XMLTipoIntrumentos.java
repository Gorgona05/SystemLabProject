
package XML_DOM;

import Logic.Calibraciones;
import Logic.Instrumento;
import Logic.TipoInstrumento;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
/**
 *
 * @author David
 */
public class XMLTipoIntrumentos {
    private static String xmlFilePath = "";

    public XMLTipoIntrumentos(String filepath){
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
                
                Element laboratorioIndustrial = document.createElement("TiposInstrumentos");
                document.appendChild(laboratorioIndustrial);
                
                Element inst1 = document.createElement("TipoInstrumento");
                laboratorioIndustrial.appendChild(inst1);
                Attr attr1 = document.createAttribute("Codigo");
                attr1.setValue("TER");
                inst1.setAttributeNode(attr1);
                 
                Element nom1 = document.createElement("Nombre");
                nom1.appendChild(document.createTextNode("Termómetro"));
                inst1.appendChild(nom1);
                
                Element uni1 = document.createElement("Unidad");
                uni1.appendChild(document.createTextNode("Grados Celsius"));
                inst1.appendChild(uni1);

                Element inst2 = document.createElement("TipoInstrumento");
                laboratorioIndustrial.appendChild(inst2);
                 Attr attr2 = document.createAttribute("Codigo");
                attr2.setValue("BAR");
                inst2.setAttributeNode(attr2);
                 
                Element nom2 = document.createElement("Nombre");
                nom2.appendChild(document.createTextNode("Barómetro"));
                inst2.appendChild(nom2);
                
                   
                Element uni2 = document.createElement("Unidad");
                uni2.appendChild(document.createTextNode("PSI"));
                inst2.appendChild(uni2);

                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));

                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging

                transformer.transform(domSource, streamResult);

                System.out.println("Done add the instrument to XML File");
            }
        } catch (ParserConfigurationException | TransformerException pce) {
            System.out.println(pce.getMessage());
            
        }
    }
    
     public boolean AddTipoInstrumento(TipoInstrumento instrumento) throws TransformerConfigurationException, TransformerException
    {
       boolean result = false;
        boolean idexist = false;
      
        //----------------Check if user id already exists--------------
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            if(!xmlFile.exists())
                return result;
            NodeList instrumentoNodes = doc.getElementsByTagName("TipoInstrumento");
            for(int i = 0; i < instrumentoNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentoNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    String codigo = instrumentoElement.getAttribute("Codigo");

                    if(instrumento.getCodigo().equals(codigo))
                    {
                        idexist = true;
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
         pce.printStackTrace();
        }
        
        
        
        //---------------If user id does not exits---------------------
        try {
            if(!idexist)
            {
                File xmlFile = new File(xmlFilePath);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);
                
                Node root = doc.getElementsByTagName("TiposInstrumentos").item(0);

                Element newInstrumento = doc.createElement("TipoInstrumento");
                
                Attr attrCodigo = doc.createAttribute("Codigo");
                attrCodigo.setValue(instrumento.getCodigo());
                newInstrumento.setAttributeNode(attrCodigo);

                Element nombreElement = doc.createElement("Nombre");
                nombreElement.appendChild(doc.createTextNode(instrumento.getNombre()));
                newInstrumento.appendChild(nombreElement);

                Element unidadElement = doc.createElement("Unidad");
                unidadElement.appendChild(doc.createTextNode(instrumento.getUnidad()));
                newInstrumento.appendChild(unidadElement);

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
    
    public boolean UpdateTipoInstrumento(TipoInstrumento instrumento) throws TransformerConfigurationException, TransformerException
    {
        boolean result = false;
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile);
            NodeList instrumentosNodes = doc.getElementsByTagName("TipoInstrumento");
            for(int i = 0; i < instrumentosNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentosNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    //int id = Integer.parseInt(userElement.getAttribute("id"));
                     
                    if(instrumento.getCodigo().equals(instrumentoElement.getAttribute("Codigo")))
                    {
                        instrumentoElement.getElementsByTagName("Nombre").item(0).setTextContent(instrumento.getNombre());
                        instrumentoElement.getElementsByTagName("Unidad").item(0).setTextContent(instrumento.getUnidad()); 
                     
                        
               
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
    
    public boolean deleteTipoInstrumento(TipoInstrumento instrumento) throws TransformerException{
        boolean result = false;
        
        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            if(!xmlFile.exists())
                return result;
            Document doc = builder.parse(xmlFile); 
            NodeList instrumentosNodes = doc.getElementsByTagName("TipoInstrumento");
            for(int i = 0; i < instrumentosNodes.getLength(); i++) {            
                Node instrumentoNode = instrumentosNodes.item(i);
                if(instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                     
                    if(instrumento.getCodigo().equals(instrumentoElement.getAttribute("Codigo")))
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
    
    public boolean recoverTipoInstrumento(List<TipoInstrumento> instrumentos) {
        boolean result = false;

        try {
            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            
            NodeList instrumentosNodes = doc.getElementsByTagName("TipoInstrumento");
            for (int i = 0; i < instrumentosNodes.getLength(); i++) {
                Node instrumentoNode = instrumentosNodes.item(i);
                if (instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    
                    String codigo = instrumentoElement.getAttribute("Codigo");
                    String nombre = instrumentoElement.getElementsByTagName("Nombre").item(0).getTextContent();
                    String unidad = instrumentoElement.getElementsByTagName("Unidad").item(0).getTextContent();
                    instrumentos.add(new TipoInstrumento(codigo, nombre, unidad));
                }
            }
            result = true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
             ex.printStackTrace();
        }

        return result;
    }
     //Calibraciones
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
                
                Node root = doc.getElementsByTagName(calibracion.getTipo()).item(0);

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
            
            NodeList instrumentosNodes = doc.getElementsByTagName("TipoInstrumento");
            for (int i = 0; i < instrumentosNodes.getLength(); i++) {
                Node instrumentoNode = instrumentosNodes.item(i);
                if (instrumentoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element instrumentoElement = (Element) instrumentoNode;
                    
                    String numero = instrumentoElement.getAttribute("Número");
                    String fecha = instrumentoElement.getElementsByTagName("Fecha").item(0).getTextContent();
                    String mediciones = instrumentoElement.getElementsByTagName("Mediciones").item(0).getTextContent();
                    String tipo = instrumentoElement.getElementsByTagName("Tipo").item(0).getTextContent();
                    calibraciones.add(new Calibraciones(numero, fecha, mediciones,tipo));
                }
            }
            result = true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
             ex.printStackTrace();
        }

        return result;
    }
    
}

