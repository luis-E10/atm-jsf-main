package com.atm.service;

import com.atm.model.Cliente;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteService() {
        cargarClientes();
    }

    private void cargarClientes() {
        try {
            InputStream is = getClass().getResourceAsStream("/clientes.csv");
            if (is == null) {
                System.err.println("No se encontró /clientes.csv en el classpath.");
                return;
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 3) {
                        String cuenta = partes[0].trim();
                        String pin = partes[1].trim();
                        double saldo = Double.parseDouble(partes[2].trim());
                        clientes.add(new Cliente(cuenta, pin, saldo));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarPorCuenta(String cuenta) {
        return clientes.stream()
                .filter(c -> c.getNumeroCuenta().equals(cuenta))
                .findFirst()
                .orElse(null);
    }

    public Cliente buscarCliente(String cuenta, String pin) {
        return clientes.stream()
                .filter(c -> c.getNumeroCuenta().equals(cuenta) && c.getPin().equals(pin))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }
}
