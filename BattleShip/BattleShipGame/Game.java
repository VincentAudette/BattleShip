import java.util.Scanner;
import java.util.Random;

public class Game {

    public static void main(String[] args) {


/**
 *
 * The ocean map is represented by a 10 x 10 grid of different strings.
 * This 2D array will be used to save where the user decides to place their
 * ships and where the computer computer randomly places them.
 * The array also keeps track when someone tries to attack a location
 * and misses. At the start of the game the array will be empty and as the
 * game is played each index of the array will be changed according to the
 * game's outcome.
 *
 *      Ocean map preview:
 *          0123456789
 *       0 |          | 0
 *       1 |          | 1
 *       2 |          | 2
 *       3 |          | 3
 *       4 |          | 4
 *       5 |          | 5
 *       6 |          | 6
 *       7 |          | 7
 *       8 |          | 8
 *       9 |          | 9
 *          0123456789
 */

        System.out.println();
        System.out.println("   Welcome to BattleShip \n " +
                "The sea is currently empty");
        System.out.println();

        String[][] user = new String[10][10];
        String[][] computer = new String[10][10];

        oceanMap(user);

        //User enters where to deploy ships
        System.out.println("\nChose where you wish to deploy your ships:");
        userChoosesShipLocations(user);
        oceanMap(user); //The user's map with ship location is printed

        //Computer deploys ships in random locations
        System.out.println("\nComputer is deploying ships");
        computerDeployment(computer);

        //User attacks computer:
        int userPoints = 0;
        int computerPoints = 0;
        while (userPoints<5 && computerPoints<5) {
            userPoints = userAttack(computer, userPoints);
            computerPoints = computerAttack(user, computerPoints);
            oceanMap(user);

            System.out.println("\nUSER:"+ userPoints + "  COMPUTER:" +computerPoints + "\n");
        }
        if(userPoints == 5){
            System.out.println("WINNER!!!");
        }else if(computerPoints == 5){
            System.out.println("GAME OVER");
        }


    }
    //Ocean map layout function:

    public static void oceanMap(String[][] stringArray) {


        System.out.println("      0123456789");

        for (int row = 0; row < stringArray.length; row++)
            for (int col = 0; col < stringArray[row].length; col++) {
                //Printing extremities of the map
                if (col == 0) {
                    System.out.print("   " + row + " |");
                }
                //Printing values on the map
                if (stringArray[row][col] == null) {
                    System.out.print(" ");
                } else System.out.print(stringArray[row][col]);

                //Printing extremities of the map
                if (col == 9) {
                    System.out.println("| " + row);
                }

            }
        System.out.println("      0123456789");
    }


    //User functions:

    public static int enterXCoordinate(int x) {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter Vertical coordinate for the ship: ");
        return x = scan.nextInt();
    }


    public static int chooseNewXCoord(int x) {
        return x = CoordinateVerification.verifyXCoordinate(x);
    }

    public static int enterYCoordinate(int y) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Horizontal coordinate for the ship: ");
        return y = scan.nextInt();
    }

    public static int chooseNewYCoord(int y) {
        return y = CoordinateVerification.verifyYCoordinate(y);
    }


    public static int addXCoordinate(int x) {
        x = enterXCoordinate(x);
        if (x < 0 || x > 10) {
            x = chooseNewXCoord(x);
        }
        return x;
    }

    public static int addYCoordinate(int y) {
        y = enterYCoordinate(y);

        if (y < 0 || y > 10) {
            y = chooseNewYCoord(y);
        }
        return y;
    }


    public static void userChoosesShipLocations(String[][] oceanMap) {
        int x = 0;
        int y = 0;

        System.out.print("Ship 1:");
        x = enterXCoordinate(x);
        y = enterYCoordinate(y);

        if (x < 0 || x > 10 || y < 0 || y > 10) {
            x = chooseNewXCoord(x);
            y = chooseNewYCoord(y);
        }

        oceanMap[x][y] = "@";

        for (int i = 0; i < 4; i++) {
            System.out.print("Ship " + (i + 2) + ":");
            x = addXCoordinate(x);
            y = addYCoordinate(y);
            if (oceanMap[x][y] != null) {
                while (oceanMap[x][y] != null) {
                    x = CoordinateVerification.placeXOccupied(x);
                }
                while (oceanMap[x][y] != null) {
                    y = CoordinateVerification.placeYOccupied(y);
                }
            }
            oceanMap[x][y] = "@";
        }
    }

    //The user attacks the computer
    public static int userAttack(String[][] computerArray, int userCount){
        Scanner userin = new Scanner(System.in);
        int x=0;
        int y=0;

        System.out.println("Choose a location to attack ");
        x = addXCoordinate(x);
        y = addYCoordinate(y);

        if(computerArray[x][y] == null){
            computerArray[x][y]="X";
        }else if(computerArray[x][y] != null && computerArray[x][y] != "*") {
            System.out.println("You have already chose this location.");
            if (computerArray[x][y] != null) {
                while (computerArray[x][y] != null || computerArray[x][y] == "!"
                        || computerArray[x][y] == "X") {
                    x = CoordinateVerification.placeXOccupied(x);
                }
                while (computerArray[x][y] != null || computerArray[x][y] == "!"
                        || computerArray[x][y] == "X") {
                    y = CoordinateVerification.placeYOccupied(y);
                }
            }
        }else if(computerArray[x][y] == "*"){
            System.out.println("*** You have hit one of the Computers boat ***");
            computerArray[x][y]="!";
            userCount++;
        }
        return userCount;

    }

    //Computer functions:

    /**
     * Computer takes in an x-coordinate to be generated randomly
     *
     * @param x
     * @return x
     */
    public static int enterComputerXCoordinate(int x) {
        Random rand = new Random();
        return x = rand.nextInt(10);
    }

    /**
     * Computer takes in an x-coordinate to be generated randomly
     *
     * @param y
     * @return y
     */
    public static int enterComputerYCoordinate(int y) {
        Random rand = new Random();
        return y = rand.nextInt(10);
    }


    public static void computerDeployment(String[][] oceanMap) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < 5; i++) {
            x = enterComputerXCoordinate(x);
            y = enterComputerYCoordinate(y);

            if (oceanMap[x][y] != null) {
                while (oceanMap[x][y] != null) {
                    x = CoordinateVerification.computerPlaceXOccupied(x);
                }
                while (oceanMap[x][y] != null) {
                    y = CoordinateVerification.computerPlaceYOccupied(y);
                }
            }
            oceanMap[x][y] = "*";
            System.out.println((i + 1) + ". ship DEPLOYED");
        }
    }

    //The computer attacks the user
    public static int computerAttack(String[][] userArray, int computerCount){
        int x=0;
        int y=0;

        x = enterComputerXCoordinate(x);
        y = enterComputerYCoordinate(y);

        if(userArray[x][y] == null){
            userArray[x][y]="X";
        }else if(userArray[x][y] != null && userArray[x][y] != "@"){
                while (userArray[x][y] != null || userArray[x][y] == "!"
                        || userArray[x][y] == "X") {
                    x = CoordinateVerification.computerPlaceXOccupied(x);
                }
                while (userArray[x][y] != null || userArray[x][y] == "!"
                        || userArray[x][y] == "X") {
                    y = CoordinateVerification.computerPlaceYOccupied(y);
                }
        } else if(userArray[x][y] != "@"){
            userArray[x][y]="!";
            computerCount++;
        }
        return computerCount;

    }

}

