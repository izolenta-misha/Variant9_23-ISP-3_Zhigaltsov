// 5. Исключение для неактивной услуги
class ServiceInactiveException extends ServiceException {
    private final String serviceId;

    public ServiceInactiveException(String serviceId) {
        super(String.format("Услуга с ID '%s' не активна", serviceId));
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }
}
