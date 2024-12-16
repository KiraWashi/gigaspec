package metaModel.type;

import metaModel.Visitor;

public class StringType extends Type{
    public StringType value;
    public StringType(StringType value) {
        this.value = value;
    }
    @Override
    public void accept(Visitor v) {
        v.visitType(this);
    }

    @Override
    public String toString() {
        return "String";
    }
}
