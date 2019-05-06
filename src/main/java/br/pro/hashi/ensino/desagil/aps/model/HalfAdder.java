package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;
    private final NandGate nand5;

    public HalfAdder() {
        super("HalfAdder", 2, 2);

        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
        nand4 = new NandGate();
        nand5 = new NandGate();

        nand1.connect(0, nand4);

        nand2.connect(1, nand4);

        nand3.connect(0, nand2);
        nand3.connect(1, nand1);

        nand5.connect(0, nand4);
        nand5.connect(1, nand4);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nand3.read();
        } else if (outputPin == 1) {
            return nand5.read();
        } else {
            throw new IndexOutOfBoundsException(outputPin);
        }

    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nand2.connect(0, emitter);
                nand4.connect(0, emitter);
                break;
            case 1:
                nand4.connect(1, emitter);
                nand1.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }

}
