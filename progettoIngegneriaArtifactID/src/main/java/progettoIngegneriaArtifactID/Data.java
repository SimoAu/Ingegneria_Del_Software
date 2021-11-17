package progettoIngegneriaArtifactID;

import java.util.Calendar;

public class Data {
	
	int giorno;
	int mese;
	int anno;

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

	public boolean isAdult(){

		Calendar calendario= Calendar.getInstance();
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
