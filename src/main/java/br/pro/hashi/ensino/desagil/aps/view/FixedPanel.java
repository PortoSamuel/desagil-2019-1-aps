package br.pro.hashi.ensino.desagil.aps.view;

import javax.swing.*;
import java.awt.*;

public class FixedPanel extends JPanel {

    protected FixedPanel(@SuppressWarnings("SameParameterValue") int width, @SuppressWarnings("SameParameterValue") int height) {

        setLayout(null);

        setPreferredSize(new Dimension(width, height));
    }

    protected void add(Component comp, @SuppressWarnings("SameParameterValue") int x, int y) {

        super.add(comp);

        comp.setBounds(x, y, 20, 25);

    }
}
