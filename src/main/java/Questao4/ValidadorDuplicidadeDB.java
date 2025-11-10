// Handler Concreto 4: Checa duplicidade e simula inserção (necessita Rollback)
public class ValidadorDuplicidadeDB extends ValidadorBase {
    
    @Override
    protected boolean executarValidacao(DocumentoFiscal documento) {
        System.out.println("[DB] Validando duplicidade e pré-inserindo...");
        
        // Simulação de Duplicidade (para o Cenário 3): se o início do UUID for PAR, é duplicidade.
        // A substring(0, 8) deve ser um hexadecimal válido.
        try {
            if (Integer.parseInt(documento.getUuid().substring(0, 8).replaceAll("[^0-9a-fA-F]", "0"), 16) % 2 == 0) {
                 return false; // Falha na Duplicidade
            }
        } catch (NumberFormatException e) {
             // Tratamento para UUIDs de teste que não são hexadecimais puros, assume par/falha.
             if (documento.getUuid().startsWith("F")) return true; // Se for falha fiscal, assume que passa aqui
             return false;
        }
        
        // Simulação de inserção que PRECISA de rollback se as regras fiscais falharem
        documento.logInsercaoDB();
        documento.setDuplicidadeChecada(true);
        return true;
    }

    /**
     * Restrição: O validador 4 deve fazer rollback se validações subsequentes falharem.
     */
    @Override
    public void rollback(DocumentoFiscal documento) {
        // Ação de compensação
        documento.logRollbackDB();
    }
}