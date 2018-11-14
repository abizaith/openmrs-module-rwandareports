package org.openmrs.module.rwandareports.reporting;

import org.openmrs.*;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.*;
import org.openmrs.module.reporting.cohort.definition.BaseObsCohortDefinition.TimeModifier;
import org.openmrs.module.reporting.common.DurationUnit;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.dataset.definition.DataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;
import org.openmrs.module.rwandareports.util.Indicators;

import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by jberchmas on 11/21/17.
 */
public class SetupHMISMOHReport {


    Program hivProgram = Context.getProgramWorkflowService().getProgramByUuid("5ea0ebfb-cec3-4cce-9479-58cf2ac5b7fa");
    Program pmtctProgram = Context.getProgramWorkflowService().getProgramByUuid("38ffe579-f99f-469c-99fc-5f8c847c33eb");
    Program ancProgram = Context.getProgramWorkflowService().getProgramByUuid("cf96655d-bcb8-4252-9db5-b7ac7bcf5f04");
    EncounterType transferInEncType = Context.getEncounterService().getEncounterTypeByUuid("f7f09c1b-8181-4a91-9cf6-eb2633ff125e");
    EncounterType HIVVisitEncType = Context.getEncounterService().getEncounterTypeByUuid("2dc31190-cf0e-4ab0-a5a1-6ad601d6ecc0");
    Concept tbScreening = Context.getConceptService().getConceptByUuid("3ce14c2c-26fe-102b-80cb-0017a47871b2");
    Concept onAntiretroviral = Context.getConceptService().getConceptByUuid("3cdc0a8c-26fe-102b-80cb-0017a47871b2");
    Concept TBDrugsConcept = Context.getConceptService().getConceptByUuid("3cd79e0c-26fe-102b-80cb-0017a47871b2");
    //UUID to be replaced by uuid from RBC local test server
    Concept unstable = Context.getConceptService().getConceptByUuid("d208371d-4542-410b-9fec-e76879dbd9f3");
    Concept stable = Context.getConceptService().getConceptByUuid("e5cb0eed-d6c5-44bd-9fc1-d88bdd0a8b12");
    Concept firstLineRegiment = Context.getConceptService().getConceptByUuid("588ad048-1f74-4555-9199-78ae3b358d7e");
    Concept secondLineRegiment = Context.getConceptService().getConceptByUuid("8fc0d105-46e4-4f90-a167-7e5e54434739");
    Concept thrirdLineRegiment = Context.getConceptService().getConceptByUuid("3d8cdb75-d07d-47af-9def-0e397c5fb4d9");
    Concept patientDied = Context.getConceptService().getConceptByUuid("3cdd446a-26fe-102b-80cb-0017a47871b2");
    Concept reasonForExitingFromCare = Context.getConceptService().getConceptByUuid("3cde5ef4-26fe-102b-80cb-0017a47871b2");
    Concept patientDefaulted = Context.getConceptService().getConceptByUuid("3cdd5176-26fe-102b-80cb-0017a47871b2");
    Concept transferedOut = Context.getConceptService().getConceptByUuid("3cdd5c02-26fe-102b-80cb-0017a47871b2");
    Concept PARTNERRESULTOFHIVTEST = Context.getConceptService().getConceptByUuid("34e08543-a000-4e8b-bd9c-435a1c15d1c2");
    Concept HOSPITAL = Context.getConceptService().getConceptByUuid("3ce0d472-26fe-102b-80cb-0017a47871b2");
    Concept HEALTHCENTER = Context.getConceptService().getConceptByUuid("7e327a1a-c1df-4712-ab5d-e05dc0dac35d");
    Concept PlaceOfDelivery = Context.getConceptService().getConceptByUuid("3b761db6-8739-408c-98ef-425d388e0301");
    Concept prophylaxisForMotherInPMTCT = Context.getConceptService().getConceptByUuid("3ce1bd24-26fe-102b-80cb-0017a47871b2");
    Concept expectedDueDate = Context.getConceptService().getConceptByUuid("ea6a744d-846c-4e29-bc89-30404853c4f9");
    Concept birthLocationType = Context.getConceptService().getConceptByUuid("78f156b9-12f6-431a-bd74-d3ad3b146a4e");
    Concept hospital = Context.getConceptService().getConceptByUuid("3ce0d472-26fe-102b-80cb-0017a47871b2");
    Concept healthCenter = Context.getConceptService().getConceptByUuid("7e327a1a-c1df-4712-ab5d-e05dc0dac35d");
    Concept house = Context.getConceptService().getConceptByUuid("4d7f36bb-84d3-4915-95cc-951811c3cd4a");
    Concept antiretroviralMonoTherapyDuringPregnancy = Context.getConceptService().getConceptByUuid("3ce1c328-26fe-102b-80cb-0017a47871b2");
    private EncounterType ANCEncounterType = Context.getEncounterService().getEncounterTypeByUuid("a703372d-28b7-4831-9817-ee385c8c47d8");
    private Concept resultOfHIVTest = Context.getConceptService().getConceptByUuid("3ce17cec-26fe-102b-80cb-0017a47871b2");
    private Concept positive = Context.getConceptService().getConceptByUuid("3cd3a7a2-26fe-102b-80cb-0017a47871b2");
    private Concept negative = Context.getConceptService().getConceptByUuid("3cd28732-26fe-102b-80cb-0017a47871b2");
    private Concept HIVResultRecieveDate = Context.getConceptService().getConceptByUuid("092e2163-e657-4961-b554-96ce2c21d051");
    private Concept partnerHIVTestDate = Context.getConceptService().getConceptByUuid("70214626-b9d2-4f16-a77e-531bfcb2cb04");
    private Concept partnerHIVStatus = Context.getConceptService().getConceptByUuid("cee6dfe4-fa1c-4cef-8acc-c35a4b2e8678");
    private Concept partnerDisclose = Context.getConceptService().getConceptByUuid("3ce0c734-26fe-102b-80cb-0017a47871b2");
    private Concept yes = Context.getConceptService().getConceptByUuid("3cd6f600-26fe-102b-80cb-0017a47871b2");
    private Concept resultOFHIVConfirmatoryTest = Context.getConceptService().getConceptByUuid("e3e9b82e-4c0e-4bb6-a93f-c9fceafac607");
    private Form ANCDeliveryReportForm = Context.getFormService().getFormByUuid("a8bb98ad-443c-43c9-bbc0-1bc8cf7da6ff");
    private Concept antiretroviralTripleTherapyAtBirth = Context.getConceptService().getConceptByUuid("ce1c49a-26fe-102b-80cb-0017a47871b2");
    private Concept nevirapineSingleDoseAfterBirth = Context.getConceptService().getConceptByUuid("3ce1ba2c-26fe-102b-80cb-0017a47871b2");
    private Concept exposedInfantState = Context.getConceptService().getConceptByUuid("2f0c165f-5cd2-4750-bdb2-24f579c99653");
    private Concept HIVTestType = Context.getConceptService().getConceptByUuid("3cdec790-26fe-102b-80cb-0017a47871b2");
    private Concept PCR = Context.getConceptService().getConceptByUuid("3cdbdf94-26fe-102b-80cb-0017a47871b2");
    private Concept cotrimo = Context.getConceptService().getConceptByUuid("3cd51772-26fe-102b-80cb-0017a47871b2");
    private Form VCTCounselingAndTesting = Context.getFormService().getFormByUuid("9ec8c6ce-a67c-4203-b5b5-cdf9739b536e");
    private Form HTCReception = Context.getFormService().getFormByUuid("05cd2514-ef4b-4d78-9701-18884952f22e");
    private Form MCOperativeNotesForm = Context.getFormService().getFormByUuid("5300ca0f-aeba-4fa2-98fa-9323b3981c30");
    private Concept EndDate = Context.getConceptService().getConceptByUuid("1bcaa8f2-e0ff-4a6b-976d-dd1ee78070e1");
    private Form PrepexPlacementProcedureForm = Context.getFormService().getFormByUuid("0dbac4dc-f3cb-48de-a34b-338fe4f9f12a");
    private Form PrepexAndSurgicalConsultationForm = Context.getFormService().getFormByUuid("c7eeb0fd-bf4b-43e3-91a3-0d450f67385a");
    private Concept HIVTESTDATE = Context.getConceptService().getConceptByUuid("3cde8c9e-26fe-102b-80cb-0017a47871b2");
    private Concept HIVSTATUS = Context.getConceptService().getConceptByUuid("aec6ad18-f4dd-4cfa-b68d-3d7bb6ea908e");
    private Form PrepexComplicationInformation = Context.getFormService().getFormByUuid("359571b4-bff1-4890-90bb-9daa4994d782");
    private Form MCFollowVisit = Context.getFormService().getFormByUuid("d47e7a21-6842-4dcc-bec9-bb987f3fb881");
    private Concept PrepexComplications = Context.getConceptService().getConceptByUuid("0ff1ec98-0a95-438b-94df-fbc1ede19f62");
    private Concept MCComplication = Context.getConceptService().getConceptByUuid("1eee14b0-29b9-41bf-9d80-046b6ad8b2f6");
    private Concept TRUE = Context.getConceptService().getConceptByUuid("3ce22110-26fe-102b-80cb-0017a47871b2");
    private Form PITCONSELINGANDTESTING = Context.getFormService().getFormByUuid("2af4fdc8-4468-4541-8a3a-36317525c5ae");
    private EncounterType PITVisitEncounterType = Context.getEncounterService().getEncounterTypeByUuid("6f75c231-240a-4a7d-a1c4-03a621be1867");
    private Form DiscordentCoupleForm = Context.getFormService().getFormByUuid("3ecf2354-c586-4296-b356-c6b77700145c");
    private Concept DISCORDANTCOUPLE = Context.getConceptService().getConceptByUuid("3cf34300-26fe-102b-80cb-0017a47871b2");
    private Concept WHYDIDYOUGETTESTEDFORHIV = Context.getConceptService().getConceptByUuid("3ce37a7e-26fe-102b-80cb-0017a47871b2");
    private Form NutritionStatusMonitoring = Context.getFormService().getFormByUuid("f8761260-3323-4bad-88fc-87edb5d7fb56");
    private EncounterType NutritionEncounterType = Context.getEncounterService().getEncounterTypeByUuid("62935462-e0cd-4f0d-8de3-d6db8f058183");
    private Concept NutritionalTreatmentType = Context.getConceptService().getConceptByUuid("d438020c-112f-499b-afc2-f6defa0d559b");
    private Form NutritionFollowup = Context.getFormService().getFormByUuid("eb6e718c-409f-425e-b545-e1bd2bf6ef83");
    private Form PrepexFormFin = Context.getFormService().getFormByUuid("1d9befb4-9c2d-43c4-bed3-45afe64b1c7c");
    private Form MCSurgicalFormFin = Context.getFormService().getFormByUuid("4a531f30-5ea0-4751-aaea-e3740cde235f");
    private Concept NewDiscordentCouple = Context.getConceptService().getConceptByUuid("5a4e8383-b544-4800-867f-9cdc405618a9");
    private Concept YES = Context.getConceptService().getConceptByUuid("3cd6f600-26fe-102b-80cb-0017a47871b2");
    private Concept NONE = Context.getConceptService().getConceptByUuid("3cd743f8-26fe-102b-80cb-0017a47871b2");
    private Form Prophylaxy = Context.getFormService().getFormByUuid("5f1e6f0b-a4d9-4afc-bc83-67b66560b450");
    private EncounterType HTCVisitEncounterType = Context.getEncounterService().getEncounterTypeByUuid("afa9251e-5d99-44dd-96d9-c0c8f54614d6");
    private Concept PREVIOUSTREATMENTREGIMEN = Context.getConceptService().getConceptByUuid("3cdc4bc8-26fe-102b-80cb-0017a47871b2");
    private Form ANCInvestigationForm = Context.getFormService().getFormByUuid("79f56680-63f7-4209-bb01-83bbd337b34b");
    private Form AdultHIVFlowsheetNewVisit = Context.getFormService().getFormByUuid("6f304bf7-0c4c-48bb-9bea-5642cc333676");
    private Form PediHIVFlowsheetNewVisit = Context.getFormService().getFormByUuid("aae6ea6e-d59c-452c-bb57-9f408203eeff");
    private Form ANCPastMedicalHistory = Context.getFormService().getFormByUuid("edceede1-f1b6-4f95-8fd6-7f27200e5602");

    private Concept DischargeUrethralMale = Context.getConceptService().getConceptByUuid("d587746f-a560-44a2-b991-0fc2f7e4d1eb");
    private Concept DischargeVaginal = Context.getConceptService().getConceptByUuid("04d04ef3-3c47-4d0e-a869-c5565cae5430");
    private Concept PURULENTCONJUCTIVITISOFNEWBORN = Context.getConceptService().getConceptByUuid("78d8930b-0ee4-499a-90f0-e63d2c0755f6");

    private Concept ENEREALVEGETATION = Context.getConceptService().getConceptByUuid("82f887d5-e32d-464b-ba46-48dbb7bc20d2");
    private Concept PELVICINFLAMMATORYDISEASE = Context.getConceptService().getConceptByUuid("3cd4ff6c-26fe-102b-80cb-0017a47871b2");
    private Concept PAINFULSWELLINGOFEPIDIDYMIS = Context.getConceptService().getConceptByUuid("2d20e7a5-64fe-4654-a761-1313e924059f");
    private Concept INGUINALbubo = Context.getConceptService().getConceptByUuid("437e32bb-498f-4880-91d6-00dc3510d9c1");
    private Concept GENITALULCERSCHANCROID = Context.getConceptService().getConceptByUuid("3ce6ce90-26fe-102b-80cb-0017a47871b2");

    private Concept STIDIAGNOSIS = Context.getConceptService().getConceptByUuid("8ada68d5-9c5b-4e51-9f73-52794b976c30");
    private Concept STITest = Context.getConceptService().getConceptByUuid("56218090-7172-4b34-9c62-c9787e1545a8");
    private Concept SYPHILIS = Context.getConceptService().getConceptByUuid("3cceae50-26fe-102b-80cb-0017a47871b2");
    private Concept HERPESSIMPLEXGENITALINFECTION = Context.getConceptService().getConceptByUuid("3cd9e694-26fe-102b-80cb-0017a47871b2");
    private Concept POSITIVE = Context.getConceptService().getConceptByUuid("3cd3a7a2-26fe-102b-80cb-0017a47871b2");
    private Concept CLAMYDIA = Context.getConceptService().getConceptByUuid("14e82298-95fe-4b3a-9f13-f0372fd881cf");
    private Concept GONORRHEA = Context.getConceptService().getConceptByUuid("3cd4eca2-26fe-102b-80cb-0017a47871b2");
    private Concept HIVTESTING = Context.getConceptService().getConceptByUuid("3cdc5ab4-26fe-102b-80cb-0017a47871b2");
    private Concept RESULTOFHIVTEST = Context.getConceptService().getConceptByUuid("3ce17cec-26fe-102b-80cb-0017a47871b2");
    private Concept TYPEOFCOUNSELING = Context.getConceptService().getConceptByUuid("d58a9dbf-650d-44d9-914d-bc5f65e84910");
    private Concept COUPLE = Context.getConceptService().getConceptByUuid("5397f161-a7c1-4924-9d09-cd66dfdff99a");
    private Concept METHODOFFAMILYPLANNING = Context.getConceptService().getConceptByUuid("3ccfbd0e-26fe-102b-80cb-0017a47871b2");
    private Concept CURRENTCOMPLAINTSORSYMPTOMS = Context.getConceptService().getConceptByUuid("3ce2b170-26fe-102b-80cb-0017a47871b2");
    private Concept Degreeofmalnutrition = Context.getConceptService().getConceptByUuid("cbd00171-6328-4bc3-9d32-d358a1fe9629");
    private Concept MALNUTRITIONSEVERE = Context.getConceptService().getConceptByUuid("3cd97fb0-26fe-102b-80cb-0017a47871b2");
    private Concept PROGRAMTHATORDEREDTEST = Context.getConceptService().getConceptByUuid("3ce1e0e2-26fe-102b-80cb-0017a47871b2");
    private Concept PATIENTUSINGFAMILYPLANNING = Context.getConceptService().getConceptByUuid("3cdcf172-26fe-102b-80cb-0017a47871b2");
    private Concept PositivepartneronARV = Context.getConceptService().getConceptByUuid("94f6be96-8fac-4db4-9507-934561cd307b");
    private Concept Newdiscordentcouple = Context.getConceptService().getConceptByUuid("5a4e8383-b544-4800-867f-9cdc405618a9");
    private Concept RETRACED = Context.getConceptService().getConceptByUuid("306ea727-b811-404f-ac2d-07bc86e3d62d");
    private Concept AppointmentDate = Context.getConceptService().getConceptByUuid("4b10f095-9f0d-4403-b050-35ecd8162728");
    private Concept NEGATIVE = Context.getConceptService().getConceptByUuid("3cd28732-26fe-102b-80cb-0017a47871b2");
    private Concept ClassificationofExposure = Context.getConceptService().getConceptByUuid("33e21448-c402-4078-9665-0194fbf66a75");
    private Concept OccupationalExposure = Context.getConceptService().getConceptByUuid("21ebe0a5-b8e5-4fa0-be2f-e8c8034c5b76");
    private Concept RapeSexualassault = Context.getConceptService().getConceptByUuid("fb25eb0e-5d12-47ae-88d3-084ef7a31fd6");
    private Concept NonOccupationalExposure = Context.getConceptService().getConceptByUuid("76851f8b-d26a-4c6a-8d78-6647f4b15104");

    private Concept MethodeOfferte = Context.getConceptService().getConceptByUuid("cd3e9923-a3d3-4309-be05-9e7f604afcd4");
    private Concept ModernContraceptiveMethod = Context.getConceptService().getConceptByUuid("cd3e9923-a3d3-4309-be05-9e7f604afcd4");

    public void setup() throws Exception {
        ReportDefinition rd = createReportDefinition();
        ReportDesign desinTemplate = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "HMISMOHreport.xls", "HMISMOHreport", null);

