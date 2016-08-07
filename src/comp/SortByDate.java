package comp;

import java.util.Comparator;

public class SortByDate implements Comparator<Vacancy> {

	public SortByDate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Vacancy o1, Vacancy o2) {
		return o2.published_at.compareTo(o1.published_at);
	}

}
