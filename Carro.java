public class Carro {
    // Atributos
    private String numAluno;
    private String matricula;
    private double totalPago;
    private double discountPercentage;

    // Construtor
    public Carro(String numAluno, String matricula) {
        this.numAluno = numAluno;
        this.matricula = matricula;
        this.totalPago = 0.0;
        this.discountPercentage = 0.0;
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

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void adicionarPagamento(double valor) {
        this.totalPago += valor;
    }

    //Verifies the if it is the same object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return numAluno.equals(carro.numAluno) && matricula.equals(carro.matricula);
    }

    /*Creates an hash code to represent the object
     * 31 is used to a better distribution of hash codes as it is a prime odd number
    */
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
