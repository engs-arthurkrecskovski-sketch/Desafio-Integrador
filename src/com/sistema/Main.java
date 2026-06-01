package com.sistema;

import com.sistema.console.ClienteMenu;
import com.sistema.console.PedidoMenu;
import com.sistema.console.ProdutoMenu;
import com.sistema.console.RelatorioMenu;
import com.sistema.thread.OrderProcessor;

import java.util.Scanner

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        OrderProcessor processor = new OrderProcessor();
        Thread threadProcessor = new Thread(processor, "order-processor");
        threadProcessor.setDaemon(true);
        threadProcessor.start();

        ClienteMenu clienteMenu     = new ClienteMenu(scanner);
        ProdutoMenu produtoMenu     = new ProdutoMenu(scanner);
        PedidoMenu pedidoMenu       = new PedidoMenu(scanner);
        RelatorioMenu relatorioMenu = new RelatorioMenu(scanner);

        System.out.println("=====================================");
        System.out.println("  SISTEMA DE GERENCIAMENTO DE PEDIDOS");
        System.out.println("=====================================");

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Clientes");
            System.out.println("2. Produtos");
            System.out.println("3. Pedidos");
            System.out.println("4. Relatorios");
            System.out.println("0. Sair");
            System.out.print("Opcao: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> clienteMenu.exibir();
                case "2" -> produtoMenu.exibir();
                case "3" -> pedidoMenu.exibir();
                case "4" -> relatorioMenu.exibir();
                case "0" -> {
                    processor.parar();
                    rodando = false;
                    System.out.println("Encerrando sistema. Ate logo!");
                }
                default -> System.out.println("Opcao invalida.");
            }
        }

        scanner.close();
    }
}