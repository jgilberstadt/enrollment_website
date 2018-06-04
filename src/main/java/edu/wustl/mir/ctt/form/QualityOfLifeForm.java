package edu.wustl.mir.ctt.form;

import edu.wustl.mir.ctt.model.AttributeDate;
import edu.wustl.mir.ctt.model.AttributeString;
import edu.wustl.mir.ctt.model.ECPFormTypes;
import java.util.Date;

/**
 *
 * @author Paul K. Commean
 */
public class QualityOfLifeForm extends BasicForm {
    
    public QualityOfLifeForm() {
        // constructor
        super();
        this.formType = ECPFormTypes.QUALITY_OF_LIFE;
        this.requiresSourceDoc = false;
        title = "Quality of Life Form";

        attributes.put("qualityOfLifeDate", new AttributeDate("qualityOfLifeDate"));
        
        // Modified Medical Research Council Dyspnea Scale
        attributes.put("qolMmrcdsQues1", new AttributeString("qolMmrcdsQues1"));
        
        // ST. GEORGE’S RESPIRATORY QUESTIONNAIRE
        attributes.put("qolSgrqcCurrentHealth", new AttributeString("qolSgrqcCurrentHealth"));
        attributes.put("qolSgrqcQues1", new AttributeString("qolSgrqcQues1"));
        attributes.put("qolSgrqcQues2", new AttributeString("qolSgrqcQues2"));
        attributes.put("qolSgrqcQues3", new AttributeString("qolSgrqcQues3"));
        attributes.put("qolSgrqcQues4", new AttributeString("qolSgrqcQues4"));
        attributes.put("qolSgrqcQues5", new AttributeString("qolSgrqcQues5"));
        attributes.put("qolSgrqcQues6", new AttributeString("qolSgrqcQues6"));
        attributes.put("qolSgrqcQues7", new AttributeString("qolSgrqcQues7"));
        attributes.put("qolSgrqcQues8", new AttributeString("qolSgrqcQues8"));
        attributes.put("qolSgrqcQues9a", new AttributeString("qolSgrqcQues9a"));
        attributes.put("qolSgrqcQues9b", new AttributeString("qolSgrqcQues9b"));
        attributes.put("qolSgrqcQues9c", new AttributeString("qolSgrqcQues9c"));
        attributes.put("qolSgrqcQues9d", new AttributeString("qolSgrqcQues9d"));
        attributes.put("qolSgrqcQues9e", new AttributeString("qolSgrqcQues9e"));
        attributes.put("qolSgrqcQues10a", new AttributeString("qolSgrqcQues10a"));
        attributes.put("qolSgrqcQues10b", new AttributeString("qolSgrqcQues10b"));
        attributes.put("qolSgrqcQues10c", new AttributeString("qolSgrqcQues10c"));
        attributes.put("qolSgrqcQues10d", new AttributeString("qolSgrqcQues10d"));
        attributes.put("qolSgrqcQues10e", new AttributeString("qolSgrqcQues10e"));
        attributes.put("qolSgrqcQues10f", new AttributeString("qolSgrqcQues10f"));
        attributes.put("qolSgrqcQues11a", new AttributeString("qolSgrqcQues11a"));
        attributes.put("qolSgrqcQues11b", new AttributeString("qolSgrqcQues11b"));
        attributes.put("qolSgrqcQues11c", new AttributeString("qolSgrqcQues11c"));
        attributes.put("qolSgrqcQues11d", new AttributeString("qolSgrqcQues11d"));
        attributes.put("qolSgrqcQues11e", new AttributeString("qolSgrqcQues11e"));
        attributes.put("qolSgrqcQues11f", new AttributeString("qolSgrqcQues11f"));
        attributes.put("qolSgrqcQues11g", new AttributeString("qolSgrqcQues11g"));
        attributes.put("qolSgrqcQues12a", new AttributeString("qolSgrqcQues12a"));
        attributes.put("qolSgrqcQues12b", new AttributeString("qolSgrqcQues12b"));
        attributes.put("qolSgrqcQues12c", new AttributeString("qolSgrqcQues12c"));
        attributes.put("qolSgrqcQues12d", new AttributeString("qolSgrqcQues12d"));
        attributes.put("qolSgrqcQues12e", new AttributeString("qolSgrqcQues12e"));
        attributes.put("qolSgrqcQues12f", new AttributeString("qolSgrqcQues12f"));
        attributes.put("qolSgrqcQues12g", new AttributeString("qolSgrqcQues12g"));
        attributes.put("qolSgrqcQues12h", new AttributeString("qolSgrqcQues12h"));
        attributes.put("qolSgrqcQues13a", new AttributeString("qolSgrqcQues13a"));
        attributes.put("qolSgrqcQues13b", new AttributeString("qolSgrqcQues13b"));
        attributes.put("qolSgrqcQues13c", new AttributeString("qolSgrqcQues13c"));
        attributes.put("qolSgrqcQues13d", new AttributeString("qolSgrqcQues13d"));
        attributes.put("qolSgrqcQues13e", new AttributeString("qolSgrqcQues13e"));
        attributes.put("qolSgrqcQues14", new AttributeString("qolSgrqcQues14"));
        
        // DYSPNEA-12 (D-12) Questionnaire
        attributes.put("qolD12Ques1", new AttributeString("qolD12Ques1"));
        attributes.put("qolD12Ques2", new AttributeString("qolD12Ques2"));
        attributes.put("qolD12Ques3", new AttributeString("qolD12Ques3"));
        attributes.put("qolD12Ques4", new AttributeString("qolD12Ques4"));
        attributes.put("qolD12Ques5", new AttributeString("qolD12Ques5"));
        attributes.put("qolD12Ques6", new AttributeString("qolD12Ques6"));
        attributes.put("qolD12Ques7", new AttributeString("qolD12Ques7"));
        attributes.put("qolD12Ques8", new AttributeString("qolD12Ques8"));
        attributes.put("qolD12Ques9", new AttributeString("qolD12Ques9"));
        attributes.put("qolD12Ques10", new AttributeString("qolD12Ques10"));
        attributes.put("qolD12Ques11", new AttributeString("qolD12Ques11"));
        attributes.put("qolD12Ques12", new AttributeString("qolD12Ques12"));
        
        // EQ-5D-5L
        attributes.put("qolEq5d5lQues1", new AttributeString("qolEq5d5lQues1"));
        attributes.put("qolEq5d5lQues2", new AttributeString("qolEq5d5lQues2"));
        attributes.put("qolEq5d5lQues3", new AttributeString("qolEq5d5lQues3"));
        attributes.put("qolEq5d5lQues4", new AttributeString("qolEq5d5lQues4"));
        attributes.put("qolEq5d5lQues5", new AttributeString("qolEq5d5lQues5"));
        
        this.clear();
    }
    

    
    public QualityOfLifeForm( BasicForm bf) {
        super(bf);		
        this.formType = ECPFormTypes.QUALITY_OF_LIFE;
        title = bf.getTitle();
        this.requiresSourceDoc = false;
    }
	
