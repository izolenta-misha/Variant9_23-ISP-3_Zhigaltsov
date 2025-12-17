// 4. Исключение для дублирования услуги
class DuplicateServiceException extends ServiceException {
    private final String serviceId;

    public DuplicateServiceException(String serviceId) {
        super(String.format("Услуга с ID '%s' уже существует", serviceId));
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }
}
