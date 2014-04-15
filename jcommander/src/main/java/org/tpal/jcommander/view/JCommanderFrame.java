package org.tpal.jcommander.view;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class JCommanderFrame
    extends JFrame
{

    private static final long serialVersionUID = 1L;

    private final String TITLE = "JCommander";

    private final int DEFAULT_WIDTH = 500;

    private final int DEFAULT_HEIGHT = 500;

    public JCommanderFrame()
        throws HeadlessException
    {
        super();
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setTitle( TITLE );
        this.setSize( DEFAULT_WIDTH, DEFAULT_HEIGHT );
        this.setLocationByPlatform( true );
        this.setVisible( true );
    }

}
