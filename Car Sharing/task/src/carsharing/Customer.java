package carsharing;

import java.util.List;

class Customer {

    public Customer(String name) {
        String str = String.format("INSERT INTO CUSTOMER(NAME) values ('%s')", name);
        Main.apply(str);
    }

    static List<String[]> getCustomerList() {
        String str = String.format("SELECT id, name from CUSTOMER");
        return Main.getStuff(str);
    }

    static void RentCar(int idCar, int idCostumer) {
        String str = String.format("UPDATE CUSTOMER SET RENTED_CAR_ID=%d WHERE ID=%d", idCar, idCostumer);
        Main.apply(str);
    }

    static void returnRentedCar(int idCustomer) {
        String str = String.format("UPDATE CUSTOMER SET RENTED_CAR_ID = NULL where ID = %d", idCustomer);
        Main.apply(str);
    }
}
