package carsharing;

import java.util.List;

class Car {
    public Car(String name, int id) {
        String str = String.format("INSERT INTO CAR(NAME, COMPANY_ID) VALUES ('%s','%d')", name, id);
        Main.apply(str);
    }

    static List<String[]> getCarList(String idCompany) {
        String str = String.format("SELECT id, name from CAR where COMPANY_ID='%d' AND car.ID NOT IN (SELECT RENTED_CAR_ID from CUSTOMER where RENTED_CAR_ID IS NOT NULL)", Integer.parseInt(idCompany));
        return Main.getStuff(str);
    }

    static List<String[]> getRentedCars(int idCustomer) {
        String str = String.format("SELECT car.NAME, company.NAME from CAR, COMPANY, CUSTOMER " +
                "WHERE car.COMPANY_ID = company.ID AND customer.RENTED_CAR_ID = car.ID " +
                " AND customer.ID = '%d'", idCustomer);
        return Main.getStuff(str);
    }

    static String getCarName(int id) {
        String str = String.format("SELECT * from CAR where id='%d'", id);
        List<String[]> get = Main.getStuff(str);
        String[] ret = get.get(0);
        return ret[1];
    }
}
