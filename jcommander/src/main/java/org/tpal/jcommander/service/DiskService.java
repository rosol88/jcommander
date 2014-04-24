package org.tpal.jcommander.service;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.model.DirItem;
import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.model.Drive;
import org.tpal.jcommander.model.FileItem;

public class DiskService
{
    private static final Logger log = Logger.getLogger( DiskService.class );

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

    public List<DiskItem> getDiskItems( String path, boolean showHidden )
        throws FileNotSupportedException
    {
        File file = new File( path );
        List<DiskItem> items = new ArrayList<DiskItem>();
        checkFile( file );
        for ( File f : file.listFiles() )
        {
            if ( !f.isHidden() && checkFile( f ) )
            {
                DiskItem item = createItem( f );
                items.add( item );
            }
        }
        Collections.sort( items, new Comparator<DiskItem>()
        {

            @Override
            public int compare( DiskItem o1, DiskItem o2 )
            {
                if ( o1.isDirectory() && o2.isDirectory() )
                {
                    if ( o1.getName().compareTo( o2.getName() ) < 0 )
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
                else if ( o1.isDirectory() )
                {
                    return -1;

                }
                else if ( o2.isDirectory() )
                {
                    return 1;
                }
                else if ( !o1.isDirectory() && !o2.isDirectory() )
                {
                    if ( o1.getName().compareTo( o2.getName() ) < 0 )
                    {
                        return -1;
                    }
                    else
                    {
                        return 1;
                    }
                }
                return 0;
            }

        } );
        return items;
    }

    public List<DiskItem> getDiskItems( String path )
        throws FileNotSupportedException
    {
        return getDiskItems( path, false );
    }

    private boolean checkFile( File file )
        throws FileNotSupportedException
    {
        try
        {
            BasicFileAttributes attr = Files.readAttributes( file.toPath(), BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS );
            if ( attr.isOther() )
            {
                return false;
                // throw new FileNotSupportedException( file );
            }
        }
        catch ( IOException e )
        {
            throw new RuntimeException( e );
        }
        return true;
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

    public void move( DiskItem sourceItem, String destinationPath )
    {
        File source = new File( sourceItem.getPath() );
        source.renameTo( new File( destinationPath + "/" + source.getName() ) );
    }

    public void copy( DiskItem sourceItem, String destinationPath )
    {
        try
        {
            File source = new File( sourceItem.getPath() );
            FileInputStream fis = new FileInputStream( source );
            final File dest = new File( destinationPath + "/" + sourceItem.getName() );
            FileOutputStream fos = new FileOutputStream( dest );
            IOUtils.copy( fis, fos );
            fis.close();
            fos.close();
        }
        catch ( Exception e )
        {
            log.error( e, e );
        }

    }

    public void remove( DiskItem item )
    {
        File source = new File( item.getPath() );
        source.delete();
    }
}
