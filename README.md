# throughProject
Задача Коллеги, я сформулировал общую для всех сквозную задачу. Она позволит изучить Java Core, Unit Testing, основные Design Patterns, Рефакторинг с упором на переход от процедурного к ООП решению и обратно ... а также другие полезные разделы, такие как форматы файлов, регулярные выражения и каплю UI если останутся силы!

Необходимо реализовать консольное приложение, которое:

Читает данные из входного файла;
Обрабатывает полученную информацию;
Записывает данные в выходной файл;
Входной и выходной файл могут быть следующих форматов: plain text, xml,html, json, yaml, protobuf. Также входные и выходные файлы могут быть архивированы и зашифрованы, разными engines (только архивирован, только зашифрован, сперва архивирован, а потом зашифрован и наоборот).

«Тип» входного и выходного файла задаются параметрами консоли (или элементами UI, скажем checkbox-ами). Приложение реализовать двумя способами: без использования Design Patterns и c использованием Design Patterns (Decorator и Builder … можно оформить Builder в виде Singleton-а), сравнить реализации.

Обработка информации на первом этапе: найти все арифметические операции во входном файле и заменить на результаты в выходном файле. Реализовать фильтрацию двумя способами без использования регулярных выражений и с использованием регулярных выражений (а также третьим :) найти библиотеку, которая все делает за вас, парсинг и калькуляцию, такие есть и не одна). Провести сравнительный анализ 2-х вариантов реализации.

Следующие шаги по нашей задаче:

Добавить UI: a. консольный; b. использую любую графическую библиотеку Java на Ваш выбор; c. сравнить CLI и UI based реализации; d. поговорить с одногруппниками и сравнить различные графические Java библиотеки;
Реализовать логику как Web Service: a. Rest, используя любой Java engine; b. SOAP, используя любой Java engine; c. GraphQL, используя любой Java engine; c. Сравнить Rest \ Soap \ GraphQL реализации; d. поговорить с одногруппниками и сравнить различные Rest \ Soap \ GraphQL Java engines;
Соединить все вместе UI и Web Service;
Примеры атомарных подзадач: Чтение \ запись текстовый файл; Чтение \ запись xml файл (используя специальный API для чтения XML и не используя – чтение построчно); Чтение \ запись json файл (используя специальный API для чтения XML и не используя – чтение построчно); и так далее … Архивация \ де Архивация файла используя ту или иную реализацию Zip; Архивация \ де Архивация файла используя ту или иную реализацию Rar; Шифровка \ деШифровка файла используя любую библиотеку шифрования; Покрыть все эти атомарные задачи Unit Test-ами; Каждая из атомарный задач простая как грабли, в несколько строк кода, причем решение можно нагуглить даже не зная синтаксиса языка.

Книги Какие книги в рамках курса нужно обязательно прочитать:

Любую хорошую книгу по Java (ниже предложено 3 варианта);
Гради Буч "ООП" - первая часть, буквально 30 страниц (один вечер);
Кент Бэк "Экстремальное программирование" (небольшая книга, одни выходные);
Мартин Фаулер "Рефакторинг" (настольная книга на время курса, читать каждый день по 1 виду рефакторинга);
GoF "Design Patterns" (настольная книга на время курса)
Роберт Мартин "Чистая Архитектура" (точечные главы при подготовке к экзамену);
Книги по Java: • Head first java • Java a beginner’s guide • think in java - начать с нее

Очень полезная книга со сквозным примером на Java, показывает, как правильно писать Unit Test-ы: Kent Beck «Extreme programming. Development through testing», книга есть на Русском и Английском языках, думаю, должна быть в бумажном варианте в нашей библиотеке.

Гради Буч "Объектно-Ориентированный анализ и проектирование" - прочитать первую часть (буквально 30 страниц включая картинки)

Literature (все книги есть в инете на Русском языке, уверен некоторые можно найти в Библиотеке)

