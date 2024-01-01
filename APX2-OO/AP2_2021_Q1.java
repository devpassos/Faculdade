import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AP2_2021_Q1 {
	
	public static void main(String[] args) {
	
	Solicitacao sangue = new Exame("Exame de Sangue", 20);
	
	Solicitacao biopsia = new Cirurgia("Biopsia", 30);
	
	Tratamento analiseAlergia = new Tratamento();
	
	analiseAlergia.adicionaSolicitacao(Arrays.asList(sangue, biopsia));
	
	System.out.println(analiseAlergia);
	
	Solicitacao anestesia = new Medicamento("Anestesia", 1000);
	
	Solicitacao septo = new Cirurgia("Correção de Septo", 120);
	
	Tratamento correcaoSepto = new Tratamento();
	
	correcaoSepto.adicionaSolicitacao(anestesia);
	
	correcaoSepto.adicionaSolicitacao(septo);
	
	System.out.println(correcaoSepto);
	
	Solicitacao cisto = new Cirurgia("Extração de Cisto", 60);
	
	Tratamento extracaoCisto = new Tratamento();
	
	extracaoCisto.adicionaSolicitacao(Arrays.asList(analiseAlergia, anestesia, cisto));
	
	System.out.println(extracaoCisto);
	
	
	}
}
/**
 * Interface para a disponibilização do método custo
 * @author Passos
 *
 */
interface Solicitacao {

	public double custo();

}

/**
 * Classe mãe dos procedimentos. fornece os métodos padrões e atributos.
 * @author Passos
 *
 */
class Procedimento {
	
	private String nome;
	private int tempoMedioEmMinutos;
	private double valor;
	
	Procedimento(String nome, int tempoMinutos){
		
		this.nome = nome;
		this.tempoMedioEmMinutos = tempoMinutos;
	}
	
	Procedimento(String nome, double valor){
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTempo() {
		return this.tempoMedioEmMinutos;
	}
	
	public void setValor(double valor) {
		
		this.valor = valor;
	}
	
	public double getValor() {
		
		return this.valor;
	}
	
	@Override
	public String toString() {
		
		return this.nome;
	}
	
}


class Exame extends Procedimento implements Solicitacao {
	
	public Exame(String nome, int tempoMinutos) {
		super(nome, tempoMinutos);
		super.setValor(20.00);
	}
	
	@Override
	public double custo() {
		
		return this.getTempo() * this.getValor();
	}
	
	@Override
	public String toString() {
		
		return super.toString() + ", " + this.custo();
	}
}

class Cirurgia extends Procedimento implements Solicitacao {
	
	public Cirurgia(String nome, int tempoMinutos) {
		super(nome, tempoMinutos);
		super.setValor(1000.00);
	}
	
	@Override
	public double custo() {
		
		return this.getTempo() * this.getValor();
	}
	
	@Override
	public String toString() {
		
		return super.toString() + ", " + this.custo();
	}
}

class Medicamento extends Procedimento implements Solicitacao {
	
	Medicamento(String principioAtivo, double custo){
		super(principioAtivo, custo);
	}
	
	@Override
	public double custo() {
		
		return this.getValor();
	}
	
	@Override
	public String toString() {
		
		return super.toString() + ", " + this.custo();
	}

}

/**
 * guarda cada procedimento feito agrupados por um ID
 * @author Passos
 *
 */
class Tratamento implements Solicitacao {
	
	static int ultimoId = 0;
	private int id;
	private List<Solicitacao> solicitacoes;
	
	
	
	public Tratamento() {
		this.solicitacoes = new ArrayList<>();
		this.id = Tratamento.ultimoId++;
	}
	
	public void adicionaSolicitacao(List<Solicitacao> solicitacoes) {
		
		solicitacoes.forEach(s -> this.solicitacoes.add(s));
		
	}
	
	public void adicionaSolicitacao(Solicitacao solicitacao) {
		
		this.solicitacoes.add(solicitacao);
	}

	@Override
	public double custo() {
		
		double custoTotal = 0;
		
		for (Solicitacao s : this.solicitacoes) {
			custoTotal += s.custo();
		}
		
		return custoTotal;
	}

	@Override
	public String toString() {
		
		String solicitacoesTemp = "";
		this.solicitacoes.sort(Comparator.comparingDouble(Solicitacao::custo).reversed());
		
		for (Solicitacao s : this.solicitacoes) {
			solicitacoesTemp += s.toString() + "\n";
		}
		 
		return "Tratamento " + this.id + " {\n" +
				 solicitacoesTemp +
				"}";
	}
}
