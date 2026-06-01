package com.sistema.thread;

import com.sistema.dao.PedidoDAO;
import com.sistema.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderProcessor implements Runnable {

    private static final int INTERVALO_MS = 5000;
    private static final int PROCESSAMENTO_MS = 3000;

    private volatile boolean rodando = true;
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    public void run() {
        System.out.println("[Thread] OrderProcessor iniciado.");
        while (rodando) {
            processarUm();
            aguardar(INTERVALO_MS);
        }
        System.out.println("[Thread] OrderProcessor encerrado.");
    }

    private void processarUm() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            conn.setAutoCommit(false);
            try {
                long pedidoId = pedidoDAO.reservarProximoPedidoDaFila(conn);
                if (pedidoId < 0) {
                    conn.rollback();
                    return;
                }

                conn.commit();
                System.out.println("[Thread] Processando pedido #" + pedidoId + "...");
                aguardar(PROCESSAMENTO_MS);