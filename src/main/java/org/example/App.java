/* Gabriel Fernandez
 * COP3330 - Summer CV01
 * "Motivated" Practice Exercises
 * Exercise 17 - Blood Alcohol Calculator
 */

package org.example;

// Import required libraries
import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class App {

    // Main function
    public static void main(String[] args) {
        // Declare constants, variables, and string prompts
        final double UNSAFE_BAC = 0.08;
        final double MEN_R = 0.73;
        final double WOMEN_R = 0.66;
        double r;
        String m = "m";
        String male = "male";
        String weightPrompt = "How much do you weigh?";
        String drinksPrompt = "How many drinks have you had?";
        String ouncesPrompt = "How many ounces were in the drinks you were having?";
        String abvPrompt = "What was the ABV of the drinks you had? (%)";
        String hoursPrompt = "How long has it been since your last drink? (hours)";

        // Call getDouble, which ensures the user enters an integer
        double weight = getDouble(weightPrompt);

        // Prompt the user for their gender
        System.out.println("What is your gender? (M/F)");
        Scanner genderIn = new Scanner(System.in);
        String gender = genderIn.next();

        // Update the alcoholic distribution ratio depending
        // on the user's gender
        if (m.equals(gender.toLowerCase()) ||
                male.equals(gender.toLowerCase())) {
            r = MEN_R;
        } else {
            r = WOMEN_R;
        }

        // Call getDouble to ensure the user enters an integer for these values
        double drinks = getDouble(drinksPrompt);
        double ounces = getDouble(ouncesPrompt);
        double abv = getDouble(abvPrompt);
        double hours = getDouble(hoursPrompt);

        // Calculate the user's BAC content
        double alcoholConsumed = (drinks * ounces) * (abv / 100);
        double bac = ((alcoholConsumed * 5.14) / (weight * r)) - (.015 * hours);

        // Limit any variable called by df.format() to two decimal places
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.CEILING);

        // Print out the user's BAC and update the output message depending
        // on whether the user's BAC exceeds or is under 0.08
        System.out.println("Your BAC is " + df.format(bac));
        final String msg = bac >= UNSAFE_BAC
                ? "It is not legal for you to drive."
                : "It is legal for you to drive.";
        System.out.println(msg);
    }

    // Get int function, loops until user enters an integer (technically
    // double but still works)
    public static double getDouble(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);

        // Unfortunately, if the user decides to input multiple strings
        // the while loop will output this message for the amount of strings
        // inputted, but still works in the sense that it does not allow the user
        // to continue until an integer is inputted
        while (!sc.hasNextDouble()) {
            System.out.println("Sorry, you must enter an integer, please try again.");
            sc.nextLine();
        }

        // If we have exited the while loop, that means we had an integer
        // be inputted, so we can return that int
        return sc.nextDouble();
    }
}
