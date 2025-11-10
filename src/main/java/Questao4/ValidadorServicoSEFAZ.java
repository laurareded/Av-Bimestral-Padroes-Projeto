public class ValidadorServicoSEFAZ extends ValidadorBase {
    @Override
    protected boolean executarValidacao(DocumentoFiscal documento) {
        System.out.println("  [5/5] Executando: Validação de Serviço SEFAZ (Consulta Online)...");
        
        // Simulação de TIMEOUT (A lógica está em ValidadorBase, mas simulamos um atraso aqui)
        try {
            if (documento.getUuid().endsWith("0")) {
                Thread.sleep(200); // 200ms, excedendo o timeout de 100ms definido em ValidadorBase
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        return true;
    }
}