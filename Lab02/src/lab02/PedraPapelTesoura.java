/* Universidade Estadual da Paraíba
*  Atividade de Laboratório 02 - Prof.: Daniel Gondim
*  Aluno: Higor Pereira
 */
package lab02;

import java.util.Random;
import java.util.Scanner;

public class PedraPapelTesoura {

    private final Jogador jogador;
    private final JogadorPC jogadorpc;
    private int placarJogador;
    private int placarPC;
    private int numDePartidas;

    private enum Move {
        PEDRA, PAPEL, TESOURA;

        public int compareMoves(Move otherMove) {

            if (this == otherMove) {
                return 0;
            }

            switch (this) {
                case PEDRA:
                    return (otherMove == TESOURA ? 1 : -1);
                case PAPEL:
                    return (otherMove == PEDRA ? 1 : -1);
                case TESOURA:
                    return (otherMove == PAPEL ? 1 : -1);
            }

            return 0;
        }
    }

    private class Jogador {

        private final Scanner inputScanner;

        public Jogador() {
            inputScanner = new Scanner(System.in);
        }

        public Move getMove() {

            System.out.print("Pedra (1), Papel (2), Tesoura (3)? ");

            String jogadorInput = inputScanner.nextLine();
            jogadorInput = jogadorInput.toUpperCase();
            int jogada = jogadorInput.charAt(0);
            if (jogada == '1' || jogada == '2' || jogada == '3') {
                // Switch das Jogadas Disponíveis
                switch (jogada) {
                    case '1':
                        return Move.PEDRA;
                    case '2':
                        return Move.PAPEL;
                    case '3':
                        return Move.TESOURA;
                }
            }

            System.out.print("Você inseriu uma opção inválida. Tente novamente!\n");
            return getMove();
        }

    }

    private class JogadorPC {

        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }
    }

    public PedraPapelTesoura() {
        jogador = new Jogador();
        jogadorpc = new JogadorPC();
        placarJogador = 0;
        placarPC = 0;
        numDePartidas = 0;
    }

    public void startGame() {
        System.out.println("JOGO DO PEDRA, PAPEL OU TESOURA!");

        //Capturando os movimentos
        Move jogadorMove = jogador.getMove();
        Move jogadorpcMove = jogadorpc.getMove();
        System.out.println("\nVocê jogou " + jogadorMove + ".");
        System.out.println("O Computador jogou " + jogadorpcMove + ".\n");

        // Comparando os movimentos e determinando o vencedor
        int compareMoves = jogadorMove.compareMoves(jogadorpcMove);
        switch (compareMoves) {
            case 0: //Empate
                System.out.println("Empate!");
                break;
            case 1: // Vitória do Jogador
                System.out.println(jogadorMove + " vence " + jogadorpcMove + ". Você ganhou! :D");
                placarJogador++;
                break;
            case -1: // Vitória do PC
                System.out.println(jogadorpcMove + " vence " + jogadorMove + ". Você perdeu.. :'(");
                placarPC++;
                break;
        }
        numDePartidas++;

        // Solicitando recomeço ou não do jogo
        System.out.print("Gostaria de jogar novamente?\nSe sim, informe um número qualquer. Se não, informe '-1': ");
        Scanner entrada = new Scanner(System.in);
        int jogadorInput = entrada.nextInt();
        // Estabelecendo parâmetro de sáida
        if (jogadorInput != -1) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }

    // Imprime as estatísticas das partidas
    private void printGameStats() {
        int vitorias = placarJogador;
        int derrotas = placarPC;
        int empates = numDePartidas - placarJogador - placarPC;
        double percentageWon = (vitorias + ((double) empates) / 2) / numDePartidas;

        System.out.print("+");
        printDashes(58);
        System.out.println("+");

        System.out.printf("| %6s | %6s | %6s | %12s | %14s |\n",
                "V", "D", "E", "PJ", "APROV");

        System.out.print("|");
        printDashes(8);
        System.out.print("+");
        printDashes(8);
        System.out.print("+");
        printDashes(8);
        System.out.print("+");
        printDashes(14);
        System.out.print("+");
        printDashes(16);
        System.out.println("|");

        System.out.printf("| %6d | %6d | %6d | %12d | %13.2f%% |\n",
                vitorias, derrotas, empates, numDePartidas, percentageWon * 100);

        System.out.print("+");
        printDashes(58);
        System.out.println("+");
    }

    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        PedraPapelTesoura game = new PedraPapelTesoura();
        game.startGame();
    }
}
