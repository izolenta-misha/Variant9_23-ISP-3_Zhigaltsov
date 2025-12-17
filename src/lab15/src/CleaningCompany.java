public class CleaningCompany {

    class Cleaner {
        private String name;
        private int experience;

        public Cleaner(String name, int experience) {
            this.name = name;
            this.experience = experience;
        }

        public String getInfo() {
            return "Сотрудник: " + name + ", опыт: " + experience + " лет";
        }
        
        public void assignService(AbstractService service) {
            System.out.println(name + " назначен на услугу: " + service.getName());
        }
    }


    public static class Address {
        private String street;
        private String city;
        private String zip;

        public Address(String street, String city, String zip) {
            this.street = street;
            this.city = city;
            this.zip = zip;
        }

        public String getFullAddress() {
            return street + ", " + city + " (" + zip + ")";
        }
    }

    private String companyName;
    private Address companyAddress;

    public CleaningCompany(String name, Address address) {
        this.companyName = name;
        this.companyAddress = address;
    }

    public void hireCleaner(String name, int experience) {
        Cleaner newCleaner = new Cleaner(name, experience);

    }

    public static void main(String[] args) {

        Address companyAddress = new Address("ул. Ленина", "Брянск", "241019");


        CleaningCompany company = new CleaningCompany("Чистый дом", companyAddress);


        CleaningCompany.Cleaner cleaner = company.new Cleaner("Жигальцов М.А.", 5);

        ConcreteService service = new ConcreteService(
                AbstractService.generateID(),
                "Обычная уборка",
                1500,
                2,
                0.1
        );


        System.out.println("Компания: " + company.companyName);
        System.out.println("Адрес компании: " + company.companyAddress.getFullAddress());
        System.out.println(cleaner.getInfo());

        cleaner.assignService(service);
        System.out.println(service);
        System.out.println("Стоимость: " + service.calculateCost());
    }
}
