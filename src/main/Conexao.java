package main;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class Conexao {
	String URL = "jdbc:mysql://localhost:3306/sistemapetshop";
	String USER = "root";
	String PASSWORD = "123456"; 
	
	public Connection ConexaoBanco() {  
		Connection conn = null;
		
		try {  
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		}
		catch(SQLException e){
			System.err.println("Erro de conexão");
		}
		
		return conn;
	}
	
	public void InserirDados() {  
		String sql = "INSERT INTO clientes (nome, telefone, endereco) VALUES (?, ?, ?)";  
		
		try(Connection conn = ConexaoBanco();
			PreparedStatement cursor = conn.prepareStatement(sql)){  
			
			String nome = JOptionPane.showInputDialog("Digite seu nome");
			String telefone = JOptionPane.showInputDialog("Digite seu telefone");
			String endereco = JOptionPane.showInputDialog("Digite seu endereço");
			
			cursor.setString(1, nome);
			cursor.setString(2, telefone);
			cursor.setString(3, endereco);
			cursor.executeUpdate();
			JOptionPane.showMessageDialog(null, "Inserido com sucesso");
		}
		catch(SQLException e) {  
			System.err.println("Erro de inserir dados");
		}
	}
	
	public void VerDados() {
		String sql = "SELECT * FROM clientes";
		try(Connection conn = ConexaoBanco();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql)){
			 ArrayList<String> nomes = new ArrayList<String>();
			while(rs.next()) {
				
				int id = rs.getInt("id"); 
				String nome = rs.getString("nome");
				String tefelone = rs.getString("telefone");
				String endereco = rs.getString("endereco");
				
				
				 nomes.add("\nID: " + id + " Nome: " + nome + " Telefone: " + tefelone + " Endereço: " + endereco + ""); 
				
				
			}
			DefaultListModel<String> listModel = new DefaultListModel<>();
			
			for (String nome : nomes)
			{
				listModel.addElement(nome);
			}
			JList<String> listaNomes = new JList<>(listModel);
			
			JLabel label = new JLabel("Lista de nomes");
			
			JPanel painel = new JPanel();
			
			painel.setLayout(new BorderLayout());
			painel.add(label, BorderLayout.NORTH);
			painel.add(new JScrollPane(listaNomes), BorderLayout.CENTER);
			
			JFrame frame = new JFrame("Lista de nomes");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(700, 400);
			frame.add(painel);   
			frame.setVisible(true);
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
				
	}
	
	
}


