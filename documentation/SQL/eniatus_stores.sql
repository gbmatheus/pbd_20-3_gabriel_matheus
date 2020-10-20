-- 
-- pesquisar_pessoa
CREATE OR REPLACE FUNCTION pesquisar_pessoa(termo varchar(30))  
RETURNS setof pessoas
AS $$
begin 
  RETURN QUERY SELECT * FROM pessoas WHERE upper(nome) LIKE upper(concat(termo, '%'));
end;
$$ 
LANGUAGE plpgsql;
-- 
-- 
-- calcular_salario_base
CREATE OR REPLACE FUNCTION calcular_salario_base(s_id int)  
RETURNS setof integer
AS $$
begin 
  RETURN QUERY SELECT horas_contratadas*quant_semanas*valor_da_hora AS salario_base,* FROM salario WHERE salario.id=s_id;
end;
$$ 
LANGUAGE plpgsql;
-- 
-- 
-- folha_mensal_funcionario
CREATE OR REPLACE FUNCTION calcular_salario_base(mes int, ano int)  
RETURNS TABLE( fmf folha_mensal_funcionario, f funcionarios, fm folha_mensal)
AS $$
begin 
  RETURN QUERY SELECT * FROM folha_mensal_funcionario fmf JOIN funcionarios f ON fmf.funcionario_id = f.id JOIN folha_mensal fm ON fmf.folha_mensal_id = fm.id WHERE fm.mes=11 AND fm.ano=ano;
end;
$$ 
LANGUAGE plpgsql;
