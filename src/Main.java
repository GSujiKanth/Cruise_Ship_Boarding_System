// Task 3 - Add Queue
// Student Id: w1867616

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Cabin class array objects
        Cabin[][] myCabin = new Cabin[12][3];
        for (int i = 0; i < 12; i++) {
            for (int l =0; l < 3; l++) {
                myCabin[i][l] = new Cabin();
            }
        }

        //Waiting list
        firstName fname = new firstName();  //first name
        sureName sname = new sureName();    //sure name
        expense expense = new expense();    //expense

        String cabinFirstName, cabinSurName;
        int CabinNumber, PassengerNumber;
        int cabinExpense = 0;
        int TotalExpense = 0;

        //manually input data for testing waiting list
        for (int a = 0; a < 12; a++) {
            for (int b = 0; b < 3; b++) {
                myCabin[a][b].setCabinName("sujikanth");
                myCabin[a][b].setFirstName("sujikanth");
                myCabin[a][b].setSureName("gunaretnam");
                myCabin[a][b].setExpense(100);
            }
        }
        //end

        while (true) {
            System.out.println();
            System.out.println("""
                    A: Add a customer to a cabin
                    V: View all cabins
                    E: Display Empty cabins
                    T: Expenses per Passenger
                    D: Delete customer from cabin
                    F: Find cabin from customer name
                    S: Store program data into file
                    L: Load program data from file
                    O: View passengersOrdered alphabetically by name
                    X: Exit
                    """);
            String choice = input.next();

            switch (choice) {
                case "A":
                    //user inputs
                    // input Cabin NUmber
                    System.out.println("""
                                      One cabin can hold up to 3 passengers. After you booked,
                                      you will be given passenger slot number from  (0 - 2).
                                      Enter Cabin Number(0-11):
                                      """);
                    try {
                        CabinNumber = input.nextInt();
                        //check cabin is fully booked or not
                        if (!myCabin[CabinNumber][0].CabinName.equals("empty") && !myCabin[CabinNumber][1].CabinName.equals("empty") && !myCabin[CabinNumber][2].CabinName.equals("empty")) {
                            System.out.println("Cabin "+CabinNumber+" is fully booked.");

                            //waiting list
                            //counting non free cabin
                            int cabin_count = 0;
                            for (int a = 0; a < 12; a++) {
                                for (int b = 0; b < 3; b++) {
                                    if (!myCabin[a][b].CabinName.equals("empty")) {
                                        cabin_count++;
                                    }
                                }
                            }
                            //adding customer to waiting list
                            if (cabin_count == 36) {
                                System.out.println("""
                                               Cruise Ship is full.
                                               Customer will be added to waiting list.
                                               """);

                                System.out.println("Enter First Name: ");
                                cabinFirstName = input.next();
                                fname.enQueue(cabinFirstName);

                                System.out.println("Enter Sure Name: ");
                                cabinSurName = input.next();
                                sname.enQueue(cabinSurName);

                                System.out.println("Enter Expense: ");
                                cabinExpense = input.nextInt();
                                expense.enQueue(cabinExpense);
                                break;
                            }
                        }
                        else {
                            for (int i = 0; i < 3; i++) {
                                PassengerNumber = i;
                                if (myCabin[CabinNumber][PassengerNumber].CabinName.equals("empty")) {
                                    System.out.println("Enter First Name: ");
                                    cabinFirstName = input.next();
                                    myCabin[CabinNumber][PassengerNumber].setFirstName(cabinFirstName);
                                    myCabin[CabinNumber][PassengerNumber].setCabinName(cabinFirstName);

                                    System.out.println("Enter Sure Name: ");
                                    cabinSurName = input.next();
                                    myCabin[CabinNumber][PassengerNumber].setSureName(cabinSurName);

                                    System.out.println("Enter Your Expense: ");
                                    cabinExpense = input.nextInt();
                                    myCabin[CabinNumber][PassengerNumber].setExpense(cabinExpense);

                                    System.out.println("Cabin "+CabinNumber+" Passenger slot "+PassengerNumber+" is booked by "+cabinFirstName+" "+cabinSurName);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                    break;

                case "V":
                    //Display all cabins status(Booked or Not Booked)
                    //display cabin details
                    for (int i = 0; i < 12; i++) {
                        if (myCabin[i][0].CabinName.equals("empty") && myCabin[i][1].CabinName.equals("empty") && myCabin[i][2].CabinName.equals("empty")) {
                            System.out.println("Cabin "+i+" is empty.");
                        } else {
                            System.out.println("Cabin "+i+" : ");
                            for (int j = 0; j < 3; j++) {
                                if (!myCabin[i][j].CabinName.equals("empty")) {
                                    System.out.println("    Cabin "+i+" Passenger slot "+j+" is booked by "+myCabin[i][j].CabinName);
                                } else {
                                    System.out.println("    Cabin "+i+" Passenger slot"+j+" is empty.");
                                }
                            }
                        }
                    }
                    break;

                case "E":
                    //Display empty cabins
                    for (int i = 0; i < 12; i++) {
                        if (myCabin[i][0].CabinName.equals("empty") && myCabin[i][1].CabinName.equals("empty") && myCabin[i][2].CabinName.equals("empty")) {
                            System.out.println("Cabin "+i+" is empty.");
                        }
                    }
                    break;

                case "T":
                    //print Passenger Expenses
                    //print each passenger expenses
                    for (int i = 0; i < 12; i++) {
                        for (int l = 0; l < 3; l++) {
                            if (!myCabin[i][l].CabinName.equals("empty")) {
                                myCabin[i][l].Expense();
                                TotalExpense += myCabin[i][l].Expense;
                            }
                        }
                    }
                    //print total passenger expenses
                    System.out.println("Total expenses of all passengers = " + TotalExpense);
                    TotalExpense = 0;
                    break;

                case "D":
                    //Delete customer from cabin
                    System.out.println("Enter Cabin Number: ");
                    try {
                        CabinNumber = input.nextInt();
                        if (CabinNumber > 13 || CabinNumber < 0) {
                            System.out.println("Invalid input");
                            break;
                        }
                        System.out.println("Enter passenger slot number in cabin "+CabinNumber+":");
                        PassengerNumber = input.nextInt();
                        //check passenger number range
                        if (PassengerNumber > 2 || PassengerNumber < 0) {
                            System.out.println("Invalid input");
                            break;
                        }
                        System.out.println("Cabin "+CabinNumber+", Passenger : "+myCabin[CabinNumber][PassengerNumber].getCabinName()+" is deleted");
                        myCabin[CabinNumber][PassengerNumber].setCabinName("empty");
                        myCabin[CabinNumber][PassengerNumber].setCabinName("empty");
                        myCabin[CabinNumber][PassengerNumber].setFirstName("empty");
                        myCabin[CabinNumber][PassengerNumber].setSureName("empty");
                        myCabin[CabinNumber][PassengerNumber].setExpense(0);

                        //assigning passengers from waiting list to free cabin
                        String elem_fname = fname.deQueue();
                        String elem_sname = sname.deQueue();
                        int elem_expense = expense.deQueue();

                        if (!elem_fname.equals("error")) {
                            myCabin[CabinNumber][PassengerNumber].setCabinName(elem_fname);
                            myCabin[CabinNumber][PassengerNumber].setFirstName(elem_fname);
                        }
                        if (!elem_sname.equals("error")) {
                            myCabin[CabinNumber][PassengerNumber].setSureName(elem_sname);
                        }
                        if (elem_expense != -1) {
                            myCabin[CabinNumber][PassengerNumber].setExpense(elem_expense);
                            System.out.println("From Waiting List to Cabin: "+elem_fname+" "+elem_sname+" added to Cabin "+CabinNumber+" passenger slot "+PassengerNumber);
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                    break;

                case "F":
                    System.out.println("Enter Customer First Name: ");
                    try {
                        cabinFirstName = input.next();
                        //Find cabin from customer name
                        for (int i = 0; i < 12; i++) {
                            for (int l = 0; l < 3; l++) {
                                if (myCabin[i][l].FirstName.equals(cabinFirstName)) {
                                    System.out.println(cabinFirstName+" booked in Cabin "+i+" passenger slot "+l);
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                    break;

                case "S":
                    //store program data into file
                    try {
                        FileWriter inputData = new FileWriter("Task3Cabin.txt");
                        for (int i = 0; i < 12; i++) {
                            if (myCabin[i][0].CabinName.equals("empty") && myCabin[i][1].CabinName.equals("empty") && myCabin[i][2].CabinName.equals("empty")) {
                                inputData.write("Cabin "+i+" is empty.\n");
                            } else {
                                inputData.write("Cabin "+i+" : \n");
                                for (int j = 0; j < 3; j++) {
                                    if (!myCabin[i][j].CabinName.equals("empty")) {
                                        inputData.write("  Cabin "+i+" Passenger slot "+j+" is booked by "+myCabin[i][j].getFirstName()+" "+myCabin[i][j].getSureName()+", Expense : "+myCabin[i][j].getExpense()+"\n");
                                    } else {
                                        inputData.write("  Cabin "+i+" Passenger slot "+j+" is empty.\n");
                                    }
                                }
                            }
                        }
                        inputData.close();
                        System.out.println("Program data successfully stored into file.");
                    } catch (IOException e) {
                        System.out.println("An Error Occurred.");
                    }
                    break;

                case "L":
                    //Load program data from file
                    try {
                        int line_count = 0;
                        File inputFile = new File("Task3Cabin.txt");
                        Scanner outputData = new Scanner(inputFile);
                        String file_line;
                        while (outputData.hasNext()) {
                            file_line = outputData.nextLine();
                            System.out.println(file_line);
                            line_count++;
                        }
                        outputData.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                    }

                    break;

                case "O":
                    //sorting
                    //create a copy array for myCabin
                    String copy[][] = new String[12][3];
                    for (int i = 0; i < 12; i++) {
                        for (int l = 0; l < 3; l++) {
                            copy[i][l] = myCabin[i][l].getCabinName();
                        }
                    }

                    //find total number of passengers
                    int total = 0;
                    for (int i = 0; i < 12; i++) {
                        for (int l = 0; l < 3; l++) {
                            if (!copy[i][l].equals("empty")) {
                                total++;
                            }
                        }
                    }

                    String sort_array[] = new String[total];
                    //copying passengers names into array_sort
                    int k = 0;
                    for (int i = 0; i < 12; i++) {
                        for (int l = 0; l < 3; l++) {
                            if (!copy[i][l].equals("empty")) {
                                sort_array[k] = copy[i][l];
                                k++;
                                copy[i][l] = "empty";
                            }
                        }
                    }

                    String temp;
                    for (int i = 0; i < total; i++) {
                        for (int j = i + 1; j < total; j++) {

                            // compare one string with other strings
                            if (sort_array[i].compareTo(sort_array[j]) > 0) {
                                temp = sort_array[i];
                                sort_array[i] = sort_array[j];
                                sort_array[j] = temp;
                            }
                        }
                    }

                    // print output array
                    System.out.println(
                            "The names in alphabetical order: ");
                    for (int p = 0; p < total; p++) {
                        System.out.println(sort_array[p]);
                    }

                    break;

                case "X":
                    return;

                default:
                    System.out.println("Invalid input\n\n");
                    break;
            }
        }
    }

    // Circular queue for First Name
    public static class firstName {
        int SIZE = 5; // Size of Circular Queue
        int front, rear;
        String items[] = new String[SIZE];

        String f_name, s_name;
        int expense;
        int cabin_num, passenger_num;

        firstName() {
            front = -1;
            rear = -1;
        }

        // Check if the queue is full
        boolean isFull() {
            if (front == 0 && rear == SIZE - 1) {
                return true;
            }
            if (front == rear + 1) {
                return true;
            }
            return false;
        }

        // Check if the queue is empty
        boolean isEmpty() {
            if (front == -1)
                return true;
            else
                return false;
        }

        // Adding an element
        void enQueue(String element) {
            if (isFull()) {
                System.out.println("Queue is full");
            } else {
                if (front == -1)
                    front = 0;
                rear = (rear + 1) % SIZE;
                items[rear] = element;
            }
        }

        // Removing an element
        String deQueue() {
            String element;
            if (isEmpty()) {
                return ("error");
            } else {
                element = items[front];
                if (front == rear) {
                    front = -1;
                    rear = -1;
                } /* Q has only one element, so we reset the queue after deleting it. */
                else {
                    front = (front + 1) % SIZE;
                }
                return (element);
            }
        }
    }

    // Circular queue for Sure Name
    public static class sureName {
        int SIZE = 5; // Size of Circular Queue
        int front, rear;
        String items[] = new String[SIZE];

        String f_name, s_name;
        int expense;
        int cabin_num, passenger_num;

        sureName() {
            front = -1;
            rear = -1;
        }

        // Check if the queue is full
        boolean isFull() {
            if (front == 0 && rear == SIZE - 1) {
                return true;
            }
            if (front == rear + 1) {
                return true;
            }
            return false;
        }

        // Check if the queue is empty
        boolean isEmpty() {
            if (front == -1)
                return true;
            else
                return false;
        }

        // Adding an element
        void enQueue(String element) {
            if (isFull()) {
                System.out.println("Queue is full");
            } else {
                if (front == -1)
                    front = 0;
                rear = (rear + 1) % SIZE;
                items[rear] = element;
            }
        }

        // Removing an element
        String deQueue() {
            String element;
            if (isEmpty()) {
                return ("error");
            } else {
                element = items[front];
                if (front == rear) {
                    front = -1;
                    rear = -1;
                } /* Q has only one element, so we reset the queue after deleting it. */
                else {
                    front = (front + 1) % SIZE;
                }
                return (element);
            }
        }
    }

    // Circular queue for Expense
    public static class expense {
        int SIZE = 5; // Size of Circular Queue
        int front, rear;
        int items[] = new int[SIZE];

        String f_name, s_name;
        int expense;
        int cabin_num, passenger_num;

        expense() {
            front = -1;
            rear = -1;
        }

        // Check if the queue is full
        boolean isFull() {
            if (front == 0 && rear == SIZE - 1) {
                return true;
            }
            if (front == rear + 1) {
                return true;
            }
            return false;
        }

        // Check if the queue is empty
        boolean isEmpty() {
            if (front == -1)
                return true;
            else
                return false;
        }

        // Adding an element
        void enQueue(int element) {
            if (isFull()) {
                System.out.println("Queue is full");
            } else {
                if (front == -1)
                    front = 0;
                rear = (rear + 1) % SIZE;
                items[rear] = element;
            }
        }

        // Removing an element
        int deQueue() {
            int element;
            if (isEmpty()) {
                return (-1);
            } else {
                element = items[front];
                if (front == rear) {
                    front = -1;
                    rear = -1;
                } /* Q has only one element, so we reset the queue after deleting it. */
                else {
                    front = (front + 1) % SIZE;
                }
                return (element);
            }
        }
    }
}
