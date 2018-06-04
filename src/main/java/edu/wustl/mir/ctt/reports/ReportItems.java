/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wustl.mir.ctt.reports;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * Organize the database query results into a structure used by the p:datatable that is used to render them.
 * 
 * @author drm and modified by PKC
 *
 */

@ManagedBean
@SessionScoped
public class ReportItems {
	private ArrayList<String> columnNames;
	private ArrayList<Map<String, Object>> dataRows;
	private DataModel<Map<String, Object>> model;
	
	public ReportItems() {
		this( new ArrayList<String>(), new ArrayList<Map<String, Object>>());
	}

	public ReportItems(ArrayList<String> columnNames, ArrayList<Map<String, Object>> dataRows) {
		this.columnNames = columnNames;
		this.dataRows = dataRows;
		model = new ListDataModel<Map<String, Object>>(dataRows);
	}
	
	public void clear() {
		columnNames.clear();
		dataRows.clear();
	}

	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(ArrayList<String> columnNames) {
		this.columnNames = columnNames;
	}

	public ArrayList<Map<String, Object>> getDataRows() {
		return dataRows;
	}

	public void setDataRows(ArrayList<Map<String, Object>> dataRows) {
		this.dataRows = dataRows;
	}

	public DataModel<Map<String, Object>> getDataModel() {
		return model;
	}

	public int getRecordCount() {
		return dataRows.size();
	}
}
