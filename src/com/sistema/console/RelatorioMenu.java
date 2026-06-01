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

private void topClientes() {
        try {
            System.out.print("Quantos clientes exibir? ");
            int limite = Integer.parseInt(scanner.nextLine().trim());
            imprimir(relatorioService.topClientes(limite));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }




}
