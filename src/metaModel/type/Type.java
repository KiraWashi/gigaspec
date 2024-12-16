package metaModel.type;

import metaModel.Visitor;

public abstract class Type {
    public abstract void accept(Visitor v);
    public abstract String toString();
}
