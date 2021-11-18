package progettoIngegneriaArtifactID;

import java.util.Calendar;

public class Main_Testing {

    private static boolean isVocal(char c){
        return (Character.toUpperCase(c)=='A' || Character.toUpperCase(c)=='E' ||Character.toUpperCase(c)=='O' ||Character.toUpperCase(c)=='I' || Character.toUpperCase(c)=='U');
    }


    //@ ensures cognome.length() >= 3 ==> (forall int i; i >= 0 && i < 3 ; cognome.contain( cf_cognome[i] ));


    public static boolean controlloCognome(char[] cf_cognome, String cognome){

        if(cognome==null)
            return false;

        switch (cognome.length()){

            case 1:

                return ((cf_cognome[0] == Character.toUpperCase(cognome.charAt(0))) && (cf_cognome[1] == 'X') && (cf_cognome[2] == 'X'));

            case 2:

                if (!isVocal(cognome.charAt(0)) || (isVocal(cognome.charAt(0))) && isVocal(cognome.charAt(1))) {
                    return ((cf_cognome[0] == Character.toUpperCase(cognome.charAt(0))) && (cf_cognome[1] == Character.toUpperCase(cognome.charAt(1))) && (cf_cognome[2] == 'X'));
                }
                else {
                    return ((cf_cognome[0] == Character.toUpperCase(cognome.charAt(1))) && (cf_cognome[1] == Character.toUpperCase(cognome.charAt(0))) && (cf_cognome[2] == 'X'));
                }

            default:
                int posizione_last_cons=0 , posizione_Last_voc=0;

                for (int i = 0; i < 3; i++) {
                    if (!isVocal(cf_cognome[i])) {
                        for (int j=posizione_last_cons; j< cognome.length(); j++){
                            if(!isVocal(cognome.charAt(j))){
                                if (Character.toUpperCase(cognome.charAt(j))==cf_cognome[i]) {
                                    posizione_last_cons=j+1;
                                    j = cognome.length();
                                }
                                else
                                    return false;
                            }
                        }
                    }

                    else{
                        for (int j=posizione_Last_voc; j< cognome.length(); j++){
                            if(isVocal(cognome.charAt(j))){
                                if (Character.toUpperCase(cognome.charAt(j))==cf_cognome[i]) {
                                    posizione_Last_voc=j+1;
                                    j = cognome.length();
                                }
                                else
                                    return false;
                            }
                        }
                    }
                }
        }
        return true;
    }

    public static boolean molteCons (String nome){
        int x=0;
        for(int i=0; i<nome.length(); i++){
            if (!isVocal(Character.toUpperCase(nome.charAt(i))))
                x++;
        }
        return x>3;
    }

    //@ ensure nome.length() >= 3 ==> (forall int i; i >= 3 && i < 6 ; nome.contain( cf_nome[i] ));
    public static boolean controlloNome(char[] cf_nome, String nome) {

        if(nome==null)
            return false;

        switch (nome.length()) {

            case 1:
                return ((cf_nome[3] == Character.toUpperCase(nome.charAt(0))) && (cf_nome[4] == 'X') && (cf_nome[5] == 'X'));

            case 2:

                if (!isVocal(nome.charAt(0)) || (isVocal(nome.charAt(0))) && isVocal(nome.charAt(1))) {
                    return ((cf_nome[3] == Character.toUpperCase(nome.charAt(0))) && (cf_nome[4] == Character.toUpperCase(nome.charAt(1))) && (cf_nome[5] == 'X'));
                } else {
                    return ((cf_nome[3] == Character.toUpperCase(nome.charAt(1))) && (cf_nome[4] == Character.toUpperCase(nome.charAt(0))) && (cf_nome[5] == 'X'));
                }
            default:
                int posizione_last_cons=0 , posizione_Last_voc=0;

                if (!molteCons(nome)) {
                    for (int i = 3; i < 6; i++) {
                        if (!isVocal(cf_nome[i])) {
                            for (int j = posizione_last_cons; j < nome.length(); j++) {
                                if (!isVocal(nome.charAt(j))) {
                                    if ( Character.toUpperCase(nome.charAt(j)) == cf_nome[i]) {
                                        posizione_last_cons = j + 1;
                                        j = nome.length();
                                    }
                                    else
                                        return false;
                                }
                            }
                        }
                        else {
                            for (int j = posizione_Last_voc; j < nome.length(); j++) {
                                if (isVocal(nome.charAt(j))) {
                                    if (Character.toUpperCase(nome.charAt(j)) == cf_nome[i]) {
                                        posizione_Last_voc = j + 1;
                                        j = nome.length();
                                    }
                                    else
                                       return false;
                                }
                            }

                        }
                    }
                }
                else{
                    boolean jumpedAlready=false;
                    for (int i = 3; i < 6; i++) {
                        if (!isVocal(cf_nome[i])) {
                            for (int j = posizione_last_cons; j < nome.length(); j++) {
                                if (!isVocal(nome.charAt(j))) {
                                    if (Character.toUpperCase(nome.charAt(j)) == cf_nome[i]) {
                                        posizione_last_cons = j + 1;
                                        j = nome.length();
                                    }
                                    else{
                                        if(i!=4 || jumpedAlready)
                                            return false;
                                        jumpedAlready=true;
                                    }
                                }
                            }
                        }
                        else
                            return false;
                        }
                }

        }
        return true;
    }

