import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static final int ASSEMBLY_TIME = 400;
    public static final int BUYER_TIMEOUT = 300;
    public static final int CAR_LIMIT = 10;

    public static void main(String[] args) {
        List<Car> carsAvailable = new ArrayList<>();

        new Thread(() -> {
            for (int i = 0; i < CAR_LIMIT; i++) {
                try {
                    Thread.sleep(ASSEMBLY_TIME);
                } catch (InterruptedException e) {
                    return;
                }
                synchronized (carsAvailable) {
                    Car car = new Car(Brand.values()[new Random().nextInt(CAR_LIMIT / 2)].name(),
                            Colors.values()[new Random().nextInt(CAR_LIMIT / 2)].name());
                    System.out.println("В салон привезли " + car);
                    carsAvailable.add(car);
                    carsAvailable.notify();
                }
            }
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Гоголь");
            for (int i = 0; i < CAR_LIMIT; i++) {
                synchronized (carsAvailable) {
                    System.out.println("Покупатель " + Thread.currentThread().getName() + " зашёл в салон");
                    if (carsAvailable.isEmpty()) {
                        System.out.println("Машин нет");
                        try {
                            carsAvailable.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " уехал на новенькой " + carsAvailable.remove(0));
                    try {
                        Thread.sleep(BUYER_TIMEOUT);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Достоевский");
            for (int i = 0; i < CAR_LIMIT; i++) {
                synchronized (carsAvailable) {
                    System.out.println("Покупатель " + Thread.currentThread().getName() + " зашёл в салон");
                    if (carsAvailable.isEmpty()) {
                        System.out.println("Машин нет");
                        try {
                            carsAvailable.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " уехал на новенькой " + carsAvailable.remove(0));
                    try {
                        Thread.sleep(BUYER_TIMEOUT);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Ремарк");
            for (int i = 0; i < CAR_LIMIT; i++) {
                synchronized (carsAvailable) {
                    System.out.println("Покупатель " + Thread.currentThread().getName() + " зашёл в салон");
                    if (carsAvailable.isEmpty()) {
                        System.out.println("Машин нет");
                        try {
                            carsAvailable.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " уехал на новенькой " + carsAvailable.remove(0));
                    try {
                        Thread.sleep(BUYER_TIMEOUT);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
