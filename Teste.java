import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        GereParque gestor = new GereParque();
        Scanner input = new Scanner(System.in);
        
        int op;
        do {
            System.out.println("\n1. Entrada no parque");
            System.out.println("2. Saida do parque");
            System.out.println("3. Finalizar mês");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            while (!input.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                input.next(); // Consume invalid input
            }
            op = input.nextInt();
            input.nextLine(); // Consume newline

            switch (op) {
                case 1:
                    entradaNoParque(gestor, input);
                    break;
                case 2:
                    saidaDoParque(gestor, input);
                    break;
                case 3:
                    gestor.aplicarDesconto();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (op != 4);

        input.close();
    }

    private static void entradaNoParque(GereParque gestor, Scanner input) {
        System.out.print("Qual o numero de aluno? ");
        String numAluno = input.nextLine();
        System.out.print("Qual a matricula do carro? ");
        String matricula = input.nextLine();
        Carro newCar = new Carro(numAluno, matricula);
        gestor.insertCar(newCar);
    }

    private static void saidaDoParque(GereParque gestor, Scanner input) {
        System.out.print("Qual o numero do aluno? ");
        String numAluno = input.nextLine();
        System.out.print("Qual a matricula do carro? ");
        String matricula = input.nextLine();
        System.out.print("Qual foi o tempo de estacionamento (em minutos)? ");
        while (!input.hasNextInt()) {
            System.out.println("Por favor, insira um número válido para os minutos.");
            input.next(); // Consume invalid input
        }
        int minutos = input.nextInt();
        input.nextLine(); // Consume newline
        Carro carToRemove = new Carro(numAluno, matricula);
        gestor.excludeCar(carToRemove, minutos);
    }
}