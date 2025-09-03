package org.example.view;

import org.example.controller.CalculadoraController;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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

            // Menu dinâmico
            for (int i = 0; i < operacoes.length; i++) {
                System.out.println((i + 1) + ". " + operacoes[i]);
            }

            System.out.println((operacoes.length + 1) + ". Listar Operações");
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

        System.out.println("Calculadora encerrada.");
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
        try {
            Class<?> classeModel = Class.forName("org.example.model.CalculadoraModel");
            Method[] metodos = classeModel.getDeclaredMethods();

            List<String> listaOps = new ArrayList<>();
            for (Method metodo : metodos) {
                listaOps.add(metodo.getName());
            }

            operacoes = listaOps.toArray(new String[0]); // atualiza as operações

            for (String op : operacoes) {
                System.out.println("- " + op);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar métodos: " + e.getMessage());
        }
    }
}


