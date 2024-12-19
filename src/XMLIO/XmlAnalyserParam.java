package XMLIO;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.*;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class XmlAnalyserParam {
        protected Map<String,String> xmlParametrage;

        public void XMLAnalyserParam() {
            this.xmlParametrage =  new HashMap<String,String>();
        }


        public void setParametres(String fileName) {
            File file = new File(fileName);

            InputStream stream = null;
            try {
                stream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                // création d'une fabrique de documents
                DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

                // création d'un constructeur de documents
                DocumentBuilder constructeur = fabrique.newDocumentBuilder();
                Document document = constructeur.parse(stream);
                Element e = document.getDocumentElement();

                NodeList nodes = e.getChildNodes();
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node n = nodes.item(i);
                    if (n instanceof Element) {
                        Element child = (Element) n;
                        String name = child.getAttribute("name");
                        if(child.hasAttribute("package")){
                            String packages = child.getAttribute("package");
                            this.xmlParametrage.put(name,packages);
                        }
                    }
                }

            } catch (ParserConfigurationException pce) {
                System.out.println("Erreur de configuration du parseur DOM");
                System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
            } catch (SAXException se) {
                System.out.println("Erreur lors du parsing du document");
                System.out.println("lors de l'appel à construteur.parse(xml)");
            } catch (IOException ioe) {
                System.out.println("Erreur d'entrée/sortie");
                System.out.println("lors de l'appel à construteur.parse(xml)");
            }
        }
    }
