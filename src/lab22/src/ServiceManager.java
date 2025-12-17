import java.util.ArrayList;
import java.util.List;

// 8. Менеджер услуг с ограничениями
class ServiceManager {
    private static final int MAX_SERVICES = 10;
    private static final int MAX_PRICE_PER_CATEGORY = 50000;

    private List<Service> services;

    public ServiceManager() {
        services = new ArrayList<>();
    }

    public void addService(Service service) throws ServiceException {
        // Проверка лимита
        if (services.size() >= MAX_SERVICES) {
            throw new ServiceLimitExceededException(services.size(), MAX_SERVICES);
        }

        // Проверка на дублирование ID
        for (Service s : services) {
            if (s.getId().equals(service.getId())) {
                throw new DuplicateServiceException(service.getId());
            }
        }

        // Проверка лимита цены для категории
        checkCategoryPriceLimit(service);

        services.add(service);
        System.out.println("Услуга успешно добавлена: " + service.getName());
    }

    private void checkCategoryPriceLimit(Service newService) throws ServiceException {
        double totalCategoryPrice = newService.getPrice();

        for (Service s : services) {
            if (s.getCategory() == newService.getCategory()) {
                totalCategoryPrice += s.getPrice();
            }
        }

        if (totalCategoryPrice > MAX_PRICE_PER_CATEGORY) {
            throw new ServiceException(
                    String.format("Превышен лимит цены для категории '%s'. Текущая сумма: %.2f, Лимит: %d",
                            newService.getCategory().getName(), totalCategoryPrice, MAX_PRICE_PER_CATEGORY)
            );
        }
    }

    public Service findService(String id) throws ServiceException {
        for (Service service : services) {
            if (service.getId().equals(id)) {
                if (!service.isActive()) {
                    throw new ServiceInactiveException(id);
                }
                return service;
            }
        }
        throw new ServiceException("Услуга с ID '" + id + "' не найдена");
    }

    public void deactivateService(String id) throws ServiceException {
        Service service = findService(id);
        service.setActive(false);
        System.out.println("Услуга деактивирована: " + service.getName());
    }

    public List<Service> getAllServices() {
        return new ArrayList<>(services);
    }

    public List<Service> getActiveServices() {
        List<Service> activeServices = new ArrayList<>();
        for (Service service : services) {
            if (service.isActive()) {
                activeServices.add(service);
            }
        }
        return activeServices;
    }

    public double calculateTotalRevenue() {
        double total = 0;
        for (Service service : services) {
            if (service.isActive()) {
                total += service.getPrice();
            }
        }
        return total;
    }
}
