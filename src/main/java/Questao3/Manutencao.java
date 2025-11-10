// Estado Concreto Especial: Manutenção (sobrescreve os estados normais)
public class Manutencao implements EstadoUsina {
    @Override
    public String getNomeEstado() { return "MANUTENCAO"; }

    @Override
    public void aumentoTemperatura(ContextoUsina contexto, double temperatura) {
        System.out.println("-> Em MANUTENÇÃO: Alertas de temperatura ignorados temporariamente.");
        // A lógica de transição normal é desabilitada.
    }

    @Override
    public void falhaSistema(ContextoUsina contexto, String sistema) {
        System.out.println("-> Em MANUTENÇÃO: Falhas em sistema " + sistema + " esperadas. Ignorando.");
    }

    @Override
    public void reset(ContextoUsina contexto) {
        // Retorna ao estado anterior salvo no Contexto.
        System.out.println("-> MANUTENÇÃO concluída. Retornando ao estado anterior.");
        contexto.restaurarEstadoAnterior();
    }
    
    @Override
    public void manutencao(ContextoUsina contexto) {
        System.out.println("-> Já em modo MANUTENÇÃO.");
    }
}