    public Date getQualityOfLifeDate() {
        return (Date) attributes.get("qualityOfLifeDate").getValue();
    }
    
    public void setQualityOfLifeDate(Date qualityOfLifeDate) {
        attributes.get("qualityOfLifeDate").setValue(qualityOfLifeDate);
    }
    
    // Quality of Life form should always be locked for editing
    @Override
    public boolean isLocked() {
        return true;
    }

    ////////////////////////////////////////////////////////////////
    // Modified Medical Research Council Dyspnea Scale
    ////////////////////////////////////////////////////////////////
    public String getQolMmrcdsQues1() {
        return (String) attributes.get("qolMmrcdsQues1").getValue();
    }
    
    public void setQolMmrcdsQues1(String qolMmrcdsQues1) {
        attributes.get("qolMmrcdsQues1").setValue(qolMmrcdsQues1);
    }

    
    
    ////////////////////////////////////////////////////////////////
    // ST. GEORGE’S RESPIRATORY QUESTIONNAIRE
    ////////////////////////////////////////////////////////////////
    public String getQolSgrqcCurrentHealth() {
        return (String) attributes.get("qolSgrqcCurrentHealth").getValue();
    }
    
    public void setQolSgrqcCurrentHealth(String qolSgrqcCurrentHealth) {
        attributes.get("qolSgrqcCurrentHealth").setValue(qolSgrqcCurrentHealth);
    }

