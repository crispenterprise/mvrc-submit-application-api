SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `genaudit` DEFAULT CHARACTER SET utf8 ;
USE `genaudit` ;

-- -----------------------------------------------------
-- Table `genaudit`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`person` (
  `person_id` INT(11) NOT NULL AUTO_INCREMENT,
  `trn` VARCHAR(9) NULL DEFAULT NULL,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `street_no` VARCHAR(15) NULL DEFAULT NULL,
  `street_name` VARCHAR(45) NULL DEFAULT NULL,
  `parish` VARCHAR(45) NULL DEFAULT NULL,
  `home_tel` VARCHAR(15) NULL DEFAULT NULL,
  `cell_tel` VARCHAR(15) NULL DEFAULT NULL,
  `create_username` VARCHAR(32) NULL DEFAULT NULL,
  `create_dtime` DATETIME NULL DEFAULT NULL,
  `update_username` VARCHAR(45) NULL DEFAULT NULL,
  `update_dtime` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE INDEX `trn_UNIQUE` (`trn` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `genaudit`.`user_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`user_account` (
  `account_no` INT(11) NOT NULL AUTO_INCREMENT,
  `person_id` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `date_joined` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `secret_question` VARCHAR(128) NULL DEFAULT NULL,
  `secret_answer` VARCHAR(45) NULL DEFAULT NULL,
  `status` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`account_no`),
  INDEX `fk_account_person_idx` (`person_id` ASC),
  CONSTRAINT `fk_account_person`
    FOREIGN KEY (`person_id`)
    REFERENCES `genaudit`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `genaudit`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`application` (
  `application_no` INT(11) NOT NULL AUTO_INCREMENT,
  `account_no` INT(11) NULL DEFAULT NULL,
  `plate_no` VARCHAR(32) NULL DEFAULT NULL,
  `renewal_period` INT(11) NULL DEFAULT NULL,
  `new_reg_expiry_date` DATETIME NULL DEFAULT NULL,
  `application_status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`application_no`),
  INDEX `fk_application_account_idx` (`account_no` ASC),
  CONSTRAINT `fk_application_account`
    FOREIGN KEY (`account_no`)
    REFERENCES `genaudit`.`user_account` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `genaudit`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`event` (
  `event_id` INT(11) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `genaudit`.`event_audit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`event_audit` (
  `audit_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL DEFAULT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `dtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `event_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`audit_id`),
  INDEX `event_id_idx` (`event_id` ASC),
  CONSTRAINT `event_id`
    FOREIGN KEY (`event_id`)
    REFERENCES `genaudit`.`event` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `genaudit`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`payment` (
  `payment_id` INT(11) NOT NULL AUTO_INCREMENT,
  `application_no` INT(11) NULL DEFAULT NULL,
  `payment_dtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `amount` DECIMAL(6,2) NULL DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX `fk_payment_application_idx` (`application_no` ASC),
  CONSTRAINT `fk_payment_application`
    FOREIGN KEY (`application_no`)
    REFERENCES `genaudit`.`application` (`application_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `genaudit`.`receipt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genaudit`.`receipt` (
  `receipt_no` INT(11) NOT NULL AUTO_INCREMENT,
  `payment_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`receipt_no`),
  INDEX `fk_receipt_payment_idx` (`payment_id` ASC),
  CONSTRAINT `fk_receipt_payment`
    FOREIGN KEY (`payment_id`)
    REFERENCES `genaudit`.`payment` (`payment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
