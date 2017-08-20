package Model;

public class Endereco {
	private int id;
	private String estado;
	private String cidade;
	private String endereco;
	private String complemento;
	private String bairro;
	private String cep;
	
	public Endereco() {
		
	}

	public Endereco(int id, String estado, String cidade, String endereco, String complemento, String bairro,
			String cep) {
		super();
		this.id = id;
		this.estado = estado;
		this.cidade = cidade;
		this.endereco = endereco;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", estado=" + estado + ", cidade=" + cidade + ", endereco=" + endereco
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cep=" + cep + "]";
	}
	
}
