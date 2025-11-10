// Handler Abstrato - Implementa a lógica básica da cadeia e o timeout
public abstract class ValidadorBase implements Validador {
    protected Validador proximoValidador;
    private long timeoutMs = 100; // 100 milissegundos

    @Override
    public void setProximo(Validador proximo) {
        this.proximoValidador = proximo;
    }

    // Implementação padrão de rollback (a maioria não modifica o documento)
    @Override
    public void rollback(DocumentoFiscal documento) {
        // Nada a fazer por padrão
    }

    /**
     * Template Method com Circuit Breaker e lógica de encadeamento.
     */
    @Override
    public boolean validar(DocumentoFiscal documento) {
        long startTime = System.currentTimeMillis();
        boolean resultadoLocal = false;
        
        try {
            resultadoLocal = executarValidacao(documento);
        } catch (Exception e) {
            System.err.println("!!! Erro durante a validação: " + getClass().getSimpleName());
            resultadoLocal = false;
        }

        long endTime = System.currentTimeMillis();
        
        // Timeout Individual
        if ((endTime - startTime) > timeoutMs) {
            System.out.println("!!! Timeout: " + getClass().getSimpleName() + " excedeu " + timeoutMs + "ms. Falha!");
            return false;
        }

        // Se falhar localmente
        if (!resultadoLocal) {
            System.out.println("-> FALHA na validação: " + getClass().getSimpleName());
            return false;
        }

        // Se passar e houver próximo, passa a responsabilidade
        if (proximoValidador != null) {
            return proximoValidador.validar(documento);
        }

        return true; // Fim da cadeia
    }

    protected abstract boolean executarValidacao(DocumentoFiscal documento);
}