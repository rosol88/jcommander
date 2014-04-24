package org.tpal.jcommander.command;

import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

public class Invoker
{
    private static Invoker invoker;

    private Map<String, Command> running = new HashMap<String, Command>();

    private static final Logger log = Logger.getLogger( Invoker.class );

    public static Invoker getInstance()
    {
        if ( invoker == null )
        {
            invoker = new Invoker();
        }
        return invoker;
    }

    private Invoker()
    {

    }

    public SwingWorker makeCommand( final Command command )
    {
        final SwingWorker ws = new SwingWorker()
        {
            @Override
            protected Void doInBackground()
                throws Exception
            {
                int p = 0;

                while ( p < 100 )
                {
                    setProgress( p );
                    Thread.sleep( 500 );
                    p = command.getProgress();
                }
                return null;
            }
        };
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                command.execute();
                ws.cancel( true );
            }

        };
        running.put( command.toString(), command );
        Thread t = new Thread( r );
        t.start();
        ws.execute();
        return ws;
    }

}
