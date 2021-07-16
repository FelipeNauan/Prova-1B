package entities;

public class Pediatria extends Medico {

	private int anosExperiencia;

	public Pediatria(int crm, String nome, double valorConsulta, int anosExperiencia) {
		super(crm, nome, valorConsulta);
		this.anosExperiencia = anosExperiencia;
	}

	public void setAnosExperiencia(int anosExperiencia) {
		this.anosExperiencia = anosExperiencia;
	}

	public int getAnosExperiencia() {
		return anosExperiencia;
	}

	public double totalConsulta(int tempoTotal) {
		if (this.anosExperiencia >= 10) {
			return (tempoTotal * this.getValorConsulta() * 2);
		} else {
			return (tempoTotal * this.getValorConsulta());
		}
	}

	@Override
	public String toString() {
		return "Pediatria [getAnosExperiencia()=" + this.getAnosExperiencia() + ", getCrm()=" + getCrm()
				+ ", getNome()=" + getNome() + ", getValorConsulta()=" + getValorConsulta() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
