package org.example.model; //regras de negocio

public class CalculadoraModel {

    public double soma(double a, double b) {
        return a + b;
    }

    public double subtracao(double a, double b) {
        return a - b;
    }

    public double multiplicacao(double a, double b) {
        return a * b;
    }

    public double divisao(double a, double b) {
        if (b == 0) throw new ArithmeticException("Divisão por zero!");
        return a / b;
    }

    public double potencia(double base, double expoente) {
        return Math.pow(base, expoente);
    }
}