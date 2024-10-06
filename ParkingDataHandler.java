import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingDataHandler {
    private static final String FILE_NAME = "parking_data.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void saveParking(String numAluno, String matricula, int minutes, double amountPaid) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            LocalDateTime now = LocalDateTime.now();
            out.println(now.format(formatter) + "," + numAluno + "," + matricula + "," + minutes + "," + amountPaid);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static Map<String, Double> getTotalPaidByStudent() {
        Map<String, Double> totalPaid = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String numAluno = parts[1];
                double amountPaid = Double.parseDouble(parts[4]);
                totalPaid.put(numAluno, totalPaid.getOrDefault(numAluno, 0.0) + amountPaid);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return totalPaid;
    }
}
