// Estado Concreto 5: Emergência
public class Emergencia implements EstadoUsina {
    @Override
    public String getNomeEstado() { return "EMERGENCIA"; }

    @Override
    public void aumentoTemperatura(ContextoUsina contexto, double temperatura) {
        System.out.println("!!! ESTADO DE EMERGÊNCIA: Aumento de temperatura ignorado, foco na contenção.");
    }

    @Override
    public void falhaSistema(ContextoUsina contexto, String sistema) {
        System.out.println("!!! ESTADO DE EMERGÊNCIA: Falha adicional em " + sistema + ". Sem transição possível, pior estado atingido.");
    }

    /**
     * Justificativa da Transição: Após uma EMERGENCIA, o único estado seguro
     * é DESLIGADA. Transição unidirecional e crítica.
     */
    @Override
    public void reset(ContextoUsina contexto) {
        System.out.println("!!! Fim da EMERGÊNCIA. Desligamento total acionado. Transicionando para DESLIGADA.");
        contexto.mudarEstado(new Desligada());
    }
    
    @Override
    public void manutencao(ContextoUsina contexto) {
        System.out.println("!!! Manutenção não é permitida ou possível durante uma EMERGÊNCIA.");
    }
}