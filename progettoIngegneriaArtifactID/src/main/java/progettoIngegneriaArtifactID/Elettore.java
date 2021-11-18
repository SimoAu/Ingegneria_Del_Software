package progettoIngegneriaArtifactID;

import java.util.Random;

import static java.lang.Math.pow;

public class Elettore{
	
	
	 
	String nome;
    String cognome;
    Data nascita= new Data();
    String nazione;
    String comune;
    char sesso;
    char [] codiceF= new char[16];
    boolean voto;
    boolean diritto_voto;

    private boolean isVocal(char c){
        return (Character.toUpperCase(c)=='A' || Character.toUpperCase(c)=='E' ||Character.toUpperCase(c)=='O' ||Character.toUpperCase(c)=='I' || Character.toUpperCase(c)=='U');
    }


    // il nome e il cognome devono avere un valore e quindi non essere nulli
    //@ requires nome != null;  
  	//@ requires cognome != null;
    
    // il sesso può avere solo valore maschio (M) o femmina (F) e di non può essere nullo
    //@ requires sesso == M || sesso == F;
  	
    // solo se è di nazionalità italiana deve essere riportato il comune e quindi non essere nullo
    //@ requires nazione = italia ==> comune != null;
    
    // controlliamo che il settimo carattere siano le decinde dell'anno di nascita ( Compreso lo zero ) e allo stesso tempo che l'ottavo rappresenti le unità 
    //@ ensure codiceF[6] == (char)(48+((anno%100)/10)) && this.codiceF[7] == (char)(48+(anno%10));
    
    // controlliamo che al mese di nascita corrisponda la lettera assegnata dal ministero
    //@ ensure (codiceF[8] == 'A' && mese == 1) || (codiceF[8] == 'B' && mese == 2) || (codiceF[8] == 'C' && mese == 3) || (codiceF[8] == 'D' && mese == 4) || (codiceF[8] == 'E' && mese == 5) || (codiceF[8] == 'H' && mese == 6) || (codiceF[8] == 'L' && mese == 7) || (codiceF[8] == 'M' && mese == 8) || (codiceF[8] == 'P' && mese == 9) || (codiceF[8] == 'R' && mese == 10) || (codiceF[8] == 'S' && mese == 11) || (codiceF[8] == 'T' && mese == 12);
    
    // controlliamo in caso di sesso femminile che il decimo carattere rappresenti le decine del giorno di nascita + 4 e l'undicesimo le unità 
    //@ ensure (Character.toUpperCase(sesso)=='F' ==> codiceF[9]==(char)(48+((giorno/10)+4))) && codiceF[10]== (char)(48+(giorno%10));
   
    // controlliamo in caso di sesso maschile che il decimo carattere rappresenti le decine del giorno di nascita e l'undicesimo le unità 
    //@ ensure (Character.toUpperCase(sesso)=='M' ==> codiceF[9] == (char) (48+(giorno / 10)) && codiceF[10]== (char)(48+(giorno%10));
    
    // controlliamo che nel caso di nazionalità straniera il dodicesimo carattere sia uguale a Z, mentre se italiana controlliamo solo che sia una lettera
    //@ ensure (!nazione.equals("Italia") && !nazione.equals("italia") && !nazione.equals("ITALIA")) ==> codiceF[11]= 'Z';
    //@ ensure (nazione.equals("Italia") || nazione.equals("italia") || nazione.equals("ITALIA")) ==> (codiceF[11].isLetter() && codiceF[11] != 'Z');
    
    // controllo semplificato del fatto che le lettere e i numeri compaiono nella corretta posizione
    //@ ensure Character.isDigit(codiceF[12])&& Character.isDigit(codiceF[13]) && Character.isDigit(codiceF[14]);
    //@ ensure Character.isLetter(codiceF[15]);
    
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
        
        // controllo i casi in cui ci siano meno di tre lettere  
       
        int cons=0, voca=0;
        for(int k=0; k<3; k++){
        	
        	// scorro il cognome salvando nella posizione corrente la lettera nel caso sia una consonante, mi salvo la posizione della consonante
            // per evitare di ripeterla ed esco direttamente dal ciclo
        	for (int i=cons; i<cognome.length() ; i++) {
               if (!isVocal(cognome.charAt(i))) {
            	   this.codiceF[k] = Character.toUpperCase(cognome.charAt(i));
                   cons=i+1;
                   i = cognome.length();
               }
               
            }
        	
        	// nel caso non abbia trovato consonanti ( e quindi la variabile corrente sia ancora vuota ) allora uso lo stesso ciclo per controllare le vocali salvandomi
            // la posizione per non ripetermi
        	if( this.codiceF[k] == '\0'){
         	   for (int j=voca; j<cognome.length() ; j++) {
         		   if (isVocal(cognome.charAt(j))) {
         			   this.codiceF[k] = Character.toUpperCase(cognome.charAt(j));
                        voca=j+1;
                        j=cognome.length();
                   }
               }
            }
        	
        	// nel caso speciale in cui non abbia trovato ulteriori vocali o consonanti ( nomi di una o due lettere ) aggiungo una X
        	if( this.codiceF[k] == '\0'){
        		this.codiceF[k] = 'X';
        	}
        }
        
