
public class Hurricane implements HurricaneInterface {
	public String name;
	public String dateFormed;
	public String dateDissipated;
	public int mph;
	public int deaths;
	
	public Hurricane(String name, String dateFormed, String dateDissipated, int mph, int deaths) {
		this.name = name;
		this.dateFormed = dateFormed;
		this.dateDissipated = dateDissipated;
		this.mph = mph;
		this.deaths = deaths;
	}
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getDateFormed() {
		return dateFormed;
	}
	@Override
	public String getDateDissipated() {
		return dateDissipated;
	}
	@Override
	public int getMPH() {
		return mph;
	}
	@Override
	public int getDeaths() {
		return deaths;
	}
	@Override
	public String toString() {
		String result = "Hurricane " + name + " formed on " + dateFormed + " and dissipated on " 
	+ dateDissipated +". It was "+mph+" mph and killed " +deaths+" people.";
		return result;
	}

	@Override
	public int compareTo(HurricaneInterface o) {
		int compare= this.getName().compareTo(o.getName());
		if(compare==0){
			return 0;
		}
		else if(compare<0){
			return -1;
		}
		else{
			return 1;
		}
	}
}
