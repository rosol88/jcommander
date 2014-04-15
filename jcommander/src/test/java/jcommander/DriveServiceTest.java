package jcommander;

import java.util.List;

import org.junit.Test;
import org.tpal.jcommander.model.DiskItem;
import org.tpal.jcommander.model.Drive;
import org.tpal.jcommander.service.DiskService;

public class DriveServiceTest
{

    private DiskService ds = DiskService.getInstance();

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
    {

        List<DiskItem> drives = ds.getDiskItems( "c:/" );
        for ( DiskItem drive : drives )
        {
            System.out.println( drive );
        }
    }

}
