package org.tpal.jcommander;

import javax.swing.SwingUtilities;

import org.tpal.jcommander.view.FormBuilder;

public class JCommander
{

    public static void main( String[] args )
    {
        SwingUtilities.invokeLater( new Runnable()
        {
            public void run()
            {
                JCommander jcommander = new JCommander();
                jcommander.initGui();
            }
        } );
    }

    public void initGui()
    {
        FormBuilder fb = new FormBuilder();
        fb.buildGui();
    }

}
