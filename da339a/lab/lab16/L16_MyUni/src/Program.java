public class Program {
    Student[] students;
    int counter;
    String programName;
    Course[] courses;

    Program(String programName, int amountOfCourses, int numberOfStudents){
        this.programName = programName;
        this.courses = new Course[amountOfCourses];
        this.students = new Student[numberOfStudents];
    }

    public Student[] getStudents() {
        return students;
    }

    public String addStudent(Student student){
        for(int i = 0; i < students.length; i++){
            if(students[i] == null){
                students[i] = student;
                return "Student tillagd i programmet.";
            }
        }
        return "Programmet är fullt.";
    }
    public String setCourses(Course[] courses){
        if(courses.length > this.courses.length){
            return "Fler kurser än tillåtet.";
        }
        this.courses = courses;
        return "";
    }
    public String removeStudent(String studentName){
        for(int i = 0; i < students.length; i++){
            if(students[i].getName().equals(studentName)){
                students[i] = null;
                return "Student borttagen.";
            }
        }
        return "Student fanns ej i programmet.";
    }
    public String getProgramName() {
        return programName;
    }
}
