package org.tpal.jcommander.model;

import java.util.Date;

public interface DiskItem
{

    public String getName();

    public String getPath();

    public String getExtension();

    public Long getSize();

    public Date getDate();

    public boolean isDirectory();

}
