package org.tpal.jcommander.locale;

import java.text.DateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class ContextLabel extends JLabel implements ContextChangeListener {
	private Context c;
	private String key;
	public ContextLabel(String name, Context c){
		super(c.getBundle().getString(name));
		this.key = name;
		
		this.c = c;
	}

	public void contextChanged(){
		this.setText(c.getBundle().getString(key));	
		
			
	}
}
