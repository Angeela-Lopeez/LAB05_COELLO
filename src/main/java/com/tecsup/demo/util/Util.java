package com.tecsup.demo.util;

import java.util.ResourceBundle;

public class Util {

    public static Tipo opc;
    public static final String ERROR1;
    public static final String ERROR2;

    static {
        ResourceBundle properties = ResourceBundle.getBundle("config"); // sin extensión
        opc = Tipo.valueOf(properties.getString("opc"));
        ERROR1 = properties.getString("error1");
        ERROR2 = properties.getString("error2");
    }

    // Ya no necesitas esto si no usarás Datos.ini
    // public static final String RUTA = "C:\\tecsup\\Datos.ini";
}
