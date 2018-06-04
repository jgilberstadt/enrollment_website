-- Run vacuumlo dbname from sudo su postgres before you run this sql file to 
-- remove the large objects (oid or blobs) from the database.  When the tables
-- are dropped the large objects are not dropped, so the links are broken to the
-- large object table in postgress.  Vacuumlo removes the large objects.
-- Run vacuumdb dbname also to make sure the databse is cleaned up.
drop table documents;
drop table formAttributes;
drop table basicForm;
drop table event;
drop table participant;
drop table logininfo;
drop table site;
drop table auditlog;

create table site (
    id       serial primary key,
    siteId   int unique,
    name     varchar(64) unique,
    status   varchar(32),
-- Crfversion is the version of the CRF listed at the bottom of each form. 
    crfversion    varchar(20),
-- Irbversion is not used presently as of 11/13/2015
    irbversion    varchar(20),
-- Irbsubmittedaddate is the date when the protocol was submitted by WashU to the WashU IRB to obtain approval and listed at the bottom of each form.
    irbsubmitteddate date,
);

create table participant (
    id              serial primary key,
    siteId          int references site(id),
    participantId   varchar(32) unique,
    enrolledDate    date,
    status          varchar(32),
    studyarmenrolldate date,
    studyarmstatus  varchar(32),
    holdstatus      boolean,
    holdStartDate   date,
    overrideDate    date
);

-- a participant may have multiple events of the same type.
-- a participant may have multiple events with the same name (title).
-- a participant may not have two events with both the same name and label.
create table event (
    id              serial primary key,
    participantId   int references participant(id),
    eventType       varchar(64),
    name            varchar(64),
    label           varchar(64),
    status          varchar(32),
    expected        boolean,
    baseDate        date,
    baseDateOffset  int,
    firstDateOffset int,
    lastDateOffset  int,
    actualDate      date,
    showBaseDate    boolean,
    showFirstAvailDate	boolean,
    showLastAvailDate	boolean,
    unique( participantId, name, label)
);

-- an event may have multiple forms of the same type but the
-- forms may not have same name.
create table basicForm (
    id            serial primary key,
    eventId       int references event(id),
    formType      varchar(64),
    title         varchar(128),
-- The creationDate also contains the Event date that is input into the form by the study coordinator.
-- The creationDate starts with the date the patient was Enrolled and DCC-VERIFIED, then when a new
-- form is submitted, the creationDate gets changed to the Event date.
    creationDate  date,
    status        varchar(32),
-- for events that have multiple forms that are ordered in some fashion.
    sequenceNumber int,
    lastSubmittedDate timestamp,
-- The version id is used from the version control table to indicate the software version among others.
    versionid     int,
-- Crfversion is the version of the CRF listed at the bottom of each form. 
    crfversion    varchar(20),
-- Irbversion is not used presently as of 11/13/2015
    irbversion    varchar(20),
-- Irbsubmittedaddate is the date when the protocol was submitted by WashU to the WashU IRB to obtain approval and listed at the bottom of each form.
    irbsubmitteddate date,
    unique( eventId, title)
);

create table formAttributes (
    id             serial primary key,
    formId         int references basicForm(id),
    name           varchar(64),
    valueType      varchar(16),
    booleanValue   boolean,
    intValue       int,
    floatValue     float,
    dateValue      date,
    timeValue      time,
    timestampValue timestamp,
    stringValue    varchar(512),
    verify         boolean default false,
    optional       boolean default false,
    verificationStatus varchar(48),
    dcccomment     text
);

create table documents (
    id             serial primary key,
    formId         int references basicForm(id),
    name           varchar(255),
    submissiondate timestamp,
    lo_oid         oid,
    sourcedoctype  varchar(75)
);

create table auditlog (
    id                    serial primary key,
    user_name             varchar(32),
    submission_timestamp  timestamp,
    form_id               int,
    form_name             varchar(128),
    class_name            varchar(128),
    object_value          bytea
);

create table logininfo (
    id              serial primary key,
    siteId          int references site(id),
    username        varchar(32),
    ipaddress       varchar(32),
    loginDate       timestamp
);


create table versioncontrol (
    id                  serial primary key,
    protocoldate        date,
    protocolvernum      varchar(12),
    crfreleasedate      date,
    crfupdate           date,
    crfvernum           varchar(12),
    softwarereleasedate date,
    softwarevernum      varchar(12),
    gitcommitnum        varchar(12),
    comments            varchar(4095)
);


