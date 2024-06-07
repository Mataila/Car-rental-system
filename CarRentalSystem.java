import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Car {
    private String carId;
    private String make;
    private String model;
    private int year;
    private double rentalPrice;
    private boolean isAvailable;

    public Car(String carId, String make, String model, int year, double rentalPrice) {
        this.carId = carId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.rentalPrice = rentalPrice;
        this.isAvailable = true;
    }

    public String getCarId() { return carId; }
    public void setCarId(String carId) { this.carId = carId; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public double getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(double rentalPrice) { this.rentalPrice = rentalPrice; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", rentalPrice=" + rentalPrice +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}


class RentalTransaction {
    private String transactionId;
    private Car car;
    private Customer customer;
    private Date rentalDate;
    private Date returnDate;

    public RentalTransaction(String transactionId, Car car, Customer customer, Date rentalDate) {
        this.transactionId = transactionId;
        this.car = car;
        this.customer = customer;
        this.rentalDate = rentalDate;
    }

    
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Date getRentalDate() { return rentalDate; }
    public void setRentalDate(Date rentalDate) { this.rentalDate = rentalDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "RentalTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", car=" + car +
                ", customer=" + customer +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                '}';
    }
}


class RentalAgency {
    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<RentalTransaction> rentals = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(String carId, String customerId) {
        Car car = findCar(carId);
        Customer customer = findCustomer(customerId);

        if (car != null && customer != null && car.isAvailable()) {
            car.setAvailable(false);
            RentalTransaction rental = new RentalTransaction("TX" + (rentals.size() + 1), car, customer, new Date());
            rentals.add(rental);
            System.out.println("Car rented successfully: " + rental);
        } else {
            System.out.println("Car rental failed.");
        }
    }

    public void returnCar(String carId) {
        Car car = findCar(carId);
        if (car != null) {
            car.setAvailable(true);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("Car return failed.");
        }
    }

    public void listAvailableCars() {
        cars.stream().filter((car) -> (car.isAvailable())).forEachOrdered((car) -> {
            System.out.println(car);
        });
    }

    private Car findCar(String carId) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId)) {
                return car;
            }
        }
        return null;
    }

    private Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}


public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        agency.addCar(new Car("V8 Black", "Land Cruiser", "Toyota", 2008, 3.9));
        agency.addCar(new Car("PM", "Harrier", "Toyota", 2016, 3.65));
        agency.addCar(new Car("XT", "Subaru", "Forester", 2010, 1.6));
        agency.addCar(new Car("NS", "X-Trail", "Nissan", 2015, 2.5));
        agency.addCar(new Car("CGI", "Mercedes-Benz", "Toyota", 2011, 1.77));
        agency.addCar(new Car("HF", "Honda-Fit", "Toyota", 2014, 1.1));
       
        
        agency.addCustomer(new Customer("V8 Black", "James Kimatu", "jimmieits@example.com", "5648390385"));
        agency.addCustomer(new Customer("PM", "Faith Wamae", "faywangui@example.com", "1209567843"));
        agency.addCustomer(new Customer("XT", "Josphat Tama", "joset@example.com", "00076435896"));
        agency.addCustomer(new Customer("NS", "Damaris Waeni", "damahk@example.com", "3567843629"));
        agency.addCustomer(new Customer("CGI", "Dominic Mutinda", "dominicma@example.com", "67480463856"));
        agency.addCustomer(new Customer("HF", "Wangari Mathai", "mathaiwa@example.com", "7654325678"));

        System.out.println("Available cars:");
        agency.listAvailableCars();

        agency.rentCar("XT", "CGI");

        System.out.println("Available cars after rental:");
        agency.listAvailableCars();

        agency.returnCar("XT");

        
        System.out.println("Available cars after return:");
        agency.listAvailableCars();
    }
}