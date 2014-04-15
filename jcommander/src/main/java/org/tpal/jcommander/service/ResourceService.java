package org.tpal.jcommander.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ResourceService
{
    private static ResourceService service;

    private final String ICON_DIR = "icons";

    private ResourceService()
    {

    }

    public static ResourceService getInstance()
    {
        if ( service == null )
        {
            service = new ResourceService();
        }
        return service;
    }

    public ImageIcon getIcon( String name )
    {
        try
        {
            BufferedImage img = ImageIO.read( this.getClass().getClassLoader().getResourceAsStream( ICON_DIR + "/" + name ) );
            ImageIcon icon = new ImageIcon( img );
            return icon;
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            throw new RuntimeException( e );
        }
    }
}
