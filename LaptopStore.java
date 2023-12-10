import java.util.*;

class Laptop {
    private String brand;
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // Геттеры для доступа к полям

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore(Set<Laptop> laptops) {
        this.laptops = laptops;
    }

    // Метод для фильтрации ноутбуков по заданным критериям
    public Set<Laptop> filterLaptops(Map<String, Object> filters) {
        Set<Laptop> result = new HashSet<>(laptops);

        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String criterion = entry.getKey().toLowerCase();

            switch (criterion) {
                case "brand":
                    String targetBrand = entry.getValue().toString().toLowerCase(); // приводим к нижнему регистру
                    result.removeIf(laptop -> !laptop.getBrand().toLowerCase().equals(targetBrand));
                    break;
                case "ram":
                    result.removeIf(laptop -> laptop.getRam() < (int) entry.getValue());
                    break;
                case "storage":
                    result.removeIf(laptop -> laptop.getStorage() < (int) entry.getValue());
                    break;
                case "os":
                    String targetOs = entry.getValue().toString().toLowerCase(); // приводим к нижнему регистру
                    result.removeIf(laptop -> !laptop.getOs().toLowerCase().equals(targetOs));
                    break;

                case "color":
                    String targetColor = entry.getValue().toString().toLowerCase(); // приводим к нижнему регистру
                    result.removeIf(laptop -> !laptop.getColor().toLowerCase().equals(targetColor));
                    break;
                // Добавьте дополнительные критерии по необходимости
                default:
                    System.out.println("Неизвестный критерий: " + criterion);
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Пример использования
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("HP", 8, 512, "Windows", "Silver"));
        laptops.add(new Laptop("Dell", 16, 1024, "Linux", "Black"));
        laptops.add(new Laptop("Lenovo", 12, 256, "Windows", "Gray"));

        LaptopStore laptopStore = new LaptopStore(laptops);

        try (Scanner scanner = new Scanner(System.in)) {
            Map<String, Object> filters = new HashMap<>();

            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - Бренд");
            System.out.println("2 - ОЗУ");
            System.out.println("3 - Объем ЖД");
            System.out.println("4 - Операционная система");
            System.out.println("5 - Цвет");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем перевод строки после ввода числа

            switch (choice) {
                case 1:
                    System.out.println("Введите бренд:");
                    String brand = scanner.nextLine();
                    filters.put("brand", brand);
                    break;
                case 2:
                    System.out.println("Введите минимальное количество ОЗУ:");
                    int ram = scanner.nextInt();
                    filters.put("ram", ram);
                    break;
                case 3:
                    System.out.println("Введите минимальный объем ЖД:");
                    int storage = scanner.nextInt();
                    filters.put("storage", storage);
                    break;
                case 4:
                    System.out.println("Введите операционную систему:");
                    String os = scanner.nextLine();
                    filters.put("os", os);
                    break;
                case 5:
                    System.out.println("Введите цвет:");
                    String color = scanner.nextLine();
                    filters.put("color", color);
                    break;
                default:
                    System.out.println("Некорректный ввод");
                    return;
            }

            Set<Laptop> filteredLaptops = laptopStore.filterLaptops(filters);

            System.out.println("Результаты фильтрации:");
            for (Laptop laptop : filteredLaptops) {
                System.out.println("Бренд: " + laptop.getBrand() + ", ОЗУ: " + laptop.getRam() +
                        ", Объем ЖД: " + laptop.getStorage() + ", Операционная система: " +
                        laptop.getOs() + ", Цвет: " + laptop.getColor());
            }
        }
    }
}
