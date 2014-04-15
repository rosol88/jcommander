package org.tpal.jcommander.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import org.tpal.jcommander.model.Drive;
import org.tpal.jcommander.service.DiskService;

public class DiskCombo
    extends JComboBox
{
    private DiskItemTable table;

    public DiskCombo( DiskItemTable dit )
    {
        super();
        table = dit;
        setPreferredSize( new Dimension( 50, 20 ) );
        loadItems();
        addActionListener( new ActionListener()
        {
            public void actionPerformed( ActionEvent actionEvent )
            {
                select();
            }

        } );

    }

    private void select()
    {
        Drive drive = (Drive) getSelectedItem();
        table.setPath( drive.getPath() );
    }

    @SuppressWarnings( "unchecked" )
    private void loadItems()
    {
        this.removeAllItems();
        List<Drive> drives = DiskService.getInstance().getAllDrives();
        for ( Drive drive : drives )
        {
            this.addItem( drive );
        }
    }

    public void selectFirst()
    {
        select();
    }
}
