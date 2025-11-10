// Contexto que monta a cadeia, executa e gerencia o Circuit Breaker/Rollback
public class CadeiaValidacao {
    private Validador primeiroValidador;
    private int maxFalhas = 3;

    /**
     * Justificativa do Padrão: Esta classe constrói a cadeia (Chain of Responsibility).
     * O método executar() implementa o mecanismo de Circuit Breaker e gerencia o Rollback
     * da cadeia em caso de falha.
     */
    public CadeiaValidacao() {
        // 1. Montagem da Cadeia na ordem correta
        Validador validador1 = new ValidadorSchemaXML(); 
        Validador validador2 = new ValidadorCertificado(); 
        Validador validador3 = new ValidadorRegrasFiscais(); 
        Validador validador4 = new ValidadorDuplicidadeDB(); 
        Validador validador5 = new ValidadorServicoSEFAZ(); 

        // Configuração da cadeia e validações condicionais
        validador1.setProximo(validador2);
        
        // Validador 3 e 5 executados apenas se anteriores passarem: 
        // Isso é intrínseco ao CoR, desde que a execução passe adiante.
        validador2.setProximo(validador4); // Vai para o DB
        validador4.setProximo(validador3); // Depois checa regras fiscais
        validador3.setProximo(validador5); // Por fim, consulta SEFAZ

        this.primeiroValidador = validador1;
    }

    public boolean executar(DocumentoFiscal documento) {
        System.out.println("\n--- INÍCIO DA VALIDAÇÃO DA CADEIA ---");
        int falhas = 0;
        Validador current = primeiroValidador;
        boolean sucessoGeral = true;
        
        // Iteração manual para implementar o Circuit Breaker
        Validador validadorRollback = null; // Guarda a referência do validador que precisa de rollback

        while (current != null) {
            // Guarda a referência se for o validador que faz a modificação (Validador 4)
            if (current instanceof ValidadorDuplicidadeDB) {
                validadorRollback = current;
            }

            // A lógica de validação do ValidadorBase já faz o trabalho real,
            // aqui apenas controlamos o circuit breaker e o rollback.

            boolean passou = current.validar(documento);

            if (!passou) {
                falhas++;
                sucessoGeral = false;
                
                // CIRCUIT BREAKER: Interrompe após 3 falhas
                if (falhas >= maxFalhas) {
                    System.out.println("\n!!! CIRCUIT BREAKER ATIVADO: 3 ou mais falhas. Interrompendo cadeia.");
                    break;
                }
            }
            
            // Move para o próximo *manualmente* para controle do Circuit Breaker
            current = ((ValidadorBase) current).proximoValidador;
        }

        // Mecanismo de ROLLBACK
        if (!sucessoGeral && validadorRollback != null) {
            System.out.println("\n--- Acionando ROLLBACK ---");
            validadorRollback.rollback(documento);
        }

        return sucessoGeral;
    }
}

