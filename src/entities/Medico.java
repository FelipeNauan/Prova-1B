package entities;

public class Medico {

	private int crm;
	private String nome;
	private double valorConsulta;

	public Medico(int crm, String nome, double valorConsulta) {
		this.crm = crm;
		this.nome = nome;
		this.valorConsulta = valorConsulta;
	}

	public int getCrm() {
		return crm;
	}

	public void setCrm(int crm) {
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorConsulta() {
		return valorConsulta;
	}

	public void setValorConsulta(double valorConsulta) {
		this.valorConsulta = valorConsulta;
	}

	public double totalConsulta(int tempoTotal) {
		return (tempoTotal * this.getValorConsulta());
	}
	
	@Override
	public String toString() {
		return "Medico [getCrm()=" + getCrm() + ", getNome()=" + getNome() + ", getValorConsulta()="
				+ getValorConsulta() + "]";
	}

}
