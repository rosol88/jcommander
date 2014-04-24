package org.tpal.jcommander.command;

import org.tpal.jcommander.model.DiskItem;

public class MoveCommand
    implements Command
{

    private DiskItem sourceItem;

    private String destinationPath;

    public MoveCommand( DiskItem sourceItem, String destinationPath )
    {
        super();
        this.sourceItem = sourceItem;
        this.destinationPath = destinationPath;
    }

    @Override
    public void execute()
    {
        sourceItem.moveTo( destinationPath );
    }

    @Override
    public int getProgress()
    {
        return 0;
    }

}
