CREATE TABLE `users` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(256) NOT NULL
);

CREATE TABLE `movies` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(256) NOT NULL,
    `duration` INT NOT NULL,
    `genre` VARCHAR(50) NOT NULL,
    `year` DATE NOT NULL
);

create table `rooms` (
`id` bigint(20) not null auto_increment primary key
);

create table `projections` (
`id` bigint(20) not null auto_increment primary key,
`room_id` bigint(20) not null,
`movie_id` bigint(20) not null,
`projection_time` datetime not null,
CONSTRAINT `FK_projections_room_id`
FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
CONSTRAINT `FK_projections_movie_id`
FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table `seats` (
`id` bigint(20) not null auto_increment primary key,
`is_taken` boolean not null,
`room_id` bigint(20) not null,
CONSTRAINT `FK_seats_room_id`
FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE `tickets` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(20) NOT NULL,
    `seat_id` bigINT(20) NOT NULL,
    `projection_id` bigint(20) not null,
    `bought_on` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `FK_tickets_projection_id`
    FOREIGN KEY (`projection_id`) REFERENCES `projections` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT `FK_tickets_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);