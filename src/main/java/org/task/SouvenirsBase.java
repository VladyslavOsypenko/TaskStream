package org.task;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SouvenirsBase {
    private static List<Souvenir> listOfSouvenirs = new ArrayList<>();

    public List<Souvenir> getListOfSouvenirs() {
        return listOfSouvenirs;
    }

    public SouvenirsBase() {
    }




    public static void getCompaniesAndTheirSouvenirs(){
        File file = new File("src/res/files/souvenirs");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
                String line = null;
                while ((line = br.readLine()) != null){
                    System.out.println(line);
                }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public  void addSouvenirToList (Souvenir souvenir) {
        if(!listOfSouvenirs.contains(souvenir)){
            listOfSouvenirs.add(souvenir);
        }
    }


    public static void addSouvenirsToList (List <Souvenir> souvenir) {
        if(!listOfSouvenirs.contains(souvenir)){
            listOfSouvenirs.addAll(souvenir);
        }
    }

    public static List<Souvenir> getAllSouvenirs(List <Souvenir> souvenirList ) {
        Pattern name = Pattern.compile("(Souvenir Name):\"([^',]*)\"");
        Pattern company = Pattern.compile("(Company Name):\"([^',]*)\"");
        Pattern country = Pattern.compile("(Company Country):\"([^',]*)\"");;
        Pattern date = Pattern.compile("(Manufacture Date):\"([^',]*)\"");;
        Pattern price = Pattern.compile("(Price):\"([^',]*)\"");;
        File file = new File("src/res/files/souvenirs");
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = null;
            souvenirList = new ArrayList<>();
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null){
                lines.clear();
                lines.add(line);
                Optional<String> message = lines.stream().filter(company.asPredicate()).findFirst();
                if (message.isPresent()) {
                    Matcher matcher1 = name.matcher(message.get());
                    Matcher matcher2 = company.matcher(message.get());
                    Matcher matcher3 = country.matcher(message.get());
                    Matcher matcher4 = date.matcher(message.get());
                    Matcher matcher5 = price.matcher(message.get());
                    matcher1.find();
                    matcher2.find();
                    matcher3.find();
                    matcher4.find();
                    matcher5.find();
                    String souvenirName = matcher1.group(2);
                    String companyName = matcher2.group(2);
                    String companyCountry = matcher3.group(2);
                    String manufactureDate = matcher4.group(2);
                    String souvenirPrice = matcher5.group(2);
                    souvenirList.add(new Souvenir(souvenirName,new Company(companyName,companyCountry),LocalDate.parse(manufactureDate),Double.parseDouble(souvenirPrice)));

                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return souvenirList;
    }

        @Override
    public String toString() {
        return "Souvenirs{" +
                "listOfSouvenirs=" + listOfSouvenirs +
                '}';
    }
}
