package fr.epsi.ipeda.service.utils;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.EnumMap;
import java.util.Locale;

public class TimeUtils {

	public static enum TIMEFIELD {
		WEEK, YEAR;
	}

	/**
	 * Permet de retourner les informations d'une semaine relative à la date passée en paramètres.
	 * 
	 * @param date
	 *            Date de la semaine courante.
	 * @param offset
	 *            Décalage du nombre de semaines relatif à la date donnée (peut être négatif).
	 * @return Une map contenant le numéro de la semaine et l'année de la semaine concernée.
	 * @see TIMEFIELD
	 */
	public static EnumMap<TIMEFIELD, Integer> getNextWeek(LocalDate date, int offset) {

		EnumMap<TIMEFIELD, Integer> result = new EnumMap<TIMEFIELD, Integer>(TIMEFIELD.class);

		// décale du nombre de semaines spécifié (peut être négatif)
		date = date.plusWeeks(offset);

		// définit le champ temporel de la semaine
		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

		// ajoute au résultat le numéro de la semaine prochaine
		result.put(TIMEFIELD.WEEK, date.get(woy));

		// ajoute au résultat l'année de la semaine prochaine
		result.put(TIMEFIELD.YEAR, date.getYear());

		return result;

	}

	/**
	 * Permet de retourner les informations de la semaine qui suit la date passée en paramètres.
	 * 
	 * @param date
	 *            Date de la semaine courante.
	 * @return Une map contenant le numéro de la semaine et l'année de la semaine concernée.
	 * @see TIMEFIELD
	 */
	public static EnumMap<TIMEFIELD, Integer> getNextWeek(LocalDate date) {
		return getNextWeek(date, 1);
	}

}
