    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Scanner;
        // Base class

        class User {
            private String username;
            private String password;
        
            public User(String username, String password) {
                this.username = username;
                this.password = password;
            }
        
            public String getUsername() {
                return username;
            }
        
            public String getPassword() {
                return password;
            }
        
            public void setPassword(String password) {
                this.password = password;
            }
        }
    
        // Admin subclass
        class Admin extends User {
            Scanner scanner = new Scanner(System.in);
    
            public Admin(String username, String password) {
                super(username, password);
            }
    
            public void viewRegisteredUsers(ArrayList<User> users) {
                boolean viewingUsers = true;
                while (viewingUsers) {
                    System.out.println("=== Registered Users ===");
                    System.out.println();
            
                    // Display all users
                    for (User user : users) {
                        if (user instanceof Admin) {
                            System.out.println("- Full Name: [Admin Name] | Username: " + user.getUsername() + " | User Type: Admin");
                        } else if (user instanceof Teacher) {
                            Teacher teacher = (Teacher) user;
                            System.out.println("- Full Name: " + teacher.getFirstName() + " " + teacher.getLastName() +
                                    " | Username: " + teacher.getUsername() +
                                    " | User Type: Teacher");
                        } else if (user instanceof Student) {
                            Student student = (Student) user;
                            System.out.println("- Full Name: " + student.getFirstName() + " " + student.getLastName() +
                                    " | Username: " + student.getUsername() +
                                    " | User Type: Student | Student ID: " + student.getStudentNumber());
                        }
                    }

                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("Options:");
                    System.out.println("[1] Search student or teacher information");
                    System.out.println("[2] Back to Menu");
                    System.out.print("Enter choice: ");
                    String choice = scanner.nextLine();
            
                    if (choice.equals("1")) {
                        System.out.println("==================================================================================================================================================================================================================================================");
                        // Search for a student or teacher
                        System.out.print("Enter Student ID, Student Last Name, or Teacher Last Name: ");
                        String searchQuery = scanner.nextLine();
            
                        boolean found = false;
                        ArrayList<User> searchResults = new ArrayList<>();
            
                        for (User user : users) {
                            if (user instanceof Student) {
                                Student student = (Student) user;
                                // Match by student ID or last name
                                if (String.valueOf(student.getStudentNumber()).equals(searchQuery) ||
                                    student.getLastName().equalsIgnoreCase(searchQuery)) {
                                    searchResults.add(user);
                                    found = true;
                                }
                            } else if (user instanceof Teacher) {
                                Teacher teacher = (Teacher) user;
                                // Match by last name
                                if (teacher.getLastName().equalsIgnoreCase(searchQuery)) {
                                    searchResults.add(user);
                                    found = true;
                                }
                            }
                        }
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("=== Search Result ===");
                        System.out.println();
                        if (found) {
                            // Display search results only
                            for (User user : searchResults) {
                                if (user instanceof Student) {
                                    Student student = (Student) user;
                                    System.out.println("- Username: " + student.getUsername());
                                    System.out.println("- Password: " + student.getPassword());
                                    System.out.println("- Full Name: " + student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
                                    System.out.println("- Contact Number: " + student.getContactNum());
                                    System.out.println("- Age: " + student.getAge());
                                    System.out.println("- Sex: " + student.getSex());
                                    System.out.println("- Birth Year: " + student.getBirthYear());
                                    System.out.println("- Scholarship: " + student.getScholarship());
                                    System.out.println("- Program: " + student.getProgram());
                                    System.out.println("- Student ID: " + student.getStudentNumber());
                                    System.out.println("- Year: " + student.getYear());
                                    System.out.println("- Mode of Payment: " + student.getModeOfPayment());
                                    System.out.println("- Balance: Php " + student.getBalance());
                                    System.out.println();
                                    System.out.println("==================================================================================================================================================================================================================================================");
                                } else if (user instanceof Teacher) {
                                    System.out.println();
                                    Teacher teacher = (Teacher) user;
                                    System.out.println("- Username: " + teacher.getUsername());
                                    System.out.println("- Password: " + teacher.getPassword());
                                    System.out.println("- Full Name: " + teacher.getFirstName() + " " + teacher.getMiddleName() + " " + teacher.getLastName());
                                    System.out.println("- Contact Number: " + teacher.getContactNum());
                                    System.out.println("- Age: " + teacher.getAge());
                                    System.out.println("- Sex: " + teacher.getSex());
                                    System.out.println();
                                    System.out.println("==================================================================================================================================================================================================================================================");
                                }
                            }
                        } else {
                            System.out.println("                                                                                                   No student or teacher found with the given ID or last name.");
                        }
                    } else if (choice.equals("2")) {
                        // Exit the view users menu
                        System.out.println("==================================================================================================================================================================================================================================================");
                        viewingUsers = false;
                        System.out.println("                                                                                                       Returning to main menu...");
                        System.out.println("==================================================================================================================================================================================================================================================");
                    } else {
                        System.out.println("                                                                                                       Invalid choice. Please try again.");
                    }
                }
            }
        }
            
        // Teacher subclass
        class Teacher extends User {
            private String lastName;
            private String firstName;
            private String middleName;
            private String contactNum;
            private int age;
            private String sex;
            private ArrayList<String> teacherSchedule;
            private ArrayList<String> teacherAssignment;
            private HashMap<String, HashMap<Student, String>> assignmentSubmissions; // Assignment -> (Student -> Answer)
            private HashMap<String, HashMap<Student, String>> grades; // Assignment -> (Student -> Grade)
    
            public Teacher(String username, String password, String firstName, String lastName, String middleName, String contactNum, int age, String sex) {
                super(username, password);
                this.firstName = firstName;
                this.lastName = lastName;
                this.middleName = middleName;
                this.contactNum = contactNum;
                this.age = age;
                this.sex = sex;
                this.teacherSchedule = new ArrayList<>();
                this.teacherAssignment = new ArrayList<>();
                this.assignmentSubmissions = new HashMap<>();
                this.grades = new HashMap<>();
            }
    
            public String getLastName() { return lastName; }
            public String getFirstName() { return firstName; }
            public String getMiddleName() { return middleName; }
            public String getContactNum() { return contactNum; }
            public int getAge() { return age; }
            public String getSex() { return sex; }
            public ArrayList<String> getTeacherSchedule() { return teacherSchedule; }
            public ArrayList<String> getTeacherAssignment() { return teacherAssignment; }
    
            public void viewRegisteredUsers(ArrayList<User> users) {
                    System.out.println("Registered Users:");
                    System.out.println();
                for (User user : users) {
                    if (user instanceof Admin) {
                        System.out.println("- " + user.getUsername() + " (Teacher)");
                    } if (user instanceof Teacher) {
                        System.out.println("- " + user.getUsername() + " (Teacher)");
                    } else if (user instanceof Student) {
                        Student student = (Student) user;
                        System.out.println("- " + student.getFirstName() + " " + student.getLastName() + " (Student)");
                    }
                }
                
            }
            
            public void receiveSubmission(String assignmentTitle, Student student, String answer) {
            assignmentSubmissions.putIfAbsent(assignmentTitle, new HashMap<>());
            assignmentSubmissions.get(assignmentTitle).put(student, answer);
            }

            public HashMap<String, HashMap<Student, String>> getAssignmentSubmissions() {
            return assignmentSubmissions;
            }

            public void gradeAssignment(String assignmentTitle, Student student, String grade) {
            grades.putIfAbsent(assignmentTitle, new HashMap<>());
            grades.get(assignmentTitle).put(student, grade);
            }

            public HashMap<String, HashMap<Student, String>> getGrades() {
            return grades;
            }
    
            public void addTeacherSchedule(String schedule) {
                this.teacherSchedule.add(schedule);
            }
    
            public void addTeacherAssignment(String assignment) {
                this.teacherAssignment.add(assignment);
            }
        }
    
        // Student subclass
        class Student extends User {
            private String lastName;
            private String firstName;
            private String middleName;
            private String contactNum;
            private int age;
            private String sex;
            private String birthYear;
            private String scholarship;
            private String program;
            private int studentNumber;
            private int year;
            private String modeOfPayment;
            private ArrayList<String> notes;
            private ArrayList<String> problems; // List for storing reported issues
            private ArrayList<String> messages;
            private double balance;
            private ArrayList<String> paymentHistory;
            private ArrayList<String> studentSchedule;
            private ArrayList<String> studentAssignment;
            private HashMap<String, String> submittedAnswers;
    
            public Student(String username, String password, String lastName, String firstName, String middleName, String contactNum,
                        int age, String sex, String birthYear, String scholarship, String program, int studentNumber, int year,
                        String modeOfPayment, double balance) {
                super(username, password);
                this.lastName = lastName;
                this.firstName = firstName;
                this.middleName = middleName;
                this.contactNum = contactNum;
                this.age = age;
                this.sex = sex;
                this.birthYear = birthYear;
                this.scholarship = scholarship;
                this.program = program;
                this.studentNumber = studentNumber;
                this.year = year;
                this.modeOfPayment = modeOfPayment;
                this.notes = new ArrayList<>();
                this.problems = new ArrayList<>();
                this.messages = new ArrayList<>();
                this.paymentHistory = new ArrayList<>();
                this.balance = balance;
                this.studentSchedule = new ArrayList<>();
                this.studentAssignment = new ArrayList<>();
                this.submittedAnswers = new HashMap<>();
    
            }
    
            // Getters
            public String getLastName() { return lastName; }
            public String getFirstName() { return firstName; }
            public String getMiddleName() { return middleName; }
            public String getContactNum() { return contactNum; }
            public int getAge() { return age; }
            public String getSex() { return sex; }
            public String getBirthYear() { return birthYear; }
            public String getScholarship() { return scholarship; }
            public String getProgram() { return program; }
            public int getStudentNumber() { return studentNumber; }
            public int getYear() { return year; }
            public String getModeOfPayment() { return modeOfPayment; }
            public ArrayList<String> getNotes() { return notes; }
            public ArrayList<String> getProblems() { return problems; }
            public double getBalance() { return balance; }
            public ArrayList<String> getPaymentHistory() { return paymentHistory; }
            public void addMessage(String message) { messages.add(message); }
            public ArrayList<String> getMessages() { return messages; }
            public ArrayList<String> getStudentSchedule() { return studentSchedule; }
            public ArrayList<String> getStudentAssignment() { return studentAssignment; }
            public HashMap<String, String> getSubmittedAnswers() {return submittedAnswers;}
    
            // Setters
            public void setLastName(String lastName) { this.lastName = lastName; }
            public void setFirstName(String firstName) { this.firstName = firstName; }
            public void setMiddleName(String middleName) { this.middleName = middleName; }
            public void setAge(int age) { this.age = age; }
            public void setSex(String sex) { this.sex = sex; }
            public void setBirthYear(String birthYear) { this.birthYear = birthYear; }
            public void setScholarship(String scholarship) { this.scholarship = scholarship; }
            public void setProgram(String program) { this.program = program; }
            public void setStudentNumber(int studentNumber) { this.studentNumber = studentNumber; }
            public void setYear(int year) { this.year = year; }
            public void setModeOfPayment(String modeOfPayment) { this.modeOfPayment = modeOfPayment; }
            public void setBalance(double balance) { this.balance = balance; }
            public void addStudentSchedule(String studentSchedule) { this.studentSchedule.add(studentSchedule); }
            public void addStudentAssignment(String studentAssignment) { this.studentAssignment.add(studentAssignment); }

            
            public void addReportIssue(String issue) {
                problems.add(issue); // Add the issue to the problems list
            } 
            // Method to add a payment and update balance
            public void addPayment(double amount) {
                balance -= amount;
                paymentHistory.add("                                                                                                           Paid Php " + amount + " | Remaining balance: Php " + balance);
                System.out.println("==================================================================================================================================================================================================================================================");
            }
            public void submitAnswer(String assignmentTitle, String answer) {
                submittedAnswers.put(assignmentTitle, answer);
            }

            public void addNote(String note) {
                notes.add(note);
            }
        }
    
        class Assignment { //new class kay assignment hirap pag wala
            private String title;
            private String description;
            private ArrayList<Student> assignedStudents; // List of students assigned this assignment
        
            public Assignment(String title, String description) {
                this.title = title;
                this.description = description;
                this.assignedStudents = new ArrayList<>();
            }
    
            public String getTitle() {
                return title;
            }
        
            public void setTitle(String title) {
                this.title = title;
            }
        
            public String getDescription() {
                return description;
            }
        
            public void setDescription(String description) {
                this.description = description;
            }
        
            public ArrayList<Student> getAssignedStudents() {
                return assignedStudents;
            }
        
            // Add a student to the assignment
            public void assignStudent(Student student) {
                this.assignedStudents.add(student);
            }
        
            // Remove a student from the assignment (if needed)
            public void removeStudent(Student student) {
                this.assignedStudents.remove(student);
            }
        }
    
        // Entire program
        public class SchoolApp {
            private ArrayList<User> users;
            private Scanner scan;
            private HashMap<String, ArrayList<String>> subjects;
            private ArrayList<String> teacherSchedules; 
    
            public SchoolApp() {
                users = new ArrayList<>();
                scan = new Scanner(System.in);
                subjects = new HashMap<>();
                teacherSchedules = new ArrayList<>();
    
                ArrayList<String> schedule = new ArrayList<>();
                subjects.put("student", schedule);
    
                //pre-registed account para madali mapakita output
                users.add(new Admin("admin", "admin123"));
                users.add(new Student("aronedc", "arone", "Dela Cruz", "Mark", "M.", "123-456-7890", 20, "M", "2004", "No", "Bachelor of Science in Information Technology", 1234567890, 1, "Credit", 60000.00));
                users.add(new Student("pauluna", "paul", "Luna", "Paul", "B.", "123-456-7891", 21, "M", "2002", "Yes", "Bachelor of Science in Information Technology", 1234567891, 2, "Debit", 60000.00));
                users.add(new Student("ryahcar", "ryah", "Carino", "Ryah", "C.", "123-456-7892", 22, "M", "2001", "No", "Bachelor of Science in Computer Engineering", 1234567892, 3, "Credit", 60000.00));
                users.add(new Student("ryhelll", "ryhel", "Ewan", "Ryhel", "D.", "123-456-7893", 19, "F", "2004", "Yes", "Mathematics", 1234567893, 1, "Cash", 60000.00));
                users.add(new Student("a", "a", "Santos", "Jimmy", "E.", "123-456-7894", 23, "M", "1999", "No", "Biology", 1234567894, 4, "Debit", 60000.00));
                users.add(new Student("2", "2", "Brillantes", "Andrea", "F.", "123-456-7895", 21, "F", "2002", "Yes", "History", 1234567895, 2, "Credit", 60000.00));
                users.add(new Student("student7", "pass123", "Richard", "Gomez", "G.", "123-456-7896", 20, "M", "2003", "No", "Physics", 1234567896, 1, "Cash", 60000.00));
                users.add(new Student("student8", "pass123", "Racal", "Maris", "H.", "123-456-7897", 22, "F", "2001", "Yes", "Nursing", 1234567897, 3, "Debit", 60000.00));
                users.add(new Student("student9", "pass123", "Blanco", "Rico", "I.", "123-456-7898", 19, "M", "2004", "No", "Psychology", 1234567898, 1, "Credit", 60000.00));
                users.add(new Student("student10", "pass123", "Duterte", "Sara", "J.", "123-456-7899", 21, "F", "2002", "Yes", "Business Administration", 1234567899, 2, "Cash", 60000.00));
    
                // Adding 6 teacher accounts
                users.add(new Teacher("teacher1", "teach123", "Gia", "Dela Cruz", "A.", "123-456-7800", 22, "F"));
                users.add(new Teacher("teacher2", "teach123", "Ryan", "Nolasco", "B.", "123-456-7801", 22, "M"));
                users.add(new Teacher("teacher3", "teach123", "Mavel", "Brown", "C.", "123-456-7802", 22, "M"));
                users.add(new Teacher("teacher4", "teach123", "Jose Crisanto", "Austria", "D.", "123-456-7803", 22, "M"));
                users.add(new Teacher("teacher5", "teach123", "Julie", "Santos", "E.", "123-456-7804", 45, "F"));
                users.add(new Teacher("1", "1", "Vylette", "Paraiso", "F.", "123-456-7805", 34, "F"));
            }
    
            public void start() {
                boolean running = true;
    
                while (running) {
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("==                                                                                                                                                                                                                                              ==");
                    System.out.println("==                                                 ███████╗ ██████╗██╗  ██╗ ██████╗  ██████╗ ██╗         ███╗   ███╗ █████╗ ███╗   ██╗ █████╗  ██████╗ ███████╗███╗   ███╗███████╗███╗   ██╗████████╗                                           ==\r\n" + //
                                            "==                                                 ██╔════╝██╔════╝██║  ██║██╔═══██╗██╔═══██╗██║         ████╗ ████║██╔══██╗████╗  ██║██╔══██╗██╔════╝ ██╔════╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝                                           ==\r\n" + //
                                            "==                                                 ███████╗██║     ███████║██║   ██║██║   ██║██║         ██╔████╔██║███████║██╔██╗ ██║███████║██║  ███╗█████╗  ██╔████╔██║█████╗  ██╔██╗ ██║   ██║                                              ==\r\n" + //
                                            "==                                                 ╚════██║██║     ██╔══██║██║   ██║██║   ██║██║         ██║╚██╔╝██║██╔══██║██║╚██╗██║██╔══██║██║   ██║██╔══╝  ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║                                              ==\r\n" + //
                                            "==                                                 ███████║╚██████╗██║  ██║╚██████╔╝╚██████╔╝███████╗    ██║ ╚═╝ ██║██║  ██║██║ ╚████║██║  ██║╚██████╔╝███████╗██║ ╚═╝ ██║███████╗██║ ╚████║   ██║                                              ==\r\n" + //
                                            "==                                                 ╚══════╝ ╚═════╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝                                              ==");
                                            System.out.println("==                                                                                                                                                                                                                                              ==");
                                            System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                           [1] Login as Admin");
                    System.out.println("                                                                                                           [2] Login as Teacher");
                    System.out.println("                                                                                                           [3] Login as Student");
                    System.out.println("                                                                                                           [4] Create Account");
                    System.out.println("                                                                                                           [5] Exit Program");
                    System.out.println();
                    System.out.print("                                                                                                           Enter choice: ");
                    String choice = scan.nextLine();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    if (choice.equals("1")) {
                        login(Admin.class);
                    } else if (choice.equals("2")) {
                        login(Teacher.class);
                    } else if (choice.equals("3")) {
                        login(Student.class);
                    } else if (choice.equals("4")) {
                        createAccount();
                    } else if (choice.equals("5")) {
                        running = false;
                        System.out.println("                                                                                                           Exiting program...");
                    } else {
                        System.out.println("                                                                                                           Invalid choice.");
                        System.out.println("==================================================================================================================================================================================================================================================");
    
                    }
                }
            }
    
            //will proceed sa if statement depending kung ano role
            private void login(Class<?> roleClass) {
                System.out.print("                                                                                                           Enter username: ");
                    String username = scan.nextLine();
                System.out.print("                                                                                                           Enter password: ");
                    String password = scan.nextLine();
    
                for (User user : users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        if (roleClass.isInstance(user)) {
                            if (user instanceof Admin) {
                                System.out.println("==================================================================================================================================================================================================================================================");
                                System.out.println();
                                System.out.println("                                                                                                           Admin login successful.");
                                System.out.println();
                                System.out.println("==================================================================================================================================================================================================================================================");
                                adminMenu((Admin) user);;
                            } else if (user instanceof Student) {
                                System.out.println("==================================================================================================================================================================================================================================================");
                                System.out.println();  
                                System.out.println("                                                                                                           Student login successful.");
                                System.out.println();
                                System.out.println("==================================================================================================================================================================================================================================================");

                                studentMenu((Student) user);
                            } else if (user instanceof Teacher) {
                                System.out.println("==================================================================================================================================================================================================================================================");
                                System.out.println();
                                System.out.println("                                                                                                           Teacher logged in successful.");
                                System.out.println();
                                System.out.println("==================================================================================================================================================================================================================================================");
                                teacherMenu((Teacher) user);
                            }
                            return;
                        }
                    }
                }
                System.out.println("                                                                                                               Invalid credentials.");
            }
    
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - MENUS - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \\ 
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ADMIN MENU - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \\ 
            private void adminMenu(Admin admin) {
                boolean loggedIn = true;
                
                while (loggedIn) {
                    System.out.println("\n                                                                                                           = = = = Admin Menu = = = =");
                    System.out.println("                                                                                                           1. View Registered Users");
                    System.out.println("                                                                                                           2. Change user Password");
                    System.out.println("                                                                                                           3. Edit Student Account Info");
                    System.out.println("                                                                                                           4. View Reported Issues");
                    System.out.println("                                                                                                           5. Delete User Account");
                    System.out.println("                                                                                                           6. Send a message/Announcements");
                    System.out.println("                                                                                                           7. Log Out");
                    System.out.println();
                    System.out.print("                                                                                                           Enter choice: ");
                    String choice = scan.nextLine();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println();
                
                        if (choice.equals("1")) {
                            admin.viewRegisteredUsers(users);
                        } else if (choice.equals("2")) {
                            changePassword(admin);
                        } else if (choice.equals("3")) {
                            editStudentInfo();
                        } else if (choice.equals("4")) {
                            viewReportedIssues();
                        } else if (choice.equals("5")) {
                            deleteUserAccount();
                        } else if (choice.equals("6")) {
                            sendMessageMenu();
                        } else if (choice.equals("7")) {
                            loggedIn = false;
                            System.out.println("                                                                                                           Logging out...");
                        } else {
                            System.out.println("                                                                                                           Invalid choice.");
                        }
                    }
                }
    
                private void sendMessageMenu() {
                    boolean inSendMessageMenu = true;
                    while (inSendMessageMenu) {
                        System.out.println("                                                                                                         = = = = Send Message Menu = = = =");
                        System.out.println("                                                                                                           1. Send a message to a student");
                        System.out.println("                                                                                                           2. Public announcement");
                        System.out.println("                                                                                                           3. Go back to menu");
                        System.out.print("                                                                                                           Enter choice: ");
                        String choice = scan.nextLine();
                
                        if (choice.equals("1")) {
                            sendMessageToStudent();
                        } else if (choice.equals("2")) {
                            sendPublicAnnouncement();
                        } else if (choice.equals("3")) {
                            inSendMessageMenu = false;
                            System.out.println("==================================================================================================================================================================================================================================================");
                            System.out.println("                                                                                                       Returning to the previous menu...");
                            System.out.println("==================================================================================================================================================================================================================================================");
                        } else {
                            System.out.println("                                                                                                   Invalid choice. Please try again.");
                        }
                    }
                }
    
                private void sendMessageToStudent() {
                    System.out.print("==================================================================================================================================================================================================================================================");
                    System.out.print("                                                                                                           \nEnter the username of the student to send a message: ");
                    String targetUsername = scan.nextLine();
                
                    Student targetStudent = null;
                    for (User user : users) {
                        if (user instanceof Student && user.getUsername().equals(targetUsername)) {
                            targetStudent = (Student) user;
                            break;
                        }
                    }
                
                    if (targetStudent != null) {
                        System.out.println("                                                                                                     \nStudent found: " + targetStudent.getFirstName() + " " + targetStudent.getLastName());
                        System.out.print("Enter your message: ");
                        String message = scan.nextLine();
                
                        targetStudent.addMessage(message);
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println();
                        System.out.println("                                                                                                       Message sent successfully to " + targetStudent.getFirstName() + "!");
                        System.out.println();
                        System.out.println("==================================================================================================================================================================================================================================================");
                    } else {
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                               Student not found. Please check the username and try again.");
                        System.out.println("==================================================================================================================================================================================================================================================");
                    }
                }
    
                private void sendPublicAnnouncement() {
                    System.out.print("==================================================================================================================================================================================================================================================");
                    System.out.print("                                                                                                           \nEnter the announcement message: ");
                    String announcement = scan.nextLine();
                
                    for (User user : users) {
                        if (user instanceof Student) {
                            ((Student) user).addMessage("[Public Announcement] " + announcement);
                        }
                    }
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println();
                    System.out.println("                                                                                                 Public announcement sent successfully to all students!");
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                }
    
                private void deleteUserAccount() {
                    System.out.println("                                                                                                         === Delete User Account ===");
                    System.out.println("                                                                                                           1. Delete Student Account");
                    System.out.println("                                                                                                           2. Delete Teacher's Account");
                    System.out.println("                                                                                                           3. Go Back");
                    System.out.print("                                                                                                           Enter choice: ");
                    String choice = scan.nextLine();
                
                    if (choice.equals("1")) {
                        deleteAccount("Student");
                    } else if (choice.equals("2")) {
                        deleteAccount("Teacher");
                    } else if (choice.equals("3")) {
                        System.out.println();
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                                       Going back to the previous menu...");
                        System.out.println("==================================================================================================================================================================================================================================================");
                    } else {
                        System.out.println("                                                                                                       Invalid choice. Please try again.");
                    }
                }
                private void deleteAccount(String accountType) {
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("Warning: Deleting an account is irreversible! Once deleted, all user data associated with this account will be permanently lost. Are you sure you want to proceed?");
                    System.out.println("                                                                                                         \n=== Delete " + accountType + " Account ===");
                    System.out.println("1. I understand, proceed to delete an account");
                    System.out.println("2. Go back");
                    System.out.print("Enter choice: ");
                    String choice = scan.nextLine();
                
                    if (choice.equals("1")) {
                        if (accountType.equals("Student")) {
                            deleteStudentAccount();
                        } else if (accountType.equals("Teacher")) {
                            deleteTeacherAccount();
                        }
                    } else if (choice.equals("2")) {
                        System.out.println("                                                                                                       Going back to the previous menu...");
                    } else {
                        System.out.println("                                                                                                       Invalid choice. Please try again.");
                    }
                }
                
                private void deleteStudentAccount() {
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                         \n=== Delete Student Account ===");
                    
                    // List all students with their username and password
                    for (User user : users) {
                        if (user instanceof Student) {
                            Student student = (Student) user;
                            System.out.println("Username: " + student.getUsername() + " | Password: " + student.getPassword());
                        }
                    }
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.print("Enter the username of the student to delete: ");
                        String username = scan.nextLine();
                
                    System.out.print("Enter the password of the account to delete: ");
                        String password = scan.nextLine();
                    
                    // Check if the account exists and verify password
                    boolean accountFound = false;
                    for (User user : users) {
                        if (user instanceof Student) {
                            Student student = (Student) user;
                            if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                                users.remove(student); // Delete student account
                                accountFound = true;
                                System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                        Student account deleted successfully.");
                    System.out.println("==================================================================================================================================================================================================================================================");
                                break;
                            }
                        }
                    }
                    
                    if (!accountFound) {
                    System.out.println("                                                                                                           Account not found or incorrect credentials.");
                    }
                }
    
                private void deleteTeacherAccount() {
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                         \n=== Delete Teacher Account ===");
                    
                    // List all teachers with their username and password
                    for (User user : users) {
                        if (user instanceof Teacher) {
                            Teacher teacher = (Teacher) user;
                            System.out.println("Username: " + teacher.getUsername() + " | Password: " + teacher.getPassword());
                        }
                    }
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.print("                                                                                                           \nEnter the username of the teacher to delete: ");
                    String username = scan.nextLine();
                
                    System.out.print("Enter the password of the account to delete: ");
                    String password = scan.nextLine();
                    
                    // Check if the account exists and verify password
                    boolean accountFound = false;
                    for (User user : users) {
                        if (user instanceof Teacher) {
                            Teacher teacher = (Teacher) user;
                            if (teacher.getUsername().equals(username) && teacher.getPassword().equals(password)) {
                                users.remove(teacher); // Delete teacher account
                                accountFound = true;
                                System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                           Teacher account deleted successfully.");
                    System.out.println("==================================================================================================================================================================================================================================================");
                                break;
                            }
                        }
                    }
                    
                    if (!accountFound) {
                    System.out.println("                                                                                                           Account not found or incorrect credentials.");
                    }
                }
    
            private void viewReportedIssues() {
                System.out.println("=== Reported Issues ===");
                boolean foundIssues = false;
                for (User user : users) {
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        if (!student.getProblems().isEmpty()) {
                            foundIssues = true;
                            System.out.println();
                            System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
                    System.out.println("Reported Issues:");
                            for (String issue : student.getProblems()) {
                                System.out.println(" - " + issue);
                            }
                            System.out.println();
                            System.out.println("==================================================================================================================================================================================================================================================");
                        }
                    }
                }
                
                if (!foundIssues) {
                    System.out.println("                                                                                                           No reported issues found.");
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                }
            }
                
            private void changePassword(Admin admin) {
                boolean isChangePassword = true;
                while (isChangePassword) {
                    System.out.println("=== Change User Password ===");
                    System.out.println("> Make sure to encode the required username <");
                    System.out.println("> Double check before submitting the request to avoid any conflict <");
                    System.out.println("> Click '1' to go back on Menu <");
                    System.out.println("");
                        System.out.print("Enter the username of the account you want to change the password for: ");
                    String targetUsername = scan.nextLine();
    
    
                    if(targetUsername.equals("1")) {
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                                           Going back to Menu . . .");
                        System.out.println("==================================================================================================================================================================================================================================================");
                        isChangePassword = false;
                    } else {

                        // Search for the user based on the entered username
                        User targetUser = null;
                        for (User user : users) {
                            if (user.getUsername().equals(targetUsername)) {
                                targetUser = user;
                                break;
                            }
                        }
                    
                        // If a user is found, proceed to change the password
                        if (targetUser != null) {
                            System.out.println("==================================================================================================================================================================================================================================================");
                            System.out.println("Account found!");
                            System.out.println();
                            System.out.print("Enter new password: ");
                            String newPassword = scan.nextLine();
                            System.out.print("Confirm new password: ");
                            String confirmPassword = scan.nextLine();
                            System.out.println("==================================================================================================================================================================================================================================================");
                            if (newPassword.equals(confirmPassword)) {
                        
                                // Update the password for the target user
                                targetUser.setPassword(newPassword);
                            System.out.println("                                                                                                           Password successfully changed for " + targetUsername);
                        
                            } else {
                            System.out.println("                                                                                                           Passwords do not match. Please try again.");
                            }System.out.println("==================================================================================================================================================================================================================================================");
                        } else {
                            System.out.println("==================================================================================================================================================================================================================================================");
                            System.out.println("                                                                                                       User not found. Please check the username and try again.");
                        System.out.println("==================================================================================================================================================================================================================================================");
                        }
                    }
                }

            }
    
            private void editStudentInfo() {
                    System.out.print("Enter the username of the student account you want to edit: ");
                    String targetUsername = scan.nextLine();
                
                    Student targetStudent = null;
                    for (User user : users) {
                        if (user instanceof Student && user.getUsername().equals(targetUsername)) {
                            targetStudent = (Student) user;
                            break;
                    }
                }
            
                if (targetStudent != null) {
                    System.out.println("\nEditing information for student: " + targetStudent.getFirstName() + " " + targetStudent.getLastName());
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.print("Enter new last name (current: " + targetStudent.getLastName() + "): ");
                    String newLastName = scan.nextLine();
                    if (!newLastName.isEmpty()) {
                        targetStudent.setLastName(newLastName);
                    }
            
                    System.out.print("Enter new first name (current: " + targetStudent.getFirstName() + "): ");
                    String newFirstName = scan.nextLine();
                    if (!newFirstName.isEmpty()) {
                        targetStudent.setFirstName(newFirstName);
                    }
            
                    System.out.print("Enter new middle name (current: " + targetStudent.getMiddleName() + "): ");
                    String newMiddleName = scan.nextLine();
                    if (!newMiddleName.isEmpty()) {
                        targetStudent.setMiddleName(newMiddleName);
                    }
            
                    while (true) {
                        System.out.print("Enter new age (current: " + targetStudent.getAge() + "): ");
                        String ageInput = scan.nextLine();
                    
                        if (!ageInput.isEmpty()) {
                            try {
                                int newAge = Integer.parseInt(ageInput);
                    
                                if (newAge > 0) { 
                                    targetStudent.setAge(newAge);
                                    System.out.println("Age updated successfully to: " + newAge);
                                    break;
                                } else {
                                    System.out.println("Age must be a positive number.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid age input. Age must be a number.");
                            }
                        } else {
                            System.out.println("Age input cannot be empty. Please enter a valid number.");
                            System.out.println();
                        }
                    }
                    
            
                    while (true) {
                        System.out.print("Enter new sex (current: " + targetStudent.getSex() + ") [M/F]: ");
                        String newSex = scan.nextLine();
                    
                        if (newSex.equalsIgnoreCase("M") || newSex.equalsIgnoreCase("F")) {
                            targetStudent.setSex(newSex);
                            System.out.println("Sex updated successfully to: " + newSex);
                            break; // Exit the loop once valid input is provided
                        } else {
                            System.out.println("Invalid input. Please enter 'M' for Male or 'F' for Female.");
                            System.out.println();
                        }
                    }
                    
            
                    System.out.print("Enter new birth year (current: " + targetStudent.getBirthYear() + "): ");
                    String newBirthYear = scan.nextLine();
                    if (!newBirthYear.isEmpty()) {
                        targetStudent.setBirthYear(newBirthYear);
                    }
            
                    while (true) {
                        System.out.print("Enter new scholarship (current: " + targetStudent.getScholarship() + ") [yes/no]: ");
                        String newScholarship = scan.nextLine();
                    
                        if (newScholarship.equalsIgnoreCase("yes") || newScholarship.equalsIgnoreCase("no")) {
                            targetStudent.setScholarship(newScholarship);
                            System.out.println("Scholarship updated successfully to: " + newScholarship);
                            break; 
                        } else {
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                            System.out.println();
                        }
                    }
                    
            
                    System.out.print("Enter new program (current: " + targetStudent.getProgram() + "): ");
                    String newProgram = scan.nextLine();
                    if (!newProgram.isEmpty()) {
                        targetStudent.setProgram(newProgram);
                    }
            
                    System.out.print("Enter new student number (current: " + targetStudent.getStudentNumber() + "): ");
                    String studentNumberInput = scan.nextLine();
                    if (!studentNumberInput.isEmpty()) {
                        try {
                            int newStudentNumber = Integer.parseInt(studentNumberInput);
                            targetStudent.setStudentNumber(newStudentNumber);
                        } catch (NumberFormatException e) {
                    System.out.println("Invalid student number input. Student number must be a number.");
                        }
                    }
            
                    System.out.print("Enter new year (current: " + targetStudent.getYear() + "): ");
                    String yearInput = scan.nextLine();
                    if (!yearInput.isEmpty()) {
                        try {
                            int newYear = Integer.parseInt(yearInput);
                            targetStudent.setYear(newYear);
                        } catch (NumberFormatException e) {
                    System.out.println("Invalid year input. Year must be a number.");
                        }
                    }
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                           Student account updated successfully.");
                    System.out.println("==================================================================================================================================================================================================================================================");
                } else {
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                               Student not found. Please check the username and try again.");
                    System.out.println("==================================================================================================================================================================================================================================================");
            }   
        }
        
        private void studentMenu(Student student) { 
            boolean loggedIn = true;
            
            while (loggedIn) {
                System.out.println("\n                                                                                                            = = = = Student Menu = = = =");
                System.out.println();
                System.out.println("                                                                                                           1. View Subjects and Schedule");
                System.out.println("                                                                                                           2. View Assignment");
                System.out.println("                                                                                                           3. NotePad");
                System.out.println("                                                                                                           4. Check Remaining Balance");
                System.out.println("                                                                                                           5. View Payment History");
                System.out.println("                                                                                                           6. Account Information");
                System.out.println("                                                                                                           7. Report a Problem");
                System.out.println("                                                                                                           8. Check Messages and Announcements");
                System.out.println("                                                                                                           9. Deposit to Pay Balance");
                System.out.println("                                                                                                           10. Log Out");
                System.out.println();
                System.out.print("                                                                                                           Enter choice: ");
                String choice = scan.nextLine();
            
                if (choice.equals("1")) {
                    viewSubjects();
                } else if (choice.equals("2")) {
                    viewAssignments(student);
                } else if (choice.equals("3")) {
                    useNotepad(student);
                } else if (choice.equals("4")) {
                    boolean inBalanceMenu = true;
                    while (inBalanceMenu) {
                    System.out.println("==================================================================================================================================================================================================================================================");
                
                System.out.println("                                                                                                           = = = = Remaining Balance = = = =");
                System.out.println("                                                                                                           Your current remaining balance is: Php " + student.getBalance());
                System.out.println("                                                                                                           [1] Pay the remaining balance");
                System.out.println("                                                                                                           [2] Back to menu");
                System.out.print("                                                                                                           Enter your choice: ");
                        String balanceChoice = scan.nextLine();
                
                        if (balanceChoice.equals("1")) {
                            // Option to pay the remaining balance
                            System.out.print("                                                                                                           Enter the amount to pay: Php ");
                            try {
                                double amount = scan.nextDouble();
                                if (amount <= 0) {
                                    System.out.println("                                                                                                           Invalid amount. Please enter a positive value.");
                                } else if (amount > student.getBalance()) {
                                    System.out.println("                                                                                                           The amount exceeds your remaining balance. Please enter a valid amount.");
                                } else {
                                    student.addPayment(amount); // Deduct the amount from the balance
                                    System.out.println("Payment of Php " + amount + " has been successfully processed.");
                                    System.out.println("Your updated remaining balance is: Php " + student.getBalance());
                                }
                            } catch (Exception e) {
                                System.out.println("                                                                                                           Invalid input. Please enter a numeric value.");
                                scan.next(); // Clear the invalid input
                            }
                        } else if (balanceChoice.equals("2")) {
                            // Back to main menu
                            inBalanceMenu = false;
                            System.out.println("==================================================================================================================================================================================================================================================");
                                    System.out.println("                                                                                                           Returning to the main menu...");
                                    System.out.println("==================================================================================================================================================================================================================================================");
                        } else {
                            System.out.println("                                                                                                           Invalid choice. Please select a valid option.");
                        }
                    }

                } else if (choice.equals("5")) {
                    System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                                           Payment History:");
                        List<String> paymentHistory = student.getPaymentHistory();
                        if (paymentHistory.isEmpty()) {
                            System.out.println("There's no record of any Transaction.");
                        } else {
                            for (String payment : paymentHistory) {
                                System.out.println(payment);
                                System.out.print("==================================================================================================================================================================================================================================================");
                        }
                    }
                } else if (choice.equals("6")) {
                    boolean viewingAccountInfo = true;
                    while (viewingAccountInfo) {
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                                           = = = =  Account Info = = = =");
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                                           Student Number: " + student.getStudentNumber());
                        System.out.println("                                                                                                           Full Name: " + student.getLastName() + ", " + student.getFirstName() + " " + student.getMiddleName());
                        System.out.println("                                                                                                           Age: " + student.getAge());
                        System.out.println("                                                                                                           Sex: " + student.getSex());
                        System.out.println("                                                                                                           Birth Year: " + student.getBirthYear());
                        System.out.println("                                                                                                           Contact Number: " + student.getContactNum());
                        System.out.println("                                                                                                           Scholarship: " + (student.getScholarship() != null && !student.getScholarship().isEmpty() ? student.getScholarship() : "None"));
                        System.out.println("                                                                                                           Program: " + student.getProgram());
                        System.out.println("                                                                                                           Year Level: " + student.getYear());
                        System.out.println("                                                                                                           Mode of Payment: " + student.getModeOfPayment());
                        System.out.println("                                                                                                           Remaining Balance: Php " + student.getBalance());
                                System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.print("                                                                                                           Press '1' to go back: ");
                        String backChoice = scan.nextLine();
                        if (backChoice.equals("1")) {
                            System.out.println("==================================================================================================================================================================================================================================================");
                            viewingAccountInfo = false;
                        } else {
                            System.out.println("                                                                                                           Invalid input. Please press '1' to go back.");
                            }
                        }    
                } else if (choice.equals("7")) {
                    // Report a Problem Sub-Menu
                    boolean inReportMenu = true;
                    while (inReportMenu) {
                    System.out.print("==================================================================================================================================================================================================================================================");
                        System.out.println("\n                                                                                                           = = = = Report a Problem = = = =");
                        System.out.println("                                                                                                           1. Write a New Problem");
                        System.out.println("                                                                                                           2. View History of Reported Problems");
                        System.out.println("                                                                                                           3. Back to Main Menu");
                        System.out.println();
                        System.out.print("                                                                                                           Enter choice: ");
                            String reportChoice = scan.nextLine();
                    
                            if (reportChoice.equals("1")) {
                                System.out.println("==================================================================================================================================================================================================================================================");
                                System.out.println();
                                System.out.print("Please describe the issue you want to report: ");
                                    String problemDescription = scan.nextLine(); 
                                System.out.println("==================================================================================================================================================================================================================================================");
                                    student.addReportIssue(problemDescription); 
                                System.out.println("                                                                                                           Your issue has been reported.");
                            } else if (reportChoice.equals("2")) {
                                System.out.println("==================================================================================================================================================================================================================================================");
                                System.out.println("Your Reported Problems:");
                                    if (student.getProblems().isEmpty()) {
                                System.out.println("                                                                                                           No problems reported yet.");
                                    } else {
                                        for (String problem : student.getProblems()) {
                                    System.out.println("- " + problem);
                                    }
                                }
                            } else if (reportChoice.equals("3")) {
                                System.out.println("==================================================================================================================================================================================================================================================");
     
                                    inReportMenu = false;
                                System.out.println("                                                                                                           Returning to the main menu...");
                                System.out.println("==================================================================================================================================================================================================================================================");
                            } else {
                        System.out.println("                                                                                                           Invalid choice.");
                        }
                    }
                } else if (choice.equals("8")) {
                    checkMessages(student);

                } else if (choice.equals("9")) {
                    // Deposit to Pay Balance
                    paymentMenu(student);
                    
                } else if (choice.equals("10")) {
                System.out.println("                                                                                                           Logging out . . .");
                    loggedIn = false;
    
                } else {
                System.out.println("                                                                                                           Invalid choice.");                  
                
                }
            }
        }

        public void paymentMenu(Student student) {
            boolean isPayment = true;
            while (isPayment) {
                
            System.out.println("==================================================================================================================================================================================================================================================");
            System.out.println("                                                                                                           = = = = Deposit to Pay Balance = = = =");
            System.out.print("                                                                                                           Enter the amount to deposit: Php ");
                try {
                    double amount = scan.nextDouble();
                    scan.nextLine();
                    if (amount <= 0) {
            System.out.println("                                                                                                           Invalid amount. Please enter a positive value.");
                    } else {
                        student.addPayment(amount);
                        System.out.println();
            System.out.println("                                                                                          Payment of Php " + amount + " has been processed. Remaining balance: Php " + student.getBalance());
            System.out.println();
            System.out.println("==================================================================================================================================================================================================================================================");
            isPayment = false;
                    }
                } catch (Exception e) {
            System.out.println("                                                                                                           Invalid input. Please enter a numeric value.");
                    scan.next(); // Clear the invalid input
                }
            }
        }

        private void submitAssignment(Student student) {
                System.out.println("                                                                                                         \n=== Submit Assignment ===");
            ArrayList<String> assignments = student.getStudentAssignment();
        
            if (assignments.isEmpty()) {
                System.out.println("                                                                                                           No assignments to submit.");
                return;
            }
            
                System.out.println("                                                                                                           Your assignments:");
            for (int i = 0; i < assignments.size(); i++) {
                System.out.println("[ " + (i + 1) + " ] " + assignments.get(i));
            }
            
                System.out.println();
                System.out.println("                                                                                                           Enter 'B' to go back to Menu");
                System.out.print("                                                                                                             Select an assignment to submit: ");
            String input = scan.nextLine(); 
            
            if (input.equalsIgnoreCase("B")) { // Check if the user wants to go back
                System.out.println("                                                                                                           Returning to the main menu...");
                return; // Exit the current method and go back to the main menu
            }
            
            try {
                int choice = Integer.parseInt(input); // Convert input to an integer
                if (choice > 0 && choice <= assignments.size()) {
                    String selectedAssignment = assignments.get(choice - 1);
                System.out.print("                                                                                                             Enter your answer: ");
                    String answer = scan.nextLine();
                    student.submitAnswer(selectedAssignment, answer);
                    boolean teacherFound = false;
                    for (User user : users) {
                        if (user instanceof Teacher) {
                            Teacher teacher = (Teacher) user;
            
                            // Check if the teacher has the assignment
                            if (teacher.getTeacherAssignment().contains(selectedAssignment)) {
                                teacher.receiveSubmission(selectedAssignment, student, answer);
                                teacherFound = true;
                                break;
                            }
                        }
                    }
            
                    if (teacherFound) {
                        System.out.println("                                                                                                           Assignment submitted successfully!");
                    } else {
                        System.out.println("                                                                                                           No teacher found for this assignment.");
                    }
                } else {
                System.out.println("                                                                                                           Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("                                                                                                           Invalid input. Please enter a number or 'B' to go back.");
            }
        }       

        private void checkMessages(Student student) {
            System.out.println("==================================================================================================================================================================================================================================================");
                System.out.println("                                                                                                           = = = = Your Messages = = = =");
            if (student.getMessages().isEmpty()) {
                System.out.println("                                                                                                           You have no new messages.");
                System.out.println("==================================================================================================================================================================================================================================================");
            } else {
                for (String message : student.getMessages()) {
                    for (User user : users) {
                        if (user instanceof Admin) {
                System.out.println("From Administrator/s: " + message);
                            break;
                        } else if (user instanceof Teacher) {
                System.out.println("From Adviser: " + message);
                            break;
                        } 
                    } 
                }
            }
        }
        
        private void teacherMenu(Teacher teacher) { // - - - - - - - - - - - - - - - - - - - - -  TEACHER MENU - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - \\
            boolean loggedIn = true;
        
            while (loggedIn) {
                System.out.println("\n                                                                                                         = = = = Teacher Menu = = = =");
                System.out.println("                                                                                                           1. View Schedule");
                System.out.println("                                                                                                           2. View Assignments");
                System.out.println("                                                                                                           3. Attendance List");
                System.out.println("                                                                                                           4. Send a message and Announcement");
                System.out.println("                                                                                                           5. Log Out");
                System.out.print("                                                                                                           Enter choice: ");
                String choice = scan.nextLine();
        
                if (choice.equals("1")) {
                    inSchedule(teacher);
                } else if (choice.equals("2")) {
                    teacherViewAssignment(teacher);
                } else if (choice.equals("3")) {
                    teacherAttendance(teacher);
                } else if (choice.equals("4")) {
                    sendMessageMenu();
                } else if (choice.equals("5")) {
                System.out.println("                                                                                                           Logging out. . . .");
                    loggedIn = false;
                } else {
                System.out.println("                                                                                                           Invalid input.");
                }
            }
        }

        private void teacherViewAssignment(Teacher teacher) {
            boolean isAssignmentRunning = true;
        
            while (isAssignmentRunning) {
                System.out.println();
                System.out.println("==================================================================================================================================================================================================================================================");
                viewAssignments(teacher); // Display the list of assignments
                System.out.println("                                                                                                           [1] Add Assignment");
                System.out.println("                                                                                                           [2] Grade Assignment");
                System.out.println("                                                                                                           [3] Back to Menu");
                System.out.println();
                System.out.print("                                                                                                             Enter choice: ");
                String choice = scan.nextLine();
        
                if (choice.equals("1")) {
                    giveAssignment(teacher);
                } else if (choice.equals("2")) {
                    gradeSubmissions(teacher);
                } else if (choice.equals("3")) {
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println();
                    System.out.println("                                                                                                          Going back to Menu . . .");
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    isAssignmentRunning = false;
                } else {
                System.out.println("                                                                                                          Invalid Input");
                }
            }
        }

        private void teacherAttendance(Teacher teacher) {
            boolean isAttendanceRunning = true;
            ArrayList<HashMap<String, Object>> attendanceRecords = new ArrayList<>();
        
            while (isAttendanceRunning) {
                System.out.println();
                System.out.println("==================================================================================================================================================================================================================================================");
                System.out.println();
                System.out.println("                                                                                                         === Attendance Menu ===");
                System.out.println("                                                                                                          [1] Add New Attendance List");
                System.out.println("                                                                                                          [2] Show Recorded Attendance List");
                System.out.println("                                                                                                          [3] Go Back to Menu");
                System.out.print("                                                                                                            Enter choice: ");
                String choice = scan.nextLine();
        
                if (choice.equals("1")) {
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    // Add New Attendance
                    System.out.println("                                                                                                             ATTENDANCE LIST INFORMATION");
                    HashMap<String, Object> newAttendance = new HashMap<>();
        
                    String attendanceDate = "";
                    boolean isDateValid = false;
                    
                    while (!isDateValid) {
                        System.out.print("                                                                                                             Enter Date (e.g., MM-DD-YYYY): ");
                        String dateInput = scan.nextLine();
                    
                        // Validate format using regex
                        if (dateInput.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            String[] dateParts = dateInput.split("-");
                    
                            try {
                                int month = Integer.parseInt(dateParts[0]);
                                int day = Integer.parseInt(dateParts[1]);
                                int year = Integer.parseInt(dateParts[2]);
                    
                                // Validate month range
                                if (month >= 1 && month <= 12) {
                                    // Validate day based on the month and year
                                    if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day >= 1 && day <= 31) {
                                        attendanceDate = dateInput;
                                        newAttendance.put("Date", attendanceDate);
                                        System.out.println("                                                                                                             Attendance Date recorded: " + attendanceDate);
                                        isDateValid = true;
                                    } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30) {
                                        attendanceDate = dateInput;
                                        newAttendance.put("Date", attendanceDate);
                                        System.out.println("                                                                                                             Attendance Date recorded: " + attendanceDate);
                                        isDateValid = true;
                                    } else if (month == 2) {
                                        // Check for leap year
                                        boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                                        if ((isLeapYear && day >= 1 && day <= 29) || (!isLeapYear && day >= 1 && day <= 28)) {
                                            attendanceDate = dateInput;
                                            newAttendance.put("Date", attendanceDate);
                                            System.out.println("                                                                                                             Attendance Date recorded: " + attendanceDate);
                                            isDateValid = true;
                                        } else {
                                            System.out.println("                                                                                                             Invalid date. February must have 28 or 29 days depending on the year.");
                                        }
                                    } else {
                                        System.out.println("                                                                                                             Invalid date. The day is out of range for the specified month.");
                                    }
                                } else {
                                    System.out.println("                                                                                                             Invalid date. Month must be 1–12.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("                                                                                                             Invalid input. Please enter numeric values in MM-DD-YYYY format.");
                            }
                        } else {
                            System.out.println("                                                                                                             Invalid format. Please enter the date in MM-DD-YYYY format.");
                            System.out.println();
                        }
                    }
                    
                    System.out.println();
                    System.out.print("                                                                                                             Enter Subject: ");
                        String subject = scan.nextLine();
                        newAttendance.put("Subject", subject);
                    
                    System.out.println();
                    System.out.print("                                                                                                             Enter Number of Students: ");
                        int numberOfStudents = Integer.parseInt(scan.nextLine());
                        ArrayList<String> students = new ArrayList<>();
                        System.out.println();
                        for (int i = 0; i < numberOfStudents; i++) {
                            System.out.print("                                                                                                             Enter Full Name of Student " + (i + 1) + ": ");
                            String studentName = scan.nextLine();
                            students.add(studentName);
                        }
                        newAttendance.put("Students", students);
            
                        attendanceRecords.add(newAttendance);
                    System.out.println("                                                                                                           Attendance record added successfully!");
        
                } else if (choice.equals("2")) {
                    // Show Recorded Attendance List
                    if (attendanceRecords.isEmpty()) {
                        System.out.println();
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println();
                        System.out.println("                                                                                                           No attendance records found.");
                       
                    } else {
                        System.out.println();
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("                                                                                                         === Recorded Attendance ===");
                                for (int i = 0; i < attendanceRecords.size(); i++) {
                                    HashMap<String, Object> record = attendanceRecords.get(i);
                                    System.out.println("                                                                                                         Record #" + (i + 1));
                                    System.out.println("                                                                                                           Date: " + record.get("Date"));
                                    System.out.println("                                                                                                           Subject: " + record.get("Subject"));
                                    System.out.println("                                                                                                           Students:");
                                    
                                    ArrayList<String> students = (ArrayList<String>) record.get("Students");
                                    for (String student : students) {
                                        System.out.println("                                                                                                           - " + student);
                                }
                            }
                        }
        
                } else if (choice.equals("3")) {
                    // Go Back to Menu
                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                           Returning to main menu...");
                    System.out.println("==================================================================================================================================================================================================================================================");
                    isAttendanceRunning = false;
            
                } else {
                System.out.println("                                                                                                           Invalid input. Please try again.");
                }
            }
        }

        private void giveAssignment(Teacher teacher) {
            boolean assignmentOptions = true;
            System.out.println();
            System.out.println("==================================================================================================================================================================================================================================================");
                System.out.print("                                                                                             Subject: ");
                    String subject = scan.nextLine();
                System.out.print("                                                                                             Schoolwork category(Quiz, Activity, Task Performance): ");
                    String activity = scan.nextLine();


                boolean isSubValid = false;
                String subDate = "";
                    while (!isSubValid) {
                        String finalDate = "";
                        System.out.print("                                                                                             Please enter submission date(MM-DD-YYYY): ");
                            subDate = scan.nextLine();
                        
                        // Validate format using regex
                        if (subDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            String[] dateParts = subDate.split("-");
                            
                        try {
                            int month = Integer.parseInt(dateParts[0]);
                            int day = Integer.parseInt(dateParts[1]);
                            int year = Integer.parseInt(dateParts[2]);
                                
                            // Validate month range
                            if (month >= 1 && month <= 12) {
                                // Validate day based on the month and year
                                if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day >= 1 && day <= 31) {
                                    subDate = subDate; // Store the valid input
                                        isSubValid = true;
                                } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30) {
                                    finalDate = subDate;
                                    isSubValid = true;
                                } else if (month == 2) {
                                    // Check for leap year
                                    boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                                    if ((isLeapYear && day >= 1 && day <= 29) || (!isLeapYear && day >= 1 && day <= 28)) {
                                        finalDate = subDate;
                                        isSubValid = true;
                                    } else {
                                        System.out.println("==================================================================================================================================================================================================================================================");
                                        System.out.println("                                                                               Invalid date. February must have 28 or 29 days depending on the year.");
                                        System.out.println("==================================================================================================================================================================================================================================================");
                                    }
                                } else {
                                        System.out.println("I                                                                                  Invalid date. The day is out of range for the specified month.");
                                }
                            } else {
                                            System.out.println("                                                                                             Invalid date. Month must be 1–12.");
                            }
                        } catch (NumberFormatException e) {
                                            System.out.println("                                                                                             Invalid input. Please enter numeric values in MM-DD-YYYY format.");
                        }
                    } else {
                                            System.out.println("                                                                                             Invalid format. Please enter the date in MM-DD-YYYY format.");
                    }

                }
                

                System.out.print("                                                                                             Instructions(Be precise as possible): ");

                    String instructions = scan.nextLine();
                int numberOfStudents;
                while (true) {
                                            System.out.print("                                                                                             Enter the number of students: ");
                    try {
                        numberOfStudents = Integer.parseInt(scan.nextLine());
                        if (numberOfStudents <= 0) {
                                            System.out.println("                                                                                             The number of students must be greater than 0.");
                        } else {
                            break; // Exit the loop if valid input
                        }
                    } catch (NumberFormatException e) {
                                            System.out.println("                                                                                             Invalid input. Please enter a valid number.");
                    }
                }

                String assignment = "Subject: " + subject + ", Category: " + activity + ", Date of Submission: " + subDate + ", (Assigned by: " + teacher.getLastName() + ", " + teacher.getFirstName() + " " + teacher.getMiddleName() + ")\n" + "                                                                                             Please follow the instructions: " + instructions;
                System.out.println();
                System.out.println("==================================================================================================================================================================================================================================================");
                teacher.addTeacherAssignment(assignment);
                System.out.println();

                for (int i = 0; i < numberOfStudents; i++) {
                System.out.print("                                                                                             Enter username for Student " + (i + 1) + ": ");
                    String username = scan.nextLine();
                    boolean studentFound = false;
            
                    // Look for the student by username and add the schedule
                    for (User user : users) {
                        if (user instanceof Student && user.getUsername().equals(username)) {
                            Student student = (Student) user;
            
                            // Add the schedule to the student's own schedule list
                            student.addStudentAssignment(assignment);
            
                            // ensure the `subjects` map gets updated
                            ArrayList<String> studentAssignment = subjects.get("student");
                            if (studentAssignment == null) {
                                studentAssignment = new ArrayList<>();
                                subjects.put("student", studentAssignment);
                            }
                            studentAssignment.add(assignment);
            
                            System.out.println("                                                                                             Schedule added for student: " + username);
                            studentFound = true;
                            break;
                        }
                    }
            
                    if (!studentFound) {
                System.out.println("                                                                                             Student with username '" + username + "' not found. Skipping.");
                }
            }
        }
        
        
        private void inSchedule(Teacher teacher) {
            boolean schedOptions = true;
            System.out.println("==================================================================================================================================================================================================================================================");
            
            while (schedOptions) {
                viewSchedule(teacher);
                System.out.println("                                                                                                           [1] Add Schedule");
                System.out.println("                                                                                                           [2] Delete Schedule");
                System.out.println("                                                                                                           [3] Go back to Menu");
                System.out.print("                                                                                                             Enter choice: ");
                String choice = scan.nextLine();
                System.out.println("==================================================================================================================================================================================================================================================");
        
                if (choice.equals("1")) {
                    addSchedule(teacher);
                } else if (choice.equals("2")) {
                    deleteSchedule(teacher);
                } else if (choice.equals("3")) {
                    System.out.println();
                System.out.println("                                                                                                           Going back to Menu . . .");
                    System.out.println("==================================================================================================================================================================================================================================================");
                schedOptions = false;
                } else {
                System.out.println("                                                                                                           Invalid input.");
                }
            }
        }

        private void deleteSchedule(Teacher teacher) {
            ArrayList<String> schedules = teacher.getTeacherSchedule();
        
            if (schedules.isEmpty()) {
                System.out.println("                                                                                                           No schedules available to delete.");
                return;
            }
        
                System.out.println("                                                                                                         \n=== Teacher's Schedules ===");
            for (int i = 0; i < schedules.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + schedules.get(i));
            }
                System.out.print("                                                                                                             Enter the number of the schedule to delete (or 0 to cancel): ");
        
            try {
                int choice = Integer.parseInt(scan.nextLine());
                if (choice == 0) {
                System.out.println("                                                                                                           Deletion canceled.");
                } else if (choice > 0 && choice <= schedules.size()) {
                    String removedSchedule = schedules.remove(choice - 1); // Remove schedule from list
                System.out.println("                                                                                                           Schedule deleted: " + removedSchedule);
                } else {
                System.out.println("                                                                                                           Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("                                                                                                           Invalid input. Please enter a number.");
            }
        }
        
        private void addSchedule(Teacher teacher) {
            // Gather schedule details
            System.out.println();
            System.out.println("                                                                                                        FILL THE FOLLOWING INFORMATION");
            System.out.println();
                System.out.print("                                                                                                           Enter the subject: ");
            String subject = scan.nextLine();

                System.out.print("                                                                                                           Enter day/s of meeting(Monday, Tuesday, etc.): ");
            String day = scan.nextLine();
        
                System.out.print("                                                                                                           Enter the time (e.g., 10:00 AM - 12:00 PM): ");
            String time = scan.nextLine();
        
                System.out.print("                                                                                                           Enter the room: ");
            String room = scan.nextLine();
        
            int numberOfStudents;
            while (true) {
                System.out.print("                                                                                                           Enter the number of students: ");
                try {
                    numberOfStudents = Integer.parseInt(scan.nextLine());
                    if (numberOfStudents <= 0) {
                System.out.println("                                                                                                         The number of students must be greater than 0.");
                    } else {
                        break; 
                    }
                } catch (NumberFormatException e) {
                System.out.println("                                                                                                         Invalid input. Please enter a valid number.");
                }
            }
            System.out.println();
        
            // add info ng sched
            String schedule = "Subject: " + subject + "Day: " + day + ", Time: " + time + ", Room: " + room + " (Assigned by: " + teacher.getLastName() + ", " + teacher.getFirstName() + " " + teacher.getMiddleName() + ")";
            System.out.println();
            System.out.println("==================================================================================================================================================================================================================================================");
                System.out.println("                                                                             The schedule is: " + schedule);
        
            // add sat eacher sched
            teacher.addTeacherSchedule(schedule);
                System.out.println("                                                                                                           Schedule added to your teaching schedule.");
                System.out.println();
            // assign yung sched sa mga student
            for (int i = 0; i < numberOfStudents; i++) {
                System.out.print("                                                                                                           Enter username for Student " + (i + 1) + ": ");
                String username = scan.nextLine();
                boolean studentFound = false;
        
                // find student
                for (User user : users) {
                    if (user instanceof Student && user.getUsername().equals(username)) {
                        Student student = (Student) user;
        
                        // Add the schedule to the student's own schedule list
                        student.addStudentSchedule(schedule);
        
                        // Also ensure the `subjects` map gets updated
                        ArrayList<String> studentSchedule = subjects.get("student");
                        if (studentSchedule == null) {
                            studentSchedule = new ArrayList<>();
                            subjects.put("student", studentSchedule);
                        }
                        studentSchedule.add(schedule);
        
                            System.out.println("                                                                                                           Schedule added for student: " + username);
                        studentFound = true;
                        break;
                    }
                }
        
                if (!studentFound) {
                System.out.println("                                                                                                           Student with username '" + username + "' not found. Skipping.");
                }
            }
        
                System.out.println("                                                                                                           Schedule creation completed.");
        }
        
        private void viewSchedule(Teacher teacher) {
            // Directly access the teacher's own schedule list
            if (teacher.getTeacherSchedule().isEmpty()) {
                System.out.println("                                                                                                           No schedules available.");
            } else {
                System.out.println();
                System.out.println("==================================================================================================================================================================================================================================================");
                System.out.println("                                                                                                         === Teacher Schedules ===");
                // Iterate over the teacher's schedule list
                for (String schedule : teacher.getTeacherSchedule()) {
                System.out.println("                                                                                    - " + schedule);
                }
            }
        }

        private void viewAssignments(Teacher teacher) {
            if (teacher.getTeacherAssignment().isEmpty()) {
                System.out.println("                                                                                                           No assignments available.");
            } else {
                System.out.println("                                                                                                           === Teacher Assignments ===");

                for (String assignment : teacher.getTeacherAssignment()) {
                    // Check if the assignment has been graded
                    boolean isGraded = teacher.getGrades().containsKey(assignment);
                    String status = isGraded ? "(Graded)" : "(Not Graded)";
                        System.out.println("                                                                                             - " + assignment + " " + status);

                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println();
                }
            }
        }

        private void gradeSubmissions(Teacher teacher) {
            System.out.println();
            System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println();
            System.out.println("                                                                                                         === Grade Assignments ===");
            HashMap<String, HashMap<Student, String>> submissions = teacher.getAssignmentSubmissions();

            if (submissions.isEmpty()) {
                        System.out.println("                                                                                                           No submissions to grade.");
                return;
            }

            for (String assignment : submissions.keySet()) {
                        System.out.println("                                                                                                           Assignment: " + assignment);
                HashMap<Student, String> studentSubmissions = submissions.get(assignment);

                for (Student student : studentSubmissions.keySet()) {
                    String answer = studentSubmissions.get(student);
                        System.out.println("\nStudent: " + student.getFirstName() + " " + student.getLastName());
                        System.out.println("                                                                                                           Answer: " + answer);
                        System.out.print("                                                                                                             Enter grade (0-100): ");
                    String grade = scan.nextLine();

                    // Record the grade in the teacher's records
                    teacher.gradeAssignment(assignment, student, grade);

                    // Send a message to the student
                    String message = String.format("From %s %s: Your grade for '%s' is %s.",
                    teacher.getFirstName(), teacher.getLastName(), assignment, grade);
                    student.addMessage(message);

                        System.out.println("                                                                                                           Grade recorded and message sent to " + student.getFirstName());
                }
            }
        }


        private double handleModeOfPayment(Student student) {
            System.out.println("\n=== Mode of Payment ===");
            System.out.println("1. Full Payment (Php 60,000 - Php 6,000 discount)");
            System.out.println("2. Monthly Payment");

            System.out.print("Enter choice: ");
            String choice = scan.nextLine();

            double tuitionFee = 60000; // Base tuition fee
            double remainingBalance = tuitionFee;

            if (choice.equals("1")) {
                // Full Payment Logic
                double discount = 6000;
                double discountedPrice = tuitionFee - discount;

                System.out.println("You have selected Full Payment. Your total fee after discount is Php " + discountedPrice);
                remainingBalance = discountedPrice; // Full payment with discount applied

            } else if (choice.equals("2")) {
                // Monthly Payment Plan
                System.out.println("Choose your monthly payment plan:");
                System.out.println("1. Php 7,000/month plan (downpayment Php 5,000)");
                System.out.println("2. Php 4,500/month plan (downpayment Php 2,000)");

                System.out.print("Enter choice: ");
                String planChoice = scan.nextLine();

                if (planChoice.equals("1")) {
                    // Php 7,000/month Plan with Php 5,000 Downpayment
                    System.out.print("You selected Php 7,000/month plan. Do you agree to pay a Php 5,000 downpayment? (y/n): ");
                    String confirmDownpayment = scan.nextLine();

                    if (confirmDownpayment.equalsIgnoreCase("y")) {
                        remainingBalance -= 5000; // Deduct downpayment
                        System.out.println("You have paid Php 5,000 downpayment. Remaining balance is Php " + remainingBalance);
                    } else {
                        System.out.println("Payment canceled. Returning to main menu.");
                    }

                } else if (planChoice.equals("2")) {
                    // Php 4,500/month Plan with Php 2,000 Downpayment
                    System.out.print("You selected Php 4,500/month plan. Do you agree to pay a Php 2,000 downpayment? (y/n): ");
                    String confirmDownpayment = scan.nextLine();

                    if (confirmDownpayment.equalsIgnoreCase("y")) {
                        remainingBalance -= 2000; // Deduct downpayment
                        System.out.println("You have paid Php 2,000 downpayment. Remaining balance is Php " + remainingBalance);
                    } else {
                        System.out.println("Payment canceled. Returning to main menu.");
                    }

                } else {
                    System.out.println("Invalid choice. Returning to main menu.");
                }

            } else {
                System.out.println("Invalid choice. Returning to main menu.");
            }

            return remainingBalance; 
        }

        
        private void viewSubjects() {
            boolean isViewSubject = true;
                while (isViewSubject) {

                    System.out.println();
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.println("                                                                                                           Subjects and Schedule:");
                    ArrayList<String> schedule = subjects.get("student");
                    if (schedule != null && !schedule.isEmpty()) {
                        for (String subject : schedule) {
                        System.out.println(subject);
                        }
                    } else {
                        System.out.println("                                                                                                           No schedule available.");
                    }
                    System.out.println("==================================================================================================================================================================================================================================================");
                    System.out.print("Press '1' to go back on Menu: ");
                    String choice = scan.nextLine();
                    if (choice.equals("1")) {
                        System.out.println("Going back to Menu . . .");
                        isViewSubject = false;
                    } else {
                        System.out.println("Invalid Input.");
                    }
                }
            }
                //sample lang di pa tapos
                private void viewAssignments(Student student) {
                    boolean isViewAssignment = true;
                    while (isViewAssignment) {
                        if (student.getStudentAssignment().isEmpty()) {
                            System.out.println("                                                                                                           No assignments available.");
                            isViewAssignment = false;
                        } else {
                            System.out.println("                                                                                                         \n=== Teacher Assignment ===");
                            // Iterate over the teacher's schedule list
                            for (String assignment : student.getStudentAssignment()) {
                                System.out.println("                                                                                                           - " + assignment);
                            }
    
                                System.out.println("                                                                                                           [1] Submit your answer");
                                System.out.println("                                                                                                           [2] Go back to Menu");
                            String choice = scan.nextLine();

                            if (choice.equals("1")) {
                                submitAssignment(student);
                            } else if(choice.equals("2")) {
                                System.out.println("                                                                                                           Going back to Menu . . .");
                                isViewAssignment = false;
                            }
                        }
                    }
                }

                private void useNotepad(Student student) {
                    System.out.println("                                                                                                         \nYour Notes:");
                    ArrayList<String> notes = student.getNotes();
                    for (String note : notes) {
                        System.out.println("- " + note);
                    }

                    // Allow the user to add a note
                System.out.print("                                                                                                           \nDo you want to add a note? (yes/no): ");
                    String response = scan.nextLine();
                    if (response.equalsIgnoreCase("yes")) {
                        System.out.print("                                                                                                             Enter your note: ");
                        String newNote = scan.nextLine();
                        student.addNote(newNote);
                        System.out.println("                                                                                                           Note added successfully.");
                    }
                }
                private void createAccount() {
                    boolean creatingAccount = true;

                    while (creatingAccount) {
                System.out.println("                                                                                                           IMPORTANT REMINDERS");
                System.out.println();
                System.out.println("                                                                                      a. Choose your Account's Role and Information accordingly. ");
                System.out.println();
                System.out.println("                                                                                      b. Your information will be saved and secured with confidentiality. ");
                System.out.println();
                System.out.println("                                                                                      c. Ensure your information is accurate before final submission. ");
                System.out.println();
                System.out.println("                                                                                      d. Ensure your password is something you can easily keep in mind. ");
                System.out.println();
                System.out.println("                                                                                      e. Your credentials will be necessary for logging in at a later time.");
                System.out.println();
                System.out.println("                                                                                      f. If you encounter any problems, contact the system administrator.");
                System.out.println("==================================================================================================================================================================================================================================================");
                System.out.println();
                System.out.println("                                                                                                           Creating an Account as: "); 
                System.out.println("                                                                                                           [1] Admin");
                System.out.println("                                                                                                           [2] Teacher");
                System.out.println("                                                                                                           [3] Student");
                System.out.println("                                                                                                           [4] Go back to Menu");
                System.out.println();
                System.out.print("                                                                                                            Choose Option: ");
                    String roleChoice = scan.nextLine();
                System.out.println("\n==================================================================================================================================================================================================================================================");

                
                    if (roleChoice.equals("1")) {
                        System.out.println("                                                                                               Sorry, this function is not allowed to use.");
                        System.out.println("==================================================================================================================================================================================================================================================\");");

                    } else if (roleChoice.equals("2")) {  // =========================================== TEACHER REGISTRATION ========================================================
                System.out.println();
                System.out.println("TEACHER ADVISER REGISTRATION FORM");
                System.out.println();
                
                System.out.print("Last Name: ");
                    String lastName = scan.nextLine();
                System.out.print("First Name: ");
                    String firstName = scan.nextLine();
                System.out.print("Middle Name: ");
                    String middleName = scan.nextLine();
                    
                System.out.print("Phone Number: ");
                    String contactNum = scan.nextLine();
                    
                    int age = 0;
                    while (age <= 0) {
                    System.out.print("Age: ");
                        try {
                            age = Integer.parseInt(scan.nextLine());
                            if (age > 0) {
                                break;
                            } else {
                            System.out.println("                                                                                                           Invalid input, please enter a positive age.");
                                }
                        } catch (NumberFormatException e) {
                    System.out.println("                                                                                                           Invalid input, please enter a number for age.");
                    }    
                    }
                    System.out.println();
                            String sex = "";
                        while (!sex.equalsIgnoreCase("M") || !sex.equalsIgnoreCase("F")) {
                            System.out.println("Sex: ");
                            System.out.println("[M] - Male");
                            System.out.println("[F] - Female");
                            System.out.print("Enter choice: ");
                            sex = scan.nextLine();
                            if(sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F")) {
                                break;
                            } else {
                        System.out.println("Invalid input, please choose with the given choices.");
                            }
                        }

                        boolean isBirthValid = false;
                        String birthInput = "";
                        System.out.println();
                        while (!isBirthValid) {
                            String birthYear = "";
                        System.out.print("Please enter your Date of Birth (MM-DD-YYYY): ");
    
                            birthInput = scan.nextLine();
                        
                        // Validate format using regex
                    if (birthInput.matches("\\d{2}-\\d{2}-\\d{4}")) {
                        String[] dateParts = birthInput.split("-");
                            
                        try {
                            int month = Integer.parseInt(dateParts[0]);
                            int day = Integer.parseInt(dateParts[1]);
                            int year = Integer.parseInt(dateParts[2]);
                             
                            // Validate month range
                        if (month >= 1 && month <= 12) {
                                // Validate day based on the month and year
                                if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day >= 1 && day <= 31) {
                                    birthYear = birthInput; // Store the valid input
                                    isBirthValid = true;
                                } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30) {
                                    birthYear = birthInput;
                                    isBirthValid = true;
                                } else if (month == 2) {
                                        // Check for leap year
                                    boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                                if ((isLeapYear && day >= 1 && day <= 29) || (!isLeapYear && day >= 1 && day <= 28)) {
                                    birthYear = birthInput;
                                    isBirthValid = true;
                                } else {
                                    System.out.println("==================================================================================================================================================================================================================================================");
                                    System.out.println("                                                                                                          Invalid date. February must have 28 or 29 days depending on the year.");
                                    System.out.println("==================================================================================================================================================================================================================================================");
                                }
                            } else {
                                System.out.println("Invalid date. The day is out of range for the specified month.");
                                System.out.println();
                            }
                        } else {
                            System.out.println("Invalid date. Month must be 1–12.");
                            System.out.println();
                        }
                                    } catch (NumberFormatException e) {
                                    System.out.println("Invalid input. Please enter numeric values in MM-DD-YYYY format.");
                                    System.out.println();
                                    }
                                } else {
                                    System.out.println("Invalid format. Please enter the date in MM-DD-YYYY format.");
                                    System.out.println();
                                        }
                                    }
                                System.out.println();
                                System.out.print("Enter Username: ");
                                        String teacherUsername = scan.nextLine();
                                        boolean usernameExists = false;
                                        
                                        // Check if the username already exists
                                        for (User user : users) {
                                            if (user.getUsername().equals(teacherUsername)) {
                                System.out.println("Sorry, this username already exists.");
                                                usernameExists = true;
                                                break;
                            }
                        }
                        
                        if (!usernameExists) {
                            System.out.print("Enter Password: ");
                            String password = scan.nextLine();
                            // Create a new Teacher object
                            Teacher teacher = new Teacher(teacherUsername, password, lastName, firstName, middleName, contactNum, age, sex);
                            users.add(teacher); // Add the Teacher to the users list
                            System.out.println("==================================================================================================================================================================================================================================================");
                            System.out.println("                                                                                                          Teacher account created.");
                        }

                        creatingAccount = false;
                    } else if (roleChoice.equals("3")) {
                        // Student account creation logic
                        System.out.print("Last Name: ");
                                    String lastName = scan.nextLine();
                        System.out.print("First Name: ");
                                    String firstName = scan.nextLine();
                        System.out.print("Middle Name: ");
                                    String middleName = scan.nextLine();

                        System.out.print("Phone/Telephone Number: ");
                                    String contactNum = scan.nextLine();

                        int age = 0;
                        while (age <= 0) {
                            try {
                        System.out.print("Age: ");
                                String ageInput = scan.nextLine();
                                age = Integer.parseInt(ageInput);
                            if (age > 0) {
                                break; // Valid input
                            } else {
                                System.out.println("                                                                                                           Invalid input, please enter a positive age.");
                            }
                                } catch (NumberFormatException e) {
                                    System.out.println("                                                                                                           Invalid input. Please enter a valid number for age.");
                                }
                            }
                        //sex, birthyear, scolarship
                        System.out.println();
                        String sex = "";
                        while (!sex.equalsIgnoreCase("M") || !sex.equalsIgnoreCase("F")) {
                        System.out.println("Sex: ");
                        System.out.println("[M] - Male");
                        System.out.println("[F] - Female");
                        System.out.print("Enter choice: ");
                            sex = scan.nextLine();
                            if(sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F")) {
                                break;
                            } else {
                                System.out.println("                                                                                                           Invalid input, please choose with the given choices.");
                            }
                        }

                        String birthYear = "";
                        boolean isBirthValid = false;
                        String birthInput = "";
                        
                        while (!isBirthValid) {
                        System.out.println();
                        System.out.print("> Please enter your Date of Birth (MM-DD-YYYY): ");
                                    birthInput = scan.nextLine();
                    
                        // Validate format using regex
                        if (birthInput.matches("\\d{2}-\\d{2}-\\d{4}")) {
                            String[] dateParts = birthInput.split("-");
                            
                            try {
                                int month = Integer.parseInt(dateParts[0]);
                                int day = Integer.parseInt(dateParts[1]);
                                int year = Integer.parseInt(dateParts[2]);
                                
                                // Validate month range
                                if (month >= 1 && month <= 12) {
                                    // Validate day based on the month and year
                                    if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day >= 1 && day <= 31) {
                                        birthYear = birthInput; // Store the valid input
                                        System.out.println("Your Date of Birth is: " + birthYear);
                                        System.out.println();
                                        isBirthValid = true;
                                    } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day >= 1 && day <= 30) {
                                        birthYear = birthInput;
                                        System.out.println("Your Date of Birth is: " + birthYear);
                                        System.out.println();
                                        isBirthValid = true;
                                    } else if (month == 2) {
                                        // Check for leap year
                                        boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                                        if ((isLeapYear && day >= 1 && day <= 29) || (!isLeapYear && day >= 1 && day <= 28)) {
                                            birthYear = birthInput;
                                            System.out.println("Your Date of Birth is: " + birthYear);
                                            isBirthValid = true;
                                        } else {
                                            System.out.println("Invalid date. February must have 28 or 29 days depending on the year.");
                                        }
                                    } else {
                                        System.out.println("Invalid date. The day is out of range for the specified month.");
                                    }
                                } else {
                                    System.out.println("Invalid date. Month must be 1–12.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter numeric values in MM-DD-YYYY format.");
                            }
                        } else {
                            System.out.println("Invalid format. Please enter the date in MM-DD-YYYY format.");
                        }
                        
                    }
                    
                
                    String scholarship = "";
                    while (!scholarship.equalsIgnoreCase("Y") && !scholarship.equalsIgnoreCase("N")) {
                        System.out.println("Scholarship Beneficiary? (eg. Varsity, Pag-ibig, Municipal, etc.)");
                        System.out.println("[Y] - Yes");
                        System.out.println("[N] - No");
                        System.out.print("Enter choice: ");
                        scholarship = scan.nextLine();
                        if (scholarship.equalsIgnoreCase("Y") || scholarship.equalsIgnoreCase("N")) {
                            break;
                        } else {
                            System.out.println("Invalid input, please choose with the given choices.");
                        }
                    }
                    
                    System.out.println();
                    System.out.print("Academic Program [BSIT, BSCS, BSCPE, BMMA, BSE]: ");
                    String program = scan.nextLine();
                    
                    int studentNumber = 0;
                    boolean isIdValid = false;
                    
                    System.out.println();
                    while (!isIdValid) {
                        System.out.print("Enter your Student ID (10 digits): ");
                        String input = scan.nextLine();
                    
                        if (input.matches("\\d{10}")) {
                            try {
                                studentNumber = Integer.parseInt(input);
                                isIdValid = true; // Exit loop
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Number is too large to process. Please enter a valid 10-digit ID.");
                            }
                        } else {
                            System.out.println("Invalid input. Student ID must be exactly 10 digits.");
                        }
                    }
                    
                    System.out.println();
                    System.out.print("Year level: ");
                    int year = scan.nextInt();
                    scan.nextLine();
                    
                    // Create student object
                    System.out.println();
                    System.out.print("Enter Username: ");
                    String studentUsername = scan.nextLine();
                    for (User user : users) {
                        if (user.getUsername().equals(studentUsername)) {
                            System.out.println("Sorry, this username already exists.");
                            return;
                        } else {
                            break;
                        }
                    }
                    
                    System.out.print("Enter Password: ");
                    String password = scan.nextLine();
                    
                    // fixed balance sa 60k
                    Student student = new Student(
                        studentUsername, password, lastName, firstName, middleName,
                        contactNum, age, sex, birthYear, scholarship, program,
                        studentNumber, year, "", 60000
                    );
                    
                    // Deduct based on mode of payment
                    double remainingBalance = handleModeOfPayment(student);
                    
                    // Update the student object with the remaining balance
                    student.setBalance(remainingBalance);
                    
                    // Check if username already exists in the system
                    boolean usernameExists = false;
                    for (User user : users) {
                        if (user.getUsername().equals(studentUsername)) {
                            System.out.println("Sorry, this username already exists. Please choose a different one.");
                            usernameExists = true;
                            break;
                        }
                    }
                    
                    if (!usernameExists) {
                        // Add the student object to the user list
                        users.add(student);
                
                        System.out.println("Student account created successfully.");
                        System.out.printf("Remaining Balance: Php %.2f%n", remainingBalance);
                    } else {
                        System.out.println("Account creation failed due to duplicate username.");
                    }
                    
                    } else if (roleChoice.equals("4")) {
                        creatingAccount = false;
                        System.out.println("==================================================================================================================================================================================================================================================");
                        System.out.println("Going back to Menu . . .");
                        System.out.println("==================================================================================================================================================================================================================================================");
                    }
                }
            }
    public static void main(String[] args) { 
        new SchoolApp().start();
    }
}