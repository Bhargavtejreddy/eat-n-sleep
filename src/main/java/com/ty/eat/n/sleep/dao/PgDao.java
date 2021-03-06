package com.ty.eat.n.sleep.dao;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.ExcelSheetHelper.PgExcelSheetGenerator;
import com.ty.eat.n.sleep.ExcelSheetHelper.UserExcelSheetGenerator;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.repository.PgRepository;

@Repository
public class PgDao {
	@Autowired
	private PgRepository pgRepository;

	public Pg savePg(Pg pg) {
		return pgRepository.save(pg);
	}

	public Pg getPg(int id) {
		Optional<Pg> optional = pgRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public List<Pg> getAllPgs() {
		return pgRepository.findAll();
	}

	public boolean deletePg(int id) {
		Pg pg = getPg(id);
		if (pg != null) {
			pgRepository.deleteById(id);
			return true;

		}
		return false;
	}

	public Pg updatePg(int id, Pg pg) {
		Pg existingPg = getPg(id);
		if (existingPg != null) {
			pg.setId(id);
			pgRepository.save(pg);
			return pg;

		}
		return null;
	}
	
	public ByteArrayInputStream load() {
		List<Pg> pgs = pgRepository.findAll();
		ByteArrayInputStream in = PgExcelSheetGenerator.pgsToExcel(pgs);
		return in;
	}

}
