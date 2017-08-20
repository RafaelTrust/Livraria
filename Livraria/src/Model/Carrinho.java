package Model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private List<Livro> livros;
	private Usuario user;
	
	public Carrinho() {
		 livros = new ArrayList<Livro>();
	}
	
	public Carrinho(List<Livro> livros, Usuario user) {
		super();
		this.livros = livros;
		this.user = user;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Integer qntdLivros(List<Livro> livros)   {
	      int j = livros.size(); 
	      System.out.println("O vetor tem " + j + " valores");
	return j;
	}
	
	public void setItemCarrinho (Livro livro){
	        if (livros.contains(livro)){
	            int id = livros.indexOf(livro);
	            
	            int qtd = livros.get(id).getQntd();
	            
	            livros.get(id).setQntd(qtd + 1);
	        }else{
	            livros.add(livro);
	        }
    }
	
	public void updateItemCarrinho(Livro livro){
        if (livros.contains(livro)){
            int id = livros.indexOf(livro);
            
            if (livro.getQntd() <= 0){
                livros.remove(livro);
            } else {
                livros.get(id).setQntd(livro.getQntd());
            }
        }
    }
	
	public void removeItemCarrinho(int index){
       livros.remove(index);
    }

	@Override
	public String toString() {
		return "Carrinho [livros=" + livros + ", user=" + user + ", getLivros()=" + getLivros() + ", getUser()="
				+ getUser() + "]";
	}
	
	
}
