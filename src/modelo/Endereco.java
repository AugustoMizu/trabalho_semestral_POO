package modelo;

public class Endereco {
	private String cep;
	private String endereco;
	
	public Endereco(String cep, String endereco) {		
		this.cep = cep;
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
 
}
