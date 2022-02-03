ShorterUrl

Для запуска приложения понадобится создать в PostgreSQL схему shorter. 
Запустить приложение:
"src/main/java/com/nord/shorter/ShorterApplication.java"

Hibernate создаст таблицы.
Далее ввести 2 SQL-комманды:
"
INSERT INTO shorter.role(id, name)
VALUES (1, 'ROLE_USER')

INSERT INTO shorter.role(id, name)
VALUES (2, 'ROLE_ADMIN')
"
После чего можно регистрироватся и пользоваться приложением.
Особенности приложения:
- В файле "src/main/resources/application.properties" надо поменять настройку (spring.jpa.hibernate.ddl-auto=create на spring.jpa.hibernate.ddl-auto=validate или spring.jpa.hibernate.ddl-auto=update).
- При регистрации стандартная роль устанавливается как "USER", для смены роли требуется указать "ROLE_ADMIN" в файле "src/main/java/com/nord/shorter/service/UserService.java" и перезапустить приложение.

На данный момент в приложении реализованы следующие пункты:
1. Возможность создать короткую ссылку по полному URL, алгоритм генерации - на Ваше усмотрение. Короткая ссылка должна содержать символы из диапазона: [0-9, a-z].
2. По известной короткой ссылке осуществить перенаправление браузера пользователя на исходный URL.
3. Управление ссылками должно быть доступно только ограниченному кругу лиц.
4. Предусмотреть возможность удаления зарегистрированных коротких ссылок.

Стэк:
-Spring
-Hibernate
-PostgreSQL
-MVC pattern