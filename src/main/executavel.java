package main;

import javax.swing.JOptionPane;

public class executavel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conexao conn = new Conexao();
		
		while (true) {
			
			JOptionPane.showMessageDialog(null, "MENU \n1- Inserir dados \n2 - Ver dados \n3 - Deletar dados \n4 - Atualizar dados");
			
			
			String opcao = JOptionPane.showInputDialog("Digite uma opção");
			switch (opcao) {
			case "1": {
				conn.InserirDados();
				break;
			}
			case "2":
				conn.VerDados();
				break;
			case "5":
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcao);
			}

		}
	}

}
