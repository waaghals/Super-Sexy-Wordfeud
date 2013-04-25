SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`player`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`player` (
  `player_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `moderator` TINYINT(1) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`player_id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`challenge`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`challenge` (
  `challenge_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `challenger_id` INT UNSIGNED NOT NULL ,
  `opponent_id` INT UNSIGNED NOT NULL ,
  `status` ENUM('accepted', 'declined', 'pending') NOT NULL DEFAULT pending ,
  `created_at` DATETIME NOT NULL ,
  PRIMARY KEY (`challenge_id`) ,
  INDEX `fk_challenger_id_idx` (`challenger_id` ASC) ,
  INDEX `fk_opponent_id_idx` (`opponent_id` ASC) ,
  CONSTRAINT `fk_challenger_id`
    FOREIGN KEY (`challenger_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_opponent_id`
    FOREIGN KEY (`opponent_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`game`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`game` (
  `game_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `challenge_id` INT UNSIGNED NOT NULL ,
  `winner_id` INT UNSIGNED NULL ,
  PRIMARY KEY (`game_id`) ,
  INDEX `fk_challenge_idx` (`challenge_id` ASC) ,
  INDEX `fk_winner_idx` (`winner_id` ASC) ,
  CONSTRAINT `fk_challenge`
    FOREIGN KEY (`challenge_id` )
    REFERENCES `mydb`.`challenge` (`challenge_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_winner`
    FOREIGN KEY (`winner_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`letter`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`letter` (
  `letter` CHAR(1) NOT NULL ,
  `value` INT NOT NULL ,
  `start_amount` TINYINT NOT NULL ,
  PRIMARY KEY (`letter`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`chat`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`chat` (
  `game_id` INT UNSIGNED NOT NULL ,
  `player_id` INT UNSIGNED NOT NULL ,
  `message` VARCHAR(500) NOT NULL ,
  `send_at` DATETIME NOT NULL ,
  PRIMARY KEY (`game_id`) ,
  INDEX `fk_sender_id_idx` (`player_id` ASC) ,
  CONSTRAINT `fk_game_id`
    FOREIGN KEY (`game_id` )
    REFERENCES `mydb`.`game` (`game_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sender_id`
    FOREIGN KEY (`player_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`stack`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`stack` (
  `game_id` INT UNSIGNED NOT NULL ,
  `letter` CHAR(1) NOT NULL ,
  `amount` INT NOT NULL ,
  PRIMARY KEY (`game_id`) ,
  INDEX `fk_letter_idx` (`letter` ASC) ,
  CONSTRAINT `fk_letter`
    FOREIGN KEY (`letter` )
    REFERENCES `mydb`.`letter` (`letter` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_id`
    FOREIGN KEY (`game_id` )
    REFERENCES `mydb`.`game` (`game_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`word`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`word` (
  `word_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `word` VARCHAR(15) NOT NULL ,
  `proposer_id` INT UNSIGNED NULL ,
  `status` ENUM('accepted', 'denied', 'pending') NOT NULL DEFAULT pending ,
  PRIMARY KEY (`word_id`) ,
  UNIQUE INDEX `word_UNIQUE` (`word` ASC) ,
  INDEX `fk_proposer_idx` (`proposer_id` ASC) ,
  CONSTRAINT `fk_proposer`
    FOREIGN KEY (`proposer_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`move`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`move` (
  `move_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `player_id` INT UNSIGNED NOT NULL ,
  `game_id` INT UNSIGNED NOT NULL ,
  `word_id` INT UNSIGNED NULL ,
  `points` TINYINT NOT NULL ,
  `type` ENUM('skipped', 'swapped', 'resigned', 'played') NOT NULL ,
  PRIMARY KEY (`move_id`) ,
  INDEX `fk_player_idx` (`player_id` ASC) ,
  INDEX `fk_game_idx` (`game_id` ASC) ,
  INDEX `fk_word_idx` (`word_id` ASC) ,
  CONSTRAINT `fk_player`
    FOREIGN KEY (`player_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game`
    FOREIGN KEY (`game_id` )
    REFERENCES `mydb`.`game` (`game_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_word`
    FOREIGN KEY (`word_id` )
    REFERENCES `mydb`.`word` (`word_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`layout`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`layout` (
  `xPos` TINYINT UNSIGNED NOT NULL ,
  `yPos` TINYINT UNSIGNED NOT NULL ,
  `letter_multiplier` TINYINT UNSIGNED NOT NULL DEFAULT 0 ,
  `word_multiplier` TINYINT UNSIGNED NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`xPos`, `yPos`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`board`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`board` (
  `move_id` INT UNSIGNED NOT NULL ,
  `letter` CHAR(1) NOT NULL ,
  `xPos` TINYINT UNSIGNED NOT NULL ,
  `yPos` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`move_id`, `letter`, `xPos`, `yPos`) ,
  INDEX `fk_letter_idx` (`letter` ASC) ,
  INDEX `fk_move_idx` (`move_id` ASC) ,
  INDEX `fk_x_idx` (`xPos` ASC) ,
  INDEX `fk_y_idx` (`yPos` ASC) ,
  CONSTRAINT `fk_letter`
    FOREIGN KEY (`letter` )
    REFERENCES `mydb`.`letter` (`letter` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_move`
    FOREIGN KEY (`move_id` )
    REFERENCES `mydb`.`move` (`move_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_x`
    FOREIGN KEY (`xPos` )
    REFERENCES `mydb`.`layout` (`xPos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_y`
    FOREIGN KEY (`yPos` )
    REFERENCES `mydb`.`layout` (`yPos` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`rack`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`rack` (
  `move_id` INT UNSIGNED NOT NULL ,
  `letter` CHAR(1) NOT NULL ,
  PRIMARY KEY (`move_id`) ,
  INDEX `fk_letter_idx` (`letter` ASC) ,
  INDEX `fk_move_id_idx` (`move_id` ASC) ,
  CONSTRAINT `fk_move_id`
    FOREIGN KEY (`move_id` )
    REFERENCES `mydb`.`move` (`move_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_letter`
    FOREIGN KEY (`letter` )
    REFERENCES `mydb`.`letter` (`letter` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`competition`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`competition` (
  `compitition_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `owner_id` INT UNSIGNED NOT NULL ,
  `type` ENUM('open', 'closed', 'invite', 'accept') NOT NULL DEFAULT open ,
  `enddate` DATE NOT NULL ,
  PRIMARY KEY (`compitition_id`) ,
  INDEX `fk_owner_idx` (`owner_id` ASC) ,
  CONSTRAINT `fk_owner`
    FOREIGN KEY (`owner_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`comp_player`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`comp_player` (
  `compitition_id` INT UNSIGNED NOT NULL ,
  `player_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`compitition_id`, `player_id`) ,
  INDEX `fk_player_idx` (`player_id` ASC) ,
  INDEX `fk_comp_idx` (`compitition_id` ASC) ,
  CONSTRAINT `fk_comp`
    FOREIGN KEY (`compitition_id` )
    REFERENCES `mydb`.`competition` (`compitition_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player`
    FOREIGN KEY (`player_id` )
    REFERENCES `mydb`.`player` (`player_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
