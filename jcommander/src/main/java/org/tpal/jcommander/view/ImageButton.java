package org.tpal.jcommander.view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import org.tpal.jcommander.service.ResourceService;

public class ImageButton
    extends JButton
{

    public static final int DEFAULT_HEIGHT = 30;

    public ImageButton( String icon )
    {
        super();
        // this.setBorder( BorderFactory.createEmptyBorder() );
        // this.setContentAreaFilled( false );
        this.setIcon( ResourceService.getInstance().getIcon( icon ) );
        Dimension dim = this.getPreferredSize();
        dim.height = DEFAULT_HEIGHT;
    }
}
