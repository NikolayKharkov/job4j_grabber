# job4j_tracker
Агрегатор Java Вакансий
Проект, который находит вакансии на позицию "JAVA разработчик".
Найденные вакансии будут храниться в БД.
В качестве СУБД используется "postgres".
Система запускается по расписанию.
Период запуска указывается в настройках - app.properties.
В проект можно добавить новые сайты без изменения кода.
В проекте можно сделать параллельный парсинг сайтов.