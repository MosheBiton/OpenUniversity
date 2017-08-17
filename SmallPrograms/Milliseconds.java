import java.util.Scanner;
import java.lang.Math;

/*
This program converts a date in ms to regular 24:h date
 * input assumption: at least 1000 (ms)
 */
public class Milliseconds {

    public static void main(String[] args) {
        final int MSTOSECOND = 1000, SECONDTOMINUTE = 60, MINUTETOHOUR = 60, HOURTODAY = 24;
        Scanner scan = new Scanner(System.in);
        System.out.println("This program reads an integer which represents Milliseconds and converts it to days, hours, minutes and seconds. ");
        System.out.println("Please enter the number of Milliseconds");
        long ms = scan.nextLong();
        int days=0, hours=0, minutes=0, seconds=0; // Those variables serve as time info variables with default value of 0

        // Checks if the user input is invalid
        if(ms <1000){
            System.out.println("Please run the program again and enter a valid number");
            return; // if the input of the user is invalid we will exit the method
        }
        // if the user input is valid, the program continues:

        // Without using the "remainder" operator.
        days = (int)(((((ms/MSTOSECOND)/SECONDTOMINUTE)/MINUTETOHOUR)/HOURTODAY)); // Calculate days
        hours = (int)(((ms/MSTOSECOND)/SECONDTOMINUTE)/MINUTETOHOUR) - (days * 24); // Calculate hours
        minutes = (int)(ms/MSTOSECOND/SECONDTOMINUTE) - ((hours * 60) + (days * 24 * 60)); // Calculate minutes
        seconds = (int)(ms/MSTOSECOND) - ((minutes * 60) + (hours * 60 * 60) + (days * 24 * 60 * 60)); // Calculate seconds

        System.out.println(days + " days " + hours + ":" + minutes + ":" + seconds); // printing the outcome to the user

        /* Another Solution: Using the "remainder" operator */
        /*
        final int MSTOSECOND = 1000, MSTOMINUTE = 60000, MSTOHOUR = 3600000, MSTODAY = 86400000;

        days = (ms/MSTODAY)
        hours = (ms/MSTOHOUR)$24
        minutes = (ms/MSTOMINUTE)%60
        seconds = (ma/MSTOSECOND)%60
        */
    }
}