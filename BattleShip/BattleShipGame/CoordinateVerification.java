import java.util.Scanner;
import java.util.Random;

public class CoordinateVerification {
    /**
     * This function verifies that the X coordinate is in bounds of the Ocean Map
     * @param y
     * @return y
     */

    public static int verifyXCoordinate(int y){
        Scanner scan = new Scanner(System.in);
        while (y < 0 || y > 10) {
            System.out.print("Please select a Vertical coordinate between 0-10: ");
            y = scan.nextInt();
        }
        return y;
    }


    /**
     * This function verifies that the Y coordinate is in bounds of the Ocean Map
     * @param x
     * @return x
     */
    public static int verifyYCoordinate(int x){
        Scanner scan = new Scanner(System.in);
        while (x < 0 || x > 10) {
            System.out.print("Please select a Horizontal coordinate between 0-10: ");
            x = scan.nextInt();
        }
        return x;
    }



    public static int placeXOccupied(int y){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a new Vertical coordinate for your ship the place is occupied: ");
        y = scan.nextInt();
        int newYOk = verifyXCoordinate(y);
        return newYOk;
    }

    public static int computerPlaceXOccupied(int y){
        Random rand = new Random();
        return y = rand.nextInt(10);
    }

    public static int placeYOccupied(int x){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a new Horizontal coordinate for your ship the place is occupied: ");
        x = scan.nextInt();
        int newXOk = verifyYCoordinate(x);
        return newXOk;
    }

    public static int computerPlaceYOccupied(int x){
        Random rand = new Random();
        return x = rand.nextInt();
    }
}
