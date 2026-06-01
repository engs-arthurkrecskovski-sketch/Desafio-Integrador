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