import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //COLORS
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RED_BG = "\033[41m";    // RED
    public static final String RESET = "\033[0m";      // RESET

    // Registration Process
    static void register() {
        System.out.println("\n\n" + BLUE+"********** REGISTRATION PROCESS ************\n\n"+ RESET);
        Scanner RegScan = new Scanner(System.in);
        
        String name; // NAME
        while (true){
            System.out.print("[#]~ Enter patient NAME : ");
            name = RegScan.nextLine();
            if ((name.length()<65) && (name.length()>5)){
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        String gender; // GENDER
        while (true) {
            System.out.print("[#]~ Enter patient GENDER (M / F): ");
            gender = RegScan.nextLine();
            if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")) {
                break;
            } else {
                System.out.println(RED + "[!] Invalid Input.\n" + RESET);
            }
        }

        String nic; //NIC AS ID
        while (true){
            System.out.print("[#]~ Enter patient NIC : ");
            nic = RegScan.nextLine();
            if ((nic.length()>=10) && (nic.length()<=12)){
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        int age; //AGE
        while (true){
            System.out.print("[#]~ Enter patient AGE : ");
            age = RegScan.nextInt();
            if ((age>0) && (age<120)){
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        String address; //ADDRESS
        while (true){
            System.out.print("[#]~ Enter patient ADDRESS : ");
            address = RegScan.next();
            if ((address.length()>4) && address.length()<65){
                if (address.contains(",")){
                    address = address.replace("," , "_");  //Replace , to avoid errors
                }
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        String contact; //CONTACT NO.
        while (true){
            System.out.print("[#]~ Enter patient CONTACT NO. : ");
            contact = RegScan.next();
            if (contact.length()==10){
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        // Store in array
        String[] RegData = {name, gender, nic, Integer.toString(age), address, contact};

        // Write the array into patient.txt
        try (BufferedWriter RegBuffer = new BufferedWriter(new FileWriter("patients.txt", true))) {
            RegBuffer.newLine();
            RegBuffer.write(Arrays.toString(RegData));
        } catch (Exception e) {
            System.out.println("[!] Error Occurred");
        }

        System.out.println(GREEN+"\n[*] New Patient Registration Successful."+RESET);

        navigateToMain();
    }

    //Manage Doctors
    static void manageDoc() {
        System.out.println("\n\n"+BLUE+"********** DOCTOR MANAGEMENT ************\n\n"+RESET);
        Scanner DocScan = new Scanner(System.in);

        // Get Inputs
        String name; //NAME
        while (true){
            System.out.print("[#]~ Enter doctor NAME : ");
            name = DocScan.nextLine();
            if ((name.length()>5) && (name.length()<65)){
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        String id;  //REG.ID
        while (true){
            System.out.print("[#]~ Enter doctor REGISTRATION ID : ");
            id = DocScan.nextLine();
            if ( (id.length()>=6) && (id.length()<=12) ){
                break;
            }
            System.out.println(RED + "[!] Invalid Input.\n" + RESET);
        }

        int price;  //PRICE
        while (true){
            try {
                System.out.print("[#]~ Enter channeling PRICE : ");
                price = DocScan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println(RED + "[!] Invalid Input.\n" + RESET);
            }
        }

        int allocation;  //ALLOCATION SEATS
        while (true) {
            try {
                System.out.print("[#]~ Enter Allocation Seats : ");
                allocation = DocScan.nextInt();
                if (allocation>0){
                    break;
                }
                System.out.println(RED + "[!] Invalid Input.\n" + RESET);
            } catch (Exception e) {
                System.out.println(RED + "[!] Invalid Input.\n" + RESET);
            }
        }

        //START TIME OF SCHEDULE
        System.out.print("\n[#]~ Enter doctor consultation start time (HH:MM) : ");
        String start = DocScan.next();

        //END OF SCHEDULED TIME
        System.out.print("\n[#]~ Enter doctor consultation end time (HH:MM) : ");
        String end = DocScan.next();

        //DATE SELECTION
        System.out.println("\n\n---- CONSULTATION DATES ----");
        System.out.println("\n[1] Monday");
        System.out.println("[2] Tuesday");
        System.out.println("[3] Wednesday ");
        System.out.println("[4] Thursday");
        System.out.println("[5] Friday");
        System.out.println("[6] Saturday");
        System.out.println("[7] Sunday");
        System.out.print("\n[#] Select your option : ");  //Date Option Selection Input
        int DayOption = DocScan.nextInt();
        String day = "";
        switch (DayOption) {
            case 1:
                day = "Monday";break;
            case 2:
                day = "Tuesday";break;
            case 3:
                day = "Wednesday";break;
            case 4:
                day = "Thursday";break;
            case 5:
                day = "Friday";break;
            case 6:
                day = "Saturday";break;
            case 7:
                day = "Sunday";break;
        }

        //SPECIALIZATIONS
        System.out.println("\n\n---- SPECIALIZATIONS ----");
        System.out.println("\n[1] Cardiology");
        System.out.println("[2] Neurology");
        System.out.println("[3] Orthopedics ");
        System.out.println("[4] Pediatrics");
        System.out.println("[5] Psychiatry");
        System.out.println("[6] ENT (Ear, Nose, Throat)");
        System.out.println("[7] VOG");
        System.out.println("[8] VP - Visiting Physician ");

        //Option Selection Input
        String spec ;
        int SpecOption;
        while (true) {
            System.out.print("\n[#] Select your option : ");
            SpecOption = DocScan.nextInt();

            spec = "";
            switch (SpecOption) {
                case 1:
                    spec = "Cardiology";break;
                case 2:
                    spec = "Neurology";break;
                case 3:
                    spec = "Orthopedics";break;
                case 4:
                    spec = "Pediatrics";break;
                case 5:
                    spec = "Psychiatry";break;
                case 6:
                    spec = "ENT";break;
                case 7:
                    spec = "VOG";break;
                case 8:
                    spec = "VP";break;
            }
            //CHECK WHETHER THE SPECIALIZATION EXISTS ALREADY
            String[] Result = searchArrayItem("doctors.txt", spec, 7);
            if (Result.length > 0) {
                System.out.println(RED+"[!] This specialization has been already added."+RESET);
            } else {
                break;
            }
        }
        // Store in array
        String[] RegData = {name, id, Integer.toString(price), Integer.toString(allocation), start, end, day, spec , String.valueOf(SpecOption)};
        // write into doctor.txt
        WriteInFiles("doctors.txt", RegData);
        System.out.println(GREEN + "\n[*] New Doctor Specialization Added Successfully." + RESET);

        navigateToMain();
    }

    //Book Appointments
    static void bookAppointment() {
        System.out.println("\n\n"+BLUE+"#~~~~~~~ DOCTOR SCHEDULES ~~~~~~~~#\n\n"+RESET);
        Scanner BookScan = new Scanner(System.in);

        try{
            for (int i = 1; i <=8 ; i++) {
                String[] Result = searchArrayItem("doctors.txt",String.valueOf(i),8);
                if (Result.length > 0) {
                    //Iterate and list out them
                    System.out.println( BLUE + "(" + Result[8] + ") " + "\n~ SPECIALIZATION :: " + Result[7] + RESET);
                    System.out.println("\t\t~ NAME : Dr."+ Result[0] );
                    System.out.println("\t\t~ DATE : " + Result[6]);
                    System.out.println("\t\t~ TIME : " + Result[4] + " - " + Result[5]);
                    System.out.println("\t\t~ PRICE : Rs." + Result[2]);
                    System.out.println("\t\t~ AVAILABLE SEATS :" + Result[3]);
                }
            }
        } catch (Exception e) {
            System.out.println(RED+"[!] Error Occurred"+RESET);
        }

        String AppointTime , specId , AppointPrice;
        //Specialist selection
        while (true){
            System.out.print("\n\n[#] Which specialist would you like to book? (Please enter the ID) : ");
            specId = BookScan.next();
            if (Integer.parseInt(specId)>8 || Integer.parseInt(specId)<1){
                System.out.println(RED+"[!] Wrong Input"+RESET);
                continue;
            }
            // Handle
            String[] Result = searchArrayItem("doctors.txt",specId,8);
            if (Result.length>0){
                AppointTime = (Result[4] + " - "  + Result[5]);
                AppointPrice = (Result[2]);
                break;
            }else{
                System.out.println(RED+"[!] Wrong Input"+RESET);
            }
        }

        String nic;
        int Option;
        while(true){
            System.out.print("\n[#] Enter patient NIC : ");
            nic = BookScan.next();

            if (!((nic.length() >= 10) && (nic.length() <= 12))){
                System.out.println(RED + "[!] Invalid Input.\n" + RESET);
                continue;
            }

            String[] Result = searchArrayItem("patients.txt",nic,2);
            if (Result.length>0){
                break;
            }else{
                System.out.println("[!] This patient has not registered yet.");
                System.out.println("\n\n[1] Register the user");
                System.out.println("[2] Enter nic again.");

                System.out.print("\n[#] Navigate to : ");
                Option = BookScan.nextInt();

                if (Option == 1) {
                    register();
                    break;
                }
            }
        }

        String AppointDate; // Set Appointment Date
        System.out.print("[#]~ Booking Date ? (MM.DD): ");
        AppointDate = BookScan.next();

        String Paid,PaymentStatus; // Set Payment Status
        while (true) {
            System.out.print("[#]~ Paid At Booking? (Y / N) : ");
            Paid = BookScan.next();
            if (Paid.equalsIgnoreCase("Y")) {
                PaymentStatus = "Paid";
                break;
            } else if (Paid.equalsIgnoreCase("N")){
                PaymentStatus = "Pending";
                break;
            } else {
                System.out.println(RED + "[!] Invalid Input.\n" + RESET);
            }
        }

        // Store in array
        String[] BookingData = {nic, AppointPrice, PaymentStatus, AppointDate, AppointTime, specId};
        // write into appointment.txt
        WriteInFiles("appointments.txt", BookingData);
        System.out.println(GREEN+"\n[*] New Appointment Placed Successfully."+RESET);

        navigateToMain();
    }

    //Search & Cancel Appointment
    static void searchAndCancelAppointment() {
        System.out.println("\n\n"+BLUE+"#~~~~~~~ SEARCH/CANCEL APPOINTEMENT  ~~~~~~~~#"+RESET);
        Scanner AppScan = new Scanner(System.in);

        System.out.println("\n\nAvailable Specializations : ");
        //List down all spec ids
        for (int i = 1; i <= 8 ; i++) {
            String[] Result = searchArrayItem("doctors.txt",String.valueOf(i),8);
            if (Result.length > 0) {
                System.out.println("\t~ ID = "+ i + ") :: " + Result[7]);  //Iterate and list out them
            }
        }

        System.out.println("\n\n[1] Search Appointments");
        System.out.println("[2] Cancel Appointments");
        System.out.print("\n[#] Option : ");  //Getting input
        int Option = AppScan.nextInt();

        int SpecId;
        String nic;
        switch(Option) {
            case 1:
                System.out.println("\n------- Search Appointments -------\n");
                System.out.print("[#] Enter the specialization ID : ");
                SpecId = AppScan.nextInt();

                try (BufferedReader Searchreader = new BufferedReader(new FileReader("appointments.txt"))) {
                    String Line;

                    System.out.println(BLUE + "\n\n# Search Results Of Specialization ID : " + SpecId+ RESET);
                    while ((Line = Searchreader.readLine()) != null) {
                        Line = Line.trim();
                        if (Line.isEmpty()) {
                            continue;
                        }
                        String[] ArrayLine = Line.replace("[", "").replace("]", "").split(", ");
                        if (Integer.parseInt(ArrayLine[5]) == SpecId) {
                            System.out.println("\t NIC: " + ArrayLine[0] + " , Booking-Date:" + ArrayLine[3] + " , Payment: " + ArrayLine[1] + " , Payment Status: " + ArrayLine[2]);

                        }
                    }
                } catch (IOException e) {
                    System.out.println(RED+"[!] Error Occurred"+RESET);
                }
                break;

            case 2:
                System.out.println("\n------- Cancel Appointments -------\n");
                System.out.print("[#] Enter the specialization ID : ");
                SpecId = AppScan.nextInt();

                System.out.print("[#] Enter the patient NIC : ");
                nic = AppScan.next();

                boolean RecordFound = false;
                try (BufferedReader Searchreader = new BufferedReader(new FileReader("appointments.txt"))) {
                    String Line;
                    while ((Line = Searchreader.readLine()) != null) {
                        Line = Line.trim();
                        if (Line.isEmpty()) {
                            continue;
                        }
                        String[] ArrayLine = Line.replace("[", "").replace("]", "").split(", ");

                        if ((Integer.parseInt(ArrayLine[5]) == SpecId) && (ArrayLine[0].equals(nic))) {
                            System.out.println(BLUE+"\n# Record Found :" + RESET);
                            System.out.println("\t NIC :" + ArrayLine[0] + " , Booking-Date:" + ArrayLine[3] + " , Payment :" + ArrayLine[1] + " , Payment Status : " + ArrayLine[2]);
                            RecordFound = true;
                            //passing this line by writing into temp.txt
                        } else {
                            WriteInFiles("temp.txt", ArrayLine);   //Write all other lines into a temp file
                        }
                    }
                } catch (IOException e) {
                    System.out.println(RED+"[!] Error Occurred"+RESET);
                }

                // Deletion
                if (RecordFound) {
                    File SourceFile = new File("temp.txt");
                    File DestinationFile = new File("appointments.txt");
                    try {
                        Files.move(SourceFile.toPath(), DestinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println( GREEN+ "\n[*] Record deleted successfully."+RESET);
                        break;
                    } catch (IOException e) {
                        System.out.println(RED+"\n[!] Failed to delete the record: " + e.getMessage() + RESET); // The move operation failed
                        break;
                    }
                } else {
                    System.out.println(RED+"\n[!] Record Not Found "+ RESET);
                }
        }
        navigateToMain();
    }

    //Write into file
    public static void WriteInFiles(String Filename,String[] Data){
        try (BufferedWriter RegBuffer = new BufferedWriter(new FileWriter(Filename, true))) {
            RegBuffer.newLine();
            RegBuffer.write(Arrays.toString(Data));
        } catch (Exception e) {
            System.out.println(RED+ "[!] Error Occurred" + RESET);
        }
    }

    // SearchArrayItems
    public static String[] searchArrayItem(String Filename, String SearchWord, int SearchIndex) {
        try (BufferedReader Searchreader = new BufferedReader(new FileReader(Filename))) {
            String Line;
            while ((Line = Searchreader.readLine()) != null) {
                Line = Line.trim();
                if (Line.isEmpty()){
                    continue;
                }
                String[] ArrayLine = Line.replace("[", "").replace("]", "").split(", ");

                if (SearchIndex >= 0 && SearchIndex < ArrayLine.length && ArrayLine[SearchIndex].equals(SearchWord)) {
                    return ArrayLine;
                }
            }
        } catch (IOException e) {
            System.out.println(RED+"[!] Error Occurred"+RESET);
        }
        return new String[0];
    }

    public static void navigateToMain(){
        Scanner Navigate = new Scanner(System.in);
        System.out.print(BLUE + "\n\n[#] Navigate To Main Interface ( y- continue | q -Quit) : " + RESET);
        String Option = Navigate.next();
        Option = Option.trim();
        if (Option.equalsIgnoreCase("q")){
            System.out.println(RED+"\n\n[*] Thank you !!! "+RESET);
            System.exit(0);
        }else{
            System.out.println(RED+"\n\n[*] Navigating To Main Interface.\n" + RESET);
        }
    }

    public static boolean login(){
        Scanner LogScan = new Scanner(System.in);
        System.out.print("\n[?] Enter Username: ");
        String username = LogScan.nextLine().trim();
        System.out.print("\n[?] Enter Password: ");
        String password = LogScan.nextLine().trim();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println(RED+"\n [!] Username and password cannot be empty."+RESET);
            return false;
        }
        if (username.equals("admin")){ // Credential checking
            if (password.equals("1234")) {
                System.out.println(GREEN+"\n[*] Logging into the system "+RESET);
                return true;
            } else {
                System.out.println(RED+"\n[!] Invalid password"+RESET);
            }
        }else{
            System.out.println(RED+"\n[!] Invalid username"+RESET);
        }
        return false;
    }

    public static void main(String[] args) {
        if (login()){
            while (true) {
                System.out.println(GREEN + "\n\n#~~~~~~~~~~~~~~~~~~~~~~| DOCTOR CHANNELING SYSTEM |~~~~~~~~~~~~~~~~~~~~~~#" + RESET);
                System.out.println("\t\t\t\t\t\t  " + RED_BG + " # BY Team 53 - OUSL " + RESET);
                System.out.println("\t\t\t\t\t\t\t " + RED_BG + " # Version 1.0 " + RESET + "\n\n");
                System.out.println("\t[1]~ New Patient Registration");
                System.out.println("\t[2]~ Book Appointment");
                System.out.println("\t[3]~ Search / Cancel Appointments");
                System.out.println("\t[4]~ Manage Doctor Schedules");
                System.out.println("\t[5]~ Exit");

                try {
                    Scanner MainOptionScan = new Scanner(System.in);
                    System.out.print("\n[#] Select your option : "); //Option Selection Input

                    int MainOption = MainOptionScan.nextInt();
                    switch (MainOption) { //Calling Functions
                        case 1:
                            register();
                            break;
                        case 2:
                            bookAppointment();
                            break;
                        case 3:
                            searchAndCancelAppointment();
                            break;
                        case 4:
                            manageDoc();
                            break;
                        case 5:
                            System.out.println(RED+"\n\n[*] Thank you !!! "+RESET);
                            System.exit(0);
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(RED+"[!] Error Occurred"+RESET);
                }
            }
        }
    }
}
