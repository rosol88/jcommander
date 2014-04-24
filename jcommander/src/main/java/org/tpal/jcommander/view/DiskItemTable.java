package org.tpal.jcommander.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.locale.ContextChangeListener;
import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.model.DiskItemTableModel;
import org.tpal.jcommander.service.ContextHolder;
import org.tpal.jcommander.service.DiskService;

public class DiskItemTable
    extends JTable
{

    private String activePath;

    public DiskItemTable()
    {

        super( new DiskItemTableModel() );
        this.addMouseListener( new MouseAdapter()
        {

            @Override
            public void mouseClicked( MouseEvent e )
            {
                if ( e.getClickCount() == 2 )
                {
                    try
                    {
                        mouseDoubleClick();
                    }
                    catch ( FileNotSupportedException ex )
                    {
                        MessageHelper.showNotSupportedFile( ex.getFile() );
                    }
                }

            }

        } );
    }

    private void mouseDoubleClick()
        throws FileNotSupportedException
    {
        DiskItem di = getSelectedItem();
        if ( di.isDirectory() )
        {
            setPath( di.getPath() );
        }
        else
        {
            DiskService.getInstance().launchFile( di.getPath() );
        }
    }

    public void setPath( String path )
        throws FileNotSupportedException
    {
        JTabbedPane tab = (JTabbedPane) getParent().getParent().getParent();
        tab.setTitleAt( 0, path );
        getDataModel().setPath( path );
        activePath = path;
    }

    private DiskItemTableModel getDataModel()
    {
        return ( (DiskItemTableModel) getModel() );
    }

    private DiskItem getSelectedItem()
    {
        int row = this.getSelectedRow();
        return this.getDataModel().getItemAt( row );
    }

    public List<DiskItem> getSelectedItems()
    {
        List<DiskItem> items = new ArrayList<DiskItem>();
        for ( int rowIdx : getSelectedRows() )
        {
            items.add( getDataModel().getItemAt( rowIdx ) );
        }
        return items;
    }

    public String getActivePath()
    {
        return activePath;
    }

    public void reload()
        throws FileNotSupportedException
    {
        getDataModel().setPath( activePath );
    }

}
