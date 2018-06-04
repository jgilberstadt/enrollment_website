package edu.wustl.mir.ctt.model;

/**
 *
 * @author drm
 */
public enum AttributeValueType {
    BOOLEAN,
    INTEGER,
    FLOAT,
    DATE,
    TIME,
    TIMESTAMP,
    STRING;
    
    public String getType() {
        switch(this) {
            case BOOLEAN:
                return "boolean";
            case INTEGER:
                return "integer";
            case FLOAT:
                return "float";
            case DATE:
                return "date";
            case TIME:
                return "time";
            case TIMESTAMP:
                return "timestamp";
            case STRING:
                return "string";
            default:
                return "unkown";
        }
    }
}
