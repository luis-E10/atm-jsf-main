package com.atm.bean;

import com.atm.model.Cliente;
import com.atm.service.ClienteService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("CajeroBean")
@SessionScoped
@RequestScoped
public class CajeroBean implements Serializable {
    private String cuenta;
    private String pin;
    private double monto;
    private Cliente clienteActual;
    private ClienteService clienteService = new ClienteService();

    // Login / validar cliente
    public String validarCliente() {
        Cliente encontrado = clienteService.buscarPorCuenta(cuenta);
        if (encontrado == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cuenta no encontrada.", null));
            return "index.xhtml";
        }
        if (!encontrado.getPin().equals(pin)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PIN inválido.", null));
            return "index.xhtml";
        }
        this.clienteActual = encontrado;
        // limpiar monto por si acaso
        this.monto = 0.0;
        return "menu.xhtml?faces-redirect=true";
    }

    public String logout() {
        this.clienteActual = null;
        this.cuenta = null;
        this.pin = null;
        this.monto = 0.0;
        return "index.xhtml?faces-redirect=true";
    }

    public void retirar() {
        if (clienteActual == null) {
            mensaje("No ha iniciado sesión.");
            return;
        }
        if (monto <= 0) {
            mensaje("Monto inválido.");
            return;
        }
        if (clienteActual.getSaldo() < monto) {
            mensaje("Saldo insuficiente.");
            return;
        }
        clienteActual.setSaldo(clienteActual.getSaldo() - monto);
        mensaje("Retiro exitoso. Nuevo saldo: " + String.format("%.2f", clienteActual.getSaldo()));
        // reset monto
        this.monto = 0.0;
    }

    public void depositar() {
        if (clienteActual == null) {
            mensaje("No ha iniciado sesión.");
            return;
        }
        if (monto <= 0) {
            mensaje("Monto inválido.");
            return;
        }
        clienteActual.setSaldo(clienteActual.getSaldo() + monto);
        mensaje("Depósito exitoso. Nuevo saldo: " + String.format("%.2f", clienteActual.getSaldo()));
        this.monto = 0.0;
    }

    private void mensaje(String texto) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(texto));
    }

    // getters y setters
    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public Cliente getClienteActual() { return clienteActual; }

    public java.util.List<com.atm.model.Cliente> getClientesPrueba() {
        return clienteService.listarClientes();
    }
}
