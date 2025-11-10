// Estado Concreto 0: Desligada
public class Desligada implements EstadoUsina {
    @Override
    public String getNomeEstado() { return "DESLIGADA"; }

    @Override
    public void aumentoTemperatura(ContextoUsina contexto, double temperatura) {
        System.out.println("-> Usina DESLIGADA. Nenhuma ação necessária para aumento de temperatura.");
    }

    @Override
    public void falhaSistema(ContextoUsina contexto, String sistema) {
        System.out.println("-> Usina DESLIGADA. Falha no sistema " + sistema + " ignorada.");
    }

    /**
     * Justificativa da Transição: Para ligar a usina, é necessário ir para o estado inicial de operação.
     * Transição controlada.
     */
    @Override
    public void reset(ContextoUsina contexto) {
        System.out.println("-> Acionando o sistema de partida. Transicionando para OPERACAO NORMAL.");
        contexto.mudarEstado(new OperacaoNormal());
    }

    @Override
    public void manutencao(ContextoUsina contexto) {
        System.out.println("-> A usina já está no estado mais seguro para MANUTENÇÃO. Iniciando Manutenção.");
        contexto.salvarEstadoAnterior(this); // Salva o estado DESLIGADA
        contexto.mudarEstado(new Manutencao());
    }
}