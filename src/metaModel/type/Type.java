package metaModel.type;

import metaModel.MinispecElement;
import metaModel.Visitor;

public abstract class Type implements MinispecElement {
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
