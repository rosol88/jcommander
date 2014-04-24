package org.tpal.jcommander.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.tpal.jcommander.ComponentController;
import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.locale.Context;
import org.tpal.jcommander.locale.ContextChangeListener;
import org.tpal.jcommander.service.ContextHolder;

public class FormBuilder
{

    List<DiskCombo> combos = new ArrayList<DiskCombo>();

    private ComponentController controller = new ComponentController();

    private Context context;

    public JCommanderFrame buildFrame()
    {
        JCommanderFrame frame = new JCommanderFrame();
        return frame;
    }

    JPanel p;

    public void buildGui()
    {
        Locale locale = new Locale( "PL" );

        context = new Context( "MyResources" );
        context.setLocale( locale );
        ContextHolder.setContext( context );
        JCommanderFrame frame = buildFrame();
        MenuBar menu = buildMenuBar();
        frame.setJMenuBar( menu );
        JPanel layout = buildLayout();
        layout.setLayout( new BorderLayout() );
        frame.add( layout );
        JPanel p1 = buildFlowPanel();
        p = p1;
        layout.add( p1, BorderLayout.NORTH );
        layout.add( buildTabsPanel(), BorderLayout.CENTER );
        layout.add( buildBottomPanel(), BorderLayout.SOUTH );
        for ( DiskCombo dc : combos )
        {
            dc.selectFirst();
        }
    }

    private Component buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout() );
        panel.add( buildPathPanel(), BorderLayout.NORTH );
        panel.add( buildBottomButtons(), BorderLayout.SOUTH );
        return panel;
    }

    private Component buildPathPanel()
    {
        final JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        cons.weightx = 1.65;

        GridBagConstraints cons2 = new GridBagConstraints();
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.weightx = .35;
        cons2.gridx = 0;
        cons2.gridy = 0;

        panel.add( new JLabel( "C:/text/text", JLabel.RIGHT ), cons2 );
        panel.add( new DiskCombo( null ), cons );
        return panel;
    }

    private Component buildBottomButtons()
    {
        final JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 0.5;
        cons.gridx = GridBagConstraints.RELATIVE;

        panel.add( buildBtn( "f3" ), cons );
        panel.add( buildBtn( "f4" ), cons );
        JButton copyBtn = buildBtn( "f5" );
        copyBtn.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                try
                {
                    controller.copyButton( e );
                }
                catch ( FileNotSupportedException ex )
                {
                    MessageHelper.showNotSupportedFile( ex.getFile() );
                }
            }
        } );
        panel.add( copyBtn, cons );
        JButton moveBtn = buildBtn( "f6" );
        moveBtn.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                try
                {
                    controller.moveButton( e );
                }
                catch ( FileNotSupportedException ex )
                {
                    MessageHelper.showNotSupportedFile( ex.getFile() );
                }
            }
        } );
        panel.add( moveBtn, cons );
        panel.add( buildBtn( "f7" ), cons );
        JButton removeBtn = buildBtn( "f8" );
        removeBtn.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                try
                {
                    controller.removeButton( e );
                }
                catch ( FileNotSupportedException ex )
                {
                    MessageHelper.showNotSupportedFile( ex.getFile() );
                }
            }
        } );
        panel.add( removeBtn, cons );
        return panel;
    }

    private JButton buildBtn( String key )
    {
        JButton btn = new JButton( context.getBundle().getString( key ) );
        context.addContextChangeListener( new ButtonContextListener( btn, key ) );
        return btn;
    }

    private JPanel buildLayout()
    {
        JPanel panel = new JPanel();
        return panel;
    }

    private JSplitPane buildTabsPanel()
    {

        DiskTabPanel leftTabPanel = buildTabPanel( true );
        DiskTabPanel rightTabPanel = buildTabPanel( false );
        controller.setActiveTabPanel( leftTabPanel );
        controller.setInactiveTabPanel( rightTabPanel );
        JSplitPane panel = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, leftTabPanel, rightTabPanel );
        panel.setOneTouchExpandable( true );
        panel.setDividerLocation( 0.5 );
        panel.setResizeWeight( 0.5 );
        return panel;
    }

    private DiskTabPanel buildTabPanel( boolean active )
    {

        DiskTabPanel dtp = new DiskTabPanel( active );
        return dtp;
    }

    private JPanel buildFlowPanel()
    {
        final JPanel panel = new JPanel();

        FlowLayout fl = new WrapLayout();
        fl.setAlignment( FlowLayout.LEFT );
        panel.setLayout( fl );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new Separator( ImageButton.DEFAULT_HEIGHT ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        return panel;
    }

    private MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        return menuBar;
    }

}
