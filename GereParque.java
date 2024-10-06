import java.util.ArrayList;

public class GereParque {
    private ArrayList<Carro>listCarros;

    /**
     * construtor da classe GereParque
     */
    public GereParque(){
        listCarros = new ArrayList<Carro>();
    }

    public double calcularValorPagar(int minutos){
        return (minutos/15) * 0.10;
    }

    public void insertCar (Carro newCar){
        if (listCarros.size() >= 500){
            System.out.println("O Parque está cheio!");
        }
        else{
            listCarros.add(newCar);
            System.out.println("Aluno" + newCar.getNumAluno() + "Entrou no parque");
        }
    }

    public void excludeCar(Carro newCar, int minutos){
        if (listCarros.contains(newCar)){
            double valorPagar = calcularValorPagar(minutos);
            newCar.adicionarPagamento(valorPagar);
            System.out.println("Aluno" + newCar.getNumAluno() + "saiu do parque. Tempo estacionado" + minutos + "minutos. Valor a pagar:" + valorPagar);
            listCarros.remove(newCar);

        }
        else{
            System.out.println("Este aluno não está no parque");
        }
    }

    public void aplicarDesconto(){
        if (listCarros.isEmpty()){
            System.out.println("nenhum aluno estacionou este mês");
        }

        Carro alunoComMaiorPagamento = listCarros.get(0);

        for (Carro c: listCarros){
            if (c.getTotalPago() > alunoComMaiorPagamento.getTotalPago()){
                alunoComMaiorPagamento = c;
            }
        }

        double desconto = alunoComMaiorPagamento.getTotalPago() * 0.90;
        System.out.println("O aluno" + alunoComMaiorPagamento.getNumAluno() + "recebeu um desconto de 10%");

    }
}

