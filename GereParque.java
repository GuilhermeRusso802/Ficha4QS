import java.util.*;

public class GereParque {
    private static final int MAX_CAPACITY = 500;
    private static final double RATE_PER_15_MINUTES = 0.10;
    private static final double DISCOUNT_PERCENTAGE = 0.10;

    private ArrayList<Carro> listCarros;
    private Map<String, Carro> allCarros;

    public GereParque() {
        listCarros = new ArrayList<>();
        allCarros = new HashMap<>();
    }

    public double calcularValorPagar(int minutos, Carro carro) {
        double valorBase = (minutos / 15.0) * RATE_PER_15_MINUTES;
        double valorComDesconto = valorBase * (1 - carro.getDiscountPercentage());
        System.out.printf("Calculando valor a pagar para %s: Valor base: %.2f, Desconto: %.2f%%, Valor final: %.2f%n",
                          carro.getNumAluno(), valorBase, carro.getDiscountPercentage() * 100, valorComDesconto);
        return valorComDesconto;
    }

    public void insertCar(Carro newCar) {
        if (listCarros.size() >= MAX_CAPACITY) {
            System.out.println("O Parque está cheio!");
        } else {
            listCarros.add(newCar);
            Carro existingCar = allCarros.get(newCar.getNumAluno());
            if (existingCar != null) {
                // Preserve the discount if the car already exists
                newCar.setDiscountPercentage(existingCar.getDiscountPercentage());
            }
            allCarros.put(newCar.getNumAluno(), newCar);
            System.out.printf("Aluno %s entrou no parque com o carro de matrícula %s. Desconto atual: %.2f%%%n", 
                              newCar.getNumAluno(), newCar.getMatricula(), newCar.getDiscountPercentage() * 100);
        }
    }

    public void excludeCar(Carro car, int minutos) {
        int index = listCarros.indexOf(car);
        if (index != -1) {
            Carro removedCar = listCarros.remove(index);
            double valorPagar = calcularValorPagar(minutos, removedCar);
            removedCar.adicionarPagamento(valorPagar);
            ParkingDataHandler.saveParking(removedCar.getNumAluno(), removedCar.getMatricula(), minutos, valorPagar);
            System.out.printf("Aluno %s saiu do parque. Tempo estacionado: %d minutos. Valor a pagar: %.2f%n", 
                              removedCar.getNumAluno(), minutos, valorPagar);
            
            if (removedCar.getDiscountPercentage() > 0) {
                System.out.printf("Desconto de %.0f%% aplicado.%n", removedCar.getDiscountPercentage() * 100);
            }
        } else {
            System.out.println("Este carro não está no parque");
        }
    }

    public void aplicarDesconto() {
        Map<String, Double> totalPaidByStudent = ParkingDataHandler.getTotalPaidByStudent();
        
        if (totalPaidByStudent.isEmpty()) {
            System.out.println("Nenhum aluno estacionou este mês");
            return;
        }

        String alunoComMaiorPagamento = Collections.max(totalPaidByStudent.entrySet(), Map.Entry.comparingByValue()).getKey();
        double totalPago = totalPaidByStudent.get(alunoComMaiorPagamento);

        // Reset discount for all students
        for (Carro carro : allCarros.values()) {
            carro.setDiscountPercentage(0.0);
            System.out.printf("Resetando desconto para aluno %s%n", carro.getNumAluno());
        }

        // Apply discount to the student with highest payment
        Carro carroComDesconto = allCarros.get(alunoComMaiorPagamento);
        if (carroComDesconto != null) {
            carroComDesconto.setDiscountPercentage(DISCOUNT_PERCENTAGE);
            System.out.printf("O aluno %s recebeu um desconto de 10%% para o próximo mês. Total pago este mês: %.2f%n", 
                              alunoComMaiorPagamento, totalPago);
        } else {
            carroComDesconto = new Carro(alunoComMaiorPagamento, "");
            carroComDesconto.setDiscountPercentage(DISCOUNT_PERCENTAGE);
            allCarros.put(alunoComMaiorPagamento, carroComDesconto);
            System.out.printf("Criado novo registro para aluno %s com desconto de 10%%%n", alunoComMaiorPagamento);
        }
    }

    // Method to check current discounts (for debugging)
    public void printCurrentDiscounts() {
        System.out.println("Current discounts:");
        for (Carro carro : allCarros.values()) {
            System.out.printf("Aluno %s: %.2f%%%n", carro.getNumAluno(), carro.getDiscountPercentage() * 100);
        }
    }
}