Grady Booch “Object oriented analysis and design with C++ examples” Notes: Do not be afraid of С++ examples, 95% of the material is conceptual, and not depending on the exact programming language From my point of view it’s one of the best books for real learning what OOP is. It might look too simple for you – which makes it even more pleasant to read before going to bed. EASY
Martin Fowler «Refactoring» Notes: IMHO strongly recommend to read from the beginning to the end, twice in a row, to make the ideas of the book part of your life and active professional skill. (I’ve done it myself and recommend to everybody). You should put the additional attention to the refactoring “Replace Conditional with Polymorphism” and vice versa “Replace Polymorphism with Conditional” and concepts “From Refactoring to Design Patterns” and “From Patterns to Refactoring” (the book and the following articles), those 2 pair refactoring and 2 pair concepts give us an exact answer where we should or shouldn’t use Design Patterns, also during designing the Architecture solutions for Automation, give us solution criteria when we should make existing lean architecture more complex and add patterns, and when should we make the existing “monster” simpler, and avoid the created complex architecture to a couple of if’s. EASY
D. Thomas, A. Hunt “The Pragmatic Programmer: From Journeyman to Master” Notes: Amazing book, consisting of a ton of atomic advices. IMHO should be read twice from the beginning to the very end and then you should look through different chapters while preparing to the discussion with the customer or an interview. EASY
Steve McConnell «Code compete» Notes: Do not be afraid of the size of that book...It should be read before going to bed from any place of the book...or by separate chapters, in order to get update and refresh the knowledge of different fiel. EASY
Gang of four “Design patterns” Notes: IMHO strongly recommend to read from the beginning to the very end, at minimum, 2 times in a row, in order to have contents of the book become your active professional baggage, and then implement each of the pattern on your personal, even training project. MEDIUM
«Pattern-Oriented Software Architecture» Volume 1-3 Notes: IMHO very good book about architecture, recommend to read from the beginning to the very end. VERY HARD
«Domain Specific Languages», Martin Fowler Notes: IMHO recommend to read from the beginning to the end, because creating DSL – a regular practice in Automated testing. HARD
«Patterns of Enterprise Application Architecture, Martin Fowler Notes: IMHO a big variety of additional Design Patterns which are relevant for big complex systems. VERY HARD
F.Brookes «The Mythical Man-Month: Essays on Software Engineering» Notes: «Spiritual» books ... easy to read, almost fiction ...recommend to read twice. EASY
Tom de Marco «Peopleware: Productive Projects and Teams.» Notes: «Spiritual» book... easy to read, almost fiction... recommend to read twice. EASY
Tom de Marco «The Deadline: A Novel About Project Management» Notes: «Spiritual» book... easy to read, almost fiction... recommend to read twice EASY
Kent Beck «Extreme programming. Development through testing» Notes: IMHO easy to read book, conceptually holistic book, with useful examples EASY
Разделы Java core; Refactoring fundamentals (упор на "from conditional to polymorphism" и "from polymorphism to conditional "); Unit Testing fundamentals including Mocks; Design Patterns fundamentals (Decorator, Builder, Singleton, Abstract Factory); Architecture (essentials) Coding standards; Code review procedure; API Automated Testing fundamentals*; WebUI, Desktop UI, Rest Services essentials;

Видео

На мой взгляд очень полезные концептуальные доклады о "физическом смысле" ООП: https://www.youtube.com/watch?v=Qx0_kFM52oU https://www.youtube.com/watch?v=CRt1XtW6-Fs https://www.youtube.com/watch?v=LeKqHN7l4Yk

SOLID и co https://www.youtube.com/watch?v=xG6NOxiOLhU https://www.youtube.com/watch?v=vKR5eR_RCQU https://www.youtube.com/watch?v=tJnTKLe8aKE https://www.youtube.com/watch?v=QsdWjWiYk8g

https://www.youtube.com/watch?v=ZuYliyKgRQQ https://www.youtube.com/watch?v=q4fvAxTapoc https://www.youtube.com/watch?v=j9vXFi6ttoI https://www.youtube.com/watch?v=MSuFztTpl0s

Для тех кто хочет войти в IT через Автоматизированное и в меньшей степени Ручное тестирование:

Легкое видео об общих вопросах Автоматизации тестирования: https://www.youtube.com/watch?v=GSFNBa9xSsE
https://www.youtube.com/watch?v=q4D8u9onKyo
Java UI Automation - эволюция учебного проекта https://www.youtube.com/watch?v=4KedRvkGuB0 https://www.youtube.com/watch?v=ck5gSJSL-cE https://www.youtube.com/watch?v=Bl3sYyGfB0c В этом году я запишу аналогичные видео на Python :))))
Около технические видео про Python Automation (в Java и других языках все аналогично) https://www.youtube.com/watch?v=dnWQiDx_vhI https://www.youtube.com/watch?v=MSuFztTpl0s
Связка Автоматизации и Ручного тестирования; https://www.youtube.com/watch?v=wb7gvPp0tJ0 https://www.youtube.com/watch?v=Vurf7G1JgG8
Полезный playlist в основном с моими лекциями в ПВТ: https://www.youtube.com/watch?v=UO_nMivQGKQ&list=PLkppYVTFAynCYVxyA1z8EXMxJjUsn6t1A&index=4

Links https://spring.io/guides/gs/rest-service/ https://spring.io/guides/tutorials/rest/ https://github.com/rest-assured/rest-assured/wiki/Usage

Полезные статьи Мартина Фаулера об Организации UI https://martinfowler.com/eaaDev/uiArchs.html https://martinfowler.com/eaaDev/SupervisingPresenter.html https://martinfowler.com/eaaDev/PassiveScreen.html

