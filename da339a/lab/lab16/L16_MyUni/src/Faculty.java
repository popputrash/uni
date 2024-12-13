import java.util.Arrays;

public class Faculty {
    Program programs[];
    Teacher teachers[];
    String facultyName;
    int programIndex;

    Faculty(String facultyName, int amountOfPrograms, int amountOfTeachers){
        this.facultyName = facultyName;
        this.programs = new Program[amountOfPrograms];
        this.teachers = new Teacher[amountOfTeachers];
    }

    private int getProgramIndex(String programName) {
        for (int i = 0; i < programs.length; i++){
            if(programs[i].getProgramName().equals(programName)){
                programIndex = i;
                return programIndex;
            }
        }
        return -1;
    }

    public String removeStudentFromProgram(String studentName, String programName){
        for (Program program : programs){
            if(program.getProgramName().equals(programName)){
                return program.removeStudent(studentName);
            }
        }
        return "Inget program med det namnet.";
    }

    public String checkIfTeacherExists(String teacherName){
        for (Teacher teacher : teachers){
            if(teacher != null  && teacher.toString().equals(teacherName)){
                return "Läraren tillhör fakulteten.";
            }
        }
        return "Läraren tillhör inte fakulteten.";
    }

    public String addCoursesToProgram(Course[] courses, String programName){
        for(Program program : programs){
            if(program.getProgramName().equals(programName)){
                return program.setCourses(courses);
            }
        }
        return "Inget program med det namnet.";
    }

    public String getFacultyName() {
        return facultyName;
    }

    public String addStudentToProgram(String programName, Student student){
        for(Program program : programs){
            if (program.getProgramName().equals(programName)){
                return (program.addStudent(student));
            }
        }
        return "Inget program med det namnet.";
    }

    public String addProgram(String programName, int amountOfCourses, int numberOfStudents){
        if(programs[programs.length-1] == null){
            for(int i = 0; i < programs.length; i++ ){
                if (programs[i] == null){
                    programs[i] = new Program(programName, amountOfCourses, numberOfStudents);
                    return "Program tillagt.";
                }
            }
        }
        return "Max antal program tillagda.";
    }

    public String[] getProgramNames(){
        String[] returnArray = new String[programs.length];
        for (int i = 0; i < programs.length; i++){
            returnArray[i] = programs[i].getProgramName();
        }
        return returnArray;
    }

    public String findStudent(String studentName, String programName){
        for (Program program : programs){
            if (program.getProgramName().equals(programName)){
                for (Student student : program.getStudents()){
                    if (student != null && student.getName().equals(studentName)){
                        return "Studenten är registrerad.";
                    }
                }
            }
        }
        return "Studenten är inte registrerad på programmet.";
    }

    public String addTeachersToFaculty(Teacher[] teachers){
        if(teachers.length > this.teachers.length){
            return "För många valda lärare.";
        }
        this.teachers = teachers;
        return "success";
    }

    public String findStudentByID(String uniID, String programName){
        for(Program program : programs){
            if (program.getProgramName().equals(programName)){
                for (Student student : program.getStudents()){
                    if(student != null && student.getUni_ID().equals(uniID)){
                        return student.getName();
                    }
                }
                return "Inget namn finns associerat med detta student-ID.";
            }
        }
        return "Inget program med det namnet.";
    }

}