    public String getQolSgrqcQues1() {
        return (String) attributes.get("qolSgrqcQues1").getValue();
    }
    
    public void setQolSgrqcQues1(String qolSgrqcQues1) {
        attributes.get("qolSgrqcQues1").setValue(qolSgrqcQues1);
    }

    public String getQolSgrqcQues2() {
        return (String) attributes.get("qolSgrqcQues2").getValue();
    }
    
    public void setQolSgrqcQues2(String qolSgrqcQues2) {
        attributes.get("qolSgrqcQues2").setValue(qolSgrqcQues2);
    }

    public String getQolSgrqcQues3() {
        return (String) attributes.get("qolSgrqcQues3").getValue();
    }
    
    public void setQolSgrqcQues3(String qolSgrqcQues3) {
        attributes.get("qolSgrqcQues3").setValue(qolSgrqcQues3);
    }

    public String getQolSgrqcQues4() {
        return (String) attributes.get("qolSgrqcQues4").getValue();
    }
    
    public void setQolSgrqcQues4(String qolSgrqcQues4) {
        attributes.get("qolSgrqcQues4").setValue(qolSgrqcQues4);
    }

    public String getQolSgrqcQues5() {
        return (String) attributes.get("qolSgrqcQues5").getValue();
    }
    
    public void setQolSgrqcQues5(String qolSgrqcQues5) {
        attributes.get("qolSgrqcQues5").setValue(qolSgrqcQues5);
    }

    public String getQolSgrqcQues6() {
        return (String) attributes.get("qolSgrqcQues6").getValue();
    }
    
    public void setQolSgrqcQues6(String qolSgrqcQues6) {
        attributes.get("qolSgrqcQues6").setValue(qolSgrqcQues6);
    }

    public String getQolSgrqcQues7() {
        return (String) attributes.get("qolSgrqcQues7").getValue();
    }
    
    public void setQolSgrqcQues7(String qolSgrqcQues7) {
        attributes.get("qolSgrqcQues7").setValue(qolSgrqcQues7);
    }

    public String getQolSgrqcQues8() {
        return (String) attributes.get("qolSgrqcQues8").getValue();
    }
    
    public void setQolSgrqcQues8(String qolSgrqcQues8) {
        attributes.get("qolSgrqcQues8").setValue(qolSgrqcQues8);
    }

    public String getQolSgrqcQues9a() {
        return (String) attributes.get("qolSgrqcQues9a").getValue();
    }
    
    public void setQolSgrqcQues9a(String qolSgrqcQues9a) {
        attributes.get("qolSgrqcQues9a").setValue(qolSgrqcQues9a);
    }

    public String getQolSgrqcQues9b() {
        return (String) attributes.get("qolSgrqcQues9b").getValue();
    }
    
    public void setQolSgrqcQues9b(String qolSgrqcQues9b) {
        attributes.get("qolSgrqcQues9b").setValue(qolSgrqcQues9b);
    }

    public String getQolSgrqcQues9c() {
        return (String) attributes.get("qolSgrqcQues9c").getValue();
    }
    
    public void setQolSgrqcQues9c(String qolSgrqcQues9c) {
        attributes.get("qolSgrqcQues9c").setValue(qolSgrqcQues9c);
    }

    public String getQolSgrqcQues9d() {
        return (String) attributes.get("qolSgrqcQues9d").getValue();
    }
    
    public void setQolSgrqcQues9d(String qolSgrqcQues9d) {
        attributes.get("qolSgrqcQues9d").setValue(qolSgrqcQues9d);
    }

    public String getQolSgrqcQues9e() {
        return (String) attributes.get("qolSgrqcQues9e").getValue();
    }
    
    public void setQolSgrqcQues9e(String qolSgrqcQues9e) {
        attributes.get("qolSgrqcQues9e").setValue(qolSgrqcQues9e);
    }

    public String getQolSgrqcQues10a() {
        return (String) attributes.get("qolSgrqcQues10a").getValue();
    }
    
    public void setQolSgrqcQues10a(String qolSgrqcQues10a) {
        attributes.get("qolSgrqcQues10a").setValue(qolSgrqcQues10a);
    }

    public String getQolSgrqcQues10b() {
        return (String) attributes.get("qolSgrqcQues10b").getValue();
    }
    
