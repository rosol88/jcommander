package org.tpal.jcommander.service;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tpal.jcommander.model.DirItem;
import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.model.Drive;
import org.tpal.jcommander.model.FileItem;

public class DiskService
{

    private DiskService()
    {
    }

    private static DiskService diskService;

    public static DiskService getInstance()
    {
        if ( diskService == null )
            diskService = new DiskService();
        return diskService;
    }

    public List<Drive> getAllDrives()
    {
        List<Drive> drives = new ArrayList<Drive>();
        File[] roots = File.listRoots();
        for ( File f : roots )
        {
            drives.add( new Drive( f ) );
        }
        return drives;
    }

    public List<DiskItem> getDiskItems( String path )
    {
        File file = new File( path );
        List<DiskItem> items = new ArrayList<DiskItem>();
        for ( File f : file.listFiles() )
        {
            DiskItem item = createItem( f );
            items.add( item );
        }
        return items;
    }

    private DiskItem createItem( File f )
    {
        if ( f.isDirectory() )
        {
            return new DirItem( f );
        }
        else
        {
            return new FileItem( f );
        }
    }

    public boolean isBasePath( String path )
    {
        if ( path.length() == 3 )
            return true;
        return false;
    }

    public void launchFile( String path )
    {
        File file = new File( path );
        try
        {
            Desktop.getDesktop().open( file );
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }

    }
}
