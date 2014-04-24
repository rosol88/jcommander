package org.tpal.jcommander.view;

import javax.swing.JButton;

import org.tpal.jcommander.locale.ContextChangeListener;
import org.tpal.jcommander.service.ContextHolder;

public class ButtonContextListener
    implements ContextChangeListener
{

    private JButton btn;

    private String key;

    public ButtonContextListener( JButton btn, String key )
    {
        super();
        this.btn = btn;
        this.key = key;
    }

    @Override
    public void contextChanged()
    {
        btn.setText( ContextHolder.getContext().getBundle().getString( key ) );

    }

}
