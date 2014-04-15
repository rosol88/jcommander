package org.tpal.jcommander.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.tpal.jcommander.service.DiskService;

public class DiskItemTableModel
    extends AbstractTableModel
{
    List<DiskItem> items = new ArrayList<DiskItem>();

    String[] columns = { "Nazwa", "Roz.", "Wielkość", "Czas" };

    public DiskItemTableModel()
    {
    }

    @Override
    public String getColumnName( int column )
    {
        return columns[column];
    }

    @Override
    public int getRowCount()
    {
        return this.items.size();
    }

    @Override
    public int getColumnCount()
    {
        return columns.length;
    }

    @Override
    public Object getValueAt( int rowIndex, int columnIndex )
    {
        DiskItem di = items.get( rowIndex );

        return getPropertyAt( di, columnIndex );
    }

    private Object getPropertyAt( DiskItem di, int columnIndex )
    {
        if ( columnIndex == 0 )
        {
            return di.getName();
        }
        else if ( columnIndex == 1 )
        {
            return di.getExtension();
        }
        else if ( columnIndex == 2 )
        {
            return di.getSize();
        }
        else if ( columnIndex == 3 )
        {
            return di.getDate();
        }
        throw new RuntimeException( "Invalid column" );
    }

    public void setPath( String path )
    {
        DiskService ds = DiskService.getInstance();
        this.items = ds.getDiskItems( path );
        if ( !ds.isBasePath( path ) )
        {
            items.add( 0, new BackItem( new File( path ) ) );
        }
        fireTableDataChanged();
    }

    public DiskItem getItemAt( int row )
    {
        return items.get( row );
    }

}
