package metaModel;



public class PrimitiveType extends Type {
    public PrimitiveType(String name) {
        super(name);
    }

    @Override
    public String getJavaType() {
        switch(getName()) {
            case "String": return "String";
            case "Integer": return "Integer";
            case "Boolean": return "Boolean";
            case "Double": return "Double";
            default: return getName();
        }
    }

}