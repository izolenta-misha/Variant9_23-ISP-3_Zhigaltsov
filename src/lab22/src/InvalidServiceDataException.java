// 2. Исключение для некорректных данных услуги
class InvalidServiceDataException extends ServiceException {
    private final String fieldName;
    private final Object invalidValue;

    public InvalidServiceDataException(String message, String fieldName, Object invalidValue) {
        super(message);
        this.fieldName = fieldName;
        this.invalidValue = invalidValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public String getMessage() {
        return String.format("%s (Поле: '%s', Некорректное значение: '%s')",
                super.getMessage(), fieldName, invalidValue);
    }
}
