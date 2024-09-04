package com.zacharyfox.rmonitor.leaderboard;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.zacharyfox.rmonitor.entities.Competitor;
import com.zacharyfox.rmonitor.utils.Duration;

public class LeaderBoardTableCellRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 8538560288679667468L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
		int row, int column)
	{
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Duration fastestLap = Competitor.getFastestLap();
		String columnName = table.getColumnName(column);
		Boolean isLastTime = false;
		Boolean isBestTime = false;
		int numberColumnNumber = 2;
		// TODO: This breaks if you reorder columns!!! Need to figure out how to do this with rowSorter and getModel
		Competitor competitor;
		
		for(int i=0;i<table.getColumnCount();i++) {
			if(((String)table.getColumnName(i)).compareTo("#") == 0) {
				numberColumnNumber = i;
			}
		}
		try {
			competitor = Competitor.getInstance((String) table.getValueAt(row, numberColumnNumber));
		}
		catch (Exception eee) {
			return c;
		}
		
		if(competitor == null) {
			return c;
		}
		Duration competitorBestLap = competitor.getBestLap();
		
		if(columnName.compareTo("Last Time") == 0){
			isLastTime = true;
		}
		if(columnName.compareTo("Best Time") == 0){
			isBestTime = true;
		}
		if (isBestTime && value.equals(fastestLap)) {
			c.setBackground(new Color(150, 0, 150));
			c.setForeground(new Color(255, 255, 255));
		} else if (isLastTime && value.equals(fastestLap)) {
			c.setBackground(new Color(150, 0, 150));
			c.setForeground(new Color(255, 255, 255));
		} else if (isLastTime && value.equals(competitorBestLap)) {
			c.setBackground(new Color(0, 150, 0));
			c.setForeground(new Color(255, 255, 255));
		} else {
			c.setBackground(table.getBackground());
			c.setForeground(table.getForeground());
		}

		return c;
	}
}
