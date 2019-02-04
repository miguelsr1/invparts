create view vw_categoria_by_modelo as
select distinct 
	ca.id_categoria,
	ca.nombre_categoria,
	ca.padre_id_categoria,
	mo.id_modelo
from 
	marca ma
	inner join modelo mo 		 	 on ma.id_marca = mo.id_marca
	inner join compatibilidad co 	 on mo.id_modelo = co.id_modelo
	inner join item it 			 	 on co.id_item = it.id_item
	inner join producto_categoria pc on pc.id_item = it.id_item
	inner join categoria ca 		 on ca.id_categoria = pc.id_categoria;

create or replace view vw_find_items_by_model_and_category as
select distinct
	it.*,
	co.id_modelo,
	pc.id_categoria
from item it
	inner join compatibilidad co 	 on it.id_item = co.id_item
	inner join producto pr		 	 on pr.id_producto = it.id_producto
	inner join producto_categoria pc on pc.id_producto = pr.id_producto;

create view vw_opciones_menu_by_usu_and_mod as
select 
	opc.*, usu.usuario
from usuario usu
	inner join usuario_empresa uemp on usu.usuario = uemp.usuario
    inner join privilegio pri on pri.id_modulo_perfil = uemp.id_modulo_perfil
    inner join opcion_menu opc on pri.id_opcion_menu = opc.id_opcion_menu;

create view vw_compatibilidad_dto as
select 
	com.*,
	m.nombre_modelo
from 
	compatibilidad com
	inner join modelo m on com.id_modelo = m.id_modelo;