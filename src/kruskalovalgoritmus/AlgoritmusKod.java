package kruskalovalgoritmus;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Fidek
 */
public class AlgoritmusKod {
    private Scanner skener;
    private Scanner skener1;
    private Scanner skener2;
    private Vrchol[][] vrcholy;
    private Hrana[][] hrany;
    private String[] splitHrany;
    private int pocetVrcholov;
    private int pocetHran;
    private int cenaKostry;
    private int pomocna1;

    public AlgoritmusKod() {
        skener = new Scanner(System.in);
        skener1 = new Scanner(System.in);
        skener2 = new Scanner(System.in);
    }


    public void pridajVrcholy() {

        System.out.println("Zadajte pocet vrcholov :");
        pocetVrcholov = skener.nextInt();
        vrcholy = new Vrchol[pocetVrcholov][2];

        for (int j = 0; j < vrcholy.length; j++) {
            for (int k = 0; k < vrcholy[j].length; k++) {
                vrcholy[j][k] = new Vrchol();
            }
        }
        for(int i=0; i < pocetVrcholov; i++) {
            char pom = (char)(i+65);
            vrcholy[i][0].setNazov(Character.toString(pom));
            vrcholy[i][1].setOznacenie(i+1);
        }

    }

    public void vypisVrcholov() {
        System.out.println("Zoznam vrcholov :");
        for(int i=0; i < vrcholy.length; i++) {
            System.out.println("V(" + vrcholy[i][0].getNazov() + ") č.: " + vrcholy[i][1].getOznacenie());
        }
    }

    public void pridajHrany() {
        System.out.println("Zadajte pocet hran :");
        pocetHran = skener.nextInt();
        hrany = new Hrana[pocetHran][3];
        for (int i = 0; i < hrany.length; i++) {
            for (int j = 0; j < hrany[i].length; j++) {
                hrany[i][j] = new Hrana();
            }
        }
        for (int k = 0; k < hrany.length; k++) {
            System.out.println("Zadajte " + (k+1) + ". hranu: (zaciatocnyVrchol-koncovyVrchol-cenaHrany)");
            String hrana = skener1.nextLine();
            splitHrany = hrana.split("-");
            hrany[k][0].setZaciatokHrany(splitHrany[0]);
            hrany[k][1].setKoniecHrany(splitHrany[1]);
            hrany[k][2].setaCenaHrany(Integer.parseInt(splitHrany[2]));
        }
    }

    public void vypisHranPredZoradenim() {
        System.out.println("Zoznam hran pred zoradenim :");
        for (int i = 0; i < hrany.length; i++) {
            System.out.println("H(" + hrany[i][0].getZaciatokHrany() + "-" + hrany[i][1].getKoniecHrany() + ") cena: " + hrany[i][2].getCenaHrany());
        }
    }

    public void zoradHrany() {
        Hrana[] hrana = new Hrana[1];
        //najlacnejsia kostra
        for (int i = 0; i < hrany.length; i++) {
            for (int j = 1; j < hrany[i].length; j++) {
                if (hrany[j-1][2].getCenaHrany() > hrany[j][2].getCenaHrany() ) {
                    hrana = hrany[j-1];
                    hrany[j-1] = hrany[j];
                    hrany[j] = hrana;
                }
            }
        }

        //nadrahsia kostra, nefunguje spravne treba opavit
//        for (int i = 0; i < hrany.length; i++) {
//            for (int j = 1; j < hrany[i].length; j++) {
//                if (hrany[j-1][2].getCenaHrany() < hrany[j][2].getCenaHrany() ) {
//                    hrana = hrany[j-1];
//                    hrany[j-1] = hrany[j];
//                    hrany[j] = hrana;
//                }
//            }
//        }
    }

    public void vypisHranPoZoradeni() {
        System.out.println("Zoznam hran po zoradeni :");
        for (int i = 0; i < hrany.length; i++) {
            System.out.println("H(" + hrany[i][0].getZaciatokHrany() + "-" + hrany[i][1].getKoniecHrany() + ") cena: " + hrany[i][2].getCenaHrany());
        }
    }

    public void algoritmus() {
        int cenaHranyZKtorejPridem=0;
        int pomCenaHrany;
        String zaciatokHrany;
        String koniecHrany;

        System.out.println();
        System.out.println("Hrany kostry :");

        for (int i = 0; i < hrany.length; i++) {
            pomCenaHrany = hrany[i][2].getCenaHrany();
            zaciatokHrany = hrany[i][0].getZaciatokHrany();
            koniecHrany = hrany[i][1].getKoniecHrany();


            for (int j = 0; j < vrcholy.length; j++) {
                for (int p = 0; p < vrcholy.length; p++) {
                    if (vrcholy[p][0].getNazov().equals(zaciatokHrany)) {
                        cenaHranyZKtorejPridem = vrcholy[p][1].getOznacenie();
                    }
                }
                //vrcholy[j][1].getNazov();
                if (vrcholy[j][0].getNazov().equals(koniecHrany)) {      //najde koniec hrany
                    pomocna1 = vrcholy[j][1].getOznacenie();

                    //if (vrcholy[j][1].getOznacenie() != 1) {
                    if (cenaHranyZKtorejPridem == pomocna1) {

                    } else if (cenaHranyZKtorejPridem < pomocna1 ) {
                        //pomocna1 = cenaHranyZKtorejPridem;
                        vrcholy[j][1].setOznacenie(cenaHranyZKtorejPridem);
                        for (int k = 0; k < vrcholy.length; k++) {
                            if (vrcholy[k][1].getOznacenie() == pomocna1) {
                                vrcholy[k][1].setOznacenie(cenaHranyZKtorejPridem);
                            }
                        }
                        cenaKostry += hrany[i][2].getCenaHrany();
                        System.out.println("Hrana " + hrany[i][0].getZaciatokHrany() + "-" + hrany[i][1].getKoniecHrany());
                    } else {
                        //vrcholy[j][1].setOznacenie(pomocna1);
                        for (int c = 0; c < vrcholy.length; c++) {
                            if(vrcholy[c][1].getOznacenie() == cenaHranyZKtorejPridem) {
                                vrcholy[c][1].setOznacenie(pomocna1);
                            }
                        }
                        cenaKostry += hrany[i][2].getCenaHrany();
                        System.out.println("Hrana " + hrany[i][0].getZaciatokHrany() + "-" + hrany[i][1].getKoniecHrany());
                    }
                    //vrcholy[j][1].setOznacenie(pomCenaHrany);

                    // }

                }
            }
        }
        System.out.println();
        System.out.println("Cena kostry: " + cenaKostry);
    }
}
