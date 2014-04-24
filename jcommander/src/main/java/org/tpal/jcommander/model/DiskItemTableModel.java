package org.tpal.jcommander.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.locale.Context;
import org.tpal.jcommander.locale.ContextChangeListener;
import org.tpal.jcommander.service.ContextHolder;
import org.tpal.jcommander.service.DiskService;

public class DiskItemTableModel
    extends AbstractTableModel
    implements ContextChangeListener
{
    List<DiskItem> items = new ArrayList<DiskItem>();

    private String[] columns;

    private Context context;

    public DiskItemTableModel()
    {
        context = ContextHolder.getContext();
        context.addContextChangeListener( this );
        initCols();
    }

    private void initCols()
    {
        columns =
            new String[] { context.getBundle().getString( "Nazwa" ),
                          context.getBundle().getString( "Rozszerzenie" ),
                          context.getBundle().getString( "Wielkosc" ),
                          context.getBundle().getString( "Czas" ) };
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
            if ( di.getSize().equals( -1L ) )
            {
                return "<DIR>";
            }
            return di.getSize();
        }
        else if ( columnIndex == 3 )
        {
            return di.getDate();
        }
        throw new RuntimeException( "Invalid column" );
    }

    public void setPath( String path )
        throws FileNotSupportedException
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

    @Override
    public void contextChanged()
    {
        initCols();
        this.fireTableStructureChanged();
    }
}
