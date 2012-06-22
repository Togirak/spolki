package pl.edu.s7245.spolki.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import pl.edu.s7245.spolki.domain.Company;
import pl.edu.s7245.spolki.service.CompanyManager;

@SessionScoped
@Named("companyBean")
public class CompanyFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Company company = new Company();
	
	private ListDataModel<Company> companys = new ListDataModel<Company>();
	
	private Map<String, Double> price;
	
	@Inject
	private CompanyManager companyManager;
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public ListDataModel<Company> getAllCompany() {
		companys.setWrappedData(companyManager.getAllCompany());
		return companys;
	}

	// Actions
	public String addCompany() {
		companyManager.addCompany(company);
		return "showCompany";
		//return null;
	}

	public String deleteCompany() {
		Company companyToDelete = companys.getRowData();
		companyManager.deleteCompany(companyToDelete);
		return null;
	}

	// Validators

	// Business logic validation
	public void uniqueSymbol(FacesContext context, UIComponent component,
			Object value) {

		String symbol = (String) value;

		for (Company company : companyManager.getAllCompany()) {
			if (company.getSymbol().equalsIgnoreCase(symbol)) {
				FacesMessage message = new FacesMessage(
						"Company with this Symbol already exists in database");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}
	
	public Map<String, Double> getPrice() {
		price = new HashMap<String, Double>();
		for (Company c : companyManager.getAllCompany()){
			Double value = price.get(c.getSector());
			if(value == null)
				value = 0.0;
			price.put(c.getSector(),value + c.getPrice());
		}
		return price;
	}
	
	public Object[] getSectors(){
		Map<String,Double> map = this.getPrice();
		return map.keySet().toArray();
	}

}
