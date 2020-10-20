-- public.inss definition

-- Drop table

-- DROP TABLE public.inss;

CREATE TABLE public.inss (
	id bigserial NOT NULL,
	aliquota numeric(19,2) NOT NULL,
	ativo bool NOT NULL,
	mes int4 NOT NULL,
	periodo timestamp NOT NULL,
	valor_max numeric(19,2) NOT NULL,
	valor_min numeric(19,2) NOT NULL,
	vigencia int4 NOT NULL,
	CONSTRAINT inss_pkey PRIMARY KEY (id)
);


-- public.irrf definition

-- Drop table

-- DROP TABLE public.irrf;

CREATE TABLE public.irrf (
	id bigserial NOT NULL,
	aliquota numeric(19,2) NOT NULL,
	ativo bool NOT NULL,
	base_calculo_max numeric(19,2) NOT NULL,
	base_calculo_min numeric(19,2) NOT NULL,
	mes int4 NOT NULL,
	periodo timestamp NOT NULL,
	valor_deduzir numeric(19,2) NOT NULL,
	vigencia int4 NOT NULL,
	CONSTRAINT irrf_pkey PRIMARY KEY (id)
);


-- public.permissao definition

-- Drop table

-- DROP TABLE public.permissao;

CREATE TABLE public.permissao (
	nome varchar(255) NOT NULL,
	CONSTRAINT permissao_pkey PRIMARY KEY (nome)
);


-- public.pessoas definition

-- Drop table

-- DROP TABLE public.pessoas;

CREATE TABLE public.pessoas (
	id bigserial NOT NULL,
	cpf varchar(14) NULL,
	dt_nascimento timestamp NOT NULL,
	naturalidade varchar(40) NULL,
	nome varchar(50) NULL,
	CONSTRAINT pessoas_pkey PRIMARY KEY (id),
	CONSTRAINT uk_c7pqbmo6e96slvonilywsb8oe UNIQUE (cpf)
);


-- public.salario definition

-- Drop table

-- DROP TABLE public.salario;

CREATE TABLE public.salario (
	id bigserial NOT NULL,
	horas_contratadas int4 NOT NULL,
	quant_semanas numeric(10,1) NULL DEFAULT 5.5,
	valor_da_hora numeric(19,2) NOT NULL,
	CONSTRAINT salario_pkey PRIMARY KEY (id)
);


-- public.salario_familia definition

-- Drop table

-- DROP TABLE public.salario_familia;

CREATE TABLE public.salario_familia (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	mes int4 NOT NULL,
	periodo timestamp NOT NULL,
	remuneracao numeric(19,2) NOT NULL,
	valor_unitario numeric(19,2) NOT NULL,
	vigencia int4 NOT NULL,
	CONSTRAINT salario_familia_pkey PRIMARY KEY (id)
);


-- public.salario_minimo definition

-- Drop table

-- DROP TABLE public.salario_minimo;

CREATE TABLE public.salario_minimo (
	id bigserial NOT NULL,
	ativo bool NOT NULL,
	mes int4 NOT NULL,
	periodo timestamp NOT NULL,
	valor numeric(19,2) NOT NULL,
	vigencia int4 NOT NULL,
	CONSTRAINT salario_minimo_pkey PRIMARY KEY (id)
);


-- public.folha_mensal definition

-- Drop table

-- DROP TABLE public.folha_mensal;

