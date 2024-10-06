public class Carro {
    // Atributos
    private String numAluno;
    private String matricula;
    private double totalPago;

    // Construtor
    public Carro(String numAluno, String matricula) {
        this.numAluno = numAluno;
        this.matricula = matricula;
        this.totalPago = 0.0;
    }

    // Getters e Setters
    public String getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(String numAluno) {
        this.numAluno = numAluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getTotalPago(){
        return totalPago;
    }

    public void adicionarPagamento(double valor) {
        this.totalPago += valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return numAluno.equals(carro.numAluno) && matricula.equals(carro.matricula);
    }

    @Override
    public int hashCode() {
        return 31 * numAluno.hashCode() + matricula.hashCode();
    }

    // Método toString para exibir as informações do carro
    @Override
    public String toString() {
        return "Carro{" +
                "numAluno='" + numAluno + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
