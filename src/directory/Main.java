package directory;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static directory.CityParser.*;
public class Main {
    public static void main(String[] args) {
        String fileName = "./data/city_ru.csv";
        try {
            List<City> cities = readFile(fileName);
            Map<String, Long> result = findNumberOfCity(cities);
            result.forEach( (k, v) -> System.out.println(k + " - " + v));

            Map<String, Integer> region = findNumberCityByRegion(cities);
            region.forEach( (k, v) -> System.out.println(k + " - " + v));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**Нахождение количества городов в разрезе регионов c помощью lambda*/
    private static Map<String, Long> findNumberOfCity(List<City>cities){
        Map<String, Long> result = cities.stream().map(c -> c.getRegion())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return result;
    }

    /**
     * Поиск количества городов в каждом из регионов (old school)
     *
     * @param cities массив городов
     */
    private static Map<String, Integer> findNumberCityByRegion(List<City> cities) {
        Map<String, Integer> result = new HashMap<>();
        for (City city : cities) {
            if (!result.containsKey(city.getRegion())) {
                result.put(city.getRegion(), 1);
            } else {
                result.put(city.getRegion(), result.get(city.getRegion()) + 1);
            }
        }
        return result;
    }


    /**Метод быстрой сортировки
     * @param cities массив городов
     * @param from начальный индекс участка сортировки
     * @param  to конечный индекс участка сортировки*/
    public static void quickSort(City[] cities, int from, int to) {
        if (from < to) {
            int divideIndex = partition(cities, from, to);
            quickSort(cities, from, divideIndex - 1);
            quickSort(cities, divideIndex, to);
        }
    }

    /**Алгоритм сортировки
     * Выбирается опорный элемент и все значения меньше опорного перемещаются влево,
     * а значения больше опорного - вправо
     * @param cities - массив элементов
     * @param from - начальный индекс
     * @param to - конечный индекс
     * @return int - возвращает индекс опорного элемента
     * */
    private static int partition(City[] cities, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int pivot = cities[from + (to - from) / 2].getPopulation();
        while (leftIndex <= rightIndex) {

            while (cities[leftIndex].getPopulation() < pivot) {
                leftIndex++;
            }

            while (cities[rightIndex].getPopulation() > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(cities, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
    }

    private static void swap(City[] array, int index1, int index2) {
        City tmp  = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    /**
     * Нахождение максимального значения
     * @param cities - массив объектов класса город
     * @return  City -  объект города с максимальным количеством жителей
     */
    private static City maxPopulation(City[] cities){
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < cities.length; i++ ){
            int value = cities[i].getPopulation();
            if(value > max){
                index = i;
                max = value;
            }
        }
        return cities[index];
    }

    private static String print(City city){
        StringBuilder builder = new StringBuilder();
        builder
                .append("[")
                .append(city.getId() - 1)
                .append("] = ")
                .append(city);
        return builder.toString();
    }


    /**
     * Сортировка массива городов по наименованию в алфавитном порядке по убыванию
     * без учета регистра, используя {@link java.util.Comparator}
     * @param cities массив городов
     */
    private static void sortByNameV1(List<City>cities){
        cities.sort(Comparator.comparing(City::getName, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Сортировка массива городов по наименованию в алфавитном порядке по убыванию
     * без учета регистра, используя lambda-выражения
     * @param cities массив городов
     */
    private static void sortByNameV2(List<City>cities){
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
    }
    /**
     * Сортировка массива городов по федеральному округу и наименованию города внутри каждого федерального округа
     * в алфавитном порядке по убыванию с учетом регистра
     * @param cities массив городов
     */
    private static void sortByDistrictAndName(List<City>cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }
}
