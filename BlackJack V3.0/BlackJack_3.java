/*
 * 14/12/2024
 * ARTHUR GAMBA BORBA | arthurborba271@gmail.com
 */
import javax.swing.*;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;

public class BlackJack_3 {
    static int[] deck = new int[13];
    final static Random ROLL = new Random();

    public static void main(String[] args) {
        // #region setando players
        int player_count = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos jogadores vão jogar?",
                "Quantidade de Jogadores", JOptionPane.QUESTION_MESSAGE));
        while (player_count < 2 || player_count > 8) {
            player_count = Integer
                    .parseInt(JOptionPane.showInputDialog(null, "Número inválido! Insira entre 2 e 8 jogadores.",
                            "Quantidade de Jogadores", JOptionPane.ERROR_MESSAGE));
        }

        fill_deck(player_count);
        LinkedList<Player> player_list = new LinkedList<>();

        // Inicializar jogadores e dealer
        for (int i = 0; i < player_count; i++) {
            String name = JOptionPane.showInputDialog(null, "Nome do Jogador " + (i + 1) + " :", "Nome do Jogador",
                    JOptionPane.QUESTION_MESSAGE);
            LinkedList<Integer> initial_hand = new LinkedList<>();
            draw_initial_hand(initial_hand);
            player_list.add(new Player(name, initial_hand));
        }

        boolean valid_dealer = false;
        do {
            String dealer_name = JOptionPane.showInputDialog(null, "Quem será o Dealer", "Nome do Dealer",
                    JOptionPane.QUESTION_MESSAGE);
            for (Player player : player_list) {
                if (player.get_name().equals(dealer_name)) {
                    player_list.remove(player);
                    player_list.add(player);
                    valid_dealer = true;
                    break;
                } else if (player.get_name().equals(player_list.getLast().get_name())) {
                    JOptionPane.showMessageDialog(null, "Digite um jogador válido", "Seleção Inválida",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } while (!valid_dealer);
        // #endregion

        // #region mostrar carta do dealer
        JOptionPane.showMessageDialog(null,
                "Mão do dealer " + player_list.getLast().get_name() + " : [ "
                        + player_list.getLast().get_card_string(player_list.getLast().get_player_hand().getFirst())
                        + ", ? ]",
                "Mão inicial do Dealer", JOptionPane.INFORMATION_MESSAGE);
        // #endregion

        // #region players comuns setados
        for (Player player : player_list) {
            if (player_list.getLast().equals(player)) { // O dealer deve ser o último a jogar
                purchase_menu(player, true);
                break;
            }
            purchase_menu(player, false);
        }
        // #endregion

        // #region Ver quem venceu
        Collections.sort(player_list);
        LinkedList<Player> winners = new LinkedList<>();

        int first_hand_value = player_list.getFirst().get_hand_value();
        for (Player player : player_list) {
            if (player.get_hand_value() == first_hand_value)
                winners.add(player);
            else
                break;
        }

        if (winners.size() == 1)
            JOptionPane.showMessageDialog(null,
                    "O jogador " + winners.getFirst().get_name() + " ganhou com " + winners.getFirst().get_hand_value()
                            + " pontos.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
        else {
            StringBuilder winners_string = new StringBuilder();
            for (Player winner : winners) {
                winners_string.append(winner.get_name()).append(", ");
            }
            winners_string.delete(winners_string.length() - 2, winners_string.length());
            JOptionPane.showMessageDialog(null,
                    "Os jogadores " + winners_string.toString() + " empataram com " + winners.getFirst().get_hand_value()
                            + " pontos.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
        // #endregion
    }

    // #region métodos lindos e sem bugs
    private static void purchase_menu(Player player, boolean is_dealer) {
        int choice = 1;
        do {
            if (is_dealer && player.get_hand_value() >= 17) {
                dealer_max_value(player);
                break;
            }

            if (player.get_hand_value() == 21) {
                if (player.get_player_hand().size() == 2)
                    player_Blackjack(player);
                else
                    player_max_value(player);
                break;
            }

            String[] options = is_dealer ? new String[] { "Sim" } : new String[] { "Sim", "Não" };
            String base_option = is_dealer ? "Sim" : "Não";
            choice = JOptionPane.showOptionDialog(
                    null, player.get_name() + ", suas cartas são: " + player.get_hand_representation()
                            + "\nDeseja comprar uma carta?",
                    "Compra de Cartas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, base_option);

            if (choice == 0) {
                draw_card(player);
                JOptionPane.showMessageDialog(null,
                        player.get_name() + " comprou a carta: " + player.get_card_string(player.get_last_card())
                                + "\nSuas cartas atuais são: " + player.get_hand_representation(),
                        "Compra", JOptionPane.INFORMATION_MESSAGE);
                if (player.get_hand_value() > 21) {
                    player.lose();
                    JOptionPane.showMessageDialog(null, player.get_name() + " estourou!", "Estouro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, player.get_name() + " passou a vez.", "Passou",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } while (choice == 0 && !player.get_player_hand().isEmpty());

    }

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

    private static void draw_card(Player player) {
        int card = ROLL.nextInt(13);
        if (deck[card] > 0) {
            player.add_card(card + 1);
            deck[card]--;
        } else {
            draw_card(player);
        }
    }

    private static void fill_deck(int player_count) {
        int quantity = player_count > 4 ? 8 : 4;
        for (int i = 0; i < deck.length; i++) {
            deck[i] = quantity;
        }
    }

    private static void dealer_max_value(Player player) {
        JOptionPane.showMessageDialog(null,
                "O dealer " + player.get_name() + " tem " + player.get_hand_value() + " pontos." + "\n Cartas do Dealer: " + player.get_hand_representation(),
                "Resultado do Dealer", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void player_max_value(Player player) {
        JOptionPane.showMessageDialog(null,
                "O jogador " + player.get_name() + " tem " + player.get_hand_value() + " pontos." + "\n Suas cartas são: " + player.get_hand_representation(),
                "Jogador com 21", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void player_Blackjack(Player player) {
        JOptionPane.showMessageDialog(null,
                "!! Blackjack !!\nO jogador " + player.get_name() + " fez 21 pontos." + "\n Suas cartas são: " + player.get_hand_representation(),
                "Blackjack", JOptionPane.INFORMATION_MESSAGE);
    }
    // #endregion
}