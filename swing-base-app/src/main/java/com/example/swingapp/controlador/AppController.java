package com.example.swingapp.controlador;

import javax.swing.*;

import com.example.swingapp.vista.ControlPanel;
import com.example.swingapp.vista.OutputPanel;

import java.awt.event.ActionEvent;

public class AppController {
    private final ControlPanel controlPanel;
    private final OutputPanel outputPanel;

    public AppController(ControlPanel c, OutputPanel o) {
        this.controlPanel = c;
        this.outputPanel  = o;

        // Acciones de botones
        controlPanel.btnIP.addActionListener(this::onIP);
        controlPanel.btnRed.addActionListener(this::onRed);
        controlPanel.btnPing.addActionListener(this::onPing);
        controlPanel.btnCurl.addActionListener(this::onCurl);
        controlPanel.btnClear.addActionListener(this::onClear);
        controlPanel.btnExit.addActionListener(e -> System.exit(0));
    }

    private void onRed(ActionEvent e) {
        outputPanel.append("Ejecutando comando...");
        try {
            ProcessBuilder pb = new ProcessBuilder("ip", "-s", "link");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            new Thread(() -> {
                try (var reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        outputPanel.append(line);
                    }
                } catch (Exception ex) {
                    outputPanel.append("Error: " + ex.getMessage());
                }
            }).start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ejecutando proceso:\n" + ex.getMessage());
        }
    }

    private void onIP(ActionEvent e) {
        outputPanel.append("Ejecutando comando...");
        try {
            ProcessBuilder pb = new ProcessBuilder("ip", "addr", "show");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            new Thread(() -> {
                try (var reader = new java.io.BufferedReader(
                        new java.io.InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        outputPanel.append(line);
                    }
                } catch (Exception ex) {
                    outputPanel.append("Error: " + ex.getMessage());
                }
            }).start();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error ejecutando proceso:\n" + ex.getMessage());
        }
    }


    private void onCurl(ActionEvent e) {
    String url = controlPanel.txtInput.getText().trim();


    outputPanel.append("Ejecutando curl para: " + url);

    try {
        ProcessBuilder pb = new ProcessBuilder("curl", url);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        new Thread(() -> {
            try (var reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    outputPanel.append(line);
                }
            } catch (Exception ex) {
                outputPanel.append("Error leyendo salida: " + ex.getMessage());
            }
        }).start();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error ejecutando curl:\n" + ex.getMessage());
    }
}

private void onPing(ActionEvent e) {
    String url = controlPanel.txtInput.getText().trim();


    outputPanel.append("Ejecutando curl para: " + url);

    try {
        ProcessBuilder pb = new ProcessBuilder("ping", url);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        new Thread(() -> {
            try (var reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    outputPanel.append(line);
                }
            } catch (Exception ex) {
                outputPanel.append("Error leyendo salida: " + ex.getMessage());
            }
        }).start();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Error ejecutando curl:\n" + ex.getMessage());
    }
}


    

    private void onClear(ActionEvent e) {
        outputPanel.clear();
    }
}