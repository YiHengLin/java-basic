package com.ov.java8;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The content is all from Lambda Expression official tutorial
 * @see https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public class FunctionInterfaceDemo {

    public static void main(String[] args) {

        Player p1 = new Player("Curry", 95);
        Player p2 = new Player("James", 99);
        Player p3 = new Player("Durant", 98);
        Player p4 = new Player("Booker", 88);
        Player p5 = new Player("Anthony", 83);
        Player p6 = new Player("Ball", 70);
        List<Player> players = List.of(p1, p2, p3, p4, p5, p6);

        // naive approach
        System.out.println("NBA players who's score is greater than 90: ");
        for(Player p : players){
            if(p.getScore() > 90){
                String name = p.getName();
                System.out.println(name);
            }
        }

        // Lambda expressions and function interface approach
        System.out.println("\nUsing lambda expressions and function interface: ");
        showTopNBAPlayers(players, p -> p.getScore() > 90, p -> p.getName(), s -> System.out.println(s));

        // Steps showTopNBAPlayers() perform(a, b, c, d) can do with aggregate operations
        System.out.println("\nUsing aggregate operations: ");
        players.stream()
               .filter(p -> p.getScore() > 90)
               .map(p -> p.getName())
               .forEach(s -> System.out.println(s));

    }

    // 1. Suppose tester has only one abstract method test() which takes one parameter and returns a boolean value
    //    We can use Predicate<T> and replace anonymous class with lambda expression.
    // 2. Since showTopNBAPlayers take one argument of type Player and returns void, we can use Consumer<T>.
    // 3. If we wanna retrieve some data from Player, which take one parameter(Player), and return some data, we can use Function<T, R>
    public static void showTopNBAPlayers(List<Player> roaster, Predicate<Player> tester,
                                         Function<Player, String> mapper, Consumer<String> block){
        for(Player p : roaster){ // a. obtain source of objects from a collection
            if(tester.test(p)){  // b. filter element with criteria specified by Predicate
                String name = mapper.apply(p); // c. map each filtered objects to a value as specified by Function
                block.accept(name); // d. perform an action on data
            }
        }
    }

    private static class Player {
        private String name;
        private int score;

        public Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}




