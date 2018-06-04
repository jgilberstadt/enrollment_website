package edu.wustl.mir.ctt.model;

import java.util.Date;

/**
 *
 * @author drm
 */
public class AttributeDate extends Attribute<Date> {
    
    public AttributeDate() {
        super();
    }
    
    public AttributeDate( String name) {
        super(name);
    }
    
    public AttributeDate( String name, boolean verify, boolean optional, boolean persistent) {
        super(name, verify, optional, persistent);
    }
    
    @Override
    public AttributeValueType getValueType() {
        return AttributeValueType.DATE;
    }
}
