/* Universidade Estadual da Paraíba
*  Atividade de Laboratório 01 - Prof.: Daniel Gondim
*  Alunos: Higor Pereira / Lanmark Rafael / Danilo de Souza
 */
package lab01;

import java.util.Scanner;

public class Lab01 {

    public static void main(String[] args) {
        
        float num, maior, menor, numeroCompare;
        maior = 0;
        menor = 0;
        int count = 1;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Informe a quantidade de números a serem comparados: ");
        numeroCompare = entrada.nextFloat();

        while (count <= numeroCompare) {
            System.out.print("Numero " + count + " : ");
            num = entrada.nextFloat();

            if (maior == 0 && menor == 0) {
                maior = num;
                menor = num;
            }
            if (num > maior) {
                maior = num;
            }
            if (num < menor) {
                menor = num;
            }
            count++;
        }
        System.out.println("O maior número informado foi: " + maior);
        System.out.println("O menor número informado foi: " + menor);
    }
}
