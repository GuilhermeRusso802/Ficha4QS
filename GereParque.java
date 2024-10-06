import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GereParque {
    private static final int MAX_CAPACITY = 500;
    private static final double RATE_PER_15_MINUTES = 0.10;
    private static final double DISCOUNT_PERCENTAGE = 0.10;

    private ArrayList<Carro> listCarros;

    public GereParque() {
        listCarros = new ArrayList<>();
    }

    public double calcularValorPagar(int minutos) {
        return (minutos / 15) * RATE_PER_15_MINUTES;
    }

    public void insertCar(Carro newCar) {
        if (listCarros.size() >= MAX_CAPACITY) {
            System.out.println("O Parque está cheio!");
        } else {
            listCarros.add(newCar);
            System.out.printf("Aluno %s entrou no parque com o carro de matrícula %s%n", 
                              newCar.getNumAluno(), newCar.getMatricula());
        }
    }

    public void excludeCar(Carro car, int minutos) {
        int index = listCarros.indexOf(car);
        if (index != -1) {
            Carro removedCar = listCarros.remove(index);
            double valorPagar = calcularValorPagar(minutos);
            removedCar.adicionarPagamento(valorPagar);
            System.out.printf("Aluno %s saiu do parque. Tempo estacionado: %d minutos. Valor a pagar: %.2f%n", 
                              removedCar.getNumAluno(), minutos, valorPagar);
        } else {
            System.out.println("Este carro não está no parque");
        }
    }

    public void aplicarDesconto() {
        if (listCarros.isEmpty()) {
            System.out.println("Nenhum aluno estacionou este mês");
            return;
        }

        Carro alunoComMaiorPagamento = Collections.max(listCarros, Comparator.comparingDouble(Carro::getTotalPago));
        double totalPago = alunoComMaiorPagamento.getTotalPago();
        double desconto = totalPago * DISCOUNT_PERCENTAGE;
        double novoTotal = totalPago - desconto;

        alunoComMaiorPagamento.adicionarPagamento(-desconto);

        System.out.printf("O aluno %s recebeu um desconto de 10%% (%.2f). Novo total: %.2f%n", 
                          alunoComMaiorPagamento.getNumAluno(), desconto, novoTotal);
    }
}