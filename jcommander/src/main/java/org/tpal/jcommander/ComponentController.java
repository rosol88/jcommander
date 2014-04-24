package org.tpal.jcommander;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import org.apache.log4j.Logger;
import org.tpal.jcommander.command.CopyCommand;
import org.tpal.jcommander.command.Invoker;
import org.tpal.jcommander.command.MoveCommand;
import org.tpal.jcommander.command.RemoveCommand;
import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.view.DiskTabPanel;

public class ComponentController
    implements ActiveListener
{
    private static final Logger log = Logger.getLogger( ComponentController.class );

    private DiskTabPanel activeTabPanel;

    private DiskTabPanel inactiveTabPanel;

    public DiskTabPanel getActiveTabPanel()
    {
        return activeTabPanel;
    }

    public void setActiveTabPanel( DiskTabPanel activeTabPanel )
    {
        this.activeTabPanel = activeTabPanel;
        activeTabPanel.addActiveListener( this );
    }

    public DiskTabPanel getInactiveTabPanel()
    {
        return inactiveTabPanel;
    }

    public void setInactiveTabPanel( DiskTabPanel inactiveTabPanel )
    {
        this.inactiveTabPanel = inactiveTabPanel;
        this.inactiveTabPanel.addActiveListener( this );
    }

    public void copyButton( ActionEvent e )
        throws FileNotSupportedException
    {
        List<DiskItem> sels = activeTabPanel.getSelectedItems();
        String path = inactiveTabPanel.getActivePath();
        Invoker invoker = Invoker.getInstance();
        for ( DiskItem diskItem : sels )
        {
            CopyCommand move = new CopyCommand( diskItem, path );
            SwingWorker sw = invoker.makeCommand( move );

            final JFrame frame = new JFrame( "MyPanel" );
            frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            JPanel p = new JPanel();
            final JProgressBar pb = new JProgressBar( 0, 100 );
            sw.addPropertyChangeListener( new PropertyChangeListener()
            {

                @Override
                public void propertyChange( PropertyChangeEvent evt )
                {
                    if ( evt.getPropertyName().equals( "progress" ) )
                    {
                        log.debug( "progress: " + evt.getNewValue() );
                        pb.setValue( (int) evt.getNewValue() );
                    }
                    else if ( evt.getPropertyName().equals( "state" ) )
                    {
                        StateValue sv = (StateValue) evt.getNewValue();

                        if ( sv == StateValue.DONE )
                        {
                            pb.setValue( 100 );
                            log.debug( "END" );
                            frame.setVisible( false );
                            frame.dispose();
                            try
                            {
                                inactiveTabPanel.reload();
                            }
                            catch ( FileNotSupportedException e )
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            } );
            p.add( pb );
            frame.getContentPane().add( p );
            frame.pack();
            frame.setVisible( true );
        }
        activeTabPanel.reload();
        inactiveTabPanel.reload();
    }

    public void moveButton( ActionEvent e )
        throws FileNotSupportedException
    {
        List<DiskItem> sels = activeTabPanel.getSelectedItems();
        String path = inactiveTabPanel.getActivePath();
        Invoker invoker = Invoker.getInstance();
        for ( DiskItem diskItem : sels )
        {
            MoveCommand move = new MoveCommand( diskItem, path );
            invoker.makeCommand( move );
        }
        activeTabPanel.reload();
        inactiveTabPanel.reload();
    }

    public void removeButton( ActionEvent e )
        throws FileNotSupportedException
    {
        List<DiskItem> sels = activeTabPanel.getSelectedItems();
        Invoker invoker = Invoker.getInstance();
        for ( DiskItem diskItem : sels )
        {
            RemoveCommand remove = new RemoveCommand( diskItem );
            invoker.makeCommand( remove );
        }
        activeTabPanel.reload();
        inactiveTabPanel.reload();
    }

    @Override
    public void activate()
    {
        swapActiveTabs();
    }

    private void swapActiveTabs()
    {
        DiskTabPanel tmp = activeTabPanel;
        activeTabPanel.setActive( false );
        inactiveTabPanel.setActive( true );
        activeTabPanel = inactiveTabPanel;
        inactiveTabPanel = tmp;

    }
}
