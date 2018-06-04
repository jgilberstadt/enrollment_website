package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeBoolean;
import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeFloat;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import java.util.Date;

public class SimpleEligibilityForm extends BasicForm {
        
    public SimpleEligibilityForm() {
        super();
        this.formType = ECPFormTypes.SIMPLE_ELIGIBILITY;
        title = "Simple Eligibility Form";
        
        attributes.put("age18OrOlder", new AttributeBoolean("age18OrOlder"));
        attributes.put("medicareEligible", new AttributeBoolean("medicareEligible"));
        attributes.put("DateOfFEV1", new AttributeDate("DateOfFEV1"));
        attributes.put("FEV1", new AttributeFloat("FEV1"));
        attributes.put("FVC1", new AttributeFloat("FVC1"));
    }
	
    public SimpleEligibilityForm( BasicForm bf) {
        super(bf);		
        this.formType = ECPFormTypes.SIMPLE_ELIGIBILITY;
        title = "Simple Eligibility Form";
    }
	
	public Boolean getAge18OrOlder() {
		return (Boolean) attributes.get("age18OrOlder").getValue();
	}
	public void setAge18OrOlder(Boolean b) {
            attributes.get("age18OrOlder").setValue(b);
	}
	public Boolean getMedicareEligible() {
		return (Boolean) attributes.get("medicareEligible").getValue();
	}
	public void setMedicareEligible(Boolean b) {
            attributes.get("medicareEligible").setValue(b);
	}
        public Date getDateOfFEV1() {
            return (Date) attributes.get("DateOfFEV1").getValue();
        }
        public void setDateOfFEV1( Date d) {
            attributes.get("DateOfFEV1").setValue(d);
        }
	public Float getFEV1() {
		return (Float) attributes.get("FEV1").getValue();
	}
	public void setFEV1(Float f) {
            attributes.get("FEV1").setValue(f);
	}	
	public Float getFVC1() {
		return (Float) attributes.get("FVC1").getValue();
	}
	public void setFVC1(Float f) {
            attributes.get("FVC1").setValue(f);
	}	
	
}
