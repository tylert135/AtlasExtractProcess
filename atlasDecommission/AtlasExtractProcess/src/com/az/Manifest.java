package com.az;

public class Manifest {
	
	public static int SOURCESYSTEM = 0;
	public static int TITLE = 1;
	public static int CLASSIFICATION = 2;
	public static int SECURITY = 3;
	public static int RECORDFORM = 4;
	public static int AUTHOR = 5;
	public static int DATECREATED = 6;
	public static int LANGUAGE = 7;
	public static int RELATIVEPATH = 8;
	public static int DATECLOSED = 9;
	public static int FILENAME = 10;
	public static int EVENTTRIGGER = 11;
	public static int LEGALHOLDNAME = 12;
	public static int PRODUCT = 13;
	public static int SUBSTANCEREFERENCE = 14;
	public static int PHYSICALLOCATIONDESCRIPTION = 15;
	public static int SIGNER = 16;
	public static int SIGNERID = 17;
	public static int SIGNATUREMETHOD = 18;
	public static int SIGNATUREVALUE = 19;
	public static int SIGNATUREENCODING = 20;
	public static int SIGNATUREPUBLICKEY = 21;
	public static int SIGNATUREDATE = 22;
	public static int ADVERSEEVENTREPORTNO = 23;
	public static int ASTRAZENECASITE = 24;
	public static int AUDIENCE = 25;
	public static int AUDITRECORDS = 26;
	public static int AUTHENTICITYCOMMENTS = 27;
	public static int AUTHENTICITYSTANDARD = 28;
	public static int AUTHORITYREFERENCENO = 29;
	public static int BATCHNO = 30;
	public static int CONTRACTNO = 31;
	public static int COUNTRY = 32;
	public static int DEPARTMENT = 33;
	public static int DOSAGEFORM = 34;
	public static int DRAWINGNO = 35;
	public static int DRUGPRODUCTID = 36;
	public static int DRUGSUBSTANCENO = 37;
	public static int EMPLOYEENAME = 38;
	public static int EMPLOYEENO = 39;
	public static int ENGINEER = 40;
	public static int EQUIPMENTID = 41;
	public static int HEALTHCAREPROFESSIONAL = 42;
	public static int INTERMEDIATEPRODUCTCODE = 43;
	public static int INVESTIGATIONALSITE = 44;
	public static int INVESTIGATOR = 45;
	public static int LABORATORYNOTEBOOKNO = 46;
	public static int MARKETINGCOMPANY = 47;
	public static int NOTES = 48;
	public static int LEGACYRECORDNUMBER = 49;
	public static int ORIGINALLOCATION = 50;
	public static int PATENTID = 51;
	public static int PATIENT = 52;
	public static int PHYSICALLOCATIONREFERENCE = 53;
	public static int POLICYNO = 54;
	public static int PROMOTIONALID = 55;
	public static int SALESREPRESENTATIVE = 56;
	public static int SIGNATUREVALIDATION = 57;
	public static int SOPNO = 58;
	public static int STUDYCODE = 59;
	public static int SUBJECT = 60;
	public static int SYSTEMID = 61;
	public static int TAXYEAR = 62;
	public static int THERAPEUTICAREA = 63;
	public static int VENDOR = 64;
	
	private String sourceSystem;
	private String title;
	private String classification;
	private String security;
	private String recordForm;
	private String author;
	private String dateCreated;
	private String language;
	private String relativePath;
	private String dateClosed;
	private String fileName;
	private String eventTrigger;
	private String legalHoldName;
	private String product;
	private String substanceReference;
	private String physicalLocationDescription;
	private String signer;
	private String signerID;
	private String signatureMethod;
	private String signatureValue;
	private String signatureEncoding;
	private String signaturePublicKey;
	private String signatureDate;
	private String adverseEventReportNo;
	private String astraZenecaSite;
	private String audience;
	private String auditRecords;
	private String authenticityComments;
	private String authenticityStandard;
	private String authorityReferenceNo;
	private String batchNo;
	private String contractNo;
	private String country;
	private String department;
	private String dosageForm;
	private String drawingNo;
	private String drugProductID;
	private String drugSubstanceNo;
	private String employeeName;
	private String employeeNo;
	private String engineer;
	private String equipmentID;
	private String healthcareProfessional;
	private String intermediateProductCode;
	private String investigationalSite;
	private String investigator;
	private String laboratoryNotebookNo;
	private String marketingCompany;
	private String notes;
	private String legacyRecordNumber;
	private String originalLocation;
	private String patentID;
	private String patient;
	private String physicalLocationReference;
	private String policyNo;
	private String promotionalID;
	private String salesRepresentative;
	private String signatureValidation;
	private String sopNo;
	private String studyCode;
	private String subject;
	private String systemID;
	private String taxYear;
	private String therapeuticArea;
	private String vendor;

