package org.tpal.jcommander.service;

import org.tpal.jcommander.locale.Context;

public class ContextHolder
{
    private static Context context;

    public static void setContext( Context ctx )
    {
        context = ctx;
    }

    public static Context getContext()
    {
        return context;
    }
}
