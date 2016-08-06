package mvc;

import java.util.Comparator;

import comp.Vacancy;

public class SortByID implements Comparator<Vacancy> {

	public SortByID() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Vacancy o1, Vacancy o2) {
		return o1.vacancy_id.compareTo(o2.vacancy_id);
	}

}
