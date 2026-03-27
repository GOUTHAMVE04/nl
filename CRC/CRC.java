import java.util.Scanner;

public class CRC {
    
    // XOR operation (skip first bit)
   static String xorOperation(String a, String b) {
        String result = "";
        for (int i = 1; i < b.length(); i++) {
            if (a.charAt(i) == b.charAt(i))
                result += "0";
            else
                 result += "1";
        }
        return result;
    }
    
    // Perform CRC division
    static String performDivision(String data, String generator) {
        String temp = data.substring(0, 4); // First 4 bits
        
        // Process remaining bits one by one
        for (int i = 4; i < data.length(); i++) {
            if (temp.charAt(0) == '1') {
                temp = xorOperation(generator, temp) + data.charAt(i);
            } else {
                temp = xorOperation("0000", temp) + data.charAt(i);
            }
        }
        
        // Final XOR
        if (temp.charAt(0) == '1') {
            temp = xorOperation(generator, temp);
        } else {
            temp = xorOperation("0000", temp);
        }
        
        return temp;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Fixed 4-bit inputs
        System.out.print("Enter 4-bit Data: ");
        String data = sc.nextLine();
        
        System.out.print("Enter 4-bit Generator: ");
        String generator = sc.nextLine();
        
        // Validate input
        if (data.length() != 4 || generator.length() != 4) {
            System.out.println("Error: Both must be exactly 4 bits!");
            return;
        }
        
        // Append 3 zeros (generator length - 1)
        String appendedData = data + "000";
        
        // Calculate remainder
        String remainder = performDivision(appendedData, generator);
        
        // Create transmitted data
        String transmittedData = data + remainder;
        
        System.out.println("\nAppended Data: " + appendedData);
        System.out.println("CRC Remainder: " + remainder);
        System.out.println("Transmitted Data: " + transmittedData);
        
        sc.close();
    }
}