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
            System.out.println("\n PEDIDOS ");
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
        long clienteId;
        while (true) {
            try {
                System.out.print("ID do cliente: ");
                clienteId = Long.parseLong(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números.");
            }
        }

        Map<Long, Integer> itens = new LinkedHashMap<>();
        System.out.println("Adicione os itens (digite 0 como ID para finalizar):");

        while (true) {
            System.out.print("  ID do produto: ");
            String entradaProd = scanner.nextLine().trim();
            if (!entradaProd.matches("\\d+")) {
                System.out.println("Erro: Digite apenas números.");
                continue;
            }
            long produtoId = Long.parseLong(entradaProd);
            if (produtoId == 0) break;

            System.out.print("  Quantidade: ");
            String entradaQtd = scanner.nextLine().trim();
            if (!entradaQtd.matches("\\d+")) {
                System.out.println("Erro: Digite apenas números.");
                continue;
            }
            int qtd = Integer.parseInt(entradaQtd);

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
        long id = -1;
        while (id < 0) {
            System.out.print("ID do pedido: ");
            String entrada = scanner.nextLine().trim();
            try {
                id = Long.parseLong(entrada);
                if (id <= 0) {
                    System.out.println("Digite um numero positivo.");
                    id = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite apenas numeros.");
            }
        }
        System.out.println(pedidoService.buscarPorId(id));
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

   private void listarPorCliente() {
    try {
        long clienteId = -1;
        while (clienteId < 0) {
            System.out.print("ID do cliente: ");
            String entrada = scanner.nextLine().trim();
            try {
                clienteId = Long.parseLong(entrada);
                if (clienteId <= 0) {
                    System.out.println("Digite um numero positivo.");
                    clienteId = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Digite apenas numeros.");
            }
        }
        List<Pedido> lista = pedidoService.listarPorCliente(clienteId);
        if (lista.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para esse cliente.");
            return;
        }
        lista.forEach(System.out::println);
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

    private void enviarParaFila() {
    while (true) {
        try {
            System.out.print("ID do pedido: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            pedidoService.enviarParaFila(id);
            break;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            break;
        }
    }
}


  private void listarPorStatus() {
    while (true) {
        try {
            System.out.println("Status: 1-ABERTO  2-FILA  3-PROCESSANDO  4-FINALIZADO");
            System.out.print("Escolha: ");
            StatusPedido status = switch (scanner.nextLine().trim()) {
                case "1" -> StatusPedido.ABERTO;
                case "2" -> StatusPedido.FILA;
                case "3" -> StatusPedido.PROCESSANDO;
                case "4" -> StatusPedido.FINALIZADO;
                default  -> throw new IllegalArgumentException("Opção inválida. Escolha de 1 a 4.");
            };
            
            List<Pedido> lista = pedidoService.listarPorStatus(status);
            if (lista.isEmpty()) {
                System.out.println("Nenhum pedido com esse status.");
            } else {
                lista.forEach(System.out::println);
            }
            break;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            break;
        }
    }
}

}