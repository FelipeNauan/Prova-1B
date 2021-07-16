package entities;

public class ClinicoGeral extends Medico {
	
	private boolean realizaCirurgia;
	
	public ClinicoGeral(int crm, String nome, double valorConsulta, boolean realizaCirurgia) {
		super(crm, nome, valorConsulta);
		this.realizaCirurgia = realizaCirurgia;
	}

	public boolean isRealizarCirurgia() {
		return realizaCirurgia;
	}

	public void setRealizarCirurgia(boolean realizarCirurgia) {
		this.realizaCirurgia = realizarCirurgia;
	}
	
	public double totalConsulta(int tempoTotal) {
		if (isRealizarCirurgia() == true) {
			return (tempoTotal * this.getValorConsulta() + 10000);
		} else {
			return (tempoTotal * this.getValorConsulta());
		}
		
	}

	@Override
	public String toString() {
		return "ClinicoGeral [isRealizarCirurgia()=" + isRealizarCirurgia() + ", getCrm()=" + getCrm() + ", getNome()="
				+ getNome() + ", getValorConsulta()=" + getValorConsulta() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
