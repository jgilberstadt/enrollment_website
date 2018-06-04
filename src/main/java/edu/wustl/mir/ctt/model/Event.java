package edu.wustl.mir.ctt.model;

import edu.wustl.mir.ctt.form.BasicForm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Model the events that occur to a participant during the clinical trial.
 * 
 * Participant id, name, label uniquely identify an event.
 * 
 * Not all events are 'expected' (scheduled) events, but we keep it all in one
 * class to ease the mapping to the DB. Only expected events can have 
 * non-null projectedDate values
 * 
 * @author drm
 */
public class Event implements Serializable, Comparable {
    // All of the following variables are class members
    private int id;
    private int participantId;      // Stored in database and retrieved from database
    protected ECPEventTypes type;   // Stored in database and retrieved from database
    protected String name;          // the name of this event that, together with label, distinguishes this event from other events of the same type for a participant.
    protected EventStatus status;   // Stored in database and retrieved from database
    private boolean expected;       // Stored in database and retrieved from database
    private Date actualDate;        // Stored in database and retrieved from database
    private List<BasicForm> forms;  // Forms are not stored in the database, but lazy loaded from other objects.
    private String label;           // Second part of unique event identifier.

    // The following variables are also class members, and in addition
    // the following variables are special class members for EXPECTED EVENTs only, see comment above author drm.
    private Date baseDate;              // Stored in database and retrieved from database
    private Date projectedDate;         // Computed in the events object
    private Date firstAvailableDate;    // Computed in the events object
    private Date lastAvailableDate;     // Computed in the events object
    private int offsetFromBaseDateInDays;           // Stored in database and retrieved from database
    private int offsetToFirstAvailableDayInDays;    // Stored in database and retrieved from database
    private int offsetToLastAvailableDayInDays;     // Stored in database and retrieved from database
    private boolean showBaseDate;                   // Stored in database and retrieved from database
    private boolean showFirstAvailableDate;         // Stored in database and retrieved from database
    private boolean showLastAvailableDate;          // Stored in database and retrieved from database

    private static final long MILLISEC_PER_DAY = 24 * 60 * 60 * 1000;
    
    public Event( Date d, int baseOffset, int firstOffset, int lastOffset) {
        super();     
        baseDate = d;
        offsetFromBaseDateInDays = baseOffset;
        offsetToFirstAvailableDayInDays = firstOffset;
        offsetToLastAvailableDayInDays = lastOffset;
        showBaseDate = true;
        showFirstAvailableDate = true;
        showLastAvailableDate = true;

        if( d != null) {
            projectedDate = new Date( baseDate.getTime() + offsetFromBaseDateInDays * MILLISEC_PER_DAY);
            firstAvailableDate = new Date( projectedDate.getTime() - offsetToFirstAvailableDayInDays * MILLISEC_PER_DAY);
            lastAvailableDate = new Date( projectedDate.getTime() + offsetToLastAvailableDayInDays * MILLISEC_PER_DAY);
        }
    }
    
    public Event( Date d, int baseOffset, int offset) {
        this( d, baseOffset, offset, offset);
    }
    
    public Event( Date d, int baseOffset) {
        this( d, baseOffset, 0, 0);
    }
    
    public Event( Date d) {
        this( d, 0, 0, 0);
    }
    
