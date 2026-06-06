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
            System.out.println("\nPRODUTOS ");
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
        String nome = "";
        BigDecimal preco = null;
        int estoque = 0;
        Categoria categoria = null;

        while (true) {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();
            if (nome.length() >= 3 && !nome.matches(".*\\d.*")) {
                break;
            }
            System.out.println("Erro: Nome invalido. Tente novamente.");
        }

        while (true) {
            System.out.print("Preco: ");
            String entradaPreco = scanner.nextLine().trim().replace(",", ".");
            if (entradaPreco.matches("\\d+(\\.\\d+)?")) {
                preco = new BigDecimal(entradaPreco);
                break;
            }
            System.out.println("Erro: Preco invalido. Tente novamente.");
        }

        while (true) {
            System.out.print("Quantidade em estoque: ");
            String entradaEstoque = scanner.nextLine().trim();
            if (entradaEstoque.matches("\\d+")) {
                estoque = Integer.parseInt(entradaEstoque);
                break;
            }
            System.out.println("Erro: Quantidade invalida. Tente novamente.");
        }

        while (true) {
            try {
                categoria = lerCategoria();
                break; 
            } catch (Exception e) {
                System.out.println("Erro: Categoria invalida. Tente novamente.");
            }
        }

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
        while (true) {
            System.out.print("ID do produto: ");
            String entradaId = scanner.nextLine().trim();
            
            if (entradaId.matches("\\d+")) {
                long id = Long.parseLong(entradaId);
                System.out.println(produtoService.buscarPorId(id));
                break;
            }
            System.out.println("Erro: ID invalido. Tente novamente.");
        }
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

    private void atualizar() {
    try {
        long id = 0;
        String nome = "";
        BigDecimal preco = null;
        int estoque = 0;

        while (true) {
            System.out.print("ID do produto: ");
            String entradaId = scanner.nextLine().trim();
            if (entradaId.matches("\\d+")) {
                id = Long.parseLong(entradaId);
                break;
            }
            System.out.println("Erro: ID invalido. Tente novamente.");
        }

        while (true) {
            System.out.print("Novo nome: ");
            nome = scanner.nextLine().trim();
            if (nome.length() >= 3 && !nome.matches(".*\\d.*")) {
                break;
            }
            System.out.println("Erro: Nome invalido. Tente novamente.");
        }

        while (true) {
            System.out.print("Novo preco: ");
            String entradaPreco = scanner.nextLine().trim().replace(",", ".");
            if (entradaPreco.matches("\\d+(\\.\\d+)?")) {
                preco = new BigDecimal(entradaPreco);
                break;
            }
            System.out.println("Erro: Preco invalido. Tente novamente.");
        }

        while (true) {
            System.out.print("Nova quantidade em estoque: ");
            String entradaEstoque = scanner.nextLine().trim();
            if (entradaEstoque.matches("\\d+")) {
                estoque = Integer.parseInt(entradaEstoque);
                break;
            }
            System.out.println("Erro: Quantidade invalida. Tente novamente.");
        }

        Categoria categoria = lerCategoria();

        Produto p = produtoService.atualizar(id, nome, preco, estoque, categoria);
        System.out.println("Produto atualizado: " + p);
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

    private void deletar() {
    try {
        while (true) {
            System.out.print("ID do produto: ");
            String entradaId = scanner.nextLine().trim();
            
            if (entradaId.matches("\\d+")) {
                long id = Long.parseLong(entradaId);
                produtoService.deletar(id);
                System.out.println("Produto removido com sucesso.");
                break;
            }
            System.out.println("Erro: ID invalido. Digite apenas numeros.");
        }
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
}

    private Categoria lerCategoria() {
    while (true) {
        System.out.println("Categorias: 1-ALIMENTOS  2-ELETRONICOS  3-LIVROS");
        System.out.print("Escolha: ");
        switch (scanner.nextLine().trim()) {
            case "1" -> { return Categoria.ALIMENTOS; }
            case "2" -> { return Categoria.ELETRONICOS; }
            case "3" -> { return Categoria.LIVROS; }
            default  -> System.out.println("Erro: Opcao invalida. Tente novamente.");
        }
    }

}

}
