package com.kodtodya.si.jdbc.demo.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TrainingMapper implements RowMapper<Training> {

	public Training mapRow(ResultSet rs, int rowNum) throws SQLException {
		Training training = new Training();
		training.setId(rs.getInt("id"));
		training.setName(rs.getString("name"));
		training.setDurationInDays(rs.getInt("durationInDays"));
		training.setPreRequisite(rs.getString("preRequisite"));
		return training;
	}
}