    public void setQolSgrqcQues10b(String qolSgrqcQues10b) {
        attributes.get("qolSgrqcQues10b").setValue(qolSgrqcQues10b);
    }

    public String getQolSgrqcQues10c() {
        return (String) attributes.get("qolSgrqcQues10c").getValue();
    }
    
    public void setQolSgrqcQues10c(String qolSgrqcQues10c) {
        attributes.get("qolSgrqcQues10c").setValue(qolSgrqcQues10c);
    }

    public String getQolSgrqcQues10d() {
        return (String) attributes.get("qolSgrqcQues10d").getValue();
    }
    
    public void setQolSgrqcQues10d(String qolSgrqcQues10d) {
        attributes.get("qolSgrqcQues10d").setValue(qolSgrqcQues10d);
    }

    public String getQolSgrqcQues10e() {
        return (String) attributes.get("qolSgrqcQues10e").getValue();
    }
    
    public void setQolSgrqcQues10e(String qolSgrqcQues10e) {
        attributes.get("qolSgrqcQues10e").setValue(qolSgrqcQues10e);
    }

    public String getQolSgrqcQues10f() {
        return (String) attributes.get("qolSgrqcQues10f").getValue();
    }
    
    public void setQolSgrqcQues10f(String qolSgrqcQues10f) {
        attributes.get("qolSgrqcQues10f").setValue(qolSgrqcQues10f);
    }

    public String getQolSgrqcQues11a() {
        return (String) attributes.get("qolSgrqcQues11a").getValue();
    }
    
    public void setQolSgrqcQues11a(String qolSgrqcQues11a) {
        attributes.get("qolSgrqcQues11a").setValue(qolSgrqcQues11a);
    }

    public String getQolSgrqcQues11b() {
        return (String) attributes.get("qolSgrqcQues11b").getValue();
    }
    
    public void setQolSgrqcQues11b(String qolSgrqcQues11b) {
        attributes.get("qolSgrqcQues11b").setValue(qolSgrqcQues11b);
    }

    public String getQolSgrqcQues11c() {
        return (String) attributes.get("qolSgrqcQues11c").getValue();
    }
    
    public void setQolSgrqcQues11c(String qolSgrqcQues11c) {
        attributes.get("qolSgrqcQues11c").setValue(qolSgrqcQues11c);
    }

    public String getQolSgrqcQues11d() {
        return (String) attributes.get("qolSgrqcQues11d").getValue();
    }
    
    public void setQolSgrqcQues11d(String qolSgrqcQues11d) {
        attributes.get("qolSgrqcQues11d").setValue(qolSgrqcQues11d);
    }

    public String getQolSgrqcQues11e() {
        return (String) attributes.get("qolSgrqcQues11e").getValue();
    }
    
    public void setQolSgrqcQues11e(String qolSgrqcQues11e) {
        attributes.get("qolSgrqcQues11e").setValue(qolSgrqcQues11e);
    }

    public String getQolSgrqcQues11f() {
        return (String) attributes.get("qolSgrqcQues11f").getValue();
    }
    
    public void setQolSgrqcQues11f(String qolSgrqcQues11f) {
        attributes.get("qolSgrqcQues11f").setValue(qolSgrqcQues11f);
    }

    public String getQolSgrqcQues11g() {
        return (String) attributes.get("qolSgrqcQues11g").getValue();
    }
    
    public void setQolSgrqcQues11g(String qolSgrqcQues11g) {
        attributes.get("qolSgrqcQues11g").setValue(qolSgrqcQues11g);
    }

    public String getQolSgrqcQues12a() {
        return (String) attributes.get("qolSgrqcQues12a").getValue();
    }
    
    public void setQolSgrqcQues12a(String qolSgrqcQues12a) {
        attributes.get("qolSgrqcQues12a").setValue(qolSgrqcQues12a);
    }

    public String getQolSgrqcQues12b() {
        return (String) attributes.get("qolSgrqcQues12b").getValue();
    }
    
    public void setQolSgrqcQues12b(String qolSgrqcQues12b) {
        attributes.get("qolSgrqcQues12b").setValue(qolSgrqcQues12b);
    }

    public String getQolSgrqcQues12c() {
        return (String) attributes.get("qolSgrqcQues12c").getValue();
    }
    
