public class LaserPrinter implements ServicePrinter{

    private String printerID;
    private int paperLevel;
    private int tonerLevel;
    private int docsPrinted;

    private int paperRefillingTimes = 0;
    private int tonerRefillingTimes = 0;

    public int getPaperRefillingTimes() {
        return paperRefillingTimes;
    }

    public int getTonerRefillingTimes() {
        return tonerRefillingTimes;
    }

    public LaserPrinter(String printerID, int paperLevel, int tonerLevel, int docsPrinted) {
        this.printerID = printerID;
        this.paperLevel = paperLevel;
        this.tonerLevel = tonerLevel;
        this.docsPrinted = docsPrinted;
    }

    @Override
    public synchronized void printDocument(Document document) {
      int numberOfPages = document.getNumberOfPages();
      boolean isWaited = false;
      while(!(numberOfPages < paperLevel && numberOfPages < tonerLevel)) {
          if (isWaited) {
              return;
          }
          try {
              wait(5000);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          isWaited = true;
      }
      paperLevel -= numberOfPages;
      tonerLevel -= numberOfPages;
      docsPrinted ++;
        System.out.println(document.getUserID() + ": Printed " + document.getDocumentName() + " consist of " + document.getNumberOfPages());
        System.out.println(this + "\n");
      notifyAll();
    }

    @Override
    public synchronized void replaceTonerCartridge() {  //replacing the tonerCartridge
        boolean isWaited = false;
        while(tonerLevel > Minimum_Toner_Level) {
            if (isWaited) {
                return;
            }
            try {
                wait(5000);  //wait 5s
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isWaited = true;
        }
        tonerLevel = Full_Toner_Level;
        this.tonerRefillingTimes++;
        System.out.println("Toner Cartridge replaced by the toner technician");
        System.out.println(this + "\n");
        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {
        boolean isWaited = false;
        while(!(paperLevel + SheetsPerPack > Full_Paper_Tray)) {
            if (isWaited){
                break;
            }
            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isWaited = true;
        }
        paperLevel += SheetsPerPack;
        this.paperRefillingTimes++;  //paper refilling increment by 1
        System.out.println("Paper Packs are refilled by the paper technician");
        System.out.println(this + "\n");
        notifyAll();
    }


    @Override
    public String toString() {
        return new String( "Document[ "  +
                "PrinterID: " + printerID        + ", " +
                "Paper Level: "   + paperLevel  + ", " +
                "Toner Level: "  + tonerLevel + ", " +
                "Documents Printed: " + docsPrinted +
                "]"  ) ;
    }
}
