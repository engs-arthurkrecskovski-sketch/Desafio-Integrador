package com.sistema.console;

import com.sistema.model.Cliente;
import com.sistema.service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteMenu {

    private final Scanner scanner;
    private final ClienteService clienteService = new ClienteService();

    public ClienteMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibir() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Listar todos");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar cliente");
            System.out.println("5. Deletar cliente");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> cadastrar();
                case "2" -> listarTodos();
                case "3" -> buscarPorId();
                case "4" -> atualizar();
                case "5" -> deletar();
                case "0" -> voltar = true;
                default  -> System.out.println("Opcao invalida.");
            }
        }
    }

    private void cadastrar() {
    try {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        while (true) {
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            try {
                Cliente cliente = clienteService.cadastrar(nome, email);
                System.out.println("Cliente cadastrado: " + cliente);
                return; 
            } catch (com.sistema.exception.EmailInvalidoException e) {
                System.out.println("Erro: " + e.getMessage() + " — tente novamente.");
               
            }
        }
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

    private void listarTodos() {
        try {
            List<Cliente> lista = clienteService.listarTodos();
            if (lista.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
                return;
            }
            lista.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

   private void buscarPorId() {
    try {
        System.out.print("ID do cliente: ");
        long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID deve ser um numero inteiro.");
            return;
        }
        System.out.println(clienteService.buscarPorId(id));
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

    private void atualizar() {
    try {
        System.out.print("ID do cliente: ");
        long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID deve ser um numero inteiro.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        while (true) {
            System.out.print("Novo e-mail: ");
            String email = scanner.nextLine();
            try {
                Cliente atualizado = clienteService.atualizar(id, nome, email);
                System.out.println("Cliente atualizado: " + atualizado);
                return;
            } catch (com.sistema.exception.EmailInvalidoException e) {
                System.out.println("Erro: " + e.getMessage() + " — tente novamente.");
            }
        }
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

   private void deletar() {
    try {
        System.out.print("ID do cliente: ");
        long id;
        try {
            id = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Erro: ID deve ser um numero inteiro.");
            return;
        }
        clienteService.deletar(id);
        System.out.println("Cliente removido com sucesso.");
    } catch (java.sql.SQLException e) {
        if (e.getMessage() != null && e.getMessage().toLowerCase().contains("foreign key")) {
            System.out.println("Erro: Este cliente possui pedidos e nao pode ser removido.");
        } else {
            System.out.println("Erro: " + e.getMessage());
        }
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}
}