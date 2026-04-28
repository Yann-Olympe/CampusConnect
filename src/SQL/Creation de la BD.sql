-- =========================================
-- BASE DE DONNEES
-- =========================================
CREATE DATABASE IF NOT EXISTS campus_connect;
USE campus_connect;

-- =========================================
-- TABLE PERSONNE
-- =========================================
CREATE TABLE Personne (
    id_personne INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    date_naissance DATE NOT NULL
);

-- =========================================
-- TABLE ETUDIANT
-- =========================================
CREATE TABLE Etudiant (
    id_etudiant INT AUTO_INCREMENT PRIMARY KEY,
    matricule VARCHAR(50) UNIQUE NOT NULL,
    anneeEtude date,
    filiere VARCHAR(100),
    id_personne INT NOT NULL,
    FOREIGN KEY (id_personne) REFERENCES Personne(id_personne)
        ON DELETE CASCADE
);

-- =========================================
-- TABLE ENSEIGNANT
-- =========================================
CREATE TABLE Enseignant (
    id_enseignant INT AUTO_INCREMENT PRIMARY KEY,
	matricule VARCHAR(50) UNIQUE NOT NULL,
    statut ENUM('PERMANENT', 'VACATAIRE') NOT NULL,
    departement VARCHAR(100),
    id_personne INT NOT NULL,
    FOREIGN KEY (id_personne) REFERENCES Personne(id_personne)
        ON DELETE CASCADE
);

-- =========================================
-- TABLE ADMIN
-- =========================================
CREATE TABLE Admin (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    role_ VARCHAR(50),
    id_personne INT NOT NULL,
    FOREIGN KEY (id_personne) REFERENCES Personne(id_personne)
        ON DELETE CASCADE
);

-- =========================================
-- TABLE COURS
-- =========================================
CREATE TABLE Cours (
    id_cours INT AUTO_INCREMENT PRIMARY KEY,
    code_ VARCHAR(20) UNIQUE NOT NULL,
    intitule VARCHAR(150) NOT NULL,
    description_ TEXT,
    volume_horaire INT,
    id_enseignant INT,
    FOREIGN KEY (id_enseignant) REFERENCES Enseignant(id_enseignant)
        ON DELETE SET NULL
);

-- =========================================
-- TABLE GROUPE
-- =========================================
CREATE TABLE Groupe (
    id_groupe INT AUTO_INCREMENT PRIMARY KEY,
    type_ ENUM('CM', 'TD', 'TP') NOT NULL,
    capacite INT NOT NULL,
    id_cours INT NOT NULL,
    id_enseignant INT,
    FOREIGN KEY (id_cours) REFERENCES Cours(id_cours)
        ON DELETE CASCADE,
    FOREIGN KEY (id_enseignant) REFERENCES Enseignant(id_enseignant)
        ON DELETE SET NULL
);

-- =========================================
-- TABLE SALLE
-- =========================================
CREATE TABLE Salle (
    id_salle INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) UNIQUE NOT NULL,
    capacite INT NOT NULL,
	type_ ENUM('CM', 'TD', 'TP') NOT NULL
);

-- =========================================
-- TABLE INSCRIPTION
-- =========================================
CREATE TABLE Inscription (
    id_inscription INT AUTO_INCREMENT PRIMARY KEY,
    id_etudiant INT NOT NULL,
    id_groupe INT NOT NULL,
    
    UNIQUE (id_etudiant, id_groupe), -- empêche double inscription
    
    FOREIGN KEY (id_etudiant) REFERENCES Etudiant(id_etudiant)
        ON DELETE CASCADE,
    FOREIGN KEY (id_groupe) REFERENCES Groupe(id_groupe)
        ON DELETE CASCADE
);

-- =========================================
-- TABLE NOTE
-- =========================================
CREATE TABLE Note (
    id_note INT AUTO_INCREMENT PRIMARY KEY,
    CC FLOAT NOT NULL CHECK (CC >= 0),
	SN FLOAT NOT NULL CHECK (SN >= 0),
    coefficient FLOAT DEFAULT 1,
    id_inscription INT NOT NULL,
    FOREIGN KEY (id_inscription) REFERENCES Inscription(id_inscription)
        ON DELETE CASCADE
);

-- =========================================
-- TABLE SEANCE
-- =========================================
CREATE TABLE Seance (
    id_seance INT AUTO_INCREMENT PRIMARY KEY,
    date_seance DATE NOT NULL,
    heure_debut TIME NOT NULL,
    heure_fin TIME NOT NULL,
    
    id_groupe INT NOT NULL,
    id_enseignant INT NOT NULL,
    id_salle INT NOT NULL,

    FOREIGN KEY (id_groupe) REFERENCES Groupe(id_groupe)
        ON DELETE CASCADE,
    FOREIGN KEY (id_enseignant) REFERENCES Enseignant(id_enseignant)
        ON DELETE CASCADE,
    FOREIGN KEY (id_salle) REFERENCES Salle(id_salle)
        ON DELETE CASCADE
)