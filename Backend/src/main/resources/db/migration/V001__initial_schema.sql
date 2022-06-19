CREATE TABLE `users` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `password` VARCHAR(256) NOT NULL
);

CREATE TABLE `rooms` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `seats` INT NOT NULL
);

CREATE TABLE `movies` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(256) NOT NULL,
    `duration` INT NOT NULL,
    `genre` VARCHAR(50) NOT NULL,
    `year` DATE NOT NULL
);

CREATE TABLE `tickets` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(20) NOT NULL,
    `movie_id` BIGINT(20) NOT NULL,
    `room_id` INT NOT NULL,
    `row_number` INT NOT NULL,
    `seat_number` INT NOT NULL,
    `bought_on` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `FK_tickets_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT `FK_tickets_movie_id`
    FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT `FK_tickets_room_id`
    FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE TABLE `movies_by_rooms` (
    `movie_id` BIGINT(20) NOT NULL,
    `room_id` INT NOT NULL,
    `projection_time` TIMESTAMP NOT NULL,
    PRIMARY KEY(`movie_id`, `room_id`, `projection_time`),
    CONSTRAINT `FK_movies_by_rooms_movie_id`
    FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT `FK_movies_by_rooms_room_id`
    FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON UPDATE NO ACTION ON DELETE CASCADE
);