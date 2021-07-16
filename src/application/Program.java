package application;

import java.util.Scanner;
import entities.Clinica;
import entities.Medico;

public class Program {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Medico medico;
		String nomeClinica = "Sempre Saudável", nome;
		int crm, numero, codigo;
		double valorConsulta, valorTotal;
		char opcao;
		
		System.out.println("-==========-{ Antes de começarmos }-==========-");
		System.out.println("Informe o endereço em que essa unidade se encontra: ");
		Clinica clinica = new Clinica(nomeClinica, input.nextLine());
		
		do {
			do {
				System.out.println();
				System.out.printf("-==========-{ Clinica %s }-==========-%n", nomeClinica);
				System.out.println("{ C }--===--{ Contratar Médico }");
				System.out.println("{ D }--===--{ Demitir Médico   }");
				System.out.println("{ M }--===--{ Marcar Consulta  }");
				System.out.println("{ P }--===--{ Pagar Consulta   }");
				System.out.println("{ I }--===--{ Informações do estado atual dos dados}");
				System.out.println("{ S }--===--{ Sair do programa }");
				opcao = input.next().toUpperCase().charAt(0);
			} while (opcao != 'C' && opcao != 'D' && opcao != 'M' && opcao != 'P' && opcao != 'I' && opcao != 'S');
			
			switch(opcao) {
			
			case 'C':
				System.out.println();
				System.out.println("Informe o CRM do médico: ");
				crm = input.nextInt();
				
				medico = clinica.buscarMedico(crm);
				
				if (medico != null) {
					System.out.println(medico.getNome()+" já está cadastrado na clínica.");
				} else {
					System.out.print("Informe o nome: ");
					input.nextLine();
					nome = input.nextLine();
					
					System.out.print("Informe o valor da consulta:");
					valorConsulta = input.nextInt();
					
					medico = new Medico(crm, nome, valorConsulta);
					if(clinica.contratarMedico(medico)) {
						System.out.println("Médico foi contratado com sucesso!!!!");
					} else {
						System.out.println("Ocorreu um erro ao tentar contratar o Médico em questão!");
					}
					
				}
				break;
				
			case 'D':
				System.out.println();
				System.out.println("Informe o CRM do médico que deseja demitir:");
				crm = input.nextInt();
				
				medico = clinica.buscarMedico(crm);
				
				if(medico != null) {
					do {
						System.out.println();
						System.out.println("Tem certeza que deseja demitir esse médico?");
						System.out.println("Todas as suas consultas serão desmarcadas! atenção!");
						System.out.println("Pessoas iram morrer sem médicos!");
						System.out.println("{ S }-===-{ Sim }");
						System.out.println("{ N }-===-{ Não }");
						opcao = input.next().toUpperCase().charAt(0);
					} while(opcao != 'S' && opcao != 'N');
					
					if(clinica.demitirMedico(medico)) {
						System.out.println("O médico foi demitido e todas as suas consultas foram desmarcadas.");
					}
				} else {
					System.out.println("O código informado não foi encontrado no sistema.");
				}
				break;
			
			case 'M':
				
				System.out.println();
				System.out.println("Informe o CRM do médico: ");
				crm = input.nextInt();
				
				medico = clinica.buscarMedico(crm);
				
				if(medico != null) {
					numero = clinica.marcarConsulta(medico);
					System.out.println("Esse é o código da consulta: "+numero);
				}else {
					System.out.println("O código informado não foi encontrado no sistema.");
				}
				break;
			
			case 'P':
				do {
					System.out.println();
					System.out.println("Informe o código da consulta que deseja pagar: ");
					codigo = input.nextInt();
				} while (codigo < 0 || codigo > 999);
				
				valorTotal = clinica.pagarConsulta(codigo);
				
				System.out.printf("O total a pagar é de R$ %.2f %n", valorTotal);
				
				break;
			case 'I':
				do {
					do {
						System.out.println();
						System.out.printf("-==========-{ Clinica %s }-==========-%n", nomeClinica);
						System.out.println("{ S }--===--{ Sumário das informações }");
						System.out.println("{ M }--===--{ Lista de médicos contratados   }");
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
