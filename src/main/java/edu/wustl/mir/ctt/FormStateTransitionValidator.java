package edu.wustl.mir.ctt;

import edu.wustl.mir.ctt.form.BasicForm;
import edu.wustl.mir.ctt.model.FormStatus;

/**
 * Encode the state transitions of form status.
 * 
 * Checks if all verifiable attributes on form have the appropriate status
 * to allow the form be submitted and transition to the next state.
 * 
 * Setting the form causes it to be validated.
 *
 * @author drm
 */
public class FormStateTransitionValidator {
    // the form tested for it being in a valid state to make a transition.
    private BasicForm form;
    // the current status of the form
    private FormStatus oldStatus;
    // the status to-be transistioned to.
    private FormStatus newStatus;
    // a brief message summarizing outcome of validity check.
    private String msg;
    // outcome of validity check.
    private boolean valid;
    
    public BasicForm getForm() {
        return form;
    }

    public FormStatus getOldStatus() {
        return oldStatus;
    }

    public FormStatus getNewStatus() {
        return newStatus;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public String getMessage() {
        return msg;
    }

    /**
     * update form status on submit.
     * 
     * Encode the state transitions for a form upon submit.
     * 
     * @param form 
     * @return String "success" if valid transition, 
     *                string containing brief description of invalid desciption
     *                appropriate for a dialog box message to the user.
     */
    public void setForm( BasicForm form) {
        this.form = form;
        oldStatus = form.getStatus();
//        System.out.println("The oldStatus value is: " + oldStatus + "\n");
        msg = null;
        
        // NOTE: If the form state is NEW or STARTED, the NEW or STARTED case will fall into CRF_QUERY (QUESTIONED).
        // CRF_QUERY is designed to handle both the NEW and STARTED case states.
        switch (oldStatus) {
            case NEW:
            case STARTED:
            case CRF_QUERY:
                if( ! form.isQuestioned()) {
                    newStatus = FormStatus.SUBMITTED;
                    valid = true;
                    msg = "success";
                }
                else {
                    msg = "All to-be-verified entries must be marked 'Verified' or 'Unverified' before the form may be submitted.";
                    valid = false;
                }
                break;
            case SUBMITTED:
                if( form.isVerified()) {
                    newStatus = FormStatus.DCC_VERIFIED;
                    valid = true;
                    msg = "success";
                }
                else if( form.isCurated()) {
                    newStatus = FormStatus.CRF_QUERY;
                    valid = true;
                    msg = "success";
                }
                // The isRequiresVerification is for the ObservePulmEvalLogForm.java and ChangeTherapyForm.java classes that do not currently require them to be DCC_VERIED.
                // The requiresVerification variable in ObservePulmEvalLogForm.java and ChangeTherapyForm.java classes are set to false, but for most other forms it is set to true.
                else if( !form.isRequiresVerification()) {
                    newStatus = FormStatus.DCC_VERIFIED;
                    valid = true;
                    msg = "success";
                }
                else {
                    valid = false;
                    msg = "All to-be-verified entries must be marked 'Verified' or 'Questioned' before the form may be submitted.";
                }
                break;
            case DCC_VERIFIED:
                if( form.isVerified()) {
                    newStatus = FormStatus.PI_APPROVED;
                    valid = true;
                    msg = "success";
                }
                else {
                    valid = false;
                    msg = "All to-be-verified entries must be marked 'Verified' or 'Unverified' before the form may be submitted.";
                }
                break;
        }
    }

}
