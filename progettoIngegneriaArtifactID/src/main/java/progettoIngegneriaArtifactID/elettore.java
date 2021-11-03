package progettoIngegneriaArtifactID;

public class elettore{

    String nome;
    String cognome;
    Data nascita;
    boolean diritto_voto;

    public elettore (String nome, String cognome, int giorno, int mese, int anno, boolean diritto_voto){

        this.nome= nome;
        this.cognome=cognome;
        this.nascita(giorno,mese,anno);
        this.diritto_voto=diritto_voto;
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


}