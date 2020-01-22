package no.simenbai.idatg2001.obligs.one;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<>();

    Deck() {
        for (int i = 1; i < 14; i++) {
            deck.add(new Card('S', i));
            deck.add(new Card('H', i));
            deck.add(new Card('D', i));
            deck.add(new Card('C', i));
        }
    }

    public Collection<Card> assign(int n) {
        if (n >= 1 && n <= 52) {
            ArrayList<Card> cards = new ArrayList<>();
            ArrayList<Integer> selectedCards = new ArrayList<>();

            Random random = new Random();
            //Get an int array of n size from 1 to 52
            while (selectedCards.size() <= n) {
                //Tilfeldig tall fra og med 0 til bound (52)
                //Tall fra 0 - 51
                int number = random.nextInt(deck.size());
                if (!selectedCards.contains(number)) {
                    selectedCards.add(number);
                    cards.add(deck.get(number));
                }
            }
            return cards;
        }
        System.out.println("Assign needs a number between 1 and 52");
        return null;
    }

    public Collection<Card> assignShuffeled(int n) {
        //Shuffle the deck of cards
        Collections.shuffle(deck);
        //New ArrayList for the cards that will be returned
        ArrayList<Card> cards = new ArrayList<>();
        //Go through the n first cards
        for (int i = 0; i < n; i++) {
            //Add the i card into the ArrayList of the response
            cards.add(deck.get(i));
        }
        return cards;
    }

    public static ArrayList<Card> collectHearts(ArrayList<Card> suit) {
        ArrayList<Card> response = new ArrayList<>();
        suit.stream().filter(card -> card.getSuit() == 'H').forEach(response::add);
        return response;
    }

    public static void printSpade(ArrayList<Card> cards) {
        cards.stream().filter((card -> card.getSuit() == 'S')).forEach(Card::printCard);
    }

    public static List<String> turnIntoColor(ArrayList<Card> cards) {
        return cards.stream().map(card ->
                (card.getSuit() == 'H' || card.getSuit() == 'D') ? "RED" : "BLACK"
        ).collect(Collectors.toList());
    }

    public static int getValue(ArrayList<Card> cards) {
        return cards.stream().reduce(0, (subtotal, card) -> subtotal + card.getFace(), Integer::sum);
    }

    public static boolean queenSpadesExists(ArrayList<Card> cards) {
        return cards.stream().anyMatch((card) -> card.getSuit() == 'S' && card.getFace() == 12);
    }

    public static boolean hasFlush(ArrayList<Card> cards) {
        Map<Character, Long> suitCount = cards.stream().collect(Collectors.groupingBy(Card::getSuit, Collectors.counting()));

        for (Long number : suitCount.values()) {
            if (number >= 5) {
                return true;
            }
        }
        return false;
    }
}