CREATE TABLE public.folha_mensal (
	id bigserial NOT NULL,
	adicional_tempo_servico numeric(10,2) NULL DEFAULT 0.00,
	ano int4 NOT NULL,
	aux_alimentacao numeric(10,2) NULL DEFAULT 0.00,
	contribuicao_sindical numeric(10,2) NULL DEFAULT 0.00,
	faltas int4 NULL DEFAULT 0,
	hora_extra int4 NULL DEFAULT 0,
	mes int4 NOT NULL,
	periodo timestamp NOT NULL,
	salario_minimo_id int8 NULL,
	inss_id int8 NULL,
	irrf_id int8 NULL,
	salario_familia_id int8 NULL,
	CONSTRAINT folha_mensal_pkey PRIMARY KEY (id),
	CONSTRAINT fk5ihg6nk7eysl5sf6e7jo6jqml FOREIGN KEY (inss_id) REFERENCES inss(id),
	CONSTRAINT fkae3p93mtxylihupvgjth8f0wa FOREIGN KEY (salario_familia_id) REFERENCES salario_familia(id),
	CONSTRAINT fkej1hnkoafpwfq9vggcksfben4 FOREIGN KEY (irrf_id) REFERENCES irrf(id),
	CONSTRAINT fkjkeuguyg1k51vikp7tnb62oyn FOREIGN KEY (salario_minimo_id) REFERENCES salario_minimo(id)
);


-- public.funcionarios definition

-- Drop table

-- DROP TABLE public.funcionarios;

CREATE TABLE public.funcionarios (
	id bigserial NOT NULL,
	ativo bool NULL,
	cod_funcionario varchar(14) NULL,
	dt_admissao timestamp NOT NULL,
	quantidade_filhos int4 NULL DEFAULT 0,
	tipo varchar(5) NULL,
	pessoa_id int8 NOT NULL,
	salario_id int8 NOT NULL,
	CONSTRAINT funcionarios_pkey PRIMARY KEY (id),
	CONSTRAINT fk8j0vewjig3gbr07brhya1ao1r FOREIGN KEY (pessoa_id) REFERENCES pessoas(id),
	CONSTRAINT fk90wwv72mm15sxrg9f95dsyyon FOREIGN KEY (salario_id) REFERENCES salario(id)
);


-- public.outros_acrescimos definition

-- Drop table

-- DROP TABLE public.outros_acrescimos;

CREATE TABLE public.outros_acrescimos (
	id bigserial NOT NULL,
	data_acrecimo timestamp NOT NULL,
	descricao varchar(255) NULL,
	valor numeric(19,2) NOT NULL,
	folha_mensal_id int8 NULL,
	CONSTRAINT outros_acrescimos_pkey PRIMARY KEY (id),
	CONSTRAINT fk327v42nnx1vpesna2qw9awesh FOREIGN KEY (folha_mensal_id) REFERENCES folha_mensal(id)
);


-- public.outros_descontos definition

-- Drop table

-- DROP TABLE public.outros_descontos;

CREATE TABLE public.outros_descontos (
	id bigserial NOT NULL,
	data_desconto timestamp NOT NULL,
	descricao varchar(255) NULL,
	valor numeric(19,2) NOT NULL,
	folha_mensal_id int8 NULL,
	CONSTRAINT outros_descontos_pkey PRIMARY KEY (id),
	CONSTRAINT fkffce6f8krn5rnvm57lae9chwb FOREIGN KEY (folha_mensal_id) REFERENCES folha_mensal(id)
);


-- public.usuarios definition

-- Drop table

-- DROP TABLE public.usuarios;

CREATE TABLE public.usuarios (
	id bigserial NOT NULL,
	email varchar(255) NOT NULL,
	login varchar(255) NULL,
	senha varchar(100) NULL,
	pessoa_id int8 NULL,
	CONSTRAINT uk_r8oo98o39ykr4hi57md9nibmw UNIQUE (login),
	CONSTRAINT usuarios_pkey PRIMARY KEY (id),
	CONSTRAINT fkejj87w947vumdyt6unimlyok8 FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);


-- public.usuarios_permissoes definition

-- Drop table

-- DROP TABLE public.usuarios_permissoes;

CREATE TABLE public.usuarios_permissoes (
	usuario_id int8 NOT NULL,
	permissoes_nome varchar(255) NOT NULL,
	CONSTRAINT fkbyf2kljok3aotwocoig8r8s54 FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
	CONSTRAINT fkew7w4uumypihwxvje0u3wsue0 FOREIGN KEY (permissoes_nome) REFERENCES permissao(nome)
);