    public void setQolSgrqcQues12c(String qolSgrqcQues12c) {
        attributes.get("qolSgrqcQues12c").setValue(qolSgrqcQues12c);
    }

    public String getQolSgrqcQues12d() {
        return (String) attributes.get("qolSgrqcQues12d").getValue();
    }
    
    public void setQolSgrqcQues12d(String qolSgrqcQues12d) {
        attributes.get("qolSgrqcQues12d").setValue(qolSgrqcQues12d);
    }

    public String getQolSgrqcQues12e() {
        return (String) attributes.get("qolSgrqcQues12e").getValue();
    }
    
    public void setQolSgrqcQues12e(String qolSgrqcQues12e) {
        attributes.get("qolSgrqcQues12e").setValue(qolSgrqcQues12e);
    }

    public String getQolSgrqcQues12f() {
        return (String) attributes.get("qolSgrqcQues12f").getValue();
    }
    
    public void setQolSgrqcQues12f(String qolSgrqcQues12f) {
        attributes.get("qolSgrqcQues12f").setValue(qolSgrqcQues12f);
    }

    public String getQolSgrqcQues12g() {
        return (String) attributes.get("qolSgrqcQues12g").getValue();
    }
    
    public void setQolSgrqcQues12g(String qolSgrqcQues12g) {
        attributes.get("qolSgrqcQues12g").setValue(qolSgrqcQues12g);
    }

    public String getQolSgrqcQues12h() {
        return (String) attributes.get("qolSgrqcQues12h").getValue();
    }
    
    public void setQolSgrqcQues12h(String qolSgrqcQues12h) {
        attributes.get("qolSgrqcQues12h").setValue(qolSgrqcQues12h);
    }

    public String getQolSgrqcQues13a() {
        return (String) attributes.get("qolSgrqcQues13a").getValue();
    }
    
    public void setQolSgrqcQues13a(String qolSgrqcQues13a) {
        attributes.get("qolSgrqcQues13a").setValue(qolSgrqcQues13a);
    }

    public String getQolSgrqcQues13b() {
        return (String) attributes.get("qolSgrqcQues13b").getValue();
    }
    
    public void setQolSgrqcQues13b(String qolSgrqcQues13b) {
        attributes.get("qolSgrqcQues13b").setValue(qolSgrqcQues13b);
    }

    public String getQolSgrqcQues13c() {
        return (String) attributes.get("qolSgrqcQues13c").getValue();
    }
    
    public void setQolSgrqcQues13c(String qolSgrqcQues13c) {
        attributes.get("qolSgrqcQues13c").setValue(qolSgrqcQues13c);
    }

    public String getQolSgrqcQues13d() {
        return (String) attributes.get("qolSgrqcQues13d").getValue();
    }
    
    public void setQolSgrqcQues13d(String qolSgrqcQues13d) {
        attributes.get("qolSgrqcQues13d").setValue(qolSgrqcQues13d);
    }

    public String getQolSgrqcQues13e() {
        return (String) attributes.get("qolSgrqcQues13e").getValue();
    }
    
    public void setQolSgrqcQues13e(String qolSgrqcQues13e) {
        attributes.get("qolSgrqcQues13e").setValue(qolSgrqcQues13e);
    }

    public String getQolSgrqcQues14() {
        return (String) attributes.get("qolSgrqcQues14").getValue();
    }
    
    public void setQolSgrqcQues14(String qolSgrqcQues14) {
        attributes.get("qolSgrqcQues14").setValue(qolSgrqcQues14);
    }


    ////////////////////////////////////////////////////////////////
    // DYSPNEA-12 (D-12) Questionnaire   
    ////////////////////////////////////////////////////////////////
    public String getQolD12Ques1() {
        return (String) attributes.get("qolD12Ques1").getValue();
    }
    
    public void setQolD12Ques1(String qolD12Ques1) {
        attributes.get("qolD12Ques1").setValue(qolD12Ques1);
    }

    public String getQolD12Ques2() {
        return (String) attributes.get("qolD12Ques2").getValue();
    }
    
    public void setQolD12Ques2(String qolD12Ques2) {
        attributes.get("qolD12Ques2").setValue(qolD12Ques2);
    }

