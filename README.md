# Spring-app

  ## Оглавление

* [Inversion of Control (Инверсия управления)](#inversion-of-control-инверсия-управления)
* [Dependency Injection (Внедрение зависимостей)](#dependency-injection-внедрение-зависимостей)
  * [Способы](#способы)
  * [Внедрение зависимостей из внешнего файла](#внедрение-зависимостей-из-внешнего-файла)
* [Scope (Область видимости бинов)](#scope-область-видимости-бинов)
* [Bean Lifestyle (Жизненный цикл бина)](#bean-lifestyle-жизненный-цикл-бина)
* [Spring-аннотации](#spring-аннотации)
  * [Аннотации для компонентов](#аннотации-для-компонентов)
  * [Аннотации для конфигурации](#аннотации-для-конфигурации)
  * [Аннотации для Spring MVC](#аннотации-для-spring-mvc)
  * [Аннотации валидации форм](#аннотации-валидации-форм)
* [Spring MVC](#spring-mvc)
  * [DispatcherServlet](#dispatcherservlet)
  * [Конфигурация при помощи Java-кода](#конфигурация-при-помощи-java-кода)
  * [Model](#model)
* [Thymeleaf](#thymeleaf)
* [Связь с БД](#связь-с-БД)
  * [JDBC API](#jdbc-api)
  * [JdbcTemplate](#jdbctemplate)
  * [Hibernate](#hibernate)

Inversion of Control (Инверсия управления)
==========================================
Принцип объектно-ориентированного программирования, 
используемый для уменьшения зацепления (связанности) в компьютерных программах. 
Также архитектурное решение интеграции, упрощающее расширение возможностей системы, 
при котором поток управления программы контролируется фреймворком.

В обычной программе программист сам решает, в какой последовательности делать вызовы процедур. 
Но, если используется фреймворк, программист может разместить свой код в определенных точках выполнения 
(используя callback или другие механизмы), затем запустить «главную функцию» фреймворка, 
которая обеспечит все выполнение и вызовет код программиста тогда, когда это будет необходимо. 
Как следствие, происходит утеря контроля над выполнением кода — это и называется инверсией управления 
(фреймворк управляет кодом программиста, а не программист управляет фреймворком).

Dependency Injection (Внедрение зависимостей)
=============================================
Работа фреймворка, обеспечивающая внедрение зависимости, 
описывается следующим образом. 
Приложение, независимо от оформления, 
исполняется внутри контейнера IoC, предоставляемого фреймворком. 
Часть объектов в программе по-прежнему создается обычным способом языка программирования, 
часть создается контейнером на основе предоставленной ему конфигурации.

Условно, если объекту нужно получить доступ к определенному сервису, 
объект берет на себя обязанность по доступу к этому сервису: 
он или получает прямую ссылку на местонахождение сервиса, 
или обращается к известному «сервис-локатору» и запрашивает ссылку на реализацию определенного типа сервиса. 
Используя же внедрение зависимости, объект просто предоставляет свойство, которое в состоянии хранить ссылку на нужный тип сервиса; 
и когда объект создается, ссылка на реализацию нужного типа сервиса автоматически вставляется в это свойство (поле), используя средства среды.

## Способы:

* ### конструктор:
    с помощью тега `<constructor-arg value=""/>` в xml-файле передаётся значение в конструктор класса 
  
* ### setter'ы
  с помощью тега `<property name="" ref=""/>` 
  (вместо ссылки может передаваться значение `value = ""`) в xml-файле передаётся значения в сеттеры соответствующих величин

* ### аннотации:
  при помощи аанотаций в коде можно внедрять зависимости и управлять ими.

## Внедрение зависимостей из внешнего файла:
Вместо того чтобы прописывать каждое значение в xml-файле, 
можно создать properties-файл и импортировать значения из него. 
Предварительно, в xml-файле необходимо прописать `<context:property-placeholder location=""/>`.

Scope (Область видимости бинов):
================================

Когда мы определяем bean в Spring Framework, 
у нас есть возможность объявить область видимости этого компонента. 
Настраивается внутри тега `<bean>` при помощи `scope = ""`

* ### singleton

  Определяет один единственный объект бина для каждого контейнера Spring IoC (используется по умолчанию).

* ### prototype

  Создаёт новый объект при каждом вызове. 
  Позволяет иметь любое количество экземпляров бина.

Bean Lifestyle (Жизненный цикл бина)
====================================

При запуске Spring-приложения запускается Spring-контейнер со всеми бинами. 
Создаются объекты бина в соответствии с xml-файлом, 
в бин внедряются зависимости, вызывается **init-method**. 
После того, как пользователь закончит работу с приложением, 
вызывается **destroy-method** и приложение прекращает работу.

Эти методы могут иметь любое название, модификатор доступа, 
тип возвращаемого значения 
(но перехватить его невозможно, так что обычно используют void), 
**но не должны принимать на вход аргументы**.

Фабричный метод (**factory-method**) - паттерн программирования, смысл которого в том,
что программист не сам создаёт объекты через оператор `new`,
а при помощи метода, внутри которого происходит инициализация. При
использовании этого метода, Spring позволяет его указать.

* ### init-method

  Запускается в ходе инициализации бина. 
  Обычно используется для обращения к внешним файлам, 
  запуска БД или инициализации ресурсов. 
  Инициализируется в теге `<bean>` при помощи команды `init-method = ""` и названия нужного метода в классе.
  
* ### destroy-method

  Запускается в ходе уничтожения бина (при завершении приложения). 
  Обычно используется для очищения ресурсов, 
  закрытия потоков или закрытия доступа к БД. 
  Инициализируется в теге `<bean>` при помощи команды `destroy-method = ""` 
  и названия нужного метода в классе. 
  **Не вызывается для бинов с scope = "prototype"**

* ### factory-method
  Используется для реализации паттерна *фабричный метод*. 
  Нужен для настройки процесса создания нового объекта.
  Инициализируется в теге `<bean>` при помощи команды `factory-method = ""`
  и названия нужного метода в классе.
  
Spring-аннотации
================

Spring сканирует все классы, находит в них специальные аннотации
и создаёт бины этих классов и необходимые зависимости.

* ## Аннотации для компонентов

  * ### @Component
    Аннотация для пометки классов, бины которых нужно создать.
    Можно указать **id**, но если не указывать название будет *сМаленькойБуквы*.
  
    В xml-файле необходимо указать директорию для сканирования при помощи 
    `<context:component-scan base-package=""/>`
  
  * ### @Autowired
    Аннотация, благодаря которой Spring сам ищет подходящий бин для зависимости 
    и [внедряет](#dependency-injection-внедрение-зависимостей) его. Если не находит ни одного подходящего бина - ошибка.
    Если находит несколько - неоднозначность.
  
    Аннотацию можно использовать к полям, сетторам, конструкторам.

  * ### @Qualifier
    Аннотация для выбора нужного бина при неоднозначности `@Autowired`.
    В скобках необходимо указать id приоритетного бина.

    Аннотацию можно использовать к полям, сетторам, конструкторам.

  * ### @Value
    Аннотация для выбора значения из properties-файла 

  * ### @Scope
    Аннотация для присвоения [области видимости](#scope-область-видимости-бинов) бину.

  * ### @PostConstruct
    Аннотация для обозначения метода, как [init-method](#init-method).

  * ### @PreDestroy
    Аннотация для обозначения метода, как [destroy-method](#destroy-method).

* ## Аннотации для конфигурации

  * ### @Configuration
    Аннотация, которая помечает класс, как конфигурацию для Spring-приложения.

  * ### @ComponentScan
    Аннотация, заменяющая тег для поиска [классов с аннотацией @Component](#component).

  * ### @Bean
    Аннотация, помечающая в [конфигурационном классе](#configuration),
    какой бин нужно создать. 
  
  * ### @PropertySource
    Аннотация, заменяющая тег поиска [внешнего файла со значениями](#внедрение-зависимостей-из-внешнего-файла).

* ## Аннотации для Spring MVC

  * ### @Controller
    Аннотация, помечающая класс, как [контроллер](#spring-mvc)
  
  * ### @GetMapping
    Аннотация указывает URL-путь, 
    по которому вызовется GET-метод [контроллера](#spring-mvc).
    
  * ### @PostMapping
    Аннотация указывает URL-путь,
    по которому вызовется POST-метод [контроллера](#spring-mvc).
    
  * ### @PatchMapping
    Аннотация указывает URL-путь,
    по которому вызовется PATCH-метод [контроллера](#spring-mvc).
    Необходимо создать фильтр в конфигурационном файле для корректной
    обработки запросов.

  * ### @DeleteMapping
    Аннотация указывает URL-путь,
    по которому вызовется DELETE-метод [контроллера](#spring-mvc).
    Необходимо создать фильтр в конфигурационном файле для корректной
    обработки запросов.

  * ### @RequestMapping
    Аннотация задаёт конкретный URL,
    по которому можно в дальнейшем обращаться к методам [контроллера](#spring-mvc).

  * ### @EnableWebMvc
    Аннотация заменяет тег `<mvc:annotation-driven/>`, 
    который нужен для конфигурации Spring MVC. 
    
  * ### @RequestParam
    Аннотация для параметра метода [контроллера](#spring-mvc),
    которая указывает, по какому ключу запроса получить значение параметра.
    Чтобы сервер не выдавал ошибку при отсутствии параметров,
    в аннотации нужно указать `required = false`.
    
  * ### @PathVariable
    Аннотация для параметра метода [контроллера](#spring-mvc),
    которая устанавливает доступ к методу по добавочной части к URL, 
    указанной в ней в аннотации [@GetMapping](#getmapping) при помощии
    фигурных скобок `{}`.

  * ### @ModelAttribute
    + Если используется к методу контроллера: 
      
      аннотация для добавления пары ключ-значение в каждую [модель](#spring-mvc)
      [контроллера](#spring-mvc).
      
    + Если используется к параметру метода] контроллера:
      
      автоматически принимает значения из запроса, создаёт объект и добавляет его в [модель](#spring-mvc).

* ## Аннотации валидации форм

  * ### @Valid
    Аннотация для параметра метода [контроллера](#spring-mvc), обозначающая, что нужно совершить
    проверку входных данных у параметра.
    
    Для отслеживания ошибок после параметра нужно указать параметр `BindingResult`.
    Также необходимо указать нужный [Thymeleaf](#thymeleaf)-тег в HTML-файле.
  * ### @NotEmpty
    Аннотация для обозначения поля, которое не может быть пустым.
    Аргументы: `message` для вывода сообщения ошибки.
  * ### @Size
    Аннотация для обозначения длины вводимых данных. Аргументы: `min`, `max`, `message`.
  * ### @Min
    Аннотация для числового значения. Обозначает минимальное допустимое вводимое число.
    Аргументы: `value`, `message`.
  * ### @Email
    Аннотация обозначает, что вводимое поле должно быть email'ом и проверяет при помощи
    встроенных регулярных выражений. Аргументы: `message` для вывода сообщения ошибки.


Spring MVC
==========
Один из компонентов Spring Framework, который позволяет  разрабатывать web-приложения на Java.
Используется архитектура **Model-View-Controller**.

+ **Model** - логика работы с данными.
+ **View** - логика представления, интерфейс.
+ **Controller** - логика навигации, обработки запросов.

Spring MVC приложение состоит из:

* классов Java (контроллеры, модели и т.д.) с аннотациями
* HTML-страницы, обычно с CSS-файлами.
* Spring конфигурация (XML, Java или аннотации)

## DispatcherServlet
Является входной точкой в Spring MVC. 
Реализует связь между HTTP request'ом и нужным контроллером.
Реализован за программиста командой Spring.

## Конфигурация при помощи Java-кода
Классом, который будет заменять `web.xml`,
необходимо (на выбор):
*  реализовать интерфейс `WebApplicationInitializer`. 
   И в метод `onStartUp()` поместить код из `web.xml`. 
   *(Больше писать самому, но больше можно настроить)*
  
* наследоваться от абстрактного класса `AbstractAnnotationConfigDispatcherServletInitializer`.
  *(Меньше кода, так как большая часть уже реализована)*
  
Конфигурационный класс необходимо реализовать при помощи [аннотаций для конфигурации](#аннотации-для-конфигурации).

## Model
[Модель](#spring-mvc) представлена в виде параметра класса `Model` для
метода [контроллера](#spring-mvc). 

Имеет метод `addAttribute()` для передачи пар ключ-значение.

В [представлении](#spring-mvc) можно использовать благодаря [Thymeleaf](#thymeleaf).

Thymeleaf
=========
шаблонизатор Java XML / XHTML / HTML5, 
который может работать как в веб-среде, так и вне ее. 
Он лучше подходит для обслуживания XHTML / HTML5 на уровне 
представления веб-приложений на основе MVC.

Для подключения необходимо скачать maven-зависимость 
и указать ссылку в HTML-файле в теге `<html>` `xmlns:th="http://thymeleaf.org"`.

Обозначения:
* `${объект}` - получение объекта из [модели](#model).
* `@{/ссылка}` - URL-ссылка.
* `.../{переменная}/...(переменная=${объект.метод()})` - получение переменной из метода объекта.
* `*{переменная}` - если тег, в котором вызывается, наследник тега, в котором определили объект.
* `${#fields}` - получение полей объявленного объекта

Готовые теги:
* `<p th:text = ${ключ}/>` - получения текста по ключу из [модели](#model).
* `<div th:each="итератор : ${множество}">` - создание блока, 
  а котором можно взаимодействовать со всеми элементами множества из [модели](#model).
* `<form th:method="тип метода" th:action="@{/url для отправки запроса}" th:object="${объект}">` -
  создание формы, в которой будет взаимодействие с указанным объектом из [модели](#model). 
  После заполнения формы запрос с введенными параметрами будет отправлен
  по указанному URL.
* `<input th:field="*{название поля объекта}">` - форма для заполнения определённого
  поля объекта из [модели](#model).
* `<p th:if="${#fields.hasErrors('поле')}" th:errors="*{поле}"/>` - вывод
  ошибки, если не вводимые данные некорректны.
  
Связь с БД
==========
Существуют 3 способа соединения с базой данных:

* ## JDBC API
  самый низкоуровневый способ. (Сами выполняем все запросы 
  и сами переводим все Java-объекты в строки таблицы)
  
* ## JdbcTemplate
  обертка вокруг [JDBC API](#jdbc-api). Часть Spring Framework. 
  Представляет абстракции, выполняет часть операций сама.
  
* ## Hibernate
  самый высокий уровень абстракции. Практически не нужно писать запросы к БД.
  Автоматически переводит Java-объекты в строки таблицы и наоборот.
  Может автоматически создавать таблицы в БД на основании Java-классов.
  Реализует ORM-модель (Object-Relational Mapping).