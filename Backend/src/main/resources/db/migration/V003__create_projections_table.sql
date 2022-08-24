create table `projections` (
`id` bigint(20) not null auto_increment primary key,
`room_id` bigint(20) not null,
`movie_id` bigint(20) not null
);

drop table `movies_by_rooms`