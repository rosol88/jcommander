package org.tpal.jcommander.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;
import javax.swing.JTable;

import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.model.DiskItemTableModel;
import org.tpal.jcommander.service.DiskService;

public class DiskItemTable
    extends JTable
{

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
                    mouseDoubleClick();
                }

            }

        } );
    }

    private void mouseDoubleClick()
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
    {
        JTabbedPane tab = (JTabbedPane) getParent().getParent().getParent();
        tab.setTitleAt( 0, path );
        getDataModel().setPath( path );

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
}
