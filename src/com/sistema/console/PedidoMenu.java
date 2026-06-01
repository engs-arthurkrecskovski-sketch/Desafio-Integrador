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

    private void criar() {
        try {
            System.out.print("ID do cliente: ");
            long clienteId = Long.parseLong(scanner.nextLine().trim());

            Map<Long, Integer> itens = new LinkedHashMap<>();
            System.out.println("Adicione os itens (digite 0 como ID para finalizar):");

            while (true) {
                System.out.print("  ID do produto: ");
                long produtoId = Long.parseLong(scanner.nextLine().trim());
                if (produtoId == 0) break;

                System.out.print("  Quantidade: ");
                int qtd = Integer.parseInt(scanner.nextLine().trim());
                itens.put(produtoId, qtd);
            }

            Pedido pedido = pedidoService.criar(clienteId, itens);
            System.out.println("Pedido criado com sucesso!\n" + pedido);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        try {
            List<Pedido> lista = pedidoService.listarTodos();
            if (lista.isEmpty()) {
                System.out.println("Nenhum pedido encontrado.");
                return;
            }
            lista.forEach(p -> System.out.println(p));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscarPorId() {
        try {
            System.out.print("ID do pedido: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            System.out.println(pedidoService.buscarPorId(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }