

CREATE TABLE public.Levels (
                level_Id INTEGER NOT NULL,
                levelName VARCHAR NOT NULL,
                semCount INTEGER NOT NULL,
                CONSTRAINT level_id PRIMARY KEY (level_Id)
);


CREATE TABLE public.Departments (
                department_Id INTEGER NOT NULL,
                fullName VARCHAR NOT NULL,
                shortName VARCHAR,
                parentId INTEGER NOT NULL,
                type INTEGER NOT NULL,
                number INTEGER NOT NULL,
                code VARCHAR,
                location VARCHAR,
                mail VARCHAR,
                www VARCHAR,
                CONSTRAINT department_id PRIMARY KEY (department_Id)
);


CREATE TABLE public.Profiles (
                profile_Id VARCHAR NOT NULL,
                profileName VARCHAR NOT NULL,
                trend_Id VARCHAR NOT NULL,
                level_Id INTEGER NOT NULL,
                department_Id INTEGER NOT NULL,
                CONSTRAINT profile_id PRIMARY KEY (profile_Id)
);


CREATE TABLE public.Groups (
                group_Id INTEGER NOT NULL,
                groupNumber VARCHAR NOT NULL,
                formYear INTEGER NOT NULL,
                profile_Id VARCHAR NOT NULL,
                CONSTRAINT group_id PRIMARY KEY (group_Id)
);


ALTER TABLE public.Profiles ADD CONSTRAINT trends_profiles_fk
FOREIGN KEY (trend_Id)
REFERENCES public.Trends (trend_Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Profiles ADD CONSTRAINT levels_profiles_fk
FOREIGN KEY (level_Id)
REFERENCES public.Levels (level_Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Profiles ADD CONSTRAINT departments_profiles_fk
FOREIGN KEY (department_Id)
REFERENCES public.Departments (department_Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Groups ADD CONSTRAINT profiles_groups_fk
FOREIGN KEY (profile_Id)
REFERENCES public.Profiles (profile_Id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

-----------------------------------------------------------------------------

insert into Departments
    (department_Id, fullName, shortName, parentId, type, number)
values
    (1, 'Факультет радиотехники и телекоммуникаций', 'ФРТ', 0, 1, 1),
    (2, 'Факультет электроники', 'ФЭЛ', 0, 1, 2),
    (3, 'Факультет компьютерных технологий и информатики', 'ФКТИ', 0, 1, 3),
    (4, 'Факультет электротехники и автоматики', 'ФЭА', 0, 1, 4),
    (5, 'Факультет информационно-измерительных и биотехнических систем', 'ФИБС', 0, 1, 5),
    (6, 'Факультет экономики и менеджмента', 'ФЭМ', 0, 1, 6),
    (7, 'Гуманитарный факультет', 'ГФ', 0, 1, 7);

insert into Departments
    (department_Id, fullName, shortName, parentId, type, number)
values
    (8,  'Кафедра радиотехнических систем',                                        'РС', 1, 1, 1),
    (9,  'Кафедра радиоэлектронных средств',                                      'РЭС', 1, 1, 2),
    (10, 'Кафедра телевидения и видеотехники',                                     'ТВ', 1, 1, 3),
    (11, 'Кафедра теоретических основ радиотехники',                              'ТОР', 1, 1, 4),
    (12, 'Кафедра микрорадиоэлектроники и технологии радиоаппаратуры',            'МИТ', 1, 1, 5);

insert into Departments
    (department_Id, fullName, shortName, parentId, type, number)
values
    (13, 'Кафедра радиотехнической электроники',                                  'РТЭ', 2, 1, 1),
    (14, 'Кафедра электронных приборов и устройств',                              'ЭПУ', 2, 1, 2),
    (15, 'Кафедра физической электроники и технологии',                           'ФЭТ', 2, 1, 3),
    (16, 'Кафедра квантовой электроники и оптико-электронных приборов',          'КЭОП', 2, 1, 4),
    (17, 'Кафедра микро- и наноэлектроники',                                      'МНЭ', 2, 1, 5),
    (18, 'Кафедра высшей математики №1',                                        'ВМ-1', 2, 1, 6),
    (19, 'Кафедра физики',                                                          'Ф', 2, 1, 7);

insert into Departments
    (department_Id, fullName, shortName, parentId, type, number)
values
    (20, 'Кафедра систем автоматизированного проектирования',                    'САПР', 3, 1, 1),
    (21, 'Кафедра автоматики и процессов управления',                             'АПУ', 3, 1, 2),
    (22, 'Кафедра автоматизированных систем обработки информации и управления', 'АСОИУ', 3, 1, 3),
    (23, 'Кафедра математического обеспечения и применения ЭВМ',               'МО ЭВМ', 3, 1, 4),
    (24, 'Кафедра вычислительной техники',                                         'ВТ', 3, 1, 5),
    (25, 'Кафедра высшей математики №2',                                        'ВМ-2', 3, 1, 6);

select * from Departments;
delete from Departments;

-----------------------------------------------------------------------------

insert into Levels
    (level_Id, levelName, semCount)
values
(1, 'Бакалавриат',  8),
(2, 'Магистратура', 4),
(3, 'Специалитет',  10);    

select * from Levels;
delete from Levels;

-----------------------------------------------------------------------------

insert into Trends
    (trend_Id, trendName)
values
('220400.62', 'Управление в технических системах'),
('230400.62', 'Информационные системы и технологии');    

select * from Trends;
delete from Trends;

-----------------------------------------------------------------------------

insert into Profiles
    (profile_Id, profileName, trend_Id, level_Id, department_Id)
values
('220401.62', 'Управление и информатика в технических системах', '220400.62', 1, 21),
('230406.62', 'Информационные системы и технологии в бизнесе',   '230400.62', 1, 21);

select * from Profiles;
delete from Profiles;

-----------------------------------------------------------------------------

insert into Groups
    (group_Id, groupNumber, formYear, profile_Id)
values
(1, '3391', 2013, '220401.62'),
(2, '3392', 2013, '220401.62'),
(3, '3371', 2013, '230406.62'),
(4, '2391', 2012, '220401.62'),
(5, '2371', 2012, '230406.62'),
(6, '1391', 2011, '220401.62'),
(7, '1371', 2011, '230406.62'),
(8, '0391', 2010, '220401.62'),
(9, '0371', 2010, '230406.62');

select * from Groups;
delete from Groups;

-----------------------------------------------------------------------------
select
    (2013 - g.formYear + 1) as courseNum,
    t.trend_Id as tId,
    t.trendName as tName,
    p.profile_Id as pId,
    p.profileName as pName,
    g.group_Id as gId,
    g.groupNumber as gName
from
    Trends t right join ((Levels l inner join Profiles p on l.level_Id = p.level_Id) right join Groups g on p.profile_Id = g.profile_Id) on t.trend_Id = p.trend_Id
where
    (2013 - g.formYear >= 0) AND (2013 - g.formYear <= l.semCount / 2 - 1)
order by
    g.formYear desc, 
    g.groupNumber;

-----------------------------------------------------------------------------
create table logs(text varchar, added timestamp);
drop table logs;
create or replace function put_groups_into_log() returns trigger as '
declare
    strgn varchar;
    strres varchar;
begin
    strgn = NEW.groupnumber;
    strres := TG_OP || '' '' || strgn;
    insert into logs (text, added) values (strres,NOW());
    return NEW;
end;
' language plpgsql;


drop trigger tg_Groups;

create trigger tg_Groups
after update or insert or delete on Groups for each row execute procedure put_groups_into_log();
select * from Departments;
select * from logs;