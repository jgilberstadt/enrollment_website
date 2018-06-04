package edu.wustl.mir.ctt.model;

/**
 *
 * @author drm
 */
public class AttributeString extends Attribute<String> {
    
    public AttributeString() {
        super();
    }
    
    public AttributeString( String name) {
        super( name);
    }
    
    public AttributeString( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.STRING;
    }
}
