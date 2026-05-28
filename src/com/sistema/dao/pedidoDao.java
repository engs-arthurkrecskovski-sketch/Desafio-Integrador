package com.sistema.dao;

import com.sistema.exception.EstoqueInsuficienteException;
import com.sistema.model.*;
import com.sistema.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDAO {