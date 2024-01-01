import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class AP2_2021_Q2 {

	public static void main(String[] args) {

		if (args.length == 0) {

			System.out.println("Nome do(s) Arquivo(s) precisa(m) ser passado(s) como argumento!");
			System.exit(-1);
		}

		List<List<Conta>> arquivosEmLista = new ArrayList<List<Conta>>();

		for (String arq : args) {

			try {
				System.out.println("Nome do Arquivo: " + arq);
				arquivosEmLista.add(leitorArquivos(arq));

			} catch (IOException ex) {

				System.out.println(ex.getMessage());
			}

		}
		
		
		System.out.println("Letra (a):");
		for (List<Conta> c : arquivosEmLista) {
			
			getNumerosMaisRepetidosIndividual(c).forEachRemaining((Consumer<? super Conta>) conta -> System.out.print(conta));
		}
		System.out.println();
		
		System.out.println("Letra (b)");
		for (List<Conta> c : arquivosEmLista) {
			
			getNumerosNaoRepetidosIndividual(c).forEachRemaining((Consumer<? super Conta>) conta -> System.out.print(conta));
			System.out.println();
		}
		
		
		
		// Questão c incompleta comentando para o código compilar...
		/*
		System.out.println("Letra (c)");
		
		
		List<Conta> numerosTemp = new ArrayList<>();
		List<Conta> numerosEmTodos = new ArrayList<>();
		
		for(List<Conta>  listaContas : arquivosEmLista) {
			
			if (numerosTemp.isEmpty()){
				numerosTemp.addAll(listaContas);
				continue;
			}
			
			System.out.println("Numero em todos: " + numerosEmTodos);
			System.out.println("Numeros Temp: "  + numerosTemp);
			
			for (Conta conta : listaContas) {
				
				Conta contaTemp = numerosTemp.stream().filter(c3 -> c3.num == conta.num).findFirst().get();
				System.out.println("Conta Temp: " + contaTemp);
				
				if(contaTemp != null) {
					System.out.println("Conta não é nulo");
					numerosEmTodos.add(contaTemp);
				}
				
			}
			
		}
		System.out.println(numerosEmTodos);
		
		*/
	}

	public static List<Conta> leitorArquivos(String nomeArq) throws IOException, FileNotFoundException {

		List<Conta> contas = new ArrayList<>();

		FileInputStream fil = new FileInputStream(nomeArq);
		InputStreamReader isr = new InputStreamReader(fil);
		BufferedReader br = new BufferedReader(isr);

		String linha = br.readLine();

		while (linha != null) {
			
			String[] numeros = linha.split(" "); 
			int numLinha = 0;
			
			for (String s : numeros) {
				if (numeros.length == 0) {
					contas.add(new Conta(Integer.parseInt(s), numLinha));
				} else {

					if (contas.stream().filter(c -> c.num == Integer.parseInt(s)).findFirst().isPresent()) {

						contas.stream().filter(c -> c.num == Integer.parseInt(s)).findFirst()
								.get().quantidade_ocorrencias += 1;
					} else {

						contas.add(new Conta(Integer.parseInt(s), numLinha));
					}

				}

			}
			
			linha = br.readLine();
			numLinha++;
		}

		br.close();

		return contas;
	}
	
	public static Iterator<Conta> getNumerosMaisRepetidosIndividual(List<Conta> listaConta) {
		
		return listaConta.stream().filter(l -> l.quantidade_ocorrencias > 1).iterator();
		
	}
	
	public static Iterator<Conta> getNumerosNaoRepetidosIndividual(List<Conta> listaConta) {
		
		return listaConta.stream().filter(l -> l.quantidade_ocorrencias == 1).iterator();
		
	}
	

}

class Conta {
	int num;
	int quantidade_ocorrencias;
	List<Integer> linhas = new ArrayList<Integer>();

	@SuppressWarnings("deprecation")
	Conta(int num, int linha) {
		this.num = num;
		quantidade_ocorrencias = 1;
		linhas.add(new Integer(linha));
	}

	public String toString() {
		return num + " ";
	}
}