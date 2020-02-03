CREATE TABLE `contactapp`.`contact` (
  `idcontact` INT NOT NULL AUTO_INCREMENT,
  `lastname` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `address` VARCHAR(200) NULL,
  `email` VARCHAR(150) NULL,
  `birth` DATE NULL,
  PRIMARY KEY (`idcontact`));