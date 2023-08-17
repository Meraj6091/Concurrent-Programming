public class PrintingSystem {
    public static void main(String[] args) {  //main
        System.out.println("------------ PRINTING SYSTEM STARTED -------------");
        System.out.println("");

        LaserPrinter laserPrinter = new LaserPrinter("hp-laser-23", 15, 10, 0);

        ThreadGroup student = new ThreadGroup("Student");
        ThreadGroup technician = new ThreadGroup("Technician");

        Thread student1 = new Student(student, laserPrinter, "student1" );
        Thread student2 = new Student(student, laserPrinter, "student2" );
        Thread student3 = new Student(student, laserPrinter, "student3" );
        Thread student4 = new Student(student, laserPrinter, "student4" );


        Thread paperTechnician = new PaperTechnician(technician, laserPrinter, "Paper Technician");
        Thread tonerTechnician = new TonerTechnician(technician, laserPrinter, "Toner Technician");

        student1.start();
        student2.start();
        student3.start();
        student4.start();
        paperTechnician.start();
        tonerTechnician.start();

        try {
            student1.join();
            student2.join();
            student3.join();
            student4.join();
            paperTechnician.join();
            tonerTechnician.join();
        } catch (InterruptedException e) {  //error handling

            throw new RuntimeException(e);
        }

        System.out.println("All documents have been printed");
        System.out.println("------ PRINTING SYSTEM ENDED ------");
        System.out.println(laserPrinter.toString());
    }
}