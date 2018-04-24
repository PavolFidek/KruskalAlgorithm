package kruskalovalgoritmus;

/**
 *
 * @author Fidek
 */
public class KruskalovAlgoritmus {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AlgoritmusKod algoritmus = new AlgoritmusKod();
        algoritmus.pridajVrcholy();
        algoritmus.vypisVrcholov();
        algoritmus.pridajHrany();
        algoritmus.vypisHranPredZoradenim();
        algoritmus.zoradHrany();
        algoritmus.vypisHranPoZoradeni();
        algoritmus.algoritmus();
        //algoritmus.vypisVrcholov();
    }
}
