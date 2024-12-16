package prettyPrinter;

import metaModel.*;
import metaModel.Class;
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
		result = result + type.toString();
	}

	@Override
	public void visitAttribute(Attribute attribute) {
		result= result + attribute.toString() + "\n";
	}
}