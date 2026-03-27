import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter bucket size, outgoing rate, number of inputs, and incoming size: ");
        int bucketSize = sc.nextInt();
        int outgoing = sc.nextInt();
        int n = sc.nextInt();
        int incoming = sc.nextInt();
        int store = 0;

        while (n != 0) {
            System.out.println("\nIncoming size is " + incoming);
            if (incoming <= (bucketSize - store)) {
                store += incoming;
                System.out.println("Bucket buffer size is " + store + " out of " + bucketSize);
            } else {
                System.out.println("Packet loss = " + (incoming - (bucketSize - store)));
                store = bucketSize;
                System.out.println("Buffer is full");
            }
            store = Math.max(0, store - outgoing);
            System.out.println("After outgoing, " + store + " packets left out of " + bucketSize);
            n--;
            Thread.sleep(1000);
        }
    }
}