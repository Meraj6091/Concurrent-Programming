public class TonerTechnician extends Thread{

    private ThreadGroup threadGroup;
    private ServicePrinter printer;
    private String name;

    public TonerTechnician(ThreadGroup threadGroup, LaserPrinter printer, String name) {
        this.threadGroup = threadGroup;
        this.printer = printer;
        this.name = name;
    }

    @Override
    public void run() {
        LaserPrinter ls = (LaserPrinter) printer;
        for (int i=0; i<3; i++) {
            printer.replaceTonerCartridge();

            try {
                Thread.sleep((int)Math.floor(Math.random() * (5000 - 1000 + 1) + 1000));  //sleep thread for a random seconds
            } catch (InterruptedException e) { //handle the exception
                throw new RuntimeException(e);
            }
        }
        System.out.println("Toner Technician Finished, cartridges replaced: " + ls.getTonerRefillingTimes() +"\n");
    }
}
