import java.util.Scanner;

public class HammingCode {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] data = new int[4];
        int[] code = new int[7];

        System.out.println("Enter 4 data bits (0 or 1):");
        for (int i = 0; i < 4; i++) {
            data[i] = sc.nextInt();
        }

        // --- ENCODING ---
        // Place data bits in positions 3, 5, 6, 7 (Indices 2, 4, 5, 6)
        code[2] = data[0];
        code[4] = data[1];
        code[5] = data[2];
        code[6] = data[3];

        // Calculate parity bits (Even Parity)
        code[0] = code[2] ^ code[4] ^ code[6]; // P1
        code[1] = code[2] ^ code[5] ^ code[6]; // P2
        code[3] = code[4] ^ code[5] ^ code[6]; // P4

        System.out.print("Generated Hamming Code: ");
        for (int bit : code) System.out.print(bit + " ");
        System.out.println();

        // --- ERROR SIMULATION / CORRECTION ---
        int[] recv = new int[7];
        System.out.println("\nEnter the received 7-bit code (to test correction):");
        for (int i = 0; i < 7; i++) {
            recv[i] = sc.nextInt();
        }

        // Recalculate parity bits from the received data
        int c1 = recv[0] ^ recv[2] ^ recv[4] ^ recv[6];
        int c2 = recv[1] ^ recv[2] ^ recv[5] ^ recv[6];
        int c4 = recv[3] ^ recv[4] ^ recv[5] ^ recv[6];

        // The syndrome is calculated by combining c4c2c1
        int errorPos = (c4 * 4) + (c2 * 2) + (c1 * 1);

        if (errorPos == 0) {
            System.out.println("No error detected.");
        } else {
            System.out.println("Error detected at position: " + errorPos);
            
            // Correct the bit (flip 0 to 1 or 1 to 0)
            // Array index is errorPos - 1
            recv[errorPos - 1] = (recv[errorPos - 1] == 0) ? 1 : 0;

            System.out.print("Corrected Hamming Code: ");
            for (int bit : recv) System.out.print(bit + " ");
            System.out.println();
        }

        sc.close();
    }
}