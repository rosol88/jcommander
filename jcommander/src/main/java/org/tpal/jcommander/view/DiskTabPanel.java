package org.tpal.jcommander.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.tpal.jcommander.ActiveListener;
import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.model.DiskItem;

public class DiskTabPanel
    extends JPanel
    implements FocusListener
{
    private List<ActiveListener> observers = new ArrayList<ActiveListener>();

    private boolean active;

    private DiskCombo diskCombo;

    private DiskItemTable table;

    private JTabbedPane tabPanel;

    public DiskTabPanel( boolean active )
    {
        super();

        setLayout( new BorderLayout() );
        table = new DiskItemTable();
        table.addFocusListener( this );
        add( buildDiskPanel( table ), BorderLayout.NORTH );

        tabPanel = new JTabbedPane();

        tabPanel.addTab( "c:", new JScrollPane( table ) );
        add( tabPanel, BorderLayout.CENTER );
        diskCombo.selectFirst();
        this.setActive( active );
    }

    private Component buildDiskPanel( DiskItemTable dit )
    {
        final JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout() );
        diskCombo = new DiskCombo( dit );
        panel.add( diskCombo, BorderLayout.WEST );
        panel.add( new JLabel( "" ), BorderLayout.CENTER );
        panel.add( new JLabel( "\\  ..  " ), BorderLayout.EAST );
        return panel;
    }

    public List<DiskItem> getSelectedItems()
    {
        return table.getSelectedItems();
    }

    public String getActivePath()
    {
        return table.getActivePath();
    }

    public void notifyActive()
    {
        for ( ActiveListener al : observers )
        {
            al.activate();
        }
    }

    public void addActiveListener( ActiveListener listener )
    {
        observers.add( listener );
    }

    @Override
    public void focusGained( FocusEvent e )
    {
        if ( active == false )
            for ( ActiveListener al : observers )
            {
                al.activate();
            }

    }

    @Override
    public void focusLost( FocusEvent e )
    {
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive( boolean active )
    {
        this.active = active;
    }

    public void reload()
        throws FileNotSupportedException
    {
        table.reload();

    }
}
