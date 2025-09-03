package org.example;

import org.example.view.MenuView;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Calculadora MVC com Reflection...");
        MenuView menu = new MenuView();
        menu.exibirMenu();
    }
}