-- public.folha_mensal_funcionario definition

-- Drop table

-- DROP TABLE public.folha_mensal_funcionario;

CREATE TABLE public.folha_mensal_funcionario (
	folha_mensal_id int8 NOT NULL,
	funcionario_id int8 NOT NULL,
	CONSTRAINT fk7ktmelc77fqcqs6qjwr7pdb02 FOREIGN KEY (folha_mensal_id) REFERENCES folha_mensal(id),
	CONSTRAINT fkk0baxwq60j3hi33ll3fq6qhxf FOREIGN KEY (funcionario_id) REFERENCES funcionarios(id)
);

INSERT INTO public.inss (aliquota,ativo,mes,periodo,valor_max,valor_min,vigencia) VALUES
	 (7.50,true,10,'2020-10-05 23:02:57.339059',1045.00,0.00,2020),
	 (9.00,true,10,'2020-10-05 23:03:13.956666',2089.60,1045.01,2020),
	 (12.00,true,10,'2020-10-05 23:03:23.890282',3134.40,2089.61,2020),
	 (14.00,true,10,'2020-10-05 23:03:28.525044',6101.06,3134.41,2020),
	 (8.00,false,1,'2020-10-05 23:04:05.266638',1830.29,0.00,2020),
	 (9.00,false,1,'2020-10-05 23:04:15.17564',3050.52,1830.30,2020),
	 (11.00,false,1,'2020-10-05 23:04:19.458504',6101.06,3050.53,2020);



INSERT INTO public.irrf (aliquota,ativo,base_calculo_max,base_calculo_min,mes,periodo,valor_deduzir,vigencia) VALUES
	 (0.00,true,1903.98,0.00,10,'2020-10-05 22:50:44.366296',0.00,2020),
	 (15.00,true,3751.05,2826.66,10,'2020-10-05 22:51:13.98719',354.80,2020),
	 (7.50,true,2826.65,1903.99,10,'2020-10-05 22:51:23.797545',142.80,2020),
	 (27.50,true,0.00,4664.68,10,'2020-10-05 22:51:29.350834',869.36,2020),
	 (22.50,true,4664.68,3751.06,10,'2020-10-05 22:53:36.407546',636.13,2020);



INSERT INTO public.permissao (nome) VALUES
	 ('ADMIN'),
	 ('CONTADOR');

   INSERT INTO public.pessoas (cpf,dt_nascimento,naturalidade,nome) VALUES
	 ('000.000.000-00','2020-10-19 23:13:49.080802','português','admin'),
	 ('000.000.000-01','2020-10-19 23:13:49.718002','português','contador'),
	 ('144.252.583-55','1987-03-24 00:00:00','Brasília - DF','Mateus Thiago Teixeira'),
	 ('565.610.640-38','1987-08-20 22:31:45','Nova Iguaçu - RJ','Hugo Kevin Edson Freitas'),
	 ('435.347.524-06','1987-10-12 22:39:21','Serra - ES','Bruno Pietro Melo'),
	 ('106.854.952-13','1987-12-08 22:41:15','Rio Branco - AC','Nicole Larissa Ferreira'),
	 ('598.407.058-81','1987-08-04 00:00:00','Lucas do Rio Verde - MT','Melissa Catarina Larissa Cardoso');


INSERT INTO public.salario (horas_contratadas,quant_semanas,valor_da_hora) VALUES
	 (40,5.5,4.75),
	 (40,5.5,4.75),
	 (30,5.5,6.00),
	 (30,5.5,15.00),
	 (40,5.5,4.75);

   INSERT INTO public.salario_familia (ativo,mes,periodo,remuneracao,valor_unitario,vigencia) VALUES
	 (false,1,'2020-10-05 22:05:19.57985',859.88,44.09,2017),
	 (false,1,'2020-10-05 22:06:21.55558',877.67,45.00,2018),
	 (false,1,'2020-10-05 22:06:06.801388',907.77,46.54,2019),
	 (false,11,'2020-10-05 22:06:42.395306',1364.43,46.54,2019),
	 (false,1,'2020-10-05 22:07:56.781218',1425.56,48.62,2020),
	 (true,10,'2020-10-05 22:08:26.351411',1425.56,46.54,2020),
	 (false,10,'2020-10-05 22:07:40.556508',1425.56,46.54,2020);


