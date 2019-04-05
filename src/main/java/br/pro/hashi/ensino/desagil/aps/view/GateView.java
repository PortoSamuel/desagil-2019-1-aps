package br.pro.hashi.ensino.desagil.aps.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

public class GateView extends JPanel implements ActionListener {
    private final Gate gate;

    private final JCheckBox[] entradaFields;
    //private final JTextField saidaField;
    private final JCheckBox saidaField;
    private final int[] entradaPinos;
    private final Switch[] entradaConectores;

    public GateView(Gate gate){
        this.gate = gate;

        entradaFields = new JCheckBox[this.gate.getInputSize()];
        //saidaField = new JTextField();
        saidaField = new JCheckBox();
        entradaPinos = new int[this.gate.getInputSize()];
        entradaConectores = new Switch[this.gate.getInputSize()];

        JLabel entradaLabel = new JLabel("Entradas");
        JLabel saidaLabel = new JLabel("Saída");

        add(entradaLabel);

        for (int i=0; i<this.gate.getInputSize(); i+=1){

            entradaFields[i] = new JCheckBox("Entrada " + i);
            entradaConectores[i] = new Switch();
            entradaPinos[i] = i;
            gate.connect(i, entradaConectores[i]);

            add(entradaFields[i]);
            entradaFields[i].addActionListener(this);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(saidaLabel);
        add(saidaField);

        saidaField.setEnabled(false);
        //saidaField.setActionCommand("disable");

        update();
    }

    public void update(){
        boolean verificador;

        for(int u: entradaPinos){
            verificador = entradaFields[u].isSelected();

            if (verificador) {
                entradaConectores[u].turnOn();
            } else{
                entradaConectores[u].turnOff();
            }

        saidaField.setText(Boolean.toString(gate.read()));
        System.out.println(gate.read());
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}