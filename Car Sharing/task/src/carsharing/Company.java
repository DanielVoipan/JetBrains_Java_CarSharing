package carsharing;


import java.util.List;

class Company {

    public Company(String name) {
        String str = String.format("INSERT INTO COMPANY(NAME) VALUES ('%s')", name);
        Main.apply(str);
    }

    static String getCompanyName(int id) {
        String str = String.format("SELECT * from COMPANY where id='%d'", id);
        List<String[]> get = Main.getStuff(str);
        String[] ret = get.get(0);
        return ret[1];
    }

    // get companies
    static List<String[]> getCompanies() {
        String str = "SELECT id, name from COMPANY;";
        return Main.getStuff(str);
    }
}
