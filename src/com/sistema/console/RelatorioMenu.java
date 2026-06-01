package com.sistema.console;

import com.sistema.service.RelatorioService;

import java.util.List;
import java.util.Scanner;

public class RelatorioMenu {

private final Scanner scanner;
    private final RelatorioService relatorioService = new RelatorioService();

    public RelatorioMenu(Scanner scanner) {
        this.scanner = scanner;
    }

 public void exibir() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1. Top clientes por valor gasto");
            System.out.println("2. Produtos mais vendidos");
            System.out.println("3. Pedidos por status (ticket medio)");
            System.out.println("4. Estoque critico");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> topClientes();
                case "2" -> produtosMaisVendidos();
                case "3" -> pedidosPorStatus();
                case "4" -> estoqueCritico();
                case "0" -> voltar = true;
                default  -> System.out.println("Opcao invalida.");
            }
        }
    }


private void topClientes() {
        try {
            System.out.print("Quantos clientes exibir? ");
            int limite = Integer.parseInt(scanner.nextLine().trim());
            imprimir(relatorioService.topClientes(limite));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

private void produtosMaisVendidos() {
        try {
            System.out.print("Quantos produtos exibir? ");
            int limite = Integer.parseInt(scanner.nextLine().trim());
            imprimir(relatorioService.produtosMaisVendidos(limite));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void pedidosPorStatus() {
        try {
            imprimir(relatorioService.pedidosPorStatus());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void estoqueCritico() {
        try {
            System.out.print("Exibir produtos com estoque abaixo de: ");
            int limite = Integer.parseInt(scanner.nextLine().trim());
            imprimir(relatorioService.estoqueCritico(limite));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void imprimir(List<String> linhas) {
        System.out.println();
        linhas.forEach(System.out::println);
    }

}
