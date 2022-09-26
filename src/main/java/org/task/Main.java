package org.task;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.task.SouvenirsBase.addSouvenirsToList;

public class Main {
    static SouvenirsBase souvenirsBase = new SouvenirsBase();
    static Scanner readLine = new Scanner((System.in));

    public static void main(String[] args) {
        List<Souvenir> fileData = new ArrayList<>();
        fileData = SouvenirsBase.getAllSouvenirs(fileData);
        System.out.println(Arrays.toString(fileData.toArray()));
        addSouvenirsToList(fileData);
        int option;
        while (true){
            System.out.println("1. Show Companies and their Souvenirs");
            System.out.println("2. Add a new souvenir");
            System.out.println("3. Edit a souvenir");
            System.out.println("4. Remove a souvenir");
            System.out.println("5. Filter Menu");
            System.out.println("6. Close");
            option = Integer.parseInt(readLine.nextLine());
            switch (option){
                case 1:
                    SouvenirsBase.getCompaniesAndTheirSouvenirs();
                    break;
                case 2:
                    Souvenir newSouvenir = addNewSouvenir();
                    souvenirsBase.addSouvenirToList(newSouvenir);
                    Souvenir.writeSouvenirToFile(newSouvenir);
                    break;
                case 3:
                    Souvenir edited =  editSouvenir();
                    Souvenir.writeSouvenirToFile(edited);
                    break;
                case 4:
                    removeSouvenir();
                    break;
                case 5:
                    filterSouvenirsAndCompanies();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }


    public static Souvenir addNewSouvenir(){
        String nameOfSouvenir;
        Company companyRequisites = new Company();
        LocalDate manufactureDate;
        double price;

        System.out.println("Souvenir Name: ");
        nameOfSouvenir = readLine.nextLine();

        System.out.println("Company Requisites: ");
        companyRequisites = addNewCompany();

        System.out.println("Manufacture Date: ");

        manufactureDate = LocalDate.parse(readLine.nextLine());

        System.out.print("Enter price: ");
        price = Double.parseDouble(readLine.nextLine());

        return Souvenir.addSouvenir(nameOfSouvenir,companyRequisites,manufactureDate,price);
    }
    public static Company addNewCompany() {
        String name, country;
        System.out.println();
        System.out.print("Enter company name: ");
        name = readLine.nextLine();
        System.out.print("Enter company country: ");
        country = readLine.nextLine();
        return Company.addCompany(name, country);
    }
    public static Souvenir editSouvenir() {
        System.out.print("Enter souvenir name: ");
        String souvenirName = readLine.nextLine();
        System.out.print("Enter company: ");
        String companyName = readLine.nextLine();
        Souvenir filterSouvenir = souvenirsBase.getListOfSouvenirs().stream()
                .filter(e -> e.getSouvenirName().equals(souvenirName) && e.getCompanyRequisites().getCompanyName().equals(companyName))
                .findFirst()
                .orElse(null);
        if(filterSouvenir!=null) {
            int index = souvenirsBase.getListOfSouvenirs().indexOf(filterSouvenir);
            Souvenir.removeSouvenirFromFile(index+1);
            filterSouvenir = Souvenir.editSouvenir(filterSouvenir);
            souvenirsBase.getListOfSouvenirs().get(index).setSouvenirName(filterSouvenir.getSouvenirName());
            souvenirsBase.getListOfSouvenirs().get(index).setCompanyRequisites(filterSouvenir.getCompanyRequisites());
            souvenirsBase.getListOfSouvenirs().get(index).setManufactureDate(filterSouvenir.getManufactureDate());
            souvenirsBase.getListOfSouvenirs().get(index).setPrice(filterSouvenir.getPrice());
        } else {
            System.out.println("Specified souvenir was not found");
        }
        return filterSouvenir;
    }
    public static void removeSouvenir() {
        System.out.print("Enter company name: ");
        String name = readLine.nextLine();
        System.out.print("Enter company country: ");
        String companyCountry = readLine.nextLine();
        Souvenir removeItem = souvenirsBase.getListOfSouvenirs().stream()
                .filter(e -> e.getCompanyRequisites().getCompanyName().equals(name) && e.getCompanyRequisites().getCountry().equals(companyCountry))
                .findFirst()
                .orElse(null);
        if (removeItem != null) {
            int index = souvenirsBase.getListOfSouvenirs().indexOf(removeItem);
            souvenirsBase.getListOfSouvenirs().remove(index);
            Souvenir.removeSouvenirFromFile(index + 1);
            }else {
            System.out.println("Specified souvenir was not found");
        }

    }
    public static void filterSouvenirsAndCompanies(){

        while (true){
            System.out.println("1. Filter souvenirs by company");
            System.out.println("2. Filter souvenirs by company country");
            System.out.println("3. Filter companies by souvenir price");
            System.out.println("4. Filter by companies and souvenirs they created");
            System.out.println("5. Filter companies by entered year");
            System.out.println("6. Filter souvenirs by manufactured year");
            System.out.println("7. Exit");
            int option = Integer.parseInt(readLine.nextLine());
            switch (option){
                case 1:
                    filterSouvenirsByCompany();
                    break;
                case 2:
                    filterSouvenirsByCompanyCountry();
                    break;
                case 3:
                    filterBySouvenirPrice();
                    break;
                case 4:
                    filterByCompaniesSouvenirs();
                    break;
                case 5:
                    filterBySouvenirYear();
                    break;
                case 6:
                    filterByManufacturedYear();
                    break;
                case 7:
                    System.exit(0);
            }
        }

    }


    public static void filterSouvenirsByCompany() {
        System.out.print("Enter company name: ");
        String companyName = readLine.nextLine();
        System.out.print("Enter company country: ");
        String companyCountry = readLine.nextLine();
        List<Souvenir> filterList = souvenirsBase.getListOfSouvenirs().stream()
                .filter(e -> e.getCompanyRequisites().getCompanyName().equals(companyName) && e.getCompanyRequisites().getCountry().equals(companyCountry))
                .collect(Collectors.toList());
        if(filterList.isEmpty()){
            System.out.println("Specified souvenir was not found");
        }
        System.out.println(filterList);
    }

    private static void filterSouvenirsByCompanyCountry() {
        System.out.print("Enter company country: ");
        String companyCountry = readLine.nextLine();
        List<Souvenir> filterList = souvenirsBase.getListOfSouvenirs().stream()
                .filter(e -> e.getCompanyRequisites().getCountry().equals(companyCountry))
                .collect(Collectors.toList());
        if(filterList.isEmpty()){
            System.out.println("Specified souvenir was not found");
        }
        System.out.println(filterList);
    }

    private static void filterBySouvenirPrice() {
        System.out.print("Enter souvenir name: ");
        String souvenirName = readLine.nextLine();
        System.out.print("Enter maximum value of souvenir price: ");
        double maxPrice = Double.parseDouble(readLine.nextLine());
        List<Company> filterList = souvenirsBase.getListOfSouvenirs().stream()
                .filter(e -> e.getSouvenirName().equals(souvenirName)&& e.getPrice()<=maxPrice)
                .map(Souvenir::getCompanyRequisites)
                .distinct()
                .collect(Collectors.toList());
        if(filterList.isEmpty()){
            System.out.println("Specified company was not found");
        }
        System.out.println(filterList);
    }
    private static void filterByCompaniesSouvenirs() {
        Map<Company, List<Souvenir>> filterList = souvenirsBase.getListOfSouvenirs().stream()
                .collect(Collectors.groupingBy(Souvenir::getCompanyRequisites));
        System.out.println(filterList);
    }
    private static void filterBySouvenirYear() {
        System.out.print("Enter souvenir name: ");
        String souvenirName = readLine.nextLine();
        System.out.print("Enter manufacture year: ");
        int year = Integer.parseInt(readLine.nextLine());
        List<Company> filterList= souvenirsBase.getListOfSouvenirs().stream()
                .filter(e->e.getManufactureDate().getYear()==year && e.getSouvenirName().equals(souvenirName))
                .map(Souvenir::getCompanyRequisites)
                .distinct()
                .collect(Collectors.toList());
        if(filterList.isEmpty()){
            System.out.println("Specified souvenir was not found");
        }
        System.out.println(filterList);
    }

    private static void filterByManufacturedYear() {
        Map<Integer, List<Souvenir>> filterList = souvenirsBase.getListOfSouvenirs().stream()
                .collect(Collectors.groupingBy(e->e.getManufactureDate().getYear()));
        System.out.println(filterList);
    }
}
