package application;

import java.util.Scanner;
import entities.Clinica;
import entities.Medico;

public class Program {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Medico medico;
		String nomeClinica = "Sempre Saud�vel", nome;
		int crm, numero, codigo;
		double valorConsulta, valorTotal;
		char opcao;
		
		System.out.println("-==========-{ Antes de come�armos }-==========-");
		System.out.println("Informe o endere�o em que essa unidade se encontra: ");
		Clinica clinica = new Clinica(nomeClinica, input.nextLine());
		
		do {
			do {
				System.out.println();
				System.out.printf("-==========-{ Clinica %s }-==========-%n", nomeClinica);
				System.out.println("{ C }--===--{ Contratar M�dico }");
				System.out.println("{ D }--===--{ Demitir M�dico   }");
				System.out.println("{ M }--===--{ Marcar Consulta  }");
				System.out.println("{ P }--===--{ Pagar Consulta   }");
				System.out.println("{ I }--===--{ Informa��es do estado atual dos dados}");
				System.out.println("{ S }--===--{ Sair do programa }");
				opcao = input.next().toUpperCase().charAt(0);
			} while (opcao != 'C' && opcao != 'D' && opcao != 'M' && opcao != 'P' && opcao != 'I' && opcao != 'S');
			
			switch(opcao) {
			
			case 'C':
				System.out.println();
				System.out.println("Informe o CRM do m�dico: ");
				crm = input.nextInt();
				
				medico = clinica.buscarMedico(crm);
				
				if (medico != null) {
					System.out.println(medico.getNome()+" j� est� cadastrado na cl�nica.");
				} else {
					System.out.print("Informe o nome: ");
					input.nextLine();
					nome = input.nextLine();
					
					System.out.print("Informe o valor da consulta:");
					valorConsulta = input.nextInt();
					
					medico = new Medico(crm, nome, valorConsulta);
					if(clinica.contratarMedico(medico)) {
						System.out.println("M�dico foi contratado com sucesso!!!!");
					} else {
						System.out.println("Ocorreu um erro ao tentar contratar o M�dico em quest�o!");
					}
					
				}
				break;
				
			case 'D':
				System.out.println();
				System.out.println("Informe o CRM do m�dico que deseja demitir:");
				crm = input.nextInt();
				
				medico = clinica.buscarMedico(crm);
				
				if(medico != null) {
					do {
						System.out.println();
						System.out.println("Tem certeza que deseja demitir esse m�dico?");
						System.out.println("Todas as suas consultas ser�o desmarcadas! aten��o!");
						System.out.println("Pessoas iram morrer sem m�dicos!");
						System.out.println("{ S }-===-{ Sim }");
						System.out.println("{ N }-===-{ N�o }");
						opcao = input.next().toUpperCase().charAt(0);
					} while(opcao != 'S' && opcao != 'N');
					
					if(clinica.demitirMedico(medico)) {
						System.out.println("O m�dico foi demitido e todas as suas consultas foram desmarcadas.");
					}
				} else {
					System.out.println("O c�digo informado n�o foi encontrado no sistema.");
				}
				break;
			
			case 'M':
				
				System.out.println();
				System.out.println("Informe o CRM do m�dico: ");
				crm = input.nextInt();
				
				medico = clinica.buscarMedico(crm);
				
				if(medico != null) {
					numero = clinica.marcarConsulta(medico);
					System.out.println("Esse � o c�digo da consulta: "+numero);
				}else {
					System.out.println("O c�digo informado n�o foi encontrado no sistema.");
				}
				break;
			
			case 'P':
				do {
					System.out.println();
					System.out.println("Informe o c�digo da consulta que deseja pagar: ");
					codigo = input.nextInt();
				} while (codigo < 0 || codigo > 999);
				
				valorTotal = clinica.pagarConsulta(codigo);
				
				System.out.printf("O total a pagar � de R$ %.2f %n", valorTotal);
				
				break;
			case 'I':
				do {
					do {
						System.out.println();
						System.out.printf("-==========-{ Clinica %s }-==========-%n", nomeClinica);
						System.out.println("{ S }--===--{ Sum�rio das informa��es }");
						System.out.println("{ M }--===--{ Lista de m�dicos contratados   }");
						System.out.println("{ C }--===--{ Lista de consultas marcadas  }");
						System.out.println("{ V }--===--{ Voltar ao menu principal   }");
						opcao = input.next().toUpperCase().charAt(0);
					} while (opcao != 'S' && opcao != 'M' && opcao != 'C' && opcao != 'V');
					if(opcao == 'V')
						break;
					
					switch(opcao) {
					
					case 'S':
						clinica.sumarioClinica();
						break;
					case 'M':
						clinica.listarMedicos();
						break;
					case 'C':
						clinica.listarConsultas();
						break;
					}
					
				} while (true);
				break;
			case 'S':
				System.out.println();
				System.out.println("Finalizando Programa.");
				System.exit(0);
				break;
			}
		} while (true);
		
	}

}
