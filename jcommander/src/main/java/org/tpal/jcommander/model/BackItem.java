package org.tpal.jcommander.model;

import java.io.File;

public class BackItem
    extends DirItem
{

    public BackItem( File file )
    {
        super( file );
        setName( "[..]" );
        setPath( file.getParentFile().getAbsolutePath() );
    }

}
