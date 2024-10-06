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

    // Método toString para exibir as informações do carro
    @Override
    public String toString() {
        return "Carro{" +
                "numAluno='" + numAluno + '\'' +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
