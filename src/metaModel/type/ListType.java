package metaModel.type;

import metaModel.Visitor;

public class ListType extends Type{
    public Type sous_type;
    public int premier_indice;
    public int dernier_indice;
    public int taille;
    public Type[] values;

    public ListType(Type type, int premier_indice, int dernier_indice) {
        this.sous_type = type;
        this.premier_indice = premier_indice;
        this.dernier_indice = dernier_indice;
        this.taille = premier_indice;
        this.values = new Type[taille];
    }
    @Override
    public void accept(Visitor v) {
        v.visitType(this);
    }
    @Override
    public String toString() {
        return "List of "+ sous_type.toString();
    }
}
