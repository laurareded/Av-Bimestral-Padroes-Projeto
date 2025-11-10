// O contexto complexo compartilhado entre os algoritmos
public class ContextoFinanceiro {
    private String nomePortifolio;
    private double capitalAlocado;
    private int horizonteDias;

    public ContextoFinanceiro(String nomePortifolio, double capitalAlocado, int horizonteDias) {
        this.nomePortifolio = nomePortifolio;
        this.capitalAlocado = capitalAlocado;
        this.horizonteDias = horizonteDias;
    }

    // Getters para os parâmetros
    public String getNomePortifolio() { return nomePortifolio; }
    public double getCapitalAlocado() { return capitalAlocado; }
    public int getHorizonteDias() { return horizonteDias; }
}