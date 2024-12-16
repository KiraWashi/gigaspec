package metaModel;


import metaModel.type.CollectionType;
import metaModel.type.Type;

public class Visitor {
	
	public void visitModel(Model e) {}
	public void visitClass(Class e) {}

	public void visitType(Type type) {
	}

	public void visitAttribute(Attribute attribute) {
	}
	public void visitReferenceType(Type type) {
	}

	public void visitCollectionType(CollectionType type) {
	}
}
