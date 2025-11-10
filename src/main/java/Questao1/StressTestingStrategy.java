// Estratégia Concreta 3: Stress Testing
public class StressTestingStrategy implements EstrategiaCalculoRisco {
    @Override
    public String calcularRisco(ContextoFinanceiro contexto) {
        // Lógica dummy: Stress Testing (simulação de crise)
        double resultadoDummy = contexto.getCapitalAlocado() * 0.10; 
        return "[Stress Testing] Perda sob cenário de crise simulada (" + contexto.getHorizonteDias() + 
               " dias) é R$ " + String.format("%.2f", resultadoDummy);
    }
}