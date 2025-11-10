// Estratégia Concreta 1: Value at Risk (VaR)
public class VaRStrategy implements EstrategiaCalculoRisco {
    /**
     * Justificativa do Padrão: Implementação concreta da Strategy.
     * Encapsula o algoritmo de VaR, permitindo que seja trocado em tempo de execução.
     * Usa o ContextoFinanceiro para simular o cálculo.
     */
    @Override
    public String calcularRisco(ContextoFinanceiro contexto) {
        // Lógica dummy: simulação de VaR a 99%
        double resultadoDummy = contexto.getCapitalAlocado() * 0.035; 
        return "[VA R - 99%] Risco calculado para " + contexto.getNomePortifolio() + 
               " é de R$ " + String.format("%.2f", resultadoDummy) + " (perda máxima esperada)";
    }
}