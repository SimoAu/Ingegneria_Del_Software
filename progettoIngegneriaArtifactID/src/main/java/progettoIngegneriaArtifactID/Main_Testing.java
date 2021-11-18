package progettoIngegneriaArtifactID;

public class Main_Testing {

    private static boolean isVocal(char c){
        return (Character.toUpperCase(c)=='A' || Character.toUpperCase(c)=='E' ||Character.toUpperCase(c)=='O' ||Character.toUpperCase(c)=='I' || Character.toUpperCase(c)=='U');
    }

    public static boolean controlloCognome(char[] cf_cognome, String cognome){

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

    public static boolean MolteCons (String nome){
        int x=0;
        for(int i=0; i<nome.length(); i++){
            if (!isVocal(Character.toUpperCase(nome.charAt(i))))
                x++;
        }
        return x>3;
    }

    public static boolean controlloNome(char[] cf_nome, String nome) {

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

                if (!MolteCons(nome)) {
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



    public static void main (String [] args){


        Elettore e1= new Elettore("ha", "ia", 15,3,1987,"italia","Varese" ,'F' );
        System.out.println("il cognome: "+ e1.cognome+" e il codice fiscale e'" );
        for (int i=0; i<16 ; i++) {
            System.out.print(e1.getCodiceF()[i] );
        }
        System.out.println(" cognome giusto: "+ controlloCognome(e1.getCodiceF(), e1.cognome)+" nome giusto: "+ controlloNome(e1.getCodiceF(),e1.getNome()));

    }
}
