public class ValidadorSchemaXML extends ValidadorBase {
    @Override
    protected boolean executarValidacao(DocumentoFiscal documento) {
        System.out.println("  [1/5] Executando: Validação de Schema XML...");
        // Deve sempre passar para prosseguir na cadeia (validação básica)
        documento.setSchemaValido(true);
        return true; 
    }
}