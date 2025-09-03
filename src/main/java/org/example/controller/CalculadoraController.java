package org.example.controller; //controla model (regras) e view (interface)

import org.example.model.CalculadoraModel;
import java.lang.reflect.Method;

public class CalculadoraController {
    private CalculadoraModel model;

    public CalculadoraController() {
        this.model = new CalculadoraModel();
    }

    public double executarOperacao(String nomeOperacao, double num1, double num2) {
        try {
            // usando Reflection para chamar dinamicamente
            Method metodo = model.getClass().getMethod( //getmethod pega a classe real do CalculadoraModel
                    nomeOperacao,
                    double.class,
                    double.class
            );
            return (double) metodo.invoke(model, num1, num2);//executa metodo
        } catch (Exception e) {
            throw new RuntimeException("Operação não encontrada: " + nomeOperacao);
        }
    }

    // catálogo dinâmico das operações
    public String[] getOperacoesDisponiveis() {
        try {
            Method[] metodos = model.getClass().getMethods();
            java.util.List<String> operacoes = new java.util.ArrayList<>();

            for (Method metodo : metodos) {
                if (metodo.getDeclaringClass() == model.getClass()) {//só pega os metodos da classe
                    operacoes.add(metodo.getName());
                }
            }

        return operacoes.toArray(new String[0]);
    } catch (Exception e) {
        return new String[]{"soma", "subtracao", "multiplicacao", "divisao"};
    }
}
}