-- 
-- listar_funcionarios
CREATE OR REPLACE VIEW listar_funcionarios AS
SELECT 
pe.nome, pe.cpf, pe.dt_nascimento, pe.naturalidade, 
f.quantidade_filhos, f.tipo, 
s.horas_contratadas, s.quant_semanas, s.valor_da_hora  
FROM pessoas pe 
JOIN funcionarios f ON f.pessoa_id = pe.id 
JOIN salario s ON s.id = f.salario_id 
WHERE ativo =true;

ALTER TABLE listar_funcionarios
 OWNER TO postgres;
-- 
-- 
-- listar_acrescimos_descontos
CREATE OR REPLACE VIEW listar_acrescimos_descontos AS
SELECT fm.*, oa.descricao AS acrescimo_descricao, oa.valor AS acrescimo_valor, od.descricao AS desconto_descricao, od.valor as desconto_valor 
FROM folha_mensal fm INNER JOIN outros_acrescimos oa ON fm.id=oa.folha_mensal_id 
INNER JOIN outros_descontos od ON fm.id=od.folha_mensal_id;

ALTER TABLE listar_acrescimos_descontos
 OWNER TO postgres;
--  
-- 
-- listar_folha_funcionario
CREATE OR REPLACE VIEW listar_folha_funcionario AS
SELECT 
f.cod_funcionario, f.quantidade_filhos, f.dt_admissao, f.tipo, f.id AS id_funcionario,
fm.*
FROM folha_mensal_funcionario fmf 
JOIN funcionarios f ON fmf.funcionario_id = f.id
JOIN folha_mensal fm ON fmf.folha_mensal_id = fm.id
WHERE f.ativo=true;

ALTER TABLE listar_folha_funcionario
 OWNER TO postgres;