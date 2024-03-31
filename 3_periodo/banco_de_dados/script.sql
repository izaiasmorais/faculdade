-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema UFPI
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema UFPI
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `UFPI` DEFAULT CHARACTER SET utf8 ;
USE `UFPI` ;

-- -----------------------------------------------------
-- Table `UFPI`.`Contas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `UFPI`.`Contas` (
  `idContas` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `valor` DOUBLE NULL,
  `categoria` VARCHAR(45) NULL,
  `dataVencimento` DATETIME NULL,
  PRIMARY KEY (`idContas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UFPI`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `UFPI`.`Usuario` (
  `idUsuario` INT NOT NULL,
  `nome` VARCHAR(75) NULL,
  `email` VARCHAR(75) NULL,
  `Contas_idContas` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  INDEX `fk_Usuario_Contas_idx` (`Contas_idContas` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Contas`
    FOREIGN KEY (`Contas_idContas`)
    REFERENCES `UFPI`.`Contas` (`idContas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UFPI`.`Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `UFPI`.`Categorias` (
  `idCategorias` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idCategorias`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `UFPI`.`Contas_has_Categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `UFPI`.`Contas_has_Categorias` (
  `Contas_idContas` INT NOT NULL,
  `Categorias_idCategorias` INT NOT NULL,
  PRIMARY KEY (`Contas_idContas`, `Categorias_idCategorias`),
  INDEX `fk_Contas_has_Categorias_Categorias1_idx` (`Categorias_idCategorias` ASC) VISIBLE,
  INDEX `fk_Contas_has_Categorias_Contas1_idx` (`Contas_idContas` ASC) VISIBLE,
  CONSTRAINT `fk_Contas_has_Categorias_Contas1`
    FOREIGN KEY (`Contas_idContas`)
    REFERENCES `UFPI`.`Contas` (`idContas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Contas_has_Categorias_Categorias1`
    FOREIGN KEY (`Categorias_idCategorias`)
    REFERENCES `UFPI`.`Categorias` (`idCategorias`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
