public class ValidadorCertificado extends ValidadorBase {
    @Override
    protected boolean executarValidacao(DocumentoFiscal documento) {
        System.out.println("  [2/5] Executando: Validação de Certificado Digital (Expiração/Revogação)...");
        
        // Simulação de FALHA (para o Cenário 3): se o UUID termina com '1'
        if (documento.getUuid().endsWith("1")) {
             documento.setCertificadoValido(false);
             return false;
        }

        documento.setCertificadoValido(true);
        return true; 
    }
}