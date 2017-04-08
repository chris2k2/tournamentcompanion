package de.cweyermann.btc.server.control;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Ordering;

import de.cweyermann.btc.server.entity.Group;
import de.cweyermann.btc.server.entity.KoRound;
import de.cweyermann.btc.server.entity.Match;

public class CalculateKoRounds {
	private static final String ACHTELFINALE = "Achtelfinale";
	private static final String VIERTELFINALE = "Viertelfinale";
	private static final String HALBFINALE = "Halbfinale";
	private static final String FINALE = "Finale";
	
	private static final ListMultimap<Integer, String> ROUNDTRANSLATIONS = ArrayListMultimap.create();

	static {
		ROUNDTRANSLATIONS.putAll(0, Arrays.asList(FINALE));
		ROUNDTRANSLATIONS.putAll(1, Arrays.asList(HALBFINALE, FINALE));
		ROUNDTRANSLATIONS.putAll(2, Arrays.asList(VIERTELFINALE, HALBFINALE, FINALE));
		ROUNDTRANSLATIONS.putAll(3, Arrays.asList(ACHTELFINALE, VIERTELFINALE, HALBFINALE, FINALE));
	}

	public void addKoRounds(Group group) {
		if (group.isKo()) {
			ListMultimap<Integer, Match> map = ArrayListMultimap.create();
			for (Match m : group.getMatches()) {
				map.put(m.getRoundnr(), m);
			}

			int max = Ordering.<Integer> natural().max(map.keySet());
			List<String> translations = ROUNDTRANSLATIONS.get(max);

			for (Integer key : map.keySet()) {
				KoRound round = new KoRound();
				round.setMatches(map.get(key));
				round.setRoundNr(key);
				round.setName(translations.get(key));
				group.addRound(round);
			}
		}
	}
}
