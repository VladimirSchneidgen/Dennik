package dennik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Udaje {
	
	String url, menoMySql, hesloMySql;
	int sirkaObrazu = 424, vyskaObrazu = 700;
	
	public Udaje() {
		
		File subor = new File("C:\\Users\\Asus\\Desktop\\Nový priečinok\\School\\Livin\\JA\\dennik\\prihlasenie.txt");
		
		try {
			Scanner citajSubor = new Scanner(subor);
			while(citajSubor.hasNext()) {
				url = citajSubor.next();
				menoMySql = citajSubor.next();
				hesloMySql = citajSubor.next();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenoMySql() {
		return menoMySql;
	}

	public void setMenoMySql(String menoMySql) {
		this.menoMySql = menoMySql;
	}

	public String getHesloMySql() {
		return hesloMySql;
	}

	public void setHesloMySql(String hesloMySql) {
		this.hesloMySql = hesloMySql;
	}

	public int getSirkaObrazu() {
		return sirkaObrazu;
	}

	public void setSirkaObrazu(int sirkaObrazu) {
		this.sirkaObrazu = sirkaObrazu;
	}

	public int getVyskaObrazu() {
		return vyskaObrazu;
	}

	public void setVyskaObrazu(int vyskaObrazu) {
		this.vyskaObrazu = vyskaObrazu;
	}
	
}
