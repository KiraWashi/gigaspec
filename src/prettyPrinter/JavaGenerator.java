package prettyPrinter;

import metaModel.*;
import metaModel.Class;
import metaModel.type.PrimitiveType;
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
		String ret = switch (type.getType()) {
            case "String" -> "String";
            case "Integer" -> "Integer";
			case "List" -> "List";
			case "Bag" -> "Bag";
			case "Array" -> "Array";
			default -> "";
        };
		result = result + ret;
    }

	public void visitReferenceType(Type type) {
		result = result + type.getType();
	}

	@Override
	public void visitAttribute(Attribute attribute) {
		result= result + attribute.getName() + " : ";
		attribute.getType().accept(this);
		result = result + "; \n";
	}
}
