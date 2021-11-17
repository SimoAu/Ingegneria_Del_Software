package progettoIngegneriaArtifactID;

import java.util.Random;

public class Elettore{

	//@ public invariant nome != null;  
	//@ public invariant cognome != null;
	//@ public invariant sesso == M || sesso == F;
	//@ public invariant nazione = italia ==> comune != null    
	


    String nome;
    String cognome;
    Data nascita= new Data();
    String nazione;
    String comune;
    char sesso;
    char [] codiceF= new char[16];
    boolean voto;
    boolean diritto_voto;

    public Elettore ( String nome, String cognome, int giorno, int mese, int anno, String nazione, String comune, char sesso){

        if(nome.contains(",")){
            String [] nomi = nome.split("[,]");
            this.nome=nomi[0];
        }
        else {
            this.nome = nome;
        }
        this.cognome=cognome;
        this.nascita.setGiorno(giorno);
        this.nascita.setMese(mese);
        this.nascita.setAnno(anno);
        this.nazione= nazione;
        this.comune= comune;
        this.sesso= sesso;
        switch (cognome.length()){

            case 1:
                this.codiceF[0]=Character.toUpperCase(cognome.charAt(0));
                this.codiceF[1]='X';
                this.codiceF[2]='X';
                break;

            case 2:
                this.codiceF[0]=Character.toUpperCase(cognome.charAt(0));
                this.codiceF[1]=Character.toUpperCase(cognome.charAt(1));
                this.codiceF[2]='X';
                break;

            default:
                int cons=0, voca=0;
                    for(int k=0; k<3; k++){
                         for (int i=cons; i<cognome.length() ; i++) {
                            if (Character.toUpperCase(cognome.charAt(i)) != 'A' || Character.toUpperCase(cognome.charAt(i)) != 'I' || Character.toUpperCase(cognome.charAt(i)) != 'O' || Character.toUpperCase(cognome.charAt(i)) != 'E' || Character.toUpperCase(cognome.charAt(i)) != 'U') {
                                   this.codiceF[k] = Character.toUpperCase(cognome.charAt(i));
                                 cons=i+1;
                                 i=cognome.length();
                             }
                             if(i+1==cognome.length()){
                              for (int j=voca; j<cognome.length() ; j++) {
                                     if (Character.toUpperCase(cognome.charAt(i)) == 'A' || Character.toUpperCase(cognome.charAt(i)) == 'I' || Character.toUpperCase(cognome.charAt(i)) == 'O' || Character.toUpperCase(cognome.charAt(i)) == 'E' || Character.toUpperCase(cognome.charAt(i)) == 'U') {
                                         this.codiceF[k] = Character.toUpperCase(cognome.charAt(j));
                                         voca=j+1;
                                         j=cognome.length();
                                     }
                              }
                             }
                         }


                    }
                    break;

        }
        switch (nome.length()){

                case 1:
                    this.codiceF[3]=Character.toUpperCase(nome.charAt(0));
                    this.codiceF[4]='X';
                    this.codiceF[5]='X';
                    break;

                case 2:
                    this.codiceF[3]=Character.toUpperCase(nome.charAt(0));
                    this.codiceF[4]=Character.toUpperCase(nome.charAt(1));
                    this.codiceF[5]='X';
                    break;

                default:
                    int cons=0, voca=0;
                    char [] arrayTemp= new char [3];
                    for(int k=0; k<4; k++){
                        for (int i=cons; i<nome.length() ; i++) {
                            if (Character.toUpperCase(nome.charAt(i)) != 'A' || Character.toUpperCase(nome.charAt(i)) != 'I' || Character.toUpperCase(nome.charAt(i)) != 'O' || Character.toUpperCase(nome.charAt(i)) != 'E' || Character.toUpperCase(nome.charAt(i)) != 'U') {
                               if(k==3){
                                   arrayTemp[1]=arrayTemp[2];
                                   arrayTemp[2]=Character.toUpperCase(nome.charAt(i));
                               }
                               else{
                                 arrayTemp[k] = nome.charAt(i);
                               }
                               cons=i+1;
                               i=nome.length();
                            }
                            if(i+1==nome.length() && k<3){
                                for (int j=voca; j<nome.length() ; j++) {
                                    if (Character.toUpperCase(nome.charAt(i)) != 'A' || Character.toUpperCase(nome.charAt(i)) != 'I' || Character.toUpperCase(nome.charAt(i)) != 'O' || Character.toUpperCase(nome.charAt(i)) != 'E' || Character.toUpperCase(nome.charAt(i)) != 'U') {
                                        arrayTemp[k] = nome.charAt(j);
                                        voca=j+1;
                                        j=nome.length();
                                    }
                                }
                            }
                        }
                        this.codiceF[3]=arrayTemp[0];
                        this.codiceF[4]=arrayTemp[1];
                        this.codiceF[5]=arrayTemp[2];


                    }
                    break;

            }
        this.codiceF[6]= (char)((anno%100)/10);
        this.codiceF[7]= (char)(anno%10);
        char [] mesi_fiscali= {'0', 'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};
        this.codiceF[8]= mesi_fiscali[mese];

        if(Character.toUpperCase(sesso)=='F'){
            this.codiceF[9]= (char)((giorno/10)+40);
        }
        if(Character.toUpperCase(sesso)=='M') {
            this.codiceF[9] = (char) (giorno / 10);
        }
        this.codiceF[10]= (char)(giorno%10);

        //i caratteri alfanumerici successivi (ad eccezione della della 'Z' in caso di stranieri) sono scelti in maniera casuale
        Random rn = new Random();
        if (nazione!= "Italia" || nazione!= "italia" || nazione!= "ITALIA"){
            this.codiceF[11]='Z';
        }
        else{
            this.codiceF[11]= mesi_fiscali [(rn.nextInt() %10)+1];
        }
        this.codiceF[12]=(char) (rn.nextInt() %10);
        this.codiceF[13]=(char) (rn.nextInt() %10);
        this.codiceF[14]=(char) (rn.nextInt() %10);
        this.codiceF[15]= mesi_fiscali [(rn.nextInt() %10)+1];

        this.diritto_voto= this.nascita.isAdult();
    }
    
    
    
    public String getNome(){
        return this.nome;
    }

    public String getCognome(){
        return this.cognome;
    }

    public Data getNascita(){
        return this.nascita;
    }

    public boolean canVote(){
        return this.diritto_voto; 
    }

    public String getNazione() {return this.nazione;}

    public String getComune() { return this.comune; }

    public char getSesso() { return this.sesso; }

    public char[] getCodiceF() { return codiceF; }

    //@ requires voto == false;
    //@ ensures voto == true && diritto_voto == true;
    
    
    public void esprimi_Voto() {

        if(diritto_voto==true && voto == false){

            this.voto= true;
        }
        
    }

}