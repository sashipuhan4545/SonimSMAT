package com.graphics.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class MyComboBoxRenderer extends JLabel implements ListCellRenderer
{
	private String _title;

	public MyComboBoxRenderer(String title)
	{
		_title = title;
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean hasFocus)
	{
		if (index == -1 && value == null) {
			setText(_title);

		}
		else {
			setText(value.toString());
		}
		return this;
	}
}