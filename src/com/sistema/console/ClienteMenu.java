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