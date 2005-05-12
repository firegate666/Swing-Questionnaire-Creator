package de.mb.swing;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JInfoDialog extends JAbstractDialog {

    private JLabel _title_text;

    private LayoutManager _layout = new GridLayout(2, 2);
    private JLabel _version_label = new JLabel("Version:");
    private JLabel _version_text;
    private JLabel _authors_label = new JLabel("Autoren:");
    private JLabel _authors_text;

    /**
     * Public constructor
     * @param owner
     * @param title Application name
     * @param version Application version
     * @param authors Authors
     */
    public JInfoDialog(
        Frame owner,
        String title,
        String version,
        String authors) {
        super(owner);

        _width = 350;
        _height = 150;

        this.setModal(true);
        setTitle(title);

        _title_text = new JLabel(title);
        this.add(_title_text, BorderLayout.NORTH);

        _version_text = new JLabel(version);
        _authors_text = new JLabel(authors);

        JPanel panel = new JPanel();
        panel.setLayout(_layout);
        panel.add(_version_label);
        panel.add(_version_text);
        panel.add(_authors_label);
        panel.add(_authors_text);

        this.add(panel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {}

}
