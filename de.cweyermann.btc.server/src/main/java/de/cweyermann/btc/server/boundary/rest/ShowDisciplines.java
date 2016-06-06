package de.cweyermann.btc.server.boundary.rest;

import de.cweyermann.btc.server.boundary.tpfile.GetDisciplines;
import de.cweyermann.btc.server.entity.Disciplines;
import io.vertx.core.MultiMap;

public class ShowDisciplines extends AbstractRestControl<Disciplines> {
	private GetDisciplines getDisciplines;

	public ShowDisciplines(GetDisciplines getDisciplines) {
		this.getDisciplines = getDisciplines;
	}

	@Override
	public Disciplines route(MultiMap params) {
		return getDisciplines.all();
	}

}
