package edu.wustl.mir.ctt.model;

import java.util.Date;

/**
 *
 * @author drm
 */
public class AttributeTimestamp extends Attribute<Date> {
    
    public AttributeTimestamp() {
        super();
    }
    
    public AttributeTimestamp( String name) {
        super( name);
    }
    
    public AttributeTimestamp( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.TIMESTAMP;
    }
}
