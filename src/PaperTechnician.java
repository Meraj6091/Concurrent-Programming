public class PaperTechnician extends Thread {

    private ThreadGroup threadGroup;
    private ServicePrinter printer;
    private String name;

    public PaperTechnician(ThreadGroup threadGroup, ServicePrinter printer, String name) {
        this.threadGroup = threadGroup;
        this.printer = printer;
        this.name = name;
    }

    @Override
    public void run() {
        LaserPrinter ls = (LaserPrinter) printer;
        for (int i=0; i<3; i++) {
            printer.refillPaper();
            try {
                Thread.sleep((int)Math.floor(Math.random() * (5000 - 1000 + 1) + 1000));  //sleep Paper Technician for random time
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Paper Technician Finished, Packs of paper used: " + ls.getPaperRefillingTimes()+"\n");
    }
}