	public Manifest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manifest (String[] manifestData) {
		super();
		this.sourceSystem = manifestData[SOURCESYSTEM];
		this.title = manifestData[TITLE];
		this.classification = manifestData[CLASSIFICATION];
		this.security = manifestData[SECURITY];
		this.recordForm = manifestData[RECORDFORM];
		this.author = manifestData[AUTHOR];
		this.dateCreated = manifestData[DATECREATED];
		this.language = manifestData[LANGUAGE];
		this.relativePath = manifestData[RELATIVEPATH];
		this.dateClosed = manifestData[DATECLOSED];
		this.fileName = manifestData[FILENAME];
		this.eventTrigger = manifestData[EVENTTRIGGER];
		this.legalHoldName = manifestData[LEGALHOLDNAME];
		this.product = manifestData[PRODUCT];
		this.substanceReference = manifestData[SUBSTANCEREFERENCE];
		this.physicalLocationDescription = manifestData[PHYSICALLOCATIONDESCRIPTION];
		this.signer = manifestData[SIGNER];
		this.signerID = manifestData[SIGNERID];
		this.signatureMethod = manifestData[SIGNATUREMETHOD];
		this.signatureValue = manifestData[SIGNATUREVALUE];
		this.signatureEncoding = manifestData[SIGNATUREENCODING];
		this.signaturePublicKey = manifestData[SIGNATUREPUBLICKEY];
		this.signatureDate = manifestData[SIGNATUREDATE];
		this.adverseEventReportNo = manifestData[ADVERSEEVENTREPORTNO];
		this.astraZenecaSite = manifestData[ASTRAZENECASITE];
		this.audience = manifestData[AUDIENCE];
		this.auditRecords = manifestData[AUDITRECORDS];
		this.authenticityComments = manifestData[AUTHENTICITYCOMMENTS];
		this.authenticityStandard = manifestData[AUTHENTICITYSTANDARD];
		this.authorityReferenceNo = manifestData[AUTHORITYREFERENCENO];
		this.batchNo = manifestData[BATCHNO];
		this.contractNo = manifestData[CONTRACTNO];
		this.country = manifestData[COUNTRY];
		this.department = manifestData[DEPARTMENT];
		this.dosageForm = manifestData[DOSAGEFORM];
		this.drawingNo = manifestData[DRAWINGNO];
		this.drugProductID = manifestData[DRUGPRODUCTID];
		this.drugSubstanceNo = manifestData[DRUGSUBSTANCENO];
		this.employeeName = manifestData[EMPLOYEENAME];
		this.employeeNo = manifestData[EMPLOYEENO];
		this.engineer = manifestData[ENGINEER];
		this.equipmentID = manifestData[EQUIPMENTID];
		this.healthcareProfessional = manifestData[HEALTHCAREPROFESSIONAL];
		this.intermediateProductCode = manifestData[INTERMEDIATEPRODUCTCODE];
		this.investigationalSite = manifestData[INVESTIGATIONALSITE];
		this.investigator = manifestData[INVESTIGATOR];
		this.laboratoryNotebookNo = manifestData[LABORATORYNOTEBOOKNO];
		this.marketingCompany = manifestData[MARKETINGCOMPANY];
		this.notes = manifestData[NOTES];
		this.legacyRecordNumber = manifestData[LEGACYRECORDNUMBER];
		this.originalLocation = manifestData[ORIGINALLOCATION];
		this.patentID = manifestData[PATENTID];
		this.patient = manifestData[PATIENT];
		this.physicalLocationReference = manifestData[PHYSICALLOCATIONREFERENCE];
		this.policyNo = manifestData[POLICYNO];
		this.promotionalID = manifestData[PROMOTIONALID];
		this.salesRepresentative = manifestData[SALESREPRESENTATIVE];
		this.signatureValidation = manifestData[SIGNATUREVALIDATION];
		this.sopNo = manifestData[SOPNO];
		this.studyCode = manifestData[STUDYCODE];
		this.subject = manifestData[SUBJECT];
		this.systemID = manifestData[SYSTEMID];
		this.taxYear = manifestData[TAXYEAR];
		this.therapeuticArea = manifestData[THERAPEUTICAREA];
		this.vendor = manifestData[VENDOR];
	}

