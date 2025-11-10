// Interface (Strategy) - Define o contrato para todos os algoritmos de risco
public interface EstrategiaCalculoRisco {
    String calcularRisco(ContextoFinanceiro contexto);
}