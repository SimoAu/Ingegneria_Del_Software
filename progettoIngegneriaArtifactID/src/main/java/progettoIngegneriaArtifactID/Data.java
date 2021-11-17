package progettoIngegneriaArtifactID;

import java.util.Calendar;

public class Data {
	
	int giorno;
	int mese;
	int anno;
	Calendar calendario= Calendar.getInstance();

	public Data() {}

	public void setGiorno(int giorno){
		this.giorno=giorno;
	}
	
	public int getGiorno() {
		return giorno;
	}
	
	public void setMese(int mese){
		this.mese=mese;
	}

	public int getMese() {
		return mese;
	}

	public void setAnno(int anno){
		this.anno=anno;
	}

	public int getAnno() {
		return anno;
	}
	
	//@ requires (calendario.YEAR < anno) || ((calendario.YEAR == anno) && (calendario.MONTH < mese)) || ((calendario.YEAR == anno) && (calendario.MONTH == mese) && (calendario.DAY_OF_MONTH <= giorno)); 
	//@ ensures (/result == true) ==> ((calendario.YEAR-anno)>18 || ((calendario.YEAR-anno)==18 && ((calendario.MONTH-mese)>0 || ((calendario.MONTH-mese)==0 && calendario.DAY_OF_MONTH-giorno)>=0));
	
	public boolean isAdult(){

		if((calendario.YEAR-anno)>18){ return true;}
 
		if((calendario.YEAR-anno)==18){

			if((calendario.MONTH-mese)>0){ return true;}

			if((calendario.MONTH-mese)==0){

				if((calendario.DAY_OF_MONTH-giorno)>=0){ return true; }
			}

		}

		return false;
	}

}
