package metaModel.type;

import metaModel.Visitor;

public abstract class Type {
    private final String type;
    public Type(String type) {
        this.type = type;
    }
    public void accept(Visitor v){
        v.visitType(this);
    };

    public String getType() {
        return type;
    }
}
