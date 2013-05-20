SELECT `lb`.`spel_id`             AS `Spel_ID`,
       `lb`.`beurt_id`            AS `Beurt_ID`,
       `b`.`account_naam`         AS `Account_naam`,
       `l`.`lettertype_karkakter` AS `letter`,
       `lt`.`waarde`              AS `waarde`
FROM   `letterbakjeletter` `lb`
       JOIN `letter` `l`
         ON `lb`.`spel_id` = `l`.`spel_id`
            AND `lb`.`letter_id` = `l`.`id`
       JOIN `beurt` `b`
         ON `lb`.`beurt_id` = `b`.`id`
       JOIN `spel` `s`
         ON `l`.`spel_id` = `s`.`id`
       RIGHT JOIN `lettertype` `lt`
               ON `lt`.`letterset_code` = `s`.`letterset_naam`
                  AND `l`.`lettertype_karkakter` = `lt`.`karkakter`
WHERE  `s`.`id` = 511
       AND `b`.`account_naam` = 'marijntje42'
ORDER  BY `lb`.`beurt_id` ASC 
