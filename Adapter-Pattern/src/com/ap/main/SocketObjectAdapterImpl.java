package com.ap.main;

// Object Adapter (Composition)
public class SocketObjectAdapterImpl implements SocketAdapter{

    // Composition for adapter pattern
    private Socket socket = new Socket();

    private Volt convertVolt(Volt v, int i) {
        return new Volt(v.getVolts() / i);
    }

    @Override
    public Volt get120Volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 10);
    }

    @Override
    public Volt get3Volt() {
        Volt v = socket.getVolt();
        return convertVolt(v, 40);
    }
}
