// Interface (State) - Define as ações que podem causar transições
public interface EstadoUsina {
    String getNomeEstado();
    void aumentoTemperatura(ContextoUsina contexto, double temperatura);
    void falhaSistema(ContextoUsina contexto, String sistema);
    void reset(ContextoUsina contexto);
    void manutencao(ContextoUsina contexto); // Regra Especial
}