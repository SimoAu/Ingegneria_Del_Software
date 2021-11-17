package progettoIngegneriaArtifactID;

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

    public Elettore (String nome, String cognome, int giorno, int mese, int anno, String nazione, String comune, char sesso, char[] codiceF){

        this.nome= nome;
        this.cognome=cognome;
        this.nascita.setGiorno(giorno);
        this.nascita.setMese(mese);
        this.nascita.setAnno(anno);
        this.nazione= nazione;
        this.comune= comune;
        this.sesso= sesso;
        for (int i=0; i<16 ; i++) { this.codiceF[i] = codiceF[i]; }
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