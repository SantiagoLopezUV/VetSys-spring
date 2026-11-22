-- Dueños
INSERT IGNORE INTO dueno (id_dueno, nombre, telefono, correo, direccion) VALUES
(1, 'Carlos Alberto Pérez Gómez', '3105551234', 'carlos.perez@gmail.com', 'Calle 25 # 28-15, Barrio Victoria, Tuluá'),
(2, 'María Fernanda Rodríguez Soto', '3158884567', 'maria.rodriguez@hotmail.com', 'Carrera 26 # 34-10, Barrio Alvernia, Tuluá'),
(3, 'Andrés Felipe Morales Ríos', '3209993322', 'andres.morales@yahoo.es', 'Transversal 12 # 22-45, Barrio Céspedes, Tuluá'),
(4, 'Laura Sofía Henao Zapata', '3004447788', 'laura.henao@outlook.com', 'Calle 28 # 19-30, Barrio El Príncipe, Tuluá'),
(5, 'Diego Fernando Ortiz Castro', '3182229911', 'diego.ortiz@gmail.com', 'Carrera 30 # 24-18, Barrio Salesiano, Tuluá');

-- Mascotas
INSERT IGNORE INTO mascota (id_mascota, nombre, especie, raza, edad, dueno_id) VALUES
(1, 'Max', 'Perro', 'Golden Retriever', 4, 1),
(2, 'Rocky', 'Perro', 'Pastor Alemán', 3, 1),
(3, 'Luna', 'Gato', 'Siamés', 2, 2),
(4, 'Michi', 'Gato', 'Persa', 5, 3),
(5, 'Toby', 'Perro', 'Beagle', 1, 4),
(6, 'Copito', 'Conejo', 'Holandés Enano', 2, 5);

-- Citas
INSERT IGNORE INTO cita (id_cita, fecha, motivo, veterinario, mascota_id) VALUES
(1, '2026-11-10', 'Vacunación anual y desparasitación interna', 'Dra. Valentina Castro', 1),
(2, '2026-11-12', 'Control post-operatorio de esterilización', 'Dr. Roberto Méndez', 3),
(3, '2026-11-15', 'Cojera leve en pata trasera derecha', 'Dr. Alejandro Gómez', 2),
(4, '2026-11-18', 'Pérdida de apetito y decaimiento general', 'Dra. Valentina Castro', 4),
(5, '2026-11-20', 'Chequeo preventivo de cachorro y primera vacuna', 'Dr. Roberto Méndez', 5),
(6, '2026-11-22', 'Revisión dental y corte preventivo de uñas', 'Dra. Carolina Duque', 6);

-- Tratamientos
INSERT IGNORE INTO tratamiento (id_tratamiento, descripcion, dosis, cita_id) VALUES
(1, 'Vacuna séxtuple canina + Antiparasitario TotalVet', '1 ampolla subcutánea + 1 tableta oral dosis única', 1),
(2, 'Meloxicam 0.5 mg/ml suspensión oral', '0.2 ml cada 24 horas vía oral por 5 días', 2),
(3, 'Curación antiséptica con Clorhexidina tópica', 'Aplicar en herida 2 veces al día por 7 días', 2),
(4, 'Carprofeno 25 mg antiinflamatorio no esteroideo', '1 tableta cada 12 horas con alimento por 4 días', 3),
(5, 'Complejo vitamínico B + Protector gástrico', '0.5 ml vía oral cada 12 horas por 3 días', 4),
(6, 'Vacuna Puppy DP + Jarabe multivitamínico', '1 dosis subcutánea y 2.5 ml diario por 15 días', 5);
