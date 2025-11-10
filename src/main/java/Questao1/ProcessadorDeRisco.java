// Contexto - Mantém a referência para a Strategy e permite a troca
public class ProcessadorDeRisco {
    private EstrategiaCalculoRisco estrategia;

    /**
     * Justificativa do Padrão: Esta classe é o Contexto do padrão Strategy.
     * Ela depende apenas da interface EstrategiaCalculoRisco (Princípio DIP),
     * permitindo que qualquer algoritmo concreto seja injetado e usado.
     */
    public ProcessadorDeRisco(EstrategiaCalculoRisco estrategiaInicial) {
        this.estrategia = estrategiaInicial;
    }

    // Permite a troca de algoritmo em tempo de execução
    public void setEstrategia(EstrategiaCalculoRisco novaEstrategia) {
        System.out.println("-> Algoritmo de risco alterado para: " + novaEstrategia.getClass().getSimpleName());
        this.estrategia = novaEstrategia;
    }

    public String executarCalculo(ContextoFinanceiro contexto) {
        return this.estrategia.calcularRisco(contexto);
    }
}