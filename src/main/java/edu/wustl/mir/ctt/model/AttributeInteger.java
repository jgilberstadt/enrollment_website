package edu.wustl.mir.ctt.model;

/**
 *
 * @author drm
 */
public class AttributeInteger extends Attribute<Integer> {
    
    public AttributeInteger() {
        super();
    }
    
    public AttributeInteger( String name) {
        super( name);
    }
    
    public AttributeInteger( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.INTEGER;
    }
}
