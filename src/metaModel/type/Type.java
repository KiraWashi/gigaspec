package metaModel.type;

import metaModel.MinispecElement;

public abstract class Type implements MinispecElement {
    private final String nom;
    public String packageName=null;
    public Type(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}