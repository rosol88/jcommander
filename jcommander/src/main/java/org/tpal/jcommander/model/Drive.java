package org.tpal.jcommander.model;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class Drive
{

    private char letter;

    private String name;

    private String path;

    public char getLetter()
    {
        return letter;
    }

    public void setLetter( char letter )
    {
        this.letter = letter;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return String.valueOf( letter );
    }

    public Drive( File disk )
    {
        this.letter = disk.getAbsolutePath().toLowerCase().charAt( 0 );
        this.name = FileSystemView.getFileSystemView().getSystemDisplayName( disk );
        this.setPath( disk.getAbsolutePath() );
    }

    public String getPath()
    {
        return path;
    }

    public void setPath( String path )
    {
        this.path = path;
    }

}
