package Trabalho;

import java.util.Objects;
import java.util.Comparator;
import java.util.LinkedList;
public class Player implements Comparable<Player> {
    private String name;
    private LinkedList<Integer> player_hand = new LinkedList<>();

    public Player(String name, LinkedList<Integer> player_hand) {
        this.set_name(name);
        this.set_initial_hand(player_hand);
    }

    public int compareTo(Player other) {
        return Objects.compare(this.get_hand_value(), other.get_hand_value(), Comparator.nullsLast(Comparator.reverseOrder()));
    }
    public String get_name() {
        return this.name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public LinkedList<Integer> get_player_hand() {
        return this.player_hand;
    }

    public void set_initial_hand(LinkedList<Integer> initial_hand) {
        this.player_hand = initial_hand;
    }

    public int get_hand_value() {
        int sum = 0;
        int ace_count = 0;
        for (Integer card : player_hand) {
            sum += card > 10 ? 10 : card;
            ace_count += card == 1 ? 1 : 0;

        }
        return sum += ace_count == 1 && sum <= 11 ? 10 : 0;
    }

    public void add_card(int card) {
        this.player_hand.add(card);
    }

    public void lose() {
        this.player_hand.clear();
    }

    public int get_last_card() {
        return this.player_hand.getLast();
    }

    // Método para obter a representação das cartas
    public String get_card_string(int card_value) {
        if (card_value == 1) {
            return "Ás";
        } else if (card_value == 11) {
            return "J";
        } else if (card_value == 12) {
            return "Q";
        } else if (card_value == 13) {
            return "K";
        } else {
            return String.valueOf(card_value);
        }
    }

    // Método para mostrar as cartas do jogador com seus nomes
    public String get_hand_representation() {
        StringBuilder hand_rep = new StringBuilder("[ ");
        for (int card : this.player_hand) {
            hand_rep.append(get_card_string(card)).append(", ");
        }
        hand_rep.replace(hand_rep.length() - 2, hand_rep.length(), " ]");
        return hand_rep.toString();
    }
}