INSERT INTO public.salario_minimo (ativo,mes,periodo,valor,vigencia) VALUES
	 (false,1,'2020-10-05 22:15:03.833686',937.00,2017),
	 (false,1,'2020-10-05 22:15:19.177953',954.00,2018),
	 (true,2,'2020-10-05 22:15:47.141271',1045.00,2020),
	 (false,1,'2020-10-05 22:15:25.217109',998.00,2019),
	 (false,1,'2020-10-05 22:15:58.161846',1039.00,2020);

  INSERT INTO public.folha_mensal (adicional_tempo_servico,ano,aux_alimentacao,contribuicao_sindical,faltas,hora_extra,mes,periodo,salario_minimo_id,inss_id,irrf_id,salario_familia_id) VALUES
	 (52.00,2020,100.00,0.00,0,0,10,'2020-10-06 00:05:29.217885',4,1,1,7),
	 (52.00,2020,100.00,0.00,0,0,10,'2020-10-06 00:10:22.510525',4,1,1,7),
	 (52.00,2020,100.00,0.00,0,0,10,'2020-10-06 00:18:20.886154',4,1,1,7);

INSERT INTO public.funcionarios (ativo,cod_funcionario,dt_admissao,quantidade_filhos,tipo,pessoa_id,salario_id) VALUES
	 (true,'144.252.583-55','2020-10-05 22:31:45.589496',0,'MES',3,1),
	 (true,'565.610.640-38','2020-10-05 22:39:21.098535',3,'MES',4,3),
	 (true,'435.347.524-06','2020-10-05 22:41:15.89793',1,'HORA',5,4),
	 (true,'106.854.952-13','2020-10-05 22:43:45.5446',0,'HORA',6,5),
	 (true,'598.407.058-81','2020-10-05 22:45:22.165314',4,'MES',7,2);

   INSERT INTO public.outros_acrescimos (data_acrecimo,descricao,valor,folha_mensal_id) VALUES
	 ('2020-10-06 00:29:58.343179','Vale Transporte',100.00,3),
	 ('2020-10-06 00:30:16.405106','Plano de saúde',120.00,3),
	 ('2020-10-06 00:30:18.663494','Academia',50.00,3),
	 ('2020-10-06 00:30:21.183041','Plano dentario',50.00,3);



INSERT INTO public.outros_descontos (data_desconto,descricao,valor,folha_mensal_id) VALUES
	 ('2020-10-06 00:36:10.160421','Caneta azul',10.00,3),
	 ('2020-10-06 00:36:20.723679','Danos causados pelo empregado',50.00,3),
	 ('2020-10-06 00:36:25.256642','Refeitorio',80.00,3);

   
INSERT INTO public.usuarios (email,login,senha,pessoa_id) VALUES
	 ('admin@mailcom','admin','$2a$10$f9pCJiJPTkEqa45nmH3NXOcegWmXIvsgxxJmWYBBpUBKH0r4AoOHy',1),
	 ('contador@email.com','contador','$2a$10$Wsl6kLUw30CDeWsjNw9gY.zy4qmkMhesqHt/OjnRw8CF1Ao159yni',2);



INSERT INTO public.usuarios_permissoes (usuario_id,permissoes_nome) VALUES
	 (1,'ADMIN'),
	 (2,'CONTADOR');



INSERT INTO public.folha_mensal_funcionario (folha_mensal_id,funcionario_id) VALUES
	 (1,1),
	 (2,3),
	 (3,3);