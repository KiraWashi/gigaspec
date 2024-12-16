package metaModel;

import metaModel.type.Type;

public class Attribute implements MinispecElement {
    private String name;
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Type getType() {return type;}
    public void setType(Type type) {this.type = type;}
    public void accept(Visitor v) {
        v.visitAttribute(this);
    };

    public String toString() {
        return name + " : " + type.toString() + ";";
    }

}