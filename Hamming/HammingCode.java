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

        // Place data bits in positions 3,5,6,7
        code[2] = data[0];
        code[4] = data[1];
        code[5] = data[2];
        code[6] = data[3];

        // Calculate parity bits (even parity)

        // P1 checks bits 1,3,5,7
        code[0] = code[2] ^ code[4] ^ code[6];

        // P2 checks bits 2,3,6,7
        code[1] = code[2] ^ code[5] ^ code[6];

        // P4 checks bits 4,5,6,7
        code[3] = code[4] ^ code[5] ^ code[6];

        System.out.print("Generated Hamming Code: ");

        for (int bit : code) {
            System.out.print(bit + " ");
        }

        sc.close();
    }
}