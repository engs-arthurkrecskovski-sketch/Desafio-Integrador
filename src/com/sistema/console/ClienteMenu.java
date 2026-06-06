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
            System.out.println("\nCLIENTES ");
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
    String nome = "";
    while (true) {
        System.out.print("Nome: ");
        nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Erro: Nome nao pode ser vazio. Tente novamente.");
        } else if (nome.length() < 3) {
            System.out.println("Erro: Nome deve ter ao menos 3 caracteres. Tente novamente.");
        } 
          else if (nome.matches(".*\\d.*")) { 
            System.out.println("Erro: Nome nao pode conter numeros. Tente novamente.");
        }
        else {
            break;
        }
    }

    while (true) {
        System.out.print("E-mail: ");
        String email = scanner.nextLine().trim();
        try {
            Cliente cliente = clienteService.cadastrar(nome, email);
            System.out.println("Cliente cadastrado: " + cliente);
            return;
        } catch (com.sistema.exception.EmailInvalidoException e) {
            System.out.println("Erro: " + e.getMessage() + " — tente novamente.");
        } catch (com.sistema.exception.ValidacaoException e) {
            System.out.println("Erro: " + e.getMessage() + " — tente novamente.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }
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
    while (true) {
        try {
            System.out.print("ID do cliente: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            System.out.println(clienteService.buscarPorId(id));
            break;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Digite apenas números.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            break;
        }
    }
}


  private void atualizar() {
    long id = 0;
    String nome = "", email = "";

    while (true) {
        System.out.print("ID do cliente: ");
        String entradaId = scanner.nextLine().trim();
        if (entradaId.matches("\\d+")) { 
            id = Long.parseLong(entradaId);
            break;
        }
        System.out.println("Erro: ID inválido. Digite apenas números.");
    }

    while (true) {
        System.out.print("Novo nome: ");
        nome = scanner.nextLine().trim();
        if (nome.length() >= 3 && !nome.matches(".*\\d.*")) {
            break; 
        }
        System.out.println("Erro: Nome inválido (mínimo 3 letras e sem números).");
    }

    while (true) {
        System.out.print("Novo e-mail: ");
        email = scanner.nextLine().trim();
        try {
            Cliente atualizado = clienteService.atualizar(id, nome, email);
            System.out.println("Cliente atualizado: " + atualizado);
            break; 
        } catch (com.sistema.exception.EmailInvalidoException e) {
            System.out.println("Erro: E-mail inválido — tente novamente.");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            break;
        }
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