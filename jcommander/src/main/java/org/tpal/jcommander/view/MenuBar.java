package org.tpal.jcommander.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.tpal.jcommander.locale.Context;
import org.tpal.jcommander.locale.ContextChangeListener;
import org.tpal.jcommander.service.ContextHolder;

public class MenuBar
    extends JMenuBar
    implements ContextChangeListener
{

    private static final long serialVersionUID = 1L;

    private Context context;

    JMenu files;

    JMenu select;

    JMenu commands;

    JMenu help;

    JMenu lang;

    JMenuItem pl;

    JMenuItem en;

    public MenuBar()
    {
        super();
        this.context = ContextHolder.getContext();
        files = new JMenu( context.getBundle().getString( "Pliki" ) );
        select = new JMenu( context.getBundle().getString( "Zaznacz" ) );
        commands = new JMenu( context.getBundle().getString( "Polecenia" ) );
        lang = new JMenu( context.getBundle().getString( "Jezyk" ) );
        pl = new JMenuItem( context.getBundle().getString( "Polski" ) );
        en = new JMenuItem( context.getBundle().getString( "Angielski" ) );
        context.addContextChangeListener( this );
        lang.add( pl );
        lang.add( en );
        pl.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                context.setLocale( new Locale( "PL" ) );
            }
        } );
        en.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                context.setLocale( new Locale( "EN" ) );
            }
        } );
        help = new JMenu( context.getBundle().getString( "Pomoc" ) );
        help.setBackground( Color.PINK );
        WrapLayout l = new WrapLayout();
        l.setAlignment( FlowLayout.LEFT );
        this.setLayout( l );
        this.add( files );
        this.add( select );
        this.add( commands );
        this.add( Box.createHorizontalGlue() );
        this.add( lang );
        this.add( help );

    }

    @Override
    public void contextChanged()
    {
        files.setText( context.getBundle().getString( "Pliki" ) );
        select.setText( context.getBundle().getString( "Zaznacz" ) );
        commands.setText( context.getBundle().getString( "Polecenia" ) );
        help.setText( context.getBundle().getString( "Pomoc" ) );
        lang.setText( context.getBundle().getString( "Jezyk" ) );
        pl.setText( context.getBundle().getString( "Polski" ) );
        en.setText( context.getBundle().getString( "Angielski" ) );
    }

}
