SISTEMA DE GERENCIAMNETO DE PEDIDOS 

        Sistema de gerenciamento de clientes, produtos e pedidos com interface de console, 
        persistência via JDBC MySQL e processamento assíncrono de pedidos em thread separada.


PRÉ-REQUISITOS

        - Java 17+
        - MySQL 8+
        - Maven ou compile manualmente com javac
        - Driver JDBC do MySQL: mysql-connector-java no classpath


CONFIGURAÇÃO DO BANCO DE DADOS 

        1. Inicie o MySQL e acesse o client:
        bash
        mysql -u root -p

        2. Execute o script DDL para criar o banco e as tabelas:
        bash
        mysql -u root -p < schema.sql

        3. Se necessário, ajuste usuário e senha em:
        src/main/java/com/sistema/util/ConnectionUtil.java
        campos USER e PASS


COMPILAÇÃO E EXECUÇÃO 

        COM MAVEN
        bash
        mvn compile
        mvn exec:java -Dexec.mainClass="com.sistema.Main"
    
        SEM MAVEN - COMPILAÇÃO MANUAL
        bash
        
        COMPILE TODOS OS FONTES
        find src -name "*.java" > fontes.txt
        javac -cp mysql-connector-java-8.x.jar -d out @fontes.txt

        EXECUTE
        java -cp out:mysql-connector-java-8.x.jar com.sistema.Main


Estrutura do projeto

        SRC/
        └── Com/Sistema   
            │        
            ├── Console - Menus de console / Sem nenhum import java.sql
            │   ├── ClienteMenu.java
            │   ├── ProdutoMenu.java
            │   ├── PedidoMenu.java
            │   └── RelatorioMenu.java
            │ 
            ├── Dao - Acesso ao banco via JDBC
            │   ├── ClienteDAO.java
            │   ├── ItemPedidoDAO.java    
            │   ├── PedidoDAO.java
            │   ├── ProdutoDAO.java
            │   └── RelatorioDAO.java
            │
            ├── Exception - Exceções customizadas
            │   ├── EmailInvalidoException.java
            │   ├── EntidadeNaoEncontradaException.java
            │   ├── EstoqueInsuficienteException.java
            │   └── ValidacaoException.java
            │
            ├── Model - Entidades imutáveis / Sem setters
            │   ├── Categoria.java               
            │   ├── Cliente.java                 
            │   ├── ItemPedido.java
            │   ├── Pedido.java
            │   ├── Produto.java
            │   └── StatusPedido.java
            │ 
            ├── Service - Regras de negócio e validações
            │   ├── ClienteService.java
            │   ├── PedidoService.java            
            │   ├── ProdutoService.java
            │   └── RelatorioService.java
            │
            ├── Thread
            │   └── OrderProcessor.java
            │
            ├── Util
            │   └── ConnectionUtil.java
            │
            ├── Main.java
            │         
            ├── Readme.md
            │ 
            └── Schema.sql


