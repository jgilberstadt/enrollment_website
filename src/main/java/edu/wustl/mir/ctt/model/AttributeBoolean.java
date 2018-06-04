package edu.wustl.mir.ctt.model;

/**
 *
 * @author drm
 */
public class AttributeBoolean extends Attribute<Boolean> {
    
    public AttributeBoolean() {
        super();
    }
    
    public AttributeBoolean( String name) {
        super(name);
    }
    
    public AttributeBoolean( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.BOOLEAN;
    }
}
