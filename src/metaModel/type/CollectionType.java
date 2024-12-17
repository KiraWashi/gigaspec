package metaModel.type;

import metaModel.Visitor;

public class CollectionType extends Type {
    private Type sousType;
    private int debut;
    private int fin;
    private int taille;
    private boolean debFinRenseigne=false;
    private boolean tailleRenseigne=false;

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
        this.debFinRenseigne=true;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
        this.debFinRenseigne=true;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        this.tailleRenseigne=true;
    }

    public void accept(Visitor v){
        v.visitCollectionType(this);
    };

    public boolean isDebFinRenseigne() {
        return debFinRenseigne;
    }

    public void setDebFinRenseigne(boolean debFinRenseigne) {
        this.debFinRenseigne = debFinRenseigne;
    }

    public boolean isTailleRenseigne() {
        return tailleRenseigne;
    }

    public void setTailleRenseigne(boolean tailleRenseigne) {
        this.tailleRenseigne = tailleRenseigne;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}