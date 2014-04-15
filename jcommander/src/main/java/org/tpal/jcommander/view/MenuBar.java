package org.tpal.jcommander.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.Box.Filler;

public class MenuBar
    extends JMenuBar
{

    private static final long serialVersionUID = 1L;

    public MenuBar()
    {
        super();
        JMenu files = new JMenu( "Pliki" );
        JMenu select = new JMenu( "Zaznacz" );
        JMenu commands = new JMenu( "Polecenia" );
        JMenu help = new JMenu( "Pomoc" );
        help.setBackground( Color.PINK );
        WrapLayout l = new WrapLayout();
        l.setAlignment( FlowLayout.LEFT );
        this.setLayout( l );
        this.add( files );
        this.add( select );
        this.add( commands );
        this.add( Box.createHorizontalGlue() );
        this.add( help );
    }

}
