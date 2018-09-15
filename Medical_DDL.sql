create table role
	(role_id int not null auto_increment,
	 role_name varchar(20) unique,
	 role_description varchar(255),
	 primary key (role_id) 
	 );

create table employee
	(emp_id	int not null auto_increment,
	 emp_name varchar(50) not null,
	 emp_role_id int,
	 emp_addr varchar(255),
	 emp_dob date,
	 emp_email varchar(255),
	 emp_mobile_num varchar(11),
	 emp_alternate_num varchar(11),
	 emp_salary numeric(12,2),
	 emp_joining_date date,
	 primary key(emp_id),
	 foreign key(emp_role_id) references role(role_id)
	 	on delete set null );
	 
	 
create table login
	(login_role_id int not null,
	 login_username varchar(20) unique,
	 login_password varchar(20),
	 primary key(login_role_id),
	 foreign key(login_role_id) references role(role_id)
	 	on delete cascade ); 
	 
create table supplier
	(supplier_id int not null auto_increment,
	 supplier_name varchar(255),
	 supplier_addr text,
	 supplier_phone_num varchar(11),
	 supplier_email varchar(255),
	 supplier_contact_person varchar(50),
	 primary key(supplier_id) );
	 
create table medicine_type
	(type_id int not null auto_increment,
	 type_name varchar(50) not null,
	 type_description text,
	 primary key(type_id) );

create table medicine
	(medc_id varchar(20) not null,
	 medc_name varchar(50) not null,
	 medc_supplier_id int,
	 medc_type_id int,
	 medc_per_strip numeric(10,2),
	 medc_cost_per_strip numeric(10,2), 
	 medc_description text,
	 shelf_no varchar(10),
	 primary key(medc_id),
	 foreign key(medc_supplier_id) references supplier(supplier_id)
	 	on delete cascade,
	 foreign key(medc_type_id) references medicine_type(type_id)
	 	on delete set null );
	 
 	
create table stock_details
	(medc_id varchar(20) not null,
	 supplier_id int not null,
	 purchase_date_timestamp datetime not null,
	 quantity int,
	 total_cost_of_purchase numeric(12,2),
	 primary key(medc_id, supplier_id, purchase_date_timestamp),
	 foreign key(medc_id) references medicine(medc_id)
	 	on delete cascade,
	 foreign key(supplier_id) references supplier(supplier_id)
	 	on delete cascade);
	 
create table defect
	(defect_id int not null auto_increment,
	 defect_description text,
	 primary key(defect_id) );
	 
create table stock_defect
	(medc_id varchar(20) not null,
	 supplier_id int(11),
	 quantity int,
	 defect_id int,
	 defect_description_in_detail text,
	 return_date date,
	 amount_received_back numeric(12,2),
	 amount_pending numeric(12,2),
	 primary key(medc_id, defect_id, return_date),
	 foreign key(medc_id) references stock_details(medc_id)
	 	on delete cascade
	 foreign key (defect_id) references defect(defect_id)
	 	on delete cascade
	 foreign key (supplier_id) references supplier (supplier_id)
	 	on delete cascade);
	 	
create table attendance_details
	(emp_id int(11) not null,
         day date,
         attendance enum('F', 'T'),
	 primary key(emp_id, day),
	 foreign key(emp_id) references employee(emp_id)
	 	on delete cascade );
	 	
 	
	
create table sales
	(bill_no int not null auto_increment,
	 sale_timestamp datetime,
	 medc_id varchar(20),
	 sold_quantity int,
	 sale_total_amount numeric(12,2),
	 sale_received_amount numeric(12,2),
	 primary key(bill_no, sale_timestamp, medc_id),
	 foreign key(medc_id) references medicine(medc_id)
	 	on delete cascade
	);
