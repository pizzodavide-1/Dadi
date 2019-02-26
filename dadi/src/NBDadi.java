

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tosetti Luca
 *
 * Classe main che collabora con la classe thDado1 e CDatiCondivisi
 */
public class NBDadi {

    private static final Scanner scan = new Scanner(System.in);

    /**
     * @brief: Metodo main che si occupa di far partire il programma
     *
     * In questo metodo si dichiara una variabile console per permettere l'input
     * di stringhe da tastiera,una variabile String di nome text e tre thread
     * (th1,th2,th3),di questi thread si inizia l'esecuzione con il metodo
     * start(), verrà fatto l'output a schermo dei valori delle varie ruote
     * della slot machine quando l'utente premerà il tasto invio e/o immetta una
     * qualsiasi stringa diversa da " ", in quel caso verranno interrotti i
     * thread th1,th2 e th3 e successivamente verrà effettuato l'output del
     * risultato(vincita o perdita).
     *
     * @param args Parametro usato per accedere ad altri eventuali parametri del
     * main.
     *
     */
    public static void main(String[] args) {
        try {
            java.io.BufferedReader console = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            
            boolean sleep=true;
            boolean yield=false;
            
            CDatiCondivisi dati = new CDatiCondivisi();            
            ThVisualizza thVis = new ThVisualizza(sleep, yield, dati);
            ThDadi dado1 = new ThDadi(sleep, yield, 1, dati);
            ThDadi dado2 = new ThDadi(sleep, yield, 2, dati);
            ThDadi dado3 = new ThDadi(sleep, yield, 3, dati);
            
            System.out.println("Premi INVIO per iniziare e premi invio per bloccare la generazione");
            scan.nextLine();
            
            dado1.start();
            dado2.start();
            dado3.start();
            thVis.start();
            
            scan.nextLine();
            
            dado1.termina();
            dado2.termina();
            dado3.termina();
            thVis.termina();
            
            dati.waitSDado1();
            dati.waitSDado2();
            dati.waitSDado3();
            dati.waitSVis();
            
            dati.VisualizzaSchermo();
            
            if ((dati.getPrimoDado() == dati.getSecondoDado()) && (dati.getPrimoDado() == dati.getTerzoDado())) {
                System.out.println("Combinazione uscita:" + dati.getPrimoDado() + "|" + dati.getSecondoDado() + "|" + dati.getTerzoDado() + " Complimenti hai vinto!");
            } else {
                System.out.println("Combinazione uscita:" + dati.getPrimoDado() + "|" + dati.getSecondoDado() + "|" + dati.getTerzoDado() + " Peccato hai perso!");
            }   System.out.println("Alla prossima");
        } catch (InterruptedException ex) {
            Logger.getLogger(NBDadi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}