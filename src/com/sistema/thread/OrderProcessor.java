package com.sistema.thread;

import com.sistema.dao.PedidoDAO;
import com.sistema.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderProcessor implements Runnable {