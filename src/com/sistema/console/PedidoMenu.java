package com.sistema.console;

import com.sistema.model.Pedido;
import com.sistema.model.StatusPedido;
import com.sistema.service.PedidoService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PedidoMenu {

    private final Scanner scanner;
    private final PedidoService pedidoService = new PedidoService();

    public PedidoMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibir() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("1. Criar pedido");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Listar por cliente");
            System.out.println("5. Enviar pedido para fila");
            System.out.println("6. Listar por status");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> criar();
                case "2" -> listarTodos();
                case "3" -> buscarPorId();
                case "4" -> listarPorCliente();
                case "5" -> enviarParaFila();
                case "6" -> listarPorStatus();
                case "0" -> voltar = true;
                default  -> System.out.println("Opcao invalida.");
            }
        }
    }