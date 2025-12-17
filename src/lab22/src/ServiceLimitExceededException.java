// 3. Исключение для превышения лимита услуг
class ServiceLimitExceededException extends ServiceException {
    private final int currentCount;
    private final int maxLimit;

    public ServiceLimitExceededException(int currentCount, int maxLimit) {
        super(String.format("Превышен лимит услуг: %d из %d", currentCount, maxLimit));
        this.currentCount = currentCount;
        this.maxLimit = maxLimit;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxLimit() {
        return maxLimit;
    }
}