        cons=0; voca=0;
        
        // per il nome dobbiamo fare il ciclo 4 volte in modo da controllare che nel caso ci sia una quarta consonante, io prenda la prima,
        // la terza e la quarta consonante per il nome
        for(int k=3; k<7; k++){
        	// controllo le consonanti salvandomi la posizione per evitare di ripetermi
        	for (int i=cons; i<nome.length() ; i++) {
        		if (!isVocal(nome.charAt(i))) {
        			
        			// se ho trovato una consonante e sono alla quarta iterazione del primo ciclo ( quindi è la quarta consonante del nome ) allora
                    // in quel caso sostituisco alla seconda posizione la terza consonante e inserisco la quarta in terza posizione
        			if(k==6){
        				this.codiceF[4]= this.codiceF[5];
        				this.codiceF[5]= Character.toUpperCase(nome.charAt(i));
        			}
        			
        			// in caso non sia la quarta consonante funziona come per i cognomi
        			else{
        				this.codiceF[k] = Character.toUpperCase(nome.charAt(i));
        			}
        			cons=i+1;
        			i=nome.length();
        		}
            				
        	}
        	
        	// funziona esattamente come il cognome tranne per il controllo per essere sicuri di non stare ancora cercando la quarta consonante
        	if( this.codiceF[k] == '\0' && k<6 ){
        		for (int j=voca; j<nome.length() ; j++) {
        			if (isVocal(nome.charAt(j))) {
        				this.codiceF[k] = Character.toUpperCase(nome.charAt(j));
        				voca=j+1;
        				j=nome.length();
        			}
        		}
        	}
        	
        	if( this.codiceF[k] == '\0' && k<6 ){
        		this.codiceF[k] = 'X';
        	}
        }
        
        // per ottenere il valore delle decine dell'anno di nascita prendo il resto di 100 e lo divido per 10
        this.codiceF[6]= (char)(48+((anno%100)/10));
        
        // per ottenere le unità dell'anno prendo il resto di 10
        this.codiceF[7]= (char)(48+(anno%10));
       
        // dato che i mesi non vengono salvati esattamente in ordine alfabetico ma alcune lettere vengono saltate ci siamo salvati
        // un array ordinato con le lettere rappresentanti i mesi
        char [] mesi_fiscali= {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};
        // prendiamo la lettera corrispondente al mese - 1 poichè iniziamo a contare da zero 
        this.codiceF[8]= mesi_fiscali[mese - 1];
        
        // facciamo un controllo del sesso in quanto nel caso di sesso femminile al giorno di nascita va sommato 40
        // ( in caso maschile non va sommato nulla ) prima di prendere le decine ( tramite divisione per 10 ) e le unità ( tramite resto di 10 )
        if(Character.toUpperCase(sesso)=='F'){
            this.codiceF[9]= (char)(48+((giorno/10)+4));
        }
        if(Character.toUpperCase(sesso)=='M') {
            this.codiceF[9] = (char) (48+(giorno / 10));
        }
        this.codiceF[10]= (char)(48+(giorno%10));

        //i caratteri alfanumerici successivi (ad eccezione della della 'Z' in caso di stranieri) sono scelti in maniera casuale
        // ( nel caso di quelli alfabetici abbiamo sfruttato l'array precedente in modo da escludere anche la Z )
        Random rn = new Random();
        if (nazione.equals("Italia") || nazione.equals("italia") || nazione.equals("ITALIA")){
        	this.codiceF[11]= mesi_fiscali [(int)(pow(rn.nextInt(),2)) %10];
        }
        else{
        	
        	this.codiceF[11]='Z';
            
        }
        this.codiceF[12]=(char) (48+(pow(rn.nextInt(),2) %10));
        this.codiceF[13]=(char) (48+(pow(rn.nextInt(),2) %10));
        this.codiceF[14]=(char) (48+(pow(rn.nextInt(),2) %10));
        this.codiceF[15]= mesi_fiscali [(int)(pow(rn.nextInt(),2)) %10];

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