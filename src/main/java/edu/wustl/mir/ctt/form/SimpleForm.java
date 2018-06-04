package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;

public class SimpleForm extends BasicForm {
        
    public SimpleForm() {
        super();
        this.formType = ECPFormTypes.SIMPLE;
        
        attributes.put("firstName", new AttributeString("firstName"));
        attributes.put("lastName", new AttributeString("lastName"));
    }
	
	public SimpleForm( BasicForm bf) {
            super(bf);		
        this.formType = ECPFormTypes.SIMPLE;
	}
	
	public String getFirstName() {
		return (String) attributes.get("firstName").getValue();
	}
	public void setFirstName(String firstName) {
            attributes.get("firstName").setValue(firstName);
	}
        public boolean getFirstNameVerified() {
            return attributes.get("firstName").isVerified();
        }
//        public void setFirstNameVerified( boolean b) {
//            attributes.get("firstName").setVerified(b);
//        }
	public String getLastName() {
		return (String) attributes.get("lastName").getValue();
	}
	public void setLastName(String lastName) {
            attributes.get("lastName").setValue(lastName);
	}	

}
