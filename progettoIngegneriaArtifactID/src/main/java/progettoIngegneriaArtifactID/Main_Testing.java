package progettoIngegneriaArtifactID;

public class Main_Testing {

    private boolean isVocal(char c){
        return (Character.toUpperCase(c)=='A' || Character.toUpperCase(c)=='E' ||Character.toUpperCase(c)=='O' ||Character.toUpperCase(c)=='I' || Character.toUpperCase(c)=='U');
    }

    public boolean controlloCognome(char[] cf_cognome, String cognome){

        switch (cognome.length()){

            case 1:

                return ((cf_cognome[0] == cognome.charAt(0)) && (cf_cognome[1] == 'X') && (cf_cognome[2] == 'X'));

            case 2:

                if (!isVocal(cognome.charAt(0)) || (isVocal(cognome.charAt(0))) && isVocal(cognome.charAt(1))) {
                    return ((cf_cognome[0] == cognome.charAt(0)) && (cf_cognome[1] == cognome.charAt(1)) && (cf_cognome[2] == 'X'));
                }
                else {
                    return ((cf_cognome[0] == cognome.charAt(1)) && (cf_cognome[1] == cognome.charAt(0)) && (cf_cognome[2] == 'X'));
                }

            default:

                int posizione_last_cons=0 , posizione_Last_voc=0, posizione=0;

                for (int i = 0; i < 3; i++) {
                    if (!isVocal(cf_cognome[i])) {
                        for (int j=posizione_last_cons; j< cognome.length(); j++){
                            if(!isVocal(cognome.charAt(j))){
                                if (posizione==i && cognome.charAt(j)==cf_cognome[i]) {
                                    posizione_last_cons=j+1;
                                    j = cognome.length();
                                    posizione++;
                                }
                            }
                        }
                        if (posizione==i)
                            return false;
                    }

                    else{
                        for (int j=posizione_Last_voc; j< cognome.length(); j++){
                            if(isVocal(cognome.charAt(j))){
                                if (posizione==i && cognome.charAt(j)==cf_cognome[i]) {
                                    posizione_Last_voc=j+1;
                                    j = cognome.length();
                                    posizione++;
                                }
                            }
                        }
                        if (posizione==i)
                            return false;
                    }
                }
        }
        return true;
    }


    public static void main (String [] args){

        String cognome="giacomini";
        Elettore e1= new Elettore("Giovanni", cognome, 15,3,1987,"Romania","" ,'M' );
        System.out.println("il cognome:"+ cognome+" e il codice fiscale è" );

    }
}
