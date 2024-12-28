import java.util.Scanner;

public class CalculateGrades {

   
    public static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Input: Take marks obtained in each subject
            System.out.print("Enter the number of subjects: ");
            int numSubjects = scanner.nextInt();
            double[] marks = new double[numSubjects];

            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
                double mark = scanner.nextDouble();
                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                } else {
                    System.out.println("Invalid input! Marks should be between 0 and 100.");
                    return;
                }
            }

            
            double totalMarks = 0;
            for (double mark : marks) {
                totalMarks += mark;
            }

            
            double averagePercentage = totalMarks / numSubjects;

            
            String grade = calculateGrade(averagePercentage);

            
            System.out.println("\n--- Results ---");
            System.out.printf("Total Marks: %.2f\n", totalMarks);
            System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
            System.out.println("Grade: " + grade);

            scanner.close();
        }
    }
}

