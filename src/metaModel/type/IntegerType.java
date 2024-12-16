package metaModel.type;

import metaModel.Visitor;

import java.lang.String;

public class IntegerType extends Type{
    public int value;
    public IntegerType(int value) {
        this.value = value;
    }

    @Override
    public void accept(Visitor v) {
        v.visitType(this);
    }

    @Override
    public String toString() {
        return "Integer";
    }

}