insert into site (siteId, name, status) values (100, 'Test Site', 'ACTIVE');
insert into site (siteId, name, status) values (101, 'WashU', 'ACTIVE');
insert into site (siteId, name, status) values (102, 'Columbia U', 'ACTIVE');
insert into site (siteId, name, status) values (103, 'UCSD', 'ACTIVE');
insert into site (siteId, name, status) values (104, 'Indiana U', 'ACTIVE');
insert into site (siteId, name, status) values (105, 'U of Iowa', 'ACTIVE');
insert into site (siteId, name, status) values (106, 'Cleveland Clinic', 'ACTIVE');
insert into site (siteId, name, status) values (107, 'U of Michigan', 'ACTIVE');
insert into site (siteId, name, status) values (108, 'U of Minnesota', 'ACTIVE');
insert into site (siteId, name, status) values (109, 'UPMC', 'ACTIVE');


insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-03-30', '1.0', '2015-03-30', 'v1.0.0', '753b44b99b4', 'Fixed message in crossover calculator to be: 4 or more FEV1 values in the last 4 weeks and the slope is OK but not significant.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-14', '1.0', '2015-04-14', 'v1.0.0', '7615329f14e', 'Modified the COE CRF to remove BOS verification, and added the question 4D Has the signed COE been uploaded. Modified the Help main menu item to be a drop down instead of a button. Added submenu items to help. Added a ECP Reports main menu item. Added disabled=true for all FEV1s in the observePulmEvalLogForm.xhtml. Changed the FormStateTransitionValidator.java to include isRequiresVerification for the ObservePulmEvalLogForm.java and ChangeTherapyForm.java classes that do not currently require them to be DCC_VERIED. Now these two form can be changed from Submitted to DCC_Verified. Fixed comments when merging.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-15', '1.0', '2015-04-15', 'v1.0.0', '63ef91801d3', 'Allow CBC question on ECP Treatment form to be Not Applicable');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-16', '1.0', '2015-04-16', 'v1.0.0', '76731fb76aa', 'ECPTreatmentForm.xhtml question 6, swapped the checkbox and Not Applicable text.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-16', '1.0', '2015-04-17', 'v1.0.0', '4d7a227e9b7', 'In the StudyArmEligibilityForm.java method getPulmEvaluations, the code that removed any dates older than six months from the list was commented out. Removed the comment to enable to code to run.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-16', '1.0', '2015-04-17', 'v1.0.0', 'b1eb2ad4147', 'In StudyArmStatus.java, I found OBSERVATIONAL_ARM was missing the S. Fixed the missing S. Also started adding a new reportMonthlyAccrual.xhtml form.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-16', '1.0', '2015-04-17', 'v1.0.0', 'f1633de5c98', 'Forgot to comment out the InclusionExclusionFEV1sPreset statement in CalcController.java.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-16', '1.0', '2015-04-17', 'v1.0.0', 'f1633de5c98', 'Forgot to comment out the InclusionExclusionFEV1sPreset statement in CalcController.java.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-04-23', '1.0', '2015-04-23', 'v1.0.0', '51d4d9d6cc4', 'Added email when participant is crossed over from Observational to ECP Treatment Arm. Added signed Crossover Safety Check CRF question and verification to the Crossover Safety Check CRF. Changed email notification from after Confirmation of Eligibility CRF is DCC_VERIFIED to after the participant is enrolled (CalcController.java) which happens before the COE is created.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-05-15', '1.0', '2015-05-15', 'v1.0.0', '73875fbdcaf', 'AE/SAE form with many many changes. See JIRA ECP Board ticket ECP-142 for many of the details regarding changes made.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-02', '1.0', '2015-06-02', 'v1.0.0', '0a9ec636381', 'Added totalAccrualReport and monthlyAccrualReport with the associated code needed to run the reports under the ECP Reports menu item. Altered the two AE/SAE forms to turn off the Not Applicable radio button for question 6 in Section 2.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-02', '1.0', '2015-06-02', 'v1.0.0', '419d620c4b5', 'Add stand-alone eligibility calculator');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-03', '1.0', '2015-06-03', 'v1.0.0', 'adaf7ff868b', 'Fixed the form titles being changed in the database by backing beans constructor. Changed ECP Treatment form question 12 word plasma to whole blood. Fixed some of the forms because they were not displaying the form number in the title. Added bread crumb to site.xhtml.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-03', '1.0', '2015-06-05', 'v1.0.0', '35e41a34b3f', 'Changed Baseline Therapy Question 1 totalLymphoidIrraditionCurrent verify to false.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-12', '1.0', '2015-06-12', 'v1.0.0', 'fd336cb3e7a', 'Changed the SAE red letter wording in Section 1 when Question 1 and/or 2 are marked YES. Changed the SAE to the Event Date is the Current Date for SAE Form - 1 only or the Onset Date. Changed the HOME button in the template.xhtml/controller to point to the correct server (Production, Training, Test) when selected. Changed the Event.xhtml and formTemplate to display the Form Creation Date and Form Last Submitted Date. Changed the AddFormBean to have todays date in it, and set the SAE Current Date to the Add SAE Form Creation Date. Changed the PulmEvalVisitLastDateOffset to be 8 and 15 instead of 7 and 14 to fix the Overdue Date issue.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-12', '1.0', '2015-06-15', 'v1.2.5', '26f55a670bb', 'Changed the enrollment and sae notification email list to be for production along with the Home button to point to ecpregistry.wustl.edu Tagged as version 1.2.5');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-12', '1.0', '2015-06-18', 'v1.2.6', '687ed73e11a', 'Added the ability to record in the database who logged into the Site Resources portion of the ECP Registry. Added the logininfo database table.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-06-22', '1.0', '2015-06-22', 'v1.2.7', '904056ebfb5', 'Changed the wording for ECP Treatment Visit CRF questions 11 and 12 to have at least or more.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-07-09', '1.0', '2015-07-09', 'v1.2.7', '8cd05f2b2f0', 'Added the word pre-bronchodilator to question 3 in the enrollStudyArmEligForm.xhtml. Changed question 4. FEV1/FVC Ratio from minimum of 30 to 10. Started adding dataMissing variable to the BaselineTherapy.java and xhtml form and formTemplate.xhtml to deal with the possibility of data missing on any particular CRF. Updated the COE power point slide pdf file to a new version in the template.xhtml.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-07-10', '1.0', '2015-07-10', 'v1.2.8', 'f169c46edf1', 'Changed the software for ECP Treatment Visit form for question 6 checkbox from Not Applicable to Not Available. Fixed the misspelling of Anti-Thymocyte Globulin in the Demographics form for Questions 12 and 13.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-07-16', '1.0', '2015-07-16', 'v1.2.9', 'e7a963717d4', 'Fixed the StudyArmEligibilityCalculator.java and other classes to use the correct FEV1 dates (spacing between visits, 6 months, last 7 days) to calculate the slope and significance. In the formTemplate.xhtml added the dialog to ask if you really want to delete the PDF file. Added getEvalsWithQualifyingDates method to ReCalcController.java. Changed minimum 30% FEV1/FVC ratio to 10%.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-07-19', '1.0', '2015-07-19', 'v1.2.10', '735e62765d8', 'Added VersionControl.java along with placing the CRF Version and Protocol Date at the bottom of each CRF via the formTemplate.xhtml. Added the versioncontrol table to the database. Inserted the software versions from code.imphub.org into the database starting with V1.0.0 on March 30 2015. Added versionid column to basicform table in database.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-07-21', '1.0', '2015-07-21', 'v1.2.11', '917772f3ad0', 'Modified the VersionControl.java to add a description of the variables, along with removing the variables gitcommitnum and comment, and remove them from the getter and insert. Fixed the insertVersionControlId and getVersionControlId methods in the JDBCPersistenceManager to remove the gitcommitnum. Inserted the software version from code.imphub.org into the database for V1.2.10 into the createdb.sql file. Modified the Controller.java file to fix the Event Date not containing the onsetDate from the form when a new Event is created by using the Add Event button. Added the new CCC and DCC MOPs to the template.xhtml page. Removed old backup copies of the BasicForm_1.java and VersionControl_1.java files.');
insert into versioncontrol(protocoldate, protocolvernum, crfreleasedate, crfupdate, crfvernum, softwarereleasedate, softwarevernum, gitcommitnum, comments)
values('2014-09-23', '4', '2014-09-23', '2015-07-31', '1.0', '2015-07-31', 'v1.2.12', '59bb8ab35d1', 'Biggest change was adding drugs to Demographics CRF Questions 12 and 13, Baseline Therapy, and Change of Therapy. Fixed CalcController.java to save the slope and significance into the studyArmEligibilityForm in the enrollIntoObservationalArmAction method so they will be written into the database. Fixed the StudyArmEligibilityForm.java code so the recalc/calculator only plots the last six months of data from the screen date. Change the code to be 182 days (~365/2) stead of 180 days. This code is configured to run on the Test Server, but I used the same code except configured to run on the Production / Training servers too.');
