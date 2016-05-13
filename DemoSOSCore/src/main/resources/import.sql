
--provincias
INSERT INTO `Provincia` (`id_provincia`, `descripcion`) VALUES ('1', 'Cordoba');
INSERT INTO `Provincia` (`id_provincia`, `descripcion`) VALUES ('2', 'Buenos Aires');

--localidades
INSERT INTO `Localidad` (`id_localidad`, `descripcion`, `id_provincia`) VALUES ('1', 'Cordoba', '1');
INSERT INTO `Localidad` (`id_localidad`, `descripcion`, `id_provincia`) VALUES ('2', 'Rio Cuarto', '1');
INSERT INTO `Localidad` (`id_localidad`, `descripcion`, `id_provincia`) VALUES ('3', 'Oncativo', '1');
INSERT INTO `Localidad` (`id_localidad`, `descripcion`, `id_provincia`) VALUES ('4', 'Villa Maria', '1');
INSERT INTO `Localidad` (`id_localidad`, `descripcion`, `id_provincia`) VALUES ('5', 'Matanza', '2');
INSERT INTO `Localidad` (`id_localidad`, `descripcion`, `id_provincia`) VALUES ('6', 'Banfield', '2');

--barrios
INSERT INTO `Barrio` (`id_barrio`, `descripcion`, `id_localidad`) VALUES ('1', 'Centro', '1');
INSERT INTO `Barrio` (`id_barrio`, `descripcion`, `id_localidad`) VALUES ('2', 'Nva Cba', '1');
INSERT INTO `Barrio` (`id_barrio`, `descripcion`, `id_localidad`) VALUES ('3', 'Gral Paz', '1');
INSERT INTO `Barrio` (`id_barrio`, `descripcion`, `id_localidad`) VALUES ('4', 'Banda Norte', '2');

--domicilios
INSERT INTO `Domicilio` (`id_domicilio`, `calle`, `latitud`, `longitud`, `numero`, `id_barrio`) VALUES ('1', 'Chile', '1', '1', '10', '1');
INSERT INTO `Domicilio` (`id_domicilio`, `calle`, `latitud`, `longitud`, `numero`, `id_barrio`) VALUES ('2', 'Colombia', '1', '1', '50', '1');
INSERT INTO `Domicilio` (`id_domicilio`, `calle`, `latitud`, `longitud`, `numero`, `id_barrio`) VALUES ('3', 'Colon', '1', '1', '70', '1');

--usuarios
INSERT INTO `Usuario` (`id_usuario`, `password`, `username`) VALUES ('1',  MD5('1234'), 'ezecolombino');
INSERT INTO `Usuario` (`id_usuario`, `password`, `username`) VALUES ('2',  MD5('1234'), 'ppivato');
INSERT INTO `Usuario` (`id_usuario`, `password`, `username`) VALUES ('3',  MD5('1234'), 'jleiguarda');
INSERT INTO `Usuario` (`id_usuario`, `password`, `username`) VALUES ('4',  MD5('1234'), 'joelchehda');

--prioridades
INSERT INTO `Prioridad` (`id_prioridad`, `descripcion`) VALUES ('1', 'Alta');
INSERT INTO `Prioridad` (`id_prioridad`, `descripcion`) VALUES ('2', 'Media');
INSERT INTO `Prioridad` (`id_prioridad`, `descripcion`) VALUES ('3', 'Baja');

--tipos de incidentes
INSERT INTO `Tipo_Incidente` (`id_tipo_incidente`, `descripcion`) VALUES (1,  'Siniestro');
INSERT INTO `Tipo_Incidente` (`id_tipo_incidente`, `descripcion`) VALUES (2,  'Hurto');
INSERT INTO `Tipo_Incidente` (`id_tipo_incidente`, `descripcion`) VALUES (2,  'Incendio');