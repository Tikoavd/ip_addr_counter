import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String... args) {
        int[] complexRepeatCounts = new int[8 * 256 * 256 * 256];
        long uniquesCount = 0;

        System.out.print("Please enter file path: ");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            long startMillis = System.currentTimeMillis();
            System.out.println("Please wait, calculating...");
            String line;
            while ((line = br.readLine()) != null) {
                int[] parts = parseIPV4Address(line);

                //convert array of numbers from IPV4 to integer
                int x = parts[0] % 8;
                for (int index = 1; index < 4; index++) {
                    x <<= 8;
                    x = x | parts[index];
                }
                int bitIndex = parts[0] / 8;

                if ((complexRepeatCounts[x] & (1 << bitIndex)) != (1 << bitIndex)) {
                    uniquesCount++;
                    complexRepeatCounts[x] = (complexRepeatCounts[x] | (1 << bitIndex));
                }
            }
            System.out.println("Unique IP addresses count: " + uniquesCount);
            long duration = System.currentTimeMillis() - startMillis;
            long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - minutes * 60;
            System.out.println("Duration: " + minutes + " minutes " + seconds + " seconds.");
        } catch (Exception ignored) {
            System.out.println("Something went wrong(");
        }
    }

    public static int[] parseIPV4Address(String line) {
        int[] arr = new int[4];
        int lineIndex = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '.') {
                lineIndex++;
            } else if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                arr[lineIndex] = arr[lineIndex] * 10 + (line.charAt(i) - '0');
            }
        }
        return arr;
    }
}

