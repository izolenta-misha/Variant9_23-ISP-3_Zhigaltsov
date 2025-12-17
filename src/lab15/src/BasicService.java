    public abstract class BasicService extends AbstractService {
        protected double duration;

        public BasicService(int id, String name, double basePrice, double duration) {
            super(id, name, basePrice);
            this.duration = duration;
        }

        @Override
        public double calculateCost() {
            return basePrice * duration;
        }

        public abstract void applyDiscount();
    }
