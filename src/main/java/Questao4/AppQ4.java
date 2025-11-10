public class AppQ4 {
    public static void main(String[] args) {
        CadeiaValidacao cadeia = new CadeiaValidacao();

        // --- Teste 1: SUCESSO TOTAL ---
        // UUID seguro (Não começa com F, não termina com 0 ou 1, e é ímpar na simulação DB)
        System.out.println("=============================================");
        System.out.println("CENÁRIO 1: SUCESSO COMPLETO");
        DocumentoFiscal docSucesso = new DocumentoFiscal("S1234567-0000-0000-0000-000000000002"); // Ímpar/Passa
        System.out.println("UUID: " + docSucesso.getUuid());
        cadeia.executar(docSucesso);
        System.out.println("Resultado Final: " + (cadeia.executar(docSucesso) ? "APROVADO" : "REPROVADO"));


        // --- Teste 2: FALHA FISCAL PÓS-INSERÇÃO (Aciona Rollback do Validador 4) ---
        // Requisito: Falha no Validador 3 (Fiscal) força o Rollback.
        System.out.println("\n=============================================");
        System.out.println("CENÁRIO 2: ROLLBACK - Falha Regra Fiscal (Validador 3)");
        // UUID que começa com 'F' (ativa a falha fiscal)
        DocumentoFiscal docRollback = new DocumentoFiscal("F1234567-0000-0000-0000-000000000003"); // Força Falha Fiscal, Passa no DB
        System.out.println("UUID: " + docRollback.getUuid());
        cadeia.executar(docRollback);
        System.out.println("Resultado Final: " + (cadeia.executar(docRollback) ? "APROVADO" : "REPROVADO"));

        
        // --- Teste 3: CIRCUIT BREAKER (3 Falhas) ---
        // Requisito: Três falhas (Certificado, Duplicidade, Fiscal) devem interromper a cadeia.
        System.out.println("\n=============================================");
        System.out.println("CENÁRIO 3: CIRCUIT BREAKER - 3 Falhas Consecutivas");
        // UUID projetado para falhar em TUDO:
        // Termina com '1' (Certificado), é PAR (Duplicidade DB), e começa com 'F' (Fiscal)
        DocumentoFiscal docCircuit = new DocumentoFiscal("F1234560-0000-0000-0000-000000000001");
        System.out.println("UUID: " + docCircuit.getUuid());
        cadeia.executar(docCircuit);
        System.out.println("Resultado Final: " + (cadeia.executar(docCircuit) ? "APROVADO" : "REPROVADO"));
    }
}