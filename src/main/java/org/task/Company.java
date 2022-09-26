package org.task;

public class Company {
    private String companyName;
    private String country;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Company() {
    }

    public Company(String companyName, String country) {
        this.companyName = companyName;
        this.country = country;

    }

    public static Company addCompany(String companyName, String country){
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setCountry(country);
        return company;
    }

    @Override
    public String toString() {
        return "Company Name:" + "\"" + companyName + "\"" +
                ",Company Country:" + "\"" + country + "\"";
    }
}