План лекций Промышленно программирование на Java https://docs.google.com/document/d/1moZNpOSvzbggsv7kDDh8gmWB0lzVDQiBIpsmP5-20qQ/edit Идеальный конечный результат: у каждого студента по завершении курса под git должна лежать сквозная работа промышленного качества, с базовой Архитектурой и Дизайном, с 5+ Design Patterns, элементарным UI и сервисом, сторонними библиотеками и автоматическими тестами согласно Пирамиде Тестирования. В дальнейшем эти подходы студенты будут применять \ оттачивать при работе над Курсовыми и Дипломными работами. В конце почти каждой лекции задается простое атомарное задание для закрепления пройденного материала, так, после каждой лекции по DP было необходимо реализовать пройденный DP в указанном контексте (по-настоящему хорошо, работает лишь десяток студентов). И конечно задания преподавателей практики! На итоговую оценку влияет: коллоквиумы, оценка за работу преподавателя практики, небольшие практические задачи после части лекций, сквозная лабораторная работа и конечно экзамен. Небольшая часть студентов получит автомат, в том числе за активность на лекциях и работу у доски.

Разное Возможно не бесполезная информация для молодых специалистов об оформлении CV: Все это необходимо чтобы не завалить пре-скрининг! Это очень обидно и основная проблема почему Ваше письмо прочитали 500 раз, но ни разу не пригласили на следующий этап. Сформулирую общие рекомендации к CV:

Главное пройти пре-скрининг: На каждую позицию приходят сотни резюме, в том числе откровенно пустых. Пре-скринингом занимаются не IT специалисты, в 95% случаев это выпускницы Ин-Яза, перегруженные работой, без понимания IT специфики, работающие строго по Checklist-у.
Обязательно исходите из принципа наименьшего удивления.
Указывайте только релевантный опыт.
Никогда не обманывайте, НО обязательно не договаривайте, если есть сомнения (см принцип Наименьшего Удивления) Практические советы:
Если у вас не топовое образование, то сократите или вообще умолчите, например: a) Никогда не указывайте техникумы, напишите Техническое или Экономическое - иначе можно не пройти пре-скрининг!
b) Никогда не указывайте ВУЗ-ы 25 эшелона, вроде Заочное отделение Сельхоз Академии имени Неизвестно Кого “условно” в Сызрани, напишите просто Высшее - иначе можно не пройти пре-скрининг!
Не указывайте возраст (если Вы слишком юн или наоборот есть сомнения что слишком "супер-стар")
Никогда не добавляйте фото. Во-первых, это международная рекомендация, во-вторых, может не повезти и Вас негативно встретят по одежке, даже если Вы писаный красавец или красавица. Технические советы: 1)Если Вы прошли курсы, не расписывайте детально что Вы делали, у многих описание занимает пол страницы, все рекрутеры и так в курсе программы популярных курсов. 2)Обязательно укажите уровень владения технологией, а не просто список. Причем, 90% должно быть novice, 2-3 skill-a intermediate, и в очень-очень редких случаев 1 - Advanced. Иначе приходится регулярно ржать в голос над Резюме, я С++ Архитектор с 20+ летним стажем и у меня С++ Advanced, потому что Expert лишь у Страуструпа, а intern присылает резюме с пометкой Expert, так как учил в ВУЗ-е С++ целых 2 семестра и получил 10 :)
Обязательно укажите ОС: Windows, Linux, Android, iOS ... даже если с какой-то из них не работали! Просто сразу же после размещения хватайте у друзей Мобильный с незнакомой ОС и устанавливайте на «виртуалку» незнакомую ОС.
Обязательно укажите virtualization engine: Virtual-Box, Hyper-V, VM-Ware и так далее! ... даже если никогда не работали! Просто сразу же после размещения резюме начинайте знакомиться.
Обязательно укажите методологии разработки ПО: Agile, Kanban, Scrum, ScrumBan и указывайте novice. Если не знакомы - читайте литературу, что бы знать основные определения!
Обязательно укажите уровень владения Английским языком. Если уровень A1 - A2, так же обязательно укажите что вы сейчас изучаете Английский и ресурс, хотя бы https://www.bbc.co.uk/learningenglish
Подумайте, что ответите на вопрос «какие книги по IT вы прочитали за последний год», если книг нет, начинайте читать и продумайте план чтения, что бы при звонке или первом интервью его озвучить.
Перед интервью поговорите сами с собой на Английском хотя бы 15 минут, чтобы разговориться. Очень полезно послушать \ посмотреть видео на Английском накануне.
И пред-последнее :) Обязательно заведите "официальный" email, Skype или другие средства связи. Мы все понимаем, что большинство завело акаунты в школе или студенчестве, поэтому названия безумные, вроде sex-machine или superstar, но .... осадочек остается ... завидите контакты для работы с рабочими названиями на основе ФИО.
В идеале обязательно укажите линк на git с Вашими учебными проектами.
