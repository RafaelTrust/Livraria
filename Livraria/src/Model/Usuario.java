package Model;

public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private Endereco endereco;
	private String celular;
	private String telefone;
	private int autenticador;
	
	public Usuario() {
		
	}

	public Usuario(int id, String nome, String email, String senha, String cpf, Endereco endereco, String celular,
			String telefone, int autenticador) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.endereco = endereco;
		this.celular = celular;
		this.telefone = telefone;
		this.autenticador = autenticador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getAutenticador() {
		return autenticador;
	}

	public void setAutenticador(int autenticador) {
		this.autenticador = autenticador;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", endereco=" + endereco + ", celular=" + celular + ", telefone=" + telefone + ", autenticador="
				+ autenticador + "]";
	}

}
