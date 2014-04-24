package org.tpal.jcommander.locale;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.xml.crypto.Data;

public class ResourceBundleDemo {

	private static int lang = 0;

	public static void main(String[] args) {
		Locale locale;
		locale = Locale.getDefault();
		// locale = Locale.ENGLISH;
		// locale = new Locale("EN");

		// locale = Locale.;
		locale = new Locale("EN");

		ResourceBundle rb = ResourceBundle.getBundle("MyResources", locale);
		final Context c = new Context("MyResources");
		c.setLocale(locale);

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("I18N Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 2));

		// final JLabel label1 = new JLabel(
		// c.getBundle().getString("userName"));
		final ContextLabel label1 = new ContextLabel("userName", c);
		final ContextLabel label2 = new ContextLabel("password", c);
		final ContextLabel label3 = new ContextLabel("login", c);

		c.addContextChangeListener(label1);
		c.addContextChangeListener(label2);
		c.addContextChangeListener(label3);
		
		
		
		

		final JButton button = new JButton("Jezyk");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Locale l;
				if (lang == 0) {
					lang = 1;
					l = new Locale("EN");
				} else {
					lang = 0;
					l = new Locale("PL");
				}

				c.setLocale(l);
				
				DateFormat d =  DateFormat.getDateInstance(DateFormat.SHORT, c.getLocale());			
				//System.out.println(d.format(new Date()));
				button.setText(d.format(new Date()));
/*
				new Thread() {
					public void run() {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								button.setEnabled(true);
								Locale l;
								if (lang == 0) {
									lang = 1;
									l = new Locale("EN");
								} else {
									lang = 0;
									l = new Locale("PL");
								}

								c.setLocale(l);

							}
						});
					}
				}.start();*/
			}
		});

		frame.add(label1);
		frame.add(new JTextField());
		frame.add(label2);
		frame.add(new JPasswordField());
		frame.add(label3);
		frame.add(button);
		frame.pack();
		frame.setVisible(true);
	}
}
