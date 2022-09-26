package org.task;

import java.io.*;
import java.time.LocalDate;

import static java.lang.Double.parseDouble;
import static org.task.Main.readLine;

public class Souvenir {
    private String souvenirName;
    private Company companyRequisites;
    private LocalDate manufactureDate;
    private double price;


    public Souvenir(String souvenirName, Company companyRequisites, LocalDate manufactureDate, double price) {
        this.souvenirName = souvenirName;
        this.companyRequisites = companyRequisites;
        this.manufactureDate = manufactureDate;
        this.price = price;
    }

    public String getSouvenirName() {
        return souvenirName;
    }

    public void setSouvenirName(String souvenirName) {
        this.souvenirName = souvenirName;
    }

    public Company getCompanyRequisites() {
        return companyRequisites;
    }

    public void setCompanyRequisites(Company companyRequisites) {
        this.companyRequisites = companyRequisites;
    }

    public LocalDate getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Souvenir() {
    }



    public static void writeSouvenirToFile(Souvenir souvenir) {
        if (souvenir != null) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/res/files/souvenirs",true))) {
                bw.write("Souvenir Name:" + "\"" + souvenir.getSouvenirName() + "\"" + ","  + souvenir.getCompanyRequisites()   + "," + "Manufacture Date:" + "\"" + souvenir.getManufactureDate() + "\"" + "," + "Price:" + "\"" + souvenir.getPrice() + "\"");
                bw.newLine();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void removeSouvenirFromFile (int deleteLine) {
            String tempFile = "temp.txt";
            File oldFile = new File("src/res/files/souvenirs");
            File newFile = new File(tempFile);
            int line = 0;
            String currenLine;
            try (FileWriter fw = new FileWriter(tempFile,true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw);
                 FileReader fr = new FileReader("src/res/files/souvenirs");
                 BufferedReader br = new BufferedReader(fr)) {
                while ((currenLine = br.readLine()) != null){
                    line++;
                    if(deleteLine != line){
                        pw.println(currenLine);
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            }
            oldFile.delete();
            File dump = new File("src/res/files/souvenirs");
            newFile.renameTo(dump);
        }

        public static Souvenir addSouvenir (String souvenirName, Company companyRequisites, LocalDate manufactureDate, double price){
        Souvenir souvenir = new Souvenir();
        souvenir.setSouvenirName(souvenirName);
        souvenir.setCompanyRequisites(companyRequisites);
        souvenir.setManufactureDate(manufactureDate);
        souvenir.setPrice((price));
        return souvenir;
    }

    @Override
    public String toString() {
        return "souvenirName: " + "\""  + souvenirName + "\""  +
                ", "  + companyRequisites +
                ", manufactureDate: " + "\"" + manufactureDate + "\"" +
                ", price:" + price;
    }
    public static Souvenir editSouvenir(Souvenir filterSouvenir) {
        Souvenir modifiedSouvenir=new Souvenir();
        modifiedSouvenir.setSouvenirName(filterSouvenir.getSouvenirName());
        modifiedSouvenir.setCompanyRequisites(filterSouvenir.getCompanyRequisites());
        modifiedSouvenir.setManufactureDate(filterSouvenir.getManufactureDate());
        modifiedSouvenir.setPrice(filterSouvenir.getPrice());
        System.out.print("Enter new souvenir name: ");
        String line = readLine.nextLine();
        if(line != null && !line.isEmpty()) {
            modifiedSouvenir.setSouvenirName(line);
        }
        System.out.print("Enter new company name: ");
        line = readLine.nextLine();
        if(line != null && !line.isEmpty()) {
            modifiedSouvenir.getCompanyRequisites().setCompanyName(line);
        }
        System.out.print("Enter new company country: ");
        line = readLine.nextLine();
        if(line != null && !line.isEmpty()) {
            modifiedSouvenir.getCompanyRequisites().setCountry(line);
        }
        System.out.print("Enter new manufacture date: ");
        line = readLine.nextLine();
        if(line != null && !line.isEmpty()) {
            LocalDate dateTime = LocalDate.parse(line);
            modifiedSouvenir.setManufactureDate(dateTime);
        }
        System.out.print("Enter new price: ");
        line = readLine.nextLine();
        if(line != null && !line.isEmpty()) {
            modifiedSouvenir.setPrice(parseDouble(line));
        }
        return modifiedSouvenir;
    }
}
