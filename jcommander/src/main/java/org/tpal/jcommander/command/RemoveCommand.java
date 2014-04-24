package org.tpal.jcommander.command;

import org.tpal.jcommander.model.DiskItem;

public class RemoveCommand
    implements Command
{

    private DiskItem item;

    public RemoveCommand( DiskItem item )
    {
        super();
        this.item = item;
    }

    @Override
    public void execute()
    {
        item.remove();
    }

    @Override
    public int getProgress()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
