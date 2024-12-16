package metaModel;


public class ReferenceType extends Type {
    private Entity referencedEntity;

    public ReferenceType(Entity entity) {
        super(entity.getName());
        this.referencedEntity = entity;
    }

    public Entity getReferencedEntity() {
        return referencedEntity;
    }

    @Override
    public String getJavaType() {
        return referencedEntity.getName();
    }

}