package org.example.view;

import org.example.controller.CalculadoraController;
import java.lang.reflect.Method;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    private CalculadoraController controller;
    private String[] operacoes;

    public MenuView() {
        this.scanner = new Scanner(System.in);
        this.controller = new CalculadoraController();
        this.operacoes = controller.getOperacoesDisponiveis();
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CALCULADORA MVC ===");

            // Menu dinâmico baseado nas operações disponíveis
            for (int i = 0; i < operacoes.length; i++) {
                System.out.println((i + 1) + ". " + capitalizar(operacoes[i]));
            }

            System.out.println((operacoes.length + 1) + ". Listar Operações (Reflection)");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            if (opcao >= 1 && opcao <= operacoes.length) {
                processarOperacao(opcao);
            } else if (opcao == operacoes.length + 1) {
                listarOperacoes();
            } else if (opcao != 0) {
                System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Calculadora encerrada. Até logo!");
        scanner.close();
    }

    private void processarOperacao(int opcao) {
        System.out.print("Primeiro número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Segundo número: ");
        double num2 = scanner.nextDouble();

        String nomeMetodo = operacoes[opcao - 1];

        try {
            double resultado = controller.executarOperacao(nomeMetodo, num1, num2);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarOperacoes() {
        System.out.println("\n=== MÉTODOS DISPONÍVEIS (Reflection) ===");
        try {
            // Usando Reflection para inspecionar a classe Model
            Class<?> classeModel = Class.forName("com.exemplo.model.CalculadoraModel");
            Method[] metodos = classeModel.getMethods();

            for (Method metodo : metodos) {
                if (metodo.getParameterCount() == 2 &&
                        metodo.getParameterTypes()[0] == double.class &&
                        metodo.getReturnType() == double.class) {
                    System.out.println("- " + metodo.getName() + "(): " +
                            metodo.getReturnType().getSimpleName());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar métodos: " + e.getMessage());
        }
    }

    private String capitalizar(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}