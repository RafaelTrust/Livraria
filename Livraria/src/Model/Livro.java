package Model;

public class Livro {
	private int id;
	private String titulo;
	private String autor;
	private float preco;
	private int qntd;
	
	public Livro() {
		
	}

	public Livro(int id, String titulo, String autor, float preco, int qntd) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.preco = preco;
		this.qntd = qntd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public int getQntd() {
		return qntd;
	}

	public void setQntd(int qntd) {
		this.qntd = qntd;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", preco=" + preco + ", qntd=" + qntd
				+ "]";
	}
	
}
