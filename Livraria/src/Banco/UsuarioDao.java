package Banco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Carrinho;
import Model.Endereco;
import Model.Livro;
import Model.Usuario;

public class UsuarioDao extends Dao {
	
	public boolean cadastrarEndereco(Endereco endereco)throws Exception{
		boolean check = false;
		try{
			open();
			stmt = con.prepareStatement("insert into endereco values(null, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, endereco.getEstado());
			stmt.setString(2, endereco.getCidade());
			stmt.setString(3, endereco.getEndereco());
			stmt.setString(4, endereco.getComplemento());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getCep());
			
			stmt.execute();
			
			check = true;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
				
	    return check;
	}
	
	public int returIdend(Endereco endereco)throws Exception{
		int check = 0;
		try{
			open();
			stmt = con.prepareStatement("select id from endereco where endereco = ? and cep = ?");
		    stmt.setString(1, endereco.getEndereco());
			stmt.setString(2, endereco.getCep());
			
			rs = stmt.executeQuery();

            if (rs.next()) {
            	check = rs.getInt("id");
            }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return check;
	}

	public boolean cadastrar (Usuario user)throws Exception{
		boolean check = false;
		try{
			open();
			stmt = con.prepareStatement("insert into usuario values(null, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getSenha());
			stmt.setString(4, user.getCpf());
			stmt.setString(5, user.getCelular());
			stmt.setString(6, user.getTelefone());
			stmt.setInt(7, user.getEndereco().getId());
			stmt.setInt(8, user.getAutenticador());
			
			stmt.execute();
			
			check = true;		
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return check;
	}
	
	public boolean checkAutenticador(String email, String senha)throws Exception{
		boolean check = false;
		try{
			open();
			stmt = con.prepareStatement("select autenticador from usuario where email = ? and senha = ?");
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				if(rs.getInt("autenticador") != 0){
					check = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return check;
	}
	public boolean checkLogin(String email, String senha) throws Exception {
		boolean check = false;
		
        try {
        	open();
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? and senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
           
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                check = true;
            }

        } catch (SQLException e) {
        	e.printStackTrace();
        }finally{
        	close();
        }

        return check;

    }
	
	public Usuario buscarUsuario(String email, String senha) throws Exception {
		Usuario user = new Usuario();
		
        try {
        	open();
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ? and senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
           
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            	user = new Usuario(rs.getInt("id"), rs.getString("Nome"), rs.getString("email"), rs.getString("senha"), rs.getString("cpf"), null, rs.getString("celular"), rs.getString("telefone"),rs.getInt("autenticador"));
            }

        } catch (SQLException e) {
        	e.printStackTrace();
        }finally{
        	close();
        }

        return user;

    }
	
	public List<Livro> buscar(String nome)throws Exception{
		List<Livro>lista = new ArrayList<Livro>();
		try{
			open();
			stmt = con.prepareStatement("SELECT * FROM livro WHERE titulo like ?");
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			  while(rs.next()){
			  Livro f = new Livro(rs.getInt("id"),rs.getString("titulo"),rs.getString("autor"),rs.getFloat("preco"), 1);
			  System.out.println("O valor de id é: " + rs.getInt("id"));
              lista.add(f);
		     }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return lista;
	}
	
	public Usuario ultimoUsuario()throws Exception{
		Usuario user = new Usuario();
		user = null;
		
		try{
			open();
			stmt = con.prepareStatement("select * from usuario order by id desc limit 1");
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				user = new Usuario(rs.getInt("id"), rs.getString("Nome"), rs.getString("email"), rs.getString("senha"), rs.getString("cpf"), null, rs.getString("celular"), rs.getString("telefone"),rs.getInt("autenticador"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return user;
	}
	
	public boolean autenticar(int aut, String email)throws Exception{
		boolean check = false;
		try{
			open();
			stmt = con.prepareStatement("select * from usuario where id = ? and email = ?");
			stmt.setInt(1, aut);
			stmt.setString(2, email);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				check = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		
		return check;
	}
	
	public boolean autenticado(int aut)throws Exception{
		boolean check = false;
		try{
			open();
			stmt = con.prepareStatement("update usuario set autenticador = 1 where id = ?");
			stmt.setInt(1, aut);
			
			stmt.execute();
			
			check = true;
			
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return check;
	}
	
	public boolean carrinho(Carrinho carrinho)throws Exception{
		boolean check = false;
		int v = 0;
		int qntd = carrinho.qntdLivros(carrinho.getLivros());
		try{
			open();
			for(int i = 0; i < qntd ; i++){
			Livro livro = new Livro();
			livro = carrinho.getLivros().get(i);
			stmt = con.prepareStatement("insert into carrinho values (null, ?, ?, ?)");
			stmt.setInt(1, livro.getId());
			stmt.setInt(2, carrinho.getUser().getId());
			stmt.setInt(3, livro.getQntd());
			
			stmt.execute();
			
			v++;
			}
			if (v == qntd){
				check = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return check;
	}
	
	public Livro comprarLivro(int id)throws Exception{
		Livro livro = new Livro();
		livro = null;
		
		try{
			open();
			stmt = con.prepareStatement("select * from livro where id = ?");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getFloat("preco"), 1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return livro;
	}
	
	public List<Integer> pegarIdlivro(int id)throws Exception{
		List<Integer> idlivros = new ArrayList<Integer>();
		try{
			open();
			stmt = con.prepareStatement("select * from carrinho where idusuario = ?");
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				idlivros.add(rs.getInt("idlivro"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return idlivros;
	}
	
	public Carrinho pegarCarrinho(List<Integer> idlivros)throws Exception{
		Carrinho carrinho = new Carrinho();
		try{
			open();
			for (int i = 0; i < idlivros.size(); i++){
				stmt = con.prepareStatement("select * from livro where id = ?");
				stmt.setInt(1, idlivros.get(i));
				
				rs = stmt.executeQuery();
				
				if(rs.next()){
					Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getFloat("preco"), rs.getInt("qntd"));
					carrinho.getLivros().add(livro);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		return carrinho;
	}
}

