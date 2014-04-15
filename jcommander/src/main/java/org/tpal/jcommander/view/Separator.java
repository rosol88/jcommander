package org.tpal.jcommander.view;

import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Separator
    extends JSeparator
{

    private final int MARGIN = 5;

    public Separator( int height )
    {
        super( SwingConstants.VERTICAL );
        this.setPreferredSize( new Dimension( 1, height - MARGIN ) );
    }

}
