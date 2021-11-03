package progettoIngegneriaArtifactID;

public class Scrutatore {
	
	String nome;
    String cognome;
    String id;
    
    public Scrutatore (String nome, String cognome, String id){

        this.nome= nome;
        this.cognome=cognome;
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getCognome(){
        return this.cognome;
    }

    public String getId(){
        return this.id;
    }

}
