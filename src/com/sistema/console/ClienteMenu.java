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
            System.out.print("E-mail: ");
            String email = scanner.nextLine();

            Cliente cliente = clienteService.cadastrar(nome, email);
            System.out.println("Cliente cadastrado: " + cliente);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    