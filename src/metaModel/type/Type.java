package metaModel.type;

import metaModel.Visitor;

public abstract class Type {
    private final String nom;
    public Type(String nom) {
        this.nom = nom;
    }
    public void accept(Visitor v){
        v.visitType(this);
    };

    public String getNom() {
        return nom;
    }
}
