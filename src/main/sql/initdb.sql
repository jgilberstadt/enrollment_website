
insert into site (siteId, name, status) values (101, 'WashU', 'ACTIVE');
insert into site (siteId, name, status) values (102, 'Mayo Clinic', 'PENDING');
insert into site (siteId, name, status) values (103, 'Emory Univ', 'STOPPED');
insert into site (siteId, name, status) values (104, 'Cleveland Clinic', 'COMPLETED');

insert into participant (siteId, participantId, enrolledDate, status) 
values (1, '101001', '2014-04-01', 'NOT_ELIGIBLE');
insert into participant (siteId, participantId, enrolledDate, status) 
values (1, '101002', '2014-04-02', 'PENDING');
insert into participant (siteId, participantId, enrolledDate, status) 
values (1, '101003', '2014-04-03', 'ACTIVE');
insert into participant (siteId, participantId, enrolledDate, status) 
values (1, '101004', '2014-04-04', 'COMPLETED');

insert into participant (siteId, participantId, enrolledDate, status) 
values (2, '102001', '2014-04-05', 'ACTIVE');
insert into participant (siteId, participantId, enrolledDate, status) 
values (2, '102002', '2014-04-06', 'ACTIVE');

insert into participant (siteId, participantId, enrolledDate, status) 
values (3, '103001', '2014-04-07', 'ACTIVE');
insert into participant (siteId, participantId, enrolledDate, status) 
values (3, '103002', '2014-04-08', 'ACTIVE');

insert into participant (siteId, participantId, enrolledDate, status) 
values (4, '104001', '2014-04-09', 'ACTIVE');
insert into participant (siteId, participantId, enrolledDate, status) 
values (4, '104002', '2014-04-10', 'ACTIVE');

insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Eligibility', 'COMPLETE', false, null, '2014-04-01');
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Demographics/MedHistory', 'COMPLETE', true, null, '2014-04-02');
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'ECP Treatment 1', 'COMPLETE', true, '2014-04-05', '2014-04-05');
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'ECP Treatment 2', 'COMPLETE', true, '2014-04-07', '2014-04-07');
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'ECP Treatment 3', 'PENDING', true, '2014-04-09', null);
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Pulmonary Eval, Day 30', 'PENDING', true, '2014-05-09', null);
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Pulmonary Eval, Day 60', 'PENDING', true, '2014-06-09', null);
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Pulmonary Eval, Day 90', 'PENDING', true, '2014-07-09', null);
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Pulmonary Eval, Day 120', 'PENDING', true, '2014-08-09', null);
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Pulmonary Eval, Day 150', 'PENDING', true, '2014-09-09', null);
insert into event (participantId, name, status, expected, projectedDate, actualDate) 
values (1, 'Pulmonary Eval, Day 180', 'PENDING', true, '2014-10-09', null);

insert into basicform ( eventId, formType, title, creationDate, status) 
values (1, 'simple', 'Simple Form', '2014-04-01 13:23:09', 'NEW');
insert into formAttributes( formId, name, valueType, stringValue, verifiable)
values (1, 'firstName', 'string', 'David', true);
insert into formAttributes( formId, name, valueType, stringValue, verifiable)
values (1, 'lastName', 'string', 'Russell', true);

insert into basicform ( eventId, formType, title, creationDate, status) 
values (2, 'simple', 'Simple Form', '2014-04-10 15:10:09', 'NEW');
insert into formAttributes( formId, name, valueType, stringValue, verifiable)
values (2, 'firstName', 'string', 'Judy', true);
insert into formAttributes( formId, name, valueType, stringValue, verifiable)
values (2, 'lastName', 'string', 'Simonson', true);

insert into basicform ( eventId, formType, title, creationDate, status) 
values (1, 'medicalHistory', 'Medical History Form', '2014-04-02 13:11:09', 'NEW');
insert into formAttributes( formId, name, valueType, intValue, verifiable)
values (3, 'bp_systolic', 'integer', 120, true);
insert into formAttributes( formId, name, valueType, intValue, verifiable)
values (3, 'bp_diastolic', 'integer', 80, true);

insert into basicform ( eventId, formType, title, creationDate, status) 
values (2, 'medicalHistory', 'Medical History Form', '2014-04-24 11:23:09', 'NEW');
insert into formAttributes( formId, name, valueType, intValue, verifiable)
values (4, 'bp_systolic', 'integer', 121, true);
insert into formAttributes( formId, name, valueType, intValue, verifiable)
values (4, 'bp_diastolic', 'integer', 81, true);


