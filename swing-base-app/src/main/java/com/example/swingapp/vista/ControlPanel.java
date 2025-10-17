package com.example.swingapp.vista;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	public JButton btnClear, btnExit, btnCurl, btnPing, btnRed, btnIP;
    public JTextField txtInput;

    public ControlPanel() {
        // grid con 3 filas (una por botón)
        setLayout(new GridLayout(3, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        txtInput = new JTextField(); 
        txtInput.setToolTipText("URL");

        btnIP    = new JButton("ip");
        btnRed   = new JButton("red");
        btnPing  = new JButton("ping");
        btnCurl  = new JButton("curl");
        btnClear = new JButton("🧹 Limpiar");
        btnExit  = new JButton("🚪 Salir");


        add(txtInput);
        add(btnPing);
        add(btnCurl);
        add(btnIP);
        add(btnRed);
        add(btnClear);
        add(btnExit);
    }
}