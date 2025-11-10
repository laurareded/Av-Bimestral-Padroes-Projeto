// Interface (Handler) - Define a cadeia e a operação
public interface Validador {
    void setProximo(Validador proximo);
    boolean validar(DocumentoFiscal documento);
    
    // Método para o Rollback (para validadores que modificam o estado)
    void rollback(DocumentoFiscal documento); 
}