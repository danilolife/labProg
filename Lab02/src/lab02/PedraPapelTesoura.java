/* Universidade Estadual da Paraíba
*  Atividade de Laboratório 02 - Prof.: Daniel Gondim
*  Alunos: Higor Pereira / Lanmark Rafael / Danilo de Souza
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
            char firstLetter = jogadorInput.charAt(0);
            if (firstLetter == 'P' || firstLetter == 'A' || firstLetter == 'T') {
                // User has entered a valid input
                switch (firstLetter) {
                    case 'P':
                        return Move.PEDRA;
                    case 'A':
                        return Move.PAPEL;
                    case 'T':
                        return Move.TESOURA;
                }
            }

            System.out.print("Você inseriu uma opção inválida. Tente novamente!");
            return getMove();
        }

        public boolean playAgain() {
            System.out.print("Gostaria de jogar novamente?\nSe sim, informe um número qualquer. Se não, informe '-1': ");
            String jogadorInput = inputScanner.nextLine();
            jogadorInput = jogadorInput.toUpperCase();
            return jogadorInput.charAt(0) == 'Y';
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

        // Compare moves and determine winner
        int compareMoves = jogadorMove.compareMoves(jogadorpcMove);
        switch (compareMoves) {
            case 0: // Tie
                System.out.println("Tie!");
                break;
            case 1: // User wins
                System.out.println(jogadorMove + " vence " + jogadorpcMove + ". Você ganhou! :D");
                placarJogador++;
                break;
            case -1: // Computer wins
                System.out.println(jogadorpcMove + " vence " + jogadorMove + ". Você perdeu.. :'(");
                placarPC++;
                break;
        }
        numDePartidas++;

        // Ask the jogador to play again
        if (jogador.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }

    /**
     * Prints out the statistics of the game. Calculates ties as 1/2 a win in
     * percentage won.
     */
    private void printGameStats() {
        int wins = placarJogador;
        int losses = placarPC;
        int ties = numDePartidas - placarJogador - placarPC;
        double percentageWon = (wins + ((double) ties) / 2) / numDePartidas;

        // Line
        System.out.print("+");
        printDashes(58);
        System.out.println("+");

        // Print titles
        System.out.printf("| %6s | %6s | %6s | %12s | %14s |\n",
                "V", "D", "E", "PJ", "APROV");

        // Line
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

        // Print values
        System.out.printf("| %6d | %6d | %6d | %12d | %13.2f%% |\n",
                wins, losses, ties, numDePartidas, percentageWon * 100);

        // Line
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
