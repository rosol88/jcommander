package org.tpal.jcommander.model;

import java.io.File;

public class DirItem
    extends AbstractDiskItem
{

    public DirItem( File file )
    {
        super( file );
    }

    @Override
    public Long getSize()
    {
        return -1L;
    }

}
