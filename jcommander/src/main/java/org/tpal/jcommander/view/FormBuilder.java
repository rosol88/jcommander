package org.tpal.jcommander.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class FormBuilder
{

    List<DiskCombo> combos = new ArrayList<DiskCombo>();

    public JCommanderFrame buildFrame()
    {
        JCommanderFrame frame = new JCommanderFrame();
        return frame;
    }

    JPanel p;

    public void buildGui()
    {
        JCommanderFrame frame = buildFrame();
        MenuBar menu = buildMenuBar();
        frame.setJMenuBar( menu );
        JPanel layout = buildLayout();
        layout.setLayout( new BorderLayout() );
        frame.add( layout );
        JPanel p1 = buildFlowPanel();
        p = p1;
        layout.add( p1, BorderLayout.NORTH );
        layout.add( buildTabsPanel(), BorderLayout.CENTER );
        layout.add( buildBottomPanel(), BorderLayout.SOUTH );
        for ( DiskCombo dc : combos )
        {
            dc.selectFirst();
        }
    }

    private Component buildBottomPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout() );
        panel.add( buildPathPanel(), BorderLayout.NORTH );
        panel.add( buildBottomButtons(), BorderLayout.SOUTH );
        return panel;
    }

    private Component buildPathPanel()
    {
        final JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        cons.weightx = 1.65;

        GridBagConstraints cons2 = new GridBagConstraints();
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.weightx = .35;
        cons2.gridx = 0;
        cons2.gridy = 0;

        panel.add( new JLabel( "C;/asdsa/cascssadasdsadsadsad", JLabel.RIGHT ), cons2 );
        panel.add( new DiskCombo( null ), cons );
        return panel;
    }

    private Component buildBottomButtons()
    {
        final JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 0.5;
        cons.gridx = GridBagConstraints.RELATIVE;

        panel.add( new JButton( "F3 Podgląd" ), cons );
        panel.add( new JButton( "F3 Podgląd" ), cons );
        panel.add( new JButton( "F3 Podgląd" ), cons );
        panel.add( new JButton( "F3 Podgląd" ), cons );
        panel.add( new JButton( "F3 Podgląd" ), cons );
        panel.add( new JButton( "F3 Podgląd" ), cons );
        panel.add( new JButton( "F3 Podgląd" ), cons );
        return panel;
    }

    private JPanel buildLayout()
    {
        JPanel panel = new JPanel();
        return panel;
    }

    private JPanel buildTabsPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout( new BoxLayout( panel, BoxLayout.X_AXIS ) );
        panel.add( buildTabPanel() );
        panel.add( buildTabPanel() );
        return panel;
    }

    private JPanel buildTabPanel()
    {
        JPanel panel = new JPanel();

        panel.setLayout( new BorderLayout() );
        DiskItemTable dit = new DiskItemTable();

        panel.add( buildDiskPanel( dit ), BorderLayout.NORTH );

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab( "c:", new JScrollPane( dit ) );
        panel.add( tabbedPane, BorderLayout.CENTER );
        return panel;
    }

    private Component buildDiskPanel( DiskItemTable dit )
    {
        final JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout() );
        DiskCombo dc = new DiskCombo( dit );
        combos.add( dc );
        panel.add( dc, BorderLayout.WEST );
        panel.add( new JLabel( "SADAS" ), BorderLayout.CENTER );
        panel.add( new JLabel( "SADAS" ), BorderLayout.EAST );
        return panel;
    }

    private JPanel buildFlowPanel()
    {
        final JPanel panel = new JPanel();

        FlowLayout fl = new WrapLayout();
        fl.setAlignment( FlowLayout.LEFT );
        panel.setLayout( fl );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new Separator( ImageButton.DEFAULT_HEIGHT ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        panel.add( new ImageButton( "icon.png" ) );
        return panel;
    }

    private MenuBar buildMenuBar()
    {
        MenuBar menuBar = new MenuBar();
        return menuBar;
    }

}
