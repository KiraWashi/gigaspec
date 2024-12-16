package metaModel.type;

import metaModel.Visitor;

public class PrimitiveType extends Type {

    public PrimitiveType(String type) {
        super(type);
    }

    public void accept(Visitor v){
        v.visitType(this);
    };
}
