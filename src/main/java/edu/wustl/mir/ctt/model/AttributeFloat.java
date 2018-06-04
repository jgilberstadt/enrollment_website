package edu.wustl.mir.ctt.model;

/**
 *
 * @author drm
 */
public class AttributeFloat extends Attribute<Float> {
    
    public AttributeFloat() {
        super();
    }
    
    public AttributeFloat( String name) {
        super( name);
    }
    
    public AttributeFloat( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.FLOAT;
    }
}
