SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `scrabble` ;
CREATE SCHEMA IF NOT EXISTS `scrabble` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `scrabble` ;

-- -----------------------------------------------------
-- Table `scrabble`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`player` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`player` (
  `player_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `moderator` TINYINT(1) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`player_id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`challenge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`challenge` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`challenge` (
  `challenge_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `challenger_id` INT UNSIGNED NOT NULL ,
  `opponent_id` INT UNSIGNED NOT NULL ,
  `status` ENUM('accepted', 'declined', 'pending') NOT NULL DEFAULT 'pending' ,
  `created_at` DATETIME NOT NULL ,
  PRIMARY KEY (`challenge_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`game` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`game` (
  `game_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `challenge_id` INT UNSIGNED NOT NULL ,
  `winner_id` INT UNSIGNED NULL ,
  PRIMARY KEY (`game_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`letter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`letter` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`letter` (
  `letter` CHAR(1) NOT NULL ,
  `value` INT NOT NULL ,
  `start_amount` TINYINT NOT NULL ,
  PRIMARY KEY (`letter`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`chat` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`chat` (
  `game_id` INT UNSIGNED NOT NULL ,
  `player_id` INT UNSIGNED NOT NULL ,
  `message` VARCHAR(500) NOT NULL ,
  `send_at` DATETIME NOT NULL ,
  PRIMARY KEY (`game_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`stack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`stack` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`stack` (
  `game_id` INT UNSIGNED NOT NULL ,
  `letter` CHAR(1) NOT NULL ,
  `amount` INT NOT NULL ,
  PRIMARY KEY (`game_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`word`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`word` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`word` (
  `word_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `word` VARCHAR(15) NOT NULL ,
  `proposer_id` INT UNSIGNED NULL ,
  `status` ENUM('accepted', 'denied', 'pending') NOT NULL DEFAULT 'pending' ,
  PRIMARY KEY (`word_id`) ,
  UNIQUE INDEX `word_UNIQUE` (`word` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`move`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`move` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`move` (
  `move_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `player_id` INT UNSIGNED NOT NULL ,
  `game_id` INT UNSIGNED NOT NULL ,
  `word_id` INT UNSIGNED NULL ,
  `points` TINYINT NOT NULL ,
  `type` ENUM('skipped', 'swapped', 'resigned', 'played') NOT NULL ,
  PRIMARY KEY (`move_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`layout`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`layout` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`layout` (
  `xPos` TINYINT UNSIGNED NOT NULL ,
  `yPos` TINYINT UNSIGNED NOT NULL ,
  `letter_multiplier` TINYINT UNSIGNED NOT NULL DEFAULT 0 ,
  `word_multiplier` TINYINT UNSIGNED NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`xPos`, `yPos`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`board` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`board` (
  `move_id` INT UNSIGNED NOT NULL ,
  `letter` CHAR(1) NOT NULL ,
  `xPos` TINYINT UNSIGNED NOT NULL ,
  `yPos` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`move_id`, `letter`, `xPos`, `yPos`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`rack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`rack` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`rack` (
  `move_id` INT UNSIGNED NOT NULL ,
  `letter` CHAR(1) NOT NULL ,
  PRIMARY KEY (`move_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`competition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`competition` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`competition` (
  `compitition_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `owner_id` INT UNSIGNED NOT NULL ,
  `type` ENUM('open', 'closed', 'invite', 'accept') NOT NULL DEFAULT 'open' ,
  `enddate` DATE NOT NULL ,
  PRIMARY KEY (`compitition_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scrabble`.`comp_player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `scrabble`.`comp_player` ;

CREATE  TABLE IF NOT EXISTS `scrabble`.`comp_player` (
  `compitition_id` INT UNSIGNED NOT NULL ,
  `player_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`compitition_id`, `player_id`) )
ENGINE = InnoDB;

USE `scrabble` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

