package Trabalho;

import javax.swing.*;
import java.util.LinkedList;

public class BlackJack_View {

    // Solicita o número de jogadores
    public static int ask_for_player_count() {
        int player_count = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos jogadores vão jogar?",
                "Quantidade de Jogadores", JOptionPane.QUESTION_MESSAGE));
        while (player_count < 2 || player_count > 8) {
            player_count = Integer.parseInt(JOptionPane.showInputDialog(null, "Número inválido! Insira entre 2 e 8 jogadores.",
                    "Quantidade de Jogadores", JOptionPane.ERROR_MESSAGE));
        }
        return player_count;
    }

    // Solicita o nome do jogador
    public static String ask_for_player_name(int player_number) {
        return JOptionPane.showInputDialog(null, "Nome do Jogador " + player_number + " :", "Nome do Jogador", JOptionPane.QUESTION_MESSAGE);
    }

    // Solicita o nome do dealer
    public static Player ask_for_dealer(LinkedList<Player> player_list) {
        boolean valid_dealer = false;
        Player dealer = null;
        do {
            String dealer_name = JOptionPane.showInputDialog(null, "Quem será o Dealer", "Nome do Dealer", JOptionPane.QUESTION_MESSAGE);
            for (Player player : player_list) {
                if (player.get_name().equals(dealer_name)) {
                    dealer = player;
                    valid_dealer = true;
                    break;
                }
            }
            if (!valid_dealer) {
                JOptionPane.showMessageDialog(null, "Digite um jogador válido", "Seleção Inválida", JOptionPane.WARNING_MESSAGE);
            }
        } while (!valid_dealer);
        return dealer;
    }

    // Exibe a mão inicial do dealer
    public static void show_dealer_hand(Player dealer) {
        JOptionPane.showMessageDialog(null,
                "Mão do dealer " + dealer.get_name() + " : [ " + dealer.get_card_string(dealer.get_player_hand().getFirst()) + ", ? ]",
                "Mão inicial do Dealer", JOptionPane.INFORMATION_MESSAGE);
    }

    // Pergunta ao jogador se ele quer comprar uma carta
    public static boolean ask_if_player_wants_to_buy_card(Player player) {
        String[] options = new String[] { "Sim", "Não" };
        int choice = JOptionPane.showOptionDialog(
                null, player.get_name() + ", suas cartas são: " + player.get_hand_representation() +
                        "\nDeseja comprar uma carta?",
                "Compra de Cartas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        return choice == 0;
    }

    // Exibe a carta comprada pelo jogador
    public static void show_player_card(Player player) {
        JOptionPane.showMessageDialog(null,
                player.get_name() + " comprou a carta: " + player.get_card_string(player.get_last_card())
                        + "\nSuas cartas atuais são: " + player.get_hand_representation(),
                "Compra", JOptionPane.INFORMATION_MESSAGE);
    }

    // Exibe que o jogador estourou
    public static void show_player_busted(Player player) {
        JOptionPane.showMessageDialog(null, player.get_name() + " estourou!", "Estouro", JOptionPane.ERROR_MESSAGE);
    }

    // Exibe a carta comprada pelo dealer
    public static void show_dealer_card(Player player) {
        JOptionPane.showMessageDialog(null,
                "O dealer comprou a carta: " + player.get_card_string(player.get_last_card()) +
                        "\nCartas do Dealer: " + player.get_hand_representation(),
                "Compra do Dealer", JOptionPane.INFORMATION_MESSAGE);
    }

    // Exibe a mão final do dealer
    public static void show_dealer_final_hand(Player player) {
        JOptionPane.showMessageDialog(null,
                "O dealer " + player.get_name() + " tem " + player.get_hand_value() + " pontos." +
                        "\n Cartas do Dealer: " + player.get_hand_representation(),
                "Resultado do Dealer", JOptionPane.INFORMATION_MESSAGE);
    }

    // Exibe o vencedor
    public static void show_winner(Player winner) {
        JOptionPane.showMessageDialog(null,
                "O jogador " + winner.get_name() + " ganhou com " + winner.get_hand_value() + " pontos.",
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    // Exibe empate
    public static void show_tie(LinkedList<Player> winners) {
        StringBuilder winners_string = new StringBuilder();
        for (Player winner : winners) {
            winners_string.append(winner.get_name()).append(", ");
        }
        winners_string.delete(winners_string.length() - 2, winners_string.length());
        JOptionPane.showMessageDialog(null,
                "Os jogadores " + winners_string.toString() + " empataram com " + winners.getFirst().get_hand_value(),
                "Empate", JOptionPane.INFORMATION_MESSAGE);
    }
}
