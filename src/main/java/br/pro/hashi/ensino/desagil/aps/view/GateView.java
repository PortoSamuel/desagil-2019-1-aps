package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

//Referencias:
//NOT: https://pt.wikipedia.org/wiki/Ficheiro:NOT_ANSI.svg
//AND: https://pt.wikipedia.org/wiki/Ficheiro:AND_ANSI.svg
//OR: https://pt.wikipedia.org/wiki/Ficheiro:OR_ANSI.svg
//NAND: https://pt.wikipedia.org/wiki/Ficheiro:NAND_ANSI.svg
//XOR: https://pt.wikipedia.org/wiki/Ficheiro:XOR_ANSI.svg

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;

    private final Image image;
    private final Light light;
    private Color color;

    public GateView(Gate gate) {
        super(245, 346);

        this.gate = gate;

        int inputSize = gate.getInputSize();

        light = new Light();

        light.setB(0);
        light.setG(0);
        light.setR(255);

        light.connect(0, gate);

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        int i = 0;
        for (JCheckBox inputBox : inputBoxes) {
            if (inputSize == 2) {

                add(inputBox, 12, 140 + i);

                i += 40;
            } else {
                add(inputBox, 12, 160);
            }
        }
//        add(outputBox, 210, 160, 20, 25);

        color = Color.BLACK;

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        int r = light.getR();
        int g = light.getG();
        int b = light.getB();

        color = new Color(r, g, b);
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        if (Math.pow(x - 222.5, 2) + Math.pow(y - 172.5, 2) < 156.25) {

            color = JColorChooser.showDialog(this, null, color);

            if (color != null) {
                light.setB(color.getBlue());
                light.setG(color.getGreen());
                light.setR(color.getRed());
            }

            repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(image, 22, 123, 200, 100, this);

        g.setColor(color);
        g.fillOval(210, 160, 25, 25);

        getToolkit().sync();
    }
}
