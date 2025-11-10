// O objeto que será passado pela cadeia
public class DocumentoFiscal {
    private String xmlData;
    private boolean schemaValido;
    private boolean certificadoValido;
    private boolean fiscalValido;
    private boolean duplicidadeChecada;
    private boolean sefazChecada;
    private String uuid; // Para simular o controle de duplicidade

    public DocumentoFiscal(String xmlData) {
        this.xmlData = xmlData;
        
        if (xmlData.contains("-")) {
             this.uuid = xmlData;
        } else {
             this.uuid = java.util.UUID.randomUUID().toString();
        }
    }

    // Getters e Setters para o estado de validação
    public boolean isSchemaValido() { return schemaValido; }
    public void setSchemaValido(boolean schemaValido) { this.schemaValido = schemaValido; }

    public boolean isCertificadoValido() { return certificadoValido; }
    public void setCertificadoValido(boolean certificadoValido) { this.certificadoValido = certificadoValido; }

    public void setFiscalValido(boolean fiscalValido) { this.fiscalValido = fiscalValido; }
    
    public String getUuid() { return uuid; }
    public void setDuplicidadeChecada(boolean duplicidadeChecada) { this.duplicidadeChecada = duplicidadeChecada; }

    // Simulação de modificação do documento (para o rollback)
    public void logInsercaoDB() {
        System.out.println("   [DB] Documento temporariamente inserido no banco. ID: " + uuid);
    }
    
    public void logRollbackDB() {
        System.out.println("   [DB] ROLLBACK: Inserção do documento ID " + uuid + " desfeita.");
    }
}