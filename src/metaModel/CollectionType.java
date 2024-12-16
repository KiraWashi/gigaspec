package metaModel;

public class CollectionType extends Type {
    private Type elementType;
    private String collectionKind; // "List", "Set", "Array"
    private int minSize = 0;
    private int maxSize = -1; // -1 pour illimité

    public CollectionType(String kind, Type elementType) {
        super(kind);
        this.collectionKind = kind;
        this.elementType = elementType;
    }

    public void setCardinality(int min, int max) {
        this.minSize = min;
        this.maxSize = max;
    }

    public Type getElementType() {
        return elementType;
    }

    public int getMinSize() {
        return minSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
        public String getJavaType() {
        switch(collectionKind) {
            case "List":
                return "List<" + elementType.getJavaType() + ">";
            case "Set":
                return "Set<" + elementType.getJavaType() + ">";
            case "Array":
                return elementType.getJavaType() + "[]";
            default:
                return getName() + "<" + elementType.getJavaType() + ">";
        }
    }

}
