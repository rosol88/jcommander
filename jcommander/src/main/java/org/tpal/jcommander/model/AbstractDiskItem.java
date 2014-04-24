package org.tpal.jcommander.model;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.tpal.jcommander.service.DiskService;

public abstract class AbstractDiskItem
    implements DiskItem
{

    private String name;

    private String extension;

    private Long size;

    private Date date;

    private String path;

    private boolean directory;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getExtension()
    {
        return extension;
    }

    public void setExtension( String extension )
    {
        this.extension = extension;
    }

    public Long getSize()
    {
        return size;
    }

    public void setSize( Long size )
    {
        this.size = size;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public AbstractDiskItem( File file )
    {
        this.date = new Date( file.lastModified() );
        this.name = FilenameUtils.getBaseName( file.getName() );
        if ( StringUtils.isBlank( this.name ) )
        {
            this.name = file.getName();
            this.extension = "";
        }
        else
        {
            this.extension = FilenameUtils.getExtension( file.getName() );
        }
        this.size = file.length();
        this.setPath( file.getAbsolutePath() );
        setDirectory( file.isDirectory() );
    }

    @Override
    public String toString()
    {
        return "AbstractDiskItem [name=" + name + ", extension=" + extension + ", size=" + size + ", date=" + date + "]";
    }

    public String getPath()
    {
        return path;
    }

    public void setPath( String path )
    {
        this.path = path;
    }

    public boolean isDirectory()
    {
        return directory;
    }

    public void setDirectory( boolean directory )
    {
        this.directory = directory;
    }

    @Override
    public void moveTo( String destinationPath )
    {
        DiskService.getInstance().move( this, destinationPath );

    }

    @Override
    public void copyTo( String destinationPath )
    {
        DiskService.getInstance().copy( this, destinationPath );

    }

    @Override
    public void remove()
    {
        DiskService.getInstance().remove( this );

    }

}
