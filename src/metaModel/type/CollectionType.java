package metaModel.type;

import metaModel.Visitor;

public class CollectionType extends Type {
    private Type sousType;
    private int debut;
    private int fin;
    private int taille;

    public CollectionType(String type, Type sousType) {
        super(type);
        this.sousType = sousType;
    }
    public Type getSousType() {
        return sousType;
    }
    public void setSousType(Type sousType) {
        this.sousType = sousType;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void accept(Visitor v){
        v.visitCollectionType(this);
    };
}
