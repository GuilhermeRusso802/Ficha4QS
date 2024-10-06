import java.util.Scanner;

public class Teste {
    public static void main(String[]args){
        GereParque gestor = new GereParque();
        Scanner input = new Scanner (System.in);


        byte op;

        System.out.println("1. Entrada no parque");
        System.out.println("2. Saida do parque");
        System.out.println("3. Finalizar mês");
        System.out.println("4. Sair");


        op = input.nextByte();

        while(op != 0){
            switch (op) {
                case 1: {
                    Carro newCar=null;
                    System.out.println("Qual o numero de aluno?");
                    String numAluno = input.nextLine();
                    System.out.println("Qual a matricula do carro?");
                    String matricula = input.nextLine();
                    newCar = new Carro(numAluno, matricula);
                    gestor.insertCar(newCar);
                    break;
                }
                case 2: {
                    Carro newCar=null;
                    System.out.println("Qual o numero do aluno?");
                    String numAluno = input.nextLine();
                    System.out.println("Qual a matricula do carro?");
                    String matricula = input.nextLine();
                    System.out.println("Qual foi o tempo de estacionamento?");
                    int minutos = input.nextInt();

                    newCar = new Carro(numAluno, matricula);
                    gestor.excludeCar(newCar, minutos);
                    break;
                }
                case 3: {
                    gestor.aplicarDesconto();
                    break;
                }
                case 4:{
                    System.out.println("saindo...");
                    break;
                }
            }
        }
    }
}
