package com.daitio.arboremr.patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.daitio.arboremr.MasterController;

@Controller
public class PatientController extends MasterController {
	
	private String patientId;

	@RequestMapping(value = { "/viewpatient.html", "/viewpatient.html{?id=}" }, method = RequestMethod.GET) 
	public ModelAndView viewPatientFormGet(@RequestParam(value = "id", required = false) String id) {
		ModelAndView model = new ModelAndView("view-patient");
		
		if (id != null) {
			Patient p = new Patient();
			startMongoSession();
			
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			ObjectId oid = new ObjectId(id);
			p = pDAO.getPatient(oid);

			addPatientData(model, p);
			
			mongo.close();
			patientId = id;
		}

		return model;	
	}
	
	@RequestMapping(value = { "/viewpatient.html", "/viewpatient.html{?id=}" }, method = RequestMethod.POST)
	public ModelAndView viewPatientFormPost(@RequestParam(value = "id", required = false) String id, 
											@RequestParam String action, 
											@ModelAttribute("patient") Patient patient, 
											@ModelAttribute("weight") Weight w) {
		ModelAndView model = new ModelAndView("view-patient");
		
		if (patientId != null && action.equals("Delete Patient")) {
			startMongoSession();
			
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			pDAO.deletePatient(new ObjectId(patientId));
		
			mongo.close();
		}
		else if (patientId != null && action.equals("Submit Weight")) {
			startMongoSession();
			
			MongoPatientDAO pDAO = new MongoPatientDAO(mongo.getInstance());
			Weight weight = new Weight(null, new Date(), w.getWeight());
			pDAO.checkInWeight(new ObjectId(patientId), weight);
			
			Patient p = new Patient();
			p = pDAO.getPatient(new ObjectId(patientId));

			addPatientData(model, p);

			mongo.close();
		}
				
		return model;
	}
	
	@RequestMapping(value = "/addpatient.html", method = RequestMethod.GET)
	public ModelAndView addPatientFormGet() {
		ModelAndView model = new ModelAndView("add-patient");
		startMongoSession();		
		populatePatientList(model);
		mongo.close();
		return model;
	}

	@RequestMapping(value = "/addpatient.html", method = RequestMethod.POST)
	public ModelAndView addPatientFormPost(@ModelAttribute("user") Patient patient) {
		ModelAndView model = new ModelAndView("add-patient");
		
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
	
	public ModelAndView addPatientData(ModelAndView model, Patient p) {
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
		
		model.addObject("ecFirstName", p.getEmergencyContact().getFirstName());
		model.addObject("ecLastName", p.getEmergencyContact().getLastName());
		model.addObject("ecPrimaryPhone", p.getEmergencyContact().getPrimaryPhone());
		model.addObject("ecSecondaryPhone", p.getEmergencyContact().getSecondaryPhone());
		model.addObject("ecEmail", p.getEmergencyContact().getEmail());
		model.addObject("ecRelationshipToPatient", p.getEmergencyContact().getRelationshipToPatient());
		model.addObject("ecAddress1", p.getEmergencyContact().getAddress1());
		model.addObject("ecAddress2", p.getEmergencyContact().getAddress2());
		model.addObject("ecCity", p.getEmergencyContact().getCity());
		model.addObject("ecState", p.getEmergencyContact().getState());
		model.addObject("ecZip", p.getEmergencyContact().getZip());
		
		model.addObject("today", new Date());
		
		List<WeightString> str = new ArrayList<WeightString>();
		List<Weight> wl = p.getWeightList();
		
		for (int i = 0; i < wl.size(); i++) 
			str.add(new WeightString(new SimpleDateFormat("MMM-dd").format(wl.get(i).getDate()), Integer.toString(wl.get(i).getWeight())));
		
		model.addObject("weightList", str);
		
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
