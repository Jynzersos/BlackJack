package Trabalho;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;

public class BlackJack_Controller {

    static int[] deck = new int[13];
    final static Random ROLL = new Random();

    // Método principal que inicia o jogo
    public static void iniciar_jogo() {
        // Solicita o número de jogadores via view
        int player_count = BlackJack_View.ask_for_player_count();
        fill_deck(player_count);
        LinkedList<Player> player_list = new LinkedList<>();

        // Inicializa os jogadores
        for (int i = 0; i < player_count; i++) {
            String name = BlackJack_View.ask_for_player_name(i + 1);
            LinkedList<Integer> initial_hand = new LinkedList<>();
            draw_initial_hand(initial_hand);
            player_list.add(new Player(name, initial_hand));
        }

        // Define o dealer
        Player dealer = BlackJack_View.ask_for_dealer(player_list);
        player_list.remove(dealer);
        player_list.add(dealer);

        // Exibe as mãos iniciais
        BlackJack_View.show_dealer_hand(dealer);

        // Jogadores compram cartas
        for (Player player : player_list) {
            if (player.equals(dealer)) {
                handle_dealer_turn(player);
            } else {
                handle_player_turn(player);
            }
        }

        // Determina os vencedores
        determine_winner(player_list);
    }

    // Lógica do turno do jogador
    private static void handle_player_turn(Player player) {
        boolean has_busted = false;
        do {
            if (player.get_hand_value() >= 21) break;
            if (!BlackJack_View.ask_if_player_wants_to_buy_card(player)) break;

            draw_card(player);
            BlackJack_View.show_player_card(player);

            if (player.get_hand_value() > 21) {
                player.lose();
                BlackJack_View.show_player_busted(player);
                has_busted = true;
            }
        } while (!has_busted && player.get_hand_value() < 21);
    }

    // Lógica do turno do dealer
    private static void handle_dealer_turn(Player player) {
        while (player.get_hand_value() < 17) {
            draw_card(player);
            BlackJack_View.show_dealer_card(player);
        }
        BlackJack_View.show_dealer_final_hand(player);
    }

    // Sorteia a carta para o jogador
    private static void draw_card(Player player) {
        int card = ROLL.nextInt(13);
        if (deck[card] > 0) {
            player.add_card(card + 1);
            deck[card]--;
        } else {
            draw_card(player);
        }
    }

    // Preenche o baralho com as quantidades de cartas necessárias
    private static void fill_deck(int player_count) {
        int quantity = player_count > 4 ? 8 : 4;
        for (int i = 0; i < deck.length; i++) {
            deck[i] = quantity;
        }
    }

    // Sorteia a mão inicial de 2 cartas para o jogador
    private static void draw_initial_hand(LinkedList<Integer> initial_hand) {
        int card;
        for (int i = 0; i < 2; i++) {
            do {
                card = ROLL.nextInt(13);
            } while (deck[card] == 0);
            initial_hand.add(card + 1);
            deck[card]--;
        }
    }

    // Determina o vencedor do jogo
    private static void determine_winner(LinkedList<Player> player_list) {
        Collections.sort(player_list);
        LinkedList<Player> winners = new LinkedList<>();

        int first_hand_value = player_list.getFirst().get_hand_value();
        for (Player player : player_list) {
            if (player.get_hand_value() == first_hand_value)
                winners.add(player);
            else
                break;
        }

        if (winners.size() == 1) {
            BlackJack_View.show_winner(winners.getFirst());
        } else {
            BlackJack_View.show_tie(winners);
        }
    }
}
