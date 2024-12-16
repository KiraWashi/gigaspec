package metaModel.type;

import metaModel.Visitor;

public class ReferenceType extends Type {

    public ReferenceType(String type) {
        super(type);
    }
    public void accept(Visitor v){
        v.visitReferenceType(this);
    };
}