        Properties props = new Properties();
        props.put("repeatingSections", "sheet:1,dataset:HMIS report dataset");
        props.put("sortWeight", "5000");
        desinTemplate.setProperties(props);
        Helper.saveReportDesign(desinTemplate);


    }

    public void delete() {

        ReportService rs = Context.getService(ReportService.class);
        for (ReportDesign rd : rs.getAllReportDesigns(false)) {
            if ("HMISMOHreport".equals(rd.getName())) {
                rs.purgeReportDesign(rd);
            }
        }
        Helper.purgeReportDefinition("MOH-HMIS Indicator report");

    }

    private ReportDefinition createReportDefinition() {
        ReportDefinition reportDef = new ReportDefinition();
        reportDef.setName("MOH-HMIS Indicator report");
        reportDef.addParameter(new Parameter("startDate", "Start Date", Date.class));
        reportDef.addParameter(new Parameter("endDate", "End Date", Date.class));
        reportDef.addParameter(new Parameter("location", "Health facility", Location.class));

        SqlCohortDefinition locationAndProgram = new SqlCohortDefinition();
        locationAndProgram.setQuery("select p.patient_id from patient p, person_attribute pa, person_attribute_type pat,patient_program pp where p.patient_id = pa.person_id and p.patient_id = pp.patient_id and pp.program_id in (" + hivProgram.getProgramId() + "," + pmtctProgram.getProgramId() + "," + ancProgram.getProgramId() + ") and pat.name ='Health Facility' and pa.voided = 0 and pat.person_attribute_type_id = pa.person_attribute_type_id and pa.value = :location");
        locationAndProgram.setName("Location and program cohort def");
        locationAndProgram.addParameter(new Parameter("location", "location", Location.class));

        reportDef.setBaseCohortDefinition(locationAndProgram, ParameterizableUtil.createParameterMappings("location=${location}"));
        reportDef.addDataSetDefinition(createDataSetDefinition(), ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}"));
        //reportDef.addDataSetDefinition(createARTDataSetDefinition(),ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}"));
        Helper.saveReportDefinition(reportDef);

        return reportDef;
    }

    private DataSetDefinition createDataSetDefinition() {
        CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
        dsd.setName("HMIS report dataset");
        dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
        dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
        dsd.addParameter(new Parameter("location", "Health facility", Location.class));

        EncounterCohortDefinition patientsWithTransferInEncounter = new EncounterCohortDefinition();
        patientsWithTransferInEncounter.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithTransferInEncounter.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientsWithTransferInEncounter.addEncounterType(transferInEncType);

        //====================================================
        //  A. ENROLLEMENT IN THE PROGRAM
        //====================================================
        //================================================================================
        //  A01: Total number of male patients newly enrolled in HIV Care and Treatment between 0 and 4 years
        //====================================================================================
        //Male patient
        GenderCohortDefinition males = new GenderCohortDefinition();
        males.setName("male Patients");
        males.setMaleIncluded(true);

        // Age between 0 and 4
        AgeCohortDefinition patientsWithAgeBetween0An4Years = new AgeCohortDefinition();
        patientsWithAgeBetween0An4Years.setName("patientsWithAgeBetween0An4Years");
        patientsWithAgeBetween0An4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween0An4Years.setMaxAge(4);
        patientsWithAgeBetween0An4Years.setMaxAgeUnit(DurationUnit.YEARS);

        // Newly Enrolled In HIV program
        ProgramEnrollmentCohortDefinition newlyEnrolledInHIV = new ProgramEnrollmentCohortDefinition();
        newlyEnrolledInHIV.setName("newlyEnrolledInHIV");
        newlyEnrolledInHIV.addProgram(hivProgram);
        newlyEnrolledInHIV.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newlyEnrolledInHIV.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));

        CompositionCohortDefinition newMalePatientsUnder4Years = new CompositionCohortDefinition();
        newMalePatientsUnder4Years.setName("newMalePatientsUnder4Years");
        newMalePatientsUnder4Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newMalePatientsUnder4Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newMalePatientsUnder4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newMalePatientsUnder4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newMalePatientsUnder4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newMalePatientsUnder4Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newMalePatientsUnder4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween0An4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newMalePatientsUnder4Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newMalePatientsUnder4Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newMalePatientsUnder4Years.setCompositionString("(1 AND 2 AND 3) AND (NOT 4)");
        newMalePatientsUnder4Years.setCompositionString("(1 AND 2 AND 3)");

        CohortIndicator newMalePatientsUnder4YearsIndicator = Indicators.newCohortIndicator("newMalePatientsUnder4YearsIndicator",
                newMalePatientsUnder4Years, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("A01", "Total number of male patients newly enrolled in HIV Care and Treatment between 0 and 4 years", new Mapped(newMalePatientsUnder4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //================================================================================
        //  A02: Total number of female patients newly enrolled in HIV Care and Treatment between 0 and 4 years
        //====================================================================================

        CompositionCohortDefinition newFemalePatientsUnder4Years = new CompositionCohortDefinition();
        newFemalePatientsUnder4Years.setName("newFemalePatientsUnder4Years");
        newFemalePatientsUnder4Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newFemalePatientsUnder4Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newFemalePatientsUnder4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newFemalePatientsUnder4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newFemalePatientsUnder4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newFemalePatientsUnder4Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newFemalePatientsUnder4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween0An4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newFemalePatientsUnder4Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newFemalePatientsUnder4Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newFemalePatientsUnder4Years.setCompositionString("(2 AND 3 AND (NOT 1)) AND (NOT 4)");
        newFemalePatientsUnder4Years.setCompositionString("(2 AND 3 AND (NOT 1))");

        CohortIndicator newFemalePatientsUnder4YearsIndicator = Indicators.newCohortIndicator("newFemalePatientsUnder4YearsIndicator",
                newFemalePatientsUnder4Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A02", "Total number of female patients newly enrolled in HIV Care and Treatment between 0 and 4 years", new Mapped(newFemalePatientsUnder4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================================
        //  A03: Total number of male patients newly enrolled in HIV Care and Treatment between 5 and 9 years
        //======================================================================

        AgeCohortDefinition patientsWithAgeBetween5An9Years = new AgeCohortDefinition();
        patientsWithAgeBetween5An9Years.setName("patientsWithAgeBetween5An9Years");
        patientsWithAgeBetween5An9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween5An9Years.setMinAge(5);
        patientsWithAgeBetween5An9Years.setMaxAge(9);
        patientsWithAgeBetween5An9Years.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition newMalePatientsBetween5And9Years = new CompositionCohortDefinition();
        newMalePatientsBetween5And9Years.setName("newMalePatientsBetween5And9Years");
        newMalePatientsBetween5And9Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newMalePatientsBetween5And9Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newMalePatientsBetween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newMalePatientsBetween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newMalePatientsBetween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newMalePatientsBetween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newMalePatientsBetween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newMalePatientsBetween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newMalePatientsBetween5And9Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newMalePatientsBetween5And9Years.setCompositionString("(1 AND 2 AND 3) AND (NOT 4)");
        newMalePatientsBetween5And9Years.setCompositionString("(1 AND 2 AND 3)");

        CohortIndicator newMalePatientsBetween5And9YearsIndicator = Indicators.newCohortIndicator("newMalePatientsBetween5And9YearsIndicator",
                newMalePatientsBetween5And9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A03", "Total number of male patients newly enrolled in HIV Care and Treatment between 5 and 9 years", new Mapped(newMalePatientsBetween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================================
        //  A04: Total number of female patients newly enrolled in HIV Care and Treatment between 5 and 9 years
        //======================================================================

        CompositionCohortDefinition newFemalePatientsBetween5And9Years = new CompositionCohortDefinition();
        newFemalePatientsBetween5And9Years.setName("newFemalePatientsBetween5And9Years");
        newFemalePatientsBetween5And9Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newFemalePatientsBetween5And9Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newFemalePatientsBetween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newFemalePatientsBetween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newFemalePatientsBetween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newFemalePatientsBetween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newFemalePatientsBetween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newFemalePatientsBetween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newFemalePatientsBetween5And9Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newFemalePatientsBetween5And9Years.setCompositionString("(3 AND 2 AND (NOT 1)) AND (NOT 4)");
        newFemalePatientsBetween5And9Years.setCompositionString("(3 AND 2 AND (NOT 1))");

        CohortIndicator newFemalePatientsBetween5And9YearsIndicator = Indicators.newCohortIndicator("newFemalePatientsBetween5And9YearsIndicator",
                newFemalePatientsBetween5And9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A04", "Total number of female patients newly enrolled in HIV Care and Treatment between 5 and 9 years", new Mapped(newFemalePatientsBetween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================================
        //  A05: Total number of male patients newly enrolled in HIV Care and Treatment between 10 and 14 years
        //======================================================================

        AgeCohortDefinition patientsWithAgeBetween10An14Years = new AgeCohortDefinition();
        patientsWithAgeBetween10An14Years.setName("patientsWithAgeBetween10An14Years");
        patientsWithAgeBetween10An14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween10An14Years.setMinAge(10);
        patientsWithAgeBetween10An14Years.setMaxAge(14);
        patientsWithAgeBetween10An14Years.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition newMalePatientsBetween10And14Years = new CompositionCohortDefinition();
        newMalePatientsBetween10And14Years.setName("newMalePatientsBetween10And14Years");
        newMalePatientsBetween10And14Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newMalePatientsBetween10And14Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newMalePatientsBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newMalePatientsBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newMalePatientsBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newMalePatientsBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newMalePatientsBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newMalePatientsBetween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newMalePatientsBetween10And14Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newMalePatientsBetween10And14Years.setCompositionString("(1 AND 2 AND 3) AND (NOT 4)");
        newMalePatientsBetween10And14Years.setCompositionString("(1 AND 2 AND 3)");

        CohortIndicator newMalePatientsBetween10And14YearsIndicator = Indicators.newCohortIndicator("newMalePatientsBetween10And14YearsIndicator",
                newMalePatientsBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A05", "Total number of male patients newly enrolled in HIV Care and Treatment between 10 and 14 years", new Mapped(newMalePatientsBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================================
        //  A06: Total number of female patients newly enrolled in HIV Care and Treatment between 10 and 14 years
        //======================================================================

        CompositionCohortDefinition newFemalePatientsBetween10And14Years = new CompositionCohortDefinition();
        newFemalePatientsBetween10And14Years.setName("newFemalePatientsBetween10And14Years");
        newFemalePatientsBetween10And14Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newFemalePatientsBetween10And14Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newFemalePatientsBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newFemalePatientsBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newFemalePatientsBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newFemalePatientsBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newFemalePatientsBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newFemalePatientsBetween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newFemalePatientsBetween10And14Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newFemalePatientsBetween10And14Years.setCompositionString("(3 AND 2 AND (NOT 1)) AND (NOT 4)");
        newFemalePatientsBetween10And14Years.setCompositionString("(3 AND 2 AND (NOT 1))");

        CohortIndicator newFemalePatientsBetween10And14YearsIndicator = Indicators.newCohortIndicator("newFemalePatientsBetween10And14YearsIndicator",
                newFemalePatientsBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A06", "Total number of female patients newly enrolled in HIV Care and Treatment between 10 and 14 years", new Mapped(newFemalePatientsBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================================
        //  A07: Total number of male patients newly enrolled in HIV Care and Treatment between 15 and 19 years
        //======================================================================

        AgeCohortDefinition patientsWithAgeBetween15An19Years = new AgeCohortDefinition();
        patientsWithAgeBetween15An19Years.setName("patientsWithAgeBetween10An14Years");
        patientsWithAgeBetween15An19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween15An19Years.setMinAge(15);
        patientsWithAgeBetween15An19Years.setMaxAge(19);
        patientsWithAgeBetween15An19Years.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition newMalePatientsBetween15And19Years = new CompositionCohortDefinition();
        newMalePatientsBetween15And19Years.setName("newMalePatientsBetween10And14Years");
        newMalePatientsBetween15And19Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newMalePatientsBetween15And19Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newMalePatientsBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newMalePatientsBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newMalePatientsBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newMalePatientsBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newMalePatientsBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newMalePatientsBetween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newMalePatientsBetween15And19Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newMalePatientsBetween15And19Years.setCompositionString("(1 AND 2 AND 3) AND (NOT 4)");
        newMalePatientsBetween15And19Years.setCompositionString("(1 AND 2 AND 3)");

        CohortIndicator newMalePatientsBetween15And19YearsIndicator = Indicators.newCohortIndicator("newMalePatientsBetween15And19YearsIndicator",
                newMalePatientsBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A07", "Total number of male patients newly enrolled in HIV Care and Treatment between 15 and 19 years", new Mapped(newMalePatientsBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================================
        //  A08: Total number of female patients newly enrolled in HIV Care and Treatment between 15 and 19 years
        //======================================================================

        CompositionCohortDefinition newFemalePatientsBetween15And19Years = new CompositionCohortDefinition();
        newFemalePatientsBetween15And19Years.setName("newFemalePatientsBetween15And19Years");
        newFemalePatientsBetween15And19Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newFemalePatientsBetween15And19Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newFemalePatientsBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newFemalePatientsBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newFemalePatientsBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newFemalePatientsBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newFemalePatientsBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newFemalePatientsBetween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newFemalePatientsBetween15And19Years.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newFemalePatientsBetween15And19Years.setCompositionString("(3 AND 2 AND (NOT 1)) AND (NOT 4)");
        newFemalePatientsBetween15And19Years.setCompositionString("(3 AND 2 AND (NOT 1))");

        CohortIndicator newFemalePatientsBetween15And19YearsIndicator = Indicators.newCohortIndicator("newFemalePatientsBetween15And19YearsIndicator",
                newFemalePatientsBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A08", "Total number of female patients newly enrolled in HIV Care and Treatment between 15 and 19 years", new Mapped(newFemalePatientsBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================================
        //  A09: Total number of male patients newly enrolled in HIV Care and Treatment with 20 years and above
        //======================================================================

        AgeCohortDefinition patientsWithAge20AndAbove = new AgeCohortDefinition();
        patientsWithAge20AndAbove.setName("patientsWithAge20AndAbove");
        patientsWithAge20AndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAge20AndAbove.setMinAge(20);
        patientsWithAge20AndAbove.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition newMalePatients20YearsAndAbove = new CompositionCohortDefinition();
        newMalePatients20YearsAndAbove.setName("newMalePatients20YearsAndAbove");
        newMalePatients20YearsAndAbove.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newMalePatients20YearsAndAbove.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newMalePatients20YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newMalePatients20YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newMalePatients20YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newMalePatients20YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newMalePatients20YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge20AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newMalePatients20YearsAndAbove.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newMalePatients20YearsAndAbove.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newMalePatients20YearsAndAbove.setCompositionString("(1 AND 2 AND 3) AND (NOT 4)");
        newMalePatients20YearsAndAbove.setCompositionString("(1 AND 2 AND 3)");

        CohortIndicator newMalePatients20YearsAndAboveIndicator = Indicators.newCohortIndicator("newMalePatients20YearsAndAboveIndicator",
                newMalePatients20YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A09", "Total number of male patients newly enrolled in HIV Care and Treatment with 20 years and above", new Mapped(newMalePatients20YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================================
        //  A10: Total number of female patients newly enrolled in HIV Care and Treatment between 15 and 19 years
        //======================================================================

        CompositionCohortDefinition newFemalePatients20YearsAndAbove = new CompositionCohortDefinition();
        newFemalePatients20YearsAndAbove.setName("newFemalePatients20YearsAndAbove");
        newFemalePatients20YearsAndAbove.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newFemalePatients20YearsAndAbove.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newFemalePatients20YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newFemalePatients20YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newFemalePatients20YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newFemalePatients20YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newFemalePatients20YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge20AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newFemalePatients20YearsAndAbove.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newFemalePatients20YearsAndAbove.getSearches().put("4",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newFemalePatients20YearsAndAbove.setCompositionString("(3 AND 2 AND (NOT 1)) AND (NOT 4)");
        newFemalePatients20YearsAndAbove.setCompositionString("(3 AND 2 AND (NOT 1))");

        CohortIndicator newFemalePatients20YearsAndAboveIndicator = Indicators.newCohortIndicator("newFemalePatients20YearsAndAboveIndicator",
                newFemalePatients20YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("A10", "Total number of female patients newly enrolled in HIV Care and Treatment with 20 years and above", new Mapped(newFemalePatients20YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //================================================
        // A11: New patients screened for active TB at enrollment this month
        //============================================

        CodedObsCohortDefinition patientWithTBScreening = new CodedObsCohortDefinition();
        patientWithTBScreening.setQuestion(tbScreening);
        patientWithTBScreening.setTimeModifier(TimeModifier.ANY);
        patientWithTBScreening.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithTBScreening.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition newPatientsWithTBScreening = new CompositionCohortDefinition();
        newPatientsWithTBScreening.setName("newPatientsWithTBScreening");
        newPatientsWithTBScreening.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newPatientsWithTBScreening.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newPatientsWithTBScreening.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newPatientsWithTBScreening.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newPatientsWithTBScreening.getSearches().put("1", new Mapped<CohortDefinition>(patientWithTBScreening, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        newPatientsWithTBScreening.getSearches().put("2", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newPatientsWithTBScreening.getSearches().put("3",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newPatientsWithTBScreening.setCompositionString("1 and 2 and (not 3)");
        newPatientsWithTBScreening.setCompositionString("1 and 2");

        CohortIndicator newPatientsWithTBScreeningIndicator = Indicators.newCohortIndicator("newPatientsWithTBScreeningIndicator",
                newPatientsWithTBScreening, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate}"));

        dsd.addColumn("A11", "New patients screened for active TB at enrollment this month", new Mapped(newPatientsWithTBScreeningIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //================================================
        // A12: New patients screened for active positive TB at enrollment this month
        //============================================
        // Concept answer=Context.getConceptService().getConcept("POSITIVE");

        Concept answer = Context.getConceptService().getConceptByUuid("3cd3a7a2-26fe-102b-80cb-0017a47871b2");

        SqlCohortDefinition patientWithPositiveTBScreening = new SqlCohortDefinition();
        patientWithPositiveTBScreening.setQuery("select person_id from obs where concept_id=" + tbScreening.getConceptId() + " and value_coded=" + answer.getConceptId() + " and voided=0 and obs_datetime>= :onOrAfter and obs_datetime<= :onOrBefore");
        patientWithPositiveTBScreening.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPositiveTBScreening.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition newPatientsWithPositiveTBScreening = new CompositionCohortDefinition();
        newPatientsWithPositiveTBScreening.setName("newPatientsWithPositiveTBScreening");
        newPatientsWithPositiveTBScreening.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newPatientsWithPositiveTBScreening.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        newPatientsWithPositiveTBScreening.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newPatientsWithPositiveTBScreening.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newPatientsWithPositiveTBScreening.getSearches().put("1", new Mapped<CohortDefinition>(patientWithPositiveTBScreening, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        newPatientsWithPositiveTBScreening.getSearches().put("2", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        //newPatientsWithPositiveTBScreening.getSearches().put("3",new Mapped<CohortDefinition>(patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //newPatientsWithPositiveTBScreening.setCompositionString("1 and 2 and (not 3)");
        newPatientsWithPositiveTBScreening.setCompositionString("1 and 2");

        CohortIndicator newPatientsWithPositiveTBScreeningIndicator = Indicators.newCohortIndicator("newPatientsWithPositiveTBScreeningIndicator",
                newPatientsWithPositiveTBScreening, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate}"));


        dsd.addColumn("A12", "Patients screened for TB Positive at enrollment this month", new Mapped(newPatientsWithPositiveTBScreeningIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=======================================================================================================
        // B. B. ART Data Elements
        //=======================================================================================================

        //============================================
        // B01 Total number of male patients with less than 1 year currently on ART
        //==========================================

        AgeCohortDefinition patientBelowOneYear = patientWithAgeBelow(1);


        InStateCohortDefinition onART = new InStateCohortDefinition();
        onART.addState(hivProgram.getWorkflowByName("TREATMENT STATUS").getState(onAntiretroviral));
        onART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        onART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition malePatientBelowOneYearOnART = new CompositionCohortDefinition();
        malePatientBelowOneYearOnART.setName("malePatientBelowOneYearOnART");
        malePatientBelowOneYearOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBelowOneYearOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBelowOneYearOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBelowOneYearOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBelowOneYearOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBelowOneYearOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBelowOneYearOnART.setCompositionString("1 and 2 and 3");

        CohortIndicator malePatientBelowOneYearOnARTIndicator = Indicators.newCohortIndicator("malePatientBelowOneYearOnARTIndicator",
                malePatientBelowOneYearOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B01", "Total number of male patients with less than 1 year currently on ART", new Mapped(malePatientBelowOneYearOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //============================================
        // B02 Total number of female patients with less than 1 year currently on ART
        //==========================================

        CompositionCohortDefinition femalePatientBelowOneYearOnART = new CompositionCohortDefinition();
        femalePatientBelowOneYearOnART.setName("femalePatientBelowOneYearOnART");
        femalePatientBelowOneYearOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBelowOneYearOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBelowOneYearOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBelowOneYearOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBelowOneYearOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBelowOneYearOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBelowOneYearOnART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalePatientBelowOneYearOnARTIndicator = Indicators.newCohortIndicator("femalePatientBelowOneYearOnARTIndicator",
                femalePatientBelowOneYearOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B02", "Total number of female patients with less than 1 year currently on ART", new Mapped(femalePatientBelowOneYearOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B03 Total number of male patients between 1 and 4 years currently on ART
        //==========================================


        AgeCohortDefinition patientsBitween1And4Years = patientWithAgeBetween(1, 4);

        CompositionCohortDefinition malepatientsBitween1And4YearsOnART = new CompositionCohortDefinition();
        malepatientsBitween1And4YearsOnART.setName("malepatientsBitween1And4YearsOnART");
        malepatientsBitween1And4YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween1And4YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientsBitween1And4YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientsBitween1And4YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween1And4YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween1And4YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientsBitween1And4YearsOnART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween1And4YearsOnARTIndicator = Indicators.newCohortIndicator("malepatientsBitween1And4YearsOnARTIndicator",
                malepatientsBitween1And4YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B03", "Total number of male patients between 1 and 4 years currently on ART", new Mapped(malepatientsBitween1And4YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B04 Total number of female patients between 1 and 4 years currently on ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween1And4YearsOnART = new CompositionCohortDefinition();
        femalepatientsBitween1And4YearsOnART.setName("femalepatientsBitween1And4YearsOnART");
        femalepatientsBitween1And4YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween1And4YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientsBitween1And4YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientsBitween1And4YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween1And4YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween1And4YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientsBitween1And4YearsOnART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween1And4YearsOnARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween1And4YearsOnARTIndicator",
                femalepatientsBitween1And4YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B04", "Total number of male patients between 1 and 4 years currently on ART", new Mapped(femalepatientsBitween1And4YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B05 Total number of male patients between 5 and 9 years currently on ART
        //==========================================


        AgeCohortDefinition patientsBitween5And9Years = patientWithAgeBetween(5, 9);

        CompositionCohortDefinition malepatientsBitween5And9YearsOnART = new CompositionCohortDefinition();
        malepatientsBitween5And9YearsOnART.setName("malepatientsBitween4And9YearsOnART");
        malepatientsBitween5And9YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween5And9YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientsBitween5And9YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientsBitween5And9YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween5And9YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween5And9YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientsBitween5And9YearsOnART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween5And9YearsOnARTIndicator = Indicators.newCohortIndicator("malepatientsBitween5And9YearsOnARTIndicator",
                malepatientsBitween5And9YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B05", "Total number of male patients between 5 and 9 years currently on ART", new Mapped(malepatientsBitween5And9YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==========================================
        // B06 Total number of female patients between 5 and 9 years currently on ART
        //==========================================


        CompositionCohortDefinition femalepatientsBitween5And9YearsOnART = new CompositionCohortDefinition();
        femalepatientsBitween5And9YearsOnART.setName("femalepatientsBitween5And9YearsOnART");
        femalepatientsBitween5And9YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween5And9YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientsBitween5And9YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientsBitween5And9YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween5And9YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween5And9YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientsBitween5And9YearsOnART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween5And9YearsOnARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween5And9YearsOnARTIndicator",
                femalepatientsBitween5And9YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B06", "Total number of male patients between 5 and 9 years currently on ART", new Mapped(femalepatientsBitween5And9YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B07 Total number of male patients between 10 and 14 years currently on ART
        //==========================================


        AgeCohortDefinition patientsBitween10And14Years = patientWithAgeBetween(10, 14);

        CompositionCohortDefinition malepatientsBitween10And14YearsOnART = new CompositionCohortDefinition();
        malepatientsBitween10And14YearsOnART.setName("malepatientsBitween10And14YearsOnART");
        malepatientsBitween10And14YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween10And14YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientsBitween10And14YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientsBitween10And14YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween10And14YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween10And14YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientsBitween10And14YearsOnART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween10And14YearsOnARTIndicator = Indicators.newCohortIndicator("malepatientsBitween10And14YearsOnARTIndicator",
                malepatientsBitween10And14YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B07", "Total number of male patients between 10 and 14 years currently on ART", new Mapped(malepatientsBitween10And14YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==========================================
        // B07 Total number of female patients between 10 and 14 years currently on ART
        //==========================================


        CompositionCohortDefinition femalepatientsBitween10And14YearsOnART = new CompositionCohortDefinition();
        femalepatientsBitween10And14YearsOnART.setName("femalepatientsBitween10And14YearsOnART");
        femalepatientsBitween10And14YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween10And14YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientsBitween10And14YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientsBitween10And14YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween10And14YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween10And14YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientsBitween10And14YearsOnART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween10And14YearsOnARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween10And14YearsOnARTIndicator",
                femalepatientsBitween10And14YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B08", "Total number of female patients between 10 and 14 years currently on ART", new Mapped(femalepatientsBitween10And14YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //============================================
        // B09 Total number of male patients between 15 and 19 years currently on ART
        //==========================================

        AgeCohortDefinition patientsBitween15And19Years = patientWithAgeBetween(15, 19);

        CompositionCohortDefinition malepatientsBitween15And19YearsOnART = new CompositionCohortDefinition();
        malepatientsBitween15And19YearsOnART.setName("malepatientsBitween10And14YearsOnART");
        malepatientsBitween15And19YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween15And19YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientsBitween15And19YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientsBitween15And19YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween15And19YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween15And19YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientsBitween15And19YearsOnART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween15And19YearsOnARTIndicator = Indicators.newCohortIndicator("malepatientsBitween15And19YearsOnARTIndicator",
                malepatientsBitween15And19YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B09", "Total number of male patients between 15 and 19 years currently on ART", new Mapped(malepatientsBitween15And19YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==========================================
        // B10 Total number of female patients between 15 and 19 years currently on ART
        //==========================================


        CompositionCohortDefinition femalepatientsBitween15And19YearsOnART = new CompositionCohortDefinition();
        femalepatientsBitween15And19YearsOnART.setName("femalepatientsBitween15And19YearsOnART");
        femalepatientsBitween15And19YearsOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween15And19YearsOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientsBitween15And19YearsOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientsBitween15And19YearsOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween15And19YearsOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween15And19YearsOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientsBitween15And19YearsOnART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween15And19YearsOnARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween15And19YearsOnARTIndicator",
                femalepatientsBitween15And19YearsOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B10", "Total number of female patients between 10 and 14 years currently on ART", new Mapped(femalepatientsBitween15And19YearsOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //============================================
        // B11 Total number of male patients with 20 years and above currently on ART
        //==========================================

        AgeCohortDefinition patientsWith20YearsAndAbove = patientWithAgeAbove(20);

        CompositionCohortDefinition malepatientsWith20YearsAndAboveOnART = new CompositionCohortDefinition();
        malepatientsWith20YearsAndAboveOnART.setName("malepatientsWith20YearsAndAboveOnART");
        malepatientsWith20YearsAndAboveOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsWith20YearsAndAboveOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientsWith20YearsAndAboveOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientsWith20YearsAndAboveOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith20YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsWith20YearsAndAboveOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsWith20YearsAndAboveOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientsWith20YearsAndAboveOnART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsWith20YearsAndAboveOnARTIndicator = Indicators.newCohortIndicator("malepatientsWith20YearsAndAboveOnARTIndicator",
                malepatientsWith20YearsAndAboveOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B11", "Total number of male patients with 20 years and above currently on ART", new Mapped(malepatientsWith20YearsAndAboveOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==========================================
        // B12 Total number of female patients with 20 years and above currently on ART
        //==========================================


        CompositionCohortDefinition femalepatientsWith20YearsAndAboveOnART = new CompositionCohortDefinition();
        femalepatientsWith20YearsAndAboveOnART.setName("femalepatientsWith20YearsAndAboveOnART");
        femalepatientsWith20YearsAndAboveOnART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsWith20YearsAndAboveOnART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientsWith20YearsAndAboveOnART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientsWith20YearsAndAboveOnART.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith20YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsWith20YearsAndAboveOnART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsWith20YearsAndAboveOnART.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientsWith20YearsAndAboveOnART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsWith20YearsAndAboveOnARTIndicator = Indicators.newCohortIndicator("femalepatientsWith20YearsAndAboveOnARTIndicator",
                femalepatientsWith20YearsAndAboveOnART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B12", "Total number of female patients with 20F1 years and above currently on ART", new Mapped(femalepatientsWith20YearsAndAboveOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


//======================================================================================
// B13 Total number of male patients with less than 1 year who initiated ART
//======================================================================================

        PatientStateCohortDefinition patientInitiatedART = new PatientStateCohortDefinition();
        patientInitiatedART.setName("patientInitiatedART");
        patientInitiatedART.addState(hivProgram.getWorkflowByName("TREATMENT STATUS").getState(onAntiretroviral));
        patientInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        patientInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));


        CompositionCohortDefinition malePatientBelowOneYearInitiatedART = new CompositionCohortDefinition();
        malePatientBelowOneYearInitiatedART.setName("malePatientBelowOneYearInitiatedART");
        malePatientBelowOneYearInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBelowOneYearInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malePatientBelowOneYearInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malePatientBelowOneYearInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBelowOneYearInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBelowOneYearInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malePatientBelowOneYearInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malePatientBelowOneYearInitiatedARTIndicator = Indicators.newCohortIndicator("malePatientBelowOneYearInitiatedARTIndicator",
                malePatientBelowOneYearInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B13", "Total number of male patients with less than 1 year who initiated ART", new Mapped(malePatientBelowOneYearInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

//===========================================================
//  B14 Total number of female patients with less than 1 year who initiated ART
//======================================================

        CompositionCohortDefinition femalePatientBelowOneYearInitiatedART = new CompositionCohortDefinition();
        femalePatientBelowOneYearInitiatedART.setName("femalePatientBelowOneYearInitiatedART");
        femalePatientBelowOneYearInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBelowOneYearInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalePatientBelowOneYearInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalePatientBelowOneYearInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBelowOneYearInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBelowOneYearInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalePatientBelowOneYearInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalePatientBelowOneYearInitiatedARTIndicator = Indicators.newCohortIndicator("femalePatientBelowOneYearOnARTIndicator",
                femalePatientBelowOneYearInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B14", "Total number of female patients with less than 1 year who initiated ART", new Mapped(femalePatientBelowOneYearInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B15 Total number of male patients between 1 and 4 years who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsBitween1And4YearsInitiatedART = new CompositionCohortDefinition();
        malepatientsBitween1And4YearsInitiatedART.setName("malepatientsBitween1And4YearsInitiatedART");
        malepatientsBitween1And4YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween1And4YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsBitween1And4YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsBitween1And4YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween1And4YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween1And4YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsBitween1And4YearsInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween1And4YearsInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsBitween1And4YearsOnARTIndicator",
                malepatientsBitween1And4YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B15", "Total number of male patients between 1 and 4 years who initiated ART", new Mapped(malepatientsBitween1And4YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B16 Total number of female patients between 1 and 4 years who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween1And4YearsInitiatedART = new CompositionCohortDefinition();
        femalepatientsBitween1And4YearsInitiatedART.setName("femalepatientsBitween1And4YearsOnART");
        femalepatientsBitween1And4YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween1And4YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsBitween1And4YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsBitween1And4YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween1And4YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween1And4YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsBitween1And4YearsInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween1And4YearsInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween1And4YearsInitiatedARTIndicator",
                femalepatientsBitween1And4YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B16", "Total number of female patients between 1 and 4 years who initiated ART", new Mapped(femalepatientsBitween1And4YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //============================================
        // B17 Total number of male patients between 5 and 9 years who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsBitween5And9YearsInitiatedART = new CompositionCohortDefinition();
        malepatientsBitween5And9YearsInitiatedART.setName("malepatientsBitween1And4YearsInitiatedART");
        malepatientsBitween5And9YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween5And9YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsBitween5And9YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsBitween5And9YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween5And9YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween5And9YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsBitween5And9YearsInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween5And9YearsInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsBitween5And9YearsInitiatedARTIndicator",
                malepatientsBitween5And9YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B17", "Total number of male patients between 5 and 9 years who initiated ART", new Mapped(malepatientsBitween5And9YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B18 Total number of female patients between 5 and 9 years who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween5And9YearsInitiatedART = new CompositionCohortDefinition();
        femalepatientsBitween5And9YearsInitiatedART.setName("femalepatientsBitween5And9YearsInitiatedART");
        femalepatientsBitween5And9YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween5And9YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsBitween5And9YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsBitween5And9YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween5And9YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween5And9YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsBitween5And9YearsInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween5And9YearsInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween1And4YearsInitiatedARTIndicator",
                femalepatientsBitween5And9YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B18", "Total number of female patients between 5 and 9 years who initiated ART", new Mapped(femalepatientsBitween5And9YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B19 Total number of male patients between 10 and 14 years who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsBitween10And14YearsInitiatedART = new CompositionCohortDefinition();
        malepatientsBitween10And14YearsInitiatedART.setName("malepatientsBitween10And14YearsInitiatedART");
        malepatientsBitween10And14YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween10And14YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsBitween10And14YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsBitween10And14YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween10And14YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween10And14YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsBitween10And14YearsInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween10And14YearsInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsBitween10And14YearsInitiatedARTIndicator",
                malepatientsBitween10And14YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B19", "Total number of male patients between 10 and 14 years who initiated ART", new Mapped(malepatientsBitween10And14YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B20 Total number of female patients between 10 and 14 years who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween10And14YearsInitiatedART = new CompositionCohortDefinition();
        femalepatientsBitween10And14YearsInitiatedART.setName("femalepatientsBitween10And14YearsInitiatedART");
        femalepatientsBitween10And14YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween10And14YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsBitween10And14YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsBitween10And14YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween10And14YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween10And14YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsBitween10And14YearsInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween10And14YearsInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween10And14YearsInitiatedARTIndicator",
                femalepatientsBitween10And14YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B20", "Total number of female patients between 10 and 14 years who initiated ART", new Mapped(femalepatientsBitween10And14YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B21 Total number of male patients between 15 and 19 years who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsBitween15And19YearsInitiatedART = new CompositionCohortDefinition();
        malepatientsBitween15And19YearsInitiatedART.setName("malepatientsBitween15And19YearsInitiatedART");
        malepatientsBitween15And19YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween15And19YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsBitween15And19YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsBitween15And19YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween15And19YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween15And19YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsBitween15And19YearsInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween15And19YearsInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsBitween15And19YearsInitiatedARTIndicator",
                malepatientsBitween15And19YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B21", "Total number of male patients between 10 and 14 years who initiated ART", new Mapped(malepatientsBitween15And19YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B22 Total number of female patients between 15 and 19 years who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween15And19YearsInitiatedART = new CompositionCohortDefinition();
        femalepatientsBitween15And19YearsInitiatedART.setName("femalepatientsBitween15And19YearsInitiatedART");
        femalepatientsBitween15And19YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween15And19YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsBitween15And19YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsBitween15And19YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween15And19YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween15And19YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsBitween15And19YearsInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween15And19YearsInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween10And14YearsInitiatedARTIndicator",
                femalepatientsBitween15And19YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B22", "Total number of female patients between 15 and 19 years who initiated ART", new Mapped(femalepatientsBitween15And19YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

//=======================================
// Other age range
//======================================
        AgeCohortDefinition patientsBitween20And24Years = patientWithAgeBetween(20, 24);
        AgeCohortDefinition patientsBitween25And49Years = patientWithAgeBetween(25, 49);
        AgeCohortDefinition patientsWith50YearsAndAbove = patientWithAgeAbove(50);

        //============================================
        // B23 Total number of male patients between 20 and 24 years who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsBitween20And24YearsInitiatedART = new CompositionCohortDefinition();
        malepatientsBitween20And24YearsInitiatedART.setName("malepatientsBitween20And24YearsInitiatedART");
        malepatientsBitween20And24YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween20And24YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsBitween20And24YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsBitween20And24YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween20And24YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween20And24YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsBitween20And24YearsInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween20And24YearsInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsBitween20And24YearsInitiatedARTIndicator",
                malepatientsBitween20And24YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B23", "Total number of male patients between 20 and 24 years who initiated ART", new Mapped(malepatientsBitween20And24YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B24 Total number of female patients between 15 and 19 years who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween20And24YearsInitiatedART = new CompositionCohortDefinition();
        femalepatientsBitween20And24YearsInitiatedART.setName("femalepatientsBitween20And24YearsInitiatedART");
        femalepatientsBitween20And24YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween20And24YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsBitween20And24YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsBitween20And24YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween20And24YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween20And24YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsBitween20And24YearsInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween20And24YearsInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween20And24YearsInitiatedARTIndicator",
                femalepatientsBitween20And24YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B24", "Total number of female patients between 20 and 24 years who initiated ART", new Mapped(femalepatientsBitween20And24YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B25 Total number of male patients between 25 and 49 years who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsBitween25And49YearsInitiatedART = new CompositionCohortDefinition();
        malepatientsBitween25And49YearsInitiatedART.setName("malepatientsBitween25And49YearsInitiatedART");
        malepatientsBitween25And49YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsBitween25And49YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsBitween25And49YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsBitween25And49YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsBitween25And49YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsBitween25And49YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsBitween25And49YearsInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsBitween25And49YearsInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsBitween25And49YearsInitiatedARTIndicator",
                malepatientsBitween25And49YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B25", "Total number of male patients between 25 and 49 years who initiated ART", new Mapped(malepatientsBitween25And49YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B26 Total number of female patients between 25 and 49 years who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsBitween25And49YearsInitiatedART = new CompositionCohortDefinition();
        femalepatientsBitween25And49YearsInitiatedART.setName("femalepatientsBitween25And49YearsInitiatedART");
        femalepatientsBitween25And49YearsInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsBitween25And49YearsInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsBitween25And49YearsInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsBitween25And49YearsInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsBitween25And49YearsInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsBitween25And49YearsInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsBitween25And49YearsInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsBitween25And49YearsInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsBitween25And49YearsInitiatedARTIndicator",
                femalepatientsBitween25And49YearsInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B26", "Total number of female patients between 25 and 49 years who initiated ART", new Mapped(femalepatientsBitween25And49YearsInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //============================================
        // B27 Total number of male patients with 50 years and above who initiated ART
        //==========================================


        CompositionCohortDefinition malepatientsWith50YearsAndAboveInitiatedART = new CompositionCohortDefinition();
        malepatientsWith50YearsAndAboveInitiatedART.setName("malepatientsWith50YearsAndAboveInitiatedART");
        malepatientsWith50YearsAndAboveInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientsWith50YearsAndAboveInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        malepatientsWith50YearsAndAboveInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        malepatientsWith50YearsAndAboveInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientsWith50YearsAndAboveInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malepatientsWith50YearsAndAboveInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        malepatientsWith50YearsAndAboveInitiatedART.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientsWith50YearsAndAboveInitiatedARTIndicator = Indicators.newCohortIndicator("malepatientsWith50YearsAndAboveInitiatedARTIndicator",
                malepatientsWith50YearsAndAboveInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B27", "Total number of male patients with 50 years and above who initiated ART", new Mapped(malepatientsWith50YearsAndAboveInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==========================================
        // B28 Total number of female patients with 50 years and above who initiated ART
        //==========================================

        CompositionCohortDefinition femalepatientsWith50YearsAndAboveInitiatedART = new CompositionCohortDefinition();
        femalepatientsWith50YearsAndAboveInitiatedART.setName("femalepatientsWith50YearsAndAboveInitiatedART");
        femalepatientsWith50YearsAndAboveInitiatedART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientsWith50YearsAndAboveInitiatedART.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        femalepatientsWith50YearsAndAboveInitiatedART.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));
        femalepatientsWith50YearsAndAboveInitiatedART.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientsWith50YearsAndAboveInitiatedART.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalepatientsWith50YearsAndAboveInitiatedART.getSearches().put("3", new Mapped<CohortDefinition>(patientInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startedOnOrAfter},startedOnOrBefore=${startedOnOrBefore}")));
        femalepatientsWith50YearsAndAboveInitiatedART.setCompositionString("1 and 3 and (not 2)");

        CohortIndicator femalepatientsWith50YearsAndAboveInitiatedARTIndicator = Indicators.newCohortIndicator("femalepatientsWith50YearsAndAboveInitiatedARTIndicator",
                femalepatientsWith50YearsAndAboveInitiatedART, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B28", "Total number of female patients with 50 years and above who initiated ART", new Mapped(femalepatientsWith50YearsAndAboveInitiatedARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


//=====================================================
// B29 Number of male patients with less than 1 year currently on ARVs who have been initiated on TB treatment
//======================================================

        List<Concept> TBDrugsConceptAnswers = TBDrugsConcept.getSetMembers();

        StringBuilder answers = new StringBuilder();

        int i = 0;

        for (Concept concept : TBDrugsConceptAnswers) {

            if (i == 0) {
                answers.append(concept.getConceptId());
            } else {
                answers.append(",");
                answers.append(concept.getConceptId());

            }
            i++;

        }


        SqlCohortDefinition patientsInitiatedTBDrugs = new SqlCohortDefinition();
        patientsInitiatedTBDrugs.setQuery("select patient_id from orders where concept_id in (" + answers.toString() + ") and start_date>= :onOrAfter and start_date<= :onOrBefore and voided=0");
        patientsInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition malePatientBelowOneYearOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBelowOneYearOnARTInitiatedTBDrugs.setName("malePatientBelowOneYearOnARTInitiatedTBDrugs");
        malePatientBelowOneYearOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBelowOneYearOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBelowOneYearOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBelowOneYearOnARTInitiatedTBDrugsIndicator",
                malePatientBelowOneYearOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B29", "Number of male patients with less than 1 year currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBelowOneYearOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // B30 Number of female patients with less than 1 year currently on ARVs who have been initiated on TB treatment
        //======================================================


        CompositionCohortDefinition femalePatientBelowOneYearOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.setName("malePatientBelowOneYearOnARTInitiatedTBDrugs");
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBelowOneYearOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBelowOneYearOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBelowOneYearOnARTInitiatedTBDrugsIndicator",
                femalePatientBelowOneYearOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B30", "Number of female patients with less than 1 year currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBelowOneYearOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B31: Number of male patients between 1 and 4 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.setName("malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs");
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator",
                malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B31", "Number of female patients between 1 and 4 years currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B32: Number of female patients between 1 and 4 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.setName("femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs");
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator",
                femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B32", "Number of female patients between 1 and 4 years currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B33: Number of male patients between 5 and 9 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.setName("malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs");
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator",
                malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B33", "Number of female patients between 5 and 9 years currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B34: Number of female patients between 5 and 9 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.setName("femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs");
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugsIndicator",
                femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B34", "Number of female patients between 5 and 9 years currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B35: Number of male patients between 10 and 14 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientBetween10And14YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.setName("malePatientBetween10And14YearsOnARTInitiatedTBDrugs");
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween10And14YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBetween10And14YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBetweenOneAndFourYearsOnARTInitiatedTBDrugsIndicator",
                malePatientBetween10And14YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B35", "Number of female patients between 10 and 14 years currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBetween10And14YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B36: Number of female patients between 10 and 14 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientBetween10And14YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.setName("femalePatientBetweenFiveAndNineYearsOnARTInitiatedTBDrugs");
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween10And14YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBetween10And14YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBetween10And14YearsOnARTInitiatedTBDrugsIndicator",
                femalePatientBetween10And14YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B36", "Number of female patients between 10 and 14 years currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBetween10And14YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B37: Number of male patients between 15 and 19 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientBetween15And19YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.setName("malePatientBetween15And19YearsOnARTInitiatedTBDrugs");
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween15And19YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBetween15And19YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBetween15And19YearsOnARTInitiatedTBDrugsIndicator",
                malePatientBetween15And19YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B37", "Number of female patients between 15 and 19 years currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBetween15And19YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B38: Number of female patients between 15 and 19 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientBetween15And19YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.setName("femalePatientBetween15And19YearsOnARTInitiatedTBDrugs");
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween15And19YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBetween15And19YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBetween14And19YearsOnARTInitiatedTBDrugsIndicator",
                femalePatientBetween15And19YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B38", "Number of female patients between 15 and 19 years currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBetween15And19YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B39: Number of male patients between 20 and 24 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientBetween20And24YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.setName("malePatientBetween20And24YearsOnARTInitiatedTBDrugs");
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween20And24YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBetween20And24YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBetween20And24YearsOnARTInitiatedTBDrugsIndicator",
                malePatientBetween20And24YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B39", "Number of female patients between 20 and 24 years currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBetween20And24YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B40: Number of female patients between 20 and 24 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientBetween20And24YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.setName("femalePatientBetween20And24YearsOnARTInitiatedTBDrugs");
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween20And24YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBetween20And24YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBetween20And24YearsOnARTInitiatedTBDrugsIndicator",
                femalePatientBetween20And24YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B40", "Number of female patients between 20 and 24 years currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBetween20And24YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B41: Number of male patients between 25 and  49 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientBetween25And49YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.setName("malePatientBetween20And24YearsOnARTInitiatedTBDrugs");
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientBetween25And49YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientBetween25And49YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientBetween25And49YearsOnARTInitiatedTBDrugsIndicator",
                malePatientBetween25And49YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B41", "Number of female patients between 25 and 49 years currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientBetween25And49YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B42: Number of female patients between 25 and 49 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientBetween25And49YearsOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.setName("femalePatientBetween25And49YearsOnARTInitiatedTBDrugs");
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientBetween25And49YearsOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientBetween25And49YearsOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientBetween25And49YearsOnARTInitiatedTBDrugsIndicator",
                femalePatientBetween25And49YearsOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B42", "Number of female patients between 20 and 24 years currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientBetween25And49YearsOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B43: Number of male patients with 50 years and above currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.setName("malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs");
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator malePatientWith50YearsAndAboveOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("malePatientWith50YearsAndAboveOnARTInitiatedTBDrugsIndicator",
                malePatientWith50YearsAndAboveOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B43", "Number of male patients with 50 years and above currently on ARVs who have been initiated on TB treatment", new Mapped(malePatientWith50YearsAndAboveOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================================================
        // B44: Number of female patients between 25 and 49 years currently on ARVs who have been initiated on TB treatment
        //==============================================================================

        CompositionCohortDefinition femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs = new CompositionCohortDefinition();
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.setName("femalePatientBetween25And49YearsOnARTInitiatedTBDrugs");
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("3", new Mapped<CohortDefinition>(onART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.getSearches().put("4", new Mapped<CohortDefinition>(patientsInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugsIndicator = Indicators.newCohortIndicator("femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugsIndicator",
                femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B44", "Number of female patients with 50 years and above currently on ARVs who have been initiated on TB treatment", new Mapped(femalePatientWith50YearsAndAboveOnARTInitiatedTBDrugsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B45 Number of male pediatric ( < 15 years ) patients unstable on 1st line regimen
        //==================================================================================

        AgeCohortDefinition patientBelow15Years = patientWithAgeBelow(15);

        AgeCohortDefinition patientsWith15YearsAndAbove = patientWithAgeAbove(15);

        InStateCohortDefinition unstablePatients = patientInModel(unstable);

        InStateCohortDefinition stablePatients = patientInModel(stable);

        InStateCohortDefinition patientsOn1stLineRegimen = patientInRegimenStatus(firstLineRegiment);

        InStateCohortDefinition patientsOn2ndLineRegimen = patientInRegimenStatus(secondLineRegiment);

        InStateCohortDefinition patientsOn3rdLineRegimen = patientInRegimenStatus(thrirdLineRegiment);


        CompositionCohortDefinition maleUnstablePatientsBelow15YearsOn1stLineRegimen = new CompositionCohortDefinition();
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.setName("maleUnstablePatientsBelow15YearsOn1stLineRegimen");
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientBelow15Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsBelow15YearsOn1stLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleUnstablePatientsBelow15YearsOn1stLineRegimenIndicator = Indicators.newCohortIndicator("maleUnstablePatientsBelow15YearsOn1stLineRegimenIndicator",
                maleUnstablePatientsBelow15YearsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B45", "Number of male pediatric ( < 15 years ) patients unstable on 1st line regimen", new Mapped(maleUnstablePatientsBelow15YearsOn1stLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==================================================================================
        // B46 Number of female pediatric ( < 15 years ) patients unstable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition femaleUnstablePatientsBelow15YearsOn1stLineRegimen = new CompositionCohortDefinition();
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.setName("femaleUnstablePatientsBelow15YearsOn1stLineRegimen");
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientBelow15Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsBelow15YearsOn1stLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleUnstablePatientsBelow15YearsOn1stLineRegimenIndicator = Indicators.newCohortIndicator("femaleUnstablePatientsBelow15YearsOn1stLineRegimenIndicator",
                femaleUnstablePatientsBelow15YearsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B46", "Number of female pediatric ( < 15 years ) patients unstable on 1st line regimen", new Mapped(femaleUnstablePatientsBelow15YearsOn1stLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==================================================================================
        // B47 Number of male pediatric ( < 15 years ) patients unstable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition maleUnstablePatientsBelow15YearsOn2ndLineRegimen = new CompositionCohortDefinition();
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.setName("maleUnstablePatientsBelow15YearsOn2ndLineRegimen");
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientBelow15Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsBelow15YearsOn2ndLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleUnstablePatientsBelow15YearsOn2ndLineRegimenIndicator = Indicators.newCohortIndicator("maleUnstablePatientsBelow15YearsOn2ndLineRegimenIndicator",
                maleUnstablePatientsBelow15YearsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B47", "Number of male pediatric ( < 15 years ) patients unstable on 2nd line regimen", new Mapped(maleUnstablePatientsBelow15YearsOn2ndLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B48 Number of female pediatric ( < 15 years ) patients unstable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition femaleUnstablePatientsBelow15YearsOn2ndLineRegimen = new CompositionCohortDefinition();
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.setName("femaleUnstablePatientsBelow15YearsOn1stLineRegimen");
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientBelow15Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsBelow15YearsOn2ndLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleUnstablePatientsBelow15YearsOn2ndLineRegimenIndicator = Indicators.newCohortIndicator("femaleUnstablePatientsBelow15YearsOn2ndLineRegimenIndicator",
                femaleUnstablePatientsBelow15YearsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B48", "Number of female pediatric ( < 15 years ) patients unstable on 2nd line regimen", new Mapped(femaleUnstablePatientsBelow15YearsOn2ndLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B49 Number of male pediatric ( < 15 years ) patients unstable on 3rd line regimen
        //==================================================================================

        CompositionCohortDefinition maleUnstablePatientsBelow15YearsOn3rdLineRegimen = new CompositionCohortDefinition();
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.setName("maleUnstablePatientsBelow15YearsOn3rdLineRegimen");
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientBelow15Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsBelow15YearsOn3rdLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleUnstablePatientsBelow15YearsOn3rdLineRegimenIndicator = Indicators.newCohortIndicator("maleUnstablePatientsBelow15YearsOn3rdLineRegimenIndicator",
                maleUnstablePatientsBelow15YearsOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B49", "Number of male pediatric ( < 15 years ) patients unstable on 3rd line regimen", new Mapped(maleUnstablePatientsBelow15YearsOn3rdLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B50 Number of female pediatric ( < 15 years ) patients unstable on 3rd line regimen
        //==================================================================================

        CompositionCohortDefinition femaleUnstablePatientsBelow15YearsOn3rdLineRegimen = new CompositionCohortDefinition();
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.setName("femaleUnstablePatientsBelow15YearsOn3rdLineRegimen");
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientBelow15Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsBelow15YearsOn3rdLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleUnstablePatientsBelow15YearsOn3rdLineRegimenIndicator = Indicators.newCohortIndicator("femaleUnstablePatientsBelow15YearsOn3rdLineRegimenIndicator",
                femaleUnstablePatientsBelow15YearsOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B50", "Number of female pediatric ( < 15 years ) patients unstable on 3rd line regimen", new Mapped(femaleUnstablePatientsBelow15YearsOn3rdLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B51 Number of male adult ( >= 15 years ) patients stable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition maleStablePatientsWith15YearsAndAboveOn1stLineRegimen = new CompositionCohortDefinition();
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.setName("maleStablePatientsWith15YearsAndAboveOn1stLineRegimen");
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(stablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleStablePatientsWith15YearsAndAboveOn1stLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator = Indicators.newCohortIndicator("maleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator",
                maleStablePatientsWith15YearsAndAboveOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B51", " Number of male adult ( >= 15 years ) patients stable on 1st line regimen", new Mapped(maleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B52 Number of female adult ( >= 15 years ) patients stable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen = new CompositionCohortDefinition();
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.setName("femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen");
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(stablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator = Indicators.newCohortIndicator("femaleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator",
                femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B52", " Number of male adult ( >= 15 years ) patients stable on 1st line regimen", new Mapped(femaleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B53 Number of male adult ( >= 15 years ) patients unstable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen = new CompositionCohortDefinition();
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.setName("maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen");
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator = Indicators.newCohortIndicator("maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator",
                maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B53", "Number of male adult ( >= 15 years ) patients stable on 1st line regimen", new Mapped(maleStablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B54 Number of female adult ( >= 15 years ) patients unstable on 1st line regimen
        //==================================================================================

        CompositionCohortDefinition femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen = new CompositionCohortDefinition();
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.setName("femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen");
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator = Indicators.newCohortIndicator("femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator",
                femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B54", " Number of male adult ( >= 15 years ) patients stable on 1st line regimen", new Mapped(femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B55 Number of male adult ( >= 15 years ) patients stable on 2nd line regimen
        //==================================================================================

        CompositionCohortDefinition maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen = new CompositionCohortDefinition();
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.setName("maleStablePatientsWith15YearsAndAboveOn1stLineRegimen");
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(stablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleStablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator = Indicators.newCohortIndicator("maleStablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator",
                maleStablePatientsWith15YearsAndAboveOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B55", " Number of male adult ( >= 15 years ) patients stable on 2nd line regimen", new Mapped(maleStablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B56 Number of female adult ( >= 15 years ) patients stable on 2nd line regimen
        //==================================================================================

        CompositionCohortDefinition femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen = new CompositionCohortDefinition();
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.setName("femaleStablePatientsWith15YearsAndAboveOn1stLineRegimen");
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(stablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator = Indicators.newCohortIndicator("femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator",
                femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B56", " Number of male adult ( >= 15 years ) patients stable on 2nd line regimen", new Mapped(femaleStablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B57 Number of male adult ( >= 15 years ) patients unstable on 2nd line regimen
        //==================================================================================

        CompositionCohortDefinition maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen = new CompositionCohortDefinition();
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.setName("maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen");
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator = Indicators.newCohortIndicator("maleUnstablePatientsWith15YearsAndAboveOn1stLineRegimenIndicator",
                maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B57", "Number of male adult ( >= 15 years ) patients stable on 2nd line regimen", new Mapped(maleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B58 Number of female adult ( >= 15 years ) patients unstable on 2nd line regimen
        //==================================================================================

        CompositionCohortDefinition femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen = new CompositionCohortDefinition();
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.setName("femaleUnstablePatientsWith15YearsAndAboveOn1stLineRegimen");
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator = Indicators.newCohortIndicator("femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator",
                femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B58", " Number of male adult ( >= 15 years ) patients stable on 2nd line regimen", new Mapped(femaleUnstablePatientsWith15YearsAndAboveOn2ndLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B59 Number of male adult ( >= 15 years ) patients unstable on 3rd line regimen
        //==================================================================================

        CompositionCohortDefinition maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen = new CompositionCohortDefinition();
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.setName("maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen");
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimenIndicator = Indicators.newCohortIndicator("maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimenIndicator",
                maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B59", "Number of male adult ( >= 15 years ) patients stable on 3rd line regimen", new Mapped(maleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==================================================================================
        // B60 Number of female adult ( >= 15 years ) patients unstable on 3rd line regimen
        //==================================================================================

        CompositionCohortDefinition femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen = new CompositionCohortDefinition();
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.setName("femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen");
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith15YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("3", new Mapped<CohortDefinition>(unstablePatients, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.getSearches().put("4", new Mapped<CohortDefinition>(patientsOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen.setCompositionString("1 and 3 and 4 and (not 2)");

        CohortIndicator femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimenIndicator = Indicators.newCohortIndicator("femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimenIndicator",
                femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("B60", " Number of male adult ( >= 15 years ) patients stable on 3rd line regimen", new Mapped(femaleUnstablePatientsWith15YearsAndAboveOn3rdLineRegimenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

//==========================================================
// B61 Total number of patients under ART at the end of last month
//==========================================================
        CohortIndicator patientOnARTIndicator = Indicators.newCohortIndicator("patientOnARTIndicator",
                onART, ParameterizableUtil.createParameterMappings("onOrAfter=${endDate},onOrBefore=${endDate}"));


        dsd.addColumn("B61", " Total number of patients under ART at the end of last month", new Mapped(patientOnARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //============================================================
        // B62 Total number of patients under ART who died this month
        //===================================================================


        PatientStateCohortDefinition diedPatientsInThisMonth = new PatientStateCohortDefinition();
        diedPatientsInThisMonth.setName("diedPatientsInThisMonth");
        diedPatientsInThisMonth.addState(hivProgram.getWorkflowByName("TREATMENT STATUS").getState(patientDied));
        diedPatientsInThisMonth.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        diedPatientsInThisMonth.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));

        CohortIndicator diedPatientsInThisMonthIndicator = Indicators.newCohortIndicator("diedPatientsInThisMonthIndicator",
                diedPatientsInThisMonth, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate}"));


        dsd.addColumn("B62", "Total number of patients under ART who died this month", new Mapped(diedPatientsInThisMonthIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=========================================
        // B63 Total number of patients under ART lost to follow up this month
        //=========================================


        CodedObsCohortDefinition exitedPatientsWithDefaultedReason = new CodedObsCohortDefinition();
        exitedPatientsWithDefaultedReason.setName("exitedPatientsWithDefaultedReason");
        exitedPatientsWithDefaultedReason.setTimeModifier(TimeModifier.LAST);
        exitedPatientsWithDefaultedReason.setOperator(SetComparator.IN);
        exitedPatientsWithDefaultedReason.setQuestion(reasonForExitingFromCare);
        exitedPatientsWithDefaultedReason.addValue(patientDefaulted);
        exitedPatientsWithDefaultedReason.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        exitedPatientsWithDefaultedReason.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator lostpatientsInThisMonthIndicator = Indicators.newCohortIndicator("lostpatientsInThisMonthIndicator",
                exitedPatientsWithDefaultedReason, ParameterizableUtil.createParameterMappings("onOrAfter=${endDate},onOrBefore=${endDate}"));


        dsd.addColumn("B63", "Total number of patients under ART at the end of last month", new Mapped(lostpatientsInThisMonthIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

//============================================================================
// B64 Total number of patients under ART retraced this month
//============================================================================


        InStateCohortDefinition defaultePatientState = new InStateCohortDefinition();
        defaultePatientState.addState(hivProgram.getWorkflowByName("TREATMENT STATUS").getState(patientDefaulted));
        defaultePatientState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        defaultePatientState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        EncounterCohortDefinition patientsWithHIVEncounter = new EncounterCohortDefinition();
        patientsWithHIVEncounter.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithHIVEncounter.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientsWithHIVEncounter.addEncounterType(HIVVisitEncType);

        CompositionCohortDefinition retracedPatient = new CompositionCohortDefinition();
        retracedPatient.setName("retracedPatient");
        retracedPatient.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        retracedPatient.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        retracedPatient.getSearches().put("1", new Mapped<CohortDefinition>(defaultePatientState, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        retracedPatient.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithHIVEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        retracedPatient.setCompositionString("1 and 2");

        CohortIndicator retracedPatientIndicator = Indicators.newCohortIndicator("retracedPatientIndicator",
                retracedPatient, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));


        dsd.addColumn("B64", "Total number of patients under ART retraced this month", new Mapped(retracedPatientIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //======================================================
        // B65 Total number of patients under ART transferred out this month
        //======================================================


        PatientStateCohortDefinition transferOutPatientsInThisMonth = new PatientStateCohortDefinition();
        transferOutPatientsInThisMonth.setName("transferOutPatientsInThisMonth");
        transferOutPatientsInThisMonth.addState(hivProgram.getWorkflowByName("TREATMENT STATUS").getState(transferedOut));
        transferOutPatientsInThisMonth.addParameter(new Parameter("startedOnOrAfter", "startedOnOrAfter", Date.class));
        transferOutPatientsInThisMonth.addParameter(new Parameter("startedOnOrBefore", "startedOnOrBefore", Date.class));

        CohortIndicator transferOutPatientsInThisMonthIndicator = Indicators.newCohortIndicator("transferOutPatientsInThisMonthIndicator",
                transferOutPatientsInThisMonth, ParameterizableUtil.createParameterMappings("startedOnOrAfter=${startDate},startedOnOrBefore=${endDate}"));


        dsd.addColumn("B65", "Total number of patients under ART transferred out this month", new Mapped(transferOutPatientsInThisMonthIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // B66 Total number of patients under ART transferred in this month
        //===============================================
        CohortIndicator patientsWithTransferInEncounterIndicator = Indicators.newCohortIndicator("patientsWithTransferInEncounterIndicator",
                patientsWithTransferInEncounter, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));


        dsd.addColumn("B66", "Total number of patients under ART transferred in this month ", new Mapped(patientsWithTransferInEncounterIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // J. STI Data Elements     Sexually Transmitted Infections
        //===============================================
        //===============================================
        // J01 Clients who received counseling and screening for STIs
        //===============================================

        SqlCohortDefinition patientWhoReceivedCounselingAndScreeningForSTIs = new SqlCohortDefinition();
        patientWhoReceivedCounselingAndScreeningForSTIs.setName("patientWhoReceivedCounselingAndScreeningForSTIs");
        patientWhoReceivedCounselingAndScreeningForSTIs.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STITest.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWhoReceivedCounselingAndScreeningForSTIs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedCounselingAndScreeningForSTIs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesWithCounselingAndScreeningForSTIs = new CompositionCohortDefinition();
        casesWithCounselingAndScreeningForSTIs.setName("casesWithCounselingAndScreeningForSTIs");
        casesWithCounselingAndScreeningForSTIs.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesWithCounselingAndScreeningForSTIs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesWithCounselingAndScreeningForSTIs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesWithCounselingAndScreeningForSTIs.getSearches().put("1", new Mapped<CohortDefinition>(patientWhoReceivedCounselingAndScreeningForSTIs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesWithCounselingAndScreeningForSTIs.setCompositionString("1");
        CohortIndicator casesWithCounselingAndScreeningForSTIsIndicator = Indicators.newCohortIndicator("casesWithCounselingAndScreeningForSTIsIndicator",
                casesWithCounselingAndScreeningForSTIs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J03", "Clients who received counseling and screening for STIs", new Mapped(casesWithCounselingAndScreeningForSTIsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J02 STI cases diagnosed and treated
        //===============================================


        //===============================================
        // J03 Cases of urethral discharge in men
        //===============================================

        SqlCohortDefinition patientWithUrethralDischargeInMen = new SqlCohortDefinition();
        patientWithUrethralDischargeInMen.setName("patientWithUrethralDischargeInMen");
        patientWithUrethralDischargeInMen.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + DischargeUrethralMale.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithUrethralDischargeInMen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithUrethralDischargeInMen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfUrethralDischargeInMen = new CompositionCohortDefinition();
        casesOfUrethralDischargeInMen.setName("casesOfUrethralDischargeInMen");
        casesOfUrethralDischargeInMen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfUrethralDischargeInMen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfUrethralDischargeInMen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfUrethralDischargeInMen.getSearches().put("1", new Mapped<CohortDefinition>(patientWithUrethralDischargeInMen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfUrethralDischargeInMen.setCompositionString("1");
        CohortIndicator casesOfUrethralDischargeInMenIndicator = Indicators.newCohortIndicator("casesOfUrethralDischargeInMenIndicator",
                casesOfUrethralDischargeInMen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J03", "Cases of urethral discharge in men", new Mapped(casesOfUrethralDischargeInMenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J04 Cases of vaginal discharge
        //===============================================

        SqlCohortDefinition patientWithVaginalDischarge = new SqlCohortDefinition();
        patientWithVaginalDischarge.setName("patientWithVaginalDischarge");
        patientWithVaginalDischarge.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + DischargeVaginal.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithVaginalDischarge.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithVaginalDischarge.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfVaginalDischarge = new CompositionCohortDefinition();
        casesOfVaginalDischarge.setName("casesOfVaginalDischarge");
        casesOfVaginalDischarge.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfVaginalDischarge.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfVaginalDischarge.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfVaginalDischarge.getSearches().put("1", new Mapped<CohortDefinition>(patientWithVaginalDischarge, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfVaginalDischarge.setCompositionString("1");
        CohortIndicator casesOfVaginalDischargeIndicator = Indicators.newCohortIndicator("casesOfVaginalDischargeIndicator",
                casesOfVaginalDischarge, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J04", "Cases of vaginal discharge", new Mapped(casesOfVaginalDischargeIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // J05 Cases of genital ulceration
        //===============================================

        SqlCohortDefinition patientWithGenitalUlceration = new SqlCohortDefinition();
        patientWithGenitalUlceration.setName("patientWithGenitalUlceration");
        patientWithGenitalUlceration.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + GENITALULCERSCHANCROID.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithGenitalUlceration.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithGenitalUlceration.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfGenitalUlceration = new CompositionCohortDefinition();
        casesOfGenitalUlceration.setName("casesOfGenitalUlceration");
        casesOfGenitalUlceration.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfGenitalUlceration.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfGenitalUlceration.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfGenitalUlceration.getSearches().put("1", new Mapped<CohortDefinition>(patientWithGenitalUlceration, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfGenitalUlceration.setCompositionString("1");
        CohortIndicator ccasesOfGenitalUlcerationIndicator = Indicators.newCohortIndicator("ccasesOfGenitalUlcerationIndicator",
                casesOfGenitalUlceration, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J05", "Cases of genital ulceration", new Mapped(ccasesOfGenitalUlcerationIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J06 Cases of inguinal bubo
        //===============================================

        SqlCohortDefinition patientWithInguinalBubo = new SqlCohortDefinition();
        patientWithInguinalBubo.setName("patientWithInguinalBubo");
        patientWithInguinalBubo.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + INGUINALbubo.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithInguinalBubo.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithInguinalBubo.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfInguinalBubo = new CompositionCohortDefinition();
        casesOfInguinalBubo.setName("casesOfInguinalBubo");
        casesOfInguinalBubo.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfInguinalBubo.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfInguinalBubo.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfInguinalBubo.getSearches().put("1", new Mapped<CohortDefinition>(patientWithInguinalBubo, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfInguinalBubo.setCompositionString("1");
        CohortIndicator casesOfInguinalBuboIndicator = Indicators.newCohortIndicator("casesOfInguinalBuboIndicator",
                casesOfInguinalBubo, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J06", "Cases of inguinal bubo", new Mapped(casesOfInguinalBuboIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J07 Cases of painful swelling of the epididymis and or testis
        //===============================================
        SqlCohortDefinition patientWithPainfulSwellingOfEpididymisOrTestis = new SqlCohortDefinition();
        patientWithPainfulSwellingOfEpididymisOrTestis.setName("patientWithPainfulSwellingOfEpididymisOrTestis");
        patientWithPainfulSwellingOfEpididymisOrTestis.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + PAINFULSWELLINGOFEPIDIDYMIS.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithPainfulSwellingOfEpididymisOrTestis.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPainfulSwellingOfEpididymisOrTestis.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfPainfulSwellingOfEpididymisOrTestis = new CompositionCohortDefinition();
        casesOfPainfulSwellingOfEpididymisOrTestis.setName("casesOfPainfulSwellingOfEpididymisOrTestis");
        casesOfPainfulSwellingOfEpididymisOrTestis.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfPainfulSwellingOfEpididymisOrTestis.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfPainfulSwellingOfEpididymisOrTestis.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfPainfulSwellingOfEpididymisOrTestis.getSearches().put("1", new Mapped<CohortDefinition>(patientWithPainfulSwellingOfEpididymisOrTestis, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfPainfulSwellingOfEpididymisOrTestis.setCompositionString("1");
        CohortIndicator casesOfPainfulSwellingOfEpididymisOrTestisIndicator = Indicators.newCohortIndicator("casesOfPainfulSwellingOfEpididymisOrTestisIndicator",
                casesOfPainfulSwellingOfEpididymisOrTestis, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J07", "Cases of painful swelling of the epididymis and or testis", new Mapped(casesOfPainfulSwellingOfEpididymisOrTestisIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J08 Cases of pelvic pain in women or Pelvic Inflammatory Syndrome
        //===============================================
        SqlCohortDefinition patientWithPelvicPainInWomen = new SqlCohortDefinition();
        patientWithPelvicPainInWomen.setName("patientWithPelvicPainInWomen");
        patientWithPelvicPainInWomen.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + PELVICINFLAMMATORYDISEASE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithPelvicPainInWomen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPelvicPainInWomen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfPelvicPainInWomen = new CompositionCohortDefinition();
        casesOfPelvicPainInWomen.setName("casesOfPelvicPainInWomen");
        casesOfPelvicPainInWomen.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfPelvicPainInWomen.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfPelvicPainInWomen.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfPelvicPainInWomen.getSearches().put("1", new Mapped<CohortDefinition>(patientWithPelvicPainInWomen, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfPelvicPainInWomen.setCompositionString("1");
        CohortIndicator casesOfPelvicPainInWomenIndicator = Indicators.newCohortIndicator("casesOfPelvicPainInWomenIndicator",
                casesOfPelvicPainInWomen, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J08", "Cases of pelvic pain in women or Pelvic Inflammatory Syndrome", new Mapped(casesOfPelvicPainInWomenIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J09 Cases of venereal vegetation
        //===============================================

        SqlCohortDefinition patientWithVenerealVegetation = new SqlCohortDefinition();
        patientWithVenerealVegetation.setName("patientWithPainfulSwellingOfEpididymisOrTestis");
        patientWithVenerealVegetation.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + ENEREALVEGETATION.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithVenerealVegetation.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithVenerealVegetation.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfVenerealVegetation = new CompositionCohortDefinition();
        casesOfVenerealVegetation.setName("casesOfVenerealVegetation");
        casesOfVenerealVegetation.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfVenerealVegetation.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfVenerealVegetation.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfVenerealVegetation.getSearches().put("1", new Mapped<CohortDefinition>(patientWithVenerealVegetation, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfVenerealVegetation.setCompositionString("1");
        CohortIndicator casesOfVenerealVegetationIndicator = Indicators.newCohortIndicator("casesOfVenerealVegetationIndicator",
                casesOfVenerealVegetation, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J09", "Cases of venereal vegetation", new Mapped(casesOfVenerealVegetationIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J10 Cases of purulent conjunctivitis of the newborn
        //===============================================

        SqlCohortDefinition patientWithPurulentConjunctivitisNewborn = new SqlCohortDefinition();
        patientWithPurulentConjunctivitisNewborn.setName("patientWithPurulentConjunctivitisNewborn");
        patientWithPurulentConjunctivitisNewborn.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + STIDIAGNOSIS.getConceptId() + " and o.value_coded=" + PURULENTCONJUCTIVITISOFNEWBORN.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithPurulentConjunctivitisNewborn.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPurulentConjunctivitisNewborn.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfPurulentConjunctivitisNewborn = new CompositionCohortDefinition();
        casesOfPurulentConjunctivitisNewborn.setName("casesOfPurulentConjunctivitisNewborn");
        casesOfPurulentConjunctivitisNewborn.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfPurulentConjunctivitisNewborn.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfPurulentConjunctivitisNewborn.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfPurulentConjunctivitisNewborn.getSearches().put("1", new Mapped<CohortDefinition>(patientWithPurulentConjunctivitisNewborn, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfPurulentConjunctivitisNewborn.setCompositionString("1");
        CohortIndicator casesOfPurulentConjunctivitisNewbornIndicator = Indicators.newCohortIndicator("casesOfPurulentConjunctivitisNewbornIndicator",
                casesOfPurulentConjunctivitisNewborn, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J10", "Cases of purulent conjunctivitis of the newborn", new Mapped(casesOfPurulentConjunctivitisNewbornIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // J11 Cases of syphilis tests turned positive
        //===============================================

        SqlCohortDefinition patientWithSyphilisTestsTurnedPositive = new SqlCohortDefinition();
        patientWithSyphilisTestsTurnedPositive.setName("patientWithSyphilisTestsTurnedPositive");
        patientWithSyphilisTestsTurnedPositive.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + SYPHILIS.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithSyphilisTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithSyphilisTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfSyphilisTestsTurnedPositive = new CompositionCohortDefinition();
        casesOfSyphilisTestsTurnedPositive.setName("casesOfSyphilisTestsTurnedPositive");
        casesOfSyphilisTestsTurnedPositive.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfSyphilisTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfSyphilisTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfSyphilisTestsTurnedPositive.getSearches().put("1", new Mapped<CohortDefinition>(patientWithSyphilisTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfSyphilisTestsTurnedPositive.setCompositionString("1");
        CohortIndicator casesOfSyphilisTestsTurnedPositiveIndicator = Indicators.newCohortIndicator("casesOfSyphilisTestsTurnedPositiveIndicator",
                casesOfSyphilisTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J11", "Cases of syphilis tests turned positive", new Mapped(casesOfSyphilisTestsTurnedPositiveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J12 Cases of herpes simplex virus tests turned positive
        //===============================================
        SqlCohortDefinition patientWithCasesHerpesSimplexVirusTestsTurnedPositive = new SqlCohortDefinition();
        patientWithCasesHerpesSimplexVirusTestsTurnedPositive.setName("patientWithCasesHerpesSimplexVirusTestsTurnedPositive");
        patientWithCasesHerpesSimplexVirusTestsTurnedPositive.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HERPESSIMPLEXGENITALINFECTION.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithCasesHerpesSimplexVirusTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithCasesHerpesSimplexVirusTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfHerpesSimplexVirusTestsTurnedPositive = new CompositionCohortDefinition();
        casesOfHerpesSimplexVirusTestsTurnedPositive.setName("casesOfHerpesSimplexVirusTestsTurnedPositive");
        casesOfHerpesSimplexVirusTestsTurnedPositive.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfHerpesSimplexVirusTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfHerpesSimplexVirusTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfHerpesSimplexVirusTestsTurnedPositive.getSearches().put("1", new Mapped<CohortDefinition>(patientWithCasesHerpesSimplexVirusTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfHerpesSimplexVirusTestsTurnedPositive.setCompositionString("1");
        CohortIndicator casesOfHerpesSimplexVirusTestsTurnedPositiveIndicator = Indicators.newCohortIndicator("casesOfHerpesSimplexVirusTestsTurnedPositiveIndicator",
                casesOfHerpesSimplexVirusTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J12", "Cases of herpes simplex virus tests turned positive", new Mapped(casesOfHerpesSimplexVirusTestsTurnedPositiveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J13 Cases of chlamydia tests turned positive
        //===============================================

        SqlCohortDefinition patientWithCasesChlamydiaTestsTurnedPositive = new SqlCohortDefinition();
        patientWithCasesChlamydiaTestsTurnedPositive.setName("patientWithCasesChlamydiaTestsTurnedPositive");
        patientWithCasesChlamydiaTestsTurnedPositive.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + CLAMYDIA.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithCasesChlamydiaTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithCasesChlamydiaTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfChlamydiaTestsTurnedPositive = new CompositionCohortDefinition();
        casesOfChlamydiaTestsTurnedPositive.setName("casesOfChlamydiaTestsTurnedPositive");
        casesOfChlamydiaTestsTurnedPositive.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfChlamydiaTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfChlamydiaTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfChlamydiaTestsTurnedPositive.getSearches().put("1", new Mapped<CohortDefinition>(patientWithCasesChlamydiaTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfChlamydiaTestsTurnedPositive.setCompositionString("1");
        CohortIndicator casesOfChlamydiaTestsTurnedPositiveIndicator = Indicators.newCohortIndicator("casesOfSyphilisTestsTurnedPositiveIndicator",
                casesOfSyphilisTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J13", "Cases of chlamydia tests turned positive", new Mapped(casesOfChlamydiaTestsTurnedPositiveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J14 Cases of gonococcal tests turned positive
        //===============================================
        SqlCohortDefinition patientWithCasesGonococcalTestsTurnedPositive = new SqlCohortDefinition();
        patientWithCasesGonococcalTestsTurnedPositive.setName("patientWithCasesGonococcalTestsTurnedPositive");
        patientWithCasesGonococcalTestsTurnedPositive.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + AdultHIVFlowsheetNewVisit.getFormId() + "," + PediHIVFlowsheetNewVisit.getFormId() + ") and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + GONORRHEA.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithCasesGonococcalTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithCasesGonococcalTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition casesOfGonococcalTestsTurnedPositive = new CompositionCohortDefinition();
        casesOfGonococcalTestsTurnedPositive.setName("casesOfChlamydiaTestsTurnedPositive");
        casesOfGonococcalTestsTurnedPositive.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        casesOfGonococcalTestsTurnedPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        casesOfGonococcalTestsTurnedPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        casesOfGonococcalTestsTurnedPositive.getSearches().put("1", new Mapped<CohortDefinition>(patientWithCasesGonococcalTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        casesOfGonococcalTestsTurnedPositive.setCompositionString("1");
        CohortIndicator casesOfGonococcalTestsTurnedPositiveIndicator = Indicators.newCohortIndicator("casesOfGonococcalTestsTurnedPositiveIndicator",
                casesOfGonococcalTestsTurnedPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("J14", "Cases of gonococcal tests turned positive", new Mapped(casesOfGonococcalTestsTurnedPositiveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // J15 Partners treated for STIs
        //===============================================
        //=============================================
        //   D. Antenatal Data Elements
        //=============================================

        //===============================================
        // D01 Women presenting for first antenatal care consultation
        //===============================================
        SqlCohortDefinition patientWithFirstANCConsultation = new SqlCohortDefinition();
        patientWithFirstANCConsultation.setName("patientWithFirstANCConsultation");
        patientWithFirstANCConsultation.setQuery("select grouped.patient_id from (select * from (select patient_id,encounter_datetime from encounter where encounter_type=33 and voided=0 order by encounter_datetime asc) ordered  group by ordered.patient_id) grouped where grouped.encounter_datetime>= :onOrAfter and grouped.encounter_datetime<= :onOrBefore");
        patientWithFirstANCConsultation.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithFirstANCConsultation.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientWithFirstANCConsultationIndicator = Indicators.newCohortIndicator("patientWithFirstANCConsultationIndicator",
                patientWithFirstANCConsultation, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D01", "Women presenting for first antenatal care consultation", new Mapped(patientWithFirstANCConsultationIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // D02 Number of women with unknown HIV status presenting for first antenatal care consultation
        //===============================================
        SqlCohortDefinition patientWithANCConsultation = new SqlCohortDefinition();
        patientWithANCConsultation.setQuery("select grouped.patient_id from (select ordered.patient_id,ordered.encounter_datetime from (select * from encounter e WHERE form_id =72 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped where grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        //patientWithANCConsultation.setQuery("select grouped.patient_id from (select ordered.patient_id,ordered.encounter_datetime from (select * from encounter e WHERE form_id =72 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped where grouped.encounter_datetime >= '2018-02-01' and grouped.encounter_datetime <= '2018-02-09'");
        patientWithANCConsultation.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultation.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        SqlCohortDefinition patientWithANCConsultationWithHIVStatus = new SqlCohortDefinition();
        patientWithANCConsultationWithHIVStatus.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =72 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HIVTESTING.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        //patientWithANCConsultationWithHIVStatus.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =72 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=1621 and grouped.encounter_datetime >= '2018-02-01' and grouped.encounter_datetime <= '2018-02-09'");
        patientWithANCConsultationWithHIVStatus.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultationWithHIVStatus.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition patientWithANCConsultationWithUnknownHIVStatus = new CompositionCohortDefinition();
        patientWithANCConsultationWithUnknownHIVStatus.setName("patientWithANCConsultationWithUnknownHIVStatus");
        //patientWithANCConsultationWithUnknownHIVStatus.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWithANCConsultationWithUnknownHIVStatus.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultationWithUnknownHIVStatus.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        //patientWithANCConsultationWithUnknownHIVStatus.getSearches().put("1",new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        //patientWithANCConsultationWithUnknownHIVStatus.getSearches().put("2",new Mapped<CohortDefinition>(males, null));
        patientWithANCConsultationWithUnknownHIVStatus.getSearches().put("1", new Mapped<CohortDefinition>(patientWithANCConsultation, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithANCConsultationWithUnknownHIVStatus.getSearches().put("2", new Mapped<CohortDefinition>(patientWithANCConsultationWithHIVStatus, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithANCConsultationWithUnknownHIVStatus.setCompositionString("1 and (not 2)");

        CohortIndicator patientWithANCConsultationWithUnknownHIVStatusIndicator = Indicators.newCohortIndicator("patientWithANCConsultationWithUnknownHIVStatusIndicator",
                patientWithANCConsultationWithUnknownHIVStatus, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D02", " Number of women with unknown HIV status presenting for first antenatal care consultation", new Mapped(patientWithANCConsultationWithUnknownHIVStatusIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D03 Number of known HIV positive pregnant women presenting for first antenatal care consultation
        //===============================================

        SqlCohortDefinition patientWithHIVPositiveFirstANCConsultation = new SqlCohortDefinition();
        patientWithHIVPositiveFirstANCConsultation.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =74 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + RESULTOFHIVTEST.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        //patientWithHIVPositiveFirstANCConsultation.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =74 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=2169 and o.value_coded=703 and grouped.encounter_datetime >= '2018-02-01' and grouped.encounter_datetime <= '2018-02-09'");
        patientWithHIVPositiveFirstANCConsultation.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithHIVPositiveFirstANCConsultation.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientWithHIVPositiveFirstANCConsultationIndicator = Indicators.newCohortIndicator("patientWithHIVPositiveFirstANCConsultationIndicator",
                patientWithHIVPositiveFirstANCConsultation, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D03", "Number of known HIV positive pregnant women presenting for first antenatal care consultation", new Mapped(patientWithHIVPositiveFirstANCConsultationIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D04 Known HIV positive pregnant women on ART presenting for first antenatal care Consultation
        //===============================================

        InStateCohortDefinition PMTCTonART = new InStateCohortDefinition();
        PMTCTonART.addState(pmtctProgram.getWorkflowByName("TREATMENT STATUS").getState(onAntiretroviral));
        PMTCTonART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PMTCTonART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation = new CompositionCohortDefinition();
        PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation.setName("PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation");
        PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation.getSearches().put("1", new Mapped<CohortDefinition>(patientWithHIVPositiveFirstANCConsultation, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation.getSearches().put("2", new Mapped<CohortDefinition>(PMTCTonART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation.setCompositionString("1 and 2");

        CohortIndicator PMTCTPatientOnARTWithHIVPositiveFirstANCConsultationIndicator = Indicators.newCohortIndicator("PMTCTPatientOnARTWithHIVPositiveFirstANCConsultationIndicator",
                PMTCTPatientOnARTWithHIVPositiveFirstANCConsultation, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D04", "Known HIV positive pregnant women on ART presenting for first antenatal care Consultation", new Mapped(PMTCTPatientOnARTWithHIVPositiveFirstANCConsultationIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D05 Number of pregnant women with unknown HIV status tested for HIV
        //===============================================

        SqlCohortDefinition patientWithANCConsultationTestedForHIV = new SqlCohortDefinition();
        patientWithANCConsultationTestedForHIV.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + ANCPastMedicalHistory.getFormId() + "," + ANCInvestigationForm.getFormId() + ") and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HIVTESTING.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWithANCConsultationTestedForHIV.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultationTestedForHIV.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWithANCConsultationTestedForHIVWithUnknownHIVStatus = new CompositionCohortDefinition();
        patientWithANCConsultationTestedForHIVWithUnknownHIVStatus.setName("patientWithANCConsultationTestedForHIVWithUnknownHIVStatus");
        //patientWithANCConsultationWithUnknownHIVStatus.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWithANCConsultationTestedForHIVWithUnknownHIVStatus.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultationTestedForHIVWithUnknownHIVStatus.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        //patientWithANCConsultationWithUnknownHIVStatus.getSearches().put("1",new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        //patientWithANCConsultationWithUnknownHIVStatus.getSearches().put("2",new Mapped<CohortDefinition>(males, null));
        patientWithANCConsultationTestedForHIVWithUnknownHIVStatus.getSearches().put("1", new Mapped<CohortDefinition>(patientWithANCConsultationTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithANCConsultationTestedForHIVWithUnknownHIVStatus.getSearches().put("2", new Mapped<CohortDefinition>(patientWithANCConsultationWithHIVStatus, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithANCConsultationTestedForHIVWithUnknownHIVStatus.setCompositionString("1 and (not 2)");

        CohortIndicator patientWithANCConsultationTestedForHIVWithUnknownHIVStatusIndicator = Indicators.newCohortIndicator("patientWithANCConsultationTestedForHIVWithUnknownHIVStatusIndicator",
                patientWithANCConsultationTestedForHIVWithUnknownHIVStatus, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D05", "Number of pregnant women with unknown HIV status tested for HIV", new Mapped(patientWithANCConsultationTestedForHIVWithUnknownHIVStatusIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D06 Pregnant women tested HIV positive
        //===============================================

        CodedObsCohortDefinition patientWithHIVPositiveObs = new CodedObsCohortDefinition();
        patientWithHIVPositiveObs.setName("PatientWithHIVPositiveObs");
        patientWithHIVPositiveObs.setQuestion(resultOfHIVTest);
        patientWithHIVPositiveObs.addValue(positive);
        patientWithHIVPositiveObs.addEncounterType(ANCEncounterType);
        patientWithHIVPositiveObs.setOperator(SetComparator.IN);
        patientWithHIVPositiveObs.setTimeModifier(TimeModifier.LAST);
        patientWithHIVPositiveObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithHIVPositiveObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientWithHIVPositiveObsIndicator = Indicators.newCohortIndicator("patientWithHIVPositiveObsIndicator",
                patientWithHIVPositiveObs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D06", "Pregnant women tested HIV positive", new Mapped(patientWithHIVPositiveObsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D07 Number of HIV positive women who received their results
        //===============================================

        DateObsCohortDefinition patientsWithDateForReceivingHIVResult = new DateObsCohortDefinition();
        patientsWithDateForReceivingHIVResult.setName("patientsWithDateForReceivingHIVResult");
        patientsWithDateForReceivingHIVResult.setQuestion(HIVResultRecieveDate);
        patientsWithDateForReceivingHIVResult.addEncounterType(ANCEncounterType);
        patientsWithDateForReceivingHIVResult.setTimeModifier(TimeModifier.LAST);
        patientsWithDateForReceivingHIVResult.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithDateForReceivingHIVResult.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientsWithDateForReceivingHIVResultIndicator = Indicators.newCohortIndicator("patientsWithDateForReceivingHIVResultIndicator",
                patientsWithDateForReceivingHIVResult, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D07", "Number of HIV positive women who received their results", new Mapped(patientsWithDateForReceivingHIVResultIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D08 HIV positive pregnant women eligible for treatment who received HAART For their own health(same as D07)
        //===============================================


        //===============================================
        // D09 Pregnant women partners tested for HIV
        //===============================================

        DateObsCohortDefinition patientsWithpartnerHIVTestDate = new DateObsCohortDefinition();
        patientsWithpartnerHIVTestDate.setName("patientsWithpartnerHIVTestDate");
        patientsWithpartnerHIVTestDate.setQuestion(partnerHIVTestDate);
        patientsWithpartnerHIVTestDate.addEncounterType(ANCEncounterType);
        patientsWithpartnerHIVTestDate.setTimeModifier(TimeModifier.LAST);
        patientsWithpartnerHIVTestDate.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithpartnerHIVTestDate.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientsWithpartnerHIVTestDateIndicator = Indicators.newCohortIndicator("patientsWithDateForReceivingHIVResultIndicator",
                patientsWithpartnerHIVTestDate, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D09", "Pregnant women partners tested for HIV", new Mapped(patientsWithpartnerHIVTestDateIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D10 Partners tested HIV positive
        //===============================================

        EncounterCohortDefinition patientsWithANCVisit = new EncounterCohortDefinition();
        patientsWithANCVisit.setName("patientsWithANCVisit");
        patientsWithANCVisit.addEncounterType(ANCEncounterType);
        patientsWithANCVisit.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithANCVisit.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CodedObsCohortDefinition patientWithPartnesHIVPositiveObs = new CodedObsCohortDefinition();
        patientWithPartnesHIVPositiveObs.setName("patientWithPartnesHIVPositiveObs");
        patientWithPartnesHIVPositiveObs.setQuestion(partnerHIVStatus);
        patientWithPartnesHIVPositiveObs.addValue(positive);
        patientWithPartnesHIVPositiveObs.addEncounterType(ANCEncounterType);
        patientWithPartnesHIVPositiveObs.setOperator(SetComparator.IN);
        patientWithPartnesHIVPositiveObs.setTimeModifier(TimeModifier.LAST);
        patientWithPartnesHIVPositiveObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPartnesHIVPositiveObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientWithPartnesHIVPositiveObsIndicator = Indicators.newCohortIndicator("patientWithPartnesHIVPositiveObsIndicator",
                patientWithPartnesHIVPositiveObs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D10", "Partners tested HIV positive", new Mapped(patientWithPartnesHIVPositiveObsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // D11 Discordant couples identified in ANC
        //===============================================

        CodedObsCohortDefinition patientWithDisclosePartne = new CodedObsCohortDefinition();
        patientWithDisclosePartne.setName("patientWithPartnesHIVPositiveObs");
        patientWithDisclosePartne.setQuestion(partnerDisclose);
        patientWithDisclosePartne.addValue(yes);
        patientWithDisclosePartne.addEncounterType(ANCEncounterType);
        patientWithDisclosePartne.setOperator(SetComparator.IN);
        patientWithDisclosePartne.setTimeModifier(TimeModifier.LAST);
        patientWithDisclosePartne.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithDisclosePartne.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientWithDisclosePartneIndicator = Indicators.newCohortIndicator("patientWithDisclosePartneIndicator",
                patientWithDisclosePartne, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D11", "Discordant couples identified in ANC", new Mapped(patientWithDisclosePartneIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D12 HIV negative pregnant women whose partners are tested HIV Positive
        //===============================================

        CodedObsCohortDefinition patientWithHIVNegativeObs = new CodedObsCohortDefinition();
        patientWithHIVNegativeObs.setName("PatientWithHIVPositiveObs");
        patientWithHIVNegativeObs.setQuestion(resultOfHIVTest);
        patientWithHIVNegativeObs.addValue(negative);
        patientWithHIVNegativeObs.addEncounterType(ANCEncounterType);
        patientWithHIVNegativeObs.setOperator(SetComparator.IN);
        patientWithHIVNegativeObs.setTimeModifier(TimeModifier.LAST);
        patientWithHIVNegativeObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithHIVNegativeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition patientWithHIVNegativeAndPartnesHIVPositive = new CompositionCohortDefinition();
        patientWithHIVNegativeAndPartnesHIVPositive.setName("patientWithHIVNegativeAndPartnesHIVPositive");
        patientWithHIVNegativeAndPartnesHIVPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithHIVNegativeAndPartnesHIVPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithHIVNegativeAndPartnesHIVPositive.getSearches().put("1", new Mapped<CohortDefinition>(patientWithHIVNegativeObs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithHIVNegativeAndPartnesHIVPositive.getSearches().put("2", new Mapped<CohortDefinition>(patientWithPartnesHIVPositiveObs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithHIVNegativeAndPartnesHIVPositive.setCompositionString("1 and 2");

        CohortIndicator patientWithHIVNegativeAndPartnesHIVPositiveIndicator = Indicators.newCohortIndicator("patientWithHIVNegativeAndPartnesHIVPositiveIndicator",
                patientWithHIVNegativeAndPartnesHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D12", "Pregnant women tested HIV positive", new Mapped(patientWithHIVNegativeAndPartnesHIVPositiveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // D13 HIV positive partners of HIV negative pregnant women started on ART
        //===============================================

        SqlCohortDefinition PatientWithHIVPositivePartnersUnderART = new SqlCohortDefinition();
        PatientWithHIVPositivePartnersUnderART.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + ANCInvestigationForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + PositivepartneronARV.getConceptId() + " and o.value_coded=" + TRUE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        PatientWithHIVPositivePartnersUnderART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithHIVPositivePartnersUnderART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PregnancyWomenWithHIVPositivePartnersUnderART = new CompositionCohortDefinition();
        PregnancyWomenWithHIVPositivePartnersUnderART.setName("PregnancyWomenWithHIVPositivePartnersUnderART");
        PregnancyWomenWithHIVPositivePartnersUnderART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PregnancyWomenWithHIVPositivePartnersUnderART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PregnancyWomenWithHIVPositivePartnersUnderART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PregnancyWomenWithHIVPositivePartnersUnderART.getSearches().put("1", new Mapped<CohortDefinition>(PatientWithHIVPositivePartnersUnderART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PregnancyWomenWithHIVPositivePartnersUnderART.setCompositionString("1");

        CohortIndicator PregnancyWomenWithHIVPositivePartnersUnderARTIndicator = Indicators.newCohortIndicator("PregnancyWomenWithHIVPositivePartnersUnderARTIndicator",
                PregnancyWomenWithHIVPositivePartnersUnderART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("D13", "HIV positive partners of HIV negative pregnant women started on ART", new Mapped(PregnancyWomenWithHIVPositivePartnersUnderARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // D14 Number of partners tested HIV positive
        //===============================================

        SqlCohortDefinition partinersWithHIVPositiveReceivedResults = new SqlCohortDefinition();
        partinersWithHIVPositiveReceivedResults.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + ANCInvestigationForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + PARTNERRESULTOFHIVTEST.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        //patientWithHIVPositiveFirstANCConsultation.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =74 and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=2169 and o.value_coded=703 and grouped.encounter_datetime >= '2018-02-01' and grouped.encounter_datetime <= '2018-02-09'");
        partinersWithHIVPositiveReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        partinersWithHIVPositiveReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator partinersWithHIVPositiveReceivedResultsIndicator = Indicators.newCohortIndicator("partinersWithHIVPositiveReceivedResultsIndicator",
                partinersWithHIVPositiveReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("D14", "Number of partners tested HIV positive who received their results", new Mapped(partinersWithHIVPositiveReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // D15 Number of HIV positive pregnant women starting Cotrimoxazole this month         (cfr form 74)  accide folique???
        //===============================================

        //===============================================
        // D17 Number of pregnant women who received therapeutic or nutritional supplementation this month
        //===============================================
        //===============================================
        // D18 Number of lactating mothers who received therapeutic or nutritional supplementation this month
        //===============================================

        //===============================================
        // E: Maternity Data Elements
        //===============================================
        //===============================================
        // E01 Number of expected deliveries at the facility this month
        //===============================================

        DateObsCohortDefinition patientWithApproximateDateOfDeliveriesAtFacilityThisMonth = new DateObsCohortDefinition();
        patientWithApproximateDateOfDeliveriesAtFacilityThisMonth.setName("patientWithApproximateDateOfDeliveriesAtFacilityThisMonth");
        patientWithApproximateDateOfDeliveriesAtFacilityThisMonth.setQuestion(expectedDueDate);
        patientWithApproximateDateOfDeliveriesAtFacilityThisMonth.addEncounterType(ANCEncounterType);
        patientWithApproximateDateOfDeliveriesAtFacilityThisMonth.setTimeModifier(TimeModifier.LAST);
        patientWithApproximateDateOfDeliveriesAtFacilityThisMonth.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithApproximateDateOfDeliveriesAtFacilityThisMonth.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CohortIndicator patientWithApproximateDateOfDeliveriesAtFacilityThisMonthIndicator = Indicators.newCohortIndicator("patientWithApproximateDateOfDeliveriesAtFacilityThisMonthIndicator",
                patientWithApproximateDateOfDeliveriesAtFacilityThisMonth, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E01", "Number of expected deliveries at the facility this month", new Mapped(patientWithApproximateDateOfDeliveriesAtFacilityThisMonthIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // E02 Number of deliveries occurring at the facility this month
        //===============================================

        SqlCohortDefinition deliveriesOccurringAtTheFacility = new SqlCohortDefinition();
        deliveriesOccurringAtTheFacility.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + ANCDeliveryReportForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + PlaceOfDelivery.getConceptId() + " and o.value_coded in (" + HOSPITAL.getConceptId() + "," + HEALTHCENTER.getConceptId() + ") and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        deliveriesOccurringAtTheFacility.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        deliveriesOccurringAtTheFacility.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition numberOfdeliveriesOccurringAtTheFacility = new CompositionCohortDefinition();
        numberOfdeliveriesOccurringAtTheFacility.setName("numberOfdeliveriesOccurringAtTheFacility");
        numberOfdeliveriesOccurringAtTheFacility.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        numberOfdeliveriesOccurringAtTheFacility.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        numberOfdeliveriesOccurringAtTheFacility.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        numberOfdeliveriesOccurringAtTheFacility.getSearches().put("1", new Mapped<CohortDefinition>(deliveriesOccurringAtTheFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        numberOfdeliveriesOccurringAtTheFacility.setCompositionString("1");

        CohortIndicator numberOfdeliveriesOccurringAtTheFacilityIndicator = Indicators.newCohortIndicator("numberOfdeliveriesOccurringAtTheFacilityIndicator",
                numberOfdeliveriesOccurringAtTheFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("E02", "Number of deliveries occurring at the facility this month", new Mapped(numberOfdeliveriesOccurringAtTheFacilityIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===============================================
        // E03 Number of expected deliveries among HIV positive women
        //===============================================

        CodedObsCohortDefinition patientWithResultsOfHIVPositiveObs = new CodedObsCohortDefinition();
        patientWithResultsOfHIVPositiveObs.setName("PatientWithHIVPositiveObs");
        patientWithResultsOfHIVPositiveObs.setQuestion(resultOfHIVTest);
        patientWithResultsOfHIVPositiveObs.addValue(positive);
        patientWithResultsOfHIVPositiveObs.setOperator(SetComparator.IN);
        patientWithResultsOfHIVPositiveObs.setTimeModifier(TimeModifier.LAST);

        CodedObsCohortDefinition patientWithPositiveResultsOfHIVConfirmatoryTestObs = new CodedObsCohortDefinition();
        patientWithPositiveResultsOfHIVConfirmatoryTestObs.setName("patientWithPositiveResultsOfHIVConfirmatoryTestObs");
        patientWithPositiveResultsOfHIVConfirmatoryTestObs.setQuestion(resultOFHIVConfirmatoryTest);
        patientWithPositiveResultsOfHIVConfirmatoryTestObs.addValue(positive);
        patientWithPositiveResultsOfHIVConfirmatoryTestObs.setOperator(SetComparator.IN);
        patientWithPositiveResultsOfHIVConfirmatoryTestObs.setTimeModifier(TimeModifier.LAST);

        CompositionCohortDefinition patientWithPositiveResultsOfHIVAndConfirmatoryObs = new CompositionCohortDefinition();
        patientWithPositiveResultsOfHIVAndConfirmatoryObs.setName("patientWithPositiveResultsOfHIVAndConfirmatoryObs");
        patientWithPositiveResultsOfHIVAndConfirmatoryObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPositiveResultsOfHIVAndConfirmatoryObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithPositiveResultsOfHIVAndConfirmatoryObs.getSearches().put("1", new Mapped<CohortDefinition>(patientWithHIVPositiveObs, null));
        patientWithPositiveResultsOfHIVAndConfirmatoryObs.getSearches().put("2", new Mapped<CohortDefinition>(patientWithPositiveResultsOfHIVConfirmatoryTestObs, null));
        patientWithPositiveResultsOfHIVAndConfirmatoryObs.getSearches().put("3", new Mapped<CohortDefinition>(patientWithApproximateDateOfDeliveriesAtFacilityThisMonth, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientWithPositiveResultsOfHIVAndConfirmatoryObs.setCompositionString("(1 OR 2) AND 3");

        CohortIndicator patientWithPositiveResultsOfHIVAndConfirmatoryObsIndicator = Indicators.newCohortIndicator("patientWithPositiveResultsOfHIVAndConfirmatoryObsIndicator",
                patientWithPositiveResultsOfHIVAndConfirmatoryObs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E03", "Number of expected deliveries among HIV positive women", new Mapped(patientWithPositiveResultsOfHIVAndConfirmatoryObsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // E04 Number of HIV positive women giving birth at the facility
        //===============================================

        CodedObsCohortDefinition womenGivingBirthAtHospital = new CodedObsCohortDefinition();
        womenGivingBirthAtHospital.setName("womenGivingBirthAtHospital");
        womenGivingBirthAtHospital.setQuestion(birthLocationType);
        womenGivingBirthAtHospital.addValue(hospital);
        womenGivingBirthAtHospital.setOperator(SetComparator.IN);
        womenGivingBirthAtHospital.setTimeModifier(TimeModifier.LAST);
        womenGivingBirthAtHospital.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        womenGivingBirthAtHospital.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CodedObsCohortDefinition womenGivingBirthAtHealthCenter = new CodedObsCohortDefinition();
        womenGivingBirthAtHealthCenter.setName("womenGivingBirthAtHealthCenter");
        womenGivingBirthAtHealthCenter.setQuestion(birthLocationType);
        womenGivingBirthAtHealthCenter.addValue(healthCenter);
        womenGivingBirthAtHealthCenter.setOperator(SetComparator.IN);
        womenGivingBirthAtHealthCenter.setTimeModifier(TimeModifier.LAST);
        womenGivingBirthAtHealthCenter.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        womenGivingBirthAtHealthCenter.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs = new CompositionCohortDefinition();
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.setName("patientWithPositiveResultsOfHIVAndConfirmatoryObs");
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.getSearches().put("1", new Mapped<CohortDefinition>(patientWithHIVPositiveObs, null));
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.getSearches().put("2", new Mapped<CohortDefinition>(patientWithPositiveResultsOfHIVConfirmatoryTestObs, null));
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.getSearches().put("3", new Mapped<CohortDefinition>(womenGivingBirthAtHospital, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.getSearches().put("4", new Mapped<CohortDefinition>(womenGivingBirthAtHealthCenter, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs.setCompositionString("((1 or 2) AND (3 OR 4)");

        CohortIndicator ppatientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObsIndicator = Indicators.newCohortIndicator("patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObsIndicator",
                patientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E04", "Number of HIV positive women giving birth at the facility", new Mapped(ppatientWithPositiveResultsOfHIVGivingBirthAtHealthFacilitiesObsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // E05 Number of reported HIV positive women giving birth at home
        //===============================================

        CodedObsCohortDefinition womenGivingBirthAtHomeObs = new CodedObsCohortDefinition();
        womenGivingBirthAtHomeObs.setName("womenGivingBirthAtHomeObs");
        womenGivingBirthAtHomeObs.setQuestion(birthLocationType);
        womenGivingBirthAtHomeObs.addValue(house);
        womenGivingBirthAtHomeObs.setOperator(SetComparator.IN);
        womenGivingBirthAtHomeObs.setTimeModifier(TimeModifier.LAST);
        womenGivingBirthAtHomeObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        womenGivingBirthAtHomeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWithPositiveResultsOfHIVGivingBirthAtHomeObs = new CompositionCohortDefinition();
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.setName("patientWithPositiveResultsOfHIVAndConfirmatoryObs");
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.getSearches().put("1", new Mapped<CohortDefinition>(patientWithHIVPositiveObs, null));
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.getSearches().put("2", new Mapped<CohortDefinition>(patientWithPositiveResultsOfHIVConfirmatoryTestObs, null));
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.getSearches().put("3", new Mapped<CohortDefinition>(womenGivingBirthAtHomeObs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithPositiveResultsOfHIVGivingBirthAtHomeObs.setCompositionString("(1 or 2) AND 3");

        CohortIndicator patientWithPositiveResultsOfHIVGivingBirthAtHomeObsIndicator = Indicators.newCohortIndicator("patientWithPositiveResultsOfHIVGivingBirthAtHomeObsIndicator",
                patientWithPositiveResultsOfHIVGivingBirthAtHomeObs, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E05", "Number of reported HIV positive women giving birth at home", new Mapped(patientWithPositiveResultsOfHIVGivingBirthAtHomeObsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // E06 Number of HIV negative women in discordant couple who received single dose of tripletherapy as prophylaxis during labor
        //===============================================

        DateObsCohortDefinition patientsWithPartnerDisclose = new DateObsCohortDefinition();
        patientsWithPartnerDisclose.setName("patientsWithPartnerDisclose");
        patientsWithPartnerDisclose.setQuestion(partnerDisclose);
        patientsWithPartnerDisclose.addEncounterType(ANCEncounterType);
        patientsWithPartnerDisclose.setTimeModifier(TimeModifier.LAST);
        patientsWithPartnerDisclose.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithPartnerDisclose.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CodedObsCohortDefinition patientWithLastHIVNegativeObs = new CodedObsCohortDefinition();
        patientWithLastHIVNegativeObs.setName("PatientWithHIVPositiveObs");
        patientWithLastHIVNegativeObs.setQuestion(resultOfHIVTest);
        patientWithLastHIVNegativeObs.addValue(negative);
        patientWithLastHIVNegativeObs.addEncounterType(ANCEncounterType);
        patientWithLastHIVNegativeObs.setOperator(SetComparator.IN);
        patientWithLastHIVNegativeObs.setTimeModifier(TimeModifier.LAST);

        CodedObsCohortDefinition patientWithDisclosePartneAnyTime = new CodedObsCohortDefinition();
        patientWithDisclosePartneAnyTime.setName("patientWithDisclosePartneAnyTime");
        patientWithDisclosePartneAnyTime.setQuestion(partnerDisclose);
        patientWithDisclosePartneAnyTime.addValue(yes);
        patientWithDisclosePartneAnyTime.setOperator(SetComparator.IN);
        patientWithDisclosePartneAnyTime.setTimeModifier(TimeModifier.LAST);

        CodedObsCohortDefinition patientwithProphylaxisForMotherInPMTCTProgramObs = new CodedObsCohortDefinition();
        patientwithProphylaxisForMotherInPMTCTProgramObs.setName("patientwithProphylaxisForMotherInPMTCTProgramObs");
        patientwithProphylaxisForMotherInPMTCTProgramObs.setQuestion(prophylaxisForMotherInPMTCT);
        patientwithProphylaxisForMotherInPMTCTProgramObs.addValue(antiretroviralMonoTherapyDuringPregnancy);
        patientwithProphylaxisForMotherInPMTCTProgramObs.setOperator(SetComparator.IN);
        patientwithProphylaxisForMotherInPMTCTProgramObs.setTimeModifier(TimeModifier.LAST);
        patientwithProphylaxisForMotherInPMTCTProgramObs.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientwithProphylaxisForMotherInPMTCTProgramObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis = new CompositionCohortDefinition();
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.setName("patientWithHIVNegativeAndPartnesHIVPositive");
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.getSearches().put("1", new Mapped<CohortDefinition>(patientWithLastHIVNegativeObs, null));
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithPartnerDisclose, null));
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.getSearches().put("3", new Mapped<CohortDefinition>(patientwithProphylaxisForMotherInPMTCTProgramObs, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxisIndicator = Indicators.newCohortIndicator("HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxisIndicator",
                HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxis, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E06", "Number of HIV negative women in discordant couple who received single dose of tripletherapy as prophylaxis during labor", new Mapped(HIVNegativewomenReceivingSingleDoseNVPBirthAsProphylaxisIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //===============================================
        // E07 Number of women receiving dual therapy after delivery
        //===============================================
        //===============================================
        // E08 Number of women with unknown HIV status tested for HIV during labor
        //===============================================

        CodedObsCohortDefinition patientWithAnyHIVNegativeObs = new CodedObsCohortDefinition();
        patientWithAnyHIVNegativeObs.setName("patientWithAnyHIVNegativeObs");
        patientWithAnyHIVNegativeObs.setQuestion(resultOfHIVTest);
        patientWithAnyHIVNegativeObs.addValue(negative);
        patientWithAnyHIVNegativeObs.addEncounterType(ANCEncounterType);
        patientWithAnyHIVNegativeObs.setOperator(SetComparator.IN);
        patientWithAnyHIVNegativeObs.setTimeModifier(TimeModifier.LAST);
        patientWithAnyHIVNegativeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CodedObsCohortDefinition patientWithAnyHIVPositiveObs = new CodedObsCohortDefinition();
        patientWithAnyHIVPositiveObs.setName("patientWithAnyHIVPositiveObs");
        patientWithAnyHIVPositiveObs.setQuestion(resultOfHIVTest);
        patientWithAnyHIVPositiveObs.addValue(positive);
        patientWithAnyHIVPositiveObs.addEncounterType(ANCEncounterType);
        patientWithAnyHIVPositiveObs.setOperator(SetComparator.IN);
        patientWithAnyHIVPositiveObs.setTimeModifier(TimeModifier.LAST);
        patientWithAnyHIVPositiveObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        SqlCohortDefinition patientWithANCConsultationTestedForHIVDuringLabor = new SqlCohortDefinition();
        patientWithANCConsultationTestedForHIVDuringLabor.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + ANCDeliveryReportForm.getFormId() + " and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + resultOfHIVTest.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore and o.voided=0");
        patientWithANCConsultationTestedForHIVDuringLabor.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultationTestedForHIVDuringLabor.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition patientWithUnknownHIVStatusAndTestedDuringLabor = new CompositionCohortDefinition();
        patientWithUnknownHIVStatusAndTestedDuringLabor.setName("patientWithUnknownHIVStatusAndTestedDuringLabor");
        patientWithUnknownHIVStatusAndTestedDuringLabor.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithUnknownHIVStatusAndTestedDuringLabor.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithUnknownHIVStatusAndTestedDuringLabor.getSearches().put("1", new Mapped<CohortDefinition>(patientWithAnyHIVNegativeObs, ParameterizableUtil.createParameterMappings("onOrBefore=${onOrBefore-1m}")));
        patientWithUnknownHIVStatusAndTestedDuringLabor.getSearches().put("2", new Mapped<CohortDefinition>(patientWithAnyHIVPositiveObs, ParameterizableUtil.createParameterMappings("onOrBefore=${onOrBefore-1m}")));
        patientWithUnknownHIVStatusAndTestedDuringLabor.getSearches().put("3", new Mapped<CohortDefinition>(patientWithANCConsultationTestedForHIVDuringLabor, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithUnknownHIVStatusAndTestedDuringLabor.setCompositionString(" 3 and (not (1 or 2))");


        CohortIndicator patientWithUnknownHIVStatusAndTestedDuringLaborIndicator = Indicators.newCohortIndicator("patientWithANCConsultationTestedForHIVWithUnknownHIVStatusDuringLaborIndicator",
                patientWithUnknownHIVStatusAndTestedDuringLabor, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E08", "Number of women with unknown HIV status tested for HIV during labor", new Mapped(patientWithUnknownHIVStatusAndTestedDuringLaborIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

//=================================================
//  E09: Number of women with unknown HIV status tested HIV positive during labor
//====================================================

        SqlCohortDefinition patientWithANCConsultationTestedPositiveForHIVDuringLabor = new SqlCohortDefinition();
        patientWithANCConsultationTestedPositiveForHIVDuringLabor.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + ANCDeliveryReportForm.getFormId() + " and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + resultOfHIVTest.getConceptId() + "  and o.value_coded=" + positive + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore and o.voided=0");
        patientWithANCConsultationTestedPositiveForHIVDuringLabor.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithANCConsultationTestedPositiveForHIVDuringLabor.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition patientWithUnknownHIVStatusAndTestedPositiveDuringLabor = new CompositionCohortDefinition();
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.setName("patientWithHIVNegativeAndPartnesHIVPositive");
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.getSearches().put("1", new Mapped<CohortDefinition>(patientWithAnyHIVNegativeObs, ParameterizableUtil.createParameterMappings("onOrBefore=${onOrAfter-1d}")));
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.getSearches().put("2", new Mapped<CohortDefinition>(patientWithAnyHIVPositiveObs, ParameterizableUtil.createParameterMappings("onOrBefore=${onOrAfter-1d}")));
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.getSearches().put("3", new Mapped<CohortDefinition>(patientWithANCConsultationTestedForHIVDuringLabor, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithUnknownHIVStatusAndTestedPositiveDuringLabor.setCompositionString(" 3 and (not (1 or 2))");


        CohortIndicator patientWithUnknownHIVStatusAndTestedPositiveDuringLaborIndicator = Indicators.newCohortIndicator("patientWithANCConsultationTestedForHIVWithUnknownHIVStatusDuringLaborIndicator",
                patientWithUnknownHIVStatusAndTestedDuringLabor, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("E09", "Number of women with unknown HIV status tested HIV positive during labor", new Mapped(patientWithUnknownHIVStatusAndTestedPositiveDuringLaborIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // c. HIV Exposed Infant Follow-up
        //====================================================

        //=====================================================
        // C02. Number of infants born from HIV positive mothers who are 6 weeks of age this month
        //====================================================

        SqlCohortDefinition patientsWhoAre6Weeks = new SqlCohortDefinition();
        patientsWhoAre6Weeks.setName("patientsWhoAre6Weeks");
        patientsWhoAre6Weeks.setQuery("select person_id from person where DATEDIFF(:onOrAfter,birthdate)<42 and DATEDIFF(:onOrBefore,birthdate)>=42 and voided=0");
        patientsWhoAre6Weeks.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWhoAre6Weeks.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        InStateCohortDefinition patientsInExposedInfantState = new InStateCohortDefinition();
        patientsInExposedInfantState.addState(pmtctProgram.getWorkflowByName("PMTCT STATUS").getState(exposedInfantState));
        patientsInExposedInfantState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsInExposedInfantState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));


        CompositionCohortDefinition patientsWhoAre6WeeksInExposedInfantState = new CompositionCohortDefinition();
        patientsWhoAre6WeeksInExposedInfantState.setName("patientsWhoAre6WeeksInExposedInfantState");
        patientsWhoAre6WeeksInExposedInfantState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWhoAre6WeeksInExposedInfantState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientsWhoAre6WeeksInExposedInfantState.getSearches().put("1", new Mapped<CohortDefinition>(patientsWhoAre6Weeks, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWhoAre6WeeksInExposedInfantState.getSearches().put("2", new Mapped<CohortDefinition>(patientsInExposedInfantState, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWhoAre6WeeksInExposedInfantState.setCompositionString(" 1 and 2");


        CohortIndicator patientsWhoAre6WeeksInExposedInfantStateIndicator = Indicators.newCohortIndicator("patientsWhoAre6WeeksInExposedInfantStateIndicator",
                patientsWhoAre6WeeksInExposedInfantState, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("C02", "Number of infants born from HIV positive mothers who are 6 weeks of age this month", new Mapped(patientsWhoAre6WeeksInExposedInfantStateIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // C01. Number of infants born to HIV positive mothers tested at 6 weeks with PCR
        //====================================================

        SqlCohortDefinition patientsWhoAre6WeeksAndTestedPCRAt6Weeks = new SqlCohortDefinition();
        patientsWhoAre6WeeksAndTestedPCRAt6Weeks.setName("patientsWhoAre6WeeksAndTestedPCRAt6Weeks");
        patientsWhoAre6WeeksAndTestedPCRAt6Weeks.setQuery("select p.person_id from person p,obs o where DATEDIFF(:onOrAfter,p.birthdate)<42 and DATEDIFF(:onOrBefore,p.birthdate)>=42 and p.voided=0 and p.person_id=o.person_id and DATEDIFF(o.obs_datetime,p.birthdate)>=42 and DATEDIFF(o.obs_datetime,p.birthdate)<49 and o.concept_id=" + HIVTestType.getConceptId() + " and o.value_coded=" + PCR.getConceptId() + " and o.voided");
        patientsWhoAre6WeeksAndTestedPCRAt6Weeks.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWhoAre6WeeksAndTestedPCRAt6Weeks.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState = new CompositionCohortDefinition();
        patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState.setName("patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState");
        patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState.getSearches().put("1", new Mapped<CohortDefinition>(patientsWhoAre6WeeksAndTestedPCRAt6Weeks, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState.getSearches().put("2", new Mapped<CohortDefinition>(patientsInExposedInfantState, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState.setCompositionString(" 1 and 2");

        CohortIndicator patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantStateIndicator = Indicators.newCohortIndicator("patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantStateIndicator",
                patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantState, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("C01", "Number of infants born to HIV positive mothers tested at 6 weeks with PCR", new Mapped(patientsWhoAre6WeeksAndTestedPCRAt6WeeksInExposedInfantStateIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // C03. Number of Infants born from HIV positive mothers starting Cotrimoxazole Prophylaxis at 6 weeks
        //====================================================

        SqlCohortDefinition patientsWhoStartedCotrimoAt6Weeks = new SqlCohortDefinition();
        patientsWhoStartedCotrimoAt6Weeks.setName("patientsWhoStartedCotrimoAt6Weeks");
        patientsWhoStartedCotrimoAt6Weeks.setQuery("select p.person_id from person p,orders o where DATEDIFF(:onOrAfter,p.birthdate)<42 and DATEDIFF(:onOrBefore,p.birthdate)>=42 and p.voided=0 and p.person_id=o.patient_id and DATEDIFF(o.start_date,p.birthdate)>=42 and DATEDIFF(o.start_date,p.birthdate)<49 and o.concept_id=" + cotrimo.getConceptId() + " and o.voided");
        patientsWhoStartedCotrimoAt6Weeks.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWhoStartedCotrimoAt6Weeks.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientsWhoStartedCotrimoAt6WeeksInExposedInfantState = new CompositionCohortDefinition();
        patientsWhoStartedCotrimoAt6WeeksInExposedInfantState.setName("patientsWhoStartedCotrimoAt6WeeksInExposedInfantState");
        patientsWhoStartedCotrimoAt6WeeksInExposedInfantState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWhoStartedCotrimoAt6WeeksInExposedInfantState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientsWhoStartedCotrimoAt6WeeksInExposedInfantState.getSearches().put("1", new Mapped<CohortDefinition>(patientsWhoStartedCotrimoAt6Weeks, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWhoStartedCotrimoAt6WeeksInExposedInfantState.getSearches().put("2", new Mapped<CohortDefinition>(patientsInExposedInfantState, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWhoStartedCotrimoAt6WeeksInExposedInfantState.setCompositionString(" 1 and 2");

        CohortIndicator patientsWhoStartedCotrimoAt6WeeksInExposedInfantStateIndicator = Indicators.newCohortIndicator("patientsWhoStartedCotrimoAt6WeeksInExposedInfantStateIndicator",
                patientsWhoStartedCotrimoAt6WeeksInExposedInfantState, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("C03", "Number of Infants born from HIV positive mothers starting Cotrimoxazole Prophylaxis at 6 weeks", new Mapped(patientsWhoStartedCotrimoAt6WeeksInExposedInfantStateIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==============================
        // F. VCT Data Elements
        //==============================
        //==============================
        //   < 1 year
        //==============================
        //  Male
        //=====================================================
        //  F01: Number of male clients counseled and tested for HIV
        //=======================================================

        SqlCohortDefinition patientCounseledAndTestedForHIV = new SqlCohortDefinition();
        patientCounseledAndTestedForHIV.setName("patientCounseledAndTestedForHIV");
        patientCounseledAndTestedForHIV.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + VCTCounselingAndTesting.getFormId() + " and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped where  grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientCounseledAndTestedForHIV.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientCounseledAndTestedForHIV.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBelow1Year = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBelow1Year.setName("malepatientCounseledAndTestedForHIVBelow1Year");
        malepatientCounseledAndTestedForHIVBelow1Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBelow1Year.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBelow1Year.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBelow1Year.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBelow1Year.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBelow1Year.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBelow1Year.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBelow1YearIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBelow1YearIndicator",
                malepatientCounseledAndTestedForHIVBelow1Year, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F01", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBelow1YearIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F02: Number of couples counseled and tested
        // =======================================================
        SqlCohortDefinition coupleCounseledAndTestedForHIV = new SqlCohortDefinition();

        coupleCounseledAndTestedForHIV.setName("coupleCounseledAndTestedForHIV");
        coupleCounseledAndTestedForHIV.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id= " + VCTCounselingAndTesting.getFormId() + " and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + TYPEOFCOUNSELING.getConceptId() + " and o.value_coded=" + COUPLE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        coupleCounseledAndTestedForHIV.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        coupleCounseledAndTestedForHIV.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition coupleCounseledAndTested = new CompositionCohortDefinition();
        coupleCounseledAndTested.setName("coupleCounseledAndTested");
        coupleCounseledAndTested.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        coupleCounseledAndTested.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        coupleCounseledAndTested.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        coupleCounseledAndTested.getSearches().put("1", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        coupleCounseledAndTested.setCompositionString("1");

        CohortIndicator coupleCounseledAndTestedIndicator = Indicators.newCohortIndicator("coupleCounseledAndTestedIndicator",
                coupleCounseledAndTested, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F02", "Number of couples counseled and tested", new Mapped(coupleCounseledAndTestedIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F03: Number of discordant couples
        //=======================================================

        SqlCohortDefinition discordantCouple = new SqlCohortDefinition();
        discordantCouple.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        discordantCouple.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        discordantCouple.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWithDiscordantCouple = new CompositionCohortDefinition();
        patientWithDiscordantCouple.setName("patientWithDiscordantCouple");
        patientWithDiscordantCouple.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWithDiscordantCouple.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithDiscordantCouple.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithDiscordantCouple.getSearches().put("1", new Mapped<CohortDefinition>(discordantCouple, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithDiscordantCouple.setCompositionString("1");

        CohortIndicator patientWithDiscordantCoupleIndicator = Indicators.newCohortIndicator("patientWithDiscordantCoupleIndicator",
                patientWithDiscordantCouple, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F03", "Number of discordant couples", new Mapped(patientWithDiscordantCoupleIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        //  F04: Number of male clients tested and received results
        //=======================================================
        SqlCohortDefinition patientTestedAndReceivedResults = new SqlCohortDefinition();
        patientTestedAndReceivedResults.setName("patientTestedAndReceivedResults");
        patientTestedAndReceivedResults.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + HTCReception.getFormId() + " and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped where  grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        ProgramEnrollmentCohortDefinition EnrolledInHIV = new ProgramEnrollmentCohortDefinition();
        EnrolledInHIV.setName("EnrolledInHIV");
        EnrolledInHIV.addProgram(hivProgram);
        EnrolledInHIV.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        EnrolledInHIV.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));

        CompositionCohortDefinition malePatientsBellow1YearTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBellow1YearTestedAndReceivedResults.setName("malePatientsBellow1YearTestedAndReceivedResults");
        malePatientsBellow1YearTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBellow1YearTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBellow1YearTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBellow1YearTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBellow1YearTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));

        malePatientsBellow1YearTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");
        CohortIndicator malePatientsBellow1YearTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBellow1YearTestedAndReceivedResultsIndicator",
                malePatientsBellow1YearTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F04", "Number of male clients tested and received results", new Mapped(malePatientsBellow1YearTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");
        //=====================================================
        //  F05: Number of HIV Positive male clients
        //=======================================================

        SqlCohortDefinition patientWithHIVPositive = new SqlCohortDefinition();
        patientWithHIVPositive.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + VCTCounselingAndTesting + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + RESULTOFHIVTEST.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWithHIVPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));

        patientWithHIVPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        CompositionCohortDefinition patientTestedandReceivedResltsBelow1Year = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBelow1Year.setName("patientTestedandReceivedResltsBelow1Year");
        patientTestedandReceivedResltsBelow1Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBelow1Year.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBelow1Year.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBelow1Year.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBelow1Year.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBelow1Year.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBelow1Year.setCompositionString("1 and 2 and 3");
        CohortIndicator patientpatientTestedandReceivedResltsBelow1YearIndicator = Indicators.newCohortIndicator("patientpatientTestedandReceivedResltsBelow1YearIndicator",
                patientTestedandReceivedResltsBelow1Year, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F05", "Number of male clients tested and received results", new Mapped(patientpatientTestedandReceivedResltsBelow1YearIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        //  F06: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBelow1Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBelow1Years.setName("femalePatientCounseledAndTestedForHIVBelow1Years");
        femalePatientCounseledAndTestedForHIVBelow1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBelow1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBelow1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBelow1Years.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBelow1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBelow1Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBelow1Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBelow1YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBelow1YearsIndicator",
                femalePatientCounseledAndTestedForHIVBelow1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F06", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBelow1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //===================================================
        //  F08: Number of discordant couples
        //=======================================================

        //=====================================================
        //  F09: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition feMalePatientsBellow1Year = new CompositionCohortDefinition();
        feMalePatientsBellow1Year.setName("feMalePatientsBellow1Year");
        feMalePatientsBellow1Year.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        feMalePatientsBellow1Year.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        feMalePatientsBellow1Year.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        feMalePatientsBellow1Year.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        feMalePatientsBellow1Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        feMalePatientsBellow1Year.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        feMalePatientsBellow1Year.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        feMalePatientsBellow1Year.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        feMalePatientsBellow1Year.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));

        feMalePatientsBellow1Year.setCompositionString("2 AND 3 AND 4 AND (NOT 1)");
        CohortIndicator feMalePatientsBellow1YearIndicator = Indicators.newCohortIndicator("feMalePatientsBellow1YearIndicator",
                feMalePatientsBellow1Year, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F09", "Number of female clients tested and received results", new Mapped(feMalePatientsBellow1YearIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F10: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBelow1Year = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBelow1Year.setName("femalePatientTestedandReceivedResltsBelow1Year");
        femalePatientTestedandReceivedResltsBelow1Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBelow1Year.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBelow1Year.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBelow1Year.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBelow1Year.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBelow1Year.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBelow1Year.setCompositionString("1 and 2 and (NOT 3)");
        CohortIndicator femalePatientTestedandReceivedResltsBelow1YearIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBelow1YearIndicator",
                femalePatientTestedandReceivedResltsBelow1Year, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F10", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBelow1YearIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==============================
        //   1-4 years
        //==============================

        //=====================================================
        //  F11: Number of male clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBitween1And4Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBitween1And4Years.setName("malepatientCounseledAndTestedForHIVBitween1And4Years");
        malepatientCounseledAndTestedForHIVBitween1And4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBitween1And4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBitween1And4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBitween1And4Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBitween1And4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBitween1And4Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBitween1And4Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBitween1And4YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBitween1And4YearsIndicator",
                malepatientCounseledAndTestedForHIVBitween1And4Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F11", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBitween1And4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F12: Number of couples counseled and tested
        // =======================================================
        //=====================================================
        //  F13: Number of discordant couples
        //=======================================================

        //=====================================================
        //  F14: Number of male clients tested and received results
        //=======================================================

        CompositionCohortDefinition malePatientsBitween1And4YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBitween1And4YearsTestedAndReceivedResults.setName("malePatientsBitween1And4YearsTestedAndReceivedResults");
        malePatientsBitween1And4YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBitween1And4YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBitween1And4YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBitween1And4YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBitween1And4YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBitween1And4YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBitween1And4YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBitween1And4YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBitween1And4YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBitween1And4YearsTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        malePatientsBitween1And4YearsTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");

        CohortIndicator malePatientsBitween1And4YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBitween1And4YearsTestedAndReceivedResultsIndicator",
                malePatientsBitween1And4YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F14", "Number of male clients tested and received results", new Mapped(malePatientsBitween1And4YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F15: Number of HIV Positive male clients
        //=======================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBitween1And4Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBitween1And4Years.setName("patientTestedandReceivedResltsBitween1And4Years");
        patientTestedandReceivedResltsBitween1And4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBitween1And4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBitween1And4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBitween1And4Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBitween1And4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBitween1And4Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBitween1And4Years.setCompositionString("1 and 2 and 3");

        CohortIndicator patientTestedandReceivedResltsBitween1And4YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBitween1And4YearsIndicator",
                patientTestedandReceivedResltsBitween1And4Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F15", "Number of male clients tested and received results", new Mapped(patientTestedandReceivedResltsBitween1And4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        //  F16: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBitween1And4Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBitween1And4Years.setName("femalePatientCounseledAndTestedForHIVBitween1And4Years");
        femalePatientCounseledAndTestedForHIVBitween1And4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBitween1And4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBitween1And4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBitween1And4Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween1And4Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBitween1And4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBitween1And4Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBitween1And4Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBitween1And4YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBitween1And4YearsIndicator",
                femalePatientCounseledAndTestedForHIVBitween1And4Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F16", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBitween1And4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F17: Number of couples counseled and tested
        // =======================================================
        //===================================================
        //  F18: Number of discordant couples
        //=======================================================
        //=====================================================
        //  F19: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition feMalePatientsBitween1And4Years = new CompositionCohortDefinition();
        feMalePatientsBitween1And4Years.setName("feMalePatientsBitween1And4Years");
        feMalePatientsBitween1And4Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        feMalePatientsBitween1And4Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        feMalePatientsBitween1And4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        feMalePatientsBitween1And4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        feMalePatientsBitween1And4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        feMalePatientsBitween1And4Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        feMalePatientsBitween1And4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        feMalePatientsBitween1And4Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        feMalePatientsBitween1And4Years.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBellow1YearTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        feMalePatientsBitween1And4Years.setCompositionString("2 AND 3 AND 4 AND (NOT 1)");

        CohortIndicator feMalePatientsBitween1And4YearsIndicator = Indicators.newCohortIndicator("feMalePatientsBitween1And4YearsIndicator",
                feMalePatientsBitween1And4Years, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F19", "Number of female clients tested and received results", new Mapped(feMalePatientsBitween1And4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F20: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBitween1And4Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBitween1And4Years.setName("femalePatientTestedandReceivedResltsBitween1And4Years");
        femalePatientTestedandReceivedResltsBitween1And4Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBitween1And4Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBitween1And4Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBitween1And4Years.getSearches().put("1", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBitween1And4Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween1And4Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBitween1And4Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator femalePatientTestedandReceivedResltsBitween1And4YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBitween1And4YearsIndicator",
                femalePatientTestedandReceivedResltsBitween1And4Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F20", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBitween1And4YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==============================
        //   5-9 years
        //==============================

        //=====================================================
        //  F21: Number of male clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBitween5And9Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBitween5And9Years.setName("malepatientCounseledAndTestedForHIVBitween5And9Years");
        malepatientCounseledAndTestedForHIVBitween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBitween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBitween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBitween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBitween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBitween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBitween5And9Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBitween5And9YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBitween5And9YearsIndicator",
                malepatientCounseledAndTestedForHIVBitween5And9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F21", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBitween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F22: Number of couples counseled and tested
        // =======================================================


        //=====================================================
        //  F23: Number of discordant couples
        //=======================================================

        //=====================================================
        //  F24: Number of male clients tested and received results
        //=======================================================

        CompositionCohortDefinition malePatientsBitween5And9YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBitween5And9YearsTestedAndReceivedResults.setName("malePatientsBitween5And9YearsTestedAndReceivedResults");
        malePatientsBitween5And9YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBitween5And9YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBitween5And9YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBitween5And9YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBitween5And9YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBitween5And9YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBitween5And9YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBitween5And9YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBitween5And9YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBitween5And9YearsTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        malePatientsBitween5And9YearsTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");

        CohortIndicator malePatientsBitween5And9YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBitween5And9YearsTestedAndReceivedResultsIndicator",
                malePatientsBitween5And9YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F24", "Number of male clients tested and received results", new Mapped(malePatientsBitween5And9YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F25: Number of HIV Positive male clients
        //=======================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBitween5And9Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBitween5And9Years.setName("patientTestedandReceivedResltsBitween5And9Years");
        patientTestedandReceivedResltsBitween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBitween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBitween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBitween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBitween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBitween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBitween5And9Years.setCompositionString("1 and 2 and 3");

        CohortIndicator patientTestedandReceivedResltsBitween5And9YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBitween5And9YearsIndicator",
                patientTestedandReceivedResltsBitween5And9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F25", "Number of male clients tested and received results", new Mapped(patientTestedandReceivedResltsBitween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        //  F26: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBitween5And9Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBitween5And9Years.setName("femalePatientCounseledAndTestedForHIVBitween5And9Years");
        femalePatientCounseledAndTestedForHIVBitween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBitween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBitween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBitween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBitween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBitween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBitween5And9Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBitween5And9YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBitween5And9YearsIndicator",
                femalePatientCounseledAndTestedForHIVBitween5And9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F26", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBitween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F27: Number of couples counseled and tested
        // =======================================================
        //===================================================
        //  F28: Number of discordant couples
        //=======================================================
        //=====================================================
        //  F29: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition feMalePatientsBitween5And9Years = new CompositionCohortDefinition();
        feMalePatientsBitween5And9Years.setName("feMalePatientsBitween5And9Years");
        feMalePatientsBitween5And9Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        feMalePatientsBitween5And9Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        feMalePatientsBitween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        feMalePatientsBitween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        feMalePatientsBitween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        feMalePatientsBitween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        feMalePatientsBitween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        feMalePatientsBitween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        feMalePatientsBitween5And9Years.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //feMalePatientsBitween5And9Years.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        feMalePatientsBitween5And9Years.setCompositionString("2 AND 3 AND 4 AND (NOT 1)");

        CohortIndicator feMalePatientsBitween5And9YearsIndicator = Indicators.newCohortIndicator("feMalePatientsBitween5And9YearsIndicator",
                feMalePatientsBitween5And9Years, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F29", "Number of female clients tested and received results", new Mapped(feMalePatientsBitween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F30: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBitween5And9Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBitween5And9Years.setName("femalePatientTestedandReceivedResltsBitween5And9Years");
        femalePatientTestedandReceivedResltsBitween5And9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBitween5And9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBitween5And9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBitween5And9Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween5And9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBitween5And9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween5And9Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBitween5And9Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator femalePatientTestedandReceivedResltsBitween5And9YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBitween5And9YearsIndicator",
                femalePatientTestedandReceivedResltsBitween5And9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F30", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBitween5And9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==============================
        //   10-14 years
        //==============================
        //=====================================================
        //  F31: Number of male clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBitween10And14Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBitween10And14Years.setName("malepatientCounseledAndTestedForHIVBitween10And14Years");
        malepatientCounseledAndTestedForHIVBitween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBitween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBitween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBitween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBitween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBitween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBitween10And14Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBitween10And14YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBitween10And14YearsIndicator",
                malepatientCounseledAndTestedForHIVBitween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F31", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBitween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F32: Number of couples counseled and tested
        // =======================================================
        //=====================================================
        //  F33: Number of discordant couples
        //=======================================================

        //=====================================================
        //  F34: Number of male clients tested and received results
        //=======================================================

        CompositionCohortDefinition malePatientsBitween10And14YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBitween10And14YearsTestedAndReceivedResults.setName("malePatientsBitween10And14YearsTestedAndReceivedResults");
        malePatientsBitween10And14YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBitween10And14YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBitween10And14YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBitween10And14YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBitween10And14YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBitween10And14YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBitween10And14YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBitween10And14YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBitween10And14YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBitween10And14YearsTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        malePatientsBitween10And14YearsTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");

        CohortIndicator malePatientsBitween10And14YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBitween10And14YearsTestedAndReceivedResultsIndicator",
                malePatientsBitween10And14YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F34", "Number of male clients tested and received results", new Mapped(malePatientsBitween10And14YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F35: Number of HIV Positive male clients
        //=======================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBitween10And14Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBitween10And14Years.setName("patientTestedandReceivedResltsBitween10And14Years");
        patientTestedandReceivedResltsBitween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBitween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBitween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBitween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBitween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBitween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBitween10And14Years.setCompositionString("1 and 2 and 3");

        CohortIndicator patientTestedandReceivedResltsBitween10And14YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBitween10And14YearsIndicator",
                patientTestedandReceivedResltsBitween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F35", "Number of male clients tested and received results", new Mapped(patientTestedandReceivedResltsBitween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        //  F36: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBitween10And14Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBitween10And14Years.setName("femalePatientCounseledAndTestedForHIVBitween10And14Years");
        femalePatientCounseledAndTestedForHIVBitween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBitween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBitween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBitween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBitween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBitween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBitween10And14Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBitween10And14YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBitween10And14YearsIndicator",
                femalePatientCounseledAndTestedForHIVBitween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F36", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBitween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F37: Number of couples counseled and tested
        // =======================================================
        //===================================================
        //  F38: Number of discordant couples
        //=======================================================
        //=====================================================
        //  F39: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition feMalePatientsBitween10And14Years = new CompositionCohortDefinition();
        feMalePatientsBitween10And14Years.setName("feMalePatientsBitween10And14Years");
        feMalePatientsBitween10And14Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        feMalePatientsBitween10And14Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        feMalePatientsBitween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        feMalePatientsBitween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        feMalePatientsBitween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        feMalePatientsBitween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        feMalePatientsBitween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        feMalePatientsBitween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        feMalePatientsBitween10And14Years.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //feMalePatientsBitween10And14Years.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        feMalePatientsBitween10And14Years.setCompositionString("2 AND 3 AND 4 AND (NOT 1)");

        CohortIndicator feMalePatientsBitween10And14YearsIndicator = Indicators.newCohortIndicator("feMalePatientsBitween10And14YearsIndicator",
                feMalePatientsBitween10And14Years, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F39", "Number of female clients tested and received results", new Mapped(feMalePatientsBitween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F40: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBitween10And14Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBitween10And14Years.setName("femalePatientTestedandReceivedResltsBitween10And14Years");
        femalePatientTestedandReceivedResltsBitween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBitween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBitween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBitween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBitween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween10And14Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBitween10And14Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator femalePatientTestedandReceivedResltsBitween10And14YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBitween10And14YearsIndicator",
                femalePatientTestedandReceivedResltsBitween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F40", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBitween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==============================
        //   15-19 years
        //==============================
        //=====================================================
        //  F41: Number of male clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBitween15And19Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBitween15And19Years.setName("malepatientCounseledAndTestedForHIVBitween15And19Years");
        malepatientCounseledAndTestedForHIVBitween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBitween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBitween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBitween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBitween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBitween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBitween15And19Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBitween15And19YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBitween15And19YearsIndicator",
                malepatientCounseledAndTestedForHIVBitween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F41", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBitween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F42: Number of couples counseled and tested
        // =======================================================
        //=====================================================
        //  F43: Number of discordant couples
        //=======================================================

        //=====================================================
        //  F44: Number of male clients tested and received results
        //=======================================================

        CompositionCohortDefinition malePatientsBitween15And19YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBitween15And19YearsTestedAndReceivedResults.setName("malePatientsBitween15And19YearsTestedAndReceivedResults");
        malePatientsBitween15And19YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBitween15And19YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBitween15And19YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBitween15And19YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBitween15And19YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBitween15And19YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBitween15And19YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBitween15And19YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBitween15And19YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBitween15And19YearsTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        malePatientsBitween15And19YearsTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");

        CohortIndicator malePatientsBitween15And19YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBitween15And19YearsTestedAndReceivedResultsIndicator",
                malePatientsBitween15And19YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F44", "Number of male clients tested and received results", new Mapped(malePatientsBitween15And19YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F45: Number of HIV Positive male clients
        //=======================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBitween15And19Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBitween15And19Years.setName("patientTestedandReceivedResltsBitween15And19Years");
        patientTestedandReceivedResltsBitween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBitween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBitween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBitween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBitween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBitween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBitween15And19Years.setCompositionString("1 and 2 and 3");

        CohortIndicator patientTestedandReceivedResltsBitween15And19YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBitween15And19YearsIndicator",
                patientTestedandReceivedResltsBitween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F45", "Number of male clients tested and received results", new Mapped(patientTestedandReceivedResltsBitween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        //  F46: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBitween15And19Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBitween15And19Years.setName("femalePatientCounseledAndTestedForHIVBitween15And19Years");
        femalePatientCounseledAndTestedForHIVBitween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBitween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBitween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBitween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBitween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBitween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBitween15And19Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBitween15And19YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBitween15And19YearsIndicator",
                femalePatientCounseledAndTestedForHIVBitween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F46", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBitween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F47: Number of couples counseled and tested
        // =======================================================
        //===================================================
        //  F48: Number of discordant couples
        //=======================================================
        //=====================================================
        //  F49: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition feMalePatientsBitween15And19Years = new CompositionCohortDefinition();
        feMalePatientsBitween15And19Years.setName("feMalePatientsBitween15And19Years");
        feMalePatientsBitween15And19Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        feMalePatientsBitween15And19Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        feMalePatientsBitween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        feMalePatientsBitween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        feMalePatientsBitween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        feMalePatientsBitween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        feMalePatientsBitween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        feMalePatientsBitween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        feMalePatientsBitween15And19Years.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //feMalePatientsBitween15And19Years.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        feMalePatientsBitween15And19Years.setCompositionString("2 AND 3 AND 4 AND (NOT 1)");

        CohortIndicator feMalePatientsBitween15And19YearsIndicator = Indicators.newCohortIndicator("feMalePatientsBitween15And19YearsIndicator",
                feMalePatientsBitween15And19Years, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F49", "Number of female clients tested and received results", new Mapped(feMalePatientsBitween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F50: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBitween15And19Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBitween15And19Years.setName("femalePatientTestedandReceivedResltsBitween15And19Years");
        femalePatientTestedandReceivedResltsBitween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBitween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBitween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBitween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBitween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween15And19Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBitween15And19Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator femalePatientTestedandReceivedResltsBitween15And19YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBitween15And19YearsIndicator",
                femalePatientTestedandReceivedResltsBitween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F50", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBitween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //==============================
        //   20-24 years
        //==============================
        //=====================================================
        //  F51: Number of male clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBitween20And24Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBitween20And24Years.setName("malepatientCounseledAndTestedForHIVBitween20And24Years");
        malepatientCounseledAndTestedForHIVBitween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBitween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBitween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBitween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBitween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBitween20And24Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBitween20And24Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBitween20And24YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBitween20And24YearsIndicator",
                malepatientCounseledAndTestedForHIVBitween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F51", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBitween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F52: Number of couples counseled and tested
        // =======================================================
        //=====================================================
        //  F53: Number of discordant couples
        //=======================================================
        //=====================================================
        //  F54: Number of male clients tested and received results
        //=======================================================

        CompositionCohortDefinition malePatientsBitween20And24YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBitween20And24YearsTestedAndReceivedResults.setName("malePatientsBitween15And19YearsTestedAndReceivedResults");
        malePatientsBitween20And24YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBitween20And24YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBitween20And24YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBitween20And24YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBitween20And24YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBitween20And24YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBitween20And24YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBitween20And24YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBitween20And24YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBitween20And24YearsTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        malePatientsBitween20And24YearsTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");

        CohortIndicator malePatientsBitween20And24YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBitween20And24YearsTestedAndReceivedResultsIndicator",
                malePatientsBitween20And24YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F54", "Number of male clients tested and received results", new Mapped(malePatientsBitween20And24YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F55: Number of HIV Positive male clients
        //=======================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBitween20And24Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBitween20And24Years.setName("patientTestedandReceivedResltsBitween20And24Years");
        patientTestedandReceivedResltsBitween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBitween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBitween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBitween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBitween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBitween20And24Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBitween20And24Years.setCompositionString("1 and 2 and 3");

        CohortIndicator patientTestedandReceivedResltsBitween20And24YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBitween20And24YearsIndicator",
                patientTestedandReceivedResltsBitween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F55", "Number of male clients tested and received results", new Mapped(patientTestedandReceivedResltsBitween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        //  F56: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBitween20And24Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBitween20And24Years.setName("femalePatientCounseledAndTestedForHIVBitween20And24Years");
        femalePatientCounseledAndTestedForHIVBitween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBitween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBitween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBitween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBitween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBitween20And24Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBitween20And24Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBitween20And24YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBitween20And24YearsIndicator",
                femalePatientCounseledAndTestedForHIVBitween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));


        dsd.addColumn("F56", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBitween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F57: Number of couples counseled and tested
        // =======================================================
        //===================================================
        //  F58: Number of discordant couples
        //=======================================================
        //=====================================================
        //  F59: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition feMalePatientsBitween20And24Years = new CompositionCohortDefinition();
        feMalePatientsBitween20And24Years.setName("feMalePatientsBitween20And24Years");
        feMalePatientsBitween20And24Years.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        feMalePatientsBitween20And24Years.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        feMalePatientsBitween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        feMalePatientsBitween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        feMalePatientsBitween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        feMalePatientsBitween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        feMalePatientsBitween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        feMalePatientsBitween20And24Years.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        feMalePatientsBitween20And24Years.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //feMalePatientsBitween20And24Years.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        feMalePatientsBitween20And24Years.setCompositionString("2 AND 3 AND 4 AND (NOT 1)");

        CohortIndicator feMalePatientsBitween20And24YearsIndicator = Indicators.newCohortIndicator("feMalePatientsBitween20And24YearsIndicator",
                feMalePatientsBitween20And24Years, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F59", "Number of female clients tested and received results", new Mapped(feMalePatientsBitween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F60: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBitween20And24Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBitween20And24Years.setName("femalePatientTestedandReceivedResltsBitween20And24Years");
        femalePatientTestedandReceivedResltsBitween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBitween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBitween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBitween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBitween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween20And24Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBitween20And24Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator femalePatientTestedandReceivedResltsBitween20And24YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBitween20And24YearsIndicator",
                femalePatientTestedandReceivedResltsBitween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F60", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBitween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //25-49 years
        //males
        //=====================================================
        //  F61: Number of male clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBitween25And49Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBitween25And49Years.setName("malepatientCounseledAndTestedForHIVBitween25And49Years");
        malepatientCounseledAndTestedForHIVBitween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBitween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBitween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBitween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBitween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBitween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBitween25And49Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBitween25And49YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBitween25And49YearsIndicator",
                malepatientCounseledAndTestedForHIVBitween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("F61", "Number of male clients counseled and tested for HIV", new Mapped(malepatientCounseledAndTestedForHIVBitween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F62: Number of male clients tested and received results
        //=======================================================
        CompositionCohortDefinition malePatientsBitween25And49YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsBitween25And49YearsTestedAndReceivedResults.setName("malePatientsBitween25And49YearsTestedAndReceivedResults");
        malePatientsBitween25And49YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBitween25And49YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBitween25And49YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBitween25And49YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBitween25And49YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBitween25And49YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBitween25And49YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBitween25And49YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(newlyEnrolledInHIV, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${enrolledOnOrAfter},enrolledOnOrBefore=${enrolledOnOrBefore}")));
        malePatientsBitween25And49YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        //malePatientsBitween25And49YearsTestedAndReceivedResults.getSearches().put("5",new Mapped<CohortDefinition>(patientTestedAndReceivedResults, null));
        malePatientsBitween25And49YearsTestedAndReceivedResults.setCompositionString("(1 AND 2 AND 3 AND 4)");

        CohortIndicator malePatientsBitween25And49YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsBitween25And49YearsTestedAndReceivedResultsIndicator",
                malePatientsBitween25And49YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F62", "Number of male clients tested and received results", new Mapped(malePatientsBitween25And49YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F63: Number of HIV Positive male clients
        //=======================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBitween25And49Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBitween25And49Years.setName("patientTestedandReceivedResltsBitween25And49Years");
        patientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBitween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBitween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBitween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBitween25And49Years.setCompositionString("1 and 2 and 3");

        CohortIndicator patientTestedandReceivedResltsBitween25And49YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBitween25And49YearsIndicator",
                patientTestedandReceivedResltsBitween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F63", "Number of male clients tested and received results", new Mapped(patientTestedandReceivedResltsBitween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //females
        //=====================================================
        //  F64: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition femalePatientCounseledAndTestedForHIVBitween25And49Years = new CompositionCohortDefinition();
        femalePatientCounseledAndTestedForHIVBitween25And49Years.setName("femalePatientCounseledAndTestedForHIVBitween25And49Years");
        femalePatientCounseledAndTestedForHIVBitween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientCounseledAndTestedForHIVBitween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientCounseledAndTestedForHIVBitween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientCounseledAndTestedForHIVBitween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientCounseledAndTestedForHIVBitween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientCounseledAndTestedForHIVBitween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientCounseledAndTestedForHIVBitween25And49Years.setCompositionString("1 and 2 and (not 3)");

        CohortIndicator femalePatientCounseledAndTestedForHIVBitween25And49YearsIndicator = Indicators.newCohortIndicator("femalePatientCounseledAndTestedForHIVBitween25And49YearsIndicator",
                femalePatientCounseledAndTestedForHIVBitween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("F64", "Number of female clients counseled and tested for HIV", new Mapped(femalePatientCounseledAndTestedForHIVBitween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        //  F65: Number of female clients tested and received results
        //=======================================================
        CompositionCohortDefinition femalePatientTestedandReceivedResltsBitween25And49Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBitween25And49Years.setName("femalePatientTestedandReceivedResltsBitween25And49Years");
        femalePatientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBitween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBitween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBitween25And49Years.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBitween25And49Years.setCompositionString("1 and 2 and 4 and (NOT 3)");

        CohortIndicator femalePatientTestedandReceivedResltsBitween25And49YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBitween25And49YearsIndicator",
                femalePatientTestedandReceivedResltsBitween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F65", "Number of female clients tested and received results", new Mapped(femalePatientTestedandReceivedResltsBitween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        //  F66: Number of HIV Positive female clients
        //=====================================================

        CompositionCohortDefinition PatientTestedandReceivedResltsBitween25And49Years = new CompositionCohortDefinition();
        PatientTestedandReceivedResltsBitween25And49Years.setName("PatientTestedandReceivedResltsBitween25And49Years");
        PatientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientTestedandReceivedResltsBitween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientTestedandReceivedResltsBitween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        PatientTestedandReceivedResltsBitween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientTestedandReceivedResltsBitween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        PatientTestedandReceivedResltsBitween25And49Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator PatientTestedandReceivedResltsBitween25And49YearsIndicator = Indicators.newCohortIndicator("PatientTestedandReceivedResltsBitween25And49YearsIndicator",
                PatientTestedandReceivedResltsBitween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F66", "Number of HIV Positive female clients", new Mapped(PatientTestedandReceivedResltsBitween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //50< years
        /* CompositionCohortDefinition malepatientCounseledAndTestedForHIVAbove25Years=new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVAbove25Years.setName("malepatientCounseledAndTestedForHIVAbove25Years");
        malepatientCounseledAndTestedForHIVAbove25Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVAbove25Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVAbove25Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVAbove25Years.getSearches().put("1",new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVAbove25Years.getSearches().put("2",new Mapped<CohortDefinition>(patientsWithAgeAbove25Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVAbove25Years.getSearches().put("3",new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVAbove25Years.setCompositionString("1 and 2 and 3");*/


        //males
        //=====================================================
        //  F67: Number of male clients counseled and tested for HIV
        //=======================================================
        AgeCohortDefinition patientsWithAge50AndAbove = new AgeCohortDefinition();
        patientsWithAge50AndAbove.setName("patientsWithAge50AndAbove");
        patientsWithAge50AndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAge50AndAbove.setMinAge(50);
        patientsWithAge50AndAbove.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove = new CompositionCohortDefinition();
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.setName("newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove");
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));

        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${orBefore}")));
        newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.setCompositionString("1 AND 2 AND 3");

        CohortIndicator newMalePatients50YearsAndAboveIndicator = Indicators.newCohortIndicator("newMalePatients50YearsAndAboveIndicator",
                newMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("F67", "Number of male clients counseled and tested for HIV", new Mapped(newMalePatients50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        //  F68: Number of male clients tested and received results
        //=======================================================

        CompositionCohortDefinition malePatientsAbove50YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        malePatientsAbove50YearsTestedAndReceivedResults.setName("malePatientsAbove50YearsTestedAndReceivedResults");
        malePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));

        malePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${orBefore}")));
        malePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsAbove50YearsTestedAndReceivedResults.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator malePatientsAbove50YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("malePatientsAbove50YearsTestedAndReceivedResultsIndicator",
                malePatientsAbove50YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F68", "Number of male clients tested and received results", new Mapped(malePatientsAbove50YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        //  F69: Number of HIV Positive male clients
        //======================================================

        CompositionCohortDefinition HIVPositiveMaleClientsAbove50Years = new CompositionCohortDefinition();
        HIVPositiveMaleClientsAbove50Years.setName("HIVPositiveMaleClientsAbove50Years");
        HIVPositiveMaleClientsAbove50Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPositiveMaleClientsAbove50Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPositiveMaleClientsAbove50Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPositiveMaleClientsAbove50Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPositiveMaleClientsAbove50Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPositiveMaleClientsAbove50Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        HIVPositiveMaleClientsAbove50Years.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVPositiveMaleClientsAbove50YearsIndicator = Indicators.newCohortIndicator("HIVPositiveMaleClientsAbove50YearsIndicator",
                HIVPositiveMaleClientsAbove50Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F69", "Number of HIV Positive male clients", new Mapped(HIVPositiveMaleClientsAbove50YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //females
        //=====================================================
        //  F70: Number of female clients counseled and tested for HIV
        //=======================================================

        CompositionCohortDefinition newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove = new CompositionCohortDefinition();
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.setName("newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove");
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));

        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${orBefore}")));
        newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove.setCompositionString("3 AND 2 AND (NOT 1)");

        CohortIndicator newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAboveIndicator",
                newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));

        dsd.addColumn("F70", "Number of female clients counseled and tested for HIV", new Mapped(newFeMalePatientsCounseledAndTestedForHIVWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F71: Number of female clients tested and received results
        //=======================================================

        CompositionCohortDefinition femalePatientsAbove50YearsTestedAndReceivedResults = new CompositionCohortDefinition();
        femalePatientsAbove50YearsTestedAndReceivedResults.setName("femalePatientsAbove50YearsTestedAndReceivedResults");
        femalePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));

        femalePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientsAbove50YearsTestedAndReceivedResults.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIV, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${orBefore}")));
        femalePatientsAbove50YearsTestedAndReceivedResults.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsAbove50YearsTestedAndReceivedResults.setCompositionString("3 AND 2 AND 4 AND (NOT 1)");

        CohortIndicator femalePatientsAbove50YearsTestedAndReceivedResultsIndicator = Indicators.newCohortIndicator("femalePatientsAbove50YearsTestedAndReceivedResultsIndicator",
                femalePatientsAbove50YearsTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("F71", "Number of male clients tested and received results", new Mapped(femalePatientsAbove50YearsTestedAndReceivedResultsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  F72: Number of HIV Positive female clients
        //=======================================================
        CompositionCohortDefinition HIVPositiveFeMaleClientsAbove50Years = new CompositionCohortDefinition();
        HIVPositiveFeMaleClientsAbove50Years.setName("HIVPositiveFeMaleClientsAbove50Years");
        HIVPositiveFeMaleClientsAbove50Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPositiveFeMaleClientsAbove50Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPositiveFeMaleClientsAbove50Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPositiveFeMaleClientsAbove50Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPositiveFeMaleClientsAbove50Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPositiveFeMaleClientsAbove50Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        HIVPositiveFeMaleClientsAbove50Years.setCompositionString("1 and 2 and (NOT 3)");

        CohortIndicator HIVPositiveFeMaleClientsAbove50YearsIndicator = Indicators.newCohortIndicator("HIVPositiveFeMaleClientsAbove50YearsIndicator",
                HIVPositiveFeMaleClientsAbove50Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F72", "Number of HIV Positive female clients", new Mapped(HIVPositiveFeMaleClientsAbove50YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //==============================
        // G. Family Planning Data Elements
        //==============================
        //=====================================================
        //  G01:Number of HIV positive women expected in family planning at the facility
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween15And45Years = new AgeCohortDefinition();
        patientsWithAgeBetween15And45Years.setName("patientsWithAgeBetween15And45Years");
        patientsWithAgeBetween15And45Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween15And45Years.setMinAge(15);
        patientsWithAgeBetween15And45Years.setMaxAge(45);
        patientsWithAgeBetween15And45Years.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning = new CompositionCohortDefinition();
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.setName("patientsWithAgeBetween15And45Years");
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween15And45Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositive, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning.setCompositionString("1 and 2 and (NOT 3)");
        CohortIndicator patientsWithAgeBetween15And45YearsExpectedInFamilyPlanningIndicator = Indicators.newCohortIndicator("patientsWithAgeBetween15And45YearsExpectedInFamilyPlanningIndicator",
                patientsWithAgeBetween15And45YearsExpectedInFamilyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("G01", "Number of HIV positive women expected in family planning at the facility", new Mapped(patientsWithAgeBetween15And45YearsExpectedInFamilyPlanningIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");
        //=====================================================
        //  G02:Number of HIV positive women seen in family planning
        //=====================================================
        SqlCohortDefinition HIVPositivePatientInFamillyPlanning = new SqlCohortDefinition();
        HIVPositivePatientInFamillyPlanning.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id= 8 and voided=0 order by e.encounter_datetime desc)ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + METHODOFFAMILYPLANNING.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVPositivePatientInFamillyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPositivePatientInFamillyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition femalePatientHIVPositiveSeenInFamillyPlanning = new CompositionCohortDefinition();
        femalePatientHIVPositiveSeenInFamillyPlanning.setName("patientsWithAgeBetween15And45Years");
        femalePatientHIVPositiveSeenInFamillyPlanning.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientHIVPositiveSeenInFamillyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientHIVPositiveSeenInFamillyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientHIVPositiveSeenInFamillyPlanning.getSearches().put("1", new Mapped<CohortDefinition>(HIVPositivePatientInFamillyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientHIVPositiveSeenInFamillyPlanning.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femalePatientHIVPositiveSeenInFamillyPlanning.setCompositionString("1 NOT 2");
        CohortIndicator femalePatientHIVPositiveSeenInFamillyPlanningIndicator = Indicators.newCohortIndicator("femalePatientHIVPositiveSeenInFamillyPlanningIndicator",
                femalePatientHIVPositiveSeenInFamillyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("G02", "Number of HIV positive women seen in family planning", new Mapped(femalePatientHIVPositiveSeenInFamillyPlanningIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  G03:Number of HIV positive women who are receiving modern contraceptive methods
        //=====================================================
        //(confer form from Serucaca)

        SqlCohortDefinition HIVPositivePatientReceivingModernContraceptiveMethods = new SqlCohortDefinition();
        HIVPositivePatientReceivingModernContraceptiveMethods.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id= 118 and voided=0 order by e.encounter_datetime desc)ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + MethodeOfferte.getConceptId() + " and o.value_coded=" + ModernContraceptiveMethod.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVPositivePatientReceivingModernContraceptiveMethods.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPositivePatientReceivingModernContraceptiveMethods.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition femaleHIVPositivePatientReceivingModernContraceptiveMethods = new CompositionCohortDefinition();
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.setName("patientsWithAgeBetween15And45Years");
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.getSearches().put("1", new Mapped<CohortDefinition>(HIVPositivePatientReceivingModernContraceptiveMethods, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleHIVPositivePatientReceivingModernContraceptiveMethods.setCompositionString("1 NOT 2");
        CohortIndicator femaleHIVPositivePatientReceivingModernContraceptiveMethodsIndicator = Indicators.newCohortIndicator("femaleHIVPositivePatientReceivingModernContraceptiveMethodsIndicator",
                femaleHIVPositivePatientReceivingModernContraceptiveMethods, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("G03", "Number of HIV positive women who are receiving modern contraceptive methods", new Mapped(femaleHIVPositivePatientReceivingModernContraceptiveMethodsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  G04:HIV positive women who initiated the modern methods of contraception
        //=====================================================
        //(cree Case status 8048 on form 118 and then poor the old staatus)

        SqlCohortDefinition HIVPositivePatientWithModernContraceptiveMethods = new SqlCohortDefinition();
        HIVPositivePatientWithModernContraceptiveMethods.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id= 118 and voided=0 order by e.encounter_datetime desc)ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + MethodeOfferte.getConceptId() + " and o.value_coded=" + ModernContraceptiveMethod.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVPositivePatientWithModernContraceptiveMethods.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPositivePatientWithModernContraceptiveMethods.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition femaleHIVPositivePatientWithModernContraceptiveMethods = new CompositionCohortDefinition();
        femaleHIVPositivePatientWithModernContraceptiveMethods.setName("femaleHIVPositivePatientWithModernContraceptiveMethods");
        femaleHIVPositivePatientWithModernContraceptiveMethods.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleHIVPositivePatientWithModernContraceptiveMethods.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleHIVPositivePatientWithModernContraceptiveMethods.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleHIVPositivePatientWithModernContraceptiveMethods.getSearches().put("1", new Mapped<CohortDefinition>(HIVPositivePatientReceivingModernContraceptiveMethods, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleHIVPositivePatientWithModernContraceptiveMethods.getSearches().put("2", new Mapped<CohortDefinition>(males, null));
        femaleHIVPositivePatientWithModernContraceptiveMethods.setCompositionString("1 NOT 2");
        CohortIndicator femaleHIVPositivePatientWithModernContraceptiveMethodsIndicator = Indicators.newCohortIndicator("femaleHIVPositivePatientWithModernContraceptiveMethodsIndicator",
                femaleHIVPositivePatientWithModernContraceptiveMethods, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("G04", "HIV positive women who initiated the modern methods of contraception", new Mapped(femaleHIVPositivePatientWithModernContraceptiveMethodsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        /*//=====================================================
        //  H:Nutrition Consultation Data Elements
        //=====================================================

        //=====================================================
        //  H01:Number of HIV+ pediatric patients (age < 5 years) with severe malnutrition this month
        // =====================================================

        AgeCohortDefinition patientsWithAgeBellow5Year = new AgeCohortDefinition();
        patientsWithAgeBellow5Year.setName("patientsWithAgeBellow5Year");
        patientsWithAgeBellow5Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBellow5Year.setMaxAge(5);
        patientsWithAgeBellow5Year.setMaxAgeUnit(DurationUnit.YEARS);

        SqlCohortDefinition patientWithSevereMalnutrition = new SqlCohortDefinition();
        patientWithSevereMalnutrition.setName("patientWithSevereMalnutrition");
        patientWithSevereMalnutrition.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + NutritionStatusMonitoring.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + Degreeofmalnutrition.getConceptId() + " and o.value_coded=" + MALNUTRITIONSEVERE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWithSevereMalnutrition.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithSevereMalnutrition.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVPatientsBelow5YearWithSevereMalnutrition = new CompositionCohortDefinition();
        HIVPatientsBelow5YearWithSevereMalnutrition.setName("HIVPatientsBelow5YearWithSevereMalnutrition");
        HIVPatientsBelow5YearWithSevereMalnutrition.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutrition.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutrition.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutrition.getSearches().put("1", new Mapped<CohortDefinition>(patientWithResultsOfHIVPositiveObs, null));
        HIVPatientsBelow5YearWithSevereMalnutrition.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBellow5Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPatientsBelow5YearWithSevereMalnutrition.getSearches().put("3", new Mapped<CohortDefinition>(patientWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPatientsBelow5YearWithSevereMalnutrition.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVPatientsBelow5YearWithSevereMalnutritionIndicator = Indicators.newCohortIndicator("HIVPatientsBelow5YearWithSevereMalnutritionIndicator",
                HIVPatientsBelow5YearWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H01", "Number of HIV positive bellow 5 years with severe malnutrition", new Mapped(HIVPatientsBelow5YearWithSevereMalnutritionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H2:Number of HIV+ pediatric patients (age < 5 years) with severe malnutrition who received therapeutic or nutritional supplementation this month
        //=====================================================

        CodedObsCohortDefinition patientWithNutritionalTreatmentTypeObs = new CodedObsCohortDefinition();
        patientWithNutritionalTreatmentTypeObs.setName("patientWithNutritionalTreatmentTypeObs");
        patientWithNutritionalTreatmentTypeObs.setQuestion(NutritionalTreatmentType);
        patientWithNutritionalTreatmentTypeObs.addEncounterType(NutritionEncounterType);
        patientWithNutritionalTreatmentTypeObs.setOperator(SetComparator.IN);
        patientWithNutritionalTreatmentTypeObs.setTimeModifier(TimeModifier.LAST);
        patientWithNutritionalTreatmentTypeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic = new CompositionCohortDefinition();
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.setName("HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic");
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.getSearches().put("1", new Mapped<CohortDefinition>(patientWithNutritionalTreatmentTypeObs, null));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBellow5Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.getSearches().put("3", new Mapped<CohortDefinition>(patientWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeuticIndicator = Indicators.newCohortIndicator("HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeuticIndicator",
                HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H02", "Number of HIV positive bellow 5 years with severe malnutrition who received therapeutic", new Mapped(HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeuticIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H3:Number of HIV+ patients (age < 15 years) who received therapeutic or nutritional supplementation this month
        //=====================================================
        AgeCohortDefinition patientsWithAgeBellow15Year = new AgeCohortDefinition();
        patientsWithAgeBellow15Year.setName("patientsWithAgeBellow15Year");
        patientsWithAgeBellow15Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBellow15Year.setMaxAge(15);
        patientsWithAgeBellow15Year.setMaxAgeUnit(DurationUnit.YEARS);

        SqlCohortDefinition patientWhoReceivedTherapeutic = new SqlCohortDefinition();
        patientWhoReceivedTherapeutic.setName("patientWhoReceivedTherapeutic");
        patientWhoReceivedTherapeutic.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + NutritionStatusMonitoring.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13325 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWhoReceivedTherapeutic.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedTherapeutic.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition maleHIVPatientsBelow15YearWithSevereMalnutrition = new CompositionCohortDefinition();
        maleHIVPatientsBelow15YearWithSevereMalnutrition.setName("maleHIVPatientsBelow15YearWithSevereMalnutrition");
        maleHIVPatientsBelow15YearWithSevereMalnutrition.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.getSearches().put("1", new Mapped<CohortDefinition>(patientWithResultsOfHIVPositiveObs, null));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBellow15Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.getSearches().put("3", new Mapped<CohortDefinition>(patientWhoReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.setCompositionString("1 and 2 and 3");

        CohortIndicator maleHIVPatientsBelow15YearWithSevereMalnutritionIndicator = Indicators.newCohortIndicator("maleHIVPatientsBelow15YearWithSevereMalnutritionIndicator",
                maleHIVPatientsBelow15YearWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H03", "Number of HIV positive bellow 15 years with severe malnutrition", new Mapped(maleHIVPatientsBelow15YearWithSevereMalnutritionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H4:Number of HIV+ patients (age 15+ years) who received therapeutic or nutritional supplementation this month
        //=====================================================

        AgeCohortDefinition patientsWithAgeAbove15Year = new AgeCohortDefinition();
        patientsWithAgeAbove15Year.setName("patientsWithAgeAbove15Year");
        patientsWithAgeAbove15Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeAbove15Year.setMinAge(15);
        patientsWithAgeAbove15Year.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition maleHIVPatientsAbove15YearWhoReceivedTherapeutic = new CompositionCohortDefinition();
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.setName("maleHIVPatientsAbove15YearWhoReceivedTherapeutic");
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.getSearches().put("1", new Mapped<CohortDefinition>(patientWithResultsOfHIVPositiveObs, null));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeAbove15Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.getSearches().put("3", new Mapped<CohortDefinition>(patientWhoReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.setCompositionString("1 and 2 and 3");

        CohortIndicator maleHIVPatientsAbove15YearWhoReceivedTherapeuticIndicator = Indicators.newCohortIndicator("maleHIVPatientsAbove15YearWhoReceivedTherapeuticIndicator",
                maleHIVPatientsAbove15YearWhoReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H04", "Number of HIV positive bellow 15 years who received therapeutic or nutritional supplementation this month", new Mapped(maleHIVPatientsAbove15YearWhoReceivedTherapeuticIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H5:Number of patients who received a follow-up and adherence counselling
        //=====================================================

        SqlCohortDefinition patientWhoReceivedFollowup = new SqlCohortDefinition();
        patientWhoReceivedFollowup.setName("patientWhoReceivedFollowup");
        patientWhoReceivedFollowup.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + NutritionFollowup.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWhoReceivedFollowup.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedFollowup.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWhoReceivedFollowupAndAdherenceCounselling = new CompositionCohortDefinition();
        patientWhoReceivedFollowupAndAdherenceCounselling.setName("patientWhoReceivedFollowupAndAdherenceCounselling");
        patientWhoReceivedFollowupAndAdherenceCounselling.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWhoReceivedFollowupAndAdherenceCounselling.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedFollowupAndAdherenceCounselling.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWhoReceivedFollowupAndAdherenceCounselling.getSearches().put("1", new Mapped<CohortDefinition>(patientWhoReceivedFollowup, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWhoReceivedFollowupAndAdherenceCounselling.setCompositionString("1");

        CohortIndicator patientWhoReceivedFollowupAndAdherenceCounsellingIndicator = Indicators.newCohortIndicator("patientWhoReceivedFollowupAndAdherenceCounsellingIndicator",
                patientWhoReceivedFollowupAndAdherenceCounselling, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H05", "Number of patients who received a follow-up and adherence counselling", new Mapped(patientWhoReceivedFollowupAndAdherenceCounsellingIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");
*/
        //=====================================================
       /* // I: Males circumcised Indicators
        //=====================================================
        // < 1 year
        //=====================================================
        // I01: Males circumcised at this Health Facility by surgical circumcision < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumsision = new SqlCohortDefinition();
        maleCircumsision.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + MCSurgicalFormFin.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + EndDate.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumsision.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumsision.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.setName("malePatientsCircomcisedWithSurgicalMethodUnder1Years");
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodUnder1YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I01", "Males circumcised at this Health Facility by surgical circumcision < 1 year", new Mapped(malePatientsCircomcisedWithSurgicalMethodUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I02: Males circumcised at this Health Facility with a medical device  < 1 year
        //=====================================================
        SqlCohortDefinition maleCircumsisionWithMedicalDevice = new SqlCohortDefinition();
        maleCircumsisionWithMedicalDevice.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PrepexFormFin.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + EndDate.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumsisionWithMedicalDevice.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumsisionWithMedicalDevice.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.setName("malePatientsCircomcisedWithMedicalDeviceUnder1Years");
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceUnder1YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I02", "Males circumcised at this Health Facility with a medical device  < 1 year", new Mapped(malePatientsCircomcisedWithMedicalDeviceUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I03: Males circumcised and tested for HIV this month at this Health Facility < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumcisedPrepexAndSurgical = new SqlCohortDefinition();
        maleCircumcisedPrepexAndSurgical.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PrepexAndSurgicalConsultationForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HIVTESTDATE.getConceptId() + " and o.value_datetime>= :onOrAfter and o.value_datetime<= :onOrBefore  and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumcisedPrepexAndSurgical.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumcisedPrepexAndSurgical.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVUnder1Years.setName("malePatientsCircomcisedAndTesedHIVUnder1Years");
        malePatientsCircomcisedAndTesedHIVUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVUnder1YearsIndicator",
                malePatientsCircomcisedAndTesedHIVUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I03", "Males circumcised and tested for HIV this month at this Health Facility < 1 year", new Mapped(malePatientsCircomcisedAndTesedHIVUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I04:  Males circumcised and tested HIV positive this month at this Health Facility < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumcisedTestedWithHIVPositive = new SqlCohortDefinition();
        maleCircumcisedTestedWithHIVPositive.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PrepexAndSurgicalConsultationForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HIVSTATUS.getConceptId() + " and o.value_coded=" + positive.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumcisedTestedWithHIVPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumcisedTestedWithHIVPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositiveUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.setName("malePatientsCircomcisedAndTesedHIVUnder1Years");
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositiveUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositiveUnder1YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositiveUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I04", "Males circumcised and tested HIV positive this month at this Health Facility < 1 year", new Mapped(malePatientsCircomcisedAndTesedHIVPositiveUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I05:  Males circumcised at this health facility who experienced one or more adverse events < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumcisedAtHealthFacility = new SqlCohortDefinition();
        maleCircumcisedAtHealthFacility.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + PrepexFormFin.getFormId() + "," + MCSurgicalFormFin.getFormId() + ") and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id in (" + PrepexComplications.getConceptId() + "," + MCComplication.getConceptId() + ") and o.value_coded=" + TRUE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumcisedAtHealthFacility.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumcisedAtHealthFacility.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsUnder1Years.setName("malePatientsCircomcisedWithAdverseEventsUnder1Years");
        malePatientsCircomcisedWithAdverseEventsUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsUnder1YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I05", "Males circumcised at this Health Facility with a medical device  < 1 year", new Mapped(malePatientsCircomcisedWithAdverseEventsUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //1-9 years

        //=====================================================
        // I06: Males circumcised at this Health Facility by surgical circumcision between1-9year
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween1And9years = patientWithAgeBetween(1, 9);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.setName("malePatientsCircomcisedWithSurgicalMethodBetween1And9years");
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween1And9yearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I06", "Males circumcised at this Health Facility by surgical circumcision between1-9year", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I07: Males circumcised at this Health Facility with a medical device  between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.setName("malePatientsCircomcisedWithMedicalDeviceBetween1And9years");
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween1And9yearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I07", "Males circumcised at this Health Facility with a medical device  between1-9year", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I08: Males circumcised and tested for HIV this month at this Health Facility between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween1And9years.setName("malePatientsCircomcisedAndTesedHIVbetween1And9years");
        malePatientsCircomcisedAndTesedHIVbetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween1And9yearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I08", "Males circumcised and tested for HIV this month at this Health Facility < 1 year", new Mapped(malePatientsCircomcisedAndTesedHIVbetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I09:  Males circumcised and tested HIV positive this month at this Health Facility between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween1And9years");
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween1And9yearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I09", "Males circumcised and tested HIV positive this month at this Health Facility between1-9year", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I10:  Males circumcised at this health facility who experienced one or more adverse events between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.setName("malePatientsCircomcisedWithAdverseEventsBetween1And9years");
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween1And9yearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I10", "Males circumcised at this Health Facility with a medical device between1-9year", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //10-14 years

        //=====================================================
        // I11: Males circumcised at this Health Facility by surgical circumcision between10-14 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween10And14Years = patientWithAgeBetween(10, 14);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween10And14Years");
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween10And14YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I11", "Males circumcised at this Health Facility by surgical circumcision between10-14 years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I12: Males circumcised at this Health Facility with a medical device  between10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween10And14Years");
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween10And14YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I12", "Males circumcised at this Health Facility with a medical device  between10And14Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I13: Males circumcised and tested for HIV this month at this Health Facility between10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.setName("malePatientsCircomcisedAndTesedHIVbetween10And14Years");
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, null));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween10And14YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I13", "Males circumcised and tested for HIV this month at this Health Facility between10And14Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I14:  Males circumcised and tested HIV positive this month at this Health Facility 10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween10And14YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I14", "Males circumcised and tested HIV positive this month at this Health Facility between10And14Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I15:  Males circumcised at this health facility who experienced one or more adverse events 10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.setName("malePatientsCircomcisedWithAdverseEventsBetween1And9years");
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween10And14YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I15", "Males circumcised at this Health Facility with a medical device between10And14Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //15-19 years

        //=====================================================
        // I16: Males circumcised at this Health Facility by surgical circumcision between15-19 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween15And19Years = patientWithAgeBetween(15, 19);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween10And14Years");
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween15And19YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I16", "Males circumcised at this Health Facility by surgical circumcision between15-19 years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I17: Males circumcised at this Health Facility with a medical device  between15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween15And19Years");
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween15And19YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I17", "Males circumcised at this Health Facility with a medical device  between15And19Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I18: Males circumcised and tested for HIV this month at this Health Facility between15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.setName("malePatientsCircomcisedAndTesedHIVbetween15And19Years");
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, null));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween15And19YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I18", "Males circumcised and tested for HIV this month at this Health Facility between15And19Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I19:  Males circumcised and tested HIV positive this month at this Health Facility 15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween15And19YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I19", "Males circumcised and tested HIV positive this month at this Health Facility between10And14Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I20:  Males circumcised at this health facility who experienced one or more adverse events 15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.setName("malePatientsCircomcisedWithAdverseEventsBetween15And19Years");
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween15And19YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I20", "Males circumcised at this Health Facility with a medical device between10And14Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //20-24 years

        //=====================================================
        // I21: Males circumcised at this Health Facility by surgical circumcision between20-24 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween20And24Years = patientWithAgeBetween(20, 24);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween20And24Years");
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween20And24YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I21", "Males circumcised at this Health Facility by surgical circumcision between20And24Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I22: Males circumcised at this Health Facility with a medical device  between20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween20And24Years");
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween20And24YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I22", "Males circumcised at this Health Facility with a medical device  between20And24Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I23: Males circumcised and tested for HIV this month at this Health Facility between20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.setName("malePatientsCircomcisedAndTesedHIVbetween20And24Years");
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, null));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween20And24YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I23", "Males circumcised and tested for HIV this month at this Health Facility between20And24Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I24:  Males circumcised and tested HIV positive this month at this Health Facility 20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween20And24YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I24", "Males circumcised and tested HIV positive this month at this Health Facility between20And24Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I25:  Males circumcised at this health facility who experienced one or more adverse events 20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.setName("malePatientsCircomcisedWithAdverseEventsBetween20And24Years");
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween20And24YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I25", "Males circumcised at this Health Facility with a medical device between20And24Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //25-29years

        //=====================================================
        // I26: Males circumcised at this Health Facility by surgical circumcision between25-29 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween25And29Years = patientWithAgeBetween(25, 29);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween25And29Years");
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween25And29YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I26", "Males circumcised at this Health Facility by surgical circumcision between25And29Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I27: Males circumcised at this Health Facility with a medical device  between25-29Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween20And29Years");
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween25And29YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I27", "Males circumcised at this Health Facility with a medical device  between25And29Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I28: Males circumcised and tested for HIV this month at this Health Facility between25-29Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.setName("malePatientsCircomcisedAndTesedHIVbetween25And29Years");
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween25And29YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I28", "Males circumcised and tested for HIV this month at this Health Facility between25And29Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I29:  Males circumcised and tested HIV positive this month at this Health Facility 25-29Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween25And29YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I29", "Males circumcised and tested HIV positive this month at this Health Facility between25And29Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I30:  Males circumcised at this health facility who experienced one or more adverse events 25-39Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.setName("malePatientsCircomcisedWithAdverseEventsBetween20And24Years");
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween25And29YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I30", "Males circumcised at this Health Facility with a medical device between25And49Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        // 30-34years

        //=====================================================
        // I31: Males circumcised at this Health Facility by surgical circumcision between 30-34 years
        //=====================================================

        AgeCohortDefinition patientsWithAgeBetween30And34Years = patientWithAgeBetween(30, 34);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween30And34Years");
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween30And34YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I31", "Males circumcised at this Health Facility by surgical circumcision between30And34Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I32: Males circumcised at this Health Facility with a medical device  between30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween30And34Years");
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween30And34YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I32", "Males circumcised at this Health Facility with a medical device  Between30And34Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I33: Males circumcised and tested for HIV this month at this Health Facility between30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.setName("malePatientsCircomcisedAndTesedHIVbetween30And34Years");
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween30And34YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I33", "Males circumcised and tested for HIV this month at this Health Facility between30And34Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I34:  Males circumcised and tested HIV positive this month at this Health Facility 30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween30And34YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I34", "Males circumcised and tested HIV positive this month at this Health Facility between30And34Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I35:  Males circumcised at this health facility who experienced one or more adverse events 30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.setName("malePatientsCircomcisedWithAdverseEventsBetween30And34Years");
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween30And34YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I35", "Males circumcised at this Health Facility with a medical device between30And34Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        // 35-39years

        //=====================================================
        // I36: Males circumcised at this Health Facility by surgical circumcision between35-39 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween35And39Years = patientWithAgeBetween(35, 39);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween35And39Years");
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween35And39YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I36", "Males circumcised at this Health Facility by surgical circumcision between35And39Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I37: Males circumcised at this Health Facility with a medical device between35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween35And39Years");
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween35And39YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I37", "Males circumcised at this Health Facility with a medical device  Between35And39Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I38: Males circumcised and tested for HIV this month at this Health Facility between35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.setName("malePatientsCircomcisedAndTesedHIVbetween35And39Years");
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween35And39YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I38", "Males circumcised and tested for HIV this month at this Health Facility between35And39Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I39:  Males circumcised and tested HIV positive this month at this Health Facility 35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween35And39YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I39", "Males circumcised and tested HIV positive this month at this Health Facility between30And34Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I40:  Males circumcised at this health facility who experienced one or more adverse events 35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.setName("malePatientsCircomcisedWithAdverseEventsBetween35And39Years");
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween35And39YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I40", "Males circumcised at this Health Facility with a medical device between30And34Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        // 40-49years
        //=====================================================
        // I41: Males circumcised at this Health Facility by surgical circumcision between40-49 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween40And49Years = patientWithAgeBetween(40, 49);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween40And49Years");
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween40And49YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I41", "Males circumcised at this Health Facility by surgical circumcision between40And49Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I42: Males circumcised at this Health Facility with a medical device between40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween40And49Years");
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween40And49YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I42", "Males circumcised at this Health Facility with a medical device  Between35And39Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I43: Males circumcised and tested for HIV this month at this Health Facility between40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.setName("malePatientsCircomcisedAndTesedHIVbetween40And49Years");
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween40And49YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I43", "Males circumcised and tested for HIV this month at this Health Facility between40And49Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I44:  Males circumcised and tested HIV positive this month at this Health Facility 40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween40And49YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I44", "Males circumcised and tested HIV positive this month at this Health Facility between40And49Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I45:  Males circumcised at this health facility who experienced one or more adverse events 40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.setName("malePatientsCircomcisedWithAdverseEventsBetween40And49Years");
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween40And49YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I45", "Males circumcised at this Health Facility with a medical device between40And49Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        // 50<years
        //=====================================================
        // I46: Males circumcised at this Health Facility by surgical circumcision With 50Years And Above
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.setName("malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove");
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAboveIndicator",
                malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I46", "Males circumcised at this Health Facility by surgical circumcision With 50Years And Above", new Mapped(malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I47: Males circumcised at this Health Facility with a medical device  With 50Years And Above
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.setName("malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove");
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAboveIndicator",
                malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I47", "Males circumcised at this Health Facility with a medical device  With 50Years And Above", new Mapped(malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I48: Males circumcised and tested for HIV this month at this Health Facility With 50Years And Above
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.setName("malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove");
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVWith50YearsAndAboveIndicator",
                malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I48", "Males circumcised and tested for HIV this month at this Health Facility With 50Years And Above", new Mapped(malePatientsCircomcisedAndTesedHIVWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I49:  Males circumcised and tested HIV positive this month at this Health Facility With 50Years And Above
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.setName("malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove");
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAboveIndicator",
                malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I49", "Males circumcised and tested HIV positive this month at this Health Facility With 50Years And Above", new Mapped(malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I50:  Males circumcised at this health facility who experienced one or more adverse events With 50Years And Above
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.setName("malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove");
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsWith50YearsAndAboveIndicator",
                malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I50", "Males circumcised at this Health Facility with a medical device With 50Years And Above", new Mapped(malePatientsCircomcisedWithAdverseEventsWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

*/
        //=====================================================
        // K: Provider-Initiated Testing (PIT)
        //=====================================================
        //5-9ans
        //=====================================================
        // K01: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================
        /*AgeCohortDefinition patientBelowOneYear = patientWithAgeBelow(1);
        AgeCohortDefinition patientsBitween20And24Years = patientWithAgeBetween(20, 24);*/
        AgeCohortDefinition patientsWithAgeBetween25And29Years = patientWithAgeBetween(25, 29);
        /*AgeCohortDefinition patientsBitween25And49Years = patientWithAgeBetween(25, 49);
        AgeCohortDefinition patientsWith50YearsAndAbove = patientWithAgeAbove(50);*/


        SqlCohortDefinition patientCounseledAndTestedForHIVTroughtPIT = new SqlCohortDefinition();
        patientCounseledAndTestedForHIVTroughtPIT.setName("patientCounseledAndTestedForHIVTroughtPIT");
        patientCounseledAndTestedForHIVTroughtPIT.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PITCONSELINGANDTESTING.getFormId() + " and voided=0 order by e.encounter_datetime desc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + PROGRAMTHATORDEREDTEST.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientCounseledAndTestedForHIVTroughtPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientCounseledAndTestedForHIVTroughtPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBetween5An9Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBetween5An9Years.setName("malepatientCounseledAndTestedForHIVBetween5An9Years");
        malepatientCounseledAndTestedForHIVBetween5An9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBetween5An9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBetween5An9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBetween5An9Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBetween5An9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBetween5An9Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBetween5An9Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBetween5An9YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBetween5An9YearsIndicator",
                malepatientCounseledAndTestedForHIVBetween5An9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K01", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(malepatientCounseledAndTestedForHIVBetween5An9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K02: Number of clients who received HIV results through PIT this month
        //=====================================================

        EncounterCohortDefinition patientWithPITVisit = new EncounterCohortDefinition();
        patientWithPITVisit.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithPITVisit.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithPITVisit.addEncounterType(PITVisitEncounterType);

        CompositionCohortDefinition malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.setName("malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT");
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPITIndicator",
                malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K02", "Number of clients who received HIV results through PIT this month", new Mapped(malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K03: Number of clients tested HIV positive through PIT this month
        //=====================================================

        SqlCohortDefinition patientWithHIVPositiveThroughPIT = new SqlCohortDefinition();
        patientWithHIVPositiveThroughPIT.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + HTCReception.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + RESULTOFHIVTEST.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWithHIVPositiveThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithHIVPositiveThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientTestedandReceivedResltsBetween5An9Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBetween5An9Years.setName("patientTestedandReceivedResltsBetween5An9Years");
        patientTestedandReceivedResltsBetween5An9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBetween5An9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBetween5An9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBetween5An9Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBetween5An9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBetween5An9Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBetween5An9Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientTestedandReceivedResltsBetween5An9Years.setCompositionString("1 and 2 and 3 and 4");
        CohortIndicator patientTestedandReceivedResltsBetween5An9YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBetween5An9YearsIndicator",
                patientTestedandReceivedResltsBetween5An9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K03", "Number of clients tested HIV positive through PIT this month", new Mapped(patientTestedandReceivedResltsBetween5An9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        // K4: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition femalepatientCounseledAndTestedForHIVBetween5An9Years = new CompositionCohortDefinition();
        femalepatientCounseledAndTestedForHIVBetween5An9Years.setName("femalepatientCounseledAndTestedForHIVBetween5An9Years");
        femalepatientCounseledAndTestedForHIVBetween5An9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientCounseledAndTestedForHIVBetween5An9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween5An9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween5An9Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalepatientCounseledAndTestedForHIVBetween5An9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientCounseledAndTestedForHIVBetween5An9Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween5An9Years.setCompositionString("2 and 3 and (not 1)");

        CohortIndicator femalepatientCounseledAndTestedForHIVBetween5An9YearsIndicator = Indicators.newCohortIndicator("femalepatientCounseledAndTestedForHIVBetween5An9YearsIndicator",
                femalepatientCounseledAndTestedForHIVBetween5An9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K04", "Female Number of clients counseled and tested for HIV through PIT this month", new Mapped(femalepatientCounseledAndTestedForHIVBetween5An9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K5: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.setName("femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT");
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT.setCompositionString("2 and 3 and 4 and (not 1)");

        CohortIndicator femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPITIndicator",
                femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K05", "Female Number of clients who received HIV results through PIT this month", new Mapped(femalePatientsetween5An9YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K6: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientTestedandReceivedResltsBetween5An9Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBetween5An9Years.setName("femalePatientTestedandReceivedResltsBetween5An9Years");
        femalePatientTestedandReceivedResltsBetween5An9Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBetween5An9Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBetween5An9Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBetween5An9Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBetween5An9Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBetween5An9Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBetween5An9Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        femalePatientTestedandReceivedResltsBetween5An9Years.setCompositionString("1 and 2 and 4 and (not 3)");
        CohortIndicator femalePatientTestedandReceivedResltsBetween5An9YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBetween5An9YearsIndicator",
                femalePatientTestedandReceivedResltsBetween5An9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K06", "Female Number of clients tested HIV positive through PIT this month", new Mapped(femalePatientTestedandReceivedResltsBetween5An9YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //10-14ans
        //=====================================================
        // K07: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================
        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBetween10An14Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBetween10An14Years.setName("malepatientCounseledAndTestedForHIVBetween10An14Years");
        malepatientCounseledAndTestedForHIVBetween10An14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBetween10An14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBetween10An14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBetween10An14Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBetween10An14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBetween10An14Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBetween10An14Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBetween10An14YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBetween10An14YearsIndicator",
                malepatientCounseledAndTestedForHIVBetween10An14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K07", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(malepatientCounseledAndTestedForHIVBetween10An14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K08: Number of clients who received HIV results through PIT this month
        //=====================================================
        CompositionCohortDefinition malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.setName("malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT");
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPITIndicator",
                malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K08", "Number of clients who received HIV results through PIT this month", new Mapped(malePatientsBetween10An14YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K09: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBetween10An14Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBetween10An14Years.setName("patientTestedandReceivedResltsBetween10An14Years");
        patientTestedandReceivedResltsBetween10An14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBetween10An14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBetween10An14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBetween10An14Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBetween10An14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBetween10An14Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBetween10An14Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientTestedandReceivedResltsBetween10An14Years.setCompositionString("1 and 2 and 3 and 4");
        CohortIndicator patientTestedandReceivedResltsBetween10An14YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBetween10An14YearsIndicator",
                patientTestedandReceivedResltsBetween10An14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K09", "Number of clients tested HIV positive through PIT this month", new Mapped(patientTestedandReceivedResltsBetween10An14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        // K10: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition femalepatientCounseledAndTestedForHIVBetween10An14Years = new CompositionCohortDefinition();
        femalepatientCounseledAndTestedForHIVBetween10An14Years.setName("femalepatientCounseledAndTestedForHIVBetween10An14Years");
        femalepatientCounseledAndTestedForHIVBetween10An14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientCounseledAndTestedForHIVBetween10An14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween10An14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween10An14Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalepatientCounseledAndTestedForHIVBetween10An14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientCounseledAndTestedForHIVBetween10An14Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween10An14Years.setCompositionString("2 and 3 and (not 1)");

        CohortIndicator femalepatientCounseledAndTestedForHIVBetween10An14YearsIndicator = Indicators.newCohortIndicator("femalepatientCounseledAndTestedForHIVBetween10An14YearsIndicator",
                femalepatientCounseledAndTestedForHIVBetween10An14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K10", "Female Number of clients counseled and tested for HIV through PIT this month", new Mapped(femalepatientCounseledAndTestedForHIVBetween10An14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K11: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.setName("femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT");
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT.setCompositionString("2 and 3 and 4 and (not 1)");

        CohortIndicator femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPITIndicator",
                femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K11", "Female Number of clients who received HIV results through PIT this month", new Mapped(femalePatientsetween10An14YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K12: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientTestedandReceivedResltsBetween10An14Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBetween10An14Years.setName("femalePatientTestedandReceivedResltsBetween10An14Years");
        femalePatientTestedandReceivedResltsBetween10An14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBetween10An14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBetween10An14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBetween10An14Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween10An14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBetween10An14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBetween10An14Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBetween10An14Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        femalePatientTestedandReceivedResltsBetween10An14Years.setCompositionString("1 and 2 and 4 and (not 3)");
        CohortIndicator femalePatientTestedandReceivedResltsBetween10An14YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBetween10An14YearsIndicator",
                femalePatientTestedandReceivedResltsBetween10An14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K12", "Female Number of clients tested HIV positive through PIT this month", new Mapped(femalePatientTestedandReceivedResltsBetween10An14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //15-19ans
        //=====================================================
        // K13: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================
        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBetween15An19Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBetween15An19Years.setName("malepatientCounseledAndTestedForHIVBetween15An19Years");
        malepatientCounseledAndTestedForHIVBetween15An19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBetween15An19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBetween15An19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBetween15An19Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBetween15An19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBetween15An19Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBetween15An19Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBetween15An19YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBetween15An19YearsIndicator",
                malepatientCounseledAndTestedForHIVBetween15An19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K13", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(malepatientCounseledAndTestedForHIVBetween15An19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K14: Number of clients who received HIV results through PIT this month
        //=====================================================
        CompositionCohortDefinition malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.setName("malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT");
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPITIndicator",
                malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K14", "Number of clients who received HIV results through PIT this month", new Mapped(malePatientsBetween15An19YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K15: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBetween15An19Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBetween15An19Years.setName("patientTestedandReceivedResltsBetween15An19Years");
        patientTestedandReceivedResltsBetween15An19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBetween15An19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBetween15An19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBetween15An19Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBetween15An19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBetween15An19Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBetween15An19Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientTestedandReceivedResltsBetween15An19Years.setCompositionString("1 and 2 and 3 and 4");
        CohortIndicator patientTestedandReceivedResltsBetween15An19YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBetween15An19YearsIndicator",
                patientTestedandReceivedResltsBetween15An19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K15", "Number of clients tested HIV positive through PIT this month", new Mapped(patientTestedandReceivedResltsBetween15An19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        // K16: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition femalepatientCounseledAndTestedForHIVBetween15An19Years = new CompositionCohortDefinition();
        femalepatientCounseledAndTestedForHIVBetween15An19Years.setName("femalepatientCounseledAndTestedForHIVBetween15An19Years");
        femalepatientCounseledAndTestedForHIVBetween15An19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientCounseledAndTestedForHIVBetween15An19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween15An19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween15An19Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalepatientCounseledAndTestedForHIVBetween15An19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientCounseledAndTestedForHIVBetween15An19Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween15An19Years.setCompositionString("2 and 3 and (not 1)");

        CohortIndicator femalepatientCounseledAndTestedForHIVBetween15An19YearsIndicator = Indicators.newCohortIndicator("femalepatientCounseledAndTestedForHIVBetween15An19YearsIndicator",
                femalepatientCounseledAndTestedForHIVBetween15An19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K16", "Female Number of clients counseled and tested for HIV through PIT this month", new Mapped(femalepatientCounseledAndTestedForHIVBetween15An19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K1: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.setName("femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT");
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT.setCompositionString("2 and 3 and 4 and (not 1)");

        CohortIndicator femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPITIndicator",
                femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K17", "Female Number of clients who received HIV results through PIT this month", new Mapped(femalePatientsetween15An19YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K18: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientTestedandReceivedResltsBetween15An19Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBetween15An19Years.setName("femalePatientTestedandReceivedResltsBetween15An19Years");
        femalePatientTestedandReceivedResltsBetween15An19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBetween15An19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBetween15An19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBetween15An19Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween15An19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBetween15An19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBetween15An19Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBetween15An19Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        femalePatientTestedandReceivedResltsBetween15An19Years.setCompositionString("1 and 2 and 4 and (not 3)");
        CohortIndicator femalePatientTestedandReceivedResltsBetween15An19YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBetween15An19YearsIndicator",
                femalePatientTestedandReceivedResltsBetween15An19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K18", "Female Number of clients tested HIV positive through PIT this month", new Mapped(femalePatientTestedandReceivedResltsBetween15An19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //20-24ans
        //=====================================================
        // K19: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================
        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBetween20An24Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBetween20An24Years.setName("malepatientCounseledAndTestedForHIVBetween20An24Years");
        malepatientCounseledAndTestedForHIVBetween20An24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBetween20An24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBetween20An24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBetween20An24Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBetween20An24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBetween20An24Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBetween20An24Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBetween20An24YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBetween20An24YearsIndicator",
                malepatientCounseledAndTestedForHIVBetween20An24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K19", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(malepatientCounseledAndTestedForHIVBetween20An24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K20: Number of clients who received HIV results through PIT this month
        //=====================================================
        CompositionCohortDefinition malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.setName("malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT");
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPITIndicator",
                malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K20", "Number of clients who received HIV results through PIT this month", new Mapped(malePatientsBetween20An24YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K21: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBetween20An24Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBetween20An24Years.setName("patientTestedandReceivedResltsBetween20An24Years");
        patientTestedandReceivedResltsBetween20An24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBetween20An24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBetween20An24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBetween20An24Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBetween20An24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBetween20An24Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBetween20An24Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientTestedandReceivedResltsBetween20An24Years.setCompositionString("1 and 2 and 3 and 4");
        CohortIndicator patientTestedandReceivedResltsBetween20An24YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBetween20An24YearsIndicator",
                patientTestedandReceivedResltsBetween20An24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K21", "Number of clients tested HIV positive through PIT this month", new Mapped(patientTestedandReceivedResltsBetween20An24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        // K22: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition femalepatientCounseledAndTestedForHIVBetween20An24Years = new CompositionCohortDefinition();
        femalepatientCounseledAndTestedForHIVBetween20An24Years.setName("femalepatientCounseledAndTestedForHIVBetween20An24Years");
        femalepatientCounseledAndTestedForHIVBetween20An24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalepatientCounseledAndTestedForHIVBetween20An24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientCounseledAndTestedForHIVBetween20An24Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween20An24Years.setCompositionString("2 and 3 and (not 1)");

        CohortIndicator femalepatientCounseledAndTestedForHIVBetween20An24YearsIndicator = Indicators.newCohortIndicator("femalepatientCounseledAndTestedForHIVBetween20An24YearsIndicator",
                femalepatientCounseledAndTestedForHIVBetween20An24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K22", "Female Number of clients counseled and tested for HIV through PIT this month", new Mapped(femalepatientCounseledAndTestedForHIVBetween20An24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K23: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT = new CompositionCohortDefinition();
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.setName("femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT");
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT.setCompositionString("2 and 3 and 4 and (not 1)");

        CohortIndicator femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPITIndicator = Indicators.newCohortIndicator("femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPITIndicator",
                femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K23", "Female Number of clients who received HIV results through PIT this month", new Mapped(femalepatientCounseledAndTestedForHIVBetween20An24YearsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K24: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientTestedandReceivedResltsBetween20An24Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBetween20An24Years.setName("femalePatientTestedandReceivedResltsBetween20An24Years");
        femalePatientTestedandReceivedResltsBetween20An24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBetween20An24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBetween20An24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBetween20An24Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsBitween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBetween20An24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBetween20An24Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBetween20An24Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        femalePatientTestedandReceivedResltsBetween20An24Years.setCompositionString("1 and 2 and 4 and (not 3)");
        CohortIndicator femalePatientTestedandReceivedResltsBetween20An24YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBetween20An24YearsIndicator",
                femalePatientTestedandReceivedResltsBetween20An24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K24", "Female Number of clients tested HIV positive through PIT this month", new Mapped(femalePatientTestedandReceivedResltsBetween20An24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //25-49ans
        //=====================================================
        // K25: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween25And49Years = new AgeCohortDefinition();
        patientsWithAgeBetween25And49Years.setName("patientsWithAgeBetween25And49Years");
        patientsWithAgeBetween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBetween25And49Years.setMinAge(25);
        patientsWithAgeBetween25And49Years.setMaxAge(49);
        patientsWithAgeBetween25And49Years.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition malepatientCounseledAndTestedForHIVBetween25An49Years = new CompositionCohortDefinition();
        malepatientCounseledAndTestedForHIVBetween25An49Years.setName("malepatientCounseledAndTestedForHIVBetween25An49Years");
        malepatientCounseledAndTestedForHIVBetween25An49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malepatientCounseledAndTestedForHIVBetween25An49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malepatientCounseledAndTestedForHIVBetween25An49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malepatientCounseledAndTestedForHIVBetween25An49Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malepatientCounseledAndTestedForHIVBetween25An49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malepatientCounseledAndTestedForHIVBetween25An49Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malepatientCounseledAndTestedForHIVBetween25An49Years.setCompositionString("1 and 2 and 3");

        CohortIndicator malepatientCounseledAndTestedForHIVBetween25An49YearsIndicator = Indicators.newCohortIndicator("malepatientCounseledAndTestedForHIVBetween25An49YearsIndicator",
                malepatientCounseledAndTestedForHIVBetween5An9Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K25", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(malepatientCounseledAndTestedForHIVBetween25An49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K26: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.setName("malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT");
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPIT.setCompositionString("1 AND 2 AND 3 AND 4");

        CohortIndicator malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPITIndicator",
                malePatientsBetween5An9YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K26", "Number of clients who received HIV results through PIT this month", new Mapped(malePatientsBetween25And49YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K27: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition patientTestedandReceivedResltsBetween25And49Years = new CompositionCohortDefinition();
        patientTestedandReceivedResltsBetween25And49Years.setName("patientTestedandReceivedResltsBetween25And49Years");
        patientTestedandReceivedResltsBetween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientTestedandReceivedResltsBetween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientTestedandReceivedResltsBetween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientTestedandReceivedResltsBetween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween5An9Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        patientTestedandReceivedResltsBetween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientTestedandReceivedResltsBetween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        patientTestedandReceivedResltsBetween25And49Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        patientTestedandReceivedResltsBetween25And49Years.setCompositionString("1 and 2 and 3 and 4");
        CohortIndicator patientTestedandReceivedResltsBetween25And49YearsIndicator = Indicators.newCohortIndicator("patientTestedandReceivedResltsBetween25And49YearsIndicator",
                patientTestedandReceivedResltsBetween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K27", "Number of clients tested HIV positive through PIT this month", new Mapped(patientTestedandReceivedResltsBetween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        // K28: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition femalepatientCounseledAndTestedForHIVBetween25And49Years = new CompositionCohortDefinition();
        femalepatientCounseledAndTestedForHIVBetween25And49Years.setName("femalepatientCounseledAndTestedForHIVBetween25And49Years");
        femalepatientCounseledAndTestedForHIVBetween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalepatientCounseledAndTestedForHIVBetween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalepatientCounseledAndTestedForHIVBetween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalepatientCounseledAndTestedForHIVBetween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalepatientCounseledAndTestedForHIVBetween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalepatientCounseledAndTestedForHIVBetween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalepatientCounseledAndTestedForHIVBetween25And49Years.setCompositionString("2 and 3 and (not 1)");

        CohortIndicator femalepatientCounseledAndTestedForHIVBetween25And49YearsIndicator = Indicators.newCohortIndicator("femalepatientCounseledAndTestedForHIVBetween25And49YearsIndicator",
                femalepatientCounseledAndTestedForHIVBetween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K28", "Female Number of clients counseled and tested for HIV through PIT this month", new Mapped(femalepatientCounseledAndTestedForHIVBetween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K29: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.setName("femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT");
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT.setCompositionString("2 and 3 and 4 and (not 1)");

        CohortIndicator femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPITIndicator",
                femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K29", "Female Number of clients who received HIV results through PIT this month", new Mapped(femalePatientsetween25And49YearsTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K30: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientTestedandReceivedResltsBetween25And49Years = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsBetween25And49Years.setName("femalePatientTestedandReceivedResltsBetween25And49Years");
        femalePatientTestedandReceivedResltsBetween25And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsBetween25And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsBetween25And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsBetween25And49Years.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsBetween25And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsBetween25And49Years.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsBetween25And49Years.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        femalePatientTestedandReceivedResltsBetween25And49Years.setCompositionString("1 and 2 and 4 and (not 3)");
        CohortIndicator femalePatientTestedandReceivedResltsBetween25And49YearsIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsBetween25And49YearsIndicator",
                femalePatientTestedandReceivedResltsBetween25And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K30", "Female Number of clients tested HIV positive through PIT this month", new Mapped(femalePatientTestedandReceivedResltsBetween25And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //50ans
        //=====================================================
        // K31: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition maleClientsAbove50YearCounseledAndTestedThroughPIT = new CompositionCohortDefinition();
        maleClientsAbove50YearCounseledAndTestedThroughPIT.setName("maleClientsAbove50YearCounseledAndTestedThroughPIT");
        maleClientsAbove50YearCounseledAndTestedThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleClientsAbove50YearCounseledAndTestedThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleClientsAbove50YearCounseledAndTestedThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleClientsAbove50YearCounseledAndTestedThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        maleClientsAbove50YearCounseledAndTestedThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleClientsAbove50YearCounseledAndTestedThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleClientsAbove50YearCounseledAndTestedThroughPIT.setCompositionString("1 and 2 and 3");

        CohortIndicator maleClientsAbove50YearCounseledAndTestedThroughPITIndicator = Indicators.newCohortIndicator("maleClientsAbove50YearCounseledAndTestedThroughPITIndicator",
                maleClientsAbove50YearCounseledAndTestedThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K31", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(maleClientsAbove50YearCounseledAndTestedThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // K32: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition ClientsAbove50YearsReceivedHIVResultsThroughPIT = new CompositionCohortDefinition();
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.setName("ClientsAbove50YearsReceivedHIVResultsThroughPIT");
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        ClientsAbove50YearsReceivedHIVResultsThroughPIT.setCompositionString("1 AND 2 AND 3");

        CohortIndicator ClientsAbove50YearsReceivedHIVResultsThroughPITIndicator = Indicators.newCohortIndicator("ClientsAbove50YearsReceivedHIVResultsThroughPITIndicator",
                ClientsAbove50YearsReceivedHIVResultsThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K32", "Number of clients who received HIV results through PIT this month", new Mapped(ClientsAbove50YearsReceivedHIVResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K33: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition HIVPositiveMaleClientsAbove50YearsThroughPIT = new CompositionCohortDefinition();
        HIVPositiveMaleClientsAbove50YearsThroughPIT.setName("HIVPositiveMaleClientsAbove50YearsThroughPIT");
        HIVPositiveMaleClientsAbove50YearsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPositiveMaleClientsAbove50YearsThroughPIT.setCompositionString("1 and 2 and 3 and 4");

        CohortIndicator HIVPositiveMaleClientsAbove50YearsThroughPITIndicator = Indicators.newCohortIndicator("HIVPositiveMaleClientsAbove50YearsThroughPITIndicator",
                HIVPositiveMaleClientsAbove50YearsThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("F33", "Number of HIV Positive male clients", new Mapped(HIVPositiveMaleClientsAbove50YearsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //Female
        //=====================================================
        // K34: Number of clients counseled and tested for HIV through PIT this month
        //=====================================================

        CompositionCohortDefinition femaleClientsAbove50YearCounseledAndTestedThroughPIT = new CompositionCohortDefinition();
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.setName("femaleClientsAbove50YearCounseledAndTestedThroughPIT");
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientCounseledAndTestedForHIVTroughtPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femaleClientsAbove50YearCounseledAndTestedThroughPIT.setCompositionString("2 and 3 and (not 1)");

        CohortIndicator femaleClientsAbove50YearCounseledAndTestedThroughPITIndicator = Indicators.newCohortIndicator("femaleClientsAbove50YearCounseledAndTestedThroughPITIndicator",
                femaleClientsAbove50YearCounseledAndTestedThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("K34", "Number of clients counseled and tested for HIV through PIT this month", new Mapped(femaleClientsAbove50YearCounseledAndTestedThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K35: Number of clients who received HIV results through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT = new CompositionCohortDefinition();
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.setName("femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT");
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrAfter", "enrolledOnOrAfter", Date.class));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("enrolledOnOrBefore", "enrolledOnOrBefore", Date.class));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.getSearches().put("1", new Mapped<CohortDefinition>(males, null));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.getSearches().put("3", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.getSearches().put("4", new Mapped<CohortDefinition>(patientTestedAndReceivedResults, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT.setCompositionString("2 and 3 and 4 and (not 1)");

        CohortIndicator femalePatientsAbove50YearTestedAndReceivedResultsThroughPITIndicator = Indicators.newCohortIndicator("femalePatientsAbove50YearTestedAndReceivedResultsThroughPITIndicator",
                femalePatientsAbove50YearTestedAndReceivedResultsThroughPIT, ParameterizableUtil.createParameterMappings("enrolledOnOrAfter=${startDate},enrolledOnOrBefore=${endDate},effectiveDate=${endDate},onOrAfter=${startDate},onOrBefore=${endDate}"));
        dsd.addColumn("K35", "Female Number of clients who received HIV results through PIT this month", new Mapped(femalePatientsAbove50YearTestedAndReceivedResultsThroughPITIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // K36: Number of clients tested HIV positive through PIT this month
        //=====================================================

        CompositionCohortDefinition femalePatientTestedandReceivedResltsAbove50Year = new CompositionCohortDefinition();
        femalePatientTestedandReceivedResltsAbove50Year.setName("femalePatientTestedandReceivedResltsAbove50Year");
        femalePatientTestedandReceivedResltsAbove50Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        femalePatientTestedandReceivedResltsAbove50Year.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        femalePatientTestedandReceivedResltsAbove50Year.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        femalePatientTestedandReceivedResltsAbove50Year.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAge50AndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        femalePatientTestedandReceivedResltsAbove50Year.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHIVPositiveThroughPIT, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        femalePatientTestedandReceivedResltsAbove50Year.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
        femalePatientTestedandReceivedResltsAbove50Year.getSearches().put("4", new Mapped<CohortDefinition>(patientWithPITVisit, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));

        femalePatientTestedandReceivedResltsAbove50Year.setCompositionString("1 and 2 and 4 and (not 3)");
        CohortIndicator femalePatientTestedandReceivedResltsAbove50YearIndicator = Indicators.newCohortIndicator("femalePatientTestedandReceivedResltsAbove50YearIndicator",
                femalePatientTestedandReceivedResltsAbove50Year, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("K36", "Female Number of clients tested HIV positive through PIT this month", new Mapped(femalePatientTestedandReceivedResltsAbove50YearIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        NutritionConsultationDataElements(dsd);
        MalesCircumcisedIndicators(dsd);
        discordantCouples(dsd);
        postExposureProphylaxis(dsd);


        /*//=====================================================
        // L: Discordant Couples
        //=====================================================
        //=====================================================
        // L01: New discordant couples registered
        //=====================================================

        SqlCohortDefinition NewDiscordantCouplesRegistered = new SqlCohortDefinition();
        NewDiscordantCouplesRegistered.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13388 and o.value_coded=1065 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        NewDiscordantCouplesRegistered.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        NewDiscordantCouplesRegistered.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesNewlyRegistered = new CompositionCohortDefinition();
        DiscordantCouplesNewlyRegistered.setName("DiscordantCouplesNewlyRegistered");
        DiscordantCouplesNewlyRegistered.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesNewlyRegistered.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesNewlyRegistered.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesNewlyRegistered.getSearches().put("1", new Mapped<CohortDefinition>(NewDiscordantCouplesRegistered, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesNewlyRegistered.setCompositionString("1");

        CohortIndicator DiscordantCouplesNewlyRegisteredIndicator = Indicators.newCohortIndicator("DiscordantCouplesNewlyRegisteredIndicator",
                DiscordantCouplesNewlyRegistered, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L01", "New discordant couples registered", new Mapped(DiscordantCouplesNewlyRegisteredIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L02: Ancient discordant couples retraced
        //=====================================================

        SqlCohortDefinition OldDiscordantCouplesRetraced = new SqlCohortDefinition();
        OldDiscordantCouplesRetraced.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13388 and o.value_coded=13230 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        OldDiscordantCouplesRetraced.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        OldDiscordantCouplesRetraced.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesNewlyRetraced = new CompositionCohortDefinition();
        DiscordantCouplesNewlyRetraced.setName("DiscordantCouplesNewlyRetraced");
        DiscordantCouplesNewlyRetraced.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesNewlyRetraced.getSearches().put("1", new Mapped<CohortDefinition>(OldDiscordantCouplesRetraced, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesNewlyRetraced.setCompositionString("1");

        CohortIndicator DiscordantCouplesNewlyRetracedIndicator = Indicators.newCohortIndicator("DiscordantCouplesNewlyRetracedIndicator",
                DiscordantCouplesNewlyRetraced, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L02", "Ancient discordant couples retraced", new Mapped(DiscordantCouplesNewlyRetracedIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L03: Discordant couples expected for re-testing
        //=====================================================

        SqlCohortDefinition DiscordantCouplesExpectedForRetesting = new SqlCohortDefinition();
        DiscordantCouplesExpectedForRetesting.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13332 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        DiscordantCouplesExpectedForRetesting.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesExpectedForRetesting.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWithDiscordantCouplesNewlyRetraced = new CompositionCohortDefinition();
        patientWithDiscordantCouplesNewlyRetraced.setName("patientWithDiscordantCouplesNewlyRetraced");
        patientWithDiscordantCouplesNewlyRetraced.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWithDiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithDiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithDiscordantCouplesNewlyRetraced.getSearches().put("1", new Mapped<CohortDefinition>(DiscordantCouplesExpectedForRetesting, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithDiscordantCouplesNewlyRetraced.setCompositionString("1");

        CohortIndicator patientWithDiscordantCouplesNewlyRetracedIndicator = Indicators.newCohortIndicator("patientWithDiscordantCouplesNewlyRetracedIndicator",
                patientWithDiscordantCouplesNewlyRetraced, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L03", "Discordant couples expected for re-testing", new Mapped(patientWithDiscordantCouplesNewlyRetracedIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L04: Discordant couples that came back for follow-up counseling re-testing
        //=====================================================

        SqlCohortDefinition CouplesCameBackForFollowUp = new SqlCohortDefinition();
        CouplesCameBackForFollowUp.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13332 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        CouplesCameBackForFollowUp.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesCameBackForFollowUp.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition CouplesCameBackForFollowUpCounselingRetesting = new CompositionCohortDefinition();
        CouplesCameBackForFollowUpCounselingRetesting.setName("SerodiscordantCouplesFollowedAtHealthFacility");
        CouplesCameBackForFollowUpCounselingRetesting.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        CouplesCameBackForFollowUpCounselingRetesting.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesCameBackForFollowUpCounselingRetesting.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        CouplesCameBackForFollowUpCounselingRetesting.getSearches().put("1", new Mapped<CohortDefinition>(CouplesCameBackForFollowUp, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        CouplesCameBackForFollowUpCounselingRetesting.setCompositionString("1");

        CohortIndicator CouplesCameBackForFollowUpCounselingRetestingIndicator = Indicators.newCohortIndicator("CouplesCameBackForFollowUpCounselingRetestingIndicator",
                CouplesCameBackForFollowUpCounselingRetesting, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L04", "Discordant couples that came back for follow-up counseling re-testing", new Mapped(CouplesCameBackForFollowUpCounselingRetestingIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // L05: Seroconverted cases
        //====================================================

        SqlCohortDefinition SeroconvertedCase = new SqlCohortDefinition();
        SeroconvertedCase.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=2169 and o.value_coded=703 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        SeroconvertedCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        SeroconvertedCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithSeroconvertedCase = new CompositionCohortDefinition();
        PatientWithSeroconvertedCase.setName("PatientWithSeroconvertedCase");
        PatientWithSeroconvertedCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithSeroconvertedCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithSeroconvertedCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithSeroconvertedCase.getSearches().put("1", new Mapped<CohortDefinition>(SeroconvertedCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithSeroconvertedCase.setCompositionString("1");

        CohortIndicator PatientWithSeroconvertedCaseIndicator = Indicators.newCohortIndicator("PatientWithSeroconvertedCaseIndicator",
                PatientWithSeroconvertedCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L05", "Seroconverted cases", new Mapped(PatientWithSeroconvertedCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L06: Discordant couples using Family Planning
        //====================================================

        SqlCohortDefinition CouplesUsingFamilyPlanning = new SqlCohortDefinition();
        CouplesUsingFamilyPlanning.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=1717 and o.value_coded=2257 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        CouplesUsingFamilyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesUsingFamilyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesUsingFamilyPlanning = new CompositionCohortDefinition();
        DiscordantCouplesUsingFamilyPlanning.setName("DiscordantCouplesUsingFamilyPlanning");
        DiscordantCouplesUsingFamilyPlanning.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesUsingFamilyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesUsingFamilyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesUsingFamilyPlanning.getSearches().put("1", new Mapped<CohortDefinition>(CouplesUsingFamilyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesUsingFamilyPlanning.setCompositionString("1");

        CohortIndicator DiscordantCouplesUsingFamilyPlanningIndicator = Indicators.newCohortIndicator("DiscordantCouplesUsingFamilyPlanningIndicator",
                DiscordantCouplesUsingFamilyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L06", "Discordant couples using Family Planning", new Mapped(DiscordantCouplesUsingFamilyPlanningIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L07: HIV positive partners in discordant couples under ART
        //=====================================================
        SqlCohortDefinition CouplesUnderART = new SqlCohortDefinition();
        CouplesUnderART.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13340 and o.value_coded=2257 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        CouplesUnderART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesUnderART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesUnderART = new CompositionCohortDefinition();
        DiscordantCouplesUnderART.setName("DiscordantCouplesUnderART");
        DiscordantCouplesUnderART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesUnderART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesUnderART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesUnderART.getSearches().put("1", new Mapped<CohortDefinition>(CouplesUnderART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesUnderART.setCompositionString("1");

        CohortIndicator DiscordantCouplesUnderARTIndicator = Indicators.newCohortIndicator("DiscordantCouplesUnderARTIndicator",
                DiscordantCouplesUnderART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L07", "HIV positive partners in discordant couples under ART", new Mapped(DiscordantCouplesUnderARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L08: Serodiscordant couples followed at Health Facility
        //=====================================================
        SqlCohortDefinition SerodiscordantCase = new SqlCohortDefinition();
        SerodiscordantCase.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=2169 and o.value_coded=664 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        SerodiscordantCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        SerodiscordantCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithSerodiscordantCase = new CompositionCohortDefinition();
        PatientWithSerodiscordantCase.setName("PatientWithSeroconvertedCase");
        PatientWithSerodiscordantCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithSerodiscordantCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithSerodiscordantCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithSerodiscordantCase.getSearches().put("1", new Mapped<CohortDefinition>(SerodiscordantCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithSerodiscordantCase.setCompositionString("1");

        CohortIndicator PatientWithSerodiscordantCaseIndicator = Indicators.newCohortIndicator("PatientWithSerodiscordantCaseIndicator",
                PatientWithSerodiscordantCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L08", "Seroconverted cases", new Mapped(PatientWithSerodiscordantCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");
*/
        /*//=====================================================
        // M: PEP - Post Exposure Prophylaxis
        //=====================================================
        //=====================================================
        // M01: Clients at risk of HIV infection as a result of occupational exposure
        //====================================================
        SqlCohortDefinition OccupationalExposureCase = new SqlCohortDefinition();
        OccupationalExposureCase.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + Prophylaxy.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13412 and o.value_coded=13413 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        OccupationalExposureCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        OccupationalExposureCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithOccupationalExposureCase = new CompositionCohortDefinition();
        PatientWithOccupationalExposureCase.setName("PatientWithOccupationalExposureCase");
        PatientWithOccupationalExposureCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithOccupationalExposureCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithOccupationalExposureCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithOccupationalExposureCase.getSearches().put("1", new Mapped<CohortDefinition>(OccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithOccupationalExposureCase.setCompositionString("1");

        CohortIndicator PatientWithOccupationalExposureCaseIndicator = Indicators.newCohortIndicator("PatientWithOccupationalExposureCaseIndicator",
                PatientWithOccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M01", "Clients at risk of HIV infection as a result of occupational exposure", new Mapped(PatientWithOccupationalExposureCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M02: Clients at risk of HIV infection as a result of rape/sexual assault
        //=====================================================
        SqlCohortDefinition HIVInfectionResultOfRape = new SqlCohortDefinition();
        HIVInfectionResultOfRape.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + Prophylaxy.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13412 and o.value_coded=13415 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVInfectionResultOfRape.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfRape.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVInfectionResultOfRapeCase = new CompositionCohortDefinition();
        HIVInfectionResultOfRapeCase.setName("HIVInfectionResultOfRapeCase");
        HIVInfectionResultOfRapeCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVInfectionResultOfRapeCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfRapeCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVInfectionResultOfRapeCase.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfRape, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVInfectionResultOfRapeCase.setCompositionString("1");

        CohortIndicator HIVInfectionResultOfRapeCaseIndicator = Indicators.newCohortIndicator("HIVInfectionResultOfRapeCaseIndicator",
                HIVInfectionResultOfRapeCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M02", "Clients at risk of HIV infection as a result of rape/sexual assault", new Mapped(HIVInfectionResultOfRapeCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M03: Clients at risk of HIV infection as a result of other non-occupational exposure
        //=====================================================
        SqlCohortDefinition HIVInfectionResultOfNonOccupationalExposure = new SqlCohortDefinition();
        HIVInfectionResultOfNonOccupationalExposure.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + Prophylaxy.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13412 and o.value_coded=13414 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVInfectionResultOfNonOccupationalExposure.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfNonOccupationalExposure.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVInfectionResultOfNonOccupationalExposureCase = new CompositionCohortDefinition();
        HIVInfectionResultOfNonOccupationalExposureCase.setName("HIVInfectionResultOfNonOccupationalExposureCase");
        HIVInfectionResultOfNonOccupationalExposureCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVInfectionResultOfNonOccupationalExposureCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfNonOccupationalExposureCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVInfectionResultOfNonOccupationalExposureCase.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfNonOccupationalExposure, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVInfectionResultOfNonOccupationalExposureCase.setCompositionString("1");

        CohortIndicator HIVInfectionResultOfNonOccupationalExposureCaseIndicator = Indicators.newCohortIndicator("HIVInfectionResultOfNonOccupationalExposureCaseIndicator",
                HIVInfectionResultOfNonOccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M03", "Clients at risk of HIV infection as a result of other non-occupational exposure", new Mapped(HIVInfectionResultOfNonOccupationalExposureCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M04: Clients at risk of HIV infection as a result of occupational exposure who received PEP
        //=====================================================
        CodedObsCohortDefinition patientWithHTCVisitEncounterTypeObs = new CodedObsCohortDefinition();
        patientWithHTCVisitEncounterTypeObs.setName("patientWithHTCVisitEncounterTypeObs");
        patientWithHTCVisitEncounterTypeObs.setQuestion(PREVIOUSTREATMENTREGIMEN);
        patientWithHTCVisitEncounterTypeObs.addEncounterType(HTCVisitEncounterType);
        patientWithHTCVisitEncounterTypeObs.setOperator(SetComparator.IN);
        patientWithHTCVisitEncounterTypeObs.setTimeModifier(TimeModifier.LAST);
        patientWithHTCVisitEncounterTypeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithOccupationalExposureCaseWhoReceivedPEP = new CompositionCohortDefinition();
        PatientWithOccupationalExposureCaseWhoReceivedPEP.setName("PatientWithOccupationalExposureCaseWhoReceivedPEP");
        PatientWithOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.getSearches().put("1", new Mapped<CohortDefinition>(OccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHTCVisitEncounterTypeObs, null));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.setCompositionString("1 and 2");

        CohortIndicator PatientWithOccupationalExposureCaseWhoReceivedPEPIndicator = Indicators.newCohortIndicator("PatientWithOccupationalExposureCaseWhoReceivedPEPIndicator",
                PatientWithOccupationalExposureCaseWhoReceivedPEP, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M04", "Clients at risk of HIV infection as a result of occupational exposure who received PEP", new Mapped(PatientWithOccupationalExposureCaseWhoReceivedPEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M05: Clients at risk of HIV infection as a result of rape/sexual assault who received PEP
        //=====================================================
        CompositionCohortDefinition PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP = new CompositionCohortDefinition();
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.setName("PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP");
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfRape, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHTCVisitEncounterTypeObs, null));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.setCompositionString("1 and 2");

        CohortIndicator PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEPIndicator = Indicators.newCohortIndicator("PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEPIndicator",
                PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M05", "Clients at risk of HIV infection as a result of rape/sexual assault who received PEP", new Mapped(PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M06: Clients at risk of HIV infection as a result of other non-occupational exposure who received PEP
        //=====================================================

        CompositionCohortDefinition PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP = new CompositionCohortDefinition();
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.setName("PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP");
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfNonOccupationalExposure, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHTCVisitEncounterTypeObs, null));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.setCompositionString("1 and 2");

        CohortIndicator PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEPIndicator = Indicators.newCohortIndicator("PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEPIndicator",
                PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M06", "Clients at risk of HIV infection as a result of other non-occupational exposure who received PEP", new Mapped(PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");
*/
        return dsd;
    }


    private AgeCohortDefinition patientWithAgeBelow(int age) {
        AgeCohortDefinition patientsWithAgebilow = new AgeCohortDefinition();
        patientsWithAgebilow.setName("patientsWithAgebilow");
        patientsWithAgebilow.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgebilow.setMaxAge(age - 1);
        patientsWithAgebilow.setMaxAgeUnit(DurationUnit.YEARS);
        return patientsWithAgebilow;
    }

    private AgeCohortDefinition patientWithAgeBelowAndIncuded(int age) {
        AgeCohortDefinition patientsWithAgebilow = new AgeCohortDefinition();
        patientsWithAgebilow.setName("patientsWithAgebilow");
        patientsWithAgebilow.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgebilow.setMaxAge(age);
        patientsWithAgebilow.setMaxAgeUnit(DurationUnit.YEARS);
        return patientsWithAgebilow;
    }

    private AgeCohortDefinition patientWithAgeBetween(int age1, int age2) {
        AgeCohortDefinition patientsWithAge = new AgeCohortDefinition();
        patientsWithAge.setName("patientsWithAge");
        patientsWithAge.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAge.setMinAge(age1);
        patientsWithAge.setMaxAge(age2);
        patientsWithAge.setMinAgeUnit(DurationUnit.YEARS);
        patientsWithAge.setMaxAgeUnit(DurationUnit.YEARS);
        return patientsWithAge;
    }

    private AgeCohortDefinition patientWithAgeAbove(int age) {
        AgeCohortDefinition patientsWithAge = new AgeCohortDefinition();
        patientsWithAge.setName("patientsWithAge");
        patientsWithAge.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAge.setMinAge(age);
        patientsWithAge.setMinAgeUnit(DurationUnit.YEARS);
        return patientsWithAge;
    }

    private InStateCohortDefinition patientInRegimenStatus(Concept state) {
        InStateCohortDefinition inState = new InStateCohortDefinition();
        inState.addState(hivProgram.getWorkflowByName("REGIMEN STATUS").getState(state));
        inState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        inState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        return inState;
    }

    private InStateCohortDefinition patientInModel(Concept state) {
        InStateCohortDefinition inState = new InStateCohortDefinition();
        inState.addState(hivProgram.getWorkflowByName("Model").getState(state));
        inState.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        inState.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        return inState;
    }


    private CohortIndicatorDataSetDefinition MalesCircumcisedIndicators(CohortIndicatorDataSetDefinition dsd) {

        // I: Males circumcised Indicators
        //=====================================================
        // < 1 year
        //=====================================================
        // I01: Males circumcised at this Health Facility by surgical circumcision < 1 year
        //=====================================================
        AgeCohortDefinition patientBelowOneYear = patientWithAgeBelow(1);
        AgeCohortDefinition patientsBitween20And24Years = patientWithAgeBetween(20, 24);
        AgeCohortDefinition patientsWithAgeBetween25And29Years = patientWithAgeBetween(25, 29);
        AgeCohortDefinition patientsBitween25And49Years = patientWithAgeBetween(25, 49);
        AgeCohortDefinition patientsWith50YearsAndAbove = patientWithAgeAbove(50);

        SqlCohortDefinition maleCircumsision = new SqlCohortDefinition();
        maleCircumsision.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + MCSurgicalFormFin.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + EndDate.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumsision.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumsision.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.setName("malePatientsCircomcisedWithSurgicalMethodUnder1Years");
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodUnder1YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I01", "Males circumcised at this Health Facility by surgical circumcision < 1 year", new Mapped(malePatientsCircomcisedWithSurgicalMethodUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I02: Males circumcised at this Health Facility with a medical device  < 1 year
        //=====================================================
        SqlCohortDefinition maleCircumsisionWithMedicalDevice = new SqlCohortDefinition();
        maleCircumsisionWithMedicalDevice.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PrepexFormFin.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + EndDate.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumsisionWithMedicalDevice.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumsisionWithMedicalDevice.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.setName("malePatientsCircomcisedWithMedicalDeviceUnder1Years");
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceUnder1YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I02", "Males circumcised at this Health Facility with a medical device  < 1 year", new Mapped(malePatientsCircomcisedWithMedicalDeviceUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I03: Males circumcised and tested for HIV this month at this Health Facility < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumcisedPrepexAndSurgical = new SqlCohortDefinition();
        maleCircumcisedPrepexAndSurgical.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PrepexAndSurgicalConsultationForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HIVTESTDATE.getConceptId() + " and o.value_datetime>= :onOrAfter and o.value_datetime<= :onOrBefore  and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumcisedPrepexAndSurgical.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumcisedPrepexAndSurgical.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVUnder1Years.setName("malePatientsCircomcisedAndTesedHIVUnder1Years");
        malePatientsCircomcisedAndTesedHIVUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVUnder1YearsIndicator",
                malePatientsCircomcisedAndTesedHIVUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I03", "Males circumcised and tested for HIV this month at this Health Facility < 1 year", new Mapped(malePatientsCircomcisedAndTesedHIVUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I04:  Males circumcised and tested HIV positive this month at this Health Facility < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumcisedTestedWithHIVPositive = new SqlCohortDefinition();
        maleCircumcisedTestedWithHIVPositive.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id=" + PrepexAndSurgicalConsultationForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + HIVSTATUS.getConceptId() + " and o.value_coded=" + positive.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumcisedTestedWithHIVPositive.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumcisedTestedWithHIVPositive.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositiveUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.setName("malePatientsCircomcisedAndTesedHIVUnder1Years");
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositiveUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositiveUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositiveUnder1YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositiveUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I04", "Males circumcised and tested HIV positive this month at this Health Facility < 1 year", new Mapped(malePatientsCircomcisedAndTesedHIVPositiveUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I05:  Males circumcised at this health facility who experienced one or more adverse events < 1 year
        //=====================================================

        SqlCohortDefinition maleCircumcisedAtHealthFacility = new SqlCohortDefinition();
        maleCircumcisedAtHealthFacility.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id in (" + PrepexFormFin.getFormId() + "," + MCSurgicalFormFin.getFormId() + ") and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id in (" + PrepexComplications.getConceptId() + "," + MCComplication.getConceptId() + ") and o.value_coded=" + TRUE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        maleCircumcisedAtHealthFacility.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleCircumcisedAtHealthFacility.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsUnder1Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsUnder1Years.setName("malePatientsCircomcisedWithAdverseEventsUnder1Years");
        malePatientsCircomcisedWithAdverseEventsUnder1Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.getSearches().put("2", new Mapped<CohortDefinition>(patientBelowOneYear, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsUnder1Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsUnder1YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsUnder1YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsUnder1Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I05", "Males circumcised at this Health Facility with a medical device  < 1 year", new Mapped(malePatientsCircomcisedWithAdverseEventsUnder1YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //1-9 years

        //=====================================================
        // I06: Males circumcised at this Health Facility by surgical circumcision between1-9year
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween1And9years = patientWithAgeBetween(1, 9);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.setName("malePatientsCircomcisedWithSurgicalMethodBetween1And9years");
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween1And9yearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I06", "Males circumcised at this Health Facility by surgical circumcision between1-9year", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I07: Males circumcised at this Health Facility with a medical device  between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.setName("malePatientsCircomcisedWithMedicalDeviceBetween1And9years");
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween1And9yearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I07", "Males circumcised at this Health Facility with a medical device  between1-9year", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I08: Males circumcised and tested for HIV this month at this Health Facility between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween1And9years.setName("malePatientsCircomcisedAndTesedHIVbetween1And9years");
        malePatientsCircomcisedAndTesedHIVbetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween1And9yearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I08", "Males circumcised and tested for HIV this month at this Health Facility < 1 year", new Mapped(malePatientsCircomcisedAndTesedHIVbetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I09:  Males circumcised and tested HIV positive this month at this Health Facility between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween1And9years");
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween1And9yearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I09", "Males circumcised and tested HIV positive this month at this Health Facility between1-9year", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I10:  Males circumcised at this health facility who experienced one or more adverse events between1-9year
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween1And9years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.setName("malePatientsCircomcisedWithAdverseEventsBetween1And9years");
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween1And9years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween1And9years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween1And9yearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween1And9yearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween1And9years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I10", "Males circumcised at this Health Facility with a medical device between1-9year", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween1And9yearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //10-14 years

        //=====================================================
        // I11: Males circumcised at this Health Facility by surgical circumcision between10-14 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween10And14Years = patientWithAgeBetween(10, 14);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween10And14Years");
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween10And14YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I11", "Males circumcised at this Health Facility by surgical circumcision between10-14 years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I12: Males circumcised at this Health Facility with a medical device  between10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween10And14Years");
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween10And14YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I12", "Males circumcised at this Health Facility with a medical device  between10And14Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I13: Males circumcised and tested for HIV this month at this Health Facility between10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.setName("malePatientsCircomcisedAndTesedHIVbetween10And14Years");
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, null));
        malePatientsCircomcisedAndTesedHIVbetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween10And14YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I13", "Males circumcised and tested for HIV this month at this Health Facility between10And14Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I14:  Males circumcised and tested HIV positive this month at this Health Facility 10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween10And14YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I14", "Males circumcised and tested HIV positive this month at this Health Facility between10And14Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I15:  Males circumcised at this health facility who experienced one or more adverse events 10And14Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween10And14Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.setName("malePatientsCircomcisedWithAdverseEventsBetween1And9years");
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween10And14Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween10And14Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween10And14YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween10And14YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween10And14Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I15", "Males circumcised at this Health Facility with a medical device between10And14Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween10And14YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //15-19 years

        //=====================================================
        // I16: Males circumcised at this Health Facility by surgical circumcision between15-19 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween15And19Years = patientWithAgeBetween(15, 19);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween10And14Years");
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween15And19YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I16", "Males circumcised at this Health Facility by surgical circumcision between15-19 years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I17: Males circumcised at this Health Facility with a medical device  between15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween15And19Years");
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween15And19YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I17", "Males circumcised at this Health Facility with a medical device  between15And19Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I18: Males circumcised and tested for HIV this month at this Health Facility between15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.setName("malePatientsCircomcisedAndTesedHIVbetween15And19Years");
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, null));
        malePatientsCircomcisedAndTesedHIVbetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween15And19YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I18", "Males circumcised and tested for HIV this month at this Health Facility between15And19Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I19:  Males circumcised and tested HIV positive this month at this Health Facility 15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween15And19YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I19", "Males circumcised and tested HIV positive this month at this Health Facility between10And14Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I20:  Males circumcised at this health facility who experienced one or more adverse events 15And19Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween15And19Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.setName("malePatientsCircomcisedWithAdverseEventsBetween15And19Years");
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween15And19Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween15And19Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween15And19YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween15And19YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween15And19Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I20", "Males circumcised at this Health Facility with a medical device between10And14Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween15And19YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //20-24 years

        //=====================================================
        // I21: Males circumcised at this Health Facility by surgical circumcision between20-24 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween20And24Years = patientWithAgeBetween(20, 24);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween20And24Years");
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween20And24YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I21", "Males circumcised at this Health Facility by surgical circumcision between20And24Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I22: Males circumcised at this Health Facility with a medical device  between20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween20And24Years");
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween20And24YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I22", "Males circumcised at this Health Facility with a medical device  between20And24Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I23: Males circumcised and tested for HIV this month at this Health Facility between20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.setName("malePatientsCircomcisedAndTesedHIVbetween20And24Years");
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, null));
        malePatientsCircomcisedAndTesedHIVbetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween20And24YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I23", "Males circumcised and tested for HIV this month at this Health Facility between20And24Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I24:  Males circumcised and tested HIV positive this month at this Health Facility 20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween20And24YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I24", "Males circumcised and tested HIV positive this month at this Health Facility between20And24Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I25:  Males circumcised at this health facility who experienced one or more adverse events 20-24 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween20And24Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.setName("malePatientsCircomcisedWithAdverseEventsBetween20And24Years");
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween20And24Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween20And24Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween20And24YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween20And24YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween20And24Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I25", "Males circumcised at this Health Facility with a medical device between20And24Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween20And24YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //25-29years

        //=====================================================
        // I26: Males circumcised at this Health Facility by surgical circumcision between25-29 years
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween25And29Years");
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween25And29YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I26", "Males circumcised at this Health Facility by surgical circumcision between25And29Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I27: Males circumcised at this Health Facility with a medical device  between25-29Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween20And29Years");
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween25And29YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I27", "Males circumcised at this Health Facility with a medical device  between25And29Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I28: Males circumcised and tested for HIV this month at this Health Facility between25-29Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.setName("malePatientsCircomcisedAndTesedHIVbetween25And29Years");
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween25And29YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I28", "Males circumcised and tested for HIV this month at this Health Facility between25And29Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I29:  Males circumcised and tested HIV positive this month at this Health Facility 25-29Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween25And29YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I29", "Males circumcised and tested HIV positive this month at this Health Facility between25And29Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I30:  Males circumcised at this health facility who experienced one or more adverse events 25-39Years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween25And29Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.setName("malePatientsCircomcisedWithAdverseEventsBetween20And24Years");
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween25And29Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween25And29Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween25And29YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween25And29YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween25And29Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I30", "Males circumcised at this Health Facility with a medical device between25And49Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween25And29YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        // 30-34years

        //=====================================================
        // I31: Males circumcised at this Health Facility by surgical circumcision between 30-34 years
        //=====================================================

        AgeCohortDefinition patientsWithAgeBetween30And34Years = patientWithAgeBetween(30, 34);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween30And34Years");
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween30And34YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I31", "Males circumcised at this Health Facility by surgical circumcision between30And34Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I32: Males circumcised at this Health Facility with a medical device  between30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween30And34Years");
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween30And34YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I32", "Males circumcised at this Health Facility with a medical device  Between30And34Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I33: Males circumcised and tested for HIV this month at this Health Facility between30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.setName("malePatientsCircomcisedAndTesedHIVbetween30And34Years");
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween30And34YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I33", "Males circumcised and tested for HIV this month at this Health Facility between30And34Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I34:  Males circumcised and tested HIV positive this month at this Health Facility 30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween30And34YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I34", "Males circumcised and tested HIV positive this month at this Health Facility between30And34Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I35:  Males circumcised at this health facility who experienced one or more adverse events 30-34 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween30And34Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.setName("malePatientsCircomcisedWithAdverseEventsBetween30And34Years");
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween30And34Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween30And34Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween30And34YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween30And34YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween30And34Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I35", "Males circumcised at this Health Facility with a medical device between30And34Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween30And34YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        // 35-39years

        //=====================================================
        // I36: Males circumcised at this Health Facility by surgical circumcision between35-39 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween35And39Years = patientWithAgeBetween(35, 39);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween35And39Years");
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween35And39YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I36", "Males circumcised at this Health Facility by surgical circumcision between35And39Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I37: Males circumcised at this Health Facility with a medical device between35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween35And39Years");
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween35And39YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I37", "Males circumcised at this Health Facility with a medical device  Between35And39Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I38: Males circumcised and tested for HIV this month at this Health Facility between35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.setName("malePatientsCircomcisedAndTesedHIVbetween35And39Years");
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween35And39YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I38", "Males circumcised and tested for HIV this month at this Health Facility between35And39Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I39:  Males circumcised and tested HIV positive this month at this Health Facility 35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween35And39YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I39", "Males circumcised and tested HIV positive this month at this Health Facility between30And34Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I40:  Males circumcised at this health facility who experienced one or more adverse events 35-39 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween35And39Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.setName("malePatientsCircomcisedWithAdverseEventsBetween35And39Years");
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween35And39Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween35And39Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween35And39YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween35And39YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween35And39Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I40", "Males circumcised at this Health Facility with a medical device between30And34Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween35And39YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        // 40-49years
        //=====================================================
        // I41: Males circumcised at this Health Facility by surgical circumcision between40-49 years
        //=====================================================
        AgeCohortDefinition patientsWithAgeBetween40And49Years = patientWithAgeBetween(40, 49);

        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodBetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.setName("malePatientsCircomcisedWithSurgicalMethodBetween40And49Years");
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodBetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodBetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodBetween40And49YearsIndicator",
                malePatientsCircomcisedWithSurgicalMethodBetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I41", "Males circumcised at this Health Facility by surgical circumcision between40And49Years", new Mapped(malePatientsCircomcisedWithSurgicalMethodBetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I42: Males circumcised at this Health Facility with a medical device between40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceBetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.setName("malePatientsCircomcisedWithMedicalDeviceBetween40And49Years");
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceBetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceBetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceBetween40And49YearsIndicator",
                malePatientsCircomcisedWithMedicalDeviceBetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I42", "Males circumcised at this Health Facility with a medical device  Between35And39Years", new Mapped(malePatientsCircomcisedWithMedicalDeviceBetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // I43: Males circumcised and tested for HIV this month at this Health Facility between40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVbetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.setName("malePatientsCircomcisedAndTesedHIVbetween40And49Years");
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVbetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVbetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVbetween40And49YearsIndicator",
                malePatientsCircomcisedAndTesedHIVbetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I43", "Males circumcised and tested for HIV this month at this Health Facility between40And49Years", new Mapped(malePatientsCircomcisedAndTesedHIVbetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I44:  Males circumcised and tested HIV positive this month at this Health Facility 40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.setName("malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years");
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositivebetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositivebetween40And49YearsIndicator",
                malePatientsCircomcisedAndTesedHIVPositivebetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I44", "Males circumcised and tested HIV positive this month at this Health Facility between40And49Years", new Mapped(malePatientsCircomcisedAndTesedHIVPositivebetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I45:  Males circumcised at this health facility who experienced one or more adverse events 40-49 years
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsBetween40And49Years = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.setName("malePatientsCircomcisedWithAdverseEventsBetween40And49Years");
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBetween40And49Years, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsBetween40And49Years.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsBetween40And49YearsIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsBetween40And49YearsIndicator",
                malePatientsCircomcisedWithAdverseEventsBetween40And49Years, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I45", "Males circumcised at this Health Facility with a medical device between40And49Years", new Mapped(malePatientsCircomcisedWithAdverseEventsBetween40And49YearsIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        // 50<years
        //=====================================================
        // I46: Males circumcised at this Health Facility by surgical circumcision With 50Years And Above
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.setName("malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove");
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsision, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAboveIndicator",
                malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I46", "Males circumcised at this Health Facility by surgical circumcision With 50Years And Above", new Mapped(malePatientsCircomcisedWithSurgicalMethodWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I47: Males circumcised at this Health Facility with a medical device  With 50Years And Above
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.setName("malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove");
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumsisionWithMedicalDevice, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAboveIndicator",
                malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I47", "Males circumcised at this Health Facility with a medical device  With 50Years And Above", new Mapped(malePatientsCircomcisedWithMedicalDeviceWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I48: Males circumcised and tested for HIV this month at this Health Facility With 50Years And Above
        //=====================================================
        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.setName("malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove");
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVWith50YearsAndAboveIndicator",
                malePatientsCircomcisedAndTesedHIVWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I48", "Males circumcised and tested for HIV this month at this Health Facility With 50Years And Above", new Mapped(malePatientsCircomcisedAndTesedHIVWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I49:  Males circumcised and tested HIV positive this month at this Health Facility With 50Years And Above
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.setName("malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove");
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedPrepexAndSurgical, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAboveIndicator",
                malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I49", "Males circumcised and tested HIV positive this month at this Health Facility With 50Years And Above", new Mapped(malePatientsCircomcisedAndTesedHIVPositiveWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // I50:  Males circumcised at this health facility who experienced one or more adverse events With 50Years And Above
        //=====================================================

        CompositionCohortDefinition malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove = new CompositionCohortDefinition();
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.setName("malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove");
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.getSearches().put("1", new Mapped<CohortDefinition>(maleCircumcisedAtHealthFacility, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.getSearches().put("2", new Mapped<CohortDefinition>(patientsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove.setCompositionString("1 and 2");
        CohortIndicator malePatientsCircomcisedWithAdverseEventsWith50YearsAndAboveIndicator = Indicators.newCohortIndicator("malePatientsCircomcisedWithAdverseEventsWith50YearsAndAboveIndicator",
                malePatientsCircomcisedWithAdverseEventsWith50YearsAndAbove, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("I50", "Males circumcised at this Health Facility with a medical device With 50Years And Above", new Mapped(malePatientsCircomcisedWithAdverseEventsWith50YearsAndAboveIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        return dsd;
}

    private CohortIndicatorDataSetDefinition NutritionConsultationDataElements(CohortIndicatorDataSetDefinition dsd) {

        //=====================================================
        //  H:Nutrition Consultation Data Elements
        //=====================================================
        //=====================================================
        //  H01:Number of HIV+ pediatric patients (age < 5 years) with severe malnutrition this month
        // =====================================================

        AgeCohortDefinition patientsWithAgeBellow5Year = new AgeCohortDefinition();
        patientsWithAgeBellow5Year.setName("patientsWithAgeBellow5Year");
        patientsWithAgeBellow5Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBellow5Year.setMaxAge(5);
        patientsWithAgeBellow5Year.setMaxAgeUnit(DurationUnit.YEARS);

        CodedObsCohortDefinition patientWithResultsOfHIVPositiveObs = new CodedObsCohortDefinition();
        patientWithResultsOfHIVPositiveObs.setName("PatientWithHIVPositiveObs");
        patientWithResultsOfHIVPositiveObs.setQuestion(resultOfHIVTest);
        patientWithResultsOfHIVPositiveObs.addValue(positive);
        patientWithResultsOfHIVPositiveObs.setOperator(SetComparator.IN);
        patientWithResultsOfHIVPositiveObs.setTimeModifier(TimeModifier.LAST);


        SqlCohortDefinition patientWithSevereMalnutrition = new SqlCohortDefinition();
        patientWithSevereMalnutrition.setName("patientWithSevereMalnutrition");
        patientWithSevereMalnutrition.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + NutritionStatusMonitoring.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + Degreeofmalnutrition.getConceptId() + " and o.value_coded=" + MALNUTRITIONSEVERE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWithSevereMalnutrition.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithSevereMalnutrition.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVPatientsBelow5YearWithSevereMalnutrition = new CompositionCohortDefinition();
        HIVPatientsBelow5YearWithSevereMalnutrition.setName("HIVPatientsBelow5YearWithSevereMalnutrition");
        HIVPatientsBelow5YearWithSevereMalnutrition.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutrition.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutrition.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutrition.getSearches().put("1", new Mapped<CohortDefinition>(patientWithResultsOfHIVPositiveObs, null));
        HIVPatientsBelow5YearWithSevereMalnutrition.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBellow5Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPatientsBelow5YearWithSevereMalnutrition.getSearches().put("3", new Mapped<CohortDefinition>(patientWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPatientsBelow5YearWithSevereMalnutrition.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVPatientsBelow5YearWithSevereMalnutritionIndicator = Indicators.newCohortIndicator("HIVPatientsBelow5YearWithSevereMalnutritionIndicator",
                HIVPatientsBelow5YearWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H01", "Number of HIV positive bellow 5 years with severe malnutrition", new Mapped(HIVPatientsBelow5YearWithSevereMalnutritionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H2:Number of HIV+ pediatric patients (age < 5 years) with severe malnutrition who received therapeutic or nutritional supplementation this month
        //=====================================================

        CodedObsCohortDefinition patientWithNutritionalTreatmentTypeObs = new CodedObsCohortDefinition();
        patientWithNutritionalTreatmentTypeObs.setName("patientWithNutritionalTreatmentTypeObs");
        patientWithNutritionalTreatmentTypeObs.setQuestion(NutritionalTreatmentType);
        patientWithNutritionalTreatmentTypeObs.addEncounterType(NutritionEncounterType);
        patientWithNutritionalTreatmentTypeObs.setOperator(SetComparator.IN);
        patientWithNutritionalTreatmentTypeObs.setTimeModifier(TimeModifier.LAST);
        patientWithNutritionalTreatmentTypeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic = new CompositionCohortDefinition();
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.setName("HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic");
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.getSearches().put("1", new Mapped<CohortDefinition>(patientWithNutritionalTreatmentTypeObs, null));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBellow5Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.getSearches().put("3", new Mapped<CohortDefinition>(patientWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic.setCompositionString("1 and 2 and 3");

        CohortIndicator HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeuticIndicator = Indicators.newCohortIndicator("HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeuticIndicator",
                HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H02", "Number of HIV positive bellow 5 years with severe malnutrition who received therapeutic", new Mapped(HIVPatientsBelow5YearWithSevereMalnutritionReceivedTherapeuticIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H3:Number of HIV+ patients (age < 15 years) who received therapeutic or nutritional supplementation this month
        //=====================================================
        AgeCohortDefinition patientsWithAgeBellow15Year = new AgeCohortDefinition();
        patientsWithAgeBellow15Year.setName("patientsWithAgeBellow15Year");
        patientsWithAgeBellow15Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeBellow15Year.setMaxAge(15);
        patientsWithAgeBellow15Year.setMaxAgeUnit(DurationUnit.YEARS);

        SqlCohortDefinition patientWhoReceivedTherapeutic = new SqlCohortDefinition();
        patientWhoReceivedTherapeutic.setName("patientWhoReceivedTherapeutic");
        patientWhoReceivedTherapeutic.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + NutritionStatusMonitoring.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=13325 and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWhoReceivedTherapeutic.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedTherapeutic.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition maleHIVPatientsBelow15YearWithSevereMalnutrition = new CompositionCohortDefinition();
        maleHIVPatientsBelow15YearWithSevereMalnutrition.setName("maleHIVPatientsBelow15YearWithSevereMalnutrition");
        maleHIVPatientsBelow15YearWithSevereMalnutrition.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.getSearches().put("1", new Mapped<CohortDefinition>(patientWithResultsOfHIVPositiveObs, null));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeBellow15Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.getSearches().put("3", new Mapped<CohortDefinition>(patientWhoReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleHIVPatientsBelow15YearWithSevereMalnutrition.setCompositionString("1 and 2 and 3");

        CohortIndicator maleHIVPatientsBelow15YearWithSevereMalnutritionIndicator = Indicators.newCohortIndicator("maleHIVPatientsBelow15YearWithSevereMalnutritionIndicator",
                maleHIVPatientsBelow15YearWithSevereMalnutrition, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H03", "Number of HIV positive bellow 15 years with severe malnutrition", new Mapped(maleHIVPatientsBelow15YearWithSevereMalnutritionIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H4:Number of HIV+ patients (age 15+ years) who received therapeutic or nutritional supplementation this month
        //=====================================================

        AgeCohortDefinition patientsWithAgeAbove15Year = new AgeCohortDefinition();
        patientsWithAgeAbove15Year.setName("patientsWithAgeAbove15Year");
        patientsWithAgeAbove15Year.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientsWithAgeAbove15Year.setMinAge(15);
        patientsWithAgeAbove15Year.setMaxAgeUnit(DurationUnit.YEARS);

        CompositionCohortDefinition maleHIVPatientsAbove15YearWhoReceivedTherapeutic = new CompositionCohortDefinition();
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.setName("maleHIVPatientsAbove15YearWhoReceivedTherapeutic");
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.getSearches().put("1", new Mapped<CohortDefinition>(patientWithResultsOfHIVPositiveObs, null));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.getSearches().put("2", new Mapped<CohortDefinition>(patientsWithAgeAbove15Year, ParameterizableUtil.createParameterMappings("effectiveDate=${effectiveDate}")));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.getSearches().put("3", new Mapped<CohortDefinition>(patientWhoReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        maleHIVPatientsAbove15YearWhoReceivedTherapeutic.setCompositionString("1 and 2 and 3");

        CohortIndicator maleHIVPatientsAbove15YearWhoReceivedTherapeuticIndicator = Indicators.newCohortIndicator("maleHIVPatientsAbove15YearWhoReceivedTherapeuticIndicator",
                maleHIVPatientsAbove15YearWhoReceivedTherapeutic, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H04", "Number of HIV positive bellow 15 years who received therapeutic or nutritional supplementation this month", new Mapped(maleHIVPatientsAbove15YearWhoReceivedTherapeuticIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        //  H5:Number of patients who received a follow-up and adherence counselling
        //=====================================================

        SqlCohortDefinition patientWhoReceivedFollowup = new SqlCohortDefinition();
        patientWhoReceivedFollowup.setName("patientWhoReceivedFollowup");
        patientWhoReceivedFollowup.setQuery("select  grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + NutritionFollowup.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id) grouped, obs o where grouped.encounter_id=o.encounter_id and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        patientWhoReceivedFollowup.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedFollowup.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWhoReceivedFollowupAndAdherenceCounselling = new CompositionCohortDefinition();
        patientWhoReceivedFollowupAndAdherenceCounselling.setName("patientWhoReceivedFollowupAndAdherenceCounselling");
        patientWhoReceivedFollowupAndAdherenceCounselling.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWhoReceivedFollowupAndAdherenceCounselling.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWhoReceivedFollowupAndAdherenceCounselling.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWhoReceivedFollowupAndAdherenceCounselling.getSearches().put("1", new Mapped<CohortDefinition>(patientWhoReceivedFollowup, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWhoReceivedFollowupAndAdherenceCounselling.setCompositionString("1");

        CohortIndicator patientWhoReceivedFollowupAndAdherenceCounsellingIndicator = Indicators.newCohortIndicator("patientWhoReceivedFollowupAndAdherenceCounsellingIndicator",
                patientWhoReceivedFollowupAndAdherenceCounselling, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));

        dsd.addColumn("H05", "Number of patients who received a follow-up and adherence counselling", new Mapped(patientWhoReceivedFollowupAndAdherenceCounsellingIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        return dsd;
    }

    private CohortIndicatorDataSetDefinition discordantCouples(CohortIndicatorDataSetDefinition dsd) {

        //=====================================================
        // L: Discordant Couples
        //=====================================================
        //=====================================================
        // L01: New discordant couples registered
        //=====================================================

        SqlCohortDefinition NewDiscordantCouplesRegistered = new SqlCohortDefinition();
        NewDiscordantCouplesRegistered.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + Newdiscordentcouple.getConceptId() + " and o.value_coded=" + YES.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        NewDiscordantCouplesRegistered.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        NewDiscordantCouplesRegistered.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesNewlyRegistered = new CompositionCohortDefinition();
        DiscordantCouplesNewlyRegistered.setName("DiscordantCouplesNewlyRegistered");
        DiscordantCouplesNewlyRegistered.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesNewlyRegistered.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesNewlyRegistered.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesNewlyRegistered.getSearches().put("1", new Mapped<CohortDefinition>(NewDiscordantCouplesRegistered, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesNewlyRegistered.setCompositionString("1");

        CohortIndicator DiscordantCouplesNewlyRegisteredIndicator = Indicators.newCohortIndicator("DiscordantCouplesNewlyRegisteredIndicator",
                DiscordantCouplesNewlyRegistered, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L01", "New discordant couples registered", new Mapped(DiscordantCouplesNewlyRegisteredIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L02: Ancient discordant couples retraced
        //=====================================================

        SqlCohortDefinition OldDiscordantCouplesRetraced = new SqlCohortDefinition();
        OldDiscordantCouplesRetraced.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + Newdiscordentcouple.getConceptId() + " and o.value_coded=" + RETRACED.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        OldDiscordantCouplesRetraced.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        OldDiscordantCouplesRetraced.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesNewlyRetraced = new CompositionCohortDefinition();
        DiscordantCouplesNewlyRetraced.setName("DiscordantCouplesNewlyRetraced");
        DiscordantCouplesNewlyRetraced.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesNewlyRetraced.getSearches().put("1", new Mapped<CohortDefinition>(OldDiscordantCouplesRetraced, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesNewlyRetraced.setCompositionString("1");

        CohortIndicator DiscordantCouplesNewlyRetracedIndicator = Indicators.newCohortIndicator("DiscordantCouplesNewlyRetracedIndicator",
                DiscordantCouplesNewlyRetraced, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L02", "Ancient discordant couples retraced", new Mapped(DiscordantCouplesNewlyRetracedIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L03: Discordant couples expected for re-testing
        //=====================================================

        SqlCohortDefinition DiscordantCouplesExpectedForRetesting = new SqlCohortDefinition();
        DiscordantCouplesExpectedForRetesting.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + AppointmentDate.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        DiscordantCouplesExpectedForRetesting.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesExpectedForRetesting.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition patientWithDiscordantCouplesNewlyRetraced = new CompositionCohortDefinition();
        patientWithDiscordantCouplesNewlyRetraced.setName("patientWithDiscordantCouplesNewlyRetraced");
        patientWithDiscordantCouplesNewlyRetraced.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        patientWithDiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        patientWithDiscordantCouplesNewlyRetraced.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        patientWithDiscordantCouplesNewlyRetraced.getSearches().put("1", new Mapped<CohortDefinition>(DiscordantCouplesExpectedForRetesting, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        patientWithDiscordantCouplesNewlyRetraced.setCompositionString("1");

        CohortIndicator patientWithDiscordantCouplesNewlyRetracedIndicator = Indicators.newCohortIndicator("patientWithDiscordantCouplesNewlyRetracedIndicator",
                patientWithDiscordantCouplesNewlyRetraced, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L03", "Discordant couples expected for re-testing", new Mapped(patientWithDiscordantCouplesNewlyRetracedIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L04: Discordant couples that came back for follow-up counseling re-testing
        //=====================================================

        SqlCohortDefinition CouplesCameBackForFollowUp = new SqlCohortDefinition();
        CouplesCameBackForFollowUp.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + AppointmentDate.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        CouplesCameBackForFollowUp.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesCameBackForFollowUp.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition CouplesCameBackForFollowUpCounselingRetesting = new CompositionCohortDefinition();
        CouplesCameBackForFollowUpCounselingRetesting.setName("SerodiscordantCouplesFollowedAtHealthFacility");
        CouplesCameBackForFollowUpCounselingRetesting.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        CouplesCameBackForFollowUpCounselingRetesting.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesCameBackForFollowUpCounselingRetesting.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        CouplesCameBackForFollowUpCounselingRetesting.getSearches().put("1", new Mapped<CohortDefinition>(CouplesCameBackForFollowUp, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        CouplesCameBackForFollowUpCounselingRetesting.setCompositionString("1");

        CohortIndicator CouplesCameBackForFollowUpCounselingRetestingIndicator = Indicators.newCohortIndicator("CouplesCameBackForFollowUpCounselingRetestingIndicator",
                CouplesCameBackForFollowUpCounselingRetesting, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L04", "Discordant couples that came back for follow-up counseling re-testing", new Mapped(CouplesCameBackForFollowUpCounselingRetestingIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");


        //=====================================================
        // L05: Seroconverted cases
        //====================================================

        SqlCohortDefinition SeroconvertedCase = new SqlCohortDefinition();
        SeroconvertedCase.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + RESULTOFHIVTEST.getConceptId() + " and o.value_coded=" + POSITIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        SeroconvertedCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        SeroconvertedCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithSeroconvertedCase = new CompositionCohortDefinition();
        PatientWithSeroconvertedCase.setName("PatientWithSeroconvertedCase");
        PatientWithSeroconvertedCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithSeroconvertedCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithSeroconvertedCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithSeroconvertedCase.getSearches().put("1", new Mapped<CohortDefinition>(SeroconvertedCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithSeroconvertedCase.setCompositionString("1");

        CohortIndicator PatientWithSeroconvertedCaseIndicator = Indicators.newCohortIndicator("PatientWithSeroconvertedCaseIndicator",
                PatientWithSeroconvertedCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L05", "Seroconverted cases", new Mapped(PatientWithSeroconvertedCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L06: Discordant couples using Family Planning
        //====================================================

        SqlCohortDefinition CouplesUsingFamilyPlanning = new SqlCohortDefinition();
        CouplesUsingFamilyPlanning.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + PATIENTUSINGFAMILYPLANNING.getConceptId() + " and o.value_coded=" + TRUE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        CouplesUsingFamilyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesUsingFamilyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesUsingFamilyPlanning = new CompositionCohortDefinition();
        DiscordantCouplesUsingFamilyPlanning.setName("DiscordantCouplesUsingFamilyPlanning");
        DiscordantCouplesUsingFamilyPlanning.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesUsingFamilyPlanning.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesUsingFamilyPlanning.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesUsingFamilyPlanning.getSearches().put("1", new Mapped<CohortDefinition>(CouplesUsingFamilyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesUsingFamilyPlanning.setCompositionString("1");

        CohortIndicator DiscordantCouplesUsingFamilyPlanningIndicator = Indicators.newCohortIndicator("DiscordantCouplesUsingFamilyPlanningIndicator",
                DiscordantCouplesUsingFamilyPlanning, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L06", "Discordant couples using Family Planning", new Mapped(DiscordantCouplesUsingFamilyPlanningIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L07: HIV positive partners in discordant couples under ART
        //=====================================================
        SqlCohortDefinition CouplesUnderART = new SqlCohortDefinition();
        CouplesUnderART.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + PositivepartneronARV.getConceptId() + " and o.value_coded=" + TRUE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        CouplesUnderART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        CouplesUnderART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition DiscordantCouplesUnderART = new CompositionCohortDefinition();
        DiscordantCouplesUnderART.setName("DiscordantCouplesUnderART");
        DiscordantCouplesUnderART.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        DiscordantCouplesUnderART.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        DiscordantCouplesUnderART.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        DiscordantCouplesUnderART.getSearches().put("1", new Mapped<CohortDefinition>(CouplesUnderART, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        DiscordantCouplesUnderART.setCompositionString("1");

        CohortIndicator DiscordantCouplesUnderARTIndicator = Indicators.newCohortIndicator("DiscordantCouplesUnderARTIndicator",
                DiscordantCouplesUnderART, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L07", "HIV positive partners in discordant couples under ART", new Mapped(DiscordantCouplesUnderARTIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // L08: Serodiscordant couples followed at Health Facility
        //=====================================================
        SqlCohortDefinition SerodiscordantCase = new SqlCohortDefinition();
        SerodiscordantCase.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + DiscordentCoupleForm.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + RESULTOFHIVTEST.getConceptId() + " and o.value_coded=" + NEGATIVE.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        SerodiscordantCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        SerodiscordantCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithSerodiscordantCase = new CompositionCohortDefinition();
        PatientWithSerodiscordantCase.setName("PatientWithSeroconvertedCase");
        PatientWithSerodiscordantCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithSerodiscordantCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithSerodiscordantCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithSerodiscordantCase.getSearches().put("1", new Mapped<CohortDefinition>(SerodiscordantCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithSerodiscordantCase.setCompositionString("1");

        CohortIndicator PatientWithSerodiscordantCaseIndicator = Indicators.newCohortIndicator("PatientWithSerodiscordantCaseIndicator",
                PatientWithSerodiscordantCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("L08", "Seroconverted cases", new Mapped(PatientWithSerodiscordantCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        return dsd;
    }


    private CohortIndicatorDataSetDefinition postExposureProphylaxis(CohortIndicatorDataSetDefinition dsd) {
        //=====================================================
        // M: PEP - Post Exposure Prophylaxis
        //=====================================================
        //=====================================================
        // M01: Clients at risk of HIV infection as a result of occupational exposure
        //====================================================
        SqlCohortDefinition OccupationalExposureCase = new SqlCohortDefinition();
        OccupationalExposureCase.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + Prophylaxy.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + ClassificationofExposure.getConceptId() + " and o.value_coded=" + OccupationalExposure.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        OccupationalExposureCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        OccupationalExposureCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithOccupationalExposureCase = new CompositionCohortDefinition();
        PatientWithOccupationalExposureCase.setName("PatientWithOccupationalExposureCase");
        PatientWithOccupationalExposureCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithOccupationalExposureCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithOccupationalExposureCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithOccupationalExposureCase.getSearches().put("1", new Mapped<CohortDefinition>(OccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithOccupationalExposureCase.setCompositionString("1");

        CohortIndicator PatientWithOccupationalExposureCaseIndicator = Indicators.newCohortIndicator("PatientWithOccupationalExposureCaseIndicator",
                PatientWithOccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M01", "Clients at risk of HIV infection as a result of occupational exposure", new Mapped(PatientWithOccupationalExposureCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M02: Clients at risk of HIV infection as a result of rape/sexual assault
        //=====================================================
        SqlCohortDefinition HIVInfectionResultOfRape = new SqlCohortDefinition();
        HIVInfectionResultOfRape.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + Prophylaxy.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + ClassificationofExposure.getConceptId() + " and o.value_coded=" + RapeSexualassault.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVInfectionResultOfRape.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfRape.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVInfectionResultOfRapeCase = new CompositionCohortDefinition();
        HIVInfectionResultOfRapeCase.setName("HIVInfectionResultOfRapeCase");
        HIVInfectionResultOfRapeCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVInfectionResultOfRapeCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfRapeCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVInfectionResultOfRapeCase.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfRape, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVInfectionResultOfRapeCase.setCompositionString("1");

        CohortIndicator HIVInfectionResultOfRapeCaseIndicator = Indicators.newCohortIndicator("HIVInfectionResultOfRapeCaseIndicator",
                HIVInfectionResultOfRapeCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M02", "Clients at risk of HIV infection as a result of rape/sexual assault", new Mapped(HIVInfectionResultOfRapeCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M03: Clients at risk of HIV infection as a result of other non-occupational exposure
        //=====================================================
        SqlCohortDefinition HIVInfectionResultOfNonOccupationalExposure = new SqlCohortDefinition();
        HIVInfectionResultOfNonOccupationalExposure.setQuery("select grouped.patient_id from (select * from (select * from encounter e WHERE form_id =" + Prophylaxy.getFormId() + " and voided=0 order by e.encounter_datetime asc) ordered group by ordered.patient_id)grouped, obs o where grouped.encounter_id=o.encounter_id and o.concept_id=" + ClassificationofExposure.getConceptId() + " and o.value_coded=" + NonOccupationalExposure.getConceptId() + " and grouped.encounter_datetime >= :onOrAfter and grouped.encounter_datetime <= :onOrBefore");
        HIVInfectionResultOfNonOccupationalExposure.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfNonOccupationalExposure.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition HIVInfectionResultOfNonOccupationalExposureCase = new CompositionCohortDefinition();
        HIVInfectionResultOfNonOccupationalExposureCase.setName("HIVInfectionResultOfNonOccupationalExposureCase");
        HIVInfectionResultOfNonOccupationalExposureCase.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        HIVInfectionResultOfNonOccupationalExposureCase.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        HIVInfectionResultOfNonOccupationalExposureCase.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        HIVInfectionResultOfNonOccupationalExposureCase.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfNonOccupationalExposure, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        HIVInfectionResultOfNonOccupationalExposureCase.setCompositionString("1");

        CohortIndicator HIVInfectionResultOfNonOccupationalExposureCaseIndicator = Indicators.newCohortIndicator("HIVInfectionResultOfNonOccupationalExposureCaseIndicator",
                HIVInfectionResultOfNonOccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M03", "Clients at risk of HIV infection as a result of other non-occupational exposure", new Mapped(HIVInfectionResultOfNonOccupationalExposureCaseIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M04: Clients at risk of HIV infection as a result of occupational exposure who received PEP
        //=====================================================
        CodedObsCohortDefinition patientWithHTCVisitEncounterTypeObs = new CodedObsCohortDefinition();
        patientWithHTCVisitEncounterTypeObs.setName("patientWithHTCVisitEncounterTypeObs");
        patientWithHTCVisitEncounterTypeObs.setQuestion(PREVIOUSTREATMENTREGIMEN);
        patientWithHTCVisitEncounterTypeObs.addEncounterType(HTCVisitEncounterType);
        patientWithHTCVisitEncounterTypeObs.setOperator(SetComparator.IN);
        patientWithHTCVisitEncounterTypeObs.setTimeModifier(TimeModifier.LAST);
        patientWithHTCVisitEncounterTypeObs.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));

        CompositionCohortDefinition PatientWithOccupationalExposureCaseWhoReceivedPEP = new CompositionCohortDefinition();
        PatientWithOccupationalExposureCaseWhoReceivedPEP.setName("PatientWithOccupationalExposureCaseWhoReceivedPEP");
        PatientWithOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.getSearches().put("1", new Mapped<CohortDefinition>(OccupationalExposureCase, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHTCVisitEncounterTypeObs, null));
        PatientWithOccupationalExposureCaseWhoReceivedPEP.setCompositionString("1 and 2");

        CohortIndicator PatientWithOccupationalExposureCaseWhoReceivedPEPIndicator = Indicators.newCohortIndicator("PatientWithOccupationalExposureCaseWhoReceivedPEPIndicator",
                PatientWithOccupationalExposureCaseWhoReceivedPEP, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M04", "Clients at risk of HIV infection as a result of occupational exposure who received PEP", new Mapped(PatientWithOccupationalExposureCaseWhoReceivedPEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M05: Clients at risk of HIV infection as a result of rape/sexual assault who received PEP
        //=====================================================
        CompositionCohortDefinition PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP = new CompositionCohortDefinition();
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.setName("PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP");
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfRape, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHTCVisitEncounterTypeObs, null));
        PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP.setCompositionString("1 and 2");

        CohortIndicator PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEPIndicator = Indicators.newCohortIndicator("PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEPIndicator",
                PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEP, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M05", "Clients at risk of HIV infection as a result of rape/sexual assault who received PEP", new Mapped(PatientWithHIVInfectionResultOfRapeCaseWhoReceivedPEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        //=====================================================
        // M06: Clients at risk of HIV infection as a result of other non-occupational exposure who received PEP
        //=====================================================

        CompositionCohortDefinition PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP = new CompositionCohortDefinition();
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.setName("PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP");
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("effectiveDate", "effectiveDate", Date.class));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrAfter", "onOrAfter", Date.class));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.addParameter(new Parameter("onOrBefore", "onOrBefore", Date.class));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.getSearches().put("1", new Mapped<CohortDefinition>(HIVInfectionResultOfNonOccupationalExposure, ParameterizableUtil.createParameterMappings("onOrAfter=${onOrAfter},onOrBefore=${onOrBefore}")));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.getSearches().put("2", new Mapped<CohortDefinition>(patientWithHTCVisitEncounterTypeObs, null));
        PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP.setCompositionString("1 and 2");

        CohortIndicator PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEPIndicator = Indicators.newCohortIndicator("PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEPIndicator",
                PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEP, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate},effectiveDate=${endDate}"));
        dsd.addColumn("M06", "Clients at risk of HIV infection as a result of other non-occupational exposure who received PEP", new Mapped(PatientWithHIVInfectionResultOfNonOccupationalExposureCaseWhoReceivedPEPIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate},location=${location}")), "");

        return dsd;
    }
}