	public Manifest(String sourceSystem, String title, String classification,
			String security, String recordForm, String author,
			String dateCreated, String language, String relativePath,
			String dateClosed, String fileName, String eventTrigger,
			String legalHoldName, String product, String substanceReference,
			String physicalLocationDescription, String signer, String signerID,
			String signatureMethod, String signatureValue,
			String signatureEncoding, String signaturePublicKey,
			String signatureDate, String adverseEventReportNo,
			String astraZenecaSite, String audience, String auditRecords,
			String authenticityComments, String authenticityStandard,
			String authorityReferenceNo, String batchNo, String contractNo,
			String country, String department, String dosageForm,
			String drawingNo, String drugProductID, String drugSubstanceNo,
			String employeeName, String employeeNo, String engineer,
			String equipmentID, String healthcareProfessional,
			String intermediateProductCode, String investigationalSite,
			String investigator, String laboratoryNotebookNo,
			String marketingCompany, String notes, String legacyRecordNumber,
			String originalLocation, String patentID, String patient,
			String physicalLocationReference, String policyNo,
			String promotionalID, String salesRepresentative,
			String signatureValidation, String sopNo, String studyCode,
			String subject, String systemID, String taxYear,
			String therapeuticArea, String vendor) {
		super();
		this.sourceSystem = sourceSystem;
		this.title = title;
		this.classification = classification;
		this.security = security;
		this.recordForm = recordForm;
		this.author = author;
		this.dateCreated = dateCreated;
		this.language = language;
		this.relativePath = relativePath;
		this.dateClosed = dateClosed;
		this.fileName = fileName;
		this.eventTrigger = eventTrigger;
		this.legalHoldName = legalHoldName;
		this.product = product;
		this.substanceReference = substanceReference;
		this.physicalLocationDescription = physicalLocationDescription;
		this.signer = signer;
		this.signerID = signerID;
		this.signatureMethod = signatureMethod;
		this.signatureValue = signatureValue;
		this.signatureEncoding = signatureEncoding;
		this.signaturePublicKey = signaturePublicKey;
		this.signatureDate = signatureDate;
		this.adverseEventReportNo = adverseEventReportNo;
		this.astraZenecaSite = astraZenecaSite;
		this.audience = audience;
		this.auditRecords = auditRecords;
		this.authenticityComments = authenticityComments;
		this.authenticityStandard = authenticityStandard;
		this.authorityReferenceNo = authorityReferenceNo;
		this.batchNo = batchNo;
		this.contractNo = contractNo;
		this.country = country;
		this.department = department;
		this.dosageForm = dosageForm;
		this.drawingNo = drawingNo;
		this.drugProductID = drugProductID;
		this.drugSubstanceNo = drugSubstanceNo;
		this.employeeName = employeeName;
		this.employeeNo = employeeNo;
		this.engineer = engineer;
		this.equipmentID = equipmentID;
		this.healthcareProfessional = healthcareProfessional;
		this.intermediateProductCode = intermediateProductCode;
		this.investigationalSite = investigationalSite;
		this.investigator = investigator;
		this.laboratoryNotebookNo = laboratoryNotebookNo;
		this.marketingCompany = marketingCompany;
		this.notes = notes;
		this.legacyRecordNumber = legacyRecordNumber;
		this.originalLocation = originalLocation;
		this.patentID = patentID;
		this.patient = patient;
		this.physicalLocationReference = physicalLocationReference;
		this.policyNo = policyNo;
		this.promotionalID = promotionalID;
		this.salesRepresentative = salesRepresentative;
		this.signatureValidation = signatureValidation;
		this.sopNo = sopNo;
		this.studyCode = studyCode;
		this.subject = subject;
		this.systemID = systemID;
		this.taxYear = taxYear;
		this.therapeuticArea = therapeuticArea;
		this.vendor = vendor;
	}
	public String getSourceSystem() {
		return sourceSystem;
	}
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public String getRecordForm() {
		return recordForm;
	}
	public void setRecordForm(String recordForm) {
		this.recordForm = recordForm;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public String getDateClosed() {
		return dateClosed;
	}
	public void setDateClosed(String dateClosed) {
		this.dateClosed = dateClosed;
	}
	public String getEventTrigger() {
		return eventTrigger;
	}
	public void setEventTrigger(String eventTrigger) {
		this.eventTrigger = eventTrigger;
	}
	public String getLegalHoldName() {
		return legalHoldName;
	}
	public void setLegalHoldName(String legalHoldName) {
		this.legalHoldName = legalHoldName;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSubstanceReference() {
		return substanceReference;
	}
	public void setSubstanceReference(String substanceReference) {
		this.substanceReference = substanceReference;
	}
	public String getPhysicalLocationDescription() {
		return physicalLocationDescription;
	}
	public void setPhysicalLocationDescription(String physicalLocationDescription) {
		this.physicalLocationDescription = physicalLocationDescription;
	}
	public String getSigner() {
		return signer;
	}
	public void setSigner(String signer) {
		this.signer = signer;
	}
	public String getSignerID() {
		return signerID;
	}
	public void setSignerID(String signerID) {
		this.signerID = signerID;
	}
	public String getSignatureMethod() {
		return signatureMethod;
	}
	public void setSignatureMethod(String signatureMethod) {
		this.signatureMethod = signatureMethod;
	}
	public String getSignatureValue() {
		return signatureValue;
	}
	public void setSignatureValue(String signatureValue) {
		this.signatureValue = signatureValue;
	}
	public String getSignatureEncoding() {
		return signatureEncoding;
	}
	public void setSignatureEncoding(String signatureEncoding) {
		this.signatureEncoding = signatureEncoding;
	}
	public String getSignaturePublicKey() {
		return signaturePublicKey;
	}
	public void setSignaturePublicKey(String signaturePublicKey) {
		this.signaturePublicKey = signaturePublicKey;
	}
	public String getSignatureDate() {
		return signatureDate;
	}
	public void setSignatureDate(String signatureDate) {
		this.signatureDate = signatureDate;
	}
	public String getAdverseEventReportNo() {
		return adverseEventReportNo;
	}
	public void setAdverseEventReportNo(String adverseEventReportNo) {
		this.adverseEventReportNo = adverseEventReportNo;
	}
	public String getAstraZenecaSite() {
		return astraZenecaSite;
	}
	public void setAstraZenecaSite(String astraZenecaSite) {
		this.astraZenecaSite = astraZenecaSite;
	}
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public String getAuditRecords() {
		return auditRecords;
	}
	public void setAuditRecords(String auditRecords) {
		this.auditRecords = auditRecords;
	}
	public String getAuthenticityComments() {
		return authenticityComments;
	}
	public void setAuthenticityComments(String authenticityComments) {
		this.authenticityComments = authenticityComments;
	}
	public String getAuthenticityStandard() {
		return authenticityStandard;
	}
	public void setAuthenticityStandard(String authenticityStandard) {
		this.authenticityStandard = authenticityStandard;
	}
	public String getAuthorityReferenceNo() {
		return authorityReferenceNo;
	}
	public void setAuthorityReferenceNo(String authorityReferenceNo) {
		this.authorityReferenceNo = authorityReferenceNo;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getDrawingNo() {
		return drawingNo;
	}
	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}
	public String getDrugProductID() {
		return drugProductID;
	}
	public void setDrugProductID(String drugProductID) {
		this.drugProductID = drugProductID;
	}
	public String getDrugSubstanceNo() {
		return drugSubstanceNo;
	}
	public void setDrugSubstanceNo(String drugSubstanceNo) {
		this.drugSubstanceNo = drugSubstanceNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEngineer() {
		return engineer;
	}
	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
	public String getHealthcareProfessional() {
		return healthcareProfessional;
	}
	public void setHealthcareProfessional(String healthcareProfessional) {
		this.healthcareProfessional = healthcareProfessional;
	}
	public String getIntermediateProductCode() {
		return intermediateProductCode;
	}
	public void setIntermediateProductCode(String intermediateProductCode) {
		this.intermediateProductCode = intermediateProductCode;
	}
	public String getInvestigationalSite() {
		return investigationalSite;
	}
	public void setInvestigationalSite(String investigationalSite) {
		this.investigationalSite = investigationalSite;
	}
	public String getInvestigator() {
		return investigator;
	}
	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}
	public String getLaboratoryNotebookNo() {
		return laboratoryNotebookNo;
	}
	public void setLaboratoryNotebookNo(String laboratoryNotebookNo) {
		this.laboratoryNotebookNo = laboratoryNotebookNo;
	}
	public String getMarketingCompany() {
		return marketingCompany;
	}
	public void setMarketingCompany(String marketingCompany) {
		this.marketingCompany = marketingCompany;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getLegacyRecordNumber() {
		return legacyRecordNumber;
	}
	public void setLegacyRecordNumber(String legacyRecordNumber) {
		this.legacyRecordNumber = legacyRecordNumber;
	}
	public String getOriginalLocation() {
		return originalLocation;
	}
	public void setOriginalLocation(String originalLocation) {
		this.originalLocation = originalLocation;
	}
	public String getPatentID() {
		return patentID;
	}
	public void setPatentID(String patentID) {
		this.patentID = patentID;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getPhysicalLocationReference() {
		return physicalLocationReference;
	}
	public void setPhysicalLocationReference(String physicalLocationReference) {
		this.physicalLocationReference = physicalLocationReference;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getPromotionalID() {
		return promotionalID;
	}
	public void setPromotionalID(String promotionalID) {
		this.promotionalID = promotionalID;
	}
	public String getSalesRepresentative() {
		return salesRepresentative;
	}
	public void setSalesRepresentative(String salesRepresentative) {
		this.salesRepresentative = salesRepresentative;
	}
	public String getSignatureValidation() {
		return signatureValidation;
	}
	public void setSignatureValidation(String signatureValidation) {
		this.signatureValidation = signatureValidation;
	}
	public String getSopNo() {
		return sopNo;
	}
	public void setSopNo(String SopNo) {
		this.sopNo = SopNo;
	}
	public String getStudyCode() {
		return studyCode;
	}
	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSystemID() {
		return systemID;
	}
	public void setSystemID(String systemID) {
		this.systemID = systemID;
	}
	public String getTaxYear() {
		return taxYear;
	}
	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}
	public String getTherapeuticArea() {
		return therapeuticArea;
	}
	public void setTherapeuticArea(String therapeuticArea) {
		this.therapeuticArea = therapeuticArea;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String[] createManifestRecord() {
		String[] manifest = {
				this.sourceSystem,
				this.title,
				this.classification,
				this.security,
				this.recordForm,
				this.author,
				this.dateCreated,
				this.language,
				this.relativePath,
				this.dateClosed,
				this.fileName,
				this.eventTrigger,
				this.legalHoldName,
				this.product,
				this.substanceReference,
				this.physicalLocationDescription,
				this.signer,
				this.signerID,
				this.signatureMethod,
				this.signatureValue,
				this.signatureEncoding,
				this.signaturePublicKey,
				this.signatureDate,
				this.adverseEventReportNo,
				this.astraZenecaSite,
				this.audience,
				this.auditRecords,
				this.authenticityComments,
				this.authenticityStandard,
				this.authorityReferenceNo,
				this.batchNo,
				this.contractNo,
				this.country,
				this.department,
				this.dosageForm,
				this.drawingNo,
				this.drugProductID,
				this.drugSubstanceNo,
				this.employeeName,
				this.employeeNo,
				this.engineer,
				this.equipmentID,
				this.healthcareProfessional,
				this.intermediateProductCode,
				this.investigationalSite,
				this.investigator,
				this.laboratoryNotebookNo,
				this.marketingCompany,
				this.notes,
				this.legacyRecordNumber,
				this.originalLocation,
				this.patentID,
				this.patient,
				this.physicalLocationReference,
				this.policyNo,
				this.promotionalID,
				this.salesRepresentative,
				this.signatureValidation,
				this.sopNo,
				this.studyCode,
				this.subject,
				this.systemID,
				this.taxYear,
				this.therapeuticArea,
				this.vendor
		};
		return manifest;
	}
	
}
