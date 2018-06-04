package edu.wustl.mir.ctt.model;

import java.util.Date;

/**
 *
 * @author drm
 */
public class AttributeTime extends Attribute<Date> {
    
    public AttributeTime() {
        super();
    }
    
    public AttributeTime( String name) {
        super( name);
    }
    
    public AttributeTime( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.TIME;
    }
}
