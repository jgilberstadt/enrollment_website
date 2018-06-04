package edu.wustl.mir.ctt.chart;

import edu.wustl.mir.ctt.model.PulmonaryEvaluation;
import edu.wustl.mir.ctt.recalc.ReCalcController;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author drm
 */
@ManagedBean
@RequestScoped
public class ChartView implements Serializable {
 
    private LineChartModel lineModel;
     
    @ManagedProperty(value="#{reCalcController}")
    private ReCalcController reCalcController;

    @PostConstruct
    public void init() {
        createLineModel();
    }

    public ReCalcController getReCalcController() {
        return reCalcController;
    }

    public void setReCalcController(ReCalcController reCalcController) {
        this.reCalcController = reCalcController;
    }
 
    public LineChartModel getLineModel() {
        return lineModel;
    }
 
    private void createLineModel() {
        lineModel = initModel();
        lineModel.setTitle("FEV1 History");
        lineModel.setLegendPosition("ne");
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("FEV1 (ml)");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
//        axis.setMax("2014-02-01");
        axis.setTickFormat("%b %#d, %y");         
        lineModel.getAxes().put(AxisType.X, axis);    
    }
     
    private LineChartModel initModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("FEV1");
        series1.setShowLine(false);
        series1.setShowMarker(true);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for( PulmonaryEvaluation pev: reCalcController.getEvalsWithQualifyingDates()) {
            series1.set(sdf.format(pev.getDate()), pev.getFev1() * 1000);
        }
//        series1.set(1, 6);
//        series1.set(2, 3);
//        series1.set(3, 2);
//        series1.set(4, 7);
//        series1.set(5, 9);
        
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Fit");
        series2.setShowLine(true);
        series2.setShowMarker(false);
 
        List<PulmonaryEvaluation> evals = reCalcController.getActiveEvaluations();
        if( evals.size() > 1) {
            Date minDate = evals.get(0).getDate();
            Date maxDate = minDate;
            
            for( PulmonaryEvaluation pev: evals) {
                Date d = pev.getDate();
                minDate = (d.before(minDate))? d: minDate;
                maxDate = (d.after(maxDate))? d: maxDate;
            }
            
            series2.set(sdf.format(minDate), reCalcController.getCalculator().predict(minDate));
            series2.set(sdf.format(maxDate), reCalcController.getCalculator().predict(maxDate));
        }
  
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
      
}