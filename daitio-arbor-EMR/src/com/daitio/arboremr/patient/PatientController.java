package com.daitio.arboremr.patient;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.MasterController;
import com.daitio.arboremr.user.User;

@Controller
public class PatientController extends MasterController {
	
	private String patientId;

	@RequestMapping(value = { "/viewpatient.html", "/viewpatient.html{?id=}" }, method = RequestMethod.GET) 
	public ModelAndView viewPatientFormGet(@RequestParam(value = "id", required = false) String id) {
		ModelAndView model = new ModelAndView("viewPatient");
		
		if (id != null) {
			Patient p = new Patient();
			startMongoSession();
			
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			ObjectId oid = new ObjectId(id);
			p = pDAO.getPatient(oid);
					
			model.addObject("firstName", p.getFirstName());
			model.addObject("middleName", p.getMiddleName());
			model.addObject("lastName", p.getLastName());
			model.addObject("dateOfBirth", p.getDateOfBirth().toString());
			model.addObject("address1", p.getAddress1());
			model.addObject("address2", p.getAddress2());
			model.addObject("city", p.getCity());
			model.addObject("state", p.getState());
			model.addObject("zip", p.getZip());
			model.addObject("primaryPhone", p.getPrimaryPhone());
			model.addObject("secondaryPhone", p.getSecondaryPhone());
			model.addObject("email", p.getEmail());
			model.addObject("height", p.getHeight());
			model.addObject("insuranceType", p.getInsuranceType());
			mongo.close();
			patientId = id;
		}

		return model;	
	}
	
	@RequestMapping(value = "/viewpatient.html", method = RequestMethod.POST)
	public ModelAndView viewPatientFormPost(@RequestParam String action, @ModelAttribute("patient") Patient patient) {
		ModelAndView model = new ModelAndView("viewPatient");
		
		if (patientId != null && action.equals("Delete Patient")) {
			startMongoSession();
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			pDAO.deletePatient(new ObjectId(patientId));
			mongo.close();
		}
				
		return model;
	}
	
	@RequestMapping(value = "/addpatient.html", method = RequestMethod.GET)
	public ModelAndView addPatientFormGet() {
		ModelAndView model = new ModelAndView("addPatient");
		startMongoSession();		
		populatePatientList(model);
		mongo.close();
		return model;
	}

	@RequestMapping(value = "/addpatient.html", method = RequestMethod.POST)
	public ModelAndView addPatientFormPost(@ModelAttribute("user") Patient patient) {
		ModelAndView model = new ModelAndView("addPatient");
		
		startMongoSession();
		
		MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
		Patient p = Patient.getDummyPatient();
		p.setFirstName(patient.getFirstName());
		p.setLastName(patient.getLastName());
		pDAO.createPatient(p);
		populatePatientList(model);
		mongo.close();
		
		return model;
	}
	
	public void populatePatientList(ModelAndView model) {
		// Requires a mongo session to be open already
		if (mongo != null) {
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			model.addObject("patientList", Patient.getAllPatientsRepeater(pDAO.getAllPatients()));
		}
	}
	
}
