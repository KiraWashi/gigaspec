package prettyPrinter;

import metaModel.*;
import metaModel.Class;
import metaModel.type.CollectionType;
import metaModel.type.Type;

public class JavaGenerator extends Visitor {
	String result = "";
	
	public String result() {
		return result;
	}
	
	public void visitModel(Model e) {
		result = "model ;\n\n";

		for (Class n : e.getClasses()) {
			n.accept(this);
		}
		result = result + "end model\n";
	}
	
	public void visitClass(Class e) {
		result = result + "package " + e.getName() ;
		result = result + "\n class " +e.getName() + " { \n";
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
		String ret = switch (type.getNom()) {
			case "List" -> "  List ["+type.getDebut() + " : "+type.getFin()+"] of ";
			case "Bag" -> "  Bag";
			case "Array" -> "  Array ["+type.getTaille()+"] of ";
			default -> "";
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