    public String getQolD12Ques3() {
        return (String) attributes.get("qolD12Ques3").getValue();
    }
    
    public void setQolD12Ques3(String qolD12Ques3) {
        attributes.get("qolD12Ques3").setValue(qolD12Ques3);
    }

    public String getQolD12Ques4() {
        return (String) attributes.get("qolD12Ques4").getValue();
    }
    
    public void setQolD12Ques4(String qolD12Ques4) {
        attributes.get("qolD12Ques4").setValue(qolD12Ques4);
    }

    public String getQolD12Ques5() {
        return (String) attributes.get("qolD12Ques5").getValue();
    }
    
    public void setQolD12Ques5(String qolD12Ques5) {
        attributes.get("qolD12Ques5").setValue(qolD12Ques5);
    }

    public String getQolD12Ques6() {
        return (String) attributes.get("qolD12Ques6").getValue();
    }
    
    public void setQolD12Ques6(String qolD12Ques6) {
        attributes.get("qolD12Ques6").setValue(qolD12Ques6);
    }

    public String getQolD12Ques7() {
        return (String) attributes.get("qolD12Ques7").getValue();
    }
    
    public void setQolD12Ques7(String qolD12Ques7) {
        attributes.get("qolD12Ques7").setValue(qolD12Ques7);
    }

    public String getQolD12Ques8() {
        return (String) attributes.get("qolD12Ques8").getValue();
    }
    
    public void setQolD12Ques8(String qolD12Ques8) {
        attributes.get("qolD12Ques8").setValue(qolD12Ques8);
    }

    public String getQolD12Ques9() {
        return (String) attributes.get("qolD12Ques9").getValue();
    }
    
    public void setQolD12Ques9(String qolD12Ques9) {
        attributes.get("qolD12Ques9").setValue(qolD12Ques9);
    }

    public String getQolD12Ques10() {
        return (String) attributes.get("qolD12Ques10").getValue();
    }
    
    public void setQolD12Ques10(String qolD12Ques10) {
        attributes.get("qolD12Ques10").setValue(qolD12Ques10);
    }

    public String getQolD12Ques11() {
        return (String) attributes.get("qolD12Ques11").getValue();
    }
    
    public void setQolD12Ques11(String qolD12Ques11) {
        attributes.get("qolD12Ques11").setValue(qolD12Ques11);
    }

    public String getQolD12Ques12() {
        return (String) attributes.get("qolD12Ques12").getValue();
    }
    
    public void setQolD12Ques12(String qolD12Ques12) {
        attributes.get("qolD12Ques12").setValue(qolD12Ques12);
    }

    ////////////////////////////////////////////////////////////////
    // EQ-5D-5L
    ////////////////////////////////////////////////////////////////
    public String getQolEq5d5lQues1() {
        return (String) attributes.get("qolEq5d5lQues1").getValue();
    }
    
    public void setQolEq5d5lQues1(String qolEq5d5lQues1) {
        attributes.get("qolEq5d5lQues1").setValue(qolEq5d5lQues1);
    }

    public String getQolEq5d5lQues2() {
        return (String) attributes.get("qolEq5d5lQues2").getValue();
    }
    
    public void setQolEq5d5lQues2(String qolEq5d5lQues2) {
        attributes.get("qolEq5d5lQues2").setValue(qolEq5d5lQues2);
    }

    public String getQolEq5d5lQues3() {
        return (String) attributes.get("qolEq5d5lQues3").getValue();
    }
    
    public void setQolEq5d5lQues3(String qolEq5d5lQues3) {
        attributes.get("qolEq5d5lQues3").setValue(qolEq5d5lQues3);
    }

    public String getQolEq5d5lQues4() {
        return (String) attributes.get("qolEq5d5lQues4").getValue();
    }
    
    public void setQolEq5d5lQues4(String qolEq5d5lQues4) {
        attributes.get("qolEq5d5lQues4").setValue(qolEq5d5lQues4);
    }

    public String getQolEq5d5lQues5() {
        return (String) attributes.get("qolEq5d5lQues5").getValue();
    }
    
    public void setQolEq5d5lQues5(String qolEq5d5lQues5) {
        attributes.get("qolEq5d5lQues5").setValue(qolEq5d5lQues5);
    }




}
