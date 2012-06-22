package pl.edu.s7245.spolki.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import pl.edu.s7245.spolki.domain.Company;


@ApplicationScoped
public class CompanyManager {
	private List<Company> db = new ArrayList<Company>();

	public void addCompany(Company company) {
		Company newCompany = new Company();

		newCompany.setName(company.getName());
		newCompany.setSymbol(company.getSymbol());
		newCompany.setSector(company.getSector());
		newCompany.setPrice(company.getPrice());
		newCompany.setCity(company.getCity());
		newCompany.setRoad(company.getRoad());
		newCompany.setNumber(company.getNumber());
		newCompany.setPhone(company.getPhone());
		
		db.add(newCompany);
	}

	// Removes the person with given PIN
	public void deleteCompany(Company company) {
		Company companyToRemove = null;
		for (Company c : db) {
			if (company.getSymbol().equals(c.getSymbol())) {
				companyToRemove = c;
				break;
			}
		}
		if (companyToRemove != null)
			db.remove(companyToRemove);
	}

	public List<Company> getAllCompany() {
		return db;
	}
}
