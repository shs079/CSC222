import java.util.Scanner;

class foodItem {
  private String name;
  private double fat;
  private double carbs;
  private double protein;

  //constants for calculating calories
  private static final double CALORIES_PER_GRAM_FAT = 9.0;
  private static final double CALORIES_PER_GRAM_CARBS = 4.0;
  private static final double CALORIES_PER_GRAM_PROTEIN = 4.0;

  
//contrustor methods
  public foodItem(String name, double fat, double carbs, double protein) {
    this.name = name;
    this.fat = fat;
    this.carbs = carbs;
    this.protein = protein;
  }

  public void printInfo() {
    System.out.println("Fat: " + fat + " g");
    System.out.println("Carbohydrates: " + carbs + " g");
    System.out.println("Protein: " + protein + " g");
  }

  public double getCalories(double servings){
    double calories_for_serving = (fat * CALORIES_PER_GRAM_FAT)
      + (carbs * CALORIES_PER_GRAM_CARBS)
      + (protein * CALORIES_PER_GRAM_PROTEIN);
    double calories = (calories_for_serving * servings);
    return calories;
  }

  public String getDominantMacronutrient(){
    String macronutrient;
    if (fat > carbs && fat > protein) {
        macronutrient = "Fat";
      } else if (carbs > fat && carbs > protein){
        macronutrient = "Carbs";
      } else {
        macronutrient = "Protein";
      }
    return macronutrient;
  }
  
}


// Class for tracking nutrition information
class NutritionTracker {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Get the number of food items from the user
    System.out.println("How many food items would you like to input?");
    int numFoods = scanner.nextInt();
    scanner.nextLine();

    double allCalories = 0;

    // Loop to input and process each food item
    for (int i = 0; i < numFoods; i++) {
      System.out.print("Enter the name of the food item: ");
      String name = scanner.nextLine();

      // Input the nutritional values for the food item
      double fat = getInputValue(scanner, "Enter the amount of fat in " + name + " (grams): ");
      double carbs = getInputValue(scanner, "Enter the amount of carbs in " + name + " (grams): ");
      double protein = getInputValue(scanner, "Enter the amount of protein in " + name + " (grams): ");
      double servings = getInputValue(scanner, "Enter the number of servings: ");

      // Create a fooItem object with the entered information
      foodItem foodItem = new foodItem(name, fat, carbs, protein);

      // Display nutritional information per serving of the food item
      System.out.println("Nutritional information per serving of " + name + ":");
      foodItem.printInfo();

      // Calculate total calories for the specified servings
      double singleCalories = foodItem.getCalories(servings);
      System.out.printf("Total Calories for %.2f servings of " + name + ": %.2f\n", servings, singleCalories);

      // Determine the dominant macronutrient of the food item
      String macronutrient = foodItem.getDominantMacronutrient();
      System.out.println("Dominant Macronutrient: " + macronutrient);

      // Accumulate the total calories for all food items
      allCalories += singleCalories;
    }

    // Display the total combined calories for all food items
    System.out.printf("Total combined calories for all food items: %.2f\n", allCalories);
    System.out.println("Thank you for using the Nutrition Information System!");

    // Close the scanner to release resources
    scanner.close();
  }

  // Method to get a valid non-negative double input from the user
  private static double getInputValue(Scanner scanner, String prompt) {
    double value;
    while (true) {
      System.out.print(prompt);
      while (!scanner.hasNextDouble()) {
        System.out.print("Invalid input. Please enter a valid number: ");
        scanner.next();
      }
      value = scanner.nextDouble();
      if (value >= 0) {
        scanner.nextLine();
        break; // Valid input, exit the loop
      } else {
        System.out.println("Input cannot be negative. Please enter a non-negative number.");
      }
    }
    return value;
  }
}
