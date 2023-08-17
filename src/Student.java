public class Student extends Thread {
    private ThreadGroup threadGroup;
    private Printer printer;
    private String name;

    public Student(ThreadGroup threadGroup, Printer printer, String name) {
        this.threadGroup = threadGroup;
        this.printer = printer;
        this.name = name;
    }

    @Override
    public void run() {
        Document[] document = new Document[5];
        document[0] = new Document(name, "Concurrent Programming", 7 );
        document[1] = new Document(name, "Formal Methods", 5 );
        document[2] = new Document(name, "Final Year Project", 2 );
        document[3] = new Document(name, "Cyber Security", 2 );
        document[4] = new Document(name, "Mobile Native Application Development", 2 );

        int numOfPages = 0;
        int printedDocuments = 0;
        for (Document docs: document) {
            printer.printDocument(docs);
            printedDocuments++;  //increment the printedDocuments by one
            numOfPages += docs.getNumberOfPages();
            try {
                Thread.sleep((int)Math.floor(Math.random() * (5000 - 1000 + 1) + 1000)); //sleep student tread for random time
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(this.name + " Finished Printing: " + printedDocuments + " Documents, " + numOfPages + " pages. \n"  );

    }


}
