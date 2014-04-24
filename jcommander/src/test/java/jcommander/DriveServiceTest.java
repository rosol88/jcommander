package jcommander;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.tpal.jcommander.exception.FileNotSupportedException;
import org.tpal.jcommander.model.DirItem;
import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.model.Drive;
import org.tpal.jcommander.service.DiskService;

public class DriveServiceTest
{

    private DiskService ds = DiskService.getInstance();

    @Test
    public void asd()
    {
        long p = 1185468416;
        long s = 1285468416;
        double x = (double) p / (double) s;
        System.out.println( x );
    }

    @Test
    public void getAllDrives()
    {
        List<Drive> drives = ds.getAllDrives();
        for ( Drive drive : drives )
        {
            System.out.println( drive );
        }
    }

    @Test
    public void getItems()
        throws FileNotSupportedException
    {

        List<DiskItem> drives = ds.getDiskItems( "c:/" );
        for ( DiskItem drive : drives )
        {
            System.out.println( drive );
        }
    }

    @Test
    public void moveDir()
        throws IOException
    {
        File tmpDir = new File( "d:/temp/move" );
        if ( !tmpDir.exists() )
        {
            tmpDir.mkdir();
        }
        else
        {
            tmpDir.delete();
            tmpDir.mkdir();
        }
        File dest = new File( "d:/temp/move2" );
        if ( !dest.exists() )
        {
            dest.mkdir();
        }
        else
        {
            dest.delete();
            dest.mkdir();
        }
        File tmpFile = new File( "d:/temp/move/tmp.txt" );
        tmpFile.createNewFile();
        DiskItem di = new DirItem( tmpDir );
        DiskService.getInstance().move( di, dest.getAbsolutePath() );

        assertFalse( "Starty plik istnieje", new File( "d:/temp/move/tmp.txt" ).exists() );
        assertTrue( "Nowy plik nie istnieje", new File( "d:/temp/move2/move/tmp.txt" ).exists() );
    }

}
