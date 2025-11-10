// Estratégia Concreta 2: Expected Shortfall (ES)
public class ExpectedShortfallStrategy implements EstrategiaCalculoRisco {
    @Override
    public String calcularRisco(ContextoFinanceiro contexto) {
        // Lógica dummy: ES (maior que VaR)
        double resultadoDummy = contexto.getCapitalAlocado() * 0.05; 
        return "[Expected Shortfall] Média de perdas nas piores situações para " + 
               contexto.getNomePortifolio() + " é R$ " + String.format("%.2f", resultadoDummy);
    }
}