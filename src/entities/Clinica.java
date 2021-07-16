package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Clinica {

	private String nomeClinica;
	private String endereco;
	private ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
	private ArrayList<Consulta> listaConsultas = new ArrayList<Consulta>();

	public Clinica(String nomeClinica, String endereco) {
		this.nomeClinica = nomeClinica;
		this.endereco = endereco;
	}
	
	public String getNomeClinica() {
		return nomeClinica;
	}

	public void setNomeClinica(String nomeClinica) {
		this.nomeClinica = nomeClinica;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean contratarMedico(Medico medico) {
		Scanner input = new Scanner(System.in);
		char opcao;
		boolean realizaCirurgia;
		int anosExperiencia;
		do {
			System.out.println("Informe a área do médico que deseja contratar: ");
			System.out.println("{P}--===--{ Pediatra }");
			System.out.println("{C}--===--{ Clinico Geral }");
			opcao = input.next().toUpperCase().charAt(0);
		} while (opcao != 'P' && opcao != 'C');

		if (opcao == 'P') {
			do {
				System.out.print("Informe quantos anos de experiência você possue: ");
				anosExperiencia = input.nextInt();
			} while (anosExperiencia < 0 || anosExperiencia > 150);
			
			Pediatria pedigri = new Pediatria(medico.getCrm(), medico.getNome(), medico.getValorConsulta(),
					anosExperiencia);
			listaMedicos.add(pedigri);
			return true;
		} else {
			do {
				System.out.println("Informe se você está realizando cirurgia: ");
				System.out.println("{S}--===--{ Sim }");
				System.out.println("{N}--===--{ Não }");
				opcao = input.next().toUpperCase().charAt(0);
			} while(opcao != 'S' && opcao != 'N');
			
			if(opcao == 'S') {
				realizaCirurgia = true;
			} else {
				realizaCirurgia = false;
			}
			
			ClinicoGeral clino = new ClinicoGeral(medico.getCrm(), medico.getNome(), medico.getValorConsulta(),
					realizaCirurgia);
			listaMedicos.add(clino);
			return true;
		}

	}

	public boolean demitirMedico(Medico medico) {
		for (int i = 0; i < listaConsultas.size(); i++) {
			if (listaConsultas.get(i).getMedicoResponsavel().getCrm() == medico.getCrm()) {
				listaConsultas.remove(i);
			}
		}
		listaMedicos.remove(medico);
		return true;
	}

	public Medico buscarMedico(int crm) {
		for (Medico var : listaMedicos) {
			if (var.getCrm() == crm) {
				return var;
			}
		}
		return null;
	}

	public int marcarConsulta(Medico medico) {
		Random naoETaoAleatorioAssim = new Random();
		boolean flag;
		Consulta consulta;
		int codConsulta = naoETaoAleatorioAssim.nextInt(999);
		
		do {
			flag = false;
			for (Consulta consu : listaConsultas) {
				if (consu.getCodConsulta() == codConsulta) {
					codConsulta = naoETaoAleatorioAssim.nextInt(999);
					flag = true;
					break;
				}
			}

		} while (flag);
		
		consulta = new Consulta(codConsulta, medico);
		listaConsultas.add(consulta);
		return codConsulta;
	}

	public double pagarConsulta(int codConsulta) {
		Scanner input = new Scanner(System.in);
		int tempoTotal;
		for (Consulta var : listaConsultas) {
			if (var.getCodConsulta() == codConsulta) {
				System.out.println();
				System.out.print("Informe o tempo da consulta: ");
				tempoTotal = input.nextInt();
				return var.getMedicoResponsavel().totalConsulta(tempoTotal);
			}
		}
		System.out.println("Não foi possivel encontrar consultas com esse código informado.");
		return 0;
	}
	
	public void sumarioClinica() {
		System.out.println();
		System.out.println("Clinica: " + this.getNomeClinica());
		System.out.println("Localizada no endereço: " + this.getEndereco());
		System.out.printf("A clinica atualmente possui %d Médico(s) contratado(s);%n", listaMedicos.size());
		System.out.printf("Possui %d consulta(s) marcada(s) dentre todos os médicos contratados.%n", listaConsultas.size());
	}
	
	public void listarMedicos() {
		System.out.println();
		System.out.println("Lista atual de Médicos contratados;");
		for(Medico medico: listaMedicos) {
			if(medico instanceof ClinicoGeral) {
				System.out.printf("Doutor: %s   Area Atuação: Clinico Geral   CRM: %d %n", medico.getNome(), medico.getCrm());
			} else if (medico instanceof Pediatria) {
				System.out.printf("Doutor: %s   Area Atuação: Pediatria       CRM: %d %n", medico.getNome(), medico.getCrm());
			}
		}
	}
	
	public void listarConsultas() {
		System.out.println();
		System.out.println("Lista atual de todas as consultas;");
		for(Consulta consulta: listaConsultas) {
			System.out.printf("Código Consulta: %s  Medico Responsavel{", consulta.getCodConsulta());
			if(consulta.getMedicoResponsavel() instanceof ClinicoGeral) {
				System.out.printf("Doutor: %s   Area Atuação: Clinico Geral   CRM: %d } %n", consulta.getMedicoResponsavel().getNome(), consulta.getMedicoResponsavel().getCrm());
			} else if (consulta.getMedicoResponsavel() instanceof Pediatria) {
				System.out.printf("Doutor: %s   Area Atuação: Pediatria       CRM: %d } %n", consulta.getMedicoResponsavel().getNome(), consulta.getMedicoResponsavel().getCrm());
			}
		}
		if(listaConsultas.size() == 0) {
			System.out.println("No momento não existe nenhuma consulta marcada.");
		}
	}

}