    public Event() {
        this( null, 0, 0, 0);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public boolean isExpected() {
        return expected;
    }

    public void setExpected(boolean expected) {
        this.expected = expected;
    }

    /*
     * can be null. Indicates unknown date.
    */
    public Date getBaseDate() {
        return baseDate;
    }
    public void setBaseDate( Date d) {
        baseDate = d;
        if( d != null) {
            projectedDate = new Date( baseDate.getTime() + offsetFromBaseDateInDays * MILLISEC_PER_DAY);
            firstAvailableDate = new Date( projectedDate.getTime() - offsetToFirstAvailableDayInDays * MILLISEC_PER_DAY);
            lastAvailableDate = new Date( projectedDate.getTime() + offsetToLastAvailableDayInDays * MILLISEC_PER_DAY);
        }
    }

    /*
     * can be null. Indicates unknown date.
    */
    public Date getProjectedDate() {
        return projectedDate;
    }

    // We never set the projected date.  Just the baseDate and offset in
    // the child class ScheduledEvent.
//    public void setProjectedDate(Date projectedDate) {
//        this.projectedDate = projectedDate;
//    }

    public int getOffsetFromBaseDateInDays() {
        return offsetFromBaseDateInDays;
    }

    public void setOffsetFromBaseDateInDays(int offsetFromBaseDateInDays) {
        this.offsetFromBaseDateInDays = offsetFromBaseDateInDays;
        if( baseDate != null) {
            projectedDate = new Date( baseDate.getTime() + offsetFromBaseDateInDays * MILLISEC_PER_DAY);
            firstAvailableDate = new Date( projectedDate.getTime() - offsetToFirstAvailableDayInDays * MILLISEC_PER_DAY);
            lastAvailableDate = new Date( projectedDate.getTime() + offsetToLastAvailableDayInDays * MILLISEC_PER_DAY);
        }
    }

    /*
     * can be null. Indicates unknown date.
    */
    public Date getFirstAvailableDate() {
        return firstAvailableDate;
    }

    /*
     * can be null. Indicates unknown date.
    */
    public Date getLastAvailableDate() {
        return lastAvailableDate;
    }

    public int getOffsetToFirstAvailableDayInDays() {
        return offsetToFirstAvailableDayInDays;
    }

    public void setOffsetToFirstAvailableDayInDays(int offsetToFirstAvailableDayInDays) {
        this.offsetToFirstAvailableDayInDays = offsetToFirstAvailableDayInDays;
        if( projectedDate != null) {
            firstAvailableDate = new Date( projectedDate.getTime() - offsetToFirstAvailableDayInDays * MILLISEC_PER_DAY);
        }
    }

    public int getOffsetToLastAvailableDayInDays() {
        return offsetToLastAvailableDayInDays;
    }

    public void setOffsetToLastAvailableDayInDays(int offsetToLastAvailableDayInDays) {
        this.offsetToLastAvailableDayInDays = offsetToLastAvailableDayInDays;
        if( projectedDate != null) {
            lastAvailableDate = new Date( projectedDate.getTime() + offsetToLastAvailableDayInDays * MILLISEC_PER_DAY);
        }
    }
    
    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    public List<BasicForm> getForms() {
        if( forms == null) forms = new ArrayList<BasicForm>();
        return forms;
    }

    public void setForms(List<BasicForm> forms) {
        this.forms = forms;
    }

    public ECPEventTypes getType() {
        return type;
    }

    public void setType(ECPEventTypes type) {
        this.type = type;
    }

    // Allow getting or turning on/off the showing of the date in the 
    // Participant Summary page under the Projected Date and/or Overdue Date column.
    
    public boolean getShowBaseDate() {
        return showBaseDate;
    }

    public void setShowBaseDate(boolean showBaseDate) {
        this.showBaseDate = showBaseDate;
    }

    public boolean getShowFirstAvaiableDate() {
        return showBaseDate;
    }

    public void setShowFirstAvaiableDate(boolean showFirstAvailableDate) {
        this.showFirstAvailableDate = showFirstAvailableDate;
    }

    public boolean getShowLastAvailableDate() {
//        System.out.println("I am in the getShowLastAvailableDate method with a value of: " + showLastAvailableDate);
        return showLastAvailableDate;
    }

    public void setShowLastAvailableDate(boolean showLastAvailableDate) {
//        System.out.println("I am in the setShowLastAvailableDate method with a value of: " + this.showLastAvailableDate);
        this.showLastAvailableDate = showLastAvailableDate;
    }

    @Override
    public int compareTo(Object o) {
        int comp = 0;
        Event other = (Event) o;
        
        Date date1 = getActualDate() == null ? getProjectedDate() : getActualDate();
        Date date2 = other.getActualDate() == null ? other.getProjectedDate() : other.getActualDate();
        
        if (date1 == null && date2 == null) {
            comp = 0;
        } else if (date1 == null) {
            comp = 1;
        } else if (date2 == null) {
            comp = -1;
        } else {
            comp = date1.compareTo(date2);
        }
        
        return comp;
    }

}
