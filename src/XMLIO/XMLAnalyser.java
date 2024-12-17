package XMLIO;

import javax.xml.parsers.*;

import metaModel.Class;
import metaModel.exception.HeritageAlreadyGivenException;
import metaModel.exception.HeritageAttributMultipleException;
import metaModel.exception.HeritageCirculaireException;
import metaModel.exception.HeritageYourselfException;
import metaModel.type.CollectionType;
import metaModel.type.PrimitiveType;
import metaModel.type.ReferenceType;
import metaModel.type.Type;
import org.w3c.dom.*;
import org.xml.sax.*;


import metaModel.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class XMLAnalyser {

	// Les clés des 2 Map sont les id

	// Map des instances de la syntaxe abstraite (metamodel)
	protected Map<String, MinispecElement> minispecIndex;
	// Map des elements XML
	protected Map<String, Element> xmlElementIndex;

	public XMLAnalyser() {
		this.minispecIndex = new HashMap<String, MinispecElement>();
		this.xmlElementIndex = new HashMap<String, Element>();
	}

	protected Model modelFromElement(Element e) {
		return new Model();
	}

	protected Class classFromElement(Element e) {
		String name = e.getAttribute("name");
		Class classObject = new Class(name);
		if(e.hasAttribute("supertype")){
			try {
				classObject.addHeritage((Class) minispecElementFromXmlElement(this.xmlElementIndex.get(e.getAttribute("supertype"))));
			} catch (HeritageAlreadyGivenException | HeritageYourselfException | HeritageCirculaireException |
					 HeritageAttributMultipleException ignored) {}
		}
		Model model = (Model) minispecElementFromXmlElement(this.xmlElementIndex.get(e.getAttribute("model")));
		model.addEntity(classObject);
		return classObject;
	}

	protected Attribute attributeFromElement(Element e) {
		String name = e.getAttribute("name");
		Attribute attribute = new Attribute();
		attribute.setName(name);
		Type type = (Type) minispecElementFromXmlElement(this.xmlElementIndex.get(e.getAttribute("type")));
		attribute.setType(type);
		Class classObject = (Class) minispecElementFromXmlElement(this.xmlElementIndex.get(e.getAttribute("entity")));
		classObject.addAttribute(attribute);
		return attribute;
	}

	private ReferenceType referenceTypeFromElement(Element e) {
        return new ReferenceType(e.getAttribute("name"));
	}

	private CollectionType collectionTypeFromElement(Element e) {
		Type type = (Type) minispecElementFromXmlElement(this.xmlElementIndex.get(e.getAttribute("baseType")));
		CollectionType collectionType = new CollectionType(e.getAttribute("name"),type);

		if(e.hasAttribute("minSize")){
			collectionType.setDebut(Integer.parseInt(e.getAttribute("minSize")));
		}
		if(e.hasAttribute("maxSize")){
			collectionType.setFin(Integer.parseInt(e.getAttribute("maxSize")));
		}
		if(e.hasAttribute("size")){
			collectionType.setTaille(Integer.parseInt(e.getAttribute("size")));
		}

		return collectionType;

	}

	private PrimitiveType primitivTypeFromElement(Element e) {
		String type = e.getAttribute("name");
		if(type.equals("String")){
			return new PrimitiveType(type);
		}else if (type.equals("Integer")){
			return new PrimitiveType(type);
		}else{
			return null;
		}
	}

	protected Type typeFromElement(String type){
		if(type.equals("String")){
			return new PrimitiveType(type);
		}else if (type.equals("Integer")){
			return new PrimitiveType(type);
		}
		else if (type.equals("Flotte")){
			return new CollectionType(type,new ReferenceType(type));
		}
		return null;
	}


	protected MinispecElement minispecElementFromXmlElement(Element e) {
		String id = e.getAttribute("id");
		MinispecElement result = this.minispecIndex.get(id);
		if (result != null) return result;
		String tag = e.getTagName();
		if (tag.equals("Model")) {
			result = modelFromElement(e);
		} else  if (tag.equals("Entity")){
			result = classFromElement(e);
		} else if (tag.equals("Attribute")){
			result = attributeFromElement(e);
		} else if (tag.equals("ReferenceType")){
			result = referenceTypeFromElement(e);
		} else if (tag.equals("CollectionType")){
			result = collectionTypeFromElement(e);
		} else {
			result = primitivTypeFromElement(e);
		}
		this.minispecIndex.put(id, result);
		return result;
	}




	// alimentation du map des elements XML
	protected void firstRound(Element el) {
		NodeList nodes = el.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n instanceof Element) {
				Element child = (Element) n;
				String id = child.getAttribute("id");
				this.xmlElementIndex.put(id, child);
			}
		}
	}

	// alimentation du map des instances de la syntaxe abstraite (metamodel)
	protected void secondRound(Element el) {
		NodeList nodes = el.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n instanceof Element) {
				minispecElementFromXmlElement((Element)n);
			}
		}
	}

	public Model getModelFromDocument(Document document) {
		Element e = document.getDocumentElement();

		firstRound(e);

		secondRound(e);

		Model model = (Model) this.minispecIndex.get(e.getAttribute("model"));

		return model;
	}

	public Model getModelFromInputStream(InputStream stream) {
		try {
			// création d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

			// création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			Document document = constructeur.parse(stream);
			return getModelFromDocument(document);

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
		return null;
	}

	public Model getModelFromString(String contents) {
		InputStream stream = new ByteArrayInputStream(contents.getBytes());
		return getModelFromInputStream(stream);
	}

	public Model getModelFromFile(File file) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getModelFromInputStream(stream);
	}

	public Model getModelFromFilenamed(String filename) {
		File file = new File(filename);
		return getModelFromFile(file);
	}
}
