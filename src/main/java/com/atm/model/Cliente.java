package com.atm.model;

public class Cliente {
    private String numeroCuenta;
    private String pin;
    private double saldo;

    public Cliente(String numeroCuenta, String pin, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}
