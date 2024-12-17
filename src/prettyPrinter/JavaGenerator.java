package prettyPrinter;

import metaModel.*;
import metaModel.Class;
import metaModel.type.CollectionType;
import metaModel.type.Type;

import java.util.ArrayList;

public class JavaGenerator extends Visitor {
	public String result = "";
	public ArrayList<String> imports = new ArrayList<String>();
	public String result() {
		return result;
	}

	public void visitModel(Model e) {
		//Giga boucle pour les imports
		for (Class n : e.getClasses()) {
			for (Attribute a : n.attributes) {
				if(a.getType().packageName!=null){
					if (!this.imports.contains(a.getType().packageName)){
						this.imports.add(a.getType().packageName);
					}
				}
			}
		}
		if (e.getPackageName()!=null){
			result += "package  "+  e.getPackageName() + ";\n";
		}
		for(String s : this.imports){
			result += "import  "+  s + ";\n";
		}
		result = result + "model "+ e.name +";\n\n";

		for (Class n : e.getClasses()) {
			n.accept(this);
		}
		result = result + "end model\n";
	}

	public void visitClass(Class e) {
		if (e.classHeritage!=null){
			result = result + "\nclass " +e.getName() + " extends "+e.classHeritage.getName()+" { \n";
		}else{
			result = result + "\nclass " +e.getName() + " { \n";
		}
		for (Attribute n : e.attributes) {
			n.accept(this);
		}
		result = result + "\n}\n";
	}

	@Override
	public void visitType(Type type) {
		String ret = switch (type.getNom()) {
			case "String" -> "  String";
			case "Integer" -> "  Integer";
			default -> "";
		};
		result = result + ret;
	}

	@Override
	public void visitReferenceType(Type type) {
		result = result + "  "+type.getNom();
	}

	@Override
	public void visitCollectionType(CollectionType type) {
		String ret="";
		switch (type.getNom()) {
			case "List" -> {
				if (type.isDebFinRenseigne()) {
					ret = "  List [" + type.getDebut() + " : " + type.getFin() + "] of ";
				} else {
					ret = "  List of ";
				}
			}
			case "Bag" -> {
				if (type.isDebFinRenseigne()) {
					ret = "  Bag [" + type.getDebut() + " : " + type.getFin() + "] of ";
				} else {
					ret = "  Bag of ";
				}
			}
			case "Set" -> {
				if (type.isDebFinRenseigne()) {
					ret = "  Set [" + type.getDebut() + " : " + type.getFin() + "] of ";
				} else {
					ret = "  Set of ";
				}
			}
			case "Array" -> {
				if (type.isTailleRenseigne()) {
					ret = "  Array [" + type.getTaille() + "] of ";
				}else{
					ret = "  Array of ";
				}
			}
			default -> ret = "";
		};
		result = result + ret;
		type.getSousType().accept(this);
	}

	@Override
	public void visitAttribute(Attribute attribute) {
		attribute.getType().accept(this);
		result= result +" "+attribute.getName();
		result = result + "; \n";
	}
}