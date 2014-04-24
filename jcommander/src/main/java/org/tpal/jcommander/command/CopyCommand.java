package org.tpal.jcommander.command;

import java.io.File;

import org.apache.log4j.Logger;
import org.tpal.jcommander.model.DiskItem;

public class CopyCommand
    implements Command
{
    private static final Logger log = Logger.getLogger( CopyCommand.class );

    private DiskItem sourceItem;

    private String destinationPath;

    private Long sourceLength;

    File destinationFile;

    public CopyCommand( DiskItem sourceItem, String destinationPath )
    {
        super();
        this.sourceItem = sourceItem;
        this.destinationPath = destinationPath;
        sourceLength = sourceItem.getSize();
        destinationFile = new File( destinationPath + "/" + sourceItem.getName() );
    }

    @Override
    public void execute()
    {
        sourceItem.copyTo( destinationPath );

    }

    public int getProgress()
    {

        long len = destinationFile.length();
        log.debug( len + "   " + sourceLength );
        double p = (double) len / sourceLength;
        log.debug( "p: " + p );
        return (int) ( p * 100 );
    }

}
