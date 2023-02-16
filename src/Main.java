import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Main {


    public interface Comparator<T> extends java.util.Comparator<Integer> {

        int compare(Integer o1, Integer o2);

    }

    public interface Comparator2<T> extends java.util.Comparator<Integer> {

        int compare(Integer o1, Integer o2);

    }

    public interface BiConsumer <T,T2>{
        void accept(Integer t, Integer t2);
    }

    public interface Predicate<T> extends java.util.function.Predicate<Integer> {

        boolean test(Integer integer);
    }

    public interface Consumer<T>{
        void accept(Integer t);
    }


    public static void findMinMax(Stream <Integer> stream,Comparator <Integer> order,BiConsumer <Integer, Integer> minMaxConsumer){
        ArrayList<Integer> sortedElements = stream.sorted(order).collect(Collectors.toCollection(ArrayList::new));
        minMaxConsumer.accept(sortedElements.get(0), sortedElements.get(sortedElements.size()-1));
    }

    public static void findNums(Stream<Integer> stream, Predicate <Integer> predicate,Comparator2 <Integer> order, Consumer<Integer> numsConsumer){
        ArrayList<Integer> sortedElements = stream.sorted(order).takeWhile(predicate).collect(Collectors.toCollection(ArrayList::new));
        numsConsumer.accept(sortedElements.size());
        for (Integer el:sortedElements
        ) {
            System.out.println(el);
        }
    }
    public static void main(String[] args) {
        //task 1
        ArrayList<Integer> elements = new ArrayList<>();
        for (int i =1;i<=20;i++){
            elements.add((int) (Math.random() * (100)));
        }

        Stream<Integer> stream = elements.stream();
        BiConsumer <Integer, Integer> minMaxConsumer = new BiConsumer <Integer, Integer>() {
            @Override
            public void accept(Integer min, Integer max) {
                System.out.println("Min: "+min+" Max: "+max);
            }
        };

        Comparator<Integer> order = new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if (t1 > t2) {
                    return 1;
                } else if (t1 < t2) {
                    return -1;
                }
                return 0;
            }
        };
        findMinMax(stream,order,minMaxConsumer);
        //task2
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if(integer % 2 == 0){
                    return true;
                }
                else return false;
            }
        };

        Consumer<Integer> numsConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer t) {
                System.out.println("Кол-во четных чисел: "+t);
            }
        };

        Comparator2<Integer> order2 = new Comparator2<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if ((t1 %2==0) && (t2%2!=0)) {
                    return -1;
                } else if ((t2 %2==0) && (t1%2!=0)) {
                    return 1;
                }
                return 0;
            }
        };

        Stream<Integer> stream2 = elements.stream();

        findNums(stream2,predicate,order2,numsConsumer);
        System.out.println("\n Массив элементов:");
        for (Integer el:elements
             ) {
            System.out.println(el);
        }
    }
}