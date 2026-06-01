package com.sistema.console;

import com.sistema.model.Categoria;
import com.sistema.model.Produto;
import com.sistema.service.ProdutoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProdutoMenu {

    private final Scanner scanner;
    private final ProdutoService produtoService = new ProdutoService();

    public ProdutoMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibir() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- PRODUTOS ---");
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Listar todos");
            System.out.println("3. Listar por categoria");
            System.out.println("4. Buscar por ID");
            System.out.println("5. Atualizar produto");
            System.out.println("6. Deletar produto");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> cadastrar();
                case "2" -> listarTodos();
                case "3" -> listarPorCategoria();
                case "4" -> buscarPorId();
                case "5" -> atualizar();
                case "6" -> deletar();
                case "0" -> voltar = true;
                default  -> System.out.println("Opcao invalida.");
            }
        }
    }

    private void cadastrar() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Preco: ");
            BigDecimal preco = new BigDecimal(scanner.nextLine().trim().replace(",", "."));
            System.out.print("Quantidade em estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine().trim());
            Categoria categoria = lerCategoria();

            Produto p = produtoService.cadastrar(nome, preco, estoque, categoria);
            System.out.println("Produto cadastrado: " + p);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarTodos() {
        try {
            List<Produto> lista = produtoService.listarTodos();
            if (lista.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
                return;
            }
            lista.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorCategoria() {
        try {
            Categoria categoria = lerCategoria();
            List<Produto> lista = produtoService.listarPorCategoria(categoria);
            if (lista.isEmpty()) {
                System.out.println("Nenhum produto nessa categoria.");
                return;
            }
            lista.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscarPorId() {
        try {
            System.out.print("ID do produto: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            System.out.println(produtoService.buscarPorId(id));
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void atualizar() {
        try {
            System.out.print("ID do produto: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo preco: ");
            BigDecimal preco = new BigDecimal(scanner.nextLine().trim().replace(",", "."));
            System.out.print("Nova quantidade em estoque: ");
            int estoque = Integer.parseInt(scanner.nextLine().trim());
            Categoria categoria = lerCategoria();

            Produto p = produtoService.atualizar(id, nome, preco, estoque, categoria);
            System.out.println("Produto atualizado: " + p);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void deletar() {
        try {
            System.out.print("ID do produto: ");
            long id = Long.parseLong(scanner.nextLine().trim());
            produtoService.deletar(id);
            System.out.println("Produto removido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private Categoria lerCategoria() {
        System.out.println("Categorias: 1-ALIMENTOS  2-ELETRONICOS  3-LIVROS");
        System.out.print("Escolha: ");
        return switch (scanner.nextLine().trim()) {
            case "1" -> Categoria.ALIMENTOS;
            case "2" -> Categoria.ELETRONICOS;
            case "3" -> Categoria.LIVROS;
            default  -> throw new IllegalArgumentException("Categoria invalida.");
        };
    }
}
