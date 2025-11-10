public class ValidadorRegrasFiscais extends ValidadorBase {
    @Override
    protected boolean executarValidacao(DocumentoFiscal documento) {
        System.out.println("  [3/5] Executando: Validação de Regras Fiscais (Cálculo de Impostos)...");
        
        // Simulação de FALHA CRÍTICA (para o Cenário 2 e 3): Se o UUID começa com 'F'
        if (documento.getUuid().startsWith("F")) {
            documento.setFiscalValido(false);
            return false;
        }
        
        documento.setFiscalValido(true);
        return true;
    }
}