    public static boolean controlloSesso(char sesso){
        return (Character.toUpperCase(sesso)=='M' || Character.toUpperCase(sesso)=='F');
    }

    public static boolean controlloNazione(char[] cf_nazione,String nazione, String comune){

        if(nazione.equals("Italia") || nazione.equals("italia") || nazione.equals("ITALIA"))
            return (comune!= null && Character.isLetter(cf_nazione[11]));

        else
            return   cf_nazione[11]== 'Z';

    }

    public static boolean controlloAnno(char[] cf_anno, int anno){
       return (cf_anno[6] == (char)(48+((anno%100)/10)) && cf_anno[7] == (char)(48+(anno%10)));
    }

    public static boolean controlloMese(char[] cf_mese, int mese){

        return (cf_mese[8] == 'A' && mese == 1) || (cf_mese[8] == 'B' && mese == 2) || (cf_mese[8] == 'C' && mese == 3) || (cf_mese[8] == 'D' && mese == 4) || (cf_mese[8] == 'E' && mese == 5) || (cf_mese[8] == 'H' && mese == 6) || (cf_mese[8] == 'L' && mese == 7) || (cf_mese[8] == 'M' && mese == 8) || (cf_mese[8] == 'P' && mese == 9) || (cf_mese[8] == 'R' && mese == 10) || (cf_mese[8] == 'S' && mese == 11) || (cf_mese[8] == 'T' && mese == 12);
    }

    public static boolean controlloGiorno(char[] cf_giorno, int giorno, char sesso){
        if (Character.toUpperCase(sesso)== 'F')
            return (cf_giorno[9]== (char)(48+((giorno/10)+4)) && cf_giorno[10]== (char)(48+(giorno%10)));
        else
            return (cf_giorno[9]== (char)(48+(giorno/10)) && cf_giorno[10]== (char)(48+(giorno%10)));
    }

    public static boolean controlloUltimiValori(char[] cf){

        return Character.isDigit(cf[12])&& Character.isDigit(cf[13]) && Character.isDigit(cf[14]) && Character.isLetter(cf[15]);
    }


    static Calendar calendario= Calendar.getInstance();
    //controlliamo che la data inserita non sia quella presente o futura
    //@ requires (calendario.YEAR < data_ins.anno) || ((calendario.YEAR == data_ins.anno) && (calendario.MONTH < data_ins.mese)) || ((calendario.YEAR == data_ins.anno) && (calendario.MONTH == data_ins.mese) && (calendario.DAY_OF_MONTH <= data_ins.giorno));
    public static boolean controlloData(Data data_ins){
        return (calendario.YEAR < data_ins.anno) || ((calendario.YEAR == data_ins.anno) && (calendario.MONTH < data_ins.mese)) || ((calendario.YEAR == data_ins.anno) && (calendario.MONTH == data_ins.mese) && (calendario.DAY_OF_MONTH <= data_ins.giorno));
    }





    public static void main (String [] args){


        Elettore e1= new Elettore("ha", "ia", 15,3,1987,"italia","Varese" ,'F' );
        System.out.println("il cognome: "+ e1.cognome+" e il codice fiscale e'" );
        for (int i=0; i<16 ; i++) {
            System.out.print(e1.getCodiceF()[i] );
        }
        System.out.println(" cognome giusto: "+ controlloCognome(e1.getCodiceF(), e1.cognome)+" nome giusto: "+ controlloNome(e1.getCodiceF(),e1.getNome()));

    }
}
