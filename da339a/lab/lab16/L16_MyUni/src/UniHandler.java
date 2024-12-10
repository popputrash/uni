public class UniHandler {

    private final Faculty[] faculties = new Faculty[2]; //När du är redo att köra
    // testerna ta bort //
    private static int AMOUNT_OF_TESTS = 20;

    public UniHandler() {
        generateData();
        runTests();
    }

    // generateData metod:
    private void generateData() {
        // Integers för en ny fakultet motsvarar storleken på program och lärar arrayerna.
        faculties[0] = new Faculty("Teknik och Samhälle", 2, 2);
        // Integers för en ny program motsvarar storleken på kurser och student arrayerna.
        faculties[0].addProgram("Systemutvecklare", 3, 2);
        // Integers för en ny program motsvarar storleken på kurser och student* arrayerna.
        faculties[0].addProgram("DT", 1, 2);
        faculties[0].addStudentToProgram("Systemutvecklare", new Student("Christel", "23TS"));
        faculties[0].addStudentToProgram("Systemutvecklare", new Student("Lukas", "24TS"));
        Course[] courses = {Course.DA242A, Course.DA339A, Course.DA335E};
        faculties[0].addCoursesToProgram(courses, "Systemutvecklare");
        Teacher[] teachers = {Teacher.Hannes, Teacher.Jennie};
        faculties[0].addTeachersToFaculty(teachers);
        // Integers för en ny fakultet motsvarar storleken på program och lärar arrayerna.
        faculties[1] = new Faculty("Kultur och Samhälle", 2, 2);
        // Integers för en ny program motsvarar storleken på kurser och student arrayerna.
        faculties[1].addProgram("Miljövetarprogrammet", 4, 3);
    }

    // runTests metod:
    //
    private void runTests() {
        // TESTER FÖR FAKULTET 1!
        // Returnerar ni samma felmeddelanden som står i uppgiften?
        // Test 1: Testar ifall ni har felhantering för att lägga till ett program
        //för mycket.

        int passed = 0;
        String expected = "Max antal program tillagda.";
        System.out.print("Expected reuslt: Max antal program tillagda.");
        String result = faculties[0].addProgram("KRASH", 1, 2);
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 2: Testar ifall ni har felhantering för lägga till en lärare för mycket
        expected = "För många valda lärare.";
        System.out.print("Expected result: För många valda lärare.");
        Teacher[] teachers = {Teacher.Jan, Teacher.Jennie, Teacher.Roger};
        result = faculties[0].addTeachersToFaculty(teachers);
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 3: Testar ifall ni har felhantering för att lägga til en student i
        // ett program som inte existerar.
        expected = "Inget program med det namnet.";
        System.out.print("Expected result: Inget program med det namnet.");
        Student student = new Student("Arvid", "25TS");
        result = faculties[0].addStudentToProgram("Systemvecklare", student);
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        //Test 4: Testar ifall ni har felhantering för att lägga til en student för mycket.
        expected = "Programmet är fullt.";
        System.out.print("Expected result: Programmet är fullt.");
        result = faculties[0].addStudentToProgram("Systemutvecklare", student);
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");
//
        // Test 5: Testar ifall ni har felhantering för att ta bort en student från
        // ett program som inte existerar.
        expected = "Inget program med det namnet.";
        System.out.print("Expected result: Inget program med det namnet.");
        result = faculties[0].removeStudentFromProgram("Christel", "Systemvetare");
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 6: Testar ifall ni har felhantering för att ta bort en student vars namn inte
        // finns med i programmet.
        expected = "Student fanns ej i programmet.";
        System.out.print("Expected result: Student fanns ej i programmet.");
        result = faculties[0].removeStudentFromProgram("Christin", "Systemutvecklare");
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        //Test 7: Testar ifall ni kan ta bort en existerande student i ett existerande program.
        expected = "Student borttagen.";
        System.out.print("Expected result: Student borttagen.");
        result = faculties[0].removeStudentFromProgram("Christel",
                "Systemutvecklare");
        System.out.println("   Result: " + result);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 8: Testar ifall ni kan lägga till en student till ett existerande program.
        expected = "Student tillagd i programmet.";
        System.out.println("Expected result: Student tillagd i programmet.");
        result = faculties[0].addStudentToProgram("Systemutvecklare", student);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 9: Testar ifall ni kan lägga till ett antal kurser i ett icke
        // existerande program.
        expected = "Inget program med det namnet.";
        Course[] courses = {Course.DA242A, Course.DA242A, Course.DA335E,
                Course.DA339A};
        System.out.println("Expected result: Inget program med det namnet.");
        result = faculties[0].addCoursesToProgram(courses, "Systemvetare");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 10: Testar ifall ni kan lägga till ett för stort antal kurser i
        // ett existerande program.

        expected = "Fler kurser än tillåtet.";
        System.out.println("Expected result: Fler kurser än tillåtet.");
        result = faculties[0].addCoursesToProgram(courses, "Systemutvecklare");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // TESTER FÖR FAKULTET 2!
        student = new Student("Arnold", "27TS");
        faculties[1].addStudentToProgram("Miljövetarprogrammet", student);

        // Test 11: Lägga till ett program i en fakultet.
        expected = "Program tillagt.";
        System.out.println("Expected result: Program tillagt.");
        // Integers för en ny program motsvarar storleken på kurser och student arrayerna.
        result = faculties[1].addProgram("Peace and Conflict Studies", 2, 2);
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 12: Kontrollera en fakultets program är rätt.
        String[] expectedPrograms = {"Miljövetarprogrammet",
                "Peace and Conflict Studies"};
        System.out.println("Expected result: Miljövetarprogrammet Peace and Conflict Studies");
        String[] expectedResults = faculties[1].getProgramNames();
        if (expectedPrograms[0].equals(expectedResults[0]) &&
                expectedPrograms[1].equals(expectedResults[1])) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 13: Kontrollerar om en student existerar på ett program.
        expected = "Studenten är registrerad.";
        System.out.println("Expected result: Studenten är registrerad.");
        result = faculties[1].findStudent("Arnold", "Miljövetarprogrammet");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 14: Kontrollerar att en student inte finns tillagd i ett program.
        expected = "Studenten är inte registrerad på programmet.";
        System.out.
                println("Expected result: Studenten är inte registrerad på programmet.");
        result = faculties[1].findStudent("Linn", "Miljövetarprogrammet");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 15: Kontrollerar om en fakultet existerar.
        expected = "Fakulteten existerar.";
        System.out.println("Expected result: Fakulteten existerar..");
        result = checkFaculty("Teknik och Samhälle");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 16: Kontrollerar ifall en lärare finns registrerad på en fakultet.
        expected = "Läraren tillhör fakulteten.";
        System.out.println("Expected rseult: Läraren tillhör fakulteten.");
        result = faculties[0].checkIfTeacherExists("Hannes");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 17: Kontrollerar ifall en lärare inte är registrerar på en fakultet
        expected = "Läraren tillhör inte fakulteten.";
        System.out.println("Expected result: Läraren tillhör inte fakulteten.");
        result = faculties[0].checkIfTeacherExists("Lars");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

        // Test 18: Kontrollerar att programmet inte kraschar ifall inga lärare ärtillagda.
        expected = "Läraren tillhör inte fakulteten.";
        System.out.println("Expected result: Läraren tillhör inte fakulteten.");
        result = faculties[1].checkIfTeacherExists("Lars");
        if (expected.equals(result)) {
            passed++;
            System.out.println("PASSED\n");
        } else System.out.println("FAILED\n");

      // Test 19: Hämta students namn utifrån ett uniID
      student = new Student("Laban", "23KS");
      faculties[1].addStudentToProgram("Peace and Conflict Studies", student);
      expected = "Laban";
      System.out.println("Expected result: Laban");
      result = faculties[1].findStudentByID("23KS", "Peace and Conflict Studies");
      if (expected.equals(result)) {
      passed++;
      System.out.println("PASSED\n");
      } else System.out.println("FAILED\n");

      // Test 20: Letar efter ett namn med ett icke existerande student ID.
      expected = "Inget namn finns associerat med detta student-ID.";
      System.out.
      println("Expected result: Inget namn finns associerat med detta student-ID."
      );
      result = faculties[1].findStudentByID("23JK", "Peace and Conflict Studies");
      if (expected.equals(result)) {
      passed++;
      System.out.println("PASSED\n");
      } else System.out.println("FAILED\n");

      System.out.println("\n\n" + passed + "/" + AMOUNT_OF_TESTS + " PASSED");

    }
//    // checkFaculty metod:
    private String checkFaculty(String facultyName){
        if (faculties[0].getFacultyName().equals(facultyName) ||
                faculties[1].getFacultyName().equals(facultyName)) {
            return "Fakulteten existerar.";
        }
        return "Fakulteten existerar inte.";

    }
}
