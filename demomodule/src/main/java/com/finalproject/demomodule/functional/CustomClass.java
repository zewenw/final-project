package com.finalproject.demomodule.functional;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        super();
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String toString() {
        return name + ":" + noOfStudents + ":" + reviewScore;
    }

}

public class CustomClass {

    public static void main(String[] args) {
        List<Course> courses = List.of(new Course("Spring", "Framework", 96, 20000),
                new Course("Spring Boot", "Framework", 95, 18000), new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000), new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000), new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000));
        //all match
        Predicate<Course> greaterThan90Predicate = course -> course.getReviewScore() > 90;
        System.out.println(courses.stream().allMatch(greaterThan90Predicate));
        //any match
        System.out.println(courses.stream().anyMatch(greaterThan90Predicate));
        //any match
        Predicate<Course> lessThan70Predicate = course -> course.getReviewScore() < 70;
        System.out.println(courses.stream().noneMatch(lessThan70Predicate));

        //compare
        Comparator<Course> increasingComparator = Comparator.comparingInt(Course::getReviewScore);
        courses.stream()
                .sorted(increasingComparator)
                .forEach(System.out::println);

        Comparator<Course> decreasingComparator = Comparator.comparingInt(Course::getReviewScore).reversed();
        courses.stream()
                .sorted(decreasingComparator)
                .forEach(System.out::println);

        Comparator<Course> decreasingComparatorThenCompareNo = Comparator
                .comparingInt(Course::getReviewScore)
                .thenComparing(Course::getNoOfStudents)
                .reversed();
        courses.stream()
                .sorted(decreasingComparatorThenCompareNo)
                .forEach(System.out::println);

        //skip limit
//        System.out.println("==============================================");
        System.out.println(courses);
//        System.out.println("==============================================");
        System.out.println(courses.stream()
                .skip(1)
                .limit(1)
                .collect(Collectors.toList()));

        //takewhile dropwhile
        System.out.println("==============================================");
        System.out.println(courses.stream().sorted(decreasingComparator).collect(Collectors.toList()));
        System.out.println("==============================================");
        System.out.println(courses.stream()
                .sorted(decreasingComparator)
//                .takeWhile(x -> x.getReviewScore() > 95)
                .dropWhile(x -> x.getReviewScore() > 95)
                .collect(Collectors.toList()));

        //max
        System.out.println(
                courses.stream()
                        .max(decreasingComparatorThenCompareNo));

        //min
        System.out.println(
                courses.stream()
                        .min(decreasingComparatorThenCompareNo)
                        .orElse(new Course("Kubernetes", "Cloud", 91, 20000))
        );


        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .min(decreasingComparatorThenCompareNo)
                        .orElse(new Course("Kubernetes", "Cloud", 91, 20000))
        );

        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .findFirst()
        );//Optional.empty

        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .findAny()
        );//Optional[Spring:20000:98]

        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .mapToInt(Course::getNoOfStudents)
                        .sum());//88000

        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .mapToInt(Course::getNoOfStudents)
                        .average());//OptionalDouble[22000.0]

        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .mapToInt(Course::getNoOfStudents)
                        .count());//4

        System.out.println(
                courses.stream()
                        .filter(greaterThan90Predicate)
                        .mapToInt(Course::getNoOfStudents)
                        .max());//OptionalInt[25000]

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory)));
        //{Cloud=[AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91],
        //  FullStack=[FullStack:14000:91],
        // Microservices=[API:22000:97, Microservices:25000:96],
        // Framework=[Spring:20000:98, Spring Boot:18000:95]}

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory, Collectors.counting())));
        //{Cloud=4, FullStack=1, Microservices=2, Framework=2}

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory,
                                Collectors.maxBy(Comparator.comparing(Course::getReviewScore)))));
        //{Cloud=Optional[Azure:21000:99], FullStack=Optional[FullStack:14000:91], Microservices=Optional[API:22000:97], Framework=Optional[Spring:20000:98]}

        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory,
                                Collectors.mapping(Course::getName, Collectors.toList()))));
        //{Cloud=[AWS, Azure, Docker, Kubernetes], FullStack=[FullStack], Microservices=[API, Microservices], Framework=[Spring, Spring Boot]}


        Predicate<Course> reviewScoreGreaterThan95Predicate2
                = createPredicateWithCutoffReviewScore(95);

        Predicate<Course> reviewScoreGreaterThan90Predicate2
                = createPredicateWithCutoffReviewScore(90);

    }

    private static Predicate<Course> createPredicateWithCutoffReviewScore(int cutoffReviewScore) {
        return course -> course.getReviewScore() > cutoffReviewScore;
    }
}
