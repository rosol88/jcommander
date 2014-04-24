package org.tpal.jcommander.exception;

import java.io.File;

public class FileNotSupportedException
    extends Exception
{
    private File file;

    public FileNotSupportedException( File file )
    {
        super();
        this.file = file;
    }

    public File getFile()
    {
        return file;
    }

}
